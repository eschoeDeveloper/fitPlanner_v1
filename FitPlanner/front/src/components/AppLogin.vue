<template>

  <section class="h-100 h-custom" style="background-color: #8fc4b7;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-8">
          <div class="card rounded-3">
            <img src="https://health.chosun.com/site/data/img_dir/2018/03/07/2018030700812_2.jpg"
                 class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                 alt="Sample photo">
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner</h3>

              <form class="px-md-2">

                <div class="form-outline mb-4">
                  <label class="form-label" for="inputId">아이디</label>
                  <input type="email" ref="inputId"  name="inputId" id="inputId" class="form-control" :placeholder="placeholderId"/>
                  <p ref="idGuide" style="display:none; color: red; font-size: 9px;">입력하신 아이디가 올바르지 않습니다.</p>
                </div>

                <div class="form-outline mb-4">
                  <label class="form-label" for="inputPwd">암호</label>
                  <input type="password" ref="inputPwd" name="inputPwd" id="inputPwd" class="form-control" :placeholder="placeholderPwd"/>
                  <p ref="pwdGuide" style="display:none; color:red; font-size: 9px;">입력하신 비밀번호가 올바르지 않습니다.</p>
                </div>

                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline mb-4">
                      <input class="form-check-input" type="checkbox" ref="idSave" id="idSave" v-on:change="saveLoginIdByCookie()" true-value="true" false-value="false"/>
                      <label class="form-check-label" for="idSave"> 아이디 저장 </label>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-6 mb-4">
                    <a class="link-primary" @click="goPwdReset()">사용중인 암호를 잊으셨나요?</a>
                  </div>
                </div>

                <div class="form-outline mb-4">
                  <button type="button" @click="goSignUp()" class="btn btn-primary btn-block mb-4">회원가입</button>
                  <button type="button" @click="goLogIn()" class="btn btn-success btn-block mb-4 ms-1">로그인</button>
                </div>

              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>


  <!-- Section: Design Block -->
</template>

<script>
export default {

  name: "AppLogin",
  data() {
    return {
      placeholderId : "Please Enter ID",
      placeholderPwd : "Please Enter Password"
    }
  },
  mounted() {
    window.addEventListener("load", () => this.setLoginIdByCookie());
  },
  methods: {
    setLoginIdByCookie() {

      // sso 로그인
      if(this.$cookies.isKey("ssoLogin")) {

        if( this.$cookies.get("ssoLogin") === "Y" ) {
          this.$router.push({ path: '/main' });
        }

      }

      if(this.$cookies.isKey("cookie_loginId")) {
        this.$refs.inputId.value = this.$cookies.get("cookie_loginId");
        this.$refs.idSave.checked = true;
      }

    },
    goSignUp() {
      this.$router.push({ path: '/signUp' });
    },
    goPwdReset() {
      this.$router.push({ path: '/pwdReset' });
    },
    goLogIn() {

      const idGuide = this.$refs.idGuide;
      const pwdGuide = this.$refs.pwdGuide;

      const checkId = this.$refs.inputId.value;
      const checkPwd = this.$refs.inputPwd.value;

      if(!checkId) {
        idGuide.style.display = "block";
        return false;
      } else {
        idGuide.style.display = "none";
      }

      if(!checkPwd) {
        pwdGuide.style.display = "block";
        return false;
      } else {
        pwdGuide.style.display = "none";
      }



      const JSONData = JSON.stringify({
        id: checkId,
        password: checkPwd
      });

      this.axios.post("/api/member/login", JSONData, {
        withCredentials: true,
      })
      .then((response) => {

        let respJson = JSON.parse(JSON.stringify(response.data));
        let respData = JSON.parse(respJson.data);
        let respCode = respJson.code;

        this.$cookies.set("ssoLogin", respData["ssoLogin"]);

        if(Number(respCode) < 400 ) {
          this.$router.push({ path: '/main' });
        } else if (Number(respCode) >= 400) {
          this.$router.push({ path: '/loginFail' });
        }

      }).catch((error) => {
        console.log(error);
      });

    },
    saveLoginIdByCookie() {

      if(this.$refs.idSave.checked) {
        this.$cookies.set("cookie_loginId", this.$refs.inputId.value);
      } else {
        if(this.$cookies.isKey("cookie_loginId")) this.$cookies.remove("cookie_loginId");
      }

    }

  }


}
</script>

<style scoped>
  @media (min-width: 1025px) {
    .h-custom {
      height: 100vh !important;
    }
  }
</style>
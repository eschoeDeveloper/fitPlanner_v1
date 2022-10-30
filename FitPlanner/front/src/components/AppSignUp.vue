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

                <div class="row">

                  <div class="col-7">
                    <div class="form-outline">
                      <label class="form-label" for="id">아이디</label>
                      <input type="text" ref="id" id="id" class="form-control" />
                      <p ref="idGuide" style="display:none; color: red; font-size: 9px;">아이디를 입력해주세요.</p>
                      <p ref="idNotUseGuide" style="display:none; color: deepskyblue; font-size: 9px;">이미 사용중인 아이디입니다.</p>
                      <p ref="idUseGuide" style="display:none; color:red; font-size:9px;">사용하실 수 있는 아이디입니다.</p>
                      <p ref="errorGuide" style="display:none; color:darkgoldenrod; font-size:9px;">사용하실 수 있는 아이디입니다.</p>
                    </div>
                  </div>

                  <div class="col-5 mt-4">
                    <div class="form-outline mt-2">
                      <button type="button" class="btn btn-info btn-block" @click="goCheckId()">중복확인</button>
                    </div>
                  </div>

                </div>

                <div class="row mt-2">
                  <div class="form-outline">
                    <label class="form-label" for="password">암호</label>
                    <input type="password" ref="password" id="password" class="form-control" />
                    <p ref="passwordGuide" style="display:none; color: red; font-size: 9px;">암호를 확인해주세요.</p>
                  </div>
                </div>

                <div class="row mt-2">
                  <div class="form-outline">
                    <label class="form-label" for="checkPassword">암호 확인</label>
                    <input type="password" ref="checkPassword" id="checkPassword" class="form-control" />
                    <p ref="checkPasswordGuide1" style="display:none; color: red; font-size: 9px;">암호 확인을 입력하지 않았습니다.</p>
                    <p ref="checkPasswordGuide2" style="display:none; color: red; font-size: 9px;">입력된 암호가 일치하지 않습니다.</p>
                  </div>
                </div>

                <div class="row mt-2">
                  <div class="col-6">
                    <div class="form-outline">
                      <label class="form-label" for="name">이름</label>
                      <input type="text" ref="name" id="name" class="form-control" />
                      <p ref="nameGuide" style="display:none; color: red; font-size: 9px;">이름을 입력해주세요.</p>
                    </div>
                  </div>

                  <div class="col-6">
                    <div class="form-outline">
                      <label class="form-label" for="phone">휴대폰번호</label>
                      <input type="text" ref="phone" id="phone" class="form-control" />
                      <p ref="phoneGuide" style="color: blue; font-size: 9px;">휴대폰 번호를 입력하시면 다양한 소식을 제공받을 수 있습니다.</p>
                    </div>
                  </div>
                </div>

                <div class="row mt-2">

                  <div class="col-6">
                    <div class="form-outline">
                      <label class="form-label" for="email">이메일</label>
                      <input type="email" ref="email" id="email" class="form-control" />
                      <p ref="phoneGuide" style="color: blue; font-size: 9px;">암호 찾기시 이메일을 통하여 인증번호가 발송됩니다.</p>
                    </div>
                  </div>

                  <div class="col-6">

                    <div class="form-outline datepicker">
                      <label for="birthday" class="form-label">생년월일</label>
                      <input type="text" ref="birthday" class="form-control" id="birthday" />
                      <p ref="birthdayGuide" style="color: blue; font-size: 9px;">생년월일을 입력하시면 보다 다양한 운동정보를 제공할 수 있습니다.</p>
                    </div>

                  </div>

                </div>

                <div class="row mt-2">


                  <div class="form-outline">

                    <select class="form-select" ref="gender" id="gender">
                      <option value="" selected disabled>성별</option>
                      <option value="G01">남성</option>
                      <option value="G02">여성</option>
                    </select>

                    <p ref="genderGuide" style="display:none; color: red; font-size: 9px;">성별을 입력해주세요.</p>

                  </div>

                </div>

                <div class="row mt-4">
                  <div class="form-outline">
                    <button type="button" ref="signUpBtn" @click="goSignUp()" class="btn btn-success btn-block mb-4" :disabled="isCheckId == 'N'">회원가입</button>
                    <button type="button" ref="signUpBtn" @click="goLogIn()" class="btn btn-primary btn-block mb-4 ms-1">로그인</button>
                  </div>
                </div>

              </form>

            </div>
          </div>
        </div>
      </div>
    </div>

  </section>
</template>

<script>

export default {
  name: "AppSignUp",
  data() {
    return {
      isCheckId : "N",
      toastMsg : ""
    }
  },
  methods: {

    goCheckId() {

      const inputId = this.$refs.id.value;
      const idGuide = this.$refs.idGuide;
      const idUseGuide = this.$refs.idUseGuide;
      const idNotUseGuide = this.$refs.idNotUseGuide;
      const errorGuide = this.$refs.errorGuide;

      idUseGuide.style.display = "none";
      idNotUseGuide.style.display = "none";
      errorGuide.style.display = "none";

      if(!inputId) { idGuide.style.display = "block"; return false; }
      else { idGuide.style.display = "none"; }

      let encodeCheckId = btoa(inputId);

      this.axios.post("/api/member/checkId/" + encodeCheckId, {}, {
        withCredentials: true,
      })
      .then((response) => {

          let respData = response.data;

          if(respData === "N") {
            idNotUseGuide.style.display = "block";
          } else if(respData === "Y"){
            idUseGuide.style.display = "block";
          }

          this.$data.isCheckId = respData;

          console.log("ssoLogin = {}", respData);

      }).catch((error) => {
        console.log(error);
      });

    },
    goSignUp() {

      const inputId = this.$refs.id.value;
      const inputPassword = this.$refs.password.value;
      const checkPassword = this.$refs.checkPassword.value;
      const inputName = this.$refs.name.value;
      const inputGender = this.$refs.gender.value;
      const inputBirthday = this.$refs.birthday.value;
      const inputPhone = this.$refs.phone.value;
      const inputEmail = this.$refs.email.value;

      const idGuide = this.$refs.idGuide;
      const nameGuide = this.$refs.nameGuide;
      const passwordGuide = this.$refs.passwordGuide;
      const checkPasswordGuide1 = this.$refs.checkPasswordGuide1;
      const checkPasswordGuide2 = this.$refs.checkPasswordGuide2;
      const genderGuide = this.$refs.genderGuide;
      const birthdayGuide = this.$refs.birthdayGuide;

      this.$refs.idNotUseGuide.style.display = "none";
      this.$refs.idUseGuide.style.display = "none";

      if(!inputId) { idGuide.style.display = "block"; return false; }
      else { idGuide.style.display = "none"; }

      if(!inputPassword) { passwordGuide.style.display = "block"; return false; }
      else { passwordGuide.style.display = "none"; }

      if(!checkPassword) { checkPasswordGuide1.style.display = "block"; return false; }
      else { checkPasswordGuide1.style.display = "none"; }

      if(checkPassword && (checkPassword != inputPassword)) {
        checkPasswordGuide2.style.display = "block"; return false;
      } else { checkPasswordGuide2.style.display = "none"; }

      if(!inputName) { nameGuide.style.display = "block"; return false; }
      else { nameGuide.style.display = "none"; }

      if(!inputGender) { genderGuide.style.display = "block"; return false; }
      else { genderGuide.style.display = "none"; }

      if(!inputBirthday) { birthdayGuide.style.display = "block"; return false; }
      else { birthdayGuide.style.display = "none"; }

      console.log("this.$data.isCheckId = {}", this.$data.isCheckId);

        const JsonData = JSON.stringify({
          id : inputId,
          name : inputName,
          password : inputPassword,
          gender : inputGender,
          birthday : inputBirthday,
          phone : inputPhone,
          email : inputEmail
        });

        this.axios.post("/api/member/signUp", JsonData, {
          withCredentials: true,
        })
        .then((response) => {

          let respJson = JSON.parse(JSON.stringify(response.data));
          let respCode = respJson.code;
          let resMsg = "";

          if (Number(respCode) >= 300) {
            resMsg = "회원가입 오류! 관리자에게 문의하여 주세요.";
          } else if(Number(respCode < 300)) {
            resMsg = "회원가입 완료! FitPlanner 회원이 되신것을 축하합니다.";
          }

          this.$router.push({name: 'AppSignUpResult', params:{respCode: respCode, respMsg: resMsg}});

        }).catch((error) => {
          console.log(error);
        });


    },
    goLogIn() {
      this.$router.push({path:"/"});
    },
    showToast(msg) {
      alert(msg);
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
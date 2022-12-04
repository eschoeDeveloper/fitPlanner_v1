<template>

  <section class="h-100 h-custom" style="background-color: #8fc4b7;">

    <div class="container h-100">

      <div class="row d-flex justify-content-center align-items-center h-100">

        <div class="col-md-8">

          <div class="card rounded-3">

            <img src="../../assets/image/fitPlanner_logo.png"
                 class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                 alt="Sample photo">
            <div class="card-body p-4 p-md-5">

              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner 비밀번호 재설정</h3>

              <form class="px-md-2">

                <div class="form-outline mb-4">
                  <label class="form-label" for="newPassword">새 비밀번호</label>
                  <input type="text" ref="newPassword"  name="newPassword" id="newPassword" class="form-control" :placeholder="placeholderNewPassword" />

                  <label class="form-label" for="newCheckPassword">비밀번호 확인</label>
                  <input type="text" ref="newCheckPassword"  name="newCheckPassword" id="newCheckPassword" class="form-control" :placeholder="placeholderNewCheckPassword" />

                  <p ref="inputGuide" style="display:none; color: red; font-size: 9px;">새 비밀번호가 일치하지 않거나, 입력되지 않았습니다.</p>
                </div>

                <div class="form-outline mb-4">
                  <button type="button" ref="resetBtn" @click="goPwdResetExecute()" class="btn btn-warning btn-block mb-4">비밀번호 변경</button>
                  <button type="button" ref="loginBtn" @click="goLogIn()" class="btn btn-success btn-block mb-4 ms-1">로그인</button>
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
    name: "AppPwdResetExecute",
    data() {
      return {
        placeholderNewPassword: "Enter Input New Password",
        placeholderNewCheckPassword: "Enter Input New Check Password",
      }
    },
    methods: {
      goPwdResetExecute() {

        const newPassword = this.$refs.newPassword.value;
        const newCheckPassword = this.$refs.newCheckPassword.value;

        if(!newPassword || !newCheckPassword || (newPassword != newCheckPassword)) {
          this.$refs.authNoGuide.style.display = "block";
          return false;
        } else {
          this.$refs.authNoGuide.style.display = "none";
        }

        const JsonData = JSON.stringify(
            {
              newPassword : btoa(newPassword)
            }
        );

        this.axios.post("/api/member/pwdResetExecute", JsonData, {})
        .then((response) => {

          let respJson = JSON.parse(JSON.stringify(response.data));
          let jsonData = JSON.parse(respJson.data);

          let isNext = jsonData.get("isNext");

          if(isNext == "Y") {
            this.$router.push({name: "AppLogin"});
          } else {
            this.$toast.show("잘못된 인증번호입니다. 다시 확인해주세요.");
            setTimeout(() => {
              this.$toast.clear;
            }, 3000);
          }

        }).catch((error) => {
          console.log(error);
        });

      },
      goLogIn() {
        this.$router.push({ name: "AppLogin" });
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
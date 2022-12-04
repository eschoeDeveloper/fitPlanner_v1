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

              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner 암호찾기 인증</h3>

              <form class="px-md-2">

                <div class="form-outline mb-4">
                  <label class="form-label" for="inputAuthNo">인증번호</label>
                  <input type="text" ref="inputAuthNo"  name="inputAuthNo" id="inputAuthNo" class="form-control" :placeholder="placeholderAuthNo" v-on:input="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
                  <p ref="authNoGuide" style="display:none; color: red; font-size: 9px;">인증번호를 입력해주세요</p>
                </div>

                <div class="form-outline mb-4">
                  <button type="button" ref="resetBtn" @click="goPwdResetAuth()" class="btn btn-warning btn-block mb-4">인증번호 입력</button>
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
    name: "AppPwdResetAuth",
    data() {
      return {
        placeholderAuthNo: "Enter Input AuthNo"
      }
    },
    methods: {
      goPwdReset() {

        const inputAuthNo = this.$refs.inputAuthNo.value;

        if(!inputAuthNo) {
          this.$refs.authNoGuide.style.display = "block";
          return false;
        } else {
          this.$refs.authNoGuide.style.display = "none";
        }

        const JsonData = JSON.stringify(
            {
              inputAuthNo : inputAuthNo
            }
        );

        this.axios.post("/api/member/pwdResetAuth", JsonData, {})
        .then((response) => {

          let respJson = JSON.parse(JSON.stringify(response.data));
          let jsonData = JSON.parse(respJson.data);

          let isNext = jsonData.get("isNext");

          if(isNext == "Y") {
            this.$router.push({name: "AppPwdResetExecute"});
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
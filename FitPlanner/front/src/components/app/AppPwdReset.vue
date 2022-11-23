<template>
  <section class="h-100 h-custom" style="background-color: #8fc4b7;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-8">
          <div class="card rounded-3">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
                 class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                 alt="Sample photo">
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner 암호찾기</h3>

              <form class="px-md-2">

                <div class="form-outline mb-4">
                  <label class="form-label" for="inputEmail">이메일</label>
                  <input type="email" ref="inputEmail"  name="inputEmail" id="inputEmail" class="form-control" :placeholder="placeholderEmail"/>
                  <p ref="emailGuide" style="display:none; color: red; font-size: 9px;">이메일을 입력해주세요.</p>
                </div>

                <div class="form-outline mb-4">
                  <button type="button" ref="loginBtn" @click="goPwdReset()" class="btn btn-warning btn-block mb-4">암호찾기</button>
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
    name: "AppPwdReset.vue",
    data() {
      return {
        placeholderEmail: "Enter Sign In Input Email"
      }
    },
    methods: {
      goPwdReset() {

        const inputEmail = this.$refs.inputEmail.value;

        if(!inputEmail) {
          this.$refs.emailGuide.style.display = "block";
          return false;
        } else {
          this.$refs.emailGuide.style.display = "none";
        }

        const JsonData = JSON.stringify(
            {
              email : inputEmail
            }
        );

        this.axios.post("/api/member/pwdReset", JsonData, {
          withCredentials: true,
        })
        .then((response) => {
          console.log(response);
        }).catch((error) => {
          console.log(error);
        });

      },
      goLogIn() {
        this.$router.push({ path: '/' });
      }
    }
  }

</script>

<style scoped>

</style>
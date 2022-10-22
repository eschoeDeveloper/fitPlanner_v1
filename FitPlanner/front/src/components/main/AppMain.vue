<template>
  <!-- Section: Design Block -->
  <div class="container h-100">
    <section class="text-lg-start h-100">
      <div class="card mb-12 h-100">
        <div class="row g-0 d-flex align-items-center">
          <div class="col-lg-4 d-none d-lg-flex">
            <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"
                 class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
          </div>
          <div class="col-lg-8">
            <div class="card-body py-5 px-md-5">

              <form>
                <!-- Email input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="inputId">ID</label>
                  <input type="email" ref="inputId"  name="inputId" id="inputId" class="form-control" :placeholder="placeholderId"/>
                  <p ref="isIdGuide" style="display:none; color: red; font-size: 9px;">입력하신 아이디가 올바르지 않습니다.</p>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="inputPassword">Password</label>
                  <input type="password" ref="inputPassword" name="inputPassword" id="inputPassword" class="form-control" :placeholder="placeholderPwd"/>
                  <p ref="isPwdGuide" style="display:none; color:red; font-size: 9px;">입력하신 비밀번호가 올바르지 않습니다.</p>
                </div>

                <!-- 2 column grid layout for inline styling -->
                <div class="row">

                  <div class="row mb-2 d-flex justify-content-right">
                    <!-- Checkbox -->
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="form2Example31"/>
                      <label class="form-check-label" for="form2Example31"> 로그인 정보 저장 </label>
                    </div>
                  </div>

                  <div class="row mb-2 d-flex justify-content-center">
                    <div class="form-check">
                      <!-- Simple link -->
                      <a href="javascript:alert('기능 준비중');">비밀번호를 잊으셨나요?</a>
                    </div>
                  </div>

                  <div class="row mb-2 d-flex justify-content-right">
                    <button type="button" @click="goSignIn()" class="btn btn-primary btn-block mb-4">Sign In</button>
                    <button type="button" ref="loginBtn" @click="goLogIn()" class="btn btn-success btn-block mb-4">Log In</button>
                  </div>

                </div>

                <!-- Submit button -->

              </form>

            </div>

          </div>

        </div>

      </div>

    </section>

  </div>
  <!-- Section: Design Block -->
</template>

<script>
import axios from "axios";

export default {

  name: "AppMain",
  data() {
    return {
      placeholderId : "Please Enter ID",
      placeholderPwd : "Please Enter Password"
    }
  },
  methods: {

    goLogIn() {

      const checkId = this.$refs.inputId.value;
      const checkPwd = this.$refs.inputPassword.value;

      if(!checkId) {
        this.$refs.isIdGuide.style.display = "block";
        return false;
      } else {
        this.$refs.isIdGuide.style.display = "none";
      }

      if(!checkPwd) {
        this.$refs.isPwdGuide.style.display = "block";
        return false;
      } else {
        this.$refs.isPwdGuide.style.display = "none";
      }

      axios.post("/api/member/memberLogin", {
        loginId: checkId,
        loginPwd: checkPwd
      }, {
        withCredentials: true
      })
      .then((response) => {
        console.log(response);
      }).catch((error) => {
        console.log(error);
      });

    }

  }


}
</script>

<style scoped>
.rounded-t-5 {
  border-top-left-radius: 0.5rem;
  border-top-right-radius: 0.5rem;
}

@media (min-width: 992px) {
  .rounded-tr-lg-0 {
    border-top-right-radius: 0;
  }

  .rounded-bl-lg-5 {
    border-bottom-left-radius: 0.5rem;
  }
}
</style>
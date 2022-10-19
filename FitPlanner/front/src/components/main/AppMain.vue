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
                  <input type="email" name="inputId" id="inputId" class="form-control" :placeholder="placeholderId"/>
                  <p v-if="isIdGuide" style="color: red; font-size: 9px;">입력하신 아이디가 올바르지 않습니다.</p>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="inputPassword">Password</label>
                  <input type="password" name="inputPassword" id="inputPassword" class="form-control" :placeholder="placeholderPwd"/>
                  <p v-if="isPwdGuide" style="color:red; font-size: 9px;">입력하신 비밀번호가 올바르지 않습니다.</p>
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
                    <button type="button" @click="goSignIn" class="btn btn-primary btn-block mb-4">Sign In</button>
                    <button type="button" @click="goLogIn" class="btn btn-success btn-block mb-4">Log In</button>
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
export default {

  name: "AppMain",
  data() {

    return {

      placeholderId : "Please Enter ID",
      placeholderPwd : "Please Enter Password",
      isIdGuide: false,
      isPwdGuide: false

    }

  },
  methods: {
    goSignIn: () => {
      alert("페이지 준비중입니다.");
    },
    goLogIn: () => {


      this.isIdGuide = false;
      this.isPwdGuide = false;

      const inputId = document.querySelector("input[name=inputId]").value;
      const inputPassword = document.querySelector("input[name=inputPassword]").value;

      if(!inputId && !inputPassword){
        this.isIdGuide = true;
        this.isPwdGuide = true;
      }else if(!inputId) {
        this.isIdGuide = true;
      }else if(!inputPassword){
        this.isPwdGuide = true;
      }

    }

  },
  created() {
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
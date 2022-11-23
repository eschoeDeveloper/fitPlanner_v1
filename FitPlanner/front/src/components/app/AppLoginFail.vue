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
                  <p ref="loginFailGuide" style="color: red; font-size: 9px;">일치하는 회원정보가 없습니다.</p>
                </div>

                <div class="form-outline mb-4">
                  <button type="button" @click="goLogIn()" class="btn btn-success btn-block mb-4 ms-1">로그인 다시하기</button>
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
  name: "AppLoginFail.vue",
  methods : {
    goLogIn() {
      this.$router.push({ path: '/member/app' });
    }
  }
}
</script>

<style scoped>

</style>
<template>

  <section class="h-100 h-custom" style="background-color: #8fc4b7;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-8">
          <div class="card rounded-3">
            <img src="../assets/image/fitPlanner_logo.png"
                 class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                 alt="Sample photo">
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner</h3>
              <div class="jumbotron">
                <h6>아래 버튼 중에서 선택하세요.</h6>

                <hr class="hr-blurry">

                <div class="form-outline">
                    <button class="btn btn-sm btn-outline-secondary" @click="goAdmin()">관리자</button>
                    <button class="btn btn-sm btn-outline-secondary" style="margin-left: 5px" @click="goUserApp()">사용자</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "index",
  methods: {

    goAdmin() {
      location.href="http://localhost:8081";
    },
    goUserApp() {
      this.$router.push({name: "AppLogin"});
    }

  }
}
</script>

<style scoped>

</style>
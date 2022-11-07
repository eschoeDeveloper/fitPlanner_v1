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
                <h1 class="display-4">운동은 계획적으로 건강하게!</h1>
                <p class="lead">여러분의 운동 라이프를 도와드립니다. 운동의 스케줄을 설정하고, 종목에 따른 운동법을 배워보세요.</p>
                <hr class="my-4">
                <p>초보자, 연령대, 성별 상관없이 모두가 이용 가능한 FitPlanner와 함께 즐거운 운동 되세요!</p>
                <button class="btn btn-sm btn-outline-secondary" @click="goIndex()">다음으로</button>
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
  name: "information.vue",
  methods: {
   goIndex() {
     this.$router.push({path: "/index"});
   }
  }
}
</script>

<style scoped>

</style>
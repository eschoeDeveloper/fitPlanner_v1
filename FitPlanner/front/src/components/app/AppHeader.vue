<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
      <div class="container-fluid">
        <a class="navbar-brand" @click="goFitMain()"><b>FitPlanner</b></a>
        <button class="navbar-toggler" ref="navbarToggleButton" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" ref="navbarSupportedContent" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goFitSchedule()">운동 스케줄</a>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goFitExercise()">운동 종목</a>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goFitFoodDiet()">식단 정보</a>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goFitHealthGym()">추천 헬스장</a>
            </li>
            <li>
              <div class="dropdown-divider"></div>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goFitMain()">메인으로</a>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goMemberEdit()">회원정보수정</a>
            </li>
            <li class="nav-item mb-2">
              <a class="dropdown-item" @click="goLogOut()">로그아웃</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "AppHeader",
  methods: {
    goFitSchedule() {
      this.$router.push({ path: '/app/fitSchedule' });
    },
    goFitExercise() {
      this.$router.push({ path: '/app/fitExercise' });
    },
    goFitHealthGym() {
      this.$router.push({ path: '/app/fitHealthGym' });
    },
    goFitFoodDiet() {
      this.$router.push({ path: '/app/fitFoodDiet' });
    },
    goMemberEdit() {
      this.$router.push({ path: '/app/fitMemberEdit' });
    },
    goFitMain() {
      this.$router.push({ path: '/app/main' });
    },
    goLogOut() {

      this.axios.post("/api/member/logout", JSON.stringify({}), {
        withCredentials: true,
      })
      .then((response) => {

        let respJson = JSON.parse(JSON.stringify(response.data));
        let respCode = respJson.code;

        if(Number(respCode) < 400) {
          this.$cookies.remove("ssoLogin");
          this.$store.dispatch("resetApiToken");
          this.$router.push({ path: '/app/login' });
        }

      }).catch((error) => {
        console.log(error);
      });

    }

  }

}
</script>

<style scoped>

</style>
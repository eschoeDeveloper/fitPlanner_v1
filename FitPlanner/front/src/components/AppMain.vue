<template>

  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#"><b>FitPlanner</b></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
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

  <section>

      <div class="container" ref="fitSchedule">

        <h1>운동 스케줄</h1>

        <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <!-- 가변처리 -->
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
            <!-- 가변처리 -->
            <div class="carousel-item active" data-bs-interval="10000">
              <img src="#" class="d-block w-100" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h5>First slide label</h5>
                <p>Some representative placeholder content for the first slide.</p>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <img src="#" class="d-block w-100" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h5>Second slide label</h5>
                <p>Some representative placeholder content for the second slide.</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="#" class="d-block w-100" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h5>Third slide label</h5>
                <p>Some representative placeholder content for the third slide.</p>
              </div>
            </div>

          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

      </div>

      <div class="container" ref="noFitSchedule" style="display: none;">
        <h3>등록된 운동 스케줄이 없습니다.</h3>
      </div>

    <div class="container" ref="fitFood">

      <h1>식단 정보</h1>

      <div id="carouselExampleDark1" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <!-- 가변처리 -->
          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <!-- 가변처리 -->
          <div class="carousel-item active" data-bs-interval="10000">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>First slide label</h5>
              <p>Some representative placeholder content for the first slide.</p>
            </div>
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Second slide label</h5>
              <p>Some representative placeholder content for the second slide.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Third slide label</h5>
              <p>Some representative placeholder content for the third slide.</p>
            </div>
          </div>

        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

    </div>

    <div class="container" ref="noFitFood" style="display: none;">
      <h3>등록된 운동 스케줄이 없습니다.</h3>
    </div>

    <div class="container" ref="fitHealthGym">

      <h1>추천 헬스장</h1>

      <div id="carouselExampleDark2" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <!-- 가변처리 -->
          <div class="carousel-item active" data-bs-interval="10000">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>First slide label</h5>
              <p>Some representative placeholder content for the first slide.</p>
            </div>
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Second slide label</h5>
              <p>Some representative placeholder content for the second slide.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="#" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Third slide label</h5>
              <p>Some representative placeholder content for the third slide.</p>
            </div>
          </div>

        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

    </div>

    <div class="container" ref="noFitHealthGym" style="display: none;">
      <h3>추천 헬스장 정보가 없습니다.</h3>
    </div>

  </section>

</template>

<script>

export default {
  name: "AppMain.vue",
  methods: {
    goFitSchedule() {
      this.$router.push({ path: '/fitSchedule' });
    },
    goFitExercise() {
      this.$router.push({ path: '/fitExercise' });
    },
    goFitHealthGym() {
      this.$router.push({ path: '/fitHealthGym' });
    },
    goFitFoodDiet() {
      this.$router.push({ path: '/fitFoodDiet' });
    },
    goMemberEdit() {
      this.$router.push({ path: '/fitMemberEdit' });
    },
    goLogOut() {
      this.axios.post("/api/member/logout", JSON.stringify({}), {
        withCredentials: true,
      })
      .then((response) => {

        let respJson = JSON.parse(JSON.stringify(response.data));
        let respCode = respJson.code;

        if(Number(respCode) < 400) {
          this.$router.push({ path: '/' });
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
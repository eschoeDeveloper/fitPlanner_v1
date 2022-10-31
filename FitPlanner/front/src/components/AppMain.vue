<template>

  <div>
    <AppHeader/>
  </div>

  <section>

      <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

        <h4>운동 스케줄</h4>

          <div class="card w-50 d-print-inline-block">
            <div class="card-body">
              <h5 class="card-title">2022.10.31</h5>
              <p class="card-text">달려라 와드! 크로스핏</p>
              <a href="#" class="btn btn-primary">상세보기</a>
            </div>
          </div>

        <div class="card w-50 d-print-inline-block">
          <div class="card-body">
            <h5 class="card-title">2022.11.01</h5>
            <p class="card-text">하체 털기</p>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>

        <div class="card w-50 d-print-inline-block">
          <div class="card-body">
            <h5 class="card-title">2022.11.02</h5>
            <p class="card-text">등 밥주기</p>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>

        <div class="card w-50 d-print-inline-block">
          <div class="card-body">
            <h5 class="card-title">2022.11.03</h5>
            <p class="card-text">이두삼두 키우기</p>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>

        <div class="card w-50 d-print-inline-block">
          <div class="card-body">
            <h5 class="card-title">2022.11.04</h5>
            <p class="card-text">벤치후라쓰</p>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>

        <div class="card w-50 d-print-inline-block">
          <div class="card-body">
            <h5 class="card-title">2022.11.05</h5>
            <p class="card-text">유산소산소</p>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>

      </div>

      <div class="container" ref="noFitSchedule" style="display: none;">
        <h3>등록된 운동 스케줄이 없습니다.</h3>
      </div>

      <hr class="hr hr-blurry">

    <div class="container mt-3" ref="fitFood">

      <h4>식단 정보</h4>

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
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>First slide label</h5>
              <p>Some representative placeholder content for the first slide.</p>
            </div>
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Second slide label</h5>
              <p>Some representative placeholder content for the second slide.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
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

    <div class="container mt-3" ref="noFitFood" style="display: none;">
      <h4>등록된 운동 스케줄이 없습니다.</h4>
    </div>

    <hr class="hr hr-blurry">

    <div class="container mt-3" ref="fitHealthGym">

      <h4>추천 헬스장</h4>

      <div id="carouselExampleDark2" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <!-- 가변처리 -->
          <div class="carousel-item active" data-bs-interval="10000">
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>First slide label</h5>
              <p>Some representative placeholder content for the first slide.</p>
            </div>
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
              <h5>Second slide label</h5>
              <p>Some representative placeholder content for the second slide.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
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

    <div class="container mt-3" ref="noFitHealthGym" style="display: none;">
      <h3>추천 헬스장 정보가 없습니다.</h3>
    </div>

  </section>

</template>

<script>

export default {
  name: "AppMain",
  methods: {}
}
</script>

<style scoped>
  .card{ display: inline-block; }
</style>
<template>

  <section>

      <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

        <h4>운동 스케줄</h4>
        <h6>준비중입니다.</h6>

        <div id="carouselSchedule" class="carousel carousel-dark slide" data-bs-ride="carousel">

          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselSchedule" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          </div>

          <div class="carousel-inner">

            <div class="carousel-item" :class="{'active': i == 0}" :key="i" v-for="(_schedule, i) in scheduleList" data-bs-interval="3000">
              <img src="../assets/image/logo.png" class="d-block w-100" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h5>First slide label</h5>
                <p>Some representative placeholder content for the first slide.</p>
                <button class="btn btn-primary" @click="goScheduleView(_schedule.scheduleNo)">상세보기</button>
              </div>
            </div>

          </div>

          <button class="carousel-control-prev" type="button" data-bs-target="#carouselSchedule" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselSchedule" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>

        </div>

      </div>

      <hr class="hr hr-blurry">

    <div class="container mt-3" ref="fitFood">

      <h4>식단 정보</h4>
      <h6>준비중입니다.</h6>

<!--      <div id="carouselExampleDark1" class="carousel carousel-dark slide" data-bs-ride="carousel">-->
<!--        <div class="carousel-indicators">-->
<!--          &lt;!&ndash; 가변처리 &ndash;&gt;-->
<!--          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>-->
<!--          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="1" aria-label="Slide 2"></button>-->
<!--          <button type="button" data-bs-target="#carouselExampleDark1" data-bs-slide-to="2" aria-label="Slide 3"></button>-->
<!--        </div>-->
<!--        <div class="carousel-inner">-->
<!--          &lt;!&ndash; 가변처리 &ndash;&gt;-->
<!--          <div class="carousel-item active" data-bs-interval="10000">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>First slide label</h5>-->
<!--              <p>Some representative placeholder content for the first slide.</p>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="carousel-item" data-bs-interval="2000">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>Second slide label</h5>-->
<!--              <p>Some representative placeholder content for the second slide.</p>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="carousel-item">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>Third slide label</h5>-->
<!--              <p>Some representative placeholder content for the third slide.</p>-->
<!--            </div>-->
<!--          </div>-->

<!--        </div>-->
<!--        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">-->
<!--          <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<!--          <span class="visually-hidden">Previous</span>-->
<!--        </button>-->
<!--        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">-->
<!--          <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<!--          <span class="visually-hidden">Next</span>-->
<!--        </button>-->
<!--      </div>-->

    </div>

    <hr class="hr hr-blurry">

    <div class="container mt-3" ref="fitHealthGym">

      <h4>추천 헬스장</h4>
      <h6>준비중입니다.</h6>

<!--      <div id="carouselExampleDark2" class="carousel carousel-dark slide" data-bs-ride="carousel">-->
<!--        <div class="carousel-indicators">-->
<!--          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>-->
<!--          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>-->
<!--          <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>-->
<!--        </div>-->
<!--        <div class="carousel-inner">-->
<!--          &lt;!&ndash; 가변처리 &ndash;&gt;-->
<!--          <div class="carousel-item active" data-bs-interval="10000">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>First slide label</h5>-->
<!--              <p>Some representative placeholder content for the first slide.</p>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="carousel-item" data-bs-interval="2000">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>Second slide label</h5>-->
<!--              <p>Some representative placeholder content for the second slide.</p>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="carousel-item">-->
<!--            <img src="../assets/image/logo.png" class="d-block w-100" alt="...">-->
<!--            <div class="carousel-caption d-none d-md-block">-->
<!--              <h5>Third slide label</h5>-->
<!--              <p>Some representative placeholder content for the third slide.</p>-->
<!--            </div>-->
<!--          </div>-->

<!--        </div>-->
<!--        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">-->
<!--          <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<!--          <span class="visually-hidden">Previous</span>-->
<!--        </button>-->
<!--        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">-->
<!--          <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<!--          <span class="visually-hidden">Next</span>-->
<!--        </button>-->
<!--      </div>-->

    </div>

  </section>

</template>

<script>

export default {
  name: "AppMain",
  data: {
    scheduleList: []
  }
  mounted() {

    this.getMainDataList();

    this.$root.$data.isHeader = true;
    this.$toast.show("안녕하세요 회원님!\n오늘도 즐거운 운동되시고 건강 조심하세요!");
    setTimeout(this.$toast.clear, 3000);

  },
  methods: {

    getMainDataList() {

    }

  }

}
</script>

<style scoped>
  .card{ display: inline-block; }
</style>
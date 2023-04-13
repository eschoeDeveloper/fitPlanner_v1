<template>

  <section>

      <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

        <div class="row col-12">
          <div class="col-6">
            <b>운동 스케줄</b>
          </div>
          <div class="col-6" style="text-align:right;">
            <button class="btn btn-outline-primary" @click="goScheduleList();">더보기</button>
          </div>
        </div>

        <hr class="hr hr-blurry">

        <span class="text-bg-dark" v-show="scheduleList.length == 0"><b>등록된 운동 스케줄 정보가 없습니다.</b></span>

        <div id="carouselSchedule" v-show="scheduleList.length > 0" class="carousel carousel-dark slide" data-bs-ride="carousel">


          <div class="carousel-inner">

            <div class="carousel-item" :class="i === 0 ? 'active' : ''" :key="i" v-for="(_schedule, i) in scheduleList" data-bs-interval="3000">
              <img src="../../assets/image/heathGym.jpg" class="d-block w-100" alt="..." style="opacity: 0.2">
              <div class="carousel-caption d-md-block">
                <h5><b>{{_schedule.SCHEDULE_TITLE}}</b></h5>
                <p><b>{{_schedule.SCHEDULE_DESC}}</b></p>
                <p><b>{{_schedule.SCHEDULE_DT}}</b></p>
              </div>
            </div>

          </div>

          <div class="carousel-indicators" >
            <button type="button" :class="i === 0 ? 'active' : ''" data-bs-target="#carouselSchedule" :key="i" v-for="(_schedule, i) in scheduleList" :data-bs-slide-to="i"  aria-current="true" :aria-label="'Slide ' + i"></button>
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

        <hr class="hr hr-blurry">

      </div>

    <div class="container mt-3" ref="fitFood">

      <div class="row col-12">
        <div class="col-6">
          <b>식단 정보</b>
        </div>
        <div class="col-6" style="text-align:right;">
          <button class="btn btn-outline-primary" @click="goFoodDietList();">더보기</button>
        </div>
      </div>

      <hr class="hr hr-blurry">

      <span class="text-bg-dark" v-show="foodDietList.length == 0"><b>등록된 식단 정보가 없습니다.</b></span>

      <div id="carouselFoodDiet" v-show="foodDietList.length > 0" class="carousel carousel-dark slide" data-bs-ride="carousel">


        <div class="carousel-inner">

          <div class="carousel-item" :class="i === 0 ? 'active' : ''" :key="i" v-for="(_foodDiet, i) in foodDietList" data-bs-interval="3000">
            <img src="../../assets/image/heathGym.jpg" class="d-block w-100" alt="..." style="opacity: 0.2">
            <div class="carousel-caption d-md-block">
              <h5><b>{{_foodDiet.FOOD}}</b></h5>
              <p><b>{{_foodDiet.INTAKE}}g</b></p>
              <p><b>{{_foodDiet.REGIST_DT}}</b></p>
            </div>
          </div>

        </div>

        <div class="carousel-indicators" >
          <button type="button" :class="i === 0 ? 'active' : ''" data-bs-target="#carouselFoodDiet" :key="i" v-for="(_foodDiet, i) in foodDietList" :data-bs-slide-to="i"  aria-current="true" :aria-label="'Slide ' + i"></button>
        </div>

        <button class="carousel-control-prev" type="button" data-bs-target="#carouselFoodDiet" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselFoodDiet" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>

      </div>


      <hr class="hr hr-blurry">

    </div>



    <div class="container mt-3" ref="fitHealthGym">

      <div class="row col-12">
        <div class="col-6">
          <b>추천 헬스장</b>
        </div>
        <div class="col-6" style="text-align:right;">
          <button class="btn btn-outline-primary" @click="goHealthGymList();">더보기</button>
        </div>
      </div>

      <hr class="hr hr-blurry">

      <span class="text-bg-dark" v-show="healthGymList.length == 0"><b>검색된 헬스장 정보가 없습니다.</b></span>

      <div id="carouselHealthGym" v-show="healthGymList.length > 0" class="carousel carousel-dark slide" data-bs-ride="carousel">

        <div class="carousel-inner">

          <div class="carousel-item" :class="i === 0 ? 'active' : ''" :key="i" v-for="(_healthGym, i) in healthGymList" data-bs-interval="3000">
            <img src="../../assets/image/heathGym.jpg" class="d-block w-100" alt="..." style="opacity: 0.2">
            <div class="carousel-caption d-md-block">
              <h5><b>{{_healthGym.title}}</b></h5>
              <p><b>{{_healthGym.roadAddress}}</b></p>
              <p><button v-on:click="openHealthGymDetail(_healthGym.link);">상세보기</button></p>
            </div>
          </div>

        </div>

        <div class="carousel-indicators" >
          <button type="button" :class="i === 0 ? 'active' : ''" data-bs-target="#carouselHealthGym" :key="i" v-for="(_healthGym, i) in healthGymList" :data-bs-slide-to="i"  aria-current="true" :aria-label="'Slide ' + i"></button>
        </div>

        <button class="carousel-control-prev" type="button" data-bs-target="#carouselHealthGym" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselHealthGym" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>

      </div>

      <hr class="hr hr-blurry">

    </div>

  </section>

</template>

<script>

export default {
  name: "AppMain",
  data() {
    return {
      scheduleList: [],
      foodDietList: [],
      healthGymList: []
    }
  },
  created() {

    this.$root.$data.isHeader = true;
    this.$toast.show("안녕하세요 회원님!\n오늘도 즐거운 운동되시고 건강 조심하세요!");
    setTimeout(this.$toast.clear, 3000);

    this.getMainDataList();

  },
  methods: {

    getMainDataList() {

      this.axios.post("/api/member/main/data", {}, {})
      .then((response) => {

        let respJson = JSON.parse(JSON.stringify(response.data));
        let respData = JSON.parse(respJson.data);

        this.scheduleList = JSON.parse(JSON.stringify(respData.fitScheduleList));
        this.foodDietList = JSON.parse(JSON.stringify(respData.fitFoodDietList));
        this.healthGymList = JSON.parse(respData.fitHealthGymList);

        console.log(this.healthGymList);

      }).catch((error) => {
        console.log(error);
        alert(error);
      });

    },
    goScheduleList() {
      this.$router.push({name: "AppFitSchedule"});
    },
    goFoodDietList() {
      this.$router.push({name: "AppFitFoodDiet"});
    },
    goHealthGymList() {
      this.$router.push({name: "AppFitHealthGym"});
    },
    openHealthGymDetail (url) {
      const requestUrl = url.trim();
      window.open(requestUrl);
    }

  }

}
</script>

<style scoped>
  .card{ display: inline-block; }
</style>
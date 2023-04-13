
<template>

  <section style="border:1px;">

    <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

      <h4>운동 스케줄</h4>

      <div class="form-row">
        <div class="col-md-8">
          <Datepicker ref="toDt" id="toDt" v-model="toDt"/>
          <input type="hidden" ref="hToDt" id="hToDt" value="" />
        </div>
        <div class="col-md-4">
          <button type="button" v-on:click="getScheduleList();">검색</button>
        </div>
      </div>

      <div class="form-row mt-2 mb-2">
        <button class="btn btn-sm btn-outline-info" @click="createSchedule()">스케줄 등록</button>
      </div>

      <div class="container" style="border : 1px solid sandybrown; min-height: 65vh;">

        <span v-show="scheduleList.length < 1" style="vertical-align: middle; text-align: center; height:100%; width:100%; padding-top: 25vh; display: inline-block;"><b>조회된 스케줄이 없습니다.</b></span>

        <div class="card w-50 d-print-inline-block" :key="i" v-for="(_schedule, i) in scheduleList">
          <div class="card-body">
            <h5 class="card-title">{{_schedule.scheduleDt}}</h5>
            <p class="card-text">{{_schedule.scheduleTitle}}</p>
            <button class="btn btn-primary" @click="goScheduleView(_schedule.scheduleNo)">상세보기</button>
          </div>
        </div>

      </div>

    </div>

  </section>

</template>

<script>

  import Datepicker from 'vue3-datepicker'

  export default {
    name: "AppFitSchedule",
    components: {
      Datepicker
    },
    data() {
      return {
        value: '',
        toDt: new Date(),
        scheduleList : []
      }
    },
    methods: {
      getDateStr(myDate) {

        let year = myDate.getFullYear();
        let month = (myDate.getMonth() + 1);
        let day = myDate.getDate();

        month = (month < 10) ? "0" + String(month) : month;
        day = (day < 10) ? "0" + String(day) : day;

        return year + '-' + month + '-' + day;

      },
      getScheduleList() {

        const dayOfDate = new Date(this.toDt.toLocaleDateString("ko-KR", {timeZone:"GMT"}));

        const dayOfMonth = dayOfDate.getDate();
        dayOfDate.setDate(dayOfMonth - 7);

        const fromDt = this.getDateStr(dayOfDate);
        const toDt = this.toDt.toISOString().slice(0,10);

        this.axios.post("/api/fitSchedule/list", {fromDt: fromDt, toDt: toDt}, {})
        .then((response) => {
          console.log(response);
          let respJson = JSON.parse(JSON.stringify(response.data));
          this.scheduleList = JSON.parse(JSON.stringify(respJson.data));
        }).catch((error) => {
          console.log(error);
        });

      },
      createSchedule() {
        this.$router.push({name: "AppFitScheduleForm"});
      },
      goScheduleView(scheduleNo) {
        this.$router.push({name: "AppFitScheduleView", params: {scheduleNo: scheduleNo}});
      }

    },
    compatConfig: { MODE: 3 },
    created() {
      this.getScheduleList();
    }
  }

</script>

<style scoped>
  .card{ display: inline-block; }
</style>
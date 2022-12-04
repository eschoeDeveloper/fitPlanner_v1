
<template>

  <section>

    <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

      <h4>운동 스케줄</h4>

      <div class="form-row">
        <div class="col-md-5">
          <Datepicker ref="toDt" id="toDt" v-model="toDt" @change="getScheduleList"/>
        </div>
      </div>

      <div class="form-row mt-2 mb-2">
        <button class="btn btn-sm btn-outline-info" @click="createSchedule()">스케줄 등록</button>
      </div>

      <div class="card w-50 d-print-inline-block" :key="i" v-for="(_schedule, i) in scheduleList">
        <div class="card-body">
          <h5 class="card-title">{{_schedule.scheduleDt}}</h5>
          <p class="card-text">{{_schedule.scheduleTitle}}</p>
          <button class="btn btn-primary" @click="goScheduleView(_schedule.scheduleNo)">상세보기</button>
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

      getScheduleList() {

        console.log(this.toDt);

        const isToDt = (this.$refs.toDt) ? this.$refs.toDt : this.toDt;

        const fromDt = this.toDt.getFullYear() + "-" + (this.toDt.getMonth()+1) + "-" + (this.toDt.getDate()-7);
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
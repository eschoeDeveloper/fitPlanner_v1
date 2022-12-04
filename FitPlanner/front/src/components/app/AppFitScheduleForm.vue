<template>

  <section>

    <div class="container mt-4">

      <h4>운동 스케쥴 등록</h4>

      <form ref="form" name="form">

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="scheduleDt">운동 스케쥴일자</label>
            <Datepicker ref="scheduleDt" name="scheduleDt" id="scheduleDt" v-model="toDt"/>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="scheduleTitle">운동 스케쥴명</label>
            <input type="text" class="form-control" ref="scheduleTitle" name="scheduleTitle" id="scheduleTitle" placeholder="스케쥴명" :value="scheduleInfo.scheduleTitle" >
          </div>
        </div>

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="scheduleDesc">운동 스케쥴상세</label>
            <textarea type="text" class="form-control" ref="scheduleDesc" name="scheduleDesc" id="scheduleDesc" placeholder="스케쥴명" :value="scheduleInfo.scheduleDesc" rows="15" style="height: 150px;"></textarea>
          </div>
        </div>

        <hr class="hr hr-blurry">

        <button type="button" class="btn btn-primary mt-4" @click="saveSchedule()">저장</button>

      </form>

    </div>

  </section>

</template>

<script>
  import Datepicker from 'vue3-datepicker'

  const $toDt = new Date();

  export default {
    name: "AppFitScheduleForm",
    data() {
      return {
        scheduleInfo : {},
        toDt: $toDt
      }
    },
    components: {
      Datepicker
    },
    methods: {

      saveSchedule() {

        const formData = new FormData(this.$refs['form']);

        const reqData = {};
        for (let [k, v] of formData.entries()) {
          Object.assign(reqData, { [k]: v })
        }

        this.axios.post("/api/fitSchedule/create", reqData, {})
        .then((response) => {

           let respJson = JSON.parse(JSON.stringify(response.data));

           const scheduleNo = respJson.data;
           const respCode = respJson.code;

           alert(scheduleNo + "::" + respCode);

           if(Number(respCode) >= 200 && Number(respCode) < 400) {
             this.$router.push({name: "AppFitScheduleView", params: {scheduleNo: scheduleNo}});
           }

        })
        .catch((error) => {
          console.log(error);
        });

      },
      getSchedule(scheduleNo) {

        let QueryString = "?scheduleNo=" + scheduleNo;

        this.axios.get("/api/fitSchedule/view" + QueryString, {})
        .then((response) => {
           let respJson = JSON.parse(JSON.stringify(response.data));
           this.scheduleInfo = JSON.parse(JSON.stringify(respJson.data));
           this.toDt = this.scheduleInfo.scheduleDt;
        })
        .catch((error) => {
           console.log(error);
        });

      },
      goList() {
        this.$router.push({name: "AppFitSchedule"});
      }

    },
    created() {
      const reqScheduleNo = this.$route.params.scheduleNo;
      if(reqScheduleNo > 0) this.getSchedule(reqScheduleNo);
    }

  }
</script>

<style scoped>

</style>
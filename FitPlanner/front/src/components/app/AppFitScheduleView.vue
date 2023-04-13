<template>

  <section>

    <div class="card w-100">

      <img class="card-img-top" src="../../assets/image/logo.png" alt="Card image cap">
      <div class="card-body">
        <h5 class="card-title">{{scheduleInfo.scheduleTitle}}</h5>
        <p class="card-text">{{scheduleInfo.scheduleDt}}</p>
        <p class="card-text">{{scheduleInfo.scheduleDesc}}</p>
      </div>
      <div class="card-footer">
        <button type="button" class="btn btn-sm btn-secondary" v-show="Number(schedulePages.prevNo) > 0" @click="goPrevOrNext(schedulePages.prevNo, 'prev')">이전으로</button>
        <button type="button" class="btn btn-sm btn-success" @click="goList()">목록으로</button>
        <button type="button" class="btn btn-sm btn-secondary" v-show="Number(schedulePages.nextNo) > 0" @click="goPrevOrNext(schedulePages.nextNo, 'next')">다음으로</button>
      </div>

    </div>

  </section>

</template>

<script>

  export default {

    name: "AppFitScheduleView",
    data() {
      return {
        scheduleInfo : {},
        schedulePages : {}
      }
    },
    methods: {
      viewSchedule(scheduleNo) {

        const reqScheduleNo = (scheduleNo) ? scheduleNo : this.$route.params.scheduleNo;

        let QueryString = "?scheduleNo=" + reqScheduleNo;

        this.axios.get("/api/fitSchedule/view" + QueryString, {})
        .then((response) => {
           let respJson = JSON.parse(JSON.stringify(response.data));
           let respData = JSON.parse(respJson.data);

           this.scheduleInfo = JSON.parse(JSON.stringify(respData.fitScheduleInfo));
           this.schedulePages = JSON.parse(JSON.stringify(respData.fitSchedulePages));

           alert(JSON.stringify(this.schedulePages));

        })
        .catch((error) => {
          console.log(error);
        });

      },
      goList() {
        this.$router.push({name: "AppFitSchedule"});
      },
      goPrevOrNext(scheduleNo, prevOrNext) {

        let refScheduleNo = Number(scheduleNo);

        if(prevOrNext === "prev") {
          this.viewSchedule(refScheduleNo-1);
        } else if(prevOrNext === "next") {
          this.viewSchedule(refScheduleNo+1);
        }

      }

    },
    created() {
      this.viewSchedule();
    }

  }

</script>

<style scoped>

</style>
<template>

  <section>

    <div class="card w-100">

      <img class="card-img-top" src="../../assets/image/logo.png" alt="Card image cap">
      <div class="card-body">
        <h5 class="card-title">{{exerciseInfo.exerciseNm}}</h5>
        <p class="card-text">{{exerciseInfo.exerciseDesc}}</p>
      </div>
      <div class="card-footer">
        <button type="button" class="btn btn-sm btn-secondary" v-show="exerciseInfo.exerciseNo > 1" @click="goPrevOrNext('prev')">이전으로</button>
        <button type="button" class="btn btn-sm btn-success" @click="goList()">목록으로</button>
        <button type="button" class="btn btn-sm btn-secondary" @click="goPrevOrNext('next')">다음으로</button>
      </div>

    </div>

  </section>

</template>

<script>
export default {
  name: "AppFitExerciseView",
  data() {
    return {
      exerciseInfo : {}
    }
  },
  methods: {
    viewExercise(exerciseNo) {

      const reqExerciseNo = (exerciseNo) ? exerciseNo : this.$route.params.exerciseNo;

      let QueryString = "?exerciseNo=" + reqExerciseNo;

      this.axios.get("/api/fitExercise/view" + QueryString, {}, {
        withCredentials: true
      })
      .then((response) => {
         let respJson = JSON.parse(JSON.stringify(response.data));
         this.exerciseInfo = JSON.parse(JSON.stringify(respJson.data));
      })
      .catch((error) => {
        console.log(error);
      });

    },
    goList() {
      this.$router.push({name: "AppFitExercise"});
    },
    goPrevOrNext(prevOrNext) {

      let exerciseNo = Number(this.$route.params.exerciseNo);

      if(exerciseNo < 1) return false;

      if(prevOrNext === "prev") {
        this.viewExercise(exerciseNo--);
      } else if(prevOrNext === "next") {
        this.viewExercise(exerciseNo++);
      }

    }

  },
  created() {
    this.viewExercise();
  }
}
</script>

<style scoped>

</style>
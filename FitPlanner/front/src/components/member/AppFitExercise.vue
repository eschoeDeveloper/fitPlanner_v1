<template>

  <section>

    <div class="row m-1">
      <div class="col-sm-4">
        <span>카테고리</span>
      </div>
      <div class="col-sm-7">
        <select class="form-select" v-model="exerciseCategory" id="exerciseCategory" name="exerciseCategory" aria-label="운동종목 카테고리" v-on:change="getExerciseList()">
          <option value="" selected>전체</option>
          <option :key="i" :value="category.categoryNo" v-for="(category, i) in categoryList">{{category.categoryNm}}</option>
        </select>
      </div>
    </div>

    <hr class="hr hr-blurry">

    <div class="album py-5">

      <div class="container">

        <div class="card w-50 box-shadow" :key="i" v-for="(exercise, i) in exerciseList">

          <img class="card-img-top" src="../../assets/image/logo.png">

          <div class="card-body">
            <p class="card-text">{{exercise.exerciseNm}}</p>
            <div class="d-flex justify-content-between align-items-center">
              <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-secondary" @click="goExerciseView(exercise.exerciseNo)">상세보기</button>
              </div>
            </div>
          </div>

        </div>

      </div>

    </div>

  </section>

</template>

<script>
export default {
  name: "AppFitExercise",
  data() {
    return {
      categoryList: [],
      exerciseList: [],
      exerciseCategory: ""
    }
  },
  methods: {

    getCategoryList() {

      this.axios.get("/api/fitExercise/category/list", {useYn: "Y"}, {
        withCredentials: true
      })
      .then((response) => {
        let respJson = JSON.parse(JSON.stringify(response.data));
        this.categoryList = JSON.parse(JSON.stringify(respJson.data));
      }).catch((error) => {
        console.log(error);
      });

    },
    getExerciseList() {

      let categoryIdx = this.exerciseCategory;
      console.log(categoryIdx);

      this.axios.post("/api/fitExercise/list", {categoryIdx: categoryIdx, useYn: "Y"}, {
        withCredentials: true
      })
      .then((response) => {
        let respJson = JSON.parse(JSON.stringify(response.data));
        this.exerciseList = JSON.parse(JSON.stringify(respJson.data));
      }).catch((error) => {
        console.log(error);
      });

    },
    goExerciseView(exerciseNo){
      this.$router.push({name: 'AppFitExerciseView', params:{exerciseNo: exerciseNo}});
    },

  },
  created() {
    this.getCategoryList();
    this.getExerciseList();
  }

}
</script>

<style scoped>
  .card{ display: inline-block; }
</style>
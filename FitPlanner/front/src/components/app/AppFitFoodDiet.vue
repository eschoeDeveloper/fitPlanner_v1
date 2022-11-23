<template>

  <section>

    <div class="container mt-3 d-print-inline-block">

      <div class="card w-100 d-print-inline-block" :key="i" v-for="(foodDiet, i) in foodDietFoodList">
        <div class="card-body">
          <h5 class="card-title">{{foodDiet.registDt}}</h5>
          <ul class="list-group">
            <li class="list-group-item">{{foodDiet.food}} {{foodDiet.intake}}g</li>
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
          </ul>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

<!--      <div class="card w-100 d-print-inline-block">-->
<!--        <div class="card-body">-->
<!--          <h5 class="card-title">2022.11.01</h5>-->
<!--          <ul class="list-group">-->
<!--            <li class="list-group-item">닭가슴살 100g</li>-->
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
<!--          </ul>-->
<!--          <a href="#" class="btn btn-primary">상세보기</a>-->
<!--        </div>-->
<!--      </div>-->

<!--      <div class="card w-100 d-print-inline-block">-->
<!--        <div class="card-body">-->
<!--          <h5 class="card-title">2022.11.02</h5>-->
<!--          <ul class="list-group">-->
<!--            <li class="list-group-item">닭가슴살 100g</li>-->
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
<!--          </ul>-->
<!--          <a href="#" class="btn btn-primary">상세보기</a>-->
<!--        </div>-->
<!--      </div>-->

<!--      <div class="card w-100 d-print-inline-block">-->
<!--        <div class="card-body">-->
<!--          <h5 class="card-title">2022.11.03</h5>-->
<!--          <ul class="list-group">-->
<!--            <li class="list-group-item">닭가슴살 100g</li>-->
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
<!--          </ul>-->
<!--          <a href="#" class="btn btn-primary">상세보기</a>-->
<!--        </div>-->
<!--      </div>-->

<!--      <div class="card w-100 d-print-inline-block">-->
<!--        <div class="card-body">-->
<!--          <h5 class="card-title">2022.11.04</h5>-->
<!--          <ul class="list-group">-->
<!--            <li class="list-group-item">닭가슴살 100g</li>-->
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
<!--          </ul>-->
<!--          <a href="#" class="btn btn-primary">상세보기</a>-->
<!--        </div>-->
<!--      </div>-->

<!--      <div class="card w-100 d-print-inline-block">-->
<!--        <div class="card-body">-->
<!--          <h5 class="card-title">2022.11.05</h5>-->
<!--          <ul class="list-group">-->
<!--            <li class="list-group-item">닭가슴살 100g</li>-->
<!--            <li class="list-group-item">고구마 1개</li>-->
<!--            <li class="list-group-item">귀리 50g</li>-->
<!--          </ul>-->
<!--          <a href="#" class="btn btn-primary">상세보기</a>-->
<!--        </div>-->
<!--      </div>-->

    </div>

  </section>

</template>

<script>
export default {
  name: "AppFitFoodDiet",
  data() {
    return {
        foodDietCategoryList: [],
        foodDietFoodList: [],
        foodDietCategory: ""
    }
  },
  methods: {

    getCategoryList() {

      this.axios.get("/api/fitFoodDiet/category/list", {useYn: "Y"}, {
        withCredentials: true
      })
      .then((response) => {
            let respJson = JSON.parse(JSON.stringify(response.data));
            this.foodDietCategoryList = JSON.parse(JSON.stringify(respJson.data));
      }).catch((error) => {
        console.log(error);
      });

    },
    getFoodDietList() {

      let categoryNo = this.foodDietCategory;

      this.axios.post("/api/fitFoodDiet/list", {categoryNo: categoryNo, useYn: "Y"}, {
        withCredentials: true
      })
      .then((response) => {
        let respJson = JSON.parse(JSON.stringify(response.data));
        this.foodDietFoodList = JSON.parse(JSON.stringify(respJson.data));
      }).catch((error) => {
        console.log(error);
      });

    },
    // goExerciseView(exerciseNo){
    //   this.$router.push({name: 'AppFitExerciseView', params:{exerciseNo: exerciseNo}});
    // },

  },
  created() {
    this.getCategoryList();
    this.getFoodDietList();
  }

}
</script>

<style scoped>

</style>
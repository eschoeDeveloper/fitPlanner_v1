<template>

  <section>

    <div class="container mt-3 d-print-inline-block">

      <span class="text-bg-dark" v-show="foodDietFoodList.length == 0"><b>등록된 식단 정보가 없습니다.</b></span>

      <div class="card w-100 d-print-inline-block" :key="i" v-for="(foodDiet, i) in foodDietFoodList">
        <div class="card-body">
          <h5 class="card-title">{{foodDiet.registDt}}</h5>
          <ul class="list-group">
            <li class="list-group-item">{{foodDiet.food}} {{foodDiet.intake}}g</li>
          </ul>
        </div>
      </div>

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

  },
  created() {
    this.getCategoryList();
    this.getFoodDietList();
  }

}
</script>

<style scoped>

</style>
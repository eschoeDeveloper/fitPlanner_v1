<template>

  <section>
    <div class="album py-5">

      <div class="container">

        <div class="card w-100 box-shadow" :class="{'mt-5': i > 0}" :key="i" v-for="(healthGym, i) in healthGymList">

          <img class="card-img-top" src="../../assets/image/heathGym.jpg">

          <div class="card-body">
            <p class="card-title h5"><b>{{healthGym.title}}</b></p>
            <p class="card-text">{{healthGym.roadAddress}}</p>
            <div class="d-flex justify-content-between align-items-center">
              <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-secondary" @click="goHealthGym(healthGym.link)">상세보기</button>
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
    name: "AppFitHealthGym",
    data() {
      return {
        healthGymList: []
      }
    },
    methods: {
      getHealthGymList() {
        this.axios.post("/api/fitHealthGym/list", {}, {})
        .then((response) => {
              let respJson = JSON.parse(JSON.stringify(response.data));
              this.healthGymList = JSON.parse(respJson.data);
        }).catch((error) => {
          console.log(error);
        });
      },
      goHealthGym(url){
        window.open(url);
      }
    },
    created() {
      this.getHealthGymList();
    }
  }
</script>

<style scoped>

</style>

<template>

  <section style="border:1px;">

    <div class="container mt-3 d-print-inline-block">

      <h4>회원 탈퇴</h4>

      <div class="container mt-3" style="border : 1px solid sandybrown; min-height: 65vh; padding:15vh 5vh;">

        <p>지금까지 FitPlanner를 사용해주셔서 감사합니다.</p>
        <p>회원탈퇴를 하시면 다음과 같은 정보가 삭제되오니, 유의하시기 바랍니다.</p>
        <p><b style="color:red;"> -> 식단정보, 운동스케줄, 회원정보 </b></p>
        <p>그래도 진행하시려면, 아래 회원탈퇴 버튼을 클릭하여 주세요.</p>
      </div>

      <button class="btn btn-primary mt-4" @click="goSignOutExecute();">회원탈퇴</button>

    </div>

  </section>

</template>

<script>

  export default {
    name: "AppSignOut",
    methods: {
      goSignOutExecute() {

        this.axios.post("/api/member/signOut", {}, {})
        .then((response) => {
          console.log(response);
          let respJson = JSON.parse(JSON.stringify(response.data));
          let respCode = Number(respJson.code);

          if(respCode < 100 || respCode > 400) {
            this.$toast.show("시스템 오류입니다. 관리자에게 문의하여 주세요.");
            setTimeout(() => {
              this.$toast.clear;
            }, 3000);
          } else if(respCode > 199 && respCode < 400) {
            this.$router.push({name: "AppLogin"});
          }

        }).catch((error) => {
          console.log(error);
        });

      }

    },
    compatConfig: { MODE: 3 },

  }

</script>

<style scoped>
  .card{ display: inline-block; }
</style>
<template>

  <section class="h-100 h-custom" style="background-color: #8fc4b7;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-8">
          <div class="card rounded-3">
            <img src="https://health.chosun.com/site/data/img_dir/2018/03/07/2018030700812_2.jpg"
                 class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                 alt="Sample photo">
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">FitPlanner</h3>

              <form class="px-md-2">
                <div class="form-outline mb-4">
                  <p ref="signUpResult" :class="[msgClass, fontClass]" v-html="respMsg"></p>
                </div>
                <div class="form-outline mb-4">
                  <button type="button" @click="goNext()" v-html="buttonText" class="btn btn-primary btn-block mb-4"></button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

</template>

<script>
export default {
  name: "AppSignUpResult",
  data() {

    const respCode = this.$route.params.respCode;
    const respMsg = this.$route.params.respMsg;
    let msgClass = "";
    const fontClass = "h6";
    let buttonText;

    console.log(respCode, respMsg);

    if(respCode <= 300 ){
      msgClass = "text-success";
      buttonText = "가입완료";
    } else if(respCode > 301){
      msgClass = "text-primary";
      buttonText = "다시하기";
    }

    return {
      respMsg: respMsg,
      msgClass: msgClass,
      fontClass: fontClass,
      buttonText: buttonText
    }

  },
  methods: {

    goNext() {

      const respCode = this.$route.params.respCode;

      if(respCode <= 300 ){
        this.$router.push({path:"/member/app/main"});
      } else if(respCode > 301){
        this.$toast.show("서버에 오류가 발생하여 회원가입을 실패하였습니다.\n오류가 지속되면 고객센터에 문의하여 주세요.");
        setTimeout(() => {
          this.$toast.clear;
          this.$router.push({path: "member/app/signUp"});
        }, 3000);

      }

    }

  }

}
</script>

<style scoped>

</style>
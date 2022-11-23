
<template>

  <div class="h-100 w-100 d-inline-block">

    <AppHeader ref="appHeader" v-show="isHeader === true"/>

    <router-view/>

  </div>

</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      isHeader : false
    }
  },
  compatConfig: { MODE: 3 },
  watch: {
    $route(to, from) {

      if(to.path != from.path) {

        this.$refs.appHeader.$refs.navbarToggleButton.classList.add("collapsed");
        this.$refs.appHeader.$refs.navbarSupportedContent.classList.remove("show");

        if(
            to.path.indexOf("index") < 0 &&
            to.path.indexOf("login") < 0 &&
            to.path.indexOf("signUp") < 0 &&
            to.path.indexOf("pwdReset") < 0 &&
            to.path.indexOf("loginFail") < 0 &&
            to.path.indexOf("signUpResult") < 0
        ) {

          if (this.$store.state.apiToken == null || this.$store.state.apiToken == "") {
            this.$toast.show("본 서비스는 로그인 후 이용 가능합니다.");
            this.$router.push({name: "AppLogin"});
            setTimeout(() => {
              this.$toast.clear;
            }, 3000);
          }

        }

      }

    }
  },
  mounted() {
    // this.$refs.appHeader.$refs.navbarToggleButton.click();
    // this.$refs.appHeader.$refs.navbarToggleButton.classList.add("collapsed");
    // this.$refs.appHeader.$refs.navbarSupportedContent.classList.remove("show");
  }
}
</script>

<style>
html,body,#app{
  height: 100%;
}
</style>

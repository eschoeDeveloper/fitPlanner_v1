
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
            to.path.indexOf("pwdResetAuth") < 0 &&
            to.path.indexOf("pwdResetExecute") < 0 &&
            to.path.indexOf("loginFail") < 0 &&
            to.path.indexOf("signUpResult") < 0
        ) {

          if (this.$store.state.apiToken == null || this.$store.state.apiToken == "") {
            this.$router.push({name: "AppLogin"});
          }

        }

      }

    }
  }
}
</script>

<style>
html,body,#app{
  height: 100%;
}
</style>


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
  watch: {
    $route(to, from) {
      if(to.path != from.path) {
        this.$refs.appHeader.$refs.navbarToggleButton.classList.add("collapsed");
        this.$refs.appHeader.$refs.navbarSupportedContent.classList.remove("show");
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

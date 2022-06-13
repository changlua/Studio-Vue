<template>
  <div id="app">
    <!-- <commit/> -->
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
export default {
  provide () {
    return {
      reload: this.reload
    }
  },
  name: 'App',
  data () {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  }
}
</script>

<style>
</style>

<script>
export default {
  created() {
    const { params, query } = this.$route
    const { path } = params
    console.log("redirect.vue（path）", this.$route)
    this.$router.replace({ path: '/' + path, query })
  },
  render: function(h) {
    return h() // avoid warning message
  }
}
</script>

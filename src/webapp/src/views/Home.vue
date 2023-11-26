<template>
  <div>
    <h1>{{ message }}</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: ''
    };
  },
  mounted() {
    this.fetchMessage();
  },
  methods: {
    fetchMessage() {
      this.$axios.get('/api/message')
        .then(response => {
          this.message = response.data.content;
        })
        .catch(error => {
          console.error('Error fetching message:', error);
        });
    }
  }
};
</script>

<style scoped>
/* Stylizacja komponentu */
</style>

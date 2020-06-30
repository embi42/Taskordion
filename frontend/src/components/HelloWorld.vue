<template>
  <div>
    <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>

    <h3>{{ response }}</h3>
  </div>
</template>

<script>
import {AXIOS} from './http-commons'

export default {
  name: "HelloWorld",

  data: () => {
    return {
      response: [],
      errors: []
    };
  },

  methods: {
    callRestService() {
      AXIOS.get('/api/hello')
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  },

  props: {
    msg: String
  }
};
</script>

<style scoped>
</style>

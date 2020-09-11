<template>
  <div>
    <input
      class="input-text"
      type="text"
      v-model="text"
      @keydown.enter.ctrl.prevent="createNewWorkLogEntry"
      autocomplete="off"
    />
    <button class="b-btn" @click="createNewWorkLogEntry()">create</button>
    <div v-for="entry in workLogEntries"
      :key="entry.id">
      {{entry.text}}
    </div>
  </div>
</template>

<script>
import {AXIOS} from './http-commons'

export default {
  name: "HelloWorld",
  data: () => {
    return {
      text: "",
      workLogEntries: []
    };
  },
  methods: {
    // callRestService() {
    //   AXIOS.get('/api/hello')
    //     .then(response => {
    //       // JSON responses are automatically parsed.
    //       this.response = response.data;
    //     })
    //     .catch(e => {
    //       this.errors.push(e);
    //     });
    // },

    createNewWorkLogEntry() {
      AXIOS.post('/api/worklog', {text: this.text, dummy: "dummy"}, {headers: {userId: "userID"}})
        .then(() => {
          this.text = "";
          this.getWorkLogEntries();
        })
    },
    getWorkLogEntries() {
      AXIOS.get('/api/worklog', {headers: {userId: "userID"}})
        .then(response => {
          this.workLogEntries = response.data;
        })
    },
  },
  mounted() {
    console.log("created");
    this.getWorkLogEntries();
  },
  props: {
    msg: String
  }
};
</script>

<style scoped>
</style>

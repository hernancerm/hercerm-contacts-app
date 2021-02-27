<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to your Vue.js and Spring Boot app" />
    <h3>The server says: {{ greeting }}</h3>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import axios from "axios";

export default {
  name: "Home",
  components: {
    HelloWorld
  },

  data() {
    return {
      greeting: ""
    };
  },

  beforeMount() {
    // Consume service from backend API
    axios.get("/api/greeting").then(result => {
      this.greeting = result.data;
    });
  }
};
</script>

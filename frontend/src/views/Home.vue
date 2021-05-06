<template>
  <div class="home">
    <h1>Contacts App</h1>
    <h3>The server says: {{ greeting }}</h3>
    <ContactsTablePaginated></ContactsTablePaginated>
  </div>
</template>

<script>
import axios from "axios";
import ContactsTablePaginated from "@/components/domain/contact/ContactsTablePaginated.vue";

export default {
  name: "Home",

  components: {
    ContactsTablePaginated
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

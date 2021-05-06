<template>
  <div class="home">
    <h1>Contacts</h1>
    <ContactsTablePaginated />
  </div>
</template>

<script>
import ContactsTablePaginated from "@/components/domain/contact/ContactsTablePaginated.vue";

export default {
  name: "Home",

  components: {
    ContactsTablePaginated
  }
};
</script>

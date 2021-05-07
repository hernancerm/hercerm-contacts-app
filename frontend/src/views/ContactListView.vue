<template>
  <div class="home">
    <router-link :to="{ name: 'ContactCreateOrUpdateView' }"
      >Add new contact</router-link
    >
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

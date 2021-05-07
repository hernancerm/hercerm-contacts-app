<template>
  <div class="home">
    <router-link :to="{ name: 'ContactCreateOrUpdateView' }"
      >Add new contact</router-link
    >
    <ContactListPaginated />
  </div>
</template>

<script>
import ContactListPaginated from "@/components/domain/contact/ContactListPaginated.vue";

export default {
  name: "Home",

  components: {
    ContactListPaginated
  }
};
</script>

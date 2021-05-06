<template>
  <div class="home">
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

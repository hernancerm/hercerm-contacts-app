<template>
  <div>
    <p>Hello Baby</p>
    <PageSelector></PageSelector>
  </div>
</template>

<script>
import PageSelector from "@/components/ui/PageSelector.vue";

export default {
  name: "ContactsTablePaginated",

  components: {
    PageSelector
  }
};
</script>

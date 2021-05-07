<template>
  <div>
    <h1>Your contacts 👩</h1>
    <div class="pb-4">
      <router-link
        class="flex space-x-1 px-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-sm"
        :to="{ name: 'ContactCreateOrUpdateView' }"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M12 6v6m0 0v6m0-6h6m-6 0H6"
          />
        </svg>
        <span>Add new contact</span></router-link
      >
    </div>
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

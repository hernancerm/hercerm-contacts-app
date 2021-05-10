<template>
  <div>
    <h1 class="relative w-max">
      Your contacts 👩
      <router-link :to="{ name: 'AboutView' }">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="text-gray-300 hover:text-gray-400 h-7 w-7 absolute -right-9 top-2"
          viewBox="0 0 20 20"
          fill="currentColor"
        >
          <path
            fill-rule="evenodd"
            d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z"
            clip-rule="evenodd"
          />
        </svg>
      </router-link>
    </h1>
    <div class="pb-5">
      <router-link
        class="flex space-x-1 px-3 py-2 bg-green-500 hover:bg-green-600 text-white rounded-sm"
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

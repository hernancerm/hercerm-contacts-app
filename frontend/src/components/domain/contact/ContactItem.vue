<template>
  <div
    class="h-36 sm:h-20 flex justify-between p-4 border border-gray-400 rounded-md"
  >
    <div class="space-y-1">
      <!-- sm applies for sm breakpoint and higher (md, lg, etc.) -->
      <div
        class="space-y-1 sm:space-y-0 flex flex-col sm:flex-row sm:space-x-4"
      >
        <div class="font-semibold">
          {{ contact.firstName + " " + contact.lastName }}
        </div>
        <div>{{ contact.email }}</div>
      </div>
      <div
        class="space-y-1 sm:space-y-0 flex flex-col sm:flex-row sm:space-x-4"
      >
        <div>{{ contact.company }}</div>
        <div>{{ contact.phoneNumber }}</div>
      </div>
    </div>
    <div
      class="flex flex-col space-y-4 align-middle sm:flex-row sm:space-x-6 sm:space-y-0"
    >
      <button
        @click="updateContact(contact.contactId)"
        class="p-2 sm:p-0 px-4 w-20 bg-blue-100 hover:bg-blue-200 rounded-sm h-full"
      >
        Edit
      </button>
      <button
        @click="deleteContact(contact.contactId)"
        class="p-2 sm:p-0 px-4 w-20 bg-red-100 hover:bg-red-200 rounded-sm h-full"
      >
        Delete
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ContactItem",

  props: {
    contact: { type: Object }
  },

  methods: {
    deleteContact(contactId) {
      axios.delete(`/api/contacts/${contactId}`).then(() => {
        this.$emit("deleted");
      });
    },

    updateContact(contactId) {
      this.$router.push({
        name: "ContactCreateOrUpdateView",
        params: { contactId }
      });
    }
  }
};
</script>

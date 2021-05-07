<template>
  <div class="h-20 flex justify-between p-4 border border-gray-400 rounded-sm">
    <div class="space-y-1">
      <div class="flex space-x-4">
        <div class="font-semibold">
          {{ contact.firstName + " " + contact.lastName }}
        </div>
        <div>{{ contact.email }}</div>
      </div>
      <div class="flex space-x-4">
        <div>{{ contact.company }}</div>
        <div>{{ contact.phoneNumber }}</div>
      </div>
    </div>
    <div class="flex align-middle space-x-6">
      <button
        @click="updateContact(contact.contactId)"
        class="px-4 w-20 bg-blue-100 hover:bg-blue-200 rounded-sm"
      >
        Edit
      </button>
      <button
        @click="deleteContact(contact.contactId)"
        class="px-4 w-20 bg-red-100 hover:bg-red-200 rounded-sm"
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

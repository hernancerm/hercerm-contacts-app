<template>
  <div>
    <table class="mb-4 border-2 border-gray">
      <thead class="font-bold">
        <tr>
          <td>First name</td>
          <td>Last name</td>
          <td>Email</td>
          <td>Company</td>
          <td>Phone number</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="contact in contacts" :key="contact.contactId">
          <td>{{ contact.firstName }}</td>
          <td>{{ contact.lastName }}</td>
          <td>{{ contact.email }}</td>
          <td>{{ contact.company | emptyFill }}</td>
          <td>{{ contact.phoneNumber | emptyFill }}</td>
          <td>
            <button
              @click="deleteContact(contact.contactId)"
              class="text-red-600"
            >
              Delete
            </button>
          </td>
          <td>
            <button
              @click="updateContact(contact.contactId)"
              class="text-blue-600"
            >
              Edit
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <PageSelector
      @page-changed="loadPage($event)"
      :page="page"
      :totalPages="totalPages"
    />
  </div>
</template>

<script>
import axios from "axios";
import PageSelector from "@/components/ui/PageSelector.vue";

export default {
  name: "ContactsTablePaginated",

  components: {
    PageSelector
  },

  data() {
    return {
      contacts: [],
      page: 0,
      totalPages: 0
    };
  },

  methods: {
    loadPage(pageNumber) {
      axios.get(`/api/contacts?page=${pageNumber}`).then(result => {
        this.contacts = result.data.content;
        this.totalPages = result.data.totalPages;
      });
      this.page = pageNumber;
    },

    deleteContact(contactId) {
      axios.delete(`/api/contacts/${contactId}`).then(() => {
        this.loadPage(this.page);
      });
    },

    updateContact(contactId) {
      this.$router.push({
        name: "ContactCreateView",
        params: { contactId }
      });
    }
  },

  beforeMount() {
    this.loadPage(this.page);
  },

  filters: {
    emptyFill(value) {
      return value === null ? "-" : value;
    }
  }
};
</script>

<style lang="scss" scoped>
tr {
  @apply border-2;
}

td {
  @apply px-4 py-1;
}
</style>

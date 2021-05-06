<template>
  <div>
    <table>
      <thead>
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
          <td>{{ contact.company }}</td>
          <td>{{ contact.phoneNumber }}</td>
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
    }
  },

  beforeMount() {
    this.loadPage(this.page);
  }
};
</script>

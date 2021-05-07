<template>
  <div class="flex flex-col align-middle">
    <div class="space-y-2 mb-5">
      <ContactItem
        v-for="contact in contacts"
        :key="contact.contactId"
        :contact="contact"
        @deleted="reloadContacts()"
      />
    </div>
    <PageSelector
      @page-changed="loadPage($event)"
      :page="page"
      :totalPages="totalPages"
      class="mx-auto"
    />
  </div>
</template>

<script>
import axios from "axios";
import PageSelector from "@/components/ui/PageSelector.vue";
import ContactItem from "./ContactItem.vue";

export default {
  name: "ContactsTablePaginated",

  components: {
    PageSelector,
    ContactItem
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
    reloadContacts() {
      this.loadPage(this.page);
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

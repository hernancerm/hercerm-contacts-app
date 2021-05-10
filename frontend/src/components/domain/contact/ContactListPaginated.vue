<template>
  <div class="flex flex-col align-middle space-y-5">
    <SearchBar
      v-model="searchTerm"
      placeholder="Search by first name or last name"
    />
    <div class="space-y-2">
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
import PageSelector from "@/components/ui/PageSelector.vue";
import ContactItem from "./ContactItem.vue";
import SearchBar from "@/components/ui/SearchBar.vue";
import { ContactService } from "@/services/ContactService.js";

export default {
  name: "ContactsTablePaginated",

  components: {
    SearchBar,
    ContactItem,
    PageSelector
  },

  data() {
    return {
      contacts: [],
      page: 0,
      totalPages: 0,
      searchTerm: ""
    };
  },

  methods: {
    async loadPage(pageNumber) {
      // "The destructuring assignment syntax is a JavaScript expression that
      // makes it possible to unpack values from arrays, or properties from
      // objects, into distinct variables."
      //
      // Object desctructuring:
      // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#object_destructuring
      const { data } = await ContactService().getContactsPaginated({
        page: pageNumber,
        query: this.searchTerm
      });

      this.contacts = data.content;
      this.totalPages = data.totalPages;
      this.page = pageNumber;
    },

    reloadContacts() {
      this.loadPage(this.page);
    }
  },

  beforeMount() {
    this.reloadContacts();
  },

  watch: {
    searchTerm: function() {
      this.loadPage(0);
    }
  }
};
</script>

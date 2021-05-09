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
import PageSelector from "@/components/ui/PageSelector.vue";
import ContactItem from "./ContactItem.vue";
import { ContactService } from "@/services/ContactService.js";

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
    async loadPage(pageNumber) {
      // "The destructuring assignment syntax is a JavaScript expression that
      // makes it possible to unpack values from arrays, or properties from
      // objects, into distinct variables."
      //
      // Object desctructuring:
      // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#object_destructuring
      const { data } = await ContactService().getContactsPaginated({
        page: pageNumber
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

  filters: {
    emptyFill(value) {
      return value === null ? "-" : value;
    }
  }
};
</script>

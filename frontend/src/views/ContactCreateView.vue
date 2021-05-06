<template>
  <div>
    <form class="space-y-8">
      <div class="flex space-x-8">
        <div class="flex flex-col">
          <label for="first-name">First name</label>
          <input
            v-model="contact.firstName"
            type="text"
            id="first-name"
            name="first-name"
            class="w-48"
          />
        </div>
        <div class="flex flex-col">
          <label for="last-name">Last name</label>
          <input
            v-model="contact.lastName"
            type="text"
            id="last-name"
            name="last-name"
            class="w-48"
          />
        </div>
        <div class="flex flex-col">
          <label for="email">Email</label>
          <input
            v-model="contact.email"
            type="text"
            id="email"
            name="email"
            class="w-48"
          />
        </div>
      </div>
      <div class="flex space-x-8">
        <div class="flex flex-col">
          <label for="company">Company</label>
          <input
            v-model="contact.company"
            type="text"
            id="company"
            name="company"
            class="w-48"
          />
        </div>
        <div class="flex flex-col">
          <label for="phone-number">Phone number</label>
          <input
            v-model="contact.phoneNumber"
            type="text"
            id="phone-number"
            name="phone-number"
            class="w-48"
          />
        </div>
      </div>
      <button
        type="submit"
        @click.prevent="createContact()"
        class="bg-blue-500 text-white py-2 px-3 rounded-sm"
      >
        Save
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ContactCreateView",

  data: function() {
    return {
      operation: "CREATE",
      contactId: null,
      contact: {
        firstName: "",
        lastName: "",
        email: "",
        company: "",
        phoneNumber: ""
      }
    };
  },

  methods: {
    createContact() {
      if (this.operation === "CREATE") {
        axios.post("/api/contacts", this.contact).then(() => {
          this.$router.push({ name: "ContactListView" });
        });
      } else if (this.operation === "UPDATE") {
        axios.put(`/api/contacts/${this.contactId}`, this.contact).then(() => {
          this.$router.push({ name: "ContactListView" });
        });
      }
    }
  },

  mounted() {
    const pathContactId = this.$route.params.contactId;
    if (pathContactId) {
      axios.get(`/api/contacts/${pathContactId}`).then(result => {
        this.contact = {
          firstName: result.data.firstName,
          lastName: result.data.lastName,
          email: result.data.email,
          company: result.data.company,
          phoneNumber: result.data.phoneNumber
        };
        this.contactId = result.data.contactId;
        this.operation = "UPDATE";
      });
    }
  }
};
</script>

<style lang="scss" scoped>
input {
  border: 1px solid gray;
}
</style>

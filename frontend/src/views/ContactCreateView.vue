<template>
  <div>
    <h1>Register contact</h1>
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
        Create
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
      axios.post("/api/contacts", this.contact).then(() => {
        this.$router.push({ name: "HomeView" });
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

<template>
  <div>
    <router-link
      :to="{ name: 'ContactListView' }"
      class="text-white w-full flex space-x-2 py-2 px-4 bg-blue-500 hover:bg-blue-700 mb-8 rounded-sm"
      ><svg
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
          d="M10 19l-7-7m0 0l7-7m-7 7h18"
        /></svg
      ><span>Back</span></router-link
    >
    <form class="space-y-9" @submit.prevent="onSubmit()">
      <InputText
        relate="firstName"
        label="First name"
        v-model="contact.firstName"
        :serverError="serverErrors.firstName"
      />
      <InputText
        relate="lastName"
        label="Last name"
        v-model="contact.lastName"
        :serverError="serverErrors.lastName"
      />
      <InputText
        relate="email"
        label="Email"
        v-model="contact.email"
        :serverError="serverErrors.email"
      />
      <InputText
        relate="company"
        label="Company"
        v-model="contact.company"
        :serverError="serverErrors.company"
      />
      <InputText
        relate="phoneNumber"
        label="Phone number"
        v-model="contact.phoneNumber"
        :serverError="serverErrors.phoneNumber"
      />
      <button
        class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded-sm w-full text-left"
      >
        Save
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import InputText from "../components/ui/InputText.vue";

export default {
  name: "ContactCreateView",

  components: {
    InputText
  },

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
      },
      serverErrors: {}
    };
  },

  methods: {
    onSubmit() {
      this.superficialTrimWhitespace(this.contact);
      const contact = this.preprocessContact(this.contact);
      if (this.operation === "CREATE") {
        axios
          .post("/api/contacts", contact)
          .then(() => {
            this.$router.push({ name: "ContactListView" });
          })
          .catch(error => {
            this.serverErrors = error.response.data;
          });
      } else if (this.operation === "UPDATE") {
        axios
          .put(`/api/contacts/${this.contactId}`, contact)
          .then(() => {
            this.$router.push({ name: "ContactListView" });
          })
          .catch(error => {
            this.serverErrors = error.response.data;
          });
      }
    },

    preprocessContact(contact) {
      contact = this.copyNonEmptyProperties(this.contact);
      return {
        ...contact,
        // Allow users to separate phone numbers with white space.
        phoneNumber: contact.phoneNumber.replaceAll(/\s/g, "")
      };
    },

    copyNonEmptyProperties(object) {
      const output = {};
      for (const property in object) {
        if (object[property] !== null && object[property] !== "") {
          output[property] = object[property];
        }
      }
      return output;
    },

    superficialTrimWhitespace(object) {
      for (const property in object) {
        if (typeof object[property] === "string") {
          object[property] = object[property].trim();
        }
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

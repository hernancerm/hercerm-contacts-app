<template>
  <div>
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
        class="bg-blue-500 focus:bg-blue-700 text-white py-2 px-4 rounded-sm"
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
      const contact = this.removeEmptyProperties(this.contact);
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

    removeEmptyProperties(object) {
      const output = {};
      for (const property in object) {
        if (object[property] !== null && object[property] !== "") {
          output[property] = object[property];
        }
      }
      return output;
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

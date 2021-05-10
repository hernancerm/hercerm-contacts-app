<template>
  <div class="relative">
    <label>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="text-gray-400 h-6 w-6 absolute left-3 top-2"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
        />
      </svg>
      <input
        :value="value"
        @input="$emit('input', $event.target.value)"
        type="text"
        :placeholder="placeholder"
        class="pl-11 p-2 pr-12 border border-gray-400 w-full rounded-sm outline-none duration-200 focus:ring-2 focus:ring-blue-300"
      />
    </label>
    <button
      @click="clearSearchTerm()"
      class="text-sm absolute top-0 right-0 bg-gray-400 hover:bg-gray-600 text-white py-1 px-2 rounded-sm h-full"
    >
      <svg
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
          d="M6 18L18 6M6 6l12 12"
        />
      </svg>
    </button>
  </div>
</template>

<script>
export default {
  name: "SearchBar",

  props: {
    value: { type: String },
    placeholder: { type: String }
  },

  methods: {
    clearSearchTerm() {
      this.value = "";
      this.$emit("input", this.value);
    }
  }
};
</script>

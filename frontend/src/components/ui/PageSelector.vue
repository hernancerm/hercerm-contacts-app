<template>
  <div class="flex w-max space-x-3 items-center">
    <button @click="decrement()">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M15 19l-7-7 7-7"
        />
      </svg>
    </button>
    <div>Page</div>
    <div class="font-bold">
      {{ offsetCurrentPage }}
    </div>
    <div>of</div>
    <div>{{ totalPages }}</div>
    <button @click="increment()">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M9 5l7 7-7 7"
        />
      </svg>
    </button>
  </div>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "PageSelector",

  props: {
    page: { type: Number },
    totalPages: { type: Number }
  },

  data() {
    return {
      currentPage: 0,
      offset: 1
    };
  },

  methods: {
    increment() {
      if (this.currentPage < this.totalPages - this.offset) {
        this.currentPage++;
        this.firePageChangedEvent();
      }
    },

    decrement() {
      if (this.currentPage > 1 - this.offset) {
        this.currentPage--;
        this.firePageChangedEvent();
      }
    },

    firePageChangedEvent() {
      this.$emit("page-changed", this.currentPage);
    }
  },

  // Allow parent to set current page.
  watch: {
    page() {
      this.currentPage = this.page;
    }
  },

  // Offest page to start at a value other than 0.
  computed: {
    offsetCurrentPage() {
      return this.currentPage + this.offset;
    }
  }
});
</script>

<style lang="scss" scoped>
button {
  @apply w-8;
}
</style>

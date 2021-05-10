<template>
  <div>
    <input
      :value="value"
      @input="$emit('input', $event.target.value)"
      type="text"
      class="p-1 border border-gray-400 w-full rounded-sm"
    />
  </div>
</template>

<script>
export default {
  name: "SearchBar",

  props: {
    value: { type: String }
  }
};
</script>

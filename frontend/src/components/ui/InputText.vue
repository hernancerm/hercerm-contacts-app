<template>
  <div class="relative flex flex-col">
    <label :for="relate" class="font-semibold">{{ label }}</label>
    <input
      type="text"
      :value="value"
      @input="$emit('input', $event.target.value)"
      :id="relate"
      class="w-full border border-gray-600 p-1"
    />
    <span class="absolute -bottom-6 text-red-600">{{ serverError }}</span>
  </div>
</template>

<script>
export default {
  name: "InputText",

  props: {
    value: { type: String },
    label: { type: String },
    relate: { type: String },
    serverError: { type: String }
  }
};
</script>

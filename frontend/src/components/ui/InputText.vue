<template>
  <div class="relative flex flex-col">
    <label :for="relate" class="font-semibold mb-1 w-max">{{ label }}</label>
    <input
      type="text"
      :value="value"
      @input="$emit('input', $event.target.value)"
      :id="relate"
      class="w-full duration-200 outline-none border border-gray-500 p-1 rounded-sm focus:ring-2 focus:ring-blue-300"
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

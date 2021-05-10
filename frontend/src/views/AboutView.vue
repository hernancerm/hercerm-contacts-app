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
    <h1>About this app 🤔</h1>
    <div class="text-xl space-y-5 leading-relaxed">
      <p>
        Hello! My name is <b>Hernán Cervera</b>, I designed and developed this
        application using Java, Spring, Vue.js, TailwindCSS and PostgreSQL.
      </p>
      <p>
        You can find the code in
        <a
          class="underline text-blue-400 hover:text-blue-500"
          target="_blank"
          href="https://github.com/HerCerM/hercerm-contacts-app"
          >my GitHub repository.</a
        >
      </p>
    </div>
  </div>
</template>

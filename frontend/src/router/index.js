import Vue from "vue";
import VueRouter from "vue-router";
import ContactsListView from "../views/ContactsListView.vue";
import ContactCreateView from "../views/ContactCreateView.vue";
import PageNotFoundView from "../views/PageNotFoundView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "ContactsListView",
    component: ContactsListView
  },
  {
    path: "/create",
    name: "ContactCreateView",
    component: ContactCreateView
  },
  {
    path: "*",
    component: PageNotFoundView
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;

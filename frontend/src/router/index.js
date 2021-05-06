import Vue from "vue";
import VueRouter from "vue-router";
import ContactListView from "../views/ContactListView.vue";
import ContactCreateView from "../views/ContactCreateView.vue";
import PageNotFoundView from "../views/PageNotFoundView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "ContactListView",
    component: ContactListView
  },
  {
    path: "/add",
    name: "ContactCreateView",
    component: ContactCreateView
  },
  {
    path: "/edit/:contactId",
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

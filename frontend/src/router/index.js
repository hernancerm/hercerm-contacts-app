import Vue from "vue";
import VueRouter from "vue-router";
import ContactListView from "../views/ContactListView.vue";
import ContactCreateOrUpdateView from "../views/ContactCreateOrUpdateView.vue";
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
    name: "ContactCreateOrUpdateView",
    component: ContactCreateOrUpdateView
  },
  {
    path: "/edit/:contactId",
    name: "ContactCreateOrUpdateView",
    component: ContactCreateOrUpdateView
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

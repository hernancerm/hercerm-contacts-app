import Vue from "vue";
import VueRouter from "vue-router";
import ContactListView from "../views/ContactListView.vue";
import ContactCreateOrUpdateView from "../views/ContactCreateOrUpdateView.vue";
import PageNotFoundView from "../views/PageNotFoundView.vue";
import AboutView from "../views/AboutView.vue";

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
    path: "/about",
    name: "AboutView",
    component: AboutView
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

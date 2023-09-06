import type { RouteRecordRaw } from "vue-router";
import ContactView from "@/views/ContactView/ContactView.vue";
import ErrorView from "@/views/ErrorView.vue";

export const ROUTES: RouteRecordRaw[] = [
    {
        path: "/",
        redirect: { name: "contacts" }
    },
    {
        path: "/contacts",
        name: "contacts",
        component: ContactView,
    },
    {
        path: "/favorite-contacts",
        name: "favorite-contacts",
        component: () => import("../../views/FavoriteContactView/FavoriteContactView.vue"),
    },
    {
        path: "/:pathMatch(.*)*",
        name: "404",
        component: ErrorView
    }
];
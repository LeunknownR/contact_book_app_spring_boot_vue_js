import AbsolutePaths from "@/router/utils/absolutePaths";
import type { NavbarItem } from "./types";

export const NAVBAR_ITEMS: NavbarItem[] = [
    {
        id: "contacts",
        text: "Contactos",
        to: AbsolutePaths.Contacts,
        icon: "foundation:torsos-all"
    },
    {
        id: "favorite-contacts",
        text: "Favoritos",
        to: AbsolutePaths.FavoriteContacts,
        icon: "uis:favorite"
    }
];
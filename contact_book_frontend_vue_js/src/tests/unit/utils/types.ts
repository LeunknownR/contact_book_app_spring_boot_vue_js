import type { mount } from "@vue/test-utils";

export type VueTestComponent<C> = ReturnType<typeof mount<C>>;

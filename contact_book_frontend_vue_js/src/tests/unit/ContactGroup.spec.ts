import type { ContactGroupItem } from "@/types/domain";
import { expect, describe, it } from "vitest";
import { shallowMount } from "@vue/test-utils";
import ContactGroup from "@/features/contacts/ContactGroup/ContactGroup.vue";
import { createMockContact } from "./utils/mocks";
import { buildContactStore } from "@/store";
import ContactCard from "@/features/contacts/ContactCard/ContactCard.vue";

describe("ContactGroup.vue", () => {
	it("should renders a contact list", () => {
		const contactGroupItem: ContactGroupItem = {
			letter: "M",
			contacts: [createMockContact()],
		};
		const wrapper = shallowMount(ContactGroup, {
			props: {
				contactGroupItem,
				contactSelected: null,
				hiddenFavoriteSwitch: true,
			},
			global: {
				plugins: [buildContactStore()],
			},
		});
		const card = wrapper.findComponent(ContactCard);
		expect(card.exists()).toBe(true);
	});
	it("should be emitted 'selectContact' event", () => {
		const contactGroupItem: ContactGroupItem = {
			letter: "M",
			contacts: [createMockContact()],
		};
		const wrapper = shallowMount(ContactGroup, {
			props: {
				contactGroupItem,
				contactSelected: null,
				hiddenFavoriteSwitch: true,
			},
			global: {
				plugins: [buildContactStore()],
				stubs: {
					ContactCard: false,
				},
			},
		});
		const card = wrapper.findComponent(ContactCard);
		card.trigger("click");
		expect(wrapper.emitted("selectContact")).toBeTruthy();
	});
});

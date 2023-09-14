import type { ContactGroupItem } from "@/types/domain";
import { expect, describe, it } from "vitest";
import { shallowMount } from "@vue/test-utils";
import ContactGroup from "@/features/contacts/ContactGroup/ContactGroup.vue";
import { MOCK_CONTACT } from "./mocks";
import { buildContactStore } from "@/store";

describe("ContactGroup.vue", () => {
	it("should renders a contact list", () => {
		const contactGroupItem: ContactGroupItem = {
			letter: "M",
			contacts: [MOCK_CONTACT],
		};
		const wrapper = shallowMount(ContactGroup, {
			props: {
				contactGroupItem,
				contactSelected: null,
				hiddenFavorite: true,
			},
			global: {
				plugins: [buildContactStore()],
			},
		});
		expect(
			wrapper.findAllComponents("contact-card-stub").length
		).toBeGreaterThan(0);
	});
});

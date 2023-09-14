import ContactCard from "@/features/contacts/ContactCard/ContactCard.vue";
import { mount, shallowMount } from "@vue/test-utils";
import { describe, expect, it } from "vitest";
import { MOCK_CONTACT } from "./mocks";
import type { Contact } from "@/types/domain";

describe("ContactCard.vue", () => {
	it("should be hidden star icon for toggle favorite", () => {
		const wrapper = shallowMount(ContactCard, {
			props: {
				contact: { ...MOCK_CONTACT },
				contactSelected: null,
				hiddenFavorite: true,
			},
		});
		const favoriteSwitchComponent = wrapper.findComponent(
			"favorite-switch-stub"
		);
		expect(favoriteSwitchComponent.exists()).toBe(false);
	});
	it("should be shown star icon for toggle favorite", () => {
		const wrapper = shallowMount(ContactCard, {
			props: {
				contact: { ...MOCK_CONTACT },
				contactSelected: null,
				hiddenFavorite: false,
			},
		});
		const favoriteSwitchComponent = wrapper.findComponent(
			"favorite-switch-stub"
		);
		expect(favoriteSwitchComponent.exists()).toBe(true);
	});
	it("should switch favorite when the star icon is clicked", async () => {
		const contact: Contact = { ...MOCK_CONTACT };
		const wrapper = mount(ContactCard, {
			props: {
				contact,
				contactSelected: null,
				hiddenFavorite: false,
				onToggleFavorite: () =>
					(contact.isFavorite = !contact.isFavorite),
			},
		});
		const favoriteSwitchComponent = wrapper.findComponent(
			"[data-test='favorite-switch']"
		);
		await favoriteSwitchComponent.trigger("click");
		expect(contact.isFavorite).toBe(!MOCK_CONTACT.isFavorite);
	});
});

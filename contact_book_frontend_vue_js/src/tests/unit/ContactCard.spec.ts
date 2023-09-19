import ContactCard from "@/features/contacts/ContactCard/ContactCard.vue";
import { mount, shallowMount } from "@vue/test-utils";
import { beforeEach, describe, expect, it } from "vitest";
import { createMockContact } from "./utils/mocks";
import type { Contact } from "@/types/domain";
import FavoriteSwitch from "@/features/contacts/ContactCard/FavoriteSwitch.vue";
import type { VueTestComponent } from "./utils/types";
import InitialName from "@/components/InitialName.vue";

type ContactCardComponent = VueTestComponent<typeof ContactCard>;
//#region Constants
const FIRST_PHONE_NUMBER_SPAN_QUERY: string = "[data-test='first-phone']";
//#endregion
describe("ContactCard.vue", () => {
	describe("for favorite switch", () => {
		let wrapper: ContactCardComponent;
		beforeEach(() => {
			wrapper = shallowMount(ContactCard, {
				props: {
					contact: createMockContact(),
					selectedContact: null,
					hiddenFavoriteSwitch: false,
				},
			});
		});
		it("should be hidden favorite switch when hiddenFavoriteSwitch is false", async () => {
			await wrapper.setProps({
				...wrapper.props(),
				hiddenFavoriteSwitch: true,
			});
			const favoriteSwitchComponent =
				wrapper.findComponent(FavoriteSwitch);
			expect(favoriteSwitchComponent.exists()).toBe(false);
		});
		it("should be shown favorite switch when hiddenFavoriteSwitch is true", () => {
			const favoriteSwitchComponent =
				wrapper.findComponent(FavoriteSwitch);
			expect(favoriteSwitchComponent.exists()).toBe(true);
		});
	});
	it("should be switched favorite value when switch favorite is flipped", async () => {
		const wrapper = mount(ContactCard, {
			props: {
				contact: createMockContact(),
				selectedContact: null,
				hiddenFavoriteSwitch: false,
			},
		});
		await wrapper.setProps({
			...wrapper.props(),
			onToggleFavorite: async () => {
				const prevProps = wrapper.props();
				await wrapper.setProps({
					...prevProps,
					contact: {
						...prevProps.contact,
						isFavorite: !prevProps.contact.isFavorite,
					},
				});
			},
		});
		const favoriteSwitchComponent = wrapper.findComponent(FavoriteSwitch);
		await favoriteSwitchComponent.trigger("click");
		expect(wrapper.vm.contact.isFavorite).toBe(
			!createMockContact().isFavorite
		);
	});
	it("should be shown the initial character of contact name", () => {
		const contact: Contact = createMockContact();
		const wrapper = mount(ContactCard, {
			props: {
				contact,
				selectedContact: null,
				hiddenFavoriteSwitch: true,
			},
		});
		const initialNameSpan = wrapper.findComponent(InitialName).find("span");
		expect(initialNameSpan.text()).toBe(contact.name[0]);
	});
	it("should exist at least 1 phone number in the contact", () => {
		const phoneNumberLength: number = 9;
		const wrapper = shallowMount(ContactCard, {
			props: {
				contact: createMockContact(),
				selectedContact: null,
				hiddenFavoriteSwitch: true,
			},
		});
		const firstPhoneSpan = wrapper.find(FIRST_PHONE_NUMBER_SPAN_QUERY);
		expect(firstPhoneSpan.text()).toHaveLength(phoneNumberLength);
	});
	it("should be selected current contact when card is clicked", async () => {
		const wrapper = shallowMount(ContactCard, {
			props: {
				contact: createMockContact(),
				selectedContact: null,
				hiddenFavoriteSwitch: true,
			},
		});
		await wrapper.setProps({
			...wrapper.props(),
			onSelectContact: async (contact: Contact) => {
				await wrapper.setProps({
					...wrapper.props(),
					selectedContact: contact,
				});
			},
		});
		await wrapper.trigger("click");
		expect(wrapper.vm.selectedContact).not.toBeNull();
	});
});

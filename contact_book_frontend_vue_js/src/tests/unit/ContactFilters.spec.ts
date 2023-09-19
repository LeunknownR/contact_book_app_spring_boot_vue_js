import ContactFilters from "@/features/contacts/ContactFilters/ContactFilters.vue";
import { mount } from "@vue/test-utils";
import { describe, expect, it, beforeEach } from "vitest";
import { INIT_CONTACT_FILTERS } from "@/features/contacts/ContactFilters/utils/constants";
import type { ContactFiltersData } from "@/services/utils/types";
import ClearFiltersButton from "@/features/contacts/ContactFilters/ClearFiltersButton.vue";
import type { VueTestComponent } from "./utils/types";

type ContactFiltersComponent = VueTestComponent<typeof ContactFilters>;
//#region Constants
const CONTACT_NAME_FILTER_QUERY: string = "[data-test='contact-name-filter']";
const PHONE_NUMBER_FILTER_QUERY: string = "[data-test='phone-number-filter']";
//#endregion
describe("ContactFilters.vue", () => {
	let wrapper: ContactFiltersComponent;
	beforeEach(() => {
		wrapper = mount(ContactFilters, {
			props: {
				filters: { ...INIT_CONTACT_FILTERS },
			},
		});
	});
	describe("when filters are empty and updated", () => {
		describe("when value of filter is changed", () => {
			it("should be updated contact name filter", async () => {
				const newContactName: string = "Nicolle";
				const input = wrapper.findComponent(CONTACT_NAME_FILTER_QUERY);
				await input.setValue(newContactName);
				expect(wrapper.vm.filters.contactName).toBe(newContactName);
			});
			it("should be updated phone number filter", async () => {
				const newPhoneNumber: string = "900696111";
				const input = wrapper.findComponent(PHONE_NUMBER_FILTER_QUERY);
				await input.setValue(newPhoneNumber);
				expect(wrapper.vm.filters.phoneNumber).toBe(newPhoneNumber);
			});
		});
		describe("should have maxlength of", () => {
			it("50 characters for contact name filter", async () => {
				const maxLength: string = "50";
				const input = wrapper.findComponent(CONTACT_NAME_FILTER_QUERY);
				expect(input.attributes("maxlength")).toBe(maxLength);
			});
			it("9 characters for phone number filter", async () => {
				const maxLength: string = "9";
				const input = wrapper.findComponent(PHONE_NUMBER_FILTER_QUERY);
				expect(input.attributes("maxlength")).toBe(maxLength);
			});
		});
	});
	describe("when clear filters button is clicked", () => {
		//#region Functions
		const clickFilterButton = async (): Promise<void> => {
			const btn = wrapper.findComponent(ClearFiltersButton);
			await btn.trigger("click");
		};
		//#endregion
		it("should be cleared all filters when have values", async () => {
			const filtersFilled: ContactFiltersData = {
				contactName: "Nicolle",
				phoneNumber: "901",
			};
			await wrapper.setProps({
				filters: filtersFilled,
			});
			await clickFilterButton();
			expect(wrapper.vm.filters).toEqual(INIT_CONTACT_FILTERS);
		});
		it("shouldn't be emitted 'fetchContacts' event when filters are empty", async () => {
			await clickFilterButton();
			expect(wrapper.emitted("fetchContacts")).toBeFalsy();
		});
	});
});

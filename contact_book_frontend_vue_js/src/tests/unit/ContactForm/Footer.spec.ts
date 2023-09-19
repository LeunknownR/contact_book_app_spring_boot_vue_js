import Footer from "@/features/contacts/ContactForm/layout/Footer.vue";
import { mount, shallowMount } from "@vue/test-utils";
import { describe, expect, it } from "vitest";
import type { VueTestComponent } from "../utils/types";

type FooterComponent = VueTestComponent<typeof Footer>;
//#region Constants
export const SAVE_BUTTON_QUERY: string = "[data-test='save-form-btn']";
export const REMOVE_CONTACT_BTN_QUERY: string =
	"[data-test='remove-contact-btn']";
//#endregion
describe("Footer.vue (ContactForm)", () => {
	describe("for save form button content", () => {
		const getButtonText = (wrapper: FooterComponent): string => {
			return wrapper.findComponent(SAVE_BUTTON_QUERY).find("span").text();
		};
		it("should be 'Agregar' when is not edition form", async () => {
			const wrapper = mount(Footer, {
				props: {
					isEdition: false,
				},
			});
			const saveButtonSpan = getButtonText(wrapper);
			expect(saveButtonSpan).toBe("Agregar");
		});
		it("should be 'Editar' when is edition form", async () => {
			const wrapper = mount(Footer, {
				props: {
					isEdition: true,
				},
			});
			const saveButtonSpan = getButtonText(wrapper);
			expect(saveButtonSpan).toBe("Editar");
		});
	});
	describe("for remove contact button", () => {
		it("shouldn't be shown when isn't edition", () => {
			const wrapper = shallowMount(Footer, {
				props: {
					isEdition: false,
				},
			});
			const button = wrapper.find(REMOVE_CONTACT_BTN_QUERY);
			expect(button.exists()).toBe(false);
		});
		it("should be shown when is edition", () => {
			const wrapper = shallowMount(Footer, {
				props: {
					isEdition: true,
				},
			});
			const button = wrapper.find(REMOVE_CONTACT_BTN_QUERY);
			expect(button.exists()).toBe(true);
		});
		it("should be emitted 'openRemoveContactConfirmModal' event when button is clicked", () => {
			const wrapper = shallowMount(Footer, {
				props: {
					isEdition: true,
				},
			});
			const button = wrapper.find(REMOVE_CONTACT_BTN_QUERY);
			button.trigger("click");
			expect(
				wrapper.emitted("openRemoveContactConfirmModal")
			).toBeTruthy();
		});
	});
});

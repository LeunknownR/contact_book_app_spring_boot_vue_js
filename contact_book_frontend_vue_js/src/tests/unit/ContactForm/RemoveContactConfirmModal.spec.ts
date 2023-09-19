import RemoveContactConfirmModal from "@/features/contacts/ContactForm/modals/RemoveContactConfirmModal.vue";
import { mount } from "@vue/test-utils";
import { beforeEach, describe, expect, it } from "vitest";
import type { VueTestComponent } from "../utils/types";
import { Transition } from "vue";
import { setupTeleport } from "../utils/helpers";

type RemoveContactConfirmModalComponent = VueTestComponent<
	typeof RemoveContactConfirmModal
>;
//#region Constants
const CANCEL_BTN_QUERY: string = "[data-test='cancel-btn']";
export const ACCEPT_BTN_QUERY: string = "[data-test='accept-btn']";
//#endregion
describe("RemoveContactConfirmModal.vue", () => {
	const modalIsOpen = (
		wrapper: RemoveContactConfirmModalComponent
	): boolean => {
		return wrapper.findComponent(Transition).find("div").exists();
	};
	setupTeleport("modals");
	it("should be hidden modal if isOpen is false", () => {
		const wrapper = mount(RemoveContactConfirmModal, {
			props: {
				isOpen: false,
			},
		});
		expect(modalIsOpen(wrapper)).toBe(false);
	});
	describe("when isOpen is true", () => {
		let wrapper: RemoveContactConfirmModalComponent;
		beforeEach(() => {
			wrapper = mount(RemoveContactConfirmModal, {
				props: {
					isOpen: true,
				},
			});
		});
		it("should be shown", () => {
			expect(modalIsOpen(wrapper)).toBe(true);
		});
		it("should be emitted 'update:isOpen' event with false when cancel button is clicked", async () => {
			const button = wrapper.findComponent(CANCEL_BTN_QUERY);
			await button.trigger("click");
			expect(wrapper.emitted("update:isOpen")?.[0][0]).toBe(false);
		});
		describe("when accept button is clicked", () => {
			beforeEach(async () => {
				const button = wrapper.findComponent(ACCEPT_BTN_QUERY);
				await button.trigger("click");
			});
			it("should be emitted 'update:isOpen' event with false", async () => {
				expect(wrapper.emitted("update:isOpen")?.[0][0]).toBe(false);
			});
			it("should be emitted 'removeContact' event", async () => {
				expect(wrapper.emitted("removeContact")).toBeTruthy();
			});
		});
	});
});

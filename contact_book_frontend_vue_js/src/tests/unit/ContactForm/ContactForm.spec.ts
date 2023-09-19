import { buildContactStore } from "@/store";
import ActionTypes from "@/store/contact-store/action-types";
import { shallowMount } from "@vue/test-utils";
import { beforeEach, describe, expect, it } from "vitest";
import useFormInitializer from "@/features/contacts/ContactForm/composables/useFormInitializer";
import ErrorMessage from "@/components/ErrorMessage.vue";
import ContactForm from "@/features/contacts/ContactForm/ContactForm.vue";
import {
	createMockContact,
	createMockContactWith3Phones,
} from "../utils/mocks";
import type { Contact } from "@/types/domain";
import type { VueTestComponent } from "../utils/types";
import type {
	ContactFormEditStatus,
	ContactFormErrors,
	ContactFormType,
} from "@/features/contacts/ContactForm/utils/types";
import type { StateContactView } from "@/views/utils/types";
import {
	buildActiveFormEditStatus,
	buildContactForm,
	buildContactFormEditStatus,
	buildContactFormErrors,
} from "@/features/contacts/ContactForm/utils/constants";

describe("ContactForm.vue", () => {
	it("should be shown error message below fields if there is an persistence error", async () => {
		const error: string = "Algún error";
		const wrapper = shallowMount(ContactForm, {
			props: {
				selectedContact: null,
				fetchContactsActionType: ActionTypes.FetchContacts,
				formInitializer: useFormInitializer({
					contactGroup: [],
					selectedContact: null,
					isAddingContact: true,
				}),
			},
			global: {
				stubs: {
					ErrorMessage: false,
				},
				plugins: [buildContactStore()],
			},
		});
		await wrapper.vm.$nextTick();
		wrapper.vm.state.errors.persistence = error;
		await wrapper.vm.$nextTick();
		expect(
			wrapper
				.findComponent(ErrorMessage)
				.find("[data-test='message']")
				.text()
		).toBe(error);
	});
	describe("when selected contact change", () => {
		let wrapper: VueTestComponent<typeof ContactForm>;
		const mountWrapper = (selectedContact: Contact | null): void => {
			wrapper = shallowMount(ContactForm, {
				props: {
					selectedContact,
					fetchContactsActionType: ActionTypes.FetchContacts,
					formInitializer: useFormInitializer({
						contactGroup: [],
						selectedContact,
						isAddingContact: selectedContact === null,
					}),
				},
				global: {
					plugins: [buildContactStore()],
				},
			});
		};
		describe("from a contact to another contact", () => {
			beforeEach(async () => {
				mountWrapper(createMockContact());
				await wrapper.setProps({
					...wrapper.props(),
					selectedContact: createMockContactWith3Phones(),
				});
			});
			it("should be updated the contact form according to new contact selected", () => {
				const newSelectedContact = createMockContactWith3Phones();
				const newForm: ContactFormType = {
					name: newSelectedContact.name,
					email: newSelectedContact.email,
					category: newSelectedContact.category,
					isFavorite: newSelectedContact.isFavorite,
					phones: newSelectedContact.phones,
				};
				expect(wrapper.vm.state.form).toEqual(newForm);
			});
			it("should be updated the edited status of form according to new contact selected", () => {
				const newFormEditStatus: ContactFormEditStatus = {
					name: false,
					email: false,
					category: false,
					phones: [false, false, false],
				};
				expect(wrapper.vm.state.formEditStatus).toEqual(
					newFormEditStatus
				);
			});
			it("should be updated the form errors according to new contact selected", () => {
				const newFormErrors: ContactFormErrors = {
					name: null,
					email: null,
					category: null,
					phones: [null, null, null],
					phoneGroup: null,
					persistence: null,
				};
				expect(wrapper.vm.state.errors).toEqual(newFormErrors);
			});
		});
		describe("from no contact to another contact", () => {
			beforeEach(async () => {
				mountWrapper(null);
				await wrapper.setProps({
					...wrapper.props(),
					selectedContact: createMockContact(),
				});
			});
			it("should be updated the contact form according to new contact selected", () => {
				const newSelectedContact = createMockContact();
				const newForm: ContactFormType = {
					name: newSelectedContact.name,
					email: newSelectedContact.email,
					category: newSelectedContact.category,
					isFavorite: newSelectedContact.isFavorite,
					phones: newSelectedContact.phones,
				};
				expect(wrapper.vm.state.form).toEqual(newForm);
			});
			it("should be updated the edited status of form according to new contact selected", () => {
				const newFormEditStatus: ContactFormEditStatus = {
					name: false,
					email: false,
					category: false,
					phones: [false],
				};
				expect(wrapper.vm.state.formEditStatus).toEqual(
					newFormEditStatus
				);
			});
			it("should be updated the form errors according to new contact selected", () => {
				const newFormErrors: ContactFormErrors = {
					name: null,
					email: null,
					category: null,
					phones: [null],
					phoneGroup: null,
					persistence: null,
				};
				expect(wrapper.vm.state.errors).toEqual(newFormErrors);
			});
		});
		describe("from contact to no contact", () => {
			beforeEach(async () => {
				mountWrapper(createMockContact());
				await wrapper.setProps({
					...wrapper.props(),
					selectedContact: null,
				});
			});
			it("should be updated the contact form according to new contact selected", () => {
				const newForm: ContactFormType = buildContactForm();
				expect(wrapper.vm.state.form).toEqual(newForm);
			});
			it("should be updated the edited status of form according to new contact selected", () => {
				const newFormEditStatus: ContactFormEditStatus =
					buildActiveFormEditStatus();
				expect(wrapper.vm.state.formEditStatus).toEqual(
					newFormEditStatus
				);
			});
			it("should be updated the form errors according to new contact selected", () => {
				const newFormErrors: ContactFormErrors =
					buildContactFormErrors();
				expect(wrapper.vm.state.errors).toEqual(newFormErrors);
			});
		});
	});
});

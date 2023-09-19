import useSetupForm from "@/features/contacts/ContactForm/composables/useSetupForm";
import {
	buildContactForm,
	buildActiveFormEditStatus,
	buildContactFormErrors,
	buildContactFormEditStatus,
} from "@/features/contacts/ContactForm/utils/constants";
import type {
	ContactFormEditStatus,
	ContactFormErrors,
	ContactFormType,
	ContactStateForm,
} from "@/features/contacts/ContactForm/utils/types";
import type { Contact } from "@/types/domain";
import { VueWrapper, mount } from "@vue/test-utils";
import { beforeAll, beforeEach, describe, expect, it } from "vitest";
import { reactive, computed } from "vue";
import { createMockContact } from "../../utils/mocks";

describe("useSetupForm.ts", () => {
	let state: ContactStateForm;
	const runUseSetupForm = (selectedContact: Contact | null): VueWrapper => {
		return mount({
			setup() {
				const selectedContactComputedRef = computed<Contact | null>(
					() => selectedContact
				);
				useSetupForm(state, selectedContactComputedRef);
			},
			template: "<div></div>",
		});
	};
	describe("when there isn't a selected contact", () => {
		beforeAll(() => {
			state = reactive({
				form: buildContactForm(),
				formEditStatus: buildContactFormEditStatus(),
				errors: buildContactFormErrors(),
			});
			runUseSetupForm(null);
		});
		it("should be an empty form", async () => {
			const expectedForm: ContactFormType = buildContactForm();
			expect(state.form).toEqual(expectedForm);
		});
		it("should be an empty edited status of form", async () => {
			const formEditStatus: ContactFormEditStatus =
				buildActiveFormEditStatus();
			expect(state.formEditStatus).toEqual(formEditStatus);
		});
		it("should be empty form errors", async () => {
			const expectedFormErrors: ContactFormErrors =
				buildContactFormErrors();
			expect(state.errors).toEqual(expectedFormErrors);
		});
	});
	describe("when there is a selected contact", () => {
		let selectedContact: Contact;
		beforeAll(() => {
			state = reactive({
				form: buildContactForm(),
				formEditStatus: buildContactFormEditStatus(),
				errors: buildContactFormErrors(),
			});
			selectedContact = createMockContact();
			runUseSetupForm(selectedContact);
		});
		it("should be filled form according to  selected contact", async () => {
			const expectedForm: ContactFormType = {
				name: selectedContact.name,
				email: selectedContact.email,
				category: selectedContact.category,
				isFavorite: selectedContact.isFavorite,
				phones: selectedContact.phones,
			};
			expect(state.form).toEqual(expectedForm);
		});
		it("should be filled edited status of form according to selected contact", async () => {
			const formEditStatus: ContactFormEditStatus = {
				name: false,
				email: false,
				category: false,
				phones: [false],
			};
			expect(state.formEditStatus).toEqual(formEditStatus);
		});
		it("should be reseted form errors according to selected contact", async () => {
			const expectedFormErrors: ContactFormErrors = {
				name: null,
				email: null,
				category: null,
				phones: [null],
				phoneGroup: null,
				persistence: null,
			};
			expect(state.errors).toEqual(expectedFormErrors);
		});
	});
});

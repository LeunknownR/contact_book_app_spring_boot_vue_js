import type ApiResponseMessages from "@/services/utils/constants";
import FormErrorMessages from "../utils/errors";
import type { ContactForm, ContactFormError } from "../utils/types";
import { RESPONSE_MESSAGE_BY_ERROR_MESSAGE } from "../utils/constants";
import { watch } from "vue";

type CheckFormAction = {
	checkErrors: () => boolean;
	checkPersistenceErrors: (message: ApiResponseMessages) => void;
};
const EMAIL_VALIDATION_REGEX: RegExp = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
const useValidationForm = (
	form: ContactForm,
	errors: ContactFormError
): CheckFormAction => {
	// Watch field changes for clear the persistence error
	watch(
		() => form,
		() => (errors.persistence = null),
		{ deep: true }
	);
	const checkName = () => {
		errors.name = !form.name ? FormErrorMessages.RequiredName : null;
	};
	const checkEmail = () => {
		const { email } = form;
		errors.email = !email ? FormErrorMessages.RequiredEmail : null;
		if (errors.email) return;
		errors.email = !EMAIL_VALIDATION_REGEX.test(email)
			? FormErrorMessages.InvalidEmail
			: null;
	};
	const checkCategory = () => {
		errors.category = !form.category
			? FormErrorMessages.RequiredCategory
			: null;
	};
	const checkPhones = () => {
		const { phones } = form;
		errors.phoneGroup =
			phones.length === 0 ? FormErrorMessages.RequiredOnePhone : null;
		phones.forEach((phone, i) => {
			errors.phones[i] =
				phone.number.length !== 9
					? FormErrorMessages.PhoneLength
					: null;
		});
	};
	const checkPersistenceErrors = (message: ApiResponseMessages): void => {
		errors.persistence =
			RESPONSE_MESSAGE_BY_ERROR_MESSAGE.get(message) || null;
	};
	const isValidForm = (): boolean => {
		return Object.values(errors)
			.flat()
			.every(error => !error);
	};
	const checkErrors = (): boolean => {
		checkName();
		checkEmail();
		checkCategory();
		checkPhones();
		return isValidForm();
	};
	return {
		checkErrors,
		checkPersistenceErrors,
	};
};

export default useValidationForm;

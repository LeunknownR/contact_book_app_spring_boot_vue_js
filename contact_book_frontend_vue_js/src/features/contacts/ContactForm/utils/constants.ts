import ApiResponseMessages from "@/services/utils/constants";
import type {
	ContactForm,
	ContactFormEditStatus,
	ContactFormError,
} from "./types";
import FormErrorMessages from "./errors";

export const INIT_CONTACT_FORM: ContactForm = {
	name: "",
	email: "",
	category: null,
	isFavorite: false,
	phones: [],
};
export const INIT_FORM_FIELD_EDIT_STATUS: ContactFormEditStatus = {
	name: false,
	email: false,
	category: false,
	phones: [],
};
export const INIT_FORM_ERRORS: ContactFormError = {
	name: null,
	email: null,
	category: null,
	phones: [],
	phoneGroup: null,
	persistence: null,
};
export const RESPONSE_MESSAGE_BY_ERROR_MESSAGE: Map<
	ApiResponseMessages,
	FormErrorMessages
> = new Map([
	[
		ApiResponseMessages.ContactNameOrEmailAlreadyExists,
		FormErrorMessages.ContactNameOrEmailAlreadyExists,
	],
	[
		ApiResponseMessages.SomePhonesAlreadyExist,
		FormErrorMessages.SomePhonesAlreadyExist,
	],
]);

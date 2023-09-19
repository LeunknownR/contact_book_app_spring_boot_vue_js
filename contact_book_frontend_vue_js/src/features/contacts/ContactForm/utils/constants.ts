import ApiResponseMessages from "@/services/utils/constants";
import type {
	ContactFormType,
	ContactFormEditStatus,
	ContactFormErrors,
} from "./types";
import FormErrorMessages from "./errors";

export const buildContactForm = (): ContactFormType => {
	return {
		name: "",
		email: "",
		category: null,
		isFavorite: false,
		phones: [],
	};
};
export const buildContactFormEditStatus = (): ContactFormEditStatus => {
	return {
		name: false,
		email: false,
		category: false,
		phones: [],
	};
};
export const buildActiveFormEditStatus = (): ContactFormEditStatus => {
	return {
		name: true,
		email: true,
		category: true,
		phones: [],
	};
};
export const buildContactFormErrors = (): ContactFormErrors => {
	return {
		name: null,
		email: null,
		category: null,
		phones: [],
		phoneGroup: null,
		persistence: null,
	};
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

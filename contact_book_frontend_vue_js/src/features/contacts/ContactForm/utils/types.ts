import type { ContactCategory, ContactPhone } from "@/types/domain";

export type ContactFormEditStatus = {
	name: boolean;
	email: boolean;
	category: boolean;
	phones: boolean[];
};
export type ContactFormType = {
	name: string;
	email: string;
	category: ContactCategory | null;
	isFavorite: boolean;
	phones: ContactPhone[];
};
export type ContactFormErrors = {
	name: string | null;
	email: string | null;
	category: string | null;
	phones: (string | null)[];
	phoneGroup: string | null;
	persistence: string | null;
};
export type ContactStateForm = {
	form: ContactFormType;
	formEditStatus: ContactFormEditStatus;
	errors: ContactFormErrors;
};

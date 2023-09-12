export type ContactCategory = {
	id: string;
	name: string;
};
export type ContactPhone = {
	id?: number;
	number: string;
};
export type Contact = {
	id: number;
	name: string;
	email: string;
	category: ContactCategory;
	isFavorite: boolean;
	phones: ContactPhone[];
};
export type ContactGroupItem = {
	letter: string;
	contacts: Contact[];
};

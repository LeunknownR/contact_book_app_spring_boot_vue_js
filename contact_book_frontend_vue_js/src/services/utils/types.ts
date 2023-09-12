import type { ContactPhone } from "@/types/domain";

export type ResponseApi<D = null> = {
	status: string;
	data: D;
};
export type AddContactPayload = {
	name: string;
	email: string;
	isFavorite: boolean;
	categoryId: string;
	phonesToAdd: string[];
};
export type EditContactPayload = {
	id: number;
	name: string;
	email: string;
	isFavorite: boolean;
	categoryId: string;
	phonesToEdit: ContactPhone[];
};

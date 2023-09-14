import type { Contact } from "@/types/domain";

export const MOCK_CONTACT: Contact = {
	id: 1,
	name: "Manuel",
	email: "mrivera@gmail.com",
	category: {
		id: "AMG",
		name: "Amigos",
	},
	isFavorite: true,
	phones: [{ number: "900123600" }],
};

import type { Contact } from "@/types/domain";

export const createMockContact = (): Contact => {
	return {
		id: 1,
		name: "Manuel Rivera",
		email: "mrivera@gmail.com",
		category: {
			id: "AMG",
			name: "Amigos",
		},
		isFavorite: true,
		phones: [{ number: "900123600" }],
	};
};
export const createMockContactWith3Phones = (): Contact => {
	return {
		id: 2,
		name: "Gabriela Ruiz",
		email: "gabrielar@gmail.com",
		category: {
			id: "TRB",
			name: "Trabajo",
		},
		isFavorite: true,
		phones: [
			{ number: "900123600" },
			{ number: "900423600" },
			{ number: "910823610" },
		],
	};
};

import api from "./config/api";
import type { Contact, ResponseAPI } from "./types";

class ContactService {
	private readonly scheme: string;
	constructor(scheme: string) {
		this.scheme = scheme;
	}
	private endpoint(path?: string) {
		return `${this.scheme}${path || ""}`;
	}
	async getContacts(): Promise<ResponseAPI<Contact[]>> {
		const response = await api.get(
			this.endpoint(`/all?contactName=&contactPhoneNumber=`)
		);
		return response.data;
	}
}

const contactService = new ContactService("/contacts");
export default contactService;

import api from "./config/api";
import type {
	AddContactPayload,
	EditContactPayload,
	ResponseApi,
} from "./utils/types";
import type { Contact } from "@/types/domain";
import { sleep } from "@/utils/helpers";

class ContactService {
	private readonly scheme: string;
	constructor(scheme: string) {
		this.scheme = scheme;
	}
	private endpoint(path?: string) {
		return `${this.scheme}${path || ""}`;
	}
	async getContacts(): Promise<ResponseApi<Contact[]>> {
		await sleep(300);
		const response = await api.get(
			this.endpoint(`/all?contactName=&contactPhoneNumber=`)
		);
		return response.data;
	}
	async addContact(payload: AddContactPayload): Promise<ResponseApi> {
		await sleep(300);
		const response = await api.post(this.endpoint("/add"), payload);
		return response.data;
	}
	async editContact(
		payload: EditContactPayload
	): Promise<ResponseApi<Contact | null>> {
		await sleep(300);
		const response = await api.put(this.endpoint("/edit"), payload);
		return response.data;
	}
	async removeContact(contactId: number): Promise<void> {
		await sleep(300);
		const response = await api.delete(
			this.endpoint(`/remove/${contactId}`)
		);
		return response.data;
	}
	async getFavoriteContacts(): Promise<ResponseApi<Contact[]>> {
		await sleep(300);
		const response = await api.get(this.endpoint("/favorites"));
		return response.data;
	}
	async toggleFavoriteContact(contactId: number): Promise<void> {
		const response = await api.patch(
			this.endpoint(`/favorites/toggle/${contactId}`)
		);
		return response.data;
	}
}

const contactService = new ContactService("/contacts");
export default contactService;

import type { ContactCategory } from "@/types/domain";
import api from "./config/api";
import type { ResponseApi } from "./utils/types";

class ContactCategoryService {
	private readonly scheme: string;
	constructor(scheme: string) {
		this.scheme = scheme;
	}
	private endpoint(path?: string) {
		return `${this.scheme}${path || ""}`;
	}
	async getContactCategories(): Promise<ResponseApi<ContactCategory[]>> {
		const response = await api.get(this.endpoint());
		return response.data;
	}
}

const contactCategoryService = new ContactCategoryService(
	"/contact-categories"
);
export default contactCategoryService;

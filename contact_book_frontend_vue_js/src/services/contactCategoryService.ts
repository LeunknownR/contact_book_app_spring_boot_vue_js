import api from "./config/api";
import type { ContactCategory, ResponseAPI } from "./types";

class ContactCategoryService {
    private readonly scheme: string;
    constructor(scheme: string) {
        this.scheme = scheme;
    }
    private endpoint(path?: string) {
        return `${this.scheme}${path || ""}`;
    }
    async getContactCategories(): Promise<ResponseAPI<ContactCategory[]>> {
        return await api.get(this.endpoint());
    }
}

const contactCategoryService = new ContactCategoryService("/contact-categories");
export default contactCategoryService;
export type ResponseAPI<D> = {
    status: string;
    data: D;
};
export type ContactCategory = {
    id: string;
    name: string;
};
export type ContactPhone = {
    id: number;
    number: string;
};
export type Contact = {
    id: number;
    name: string;
    email: string;
    categoryId: number;
    isFavorite: boolean;
    phones: ContactPhone[];
};
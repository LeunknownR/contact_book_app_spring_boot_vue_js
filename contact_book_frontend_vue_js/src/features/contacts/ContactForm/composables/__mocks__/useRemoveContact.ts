import type {
	ContactCrudAction,
	RemoveContactComposableParams,
} from "../types";

const useRemoveContact = (
	_: RemoveContactComposableParams
): ContactCrudAction => {
	const removeContact: ContactCrudAction = async () => {};
	return removeContact;
};

export default useRemoveContact;

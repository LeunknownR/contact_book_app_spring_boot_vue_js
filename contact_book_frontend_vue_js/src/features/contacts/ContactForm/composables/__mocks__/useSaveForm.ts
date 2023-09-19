import type { ContactCrudAction, SaveFormComposableParams } from "../types";

const useSaveForm = (_: SaveFormComposableParams): ContactCrudAction => {
	const saveForm = async (): Promise<void> => {};
	return saveForm;
};

export default useSaveForm;

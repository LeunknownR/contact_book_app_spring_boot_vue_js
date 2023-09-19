import FavoriteSwitch from "@/features/contacts/ContactCard/FavoriteSwitch.vue";
import { DOMWrapper, mount } from "@vue/test-utils";
import { beforeEach, describe, expect, it } from "vitest";
import type { VueTestComponent } from "./utils/types";

type ElementIcon = DOMWrapper<Element>;
//#region Constants
const HIDDEN_ICON_CLASS: string = "hidden-icon";
const FAVORITE_ICON_QUERY: string = "[data-test='favorite-icon']";
const NON_FAVORITE_ICON_QUERY: string = "[data-test='non-favorite-icon']";
//#endregion
describe("FavoriteSwitch.vue", () => {
	let wrapper: VueTestComponent<typeof FavoriteSwitch>;
	//#region Functions
	const findIcons = (): [ElementIcon, ElementIcon] => {
		const favoriteIcon = wrapper.find(FAVORITE_ICON_QUERY);
		const nonFavoriteIcon = wrapper.find(NON_FAVORITE_ICON_QUERY);
		return [favoriteIcon, nonFavoriteIcon];
	};
	//#endregion
	beforeEach(() => {
		wrapper = mount(FavoriteSwitch, {
			props: {
				isFavorite: true,
			},
		});
	});
	it("should be visible only favorite icon", () => {
		const [favoriteIcon, nonFavoriteIcon] = findIcons();
		expect(favoriteIcon.classes(HIDDEN_ICON_CLASS)).toBe(false);
		expect(nonFavoriteIcon.classes(HIDDEN_ICON_CLASS)).toBe(true);
	});
	it("should be visible only non-favorite icon", async () => {
		await wrapper.setProps({
			...wrapper.props(),
			isFavorite: false,
		});
		const [favoriteIcon, nonFavoriteIcon] = findIcons();
		expect(favoriteIcon.classes(HIDDEN_ICON_CLASS)).toBe(true);
		expect(nonFavoriteIcon.classes(HIDDEN_ICON_CLASS)).toBe(false);
	});
});

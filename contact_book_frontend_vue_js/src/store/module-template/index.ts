import type { Module } from "vuex";

import type { ExampleStateInterface } from "./state";
import state from "./state";
import actions from "./actions";
import getters from "./getters";
import mutations from "./mutations";

export interface StateInterface {
	// Define your own store structure, using submodules if needed
	// example: ExampleStateInterface;
	// Declared as unknown to avoid linting issue. Best to strongly type as per the line above.
	example: ExampleStateInterface;
}

const exampleModule: Module<ExampleStateInterface, StateInterface> = {
	namespaced: true,
	actions,
	getters,
	mutations,
	state,
};

export default exampleModule;

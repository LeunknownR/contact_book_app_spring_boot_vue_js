<template>
	<ContactViewLayout
		title="Contactos"
		:is-fetching-contacts="isFetchingContacts"
		:show-form="formInitializer.show.value"
		@start-adding-form="formInitializer.startAddingForm"
	>
		<template v-slot:filters>
			<ContactFilters
				:filters="state.contactFilters"
				@fetch-contacts="fetchContacts"
			/>
		</template>
		<template v-slot:contact-group>
			<ContactGroup
				v-if="state.contactGroup.length > 0"
				v-for="(contactGroupItem, idx) in state.contactGroup"
				:key="idx"
				:contactGroupItem="contactGroupItem"
				:contact-selected="state.contactSelected"
				:hidden-favorite="false"
				@selectContact="formInitializer.selectContact"
			/>
			<NoContacts v-else />
		</template>
		<template v-slot:form>
			<ContactForm
				:contact-selected="state.contactSelected"
				:fetch-contacts-action-type="FETCH_CONTACTS_ACTION_TYPE"
				:form-initializer="formInitializer"
				@fetch-contacts="fetchContacts"
			/>
		</template>
	</ContactViewLayout>
</template>
<script setup lang="ts">
	import ContactGroup from "@/features/contacts/ContactGroup/ContactGroup.vue";
	import ContactViewLayout from "@/features/contacts/ContactViewLayout.vue";
	import ContactForm from "@/features/contacts/ContactForm/ContactForm.vue";
	import ActionTypes from "@/store/contact-store/action-types";
	import useContactView from "../composables/useContactView";
	import ContactFilters from "@/features/contacts/ContactFilters/ContactFilters.vue";
	import NoContacts from "@/features/contacts/NoContacts.vue";

	const FETCH_CONTACTS_ACTION_TYPE: ActionTypes = ActionTypes.FetchContacts;
	const { state, isFetchingContacts, fetchContacts, formInitializer } =
		useContactView(FETCH_CONTACTS_ACTION_TYPE);
</script>

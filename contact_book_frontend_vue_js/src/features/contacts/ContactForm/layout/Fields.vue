<template>
	<section class="flex gap-6 flex-wrap">
		<div class="flex flex-col items-center gap-3">
			<InitialName
				:initial="firstLetter"
				:is-big="true"
			/>
			<FavoriteField v-model="form.isFavorite" />
		</div>
		<div class="flex flex-col gap-3 justify-between">
			<NameField
				v-model:edit-status="formEditStatus.name"
				v-model:value.trim="form.name"
				:error="errors.name"
				:is-edit="isEdit"
				@clear-error="errors.name = null"
			/>
			<EmailField
				v-model:edit-status="formEditStatus.email"
				v-model:value.trim="form.email"
				:is-edit="isEdit"
				:error="errors.email"
				@clear-error="errors.email = null"
			/>
			<CategoryField
				v-model:edit-status="formEditStatus.category"
				v-model:value="form.category"
				:is-edit="isEdit"
				:error="errors.category"
				@clear-error="errors.category = null"
			/>
		</div>
		<div class="flex flex-col gap-3 justify-between">
			<ErrorMessage :message="errors.phoneGroup">
				<PhoneGroupField
					:phones="form.phones"
					:edit-status="formEditStatus.phones"
					:is-edit="isEdit"
					:errors="errors.phones"
				/>
			</ErrorMessage>
		</div>
	</section>
</template>
<script setup lang="ts">
	import { computed } from "vue";
	import InitialName from "@/components/InitialName.vue";
	import FavoriteField from "../fields/FavoriteField.vue";
	import type { Contact } from "@/types/domain";
	import PhoneGroupField from "../fields/PhoneGroupField.vue";
	import NameField from "../fields/NameField.vue";
	import EmailField from "../fields/EmailField.vue";
	import type {
		ContactFormType,
		ContactFormErrors,
		ContactFormEditStatus,
	} from "../utils/types";
	import CategoryField from "../fields/CategoryField.vue";
	import ErrorMessage from "@/components/ErrorMessage.vue";

	const props = defineProps<{
		selectedContact: Contact | null;
		form: ContactFormType;
		errors: ContactFormErrors;
		formEditStatus: ContactFormEditStatus;
	}>();
	const isEdit = computed<boolean>(() => props.selectedContact !== null);
	const firstLetter = computed<string>(() => {
		const { name } = props.form;
		return name ? name[0] : "-";
	});
</script>

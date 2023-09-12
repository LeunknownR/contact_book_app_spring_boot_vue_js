<template>
	<div class="flex flex-col gap-2">
		<Label
			label="Números de celular"
			icon="mdi:phone-log"
		/>
		<div class="flex flex-col gap-3 w-48">
			<PhoneField
				v-for="(phone, i) in phones"
				:key="i"
				:edit-status="editStatus[i]"
				:value="phone"
				:is-edit="isEdit"
				:error="errors[i]"
				@remove="removePhoneNumber"
				@update:value="value => changePhoneNumber(i, value)"
				@update:edit-status="toggleEditStatus(i)"
				@clear-error="errors[i] = null"
			/>
		</div>
		<button
			v-show="!reachedCapacity"
			type="button"
			class="grid place-items-center mt-1 self-center"
			@click="addPhoneNumber"
		>
			<Icon
				class="text-xl text-indigo-200 duration-300 hover:text-indigo-300"
				icon="icon-park-solid:add"
			/>
		</button>
	</div>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import Label from "@/components/Fields/Label.vue";
	import { computed } from "vue";
	import PhoneField from "./PhoneField.vue";
	import type { ContactPhone } from "@/types/domain";

	const props = defineProps<{
		phones: ContactPhone[];
		editStatus: boolean[];
		isEdit: boolean;
		errors: (string | null)[];
	}>();
	const addPhoneNumber = (): void => {
		props.phones.push({
			number: "",
		});
		props.editStatus.push(true);
		props.errors.push(null);
	};
	const removePhoneNumber = (phone: ContactPhone): void => {
		const phoneIdx = props.phones.findIndex(
			({ number }) => number === phone.number
		);
		props.phones.splice(phoneIdx, 1);
		props.editStatus.splice(phoneIdx, 1);
		props.errors.splice(phoneIdx, 1);
	};
	const toggleEditStatus = (i: number): void => {
		props.editStatus[i] = !props.editStatus[i];
	};
	const changePhoneNumber = (i: number, value: string) => {
		props.phones[i].number = value.trim();
	};
	const reachedCapacity = computed<boolean>(() => props.phones.length >= 3);
</script>

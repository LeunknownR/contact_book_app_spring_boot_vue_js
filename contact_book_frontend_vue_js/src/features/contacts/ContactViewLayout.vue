<template>
	<section
		class="flex flex-col justify-between items-center gap-10 px-5 flex-grow md:gap-14 md:w-11/12 lg:w-10/12 md:flex-row"
	>
		<section
			class="flex flex-col gap-5 w-full self-stretch flex-grow md:w-3/12"
		>
			<header class="flex flex-col gap-2">
				<section class="flex justify-between">
					<Title :title="props.title" />
					<button
						@click="emits('startAddingForm')"
						type="button"
						class="text-3xl text-indigo-400 pr-4 duration-300 hover:text-indigo-300"
					>
						<Icon icon="icon-park-solid:add" />
					</button>
				</section>
				<slot name="filters"></slot>
			</header>
			<aside
				class="relative flex flex-col gap-3.5 max-h-[50vh] max-w-3xl overflow-y-auto flex-grow"
			>
				<ContactGroupSkelleton v-if="isFetchingContacts" />
				<slot
					v-else
					name="contact-group"
				></slot>
			</aside>
		</section>
		<slot
			v-if="props.showForm"
			name="form"
		></slot>
		<UnselectedContact v-else />
	</section>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import Title from "@/components/Title.vue";
	import UnselectedContact from "@/components/UnselectedContact.vue";
	import ContactGroupSkelleton from "@/features/contacts/ContactGroupSkelleton/ContactGroupSkelleton.vue";

	const props = defineProps<{
		title: string;
		isFetchingContacts: boolean;
		showForm: boolean;
	}>();
	const emits = defineEmits<{
		(e: "startAddingForm"): void;
	}>();
</script>

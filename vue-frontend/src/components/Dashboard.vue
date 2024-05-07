<template>
  <div>
    <h1>Kanban</h1>
    <v-switch
      v-model="mobile"
      color="primary"
      label="Switch mobile ver"
      hide-details
    ></v-switch>
    <template v-if="mobile">
      <DashboardMobile />
    </template>
    <template v-else>
      <DashboardDesktop />
    </template>
    <AddKanban />
  </div>
</template>

<script setup>
import { ref } from "vue";
import DashboardMobile from "./DashboardMobile.vue";
import DashboardDesktop from "./DashboardDesktop.vue";
import AddKanban from "./AddKanban.vue";

const mobile = ref(false);
</script>

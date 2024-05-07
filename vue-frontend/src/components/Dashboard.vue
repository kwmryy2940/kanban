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
      <DashboardDesktop :isAddTicket="isAddTicket"/>
    </template>
    <AddKanban @add-data="addData"/>
  </div>
</template>

<script setup>
import { ref } from "vue";
import DashboardMobile from "./DashboardMobile.vue";
import DashboardDesktop from "./DashboardDesktop.vue";
import AddKanban from "./AddKanban.vue";

const mobile = ref(false);
const isAddTicket=ref(false);

function addData(addData){
  isAddTicket.value=addData;
}

</script>

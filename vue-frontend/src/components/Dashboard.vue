<template>
  <div>
    <h1 class="text-center">カンバン式ToDoアプリ</h1>
    <v-switch
      v-model="mobile"
      color="primary"
      label="Switch mobile ver"
      hide-details
      absolute
    ></v-switch>
    <template v-if="mobile">
      <DashboardMobile
        :isAddTicket="isAddTicket"
        @add-data-completed-mobile="onAddDataCompletedMobile"
      />
    </template>
    <template v-else>
      <DashboardDesktop
        :isAddTicket="isAddTicket"
        @add-data-completed="onAddDataCompleted"
      />
    </template>
    <AddKanban
      :isAddCompletedTicketByMobile="isAddCompletedTicketByMobile"
      :isAddCompletedTicket="isAddCompletedTicket"
      @add-data="onAddData"
    />
  </div>
</template>

<script setup>
import { ref } from "vue";
import DashboardMobile from "./DashboardMobile.vue";
import DashboardDesktop from "./DashboardDesktop.vue";
import AddKanban from "./AddKanban.vue";

const mobile = ref(false);
const isAddTicket = ref(false);
const isAddCompletedTicket = ref(false);
const isAddCompletedTicketByMobile = ref(false);

function onAddData(addData) {
  isAddTicket.value = addData;
}
function onAddDataCompleted(addDataCompleted) {
  isAddCompletedTicket.value = addDataCompleted;
}
function onAddDataCompletedMobile(addDataCompletedByMobile) {
  isAddCompletedTicketByMobile.value = addDataCompletedByMobile;
}
</script>

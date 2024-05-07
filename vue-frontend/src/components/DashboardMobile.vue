<template>
  <div>
    <v-container fluid class="d-flex align-center justify-center">
      <v-sheet class="pa-1 ma-1 border">
        <v-tabs v-model="tab" bg-color="primary">
          <v-tab value="todos">ToDos</v-tab>
          <v-tab value="inProgress">InProgress</v-tab>
          <v-tab value="completed">Completed</v-tab>
        </v-tabs>

        <v-tabs-window v-model="tab">
          <v-tabs-window-item value="todos" item-key="id">
            <div v-for="item in tasks.todos">
            <v-list>
              <v-card>
                <v-card-item :title="item.ticketTitle"></v-card-item>
                <v-list-item>
                  <v-list-item-subtitle
                    >Task-{{ item.id }}</v-list-item-subtitle
                  >
                  {{ item.category.categoryName }}
                </v-list-item>
                <v-card-text>
                  {{ item.ticketDetail }}
                </v-card-text>
              </v-card>
            </v-list>
          </div>
          </v-tabs-window-item>
          <v-tabs-window-item value="inProgress">
            <div v-for="item in tasks.inProgress">
            <v-list>
              <v-card>
                <v-card-item :title="item.ticketTitle"></v-card-item>
                <v-list-item>
                  <v-list-item-subtitle
                    >Task-{{ item.id }}</v-list-item-subtitle
                  >
                  {{ item.category.categoryName }}
                </v-list-item>
                <v-card-text>
                  {{ item.ticketDetail }}
                </v-card-text>
              </v-card>
            </v-list>
          </div>
          </v-tabs-window-item>
          <v-tabs-window-item value="completed">
            <div v-for="item in tasks.completed">
            <v-list>
              <v-card>
                <v-card-item :title="item.ticketTitle"></v-card-item>
                <v-list-item>
                  <v-list-item-subtitle
                    >Task-{{ item.id }}</v-list-item-subtitle
                  >
                  {{ item.category.categoryName }}
                </v-list-item>
                <v-card-text>
                  {{ item.ticketDetail }}
                </v-card-text>
              </v-card>
            </v-list>
          </div>
          </v-tabs-window-item>
        </v-tabs-window>
      </v-sheet>
    </v-container>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
const tab = ref(null);
const tasks = ref({
  todos: [
    {
      id: 1,
      userId: 1,
      categoryId: 1,
      status: 0,
      ticketTitle: "お買い物",
      ticketDetail: "ネギ、醤油、みそを購入する",
      createdAt: "2024-05-05T16:43:14",
      updatedAt: "2024-05-05T16:43:14",
      user: {
        id: 1,
        userName: "test_user",
        userEmail: null,
        password: null,
        adminFlg: 0,
        createdAt: "2024-05-05T16:39:37",
        updatedAt: "2024-05-05T16:39:37",
      },
      category: {
        id: 1,
        categoryName: "仕事",
        createdAt: "2024-05-05T16:37:18",
        updatedAt: "2024-05-05T16:37:18",
      },
    },
    {
      id: 3,
      userId: 1,
      categoryId: 1,
      status: null,
      ticketTitle: "create_test",
      ticketDetail: "create_test_detail",
      createdAt: "2024-05-05T22:35:00",
      updatedAt: "2024-05-05T22:35:00",
      user: {
        id: 1,
        userName: "test_user",
        userEmail: null,
        password: null,
        adminFlg: 0,
        createdAt: "2024-05-05T16:39:37",
        updatedAt: "2024-05-05T16:39:37",
      },
      category: {
        id: 1,
        categoryName: "仕事",
        createdAt: "2024-05-05T16:37:18",
        updatedAt: "2024-05-05T16:37:18",
      },
    },
    {
      id: 4,
      userId: 1,
      categoryId: 2,
      status: 0,
      ticketTitle: "create_test",
      ticketDetail: "create_test_detail",
      createdAt: "2024-05-05T22:36:54",
      updatedAt: "2024-05-05T22:36:54",
      user: {
        id: 1,
        userName: "test_user",
        userEmail: null,
        password: null,
        adminFlg: 0,
        createdAt: "2024-05-05T16:39:37",
        updatedAt: "2024-05-05T16:39:37",
      },
      category: {
        id: 2,
        categoryName: "趣味",
        createdAt: "2024-05-05T16:37:18",
        updatedAt: "2024-05-05T16:37:18",
      },
    },
  ],
  inProgress: [],
  completed: [],
});

async function fetchData() {
  const response = await fetch("/api/v1/td_ticket");
  const data = await response.json();
  return data;
}

onMounted(async () => {
  // const data = await fetchData();
  // data.forEach((item) => {
  //   if (item.status === 0) tasks.value.todos.push(item);
  //   else if (item.status === 1) tasks.value.inProgress.push(item);
  //   else tasks.value.completed.push(item);
  // });
});
</script>

<style scoped>
.dragArea {
  padding: 10px;
  /* background-color: aqua; */
}
.handle {
  cursor: pointer;
}
</style>

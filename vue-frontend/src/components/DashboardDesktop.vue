<template>
  <div>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" sm="4">
          <v-sheet class="pa-1 ma-1 border">
            <h4>ToDos</h4>
            <draggable
              v-model="tasks.todos"
              group="my-group"
              item-key="id"
              class="dragArea"
              animation="200"
              handle=".handle"
            >
              <template #item="{ element }">
                <v-list>
                  <v-card class="handle">
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      {{ element.category.categoryName }}
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                    </v-card-text>
                    <v-btn
                      icon="mdi-pencil"
                      size="x-small"
                      @click="editTicketData(element.id)"
                    ></v-btn>
                    <v-btn
                      icon="mdi-delete"
                      size="x-small"
                      @click="deleteTicketData(element.id)"
                    ></v-btn>
                  </v-card>
                </v-list>
              </template>
            </draggable>
          </v-sheet>
        </v-col>
        <v-col cols="12" sm="4">
          <v-sheet class="pa-1 ma-1 border">
            <h4>InProgress</h4>
            <draggable
              v-model="tasks.inProgress"
              group="my-group"
              item-key="id"
              class="dragArea"
              animation="200"
              handle=".handle"
            >
              <template #item="{ element }">
                <v-list>
                  <v-card class="handle">
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      {{ element.category.categoryName }}
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                      <v-btn
                        icon="mdi-pencil"
                        size="x-small"
                        @click="editTicketData(element.id)"
                      ></v-btn>
                      <v-btn
                        icon="mdi-delete"
                        size="x-small"
                        @click="deleteTicketData(element.id)"
                      ></v-btn>
                    </v-card-text>
                  </v-card>
                </v-list>
              </template>
            </draggable>
          </v-sheet>
        </v-col>
        <v-col cols="12" sm="4">
          <v-sheet class="pa-1 ma-1 border">
            <h4>Complete</h4>
            <draggable
              v-model="tasks.completed"
              group="my-group"
              item-key="id"
              class="dragArea"
              animation="200"
              handle=".handle"
            >
              <template #item="{ element }">
                <v-list>
                  <v-card class="handle">
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      {{ element.category.categoryName }}
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                      <v-btn
                        icon="mdi-pencil"
                        size="x-small"
                        @click="editTicketData(element.id)"
                      ></v-btn>
                      <v-btn
                        icon="mdi-delete"
                        size="x-small"
                        @click="deleteTicketData(element.id)"
                      ></v-btn>
                    </v-card-text>
                  </v-card>
                </v-list>
              </template>
            </draggable>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import draggable from "vuedraggable";

// const tasks = ref({
//   todos: [],
//   inProgress: [],
//   completed: [],
// });

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

async function editTicketData(ticketId) {
  console.log("id:", ticketId);
}

async function deleteTicketData(ticketId) {
  console.log("id:", ticketId);
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

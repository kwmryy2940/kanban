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
              class="dragArea todos"
              animation="200"
              handle=".handle"
              @add="addItem"
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
                      @click="deleteTicketData(element.id, element.status)"
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
              class="dragArea inprogress"
              animation="200"
              handle=".handle"
              @add="addItem"
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
                        @click="deleteTicketData(element.id, element.status)"
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
            <h4>Completed</h4>
            <draggable
              v-model="tasks.completed"
              group="my-group"
              item-key="id"
              class="dragArea completed"
              animation="200"
              handle=".handle"
              @add="addItem"
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
                        @click="deleteTicketData(element.id, element.status)"
                      ></v-btn>
                    </v-card-text>
                  </v-card>
                </v-list>
              </template>
            </draggable>
          </v-sheet>
        </v-col>
      </v-row>
      <v-snackbar v-model="errorSnackBar" :timeout="5000" color="error">
        <v-icon class="me-2" size="small"> mdi-alert-circle-outline </v-icon>
        {{ errorSnackBarText }}
        <template v-slot:actions>
          <v-btn color="white" variant="text" @click="errorSnackBar = false">
            閉じる
          </v-btn>
        </template>
      </v-snackbar>
    </v-container>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import draggable from "vuedraggable";

// interface User {
//   id: Number,
//   userName: String,
//   userEmail: String,
//   password: String,
//   adminFlg: Number,
//   createdAt: Date,
//   updatedAt: Date,
// };
// interface Category {
//   id: Number,
//   categoryName: String,
//   createdAt: Date,
//   updatedAt: Date,
// };

// interface Ticket {
//   id: Number,
//   userId: Number,
//   categoryId: Number,
//   status: Number,
//   ticketTitle: String,
//   ticketDetail: String,
//   createdAt: Date,
//   updatedAt: Date,
//   user:{user},
//   category:{category}
// };

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
      status: 0,
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

// interface Tasks{
//   todos:Ticket[];
//   inProgress:Ticket[];
//   completed:Ticket[];
// }

// const tasks=ref<Tasks>({
//   todos:[],
//   inProgress:[],
//   completed:[]

// });

// const tasks = ref({
//   todos: [],
//   inProgress: [],
//   completed: [],
// });
const editedItem = ref({
  userId: 0,
  categoryId: 0,
  status: 0,
  ticketTitle: "",
  ticketDetail: "",
});

const errorSnackBar = ref(false); // エラー通知バーの表示を管理する変数
const errorSnackBarText = ref(""); // エラー通知バーの通知内容

async function fetchData() {
  const response = await fetch("/api/v1/td_ticket");
  const data = await response.json();
  return data;
}

async function updateData(ticketId) {
  const body = { ...editedItem.value };

  const response = await fetch(`/api/v1/td_ticket/${ticketId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  const data = await response.json();
  return data;
}

async function deleteData(ticketId) {
  const response = await fetch(`/api/v1/td_ticket/${ticketId}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  });
  const data = await response.json();
  return data;
}

async function editTicketData(ticket) {
  console.log("ticket:", ticket);
}

async function deleteTicketData(ticketId, status) {
  console.log("id:", ticketId);
  console.log("status:", status);

  try {
    // const response = await deleteData(ticketId);
    // // 200 OK以外のレスポンスの場合はエラーをスロー
    // if (!response.ok) {
    //   // エラー時にsnackbarの状態を更新
    //   errorSnackBar.value = true;
    //   errorSnackBarText.value = "ユーザー情報の削除に失敗しました。";
    //   throw new Error(`HTTP error! status: ${response.status}`);
    // }

    // リストから該当ユーザー情報を削除
    if (status === 0) {
      const index = tasks.value.todos.findIndex(
        (ticket) => ticket.id === ticketId
      );
      tasks.value.todos.splice(index, 1);
      console.log("tasks.value.todos:", tasks.value.todos);
    } else if (status === 1) {
      const index = tasks.value.inProgress.findIndex(
        (ticket) => ticket.id === ticketId
      );
      console.log("index:", index);
      tasks.value.inProgress.splice(index, 1);
    } else {
      const index = tasks.value.completed.findIndex(
        (ticket) => ticket.id === ticketId
      );
      console.log("index:", index);
      tasks.value.completed.splice(index, 1);
    }
  } catch (error) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "ユーザー情報の削除に失敗しました。";
    throw error;
  }
}

function addItem(event) {
  const className = event.to.className.split(" ")[1];
  const element = event.item.__draggable_context.element;

  editedItem.value.userId = element.userId;
  editedItem.value.categoryId = element.categoryId;
  editedItem.value.ticketTitle = element.ticketTitle;
  editedItem.value.ticketDetail = element.ticketDetail;

  if (className === "todos") {
    editedItem.value.status = 0;
  } else if (className === "inprogress") {
    editedItem.value.status = 1;
  } else {
    editedItem.value.status = 2;
  }
  updateData(element.id);
}

// onMounted(async () => {
//   const data = await fetchData();
//   data.forEach((item) => {
//     if (item.status === 0) tasks.value.todos.push(item);
//     else if (item.status === 1) tasks.value.inProgress.push(item);
//     else tasks.value.completed.push(item);
//   });
// });
</script>

<style scoped>
.dragArea {
  padding: 10px;
}
.handle {
  cursor: pointer;
}
</style>

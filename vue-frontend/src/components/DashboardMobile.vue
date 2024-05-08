<template>
  <div>
    <v-container fluid class="d-flex align-center justify-center">
      <v-sheet class="pa-1 ma-1 border" width="95vw">
        <v-tabs v-model="tab" align-tabs="center" color="secondary">
          <v-tab value="todos" class="title-todos">未着手</v-tab>
          <v-tab value="inProgress" class="title-inprogress">処理中</v-tab>
          <v-tab value="completed" class="title-completed">完了</v-tab>
        </v-tabs>

        <v-tabs-window v-model="tab">
          <v-tabs-window-item value="todos" item-key="id">
            <div v-for="item in tasks.todos">
              <v-list>
                <v-card elevation="0" variant="outlined">
                  <v-card-item :title="item.ticketTitle">{{
                    item.ticketDetail
                  }}</v-card-item>
                  <v-card-subtitle>
                    <template v-if="item.category != null">
                      Task-{{ item.id }}
                      {{ item.category.categoryName }}
                    </template>
                    <template> Task-{{ item.id }} </template>
                  </v-card-subtitle>
                  <v-btn icon="mdi-pencil" size="x-small" @click=""></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click=""></v-btn>
                </v-card>
              </v-list>
            </div>
          </v-tabs-window-item>
          <v-tabs-window-item value="inProgress">
            <div v-for="item in tasks.inProgress">
              <v-list>
                <v-card elevation="0" variant="outlined">
                  <v-card-item :title="item.ticketTitle">{{
                    item.ticketDetail
                  }}</v-card-item>
                  <v-card-subtitle>
                    <template v-if="item.category != null">
                      Task-{{ item.id }}
                      {{ item.category.categoryName }}
                    </template>
                    <template> Task-{{ item.id }} </template>
                  </v-card-subtitle>
                  <v-btn icon="mdi-pencil" size="x-small" @click=""></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click=""></v-btn>
                </v-card>
              </v-list>
            </div>
          </v-tabs-window-item>
          <v-tabs-window-item value="completed">
            <div v-for="item in tasks.completed">
              <v-list>
                <v-card elevation="0" variant="outlined">
                  <v-card-item :title="item.ticketTitle">{{
                    item.ticketDetail
                  }}</v-card-item>
                  <v-card-subtitle>
                    <template v-if="item.category != null">
                      Task-{{ item.id }}
                      {{ item.category.categoryName }}
                    </template>
                    <template> Task-{{ item.id }} </template>
                  </v-card-subtitle>
                  <v-btn icon="mdi-pencil" size="x-small" @click=""></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click=""></v-btn>
                </v-card>
              </v-list>
            </div>
          </v-tabs-window-item>
        </v-tabs-window>
      </v-sheet>
      <v-snackbar v-model="noticeSnackBar" :timeout="5000" color="success">
        <v-icon class="me-2" size="small"> mdi-check-circle-outline </v-icon>
        {{ noticeSnackBarText }}
        <template v-slot:actions>
          <v-btn color="white" variant="text" @click="noticeSnackBar = false">
            閉じる
          </v-btn>
        </template>
      </v-snackbar>
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
import { onMounted, ref, watch } from "vue";
const tab = ref(null);
// const tasks = ref({
//   todos: [
//     {
//       id: 1,
//       userId: 1,
//       categoryId: 1,
//       status: 0,
//       ticketTitle: "お買い物",
//       ticketDetail: "ネギ、醤油、みそを購入する",
//       createdAt: "2024-05-05T16:43:14",
//       updatedAt: "2024-05-05T16:43:14",
//       user: {
//         id: 1,
//         userName: "test_user",
//         userEmail: null,
//         password: null,
//         adminFlg: 0,
//         createdAt: "2024-05-05T16:39:37",
//         updatedAt: "2024-05-05T16:39:37",
//       },
//       category: {
//         id: 1,
//         categoryName: "仕事",
//         createdAt: "2024-05-05T16:37:18",
//         updatedAt: "2024-05-05T16:37:18",
//       },
//     },
//     {
//       id: 3,
//       userId: 1,
//       categoryId: 1,
//       status: null,
//       ticketTitle: "create_test",
//       ticketDetail: "create_test_detail",
//       createdAt: "2024-05-05T22:35:00",
//       updatedAt: "2024-05-05T22:35:00",
//       user: {
//         id: 1,
//         userName: "test_user",
//         userEmail: null,
//         password: null,
//         adminFlg: 0,
//         createdAt: "2024-05-05T16:39:37",
//         updatedAt: "2024-05-05T16:39:37",
//       },
//       category: {
//         id: 1,
//         categoryName: "仕事",
//         createdAt: "2024-05-05T16:37:18",
//         updatedAt: "2024-05-05T16:37:18",
//       },
//     },
//     {
//       id: 4,
//       userId: 1,
//       categoryId: 2,
//       status: 0,
//       ticketTitle: "create_test",
//       ticketDetail: "create_test_detail",
//       createdAt: "2024-05-05T22:36:54",
//       updatedAt: "2024-05-05T22:36:54",
//       user: {
//         id: 1,
//         userName: "test_user",
//         userEmail: null,
//         password: null,
//         adminFlg: 0,
//         createdAt: "2024-05-05T16:39:37",
//         updatedAt: "2024-05-05T16:39:37",
//       },
//       category: {
//         id: 2,
//         categoryName: "趣味",
//         createdAt: "2024-05-05T16:37:18",
//         updatedAt: "2024-05-05T16:37:18",
//       },
//     },
//   ],
//   inProgress: [],
//   completed: [],
// });
const emit = defineEmits(['add-data-completed-mobile']);

const props = defineProps({
  isAddTicket: Boolean,
});
// props.isAddTicketを返すgetter関数を作成
const isAddTicket = () => props.isAddTicket;

const tasks = ref({
  todos: [],
  inProgress: [],
  completed: [],
});

const editedItem = ref({
  id: 0,
  userId: 0,
  categoryId: 0,
  status: 0,
  ticketTitle: "",
  ticketDetail: "",
});

const noticeSnackBar = ref(false); // 通知バーの表示を管理する変数
const noticeSnackBarText = ref(false); // 通知バーの通知内容
const errorSnackBar = ref(false); // エラー通知バーの表示を管理する変数
const errorSnackBarText = ref(""); // エラー通知バーの通知内容
const inputForm = ref(null);

const userList = ref([]);
const categoryList = ref([]);

// 各項目の入力に対するバリデーションルール
const validationRules = ref({
  ticketTitle: [
    (value) => !!value || "タイトルは必須です。",
    (value) =>
      value.length <= 128 || "タイトルは128文字以下で入力してください。",
  ],
  ticketDetail: [
    (value) => value.length <= 512 || "内容は512文字以下で入力してください。",
  ],
});

async function validate() {
  const validationResult = await inputForm.value.validate();
  return validationResult.valid;
}


async function fetchData() {
  const response = await fetch("/api/v1/td_ticket");
  const data = await response.json();
  return data;
}
async function loadTicketData() {
  tasks.value.todos = [];
  tasks.value.inProgress = [];
  tasks.value.completed = [];

  try {
    const data = await fetchData();
    data.forEach((item) => {
      if (item.status === 0) tasks.value.todos.push(item);
      else if (item.status === 1) tasks.value.inProgress.push(item);
      else tasks.value.completed.push(item);
    });
  } catch (error) {
    throw error;
  }
}
onMounted(async () => {
  loadTicketData();
});

watch(isAddTicket, (newVal, oldVal) => {
  console.log("isAddTicket.newVal=",newVal);
  console.log("isAddTicket.oldVal=",oldVal);
  if (oldVal === false && newVal === true) {
    loadTicketData();
    noticeSnackBar.value = true;
    noticeSnackBarText.value = "タスクを登録しました。";

    emit('add-data-completed-mobile',true);
  }else{
    emit('add-data-completed-mobile',false);
  }
});
</script>

<style scoped>
.dragArea {
  padding: 10px;
}
.handle {
  cursor: pointer;
}
.title-todos,
.title-inprogress,
.title-completed {
  color: white;
  width: 33%;
}

.title-todos {
  background-color: salmon;
}
.title-inprogress {
  background-color: orange; /* 背景色 */
}
.title-completed {
  background-color: springgreen; /* 背景色 */
}
</style>

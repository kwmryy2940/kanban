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
                  <v-btn icon="mdi-pencil" size="x-small" @click="openEditDiaolog(item)"></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click="deleteTicketData(item.id,item.status)"></v-btn>
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
                  <v-btn icon="mdi-pencil" size="x-small" @click="openEditDiaolog(item)"></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click="deleteTicketData(item.id,item.status)"></v-btn>
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
                  <v-btn icon="mdi-pencil" size="x-small" @click="openEditDiaolog(item)"></v-btn>
                  <v-btn icon="mdi-delete" size="x-small" @click="deleteTicketData(item.id,item.status)"></v-btn>
                </v-card>
              </v-list>
            </div>
          </v-tabs-window-item>
        </v-tabs-window>
      </v-sheet>
      <v-dialog v-model="dialog">
        <v-card>
          <v-card-title>タスク編集</v-card-title>
          <v-card-text>
            <v-container>
              <v-form ref="inputForm">
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      v-model="editedItem.ticketTitle"
                      label="タイトル"
                      variant="outlined"
                      maxlength="128"
                      :rules="validationRules.ticketTitle"
                      ><span style="color: red"> *</span></v-text-field
                    >
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      v-model="editedItem.ticketDetail"
                      label="内容"
                      maxlength="512"
                      variant="outlined"
                      :rules="validationRules.ticketDetail"
                    ></v-textarea>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-select
                      v-model="editedItem.userId"
                      label="担当者"
                      variant="outlined"
                      :items="userList"
                      item-title="userName"
                      item-value="id"
                      return-value
                    >
                    </v-select>
                  </v-col>
                  <v-col>
                    <v-select
                      v-model="editedItem.categoryId"
                      label="カテゴリ"
                      variant="outlined"
                      :items="categoryList"
                      item-title="categoryName"
                      item-value="id"
                      return-value
                    >
                    </v-select>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-select
                    v-model="editedItem.status"
                      label="状態"
                      variant="outlined"
                      :items="statusList"
                      item-title="text"
                      item-value="value"
                      return-value
                    >
                    </v-select>
                  </v-col>
                </v-row>
              </v-form>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-btn color="blue-darken-1" variant="text" @click="dialog = false">
              キャンセル
            </v-btn>
            <v-btn color="blue-darken-1" variant="text" @click="editTicketData">
              登録
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
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
import { computed, onMounted, ref, watch } from "vue";
import { apiUrl } from "../../config.js";

const dialog=ref(false);
const tab = ref(null);

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
const statusList = computed(() => [
  { text: "未着手", value: 0 },
  { text: "処理中", value: 1 },
  { text: "完了", value: 2 },
]);

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

async function fetchUserData() {
  const response = await fetch(apiUrl + "/api/v1/tm_users");
  const data = await response.json();
  return data;
}

async function fetchCategoryData() {
  const response = await fetch(apiUrl + "/api/v1/tm_category");
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

function openEditDiaolog(task) {
  editedItem.value.id = task.id;
  editedItem.value.userId = task.userId;
  editedItem.value.categoryId = task.categoryId;
  editedItem.value.status = task.status;
  editedItem.value.ticketTitle = task.ticketTitle;
  editedItem.value.ticketDetail = task.ticketDetail;
  dialog.value = true;
}

async function updateData() {
  const body = {
    userId: editedItem.value.userId,
    categoryId: editedItem.value.categoryId,
    status: editedItem.value.status,
    ticketTitle: editedItem.value.ticketTitle,
    ticketDetail: editedItem.value.ticketDetail,
  };

  const response = await fetch(
    apiUrl + `/api/v1/td_ticket/${editedItem.value.id}`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  );
  return response;
}
async function editTicketData() {
  // 入力フォームのバリデーションチェック
  const validationResult = await validate();
  if (!validationResult) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "正しく入力されていない項目があります。";
    throw new Error("Validation error.");
  }
  try {
    const response = await updateData();
    // 200 OK以外のレスポンスの場合はエラーをスロー
    if (!response.ok) {
      // エラー時にsnackbarの状態を更新
      errorSnackBar.value = true;
      errorSnackBarText.value = "タスクの更新に失敗しました。";
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    loadTicketData();
    dialog.value = false;
  } catch (error) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "タスクの更新に失敗しました。";
    throw error;
  }
}

async function deleteData(ticketId) {
  const response = await fetch(apiUrl + `/api/v1/td_ticket/${ticketId}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response;
}

async function deleteTicketData(ticketId, status) {
  try {
    const response = await deleteData(ticketId);
    // 200 OK以外のレスポンスの場合はエラーをスロー
    if (!response.ok) {
      // エラー時にsnackbarの状態を更新
      errorSnackBar.value = true;
      errorSnackBarText.value = "ユーザー情報の削除に失敗しました。";
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    // リストから該当ユーザー情報を削除
    if (status === 0) {
      const index = tasks.value.todos.findIndex(
        (ticket) => ticket.id === ticketId
      );
      tasks.value.todos.splice(index, 1);
    } else if (status === 1) {
      const index = tasks.value.inProgress.findIndex(
        (ticket) => ticket.id === ticketId
      );
      tasks.value.inProgress.splice(index, 1);
    } else {
      const index = tasks.value.completed.findIndex(
        (ticket) => ticket.id === ticketId
      );
      tasks.value.completed.splice(index, 1);
    }
  } catch (error) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "ユーザー情報の削除に失敗しました。";
    throw error;
  }
}

onMounted(async () => {
  loadTicketData();
  userList.value = await fetchUserData();
  categoryList.value = await fetchCategoryData();
});

watch(isAddTicket, (newVal, oldVal) => {
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

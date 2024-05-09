<template>
  <div>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" sm="4">
          <v-sheet class="pa-1 ma-1 border">
            <h4 class="title-todos">未着手</h4>
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
                  <v-card
                    class="handle"
                    elevation="0"
                    variant="outlined"
                    @dblclick="openEditDiaolog(element)"
                  >
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      <template v-if="element.category != null">
                        {{ element.category.categoryName }}
                      </template>
                      <template v-else> </template>
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                    </v-card-text>
                    <v-btn
                      icon="mdi-pencil"
                      size="x-small"
                      @click="openEditDiaolog(element)"
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
            <h4 class="title-inprogress">処理中</h4>
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
                  <v-card
                    class="handle"
                    elevation="0"
                    variant="outlined"
                    @dblclick="openEditDiaolog(element)"
                  >
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      <template v-if="element.category != null">
                        {{ element.category.categoryName }}
                      </template>
                      <template v-else> </template>
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                    </v-card-text>
                    <v-btn
                      icon="mdi-pencil"
                      size="x-small"
                      @click="openEditDiaolog(element)"
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
            <h4 class="title-completed">完了</h4>
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
                  <v-card
                    class="handle"
                    elevation="0"
                    variant="outlined"
                    @dblclick="openEditDiaolog(element)"
                  >
                    <v-card-item :title="element.ticketTitle"></v-card-item>
                    <v-list-item>
                      <v-list-item-subtitle
                        >Task-{{ element.id }}</v-list-item-subtitle
                      >
                      <template v-if="element.category != null">
                        {{ element.category.categoryName }}
                      </template>
                      <template v-else> </template>
                    </v-list-item>
                    <v-card-text>
                      {{ element.ticketDetail }}
                    </v-card-text>
                    <v-btn
                      icon="mdi-pencil"
                      size="x-small"
                      @click="openEditDiaolog(element)"
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
      </v-row>
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
import { onMounted, ref, watch } from "vue";
import draggable from "vuedraggable";
import { commonFunctions } from "../utils/utils.js";

// const apiUrl = import.meta.env.VITE_API_URL
const apiUrl = "";
const emit = defineEmits(['add-data-completed']);

const props = defineProps({
  isAddTicket: Boolean,
});
// props.isAddTicketを返すgetter関数を作成
const isAddTicket = () => props.isAddTicket;

const dialog = ref(false);

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

function openEditDiaolog(task) {
  editedItem.value.id = task.id;
  editedItem.value.userId = task.userId;
  editedItem.value.categoryId = task.categoryId;
  editedItem.value.status = task.status;
  editedItem.value.ticketTitle = task.ticketTitle;
  editedItem.value.ticketDetail = task.ticketDetail;
  dialog.value = true;
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
    const response = await commonFunctions.updateData(editedItem.value, apiUrl);
    // 200 OK以外のレスポンスの場合はエラーをスロー
    if (!response.ok) {
      // エラー時にsnackbarの状態を更新
      errorSnackBar.value = true;
      errorSnackBarText.value = "タスクの更新に失敗しました。";
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    await commonFunctions.loadTicketData(tasks,apiUrl);
    dialog.value = false;
  } catch (error) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "タスクの更新に失敗しました。";
    throw error;
  }
}

async function deleteTicketData(ticketId, status) {
  try {
    const response = await commonFunctions.deleteData(apiUrl,ticketId);
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

function addItem(event) {
  const className = event.to.className.split(" ")[1];
  const element = event.item.__draggable_context.element;

  editedItem.value.id = element.id;
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
  commonFunctions.updateData(editedItem.value,apiUrl);
}

watch(isAddTicket, (newVal, oldVal) => {
  if (oldVal === false && newVal === true) {
    commonFunctions.loadTicketData(tasks,apiUrl);
    noticeSnackBar.value = true;
    noticeSnackBarText.value = "タスクを登録しました。";
    emit('add-data-completed',true);
  }
  else{
    emit('add-data-completed',false);
  }
});

onMounted(async () => {
  await commonFunctions.loadTicketData(tasks,apiUrl);
  userList.value = await commonFunctions.fetchUserData(apiUrl);
  categoryList.value = await commonFunctions.fetchCategoryData(apiUrl);
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
  display: inline-block;
  border: 1px solid #ccc; /* 枠線の色と太さ */
  padding: 3px; /* 内側の余白 */
  color: white;
  border-radius: 10px; /* 半径(px)を指定 */
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

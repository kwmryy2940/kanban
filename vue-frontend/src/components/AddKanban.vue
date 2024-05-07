<template>
  <!-- タスク登録ボタン -->
  <v-dialog v-model="dialog">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
        v-bind="activatorProps"
        fab
        absolute
        style="right: 20px; bottom: 20px; position: fixed"
        color="primary"
        icon="mdi-plus"
      >
      </v-btn>
    </template>
    <template v-slot:default="{ isActive }">
      <v-card>
        <v-card-title>タスク登録</v-card-title>

        <v-card-text>
          <v-container>
            <v-form ref="inputForm">
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="editedItem.ticketTitle"
                    label="タイトル"
                    maxlength="128"
                    :rules="validationRules.ticketTitle"
                    ><span style="color: red"> *</span></v-text-field
                  >
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="editedItem.ticketDetail"
                    label="内容"
                    maxlength="512"
                    :rules="validationRules.ticketDetail"
                  ></v-text-field>
                </v-col>
                <v-col>
                  <v-select
                    v-model="editedItem.userId"
                    label="担当者"
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
          <v-snackbar v-model="errorSnackBar" :timeout="5000" color="error">
            <v-icon class="me-2" size="small">
              mdi-alert-circle-outline
            </v-icon>
            {{ errorSnackBarText }}
            <template v-slot:actions>
              <v-btn
                color="white"
                variant="text"
                @click="errorSnackBar = false"
              >
                閉じる
              </v-btn>
            </template>
          </v-snackbar>
        </v-card-text>

        <v-card-actions>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="dialog = false"
          >
            キャンセル
          </v-btn>
          <v-btn color="blue-darken-1" variant="text" @click="save">
            登録
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script setup>
import { onMounted, ref } from "vue";

const dialog=ref(false);

const emit = defineEmits(['add-data']);

const errorSnackBar = ref(false); // エラー通知バーの表示を管理する変数
const errorSnackBarText = ref(""); // エラー通知バーの通知内容

const editedItem = ref({
  userId: null,
  categoryId: null,
  ticketTitle: "",
  ticketDetail: "",
});

const userList=ref([]);
const categoryList=ref([]);

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

const inputForm = ref(null);

async function validate() {
  const validationResult = await inputForm.value.validate();
  return validationResult.valid;
}

async function clearItems() {
  editedItem.value.userId = null;
  editedItem.value.categoryId = null;
  editedItem.value.ticketTitle = "";
  editedItem.value.ticketDetail = "";
}

async function fetchUserData(){
  const response = await fetch("/api/v1/tm_users");
  const data = await response.json();
  return data;
}

async function fetchCategoryData(){
  const response = await fetch("/api/v1/tm_category");
  const data = await response.json();
  return data;
}

async function registerData() {
  const body = {
    ...editedItem.value,
  };

  const response = await fetch("/api/v1/td_ticket", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return response;
}

async function save() {
  // 入力フォームのバリデーションチェック
  const validationResult = await validate();
  if (!validationResult) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "正しく入力されていない項目があります。";
    throw new Error("Validation error.");
  }

  try {
    const response = await registerData();
    // 200 OK以外のレスポンスの場合はエラーをスロー
    if (!response.ok) {
      // エラー時にsnackbarの状態を更新
      errorSnackBar.value = true;
      errorSnackBarText.value = "タスクの登録に失敗しました。";
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    emit('add-data',true);
    clearItems();
    dialog.value=false;
  } catch (error) {
    // エラー時にsnackbarの状態を更新
    errorSnackBar.value = true;
    errorSnackBarText.value = "タスク登録に失敗しました。";
    throw error;
  }
}

onMounted(async()=>{
  userList.value=await fetchUserData();
  categoryList.value=await fetchCategoryData();
})

</script>

<template>
  <!-- タスク登録ボタン -->
  <v-dialog>
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
            <v-form>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                  label="タイトル"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                  label="内容"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
              <v-select
                label="担当者"
                return-object
              >
              </v-select>
            </v-col>
            <v-col>
              <v-select
                label="カテゴリ"
                return-object
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

        <v-card-action>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="isActive.value = false"
          >
            キャンセル
          </v-btn>
          <v-btn color="blue-darken-1" variant="text">
            登録
          </v-btn>
        </v-card-action>
      </v-card>
    </template>
  </v-dialog>
</template>

<script setup>
import { ref } from "vue";

const errorSnackBar = ref(false); // エラー通知バーの表示を管理する変数
const errorSnackBarText = ref(""); // エラー通知バーの通知内容
</script>
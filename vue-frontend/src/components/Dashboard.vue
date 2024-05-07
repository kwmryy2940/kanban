<template>
  <div>
    <v-container>
    <h1>Dashboard</h1>
      <v-row>
        <v-col class="col border ma-1">
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
                  <v-list-item>
                    {{ element.name }}
                  </v-list-item>
                </v-card>
              </v-list>
            </template>
          </draggable>
        </v-col>
        <v-col class="col border ma-1">
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
                  <v-list-item>
                    {{ element.name }}
                  </v-list-item>
                </v-card>
              </v-list>
            </template>
          </draggable>
        </v-col>
         <v-col class="col border ma-1">
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
                  <v-list-item>
                    {{ element.name }}
                  </v-list-item>
                </v-card>
              </v-list>
            </template>
          </draggable>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import draggable from "vuedraggable";

const tasks = ref({
  todos: [
    {
      id: 1,
      name: "にんじん",
    },
    {
      id: 2,
      name: "じゃがいも",
    },
    {
      id: 3,
      name: "たまねぎ",
    },
    {
      id: 4,
      name: "カレールー",
    },
    {
      id: 5,
      name: "鶏肉",
    },
  ],
  inProgress: [],
  completed: [],
});


watch(tasks.todos, (val) => {
  console.log("todos:", val);
});
watch(tasks, (inProgress) => {
  console.log("inProgress:", val);
});

</script>

<style scoped>
.col {
  height: 90vh;
  overflow: auto;
}
.dragArea {
  padding: 10px;
  background-color: aqua;
}
.handle {
  cursor: pointer;
}
</style>

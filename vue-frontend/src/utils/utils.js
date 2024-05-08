export const commonFunctions = {
  // チケットデータ取得
  fetchTicketData: async (apiUrl) => {
    const response = await fetch(apiUrl + "/api/v1/td_ticket");
    const data = await response.json();
    return data;
  },

  // ユーザーマスタ取得
  fetchUserData: async (apiUrl) => {
    const response = await fetch(apiUrl + "/api/v1/tm_users");
    const data = await response.json();
    return data;
  },
  // タスクカテゴリマスタ取得
  fetchCategoryData: async (apiUrl) => {
    const response = await fetch(apiUrl + "/api/v1/tm_category");
    const data = await response.json();
    return data;
  },
  // 最新のチケットデータを取得し、メモリに保存する
  loadTicketData: async (tasks, apiUrl) => {
    tasks.value.todos = [];
    tasks.value.inProgress = [];
    tasks.value.completed = [];

    try {
      const data = await commonFunctions.fetchTicketData(apiUrl);
      data.forEach((item) => {
        if (item.status === 0) tasks.value.todos.push(item);
        else if (item.status === 1) tasks.value.inProgress.push(item);
        else tasks.value.completed.push(item);
      });
    } catch (error) {
      throw error;
    }
  },
  // チケットデータを登録する
  registerData: async (editedItem, apiUrl) => {
    const body = {
      ...editedItem,
    };

    const response = await fetch(apiUrl + "/api/v1/td_ticket", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    return response;
  },
  // チケットデータを更新する
  updateData: async (editedItem, apiUrl) => {
    const body = {
      userId: editedItem.userId,
      categoryId: editedItem.categoryId,
      status: editedItem.status,
      ticketTitle: editedItem.ticketTitle,
      ticketDetail: editedItem.ticketDetail,
    };

    const response = await fetch(
      apiUrl + `/api/v1/td_ticket/${editedItem.id}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      }
    );
    return response;
  },
  // チケットデータを削除する
  deleteData: async (apiUrl, ticketId) => {
    const response = await fetch(apiUrl + `/api/v1/td_ticket/${ticketId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response;
  },
};

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
};

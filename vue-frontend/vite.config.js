import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: "/kanban",
  build: {
    // outDir: "../src/main/resources/static",
    emptyOutDir: true,
    rollupOptions: {
      output: {
        entryFileNames: `assets/[name].js`,
        chunkFileNames: `assets/[name].js`,
        assetFileNames: `assets/[name].[ext]`,
      },
    },
    outDir:"./dist"
  },
  server: {
    proxy: {
      "/api": "http://localhost:8080",
    },
    port:5174
  },
});

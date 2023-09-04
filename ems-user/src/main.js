import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "vant/lib/index.css";
import {
  Form,
  Field,
  CellGroup,
  Sidebar,
  SidebarItem,
  Popup,
  Tabbar,
  TabbarItem,
} from "vant";

createApp(App)
  .use(store)
  .use(router)
  .use(Form)
  .use(Field)
  .use(CellGroup)
  .use(Sidebar)
  .use(SidebarItem)
  .use(Popup)
  .use(Tabbar)
  .use(TabbarItem)
  .mount("#app");

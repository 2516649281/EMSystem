import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "vant/lib/index.css";
import {
  Button,
  Form,
  Field,
  CellGroup,
  Sidebar,
  SidebarItem,
  Popup,
  Tabbar,
  TabbarItem,
  Cell,
  Image as VanImage,
  NavBar,
  Loading,
  Overlay,
  Uploader,
  Dialog,
  Search,
  DropdownMenu,
  DropdownItem,
  Card,
  SwipeCell,
  Tag,
  CountDown,
  Checkbox,
  CheckboxGroup,
  Icon,
} from "vant";

createApp(App)
  .use(store)
  .use(router)
  .use(Button)
  .use(Form)
  .use(Field)
  .use(CellGroup)
  .use(Sidebar)
  .use(SidebarItem)
  .use(Popup)
  .use(Tabbar)
  .use(TabbarItem)
  .use(Cell)
  .use(VanImage)
  .use(NavBar)
  .use(Loading)
  .use(Overlay)
  .use(Uploader)
  .use(Dialog)
  .use(Search)
  .use(DropdownMenu)
  .use(DropdownItem)
  .use(Card)
  .use(SwipeCell)
  .use(Tag)
  .use(CountDown)
  .use(Checkbox)
  .use(CheckboxGroup)
  .use(Icon)
  .mount("#app");

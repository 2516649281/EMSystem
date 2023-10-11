import {app} from "../main.js";
import "vant/lib/index.css";
import {
    Button,
    Card,
    Cell,
    CellGroup,
    Checkbox,
    CheckboxGroup,
    CountDown,
    Dialog,
    DropdownItem,
    DropdownMenu,
    Field,
    Form,
    Icon,
    Image as VanImage,
    Loading,
    NavBar,
    Overlay,
    Popup,
    Radio,
    RadioGroup,
    Search,
    Sidebar,
    SidebarItem,
    SwipeCell,
    Switch,
    Tabbar,
    TabbarItem,
    Tag,
    Uploader,
} from "vant";

app
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
    .use(RadioGroup)
    .use(Radio)
    .use(Switch)
    .mount("#app");

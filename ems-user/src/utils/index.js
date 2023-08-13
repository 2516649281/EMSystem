import {
    Toast
} from 'vant';

export const loading = (flag) => {
    if (flag) {
        Toast.loading({
            mask: true,
            message: '加载中...'
        });
    } else {
        Toast.clear();
    }
}
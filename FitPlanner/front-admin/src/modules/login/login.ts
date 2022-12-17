import {Options, Vue} from 'vue-class-component';
import { loginByAuth } from '@/services/auth';

import {PfButton, PfCheckbox} from "@profabric/vue-components";
import Input from "@/components/input/input.vue";

import {useToast} from 'vue-toastification';


@Options({
    components: {
        'app-input': Input,
        'pf-checkbox': PfCheckbox,
        'pf-button': PfButton
    }
})
export default class Login extends Vue {

    private appElement: HTMLElement | null = null;
    private toast = useToast();

    private inputId: string = '';
    private inputPassword: string = '';
    private rememberMe: boolean = false;

    public isAuthLoading: boolean = false;

    public mounted(): void {
        this.appElement = document.getElementById('app') as HTMLElement;
        this.appElement.classList.add('login-page');
    }

    public unmounted(): void {
        (this.appElement as HTMLElement).classList.remove('login-page');
    }

    public async loginByAuth(): Promise<void> {
        try {
            this.isAuthLoading = true;

            const token = await loginByAuth(this.inputId, this.inputPassword);

            if(token != null && token != "") {
                this.$store.dispatch('auth/login', token);
                this.toast.success(this.inputId + '님 로그인 완료');
            } else {
                this.toast.error("로그인을 실패하였습니다.\n확인 후, 다시 시도하여 주세요.");
            }

            this.isAuthLoading = false;

        } catch (error: any) {
            this.toast.error(error.message);
            this.isAuthLoading = false;
        }
    }

}
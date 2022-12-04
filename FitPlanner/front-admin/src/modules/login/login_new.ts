import {Options, Vue} from 'vue-class-component';

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
            const token = await loginByAuth(this.email, this.password);
            this.$store.dispatch('auth/login', token);
            this.toast.success('로그인 완료');
            this.isAuthLoading = false;
        } catch (error: any) {
            this.toast.error(error.message);
            this.isAuthLoading = false;
        }
    }

}
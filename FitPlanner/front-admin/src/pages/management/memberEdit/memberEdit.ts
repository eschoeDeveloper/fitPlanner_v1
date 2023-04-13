import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";
import {Form} from "element-ui";

import {useToast} from 'vue-toastification';


@Options({})
export default class MemberEdit extends Vue {

    private memberInfo: any = {};
    private toast = useToast();

    public created(): void {
        this.getMemberInfo();
    }

    public getMemberInfo(): void {

        axios.post("/api/admin/member/get", {seq: this.$route.query.memberSeq}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            this.memberInfo = JSON.parse(respJson.data);

        }).catch((error) => {
            console.log(error);
        });

    };

    public executeMemberEdit(): void {

        const jsonData: any = {};
        const memberForm = document.querySelector("form") as HTMLFormElement;
        const formData = new FormData(memberForm);

        if((formData.get("password") && formData.get("checkPassword")) && (formData.get("password") != formData.get("checkPassword"))) {
            this.toast.error("변경 비밀번호가 일치하지 않습니다.");
            return;
        }

        formData.set("seq", this.$route.query.memberSeq.toString());
        formData.forEach((value: any, key: any) => (jsonData[key] = value));

        axios.post("/api/admin/member/update", JSON.stringify(jsonData), {headers:{"Content-Type":"application/json"}})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));

            const respCode = Number(respJson.code);

            if(respCode == 200) {
                this.getMemberInfo();
            } else {
                this.toast.error("오류가 발생하였습니다. 잠시 후, 다시 시도하여 주세요.");
            }

        }).catch((error) => {
            console.log(error);
        });

    };

    public goMemberList(): void {
        this.$router.push({name: "Member"});
    }

}

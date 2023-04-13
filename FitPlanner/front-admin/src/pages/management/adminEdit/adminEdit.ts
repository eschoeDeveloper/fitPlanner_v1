import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";
import {Form} from "element-ui";

import {useToast} from 'vue-toastification';


@Options({})
export default class AdminEdit extends Vue {

    private adminInfo: any = {};
    private toast = useToast();
    private editMode: string = "";

    public created(): void {

        if(Number(this.$route.query.adminSeq) < 1) {
            this.editMode = "create";
        } else {
            this.editMode = "update";
            this.getAdminInfo();
        }

    }

    public mounted(): void {
        const inputDelYn = document.getElementById("inputDelYn") as HTMLSelectElement;
        if(this.editMode == "create") {
            inputDelYn.value = "N";
        }
        inputDelYn.disabled = true;
    }

    public getAdminInfo(): void {

        const jsonData: any = {};

        if(Number(this.$route.query.adminSeq) > 0) jsonData["seq"] = this.$route.query.adminSeq;

        alert(JSON.stringify(jsonData));

        axios.post("/api/admin/get", jsonData, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            this.adminInfo = JSON.parse(respJson.data);

        }).catch((error) => {
            console.log(error);
        });

    };

    public executeAdminEdit(): void {
        const inputDelYn = document.getElementById("inputDelYn") as HTMLSelectElement;
        const memberForm = document.querySelector("form") as HTMLFormElement;
        const formData = new FormData(memberForm);
        const jsonData: any = {};

        if((formData.get("password") && formData.get("checkPassword")) && (formData.get("password") != formData.get("checkPassword"))) {
            this.toast.error("변경 비밀번호가 일치하지 않습니다.");
            return;
        }

        if(Number(this.$route.query.adminSeq) > 0) {
            formData.set("delYn", inputDelYn.value);
            formData.set("seq", this.$route.query.adminSeq.toString());
        } else {
            formData.set("delYn", "N");
        }

        formData.forEach((value: any, key: any) => (jsonData[key] = value));

        axios.post("/api/admin/" + this.editMode, JSON.stringify(jsonData), {headers:{"Content-Type":"application/json"}})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));

            const respCode = Number(respJson.code);

            if(respCode == 200) {
                this.getAdminInfo();
            } else {
                this.toast.error("오류가 발생하였습니다. 잠시 후, 다시 시도하여 주세요.");
            }

        }).catch((error) => {
            console.log(error);
        });

    };

    public goAdminList(): void {
        this.$router.push({name: "Admin"});
    }

}

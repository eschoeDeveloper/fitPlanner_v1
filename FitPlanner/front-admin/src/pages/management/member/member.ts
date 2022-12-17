import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";
import {IMember} from "@/interfaces/member";

import {useToast} from 'vue-toastification';

@Options({})
export default class Member extends Vue {

    private memberList: any = [];

    private toast = useToast();

    public created(): void {
        this.getMemberList();
    };

    public getMemberList(): void {
        axios.post("/api/admin/member/list", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            const respData = JSON.parse(respJson.data);

            this.memberList = JSON.parse(JSON.stringify(respData.memberList));

        }).catch((error) => {
            console.log(error);
        });
    }

    public goMemberEdit(memberSeq: number): void {
        this.$router.push({path: '/management/member/edit', query: {memberSeq: memberSeq}});
    }

    public deleteMember(memberId: string): void {

        axios.post("/api/admin/member/delete", {id: memberId}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            // const respData = JSON.parse(respJson.data);

            const respCode = Number(respJson.code);

            if(respCode == 200) {
                // this.$router.push({name: 'Member'});
                this.getMemberList();
            } else {
                this.toast.error("오류가 발생하였습니다. 잠시 후, 다시 시도하여 주세요.");
            }


        }).catch((error) => {
            console.log(error);
        });



    }

}

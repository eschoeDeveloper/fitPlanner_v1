import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";

@Options({})
export default class Admin extends Vue {

    private adminList: any = [];

    public created(): void {

        axios.post("/api/admin/list", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            const respData = JSON.parse(respJson.data);

            this.adminList = JSON.parse(JSON.stringify(respData.adminList));

        }).catch((error) => {
            console.log(error);
        });

    };

    public goAdminEdit(adminSeq: number): void {
        this.$router.push({path: '/management/admin/edit', query: {adminSeq: adminSeq}});
    }

}

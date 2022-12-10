import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";

@Options({})
export default class Dashboard extends Vue {

    private dashboardInfo: object = {};

    public created(): void {

        this.dashboardInfo = axios.post("/api/admin/login", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            const respData = JSON.parse(respJson.data);
            const respCode = respJson.code;

            if (Number(respCode) < 400) {
                axios.defaults.headers.common["Authorization"] = "Bearer " + respData.jwtToken;
                return respData.jwtToken;
            }

        }).catch((error) => {
           console.log(error);
        });

    }

}

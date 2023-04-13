import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";

@Options({})
export default class Dashboard extends Vue {

    private exerciseRankList: any = [];
    private memberSexList: any = [];
    private memberAgeList: any = [];

    public created(): void {

        axios.post("/api/admin/main/data", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            const respData = JSON.parse(respJson.data);

            this.exerciseRankList = JSON.parse(JSON.stringify(respData.getExerciseRankList));
            this.memberSexList = JSON.parse(JSON.stringify(respData.getMemberSexList));
            this.memberAgeList = JSON.parse(JSON.stringify(respData.getMemberAgeList));

        }).catch((error) => {
           console.log(error);
        });

    }

}

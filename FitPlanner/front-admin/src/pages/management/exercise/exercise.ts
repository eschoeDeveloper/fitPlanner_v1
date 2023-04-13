import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";

@Options({})
export default class Exercise extends Vue {

    private exerciseList: any = [];

    public created() : void {
        this.getExerciseList();
    }

    public getExerciseList(): void {

        axios.post("/api/admin/fitExercise/list", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            this.exerciseList = JSON.parse(JSON.stringify(respJson.data));

        }).catch((error) => {
            console.log(error);
        });

    }



}

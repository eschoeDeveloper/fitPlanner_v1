import {Options, Vue} from 'vue-class-component';
import axios from "@/utils/axios";

@Options({})
export default class ExerciseCategory extends Vue {

    private exerciseCategoryList: any = [];

    public created() : void {
        this.getExerciseCategoryList();
    }

    public getExerciseCategoryList(): void {

        axios.post("/api/admin/fitExercise/category/list", {}, {})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            this.exerciseCategoryList = JSON.parse(JSON.stringify(respJson.data));

        }).catch((error) => {
            console.log(error);
        });

    }



}

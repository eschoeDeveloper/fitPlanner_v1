
import axios from "@/utils/axios";

const getError = (error: any) => {
    const message = error.message || 'Failed';
    return new Error(message);
};

export const loginByAuth = async (inputId: string, inputPassword: string) => {

    try {

        const requestData = JSON.stringify({
            inputId: inputId,
            inputPassword: inputPassword
        });

        const jwtToken = await axios.post("/api/admin/login", requestData, {headers:{"Content-Type": "application/json"}})
        .then((response) => {

            const respJson = JSON.parse(JSON.stringify(response.data));
            const respData = JSON.parse(respJson.data);
            const respCode = respJson.code;

            if(Number(respCode) < 400 ) {
                axios.defaults.headers.common["Authorization"] = "Bearer " + respData.jwtToken;
                return respData.jwtToken;
            }

        }).catch((error) => {
            console.log(error);
        });

        return jwtToken;

    } catch (error: any) {
        throw getError(error);
    }

};
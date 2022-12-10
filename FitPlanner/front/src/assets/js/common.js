/**
* Common.JS - Vue Component에서 활용할 공통 기능 함수들
* */

import axios from "axios";

// =================================================
// [QueryString -> JSON]
// =================================================
const getQueryStringToJson = async (queryString) => {

    if(!queryString) return {};
    else return JSON.parse('{"' + decodeURI(queryString).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');

};

// =================================================
// [HTTP]
// =================================================
// await로 처리할 것
const callRestApi = async (url, method, params) => {

    const realMethod = (!method) ? "POST" : method;
    let realParams = params;

    if(realMethod.startsWith("GET")) {
        realParams = getQueryStringToJson(realParams);
    }

    return await axios({
        method : realMethod,
        url : url,
        data : realParams
    })
    .then((response) => {
        console.log(response.data);
        return response.data;
    })
    .catch((error) => {
       console.log(error);
       return error;
    });

};

export {callRestApi, getQueryStringToJson};
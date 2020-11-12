import axios from "axios";

let g_cxt = "/";
g_cxt = g_cxt.substring(0, g_cxt.length - 1);

/** 필요 함수: cf_whatIsIt, cf_isEmpty */
/**
 * 해당객체의 타입명을 반환해주는 함수
 * @param obj
 * @returns
 */
function cf_whatIsIt(obj) {
  let stringConstructor = "test".constructor;
  let numberConstructor = Number("123").constructor;
  let arrayConstructor = [].constructor;
  let objectConstructor = {}.constructor;

  if (obj === null) {
    return "null";
  } else if (obj === undefined) {
    return "undefined";
  } else if (obj.constructor === stringConstructor) {
    return "string";
  } else if (obj.constructor === numberConstructor) {
    if (isNaN(obj)) return "nothing";
    return "number";
  } else if (obj.constructor === arrayConstructor) {
    return "array";
  } else if (obj.constructor === objectConstructor) {
    return "object";
  } else {
    return "nothing";
  }
}

/**
 * 해당 파라메타가 비어있는지 확인
 *
 * @param obj
 * @returns
 */
function cf_isEmpty(obj) {
  let objtyp = cf_whatIsIt(obj);
  if (objtyp == "null") return true;
  else if (objtyp == "undefined") return true;
  else if (objtyp == "string" && obj == "") return true;
  else if (objtyp == "object" && obj == {}) return true;
  else if (objtyp == "object" && obj.length == undefined) return true;
  return false;
}

const rsltFailArr = ["error", "dev-error", "FAIL"];

export default async ({ Vue, store }) => {
  const cf = {
    /**
     * ajax 통신함수
     *
     * @param url
     * @param param
     * @param callback
     * @param options
     * @param loadingbar
     * @returns
     */
    async call(url, param, callback, options, loadingbar) {
      if (cf_isEmpty(options)) options = {};

      if (loadingbar !== false) {
        store.commit("setLoading", { isLoading: true });
      }

      axios
        .post(g_cxt + url, param, options)
        .then(function(response) {
          store.commit("setLoading", { isLoading: false });
          if (rsltFailArr.includes(response.data.rsltStat)) {
            if (
              response.data.rsltStat == "dev-error" &&
              !cf_isEmpty(response.data.errMsg)
            ) {
              alert(response.data.errMsg);
            } else {
              alert("처리 중 오류가 발생했습니다. \n관리자에게 문의하세요.");
            }
          } else {
            if (callback != null) {
              if (
                cf_whatIsIt(response.data) === "string" &&
                response.data.indexOf("<!DOCTYPE html>") != -1
              ) {
                alert("처리 중 오류가 발생했습니다. \n관리자에게 문의하세요.");
              } else {
                callback(response.data);
              }
            }
          }
        })
        .catch(function(error) {
          store.commit("setLoading", { isLoading: false });
          if (error.message == "Network Error") {
            alert("네트워크 상태 또는 서버 구동상태를 확인해 주세요.");
          } else {
            alert("처리 중 오류가 발생했습니다. \n관리자에게 문의하세요.");
          }
          console.log(error);
        });
    },

    /**
     * 파일 업로드를 동반한 통신함수
     * @param url
     * @param fileList
     * @param param
     * @param callback
     * @returns
     */
    async callWithFiles(url, fileList, param, callback) {
      let formData = new FormData();
      for (let i = 0; i < fileList.length; i++) {
        formData.append("fileList", fileList[i]);
      }
      formData.append("param", JSON.stringify(param));

      $.ajax({
        url: g_cxt + url,
        dataType: "json",
        data: formData,
        type: "POST",
        contentType: false,
        processData: false,
        beforeSend: function() {
          store.commit("setLoading", { isLoading: true });
        },
        success: function(data, status) {
          store.commit("setLoading", { isLoading: false });
          if (rsltFailArr.includes(data.rsltStat)) {
            if (data.rsltStat == "user-error" && !cf_isEmpty(data.errMsg)) {
              alert(data.errMsg);
            } else {
              alert("업로드에 실패했습니다. \n관리자에게 문의하세요.");
            }
          } else {
            if (callback != null) {
              callback(data);
            }
          }
        },
        error: function() {
          store.commit("setLoading", { isLoading: false });
          alert("업로드에 실패했습니다. \n관리자에게 문의하세요.");
        }
      });
    },

    /**
     * 파일 다운로드 함수
     * @param fileName
     * @returns
     */
    async downloadFile(fileName) {
      location.href = g_cxt + "/downloadFile?fileName=" + fileName;
    },

    /**
     * 해당 파라메타가 비어있는지 확인
     *
     * @param obj
     * @returns
     */
    isEmpty(obj) {
      return cf_isEmpty(obj);
    }
  };

  cf.Vue = Vue;

  Vue.prototype.$cf = cf;
};

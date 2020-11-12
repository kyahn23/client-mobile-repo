export default ({ Vue }) => {
  /** 돈 양식 필터 */
  Vue.filter("showMeTheMoney", value => {
    if (!value) {
      return "";
    }
    return parseInt(value, 10).toLocaleString() + "원";
  });

  /** 포함/미포함 여부 필터 */
  Vue.filter("isIncluded", value => {
    if (!value) {
      return "";
    }

    value = value.substring(0, 1).toUpperCase();

    if (value === "Y" || value === "T") {
      return "포함";
    }

    return "미포함";
  });
};

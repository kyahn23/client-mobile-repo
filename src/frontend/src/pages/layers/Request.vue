<template>
  <q-page class="flex column">
    <div class="fixed-top full-width">
      <div class="q-my-md full-width text-body1 text-center text-weight-bold">
        상담신청
      </div>
      <q-separator />
    </div>

    <q-form ref="cancelForm" @submit.prevent="onConfirm">
      <div
        id="scrollDiv"
        class="overflow-auto q-px-md q-pt-md"
        style="margin-top: 25px; height: calc(100vh - 125px);"
      >
        <div class="q-mb-lg text-body1 text-weight-bold">
          선택하신 <span class="text-primary">{{ bnList.length }}</span
          >개의 업체에 상담을 신청하시겠습니까?
        </div>
        <div class="q-mb-sm text-subtitle2 text-weight-bold">
          선택 업체
        </div>
        <div class="q-mb-lg column">
          <div v-for="(bn, index) in bnList" :key="index" class="q-mb-xs row">
            <q-icon
              name="store"
              style="font-size: 1.6em; margin-right: 10px;"
            />{{ bn.bnNm }}
          </div>
        </div>
        <!-- <div class="q-mb-sm text-subtitle2 text-weight-bold">
          이름
        </div>
        <div class="q-mb-sm">
          <q-input
            outlined
            dense
            v-model="clientName"
            placeholder="이름을 입력해주세요"
            :rules="[$rules.required('이름을 입력해주세요.')]"
          />
        </div> -->
        <div class="q-mb-sm text-subtitle2 text-weight-bold">
          연락처
        </div>
        <div class="q-mb-sm">
          <q-input
            outlined
            dense
            type="tel"
            v-model="clientNumber"
            mask="###########"
            placeholder="연락처를 입력해주세요"
            :rules="[$rules.required('연락처를 입력해주세요.')]"
          />
        </div>
        <div class="q-mb-xs text-subtitle2 text-weight-bold">
          개인정보 수집 이용 및 제3자 제공 동의
        </div>
        <div class="q-mb-sm row" style="z-index: 70000;">
          <div class="col-auto">
            <q-checkbox
              class="text-caption text-weight-light"
              size="xs"
              v-model="privacyAgree"
              label="개인정보 수집 이용 및 제3자 제공에 동의합니다."
              style="margin: 0 4px 0 -6px; font-size: 0.6em;"
            />
          </div>
          <div class="col-1 q-ml-md q-mt-xs">
            <q-icon name="expand_more" :style="expandStyle" @click="onExpand" />
          </div>
        </div>
        <div id="privacyContent" class="q-mb-xs" :style="scrollBoxStyle">
          <q-scroll-area
            v-if="expandToggle"
            visible
            class="q-py-md q-px-md bg-grey-1"
            :thumb-style="thumbStyle"
            style="height: 100px; font-size: 0.8em;"
          >
            <p class="q-mb-none" v-if="expandLoading" :style="pTextStyle">
              ①개인정보의 수집·이용목적<br />
              예시) 포인트 적립, 입시상담, 급여관리, ...<br /><br />
              ②수집하려는 개인정보의 항목<br />
              예시) 성명, 번화번호, 주소, 이메일, 학년, ...<br /><br />
              ③개인정보의 보유 및 이용기간(근거법률)<br />
              예시)1년, 수강기간, 퇴사후 3년, ...<br /><br />
              거래기록<br />
              5년(전자상거래 등에서의 소비자 보호에 관한 법률)<br />
              ④동의를 거부할 수 있으며, 예시)동의 거부시 ○○서비스가 제공되지
              않습니다.
            </p>
          </q-scroll-area>
        </div>
      </div>

      <div class="row q-mb-md q-col-gutter-x-xs justify-center fixed-bottom">
        <div style="width: 48%;">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="취소"
            style="width: 100%;"
            @click="onClose"
          />
        </div>
        <div style="width: 48%;">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="확인"
            style="width: 100%;"
            type="submit"
          />
        </div>
      </div>
    </q-form>
  </q-page>
</template>

<script>
import { scroll } from "quasar";
const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "LayerRequest",
  props: {
    dealno: {
      type: String,
      required: true
    },
    regdis: {
      type: String,
      required: true
    },
    reqno: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      /** 상담요청 업체 목록 */
      requestList: [],
      /** 업체 이름 목록 */
      bnList: [],
      /** 고객 이름 */
      // clientName: "",
      /** 고객 전화번호 */
      clientNumber: "",
      /** 개인정보 동의 여부 */
      privacyAgree: false,
      /** 개인정보 동의서 읽음 여부 */
      privacyRead: false,
      /** 확장 버튼 style object */
      expandStyle: {
        fontSize: "1.6em",
        transition: "all ease 0.8s",
        transform: ""
      },
      /** 확장 버튼 누름 여부 */
      expandToggle: false,
      /** 확장 완료 여부 */
      expandLoading: false,
      /** 스크롤박스 style object */
      scrollBoxStyle: {
        transition: "all ease 0.6s",
        opacity: 0,
        transform: "translateY(-50%) scaleY(0.25)"
      },
      /** 스크롤바 style object */
      thumbStyle: {
        transition: "all ease 0.2s",
        right: "4px",
        borderRadius: "2px",
        backgroundColor: "#000000",
        width: "3px",
        opacity: 0
      },
      /** 스크롤박스 텍스트 style object */
      pTextStyle: {
        transition: "all ease 0.2s",
        opacity: 0
      }
    };
  },
  watch: {
    /** 동의 checkbox 최초 클릭 시 동의서 표시 */
    privacyAgree(newValue) {
      if (newValue && !this.privacyRead) {
        this.privacyRead = true;
        if (!this.expandToggle) {
          this.onExpand();
        }
      }
    }
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.requestList = this.reqno.split("-", 3);
    this.getShopName();
  },
  methods: {
    /** 업체 이름 호출 함수 */
    getShopName() {
      this.$cf.call(
        process.env.API + "/api/shop/name",
        {
          bnList: this.requestList
        },
        this.shopNameCb,
        true
      );
    },
    /** 업체 이름 콜백 함수 */
    shopNameCb(response) {
      this.bnList = response.nameList;
    },
    /** 확장 버튼 클릭 이벤트 */
    onExpand() {
      this.privacyRead = true;
      if (!this.expandToggle) {
        this.expandToggle = !this.expandToggle;
        this.expandLoading = !this.expandLoading;

        this.expandStyle.transform = "rotate( -180deg )";
        this.scrollBoxStyle.opacity = 1;
        this.scrollBoxStyle.transform = "";

        const ele = document.getElementById("privacyContent");
        const target = getScrollTarget(ele);
        const offset = ele.offsetTop;
        const duration = 1000;
        setScrollPosition(target, offset, duration);

        setTimeout(() => {
          this.thumbStyle.opacity = 0.2;
          this.pTextStyle.opacity = 1;
        }, 600);
      } else {
        this.pTextStyle.opacity = 0;
        this.thumbStyle.opacity = 0;

        const ele = document.getElementById("privacyContent");
        const target = getScrollTarget(ele);
        const offset =
          ele.offsetTop - document.documentElement.clientHeight * 0.84;
        const duration = 300;
        setScrollPosition(target, offset, duration);

        setTimeout(() => {
          this.expandStyle.transform = "";
          this.scrollBoxStyle.opacity = 0;
          this.scrollBoxStyle.transform = "translateY(-50%) scaleY(0.1)";
        }, 200);
        setTimeout(() => {
          this.expandLoading = !this.expandLoading;
          this.expandToggle = !this.expandToggle;
        }, 600);
      }
    },
    /** 취소 버튼 클릭 이벤트 */
    onClose() {
      document.getElementById("layerCloseBtn").click();
    },
    /** 확인 버튼 클릭 이벤트 */
    onConfirm() {
      if (!this.privacyAgree) {
        this.$store.commit("setNotification", {
          color: "negative",
          textColor: "white",
          message: "개인정보 이용 및 제공에 동의해주세요.",
          caption: "동의 후 상담신청이 가능합니다."
        });
      } else {
        if (!this.$cf.isEmpty(this.clientNumber)) {
          this.$cf.call(
            process.env.API + "/api/call/register",
            {
              phoneNo: this.clientNumber,
              dealNo: this.dealno,
              pnRegDis: this.regdis,
              bnList: this.requestList
            },
            this.confirmCb,
            true
          );
        }
      }
    },
    /** 확인 버튼 이벤트 콜백 함수 */
    confirmCb() {
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "상담신청이 완료되었습니다."
      });
      this.onClose();
    }
  }
};
</script>
<style></style>

<template>
  <q-page class="flex column">
    <div class="fixed-top full-width">
      <div class="q-my-md full-width text-body1 text-center text-weight-bold">
        상담취소
      </div>
      <q-separator />
    </div>

    <q-form ref="cancelForm" @submit.prevent="onConfirm">
      <div
        id="scrollDiv"
        class="overflow-auto q-px-md q-py-md"
        style="margin-top: 25px; height: calc(100vh - 125px);"
      >
        <div class="q-mb-lg text-body1 text-weight-bold">
          <span class="text-primary">{{ businessName }}</span> 상담신청을
          취소하시겠습니까?
        </div>
        <div class="q-mb-sm text-subtitle2 text-weight-bold">
          취소사유
        </div>
        <div>
          <q-input
            outlined
            dense
            v-model="cnclCmnt"
            placeholder="취소사유를 입력해주세요"
            :rules="[$rules.required('취소사유를 입력해주세요.')]"
          />
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
export default {
  name: "LayerCancel",
  props: {
    callno: {
      type: String,
      required: true
    },
    bnno: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      businessName: "",
      cnclCmnt: ""
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.getShopName();
  },
  methods: {
    /** 업체 이름 호출 함수 */
    getShopName() {
      this.$cf.call(
        process.env.API + "/api/shop/name",
        {
          bnList: [this.bnno]
        },
        this.shopNameCb,
        true
      );
    },
    /** 업체 이름 콜백 함수 */
    shopNameCb(response) {
      this.businessName = response.nameList[0].bnNm;
    },
    /** 취소 버튼 클릭 이벤트 */
    onClose() {
      document.getElementById("layerCloseBtn").click();
    },
    /** 확인 버튼 클릭 이벤트 */
    onConfirm() {
      if (!this.$cf.isEmpty(this.cnclCmnt)) {
        this.$cf.call(
          process.env.API + "/api/call/delete",
          {
            callNo: this.callno,
            cnclCmnt: this.cnclCmnt,
            email: this.currentUser
          },
          this.confirmCb,
          true
        );
      }
    },
    /** 확인 버튼 이벤트 콜백 함수 */
    confirmCb() {
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "해당 상담이 취소되었습니다."
      });
      this.onClose();
    }
  },
  computed: {
    /** 현재 로그인 한 사용자 */
    currentUser: {
      get() {
        return this.$store.getters.currentUser;
      }
    }
  }
};
</script>

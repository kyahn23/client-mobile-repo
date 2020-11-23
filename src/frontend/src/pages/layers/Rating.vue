<template>
  <q-page class="flex column">
    <div class="fixed-top full-width">
      <div class="q-my-md full-width text-body1 text-center text-weight-bold">
        평가하기
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
          <span class="text-primary">강서대리점</span> 서비스에 만족하셨습니까?
        </div>
        <div class="q-mb-sm text-subtitle2 text-weight-bold">
          서비스 만족도 평가
        </div>
        <div class="q-mb-md">
          <q-rating
            v-model="ratingScore"
            size="2.5em"
            color="grey"
            color-selected="amber"
          />
        </div>
        <div style="width: 100%;">
          <q-input
            v-model="ratingComment"
            outlined
            counter
            maxlength="100"
            type="textarea"
            style="max-height: 146px;"
          />
        </div>
        <div class="row">
          <div class="col" style="max-width: 200px;">
            <q-checkbox
              class="text-caption text-weight-bold"
              size="xs"
              v-model="callReport"
              label="판매자 신고하기"
              style="margin: 0 4px 0 -6px;"
            />
          </div>
          <div class="col">
            <q-checkbox
              class="text-caption text-weight-bold"
              size="xs"
              v-model="callTerminated"
              label="개통완료"
              style="margin: 0 4px 0 -6px;"
            />
          </div>
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
    <q-dialog
      v-model="warningDialog"
      persistent
      transition-show="scale"
      transition-hide="scale"
    >
      <q-card style="width: 300px">
        <q-card-section>
          <div class="text-subtitle1 text-weight-bold">
            <q-icon name="warning" style="font-size: 1.2em;" /> 판매자 신고하기
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          판매자 신고는 신중히 해주세요!<br />
          판매자 신고 시 평가완료 후 평가수정을 할 수 없습니다.
        </q-card-section>

        <q-card-actions align="right">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="확인"
            style="width: 48%"
            v-close-popup
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
export default {
  name: "LayerRating",
  props: {
    dealno: {
      type: String,
      required: true
    },
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
      /** 평점 */
      ratingScore: 3,
      /** 평가내용 */
      ratingComment: "",
      /** 신고 여부 */
      callReport: false,
      /** 신고 안내문 1회 표시 여부 */
      callReportWarning: false,
      /** 신고 안내문 dialog 표시 여부 */
      warningDialog: false,
      /** 개통 완료 여부 */
      callTerminated: false
    };
  },
  watch: {
    /** 신고 checkbox 최초 클릭 시 안내문 표시 */
    callReport(newValue) {
      if (newValue && !this.callReportWarning) {
        this.warningDialog = true;
        this.callReportWarning = true;
      }
    }
  },
  mounted() {},
  methods: {
    /** 취소 버튼 클릭 이벤트 */
    onClose() {
      document.getElementById("layerCloseBtn").click();
    },
    /** 확인 버튼 클릭 이벤트 */
    onConfirm() {
      if (!this.$cf.isEmpty(this.ratingComment)) {
        this.$cf.call(
          process.env.API + "URL",
          { PARAMS },
          this.confirmCb,
          true
        );
      }
    },
    /** 확인 버튼 이벤트 콜백 함수 */
    confirmCb() {
      this.onClose();
    }
  }
};
</script>

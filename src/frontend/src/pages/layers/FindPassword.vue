<template>
  <q-page class="flex column flex-center">
    <h5>비밀번호 찾기</h5>
    <div class="q-pb-xl">가입정보를 아래에 입력하세요.</div>
    <q-form
      class="flex column flex-center q-px-sm q-py-none fit"
      @submit.prevent="onSubmit"
    >
      <div class="q-px-md q-py-none fit">
        <span class="text-subtitle1">아이디</span>
      </div>
      <q-input
        v-model="userId"
        type="email"
        placeholder="이메일"
        dense
        class="q-px-md q-pb-md fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.email('올바른 이메일이 아닙니다.')
        ]"
      />
      <div class="q-px-md q-pt-lg q-pb-none fit">
        <span class="text-subtitle1">닉네임</span>
      </div>
      <q-input
        v-model="userNm"
        placeholder="닉네임"
        dense
        class="q-px-md q-pb-md fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.minLength(3, '3자리 이상 입력해주세요.'),
          $rules.maxLength(20, '20자리 이하 입력해주세요.')
        ]"
      />
      <div class="q-px-md q-pt-xl q-pb-lg full-width">
        <q-btn
          rounded
          unelevated
          class="full-width"
          color="primary"
          label="확인"
          type="submit"
        />
      </div>
    </q-form>
    <q-dialog
      v-model="completeDialog"
      persistent
      transition-show="scale"
      transition-hide="scale"
    >
      <q-card style="width: 300px">
        <q-card-section>
          <div class="text-subtitle1 text-weight-bold">
            <q-icon name="error" style="font-size: 1.2em;" /> 비밀번호 찾기
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          입력하신 가입정보 확인 후 가입정보가 있을 시 비밀번호 재설정 메일을
          이메일로 보내드립니다.
        </q-card-section>

        <q-card-actions align="right">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="확인"
            style="width: 48%"
            @click="onCloseLayer"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
export default {
  name: "LayerFindPassword",
  data() {
    return {
      userId: "",
      userNm: "",
      completeDialog: false
    };
  },
  methods: {
    /** form submit 이벤트 함수 */
    onSubmit() {
      /** form 자체 validation 이용 */
      let param = {
        email: this.userId,
        nickname: this.userNm
      };

      this.$cf.call(
        process.env.API + "/api/auth/findpw",
        param,
        this.afterSubmit,
        true
      );
    },
    /** submit 콜백 함수 */
    afterSubmit(response) {
      this.completeDialog = true;
    },
    onCloseLayer() {
      this.completeDialog = false;
      this.$router.push({ path: "/main" });
    }
  }
};
</script>

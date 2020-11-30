<template>
  <q-page class="flex column flex-center">
    <h5>비밀번호 재설정</h5>
    <div>
      <span class="text-primary text-weight-bold">{{ userNm }}</span
      >님의 비밀번호를 재설정합니다.
    </div>
    <div class="q-pb-lg">새 비밀번호를 입력해주세요.</div>
    <q-form
      class="flex column flex-center q-px-sm q-py-none fit"
      @submit.prevent="onSubmit"
    >
      <div class="q-px-md q-py-none fit">
        <span class="text-subtitle1">새 비밀번호</span>
      </div>
      <q-input
        v-model="newPassword"
        placeholder="새 비밀번호"
        :type="isPwd ? 'password' : 'text'"
        dense
        class="q-px-md q-pb-md fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.alphaNum('영어 대문자, 소문자, 숫자 입력만 가능합니다.'),
          $rules.minLength(10, '10-20자리 비밀번호를 입력해주세요.'),
          $rules.maxLength(20, '10-20자리 비밀번호를 입력해주세요.')
        ]"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <div class="q-px-md q-pt-lg q-pb-none fit">
        <span class="text-subtitle1">새 비밀번호 확인</span>
      </div>
      <q-input
        v-model="newPasswordChk"
        placeholder="새 비밀번호 확인"
        :type="isPwdChk ? 'password' : 'text'"
        dense
        class="q-px-md q-pb-md fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.sameAs(newPassword, '입력하신 비밀번호와 일치하지 않습니다.')
        ]"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwdChk ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwdChk = !isPwdChk"
          />
        </template>
      </q-input>
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
  </q-page>
</template>

<script>
import sha256 from "crypto-js/sha256";

export default {
  name: "LayerResetPassword",
  props: {
    mbr: {
      type: String,
      required: true
    },
    cue: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      userId: "",
      userNm: "",
      newPassword: "",
      newPasswordChk: "",
      isPwd: true,
      isPwdChk: true
    };
  },
  mounted() {
    if (this.cue === undefined || this.$cf.isEmpty(this.cue)) {
      this.$router.push({ path: "/404" });
    } else {
      this.verifyUser();
    }
  },
  methods: {
    /** props 확인 함수 */
    verifyUser() {
      this.$cf.call(
        process.env.API + "/api/auth/verify",
        {
          mbr: this.mbr,
          cue: this.cue,
          tgt: "USER"
        },
        this.verifyCb,
        true
      );
    },
    verifyCb(response) {
      if (this.$cf.isEmpty(response.userId)) {
        this.$router.push({ path: "/404" });
      } else {
        this.userId = response.userId;
        this.userNm = response.userNm;
      }
    },
    /** form submit 이벤트 함수 */
    onSubmit() {
      /** form 자체 validation 이용 */
      let param = {
        email: this.userId,
        newPassword: sha256(this.newPassword).toString()
      };

      this.$cf.call(
        process.env.API + "/api/auth/reset",
        param,
        this.afterSubmit,
        true
      );
    },
    /** submit 콜백 함수 */
    afterSubmit(response) {
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "비밀번호 변경이 완료되었습니다.",
        caption: "이용을 위해 로그인 해주세요."
      });
      this.$router.push({ path: "/layer/login" });
    }
  }
};
</script>

<template>
  <div class="column">
    <div class="q-ml-md q-mt-lg q-mb-md text-subtitle1 text-weight-bold">
      마이페이지
    </div>
    <q-form
      class="q-mx-md flex column q-gutter-y-sm"
      ref="mypageForm"
      @submit.prevent="onSubmit"
    >
      <q-input
        outlined
        dense
        v-model="memberName"
        label="닉네임"
        stack-label
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.minLength(3, '3자리 이상 입력해주세요.'),
          $rules.maxLength(20, '20자리 이하 입력해주세요.')
        ]"
      />
      <q-input
        v-if="!isSocialLogin"
        outlined
        dense
        :type="isPwd ? 'password' : 'text'"
        v-model="oldPassword"
        label="현재 비밀번호"
        stack-label
        reactive-rules
        class="q-pb-xs q-mb-md"
        :rules="passwordRules"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <q-input
        v-if="!isSocialLogin"
        outlined
        dense
        :type="isPwd ? 'password' : 'text'"
        v-model="newPassword"
        label="새 비밀번호"
        stack-label
        :rules="[
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
      <q-input
        v-if="!isSocialLogin"
        outlined
        dense
        :type="isPwd ? 'password' : 'text'"
        v-model="passwordCheck"
        label="새 비밀번호 확인"
        stack-label
        :rules="[
          $rules.sameAs(newPassword, '입력하신 비밀번호와 일치하지 않습니다.')
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
      <div class="q-my-lg fit">
        <q-btn
          unelevated
          rounded
          color="primary"
          label="변경하기"
          type="submit"
          class="fit"
        />
      </div>
    </q-form>
  </div>
</template>

<script>
import sha256 from "crypto-js/sha256";

export default {
  name: "PageMyPage",
  data() {
    return {
      isPwd: true,
      isSocialLogin: false,
      oldName: "asdf",
      memberName: "asdf",
      oldPassword: "",
      passwordRules: [],
      newPassword: "",
      passwordCheck: ""
    };
  },
  mounted() {
    this.getInfo();
  },
  watch: {
    memberName(newValue) {
      this.needPassword();
    },
    newPassword(newValue) {
      this.needPassword();
    }
  },
  methods: {
    /** 회원정보 호출 함수 */
    getInfo() {
      this.$store.commit("setLoading", { isLoading: true });
      this.$cf.call(
        process.env.API + "/api/auth/getinfo",
        { email: this.currentUser },
        this.infoCb,
        true
      );
    },
    /** 회원정보 콜백 함수 */
    infoCb(response) {
      this.oldName = response.mbrNm;
      this.memberName = response.mbrNm;
      this.isSocialLogin = response.snsLoginYn === "Y";
    },
    /** 비밀번호 입력 필요 판단 함수 */
    needPassword() {
      if (
        this.oldName !== this.memberName ||
        !this.$cf.isEmpty(this.newPassword)
      ) {
        this.passwordRules.push(
          this.$rules.required("필수 항목입니다."),
          this.$rules.alphaNum("영어 대문자, 소문자, 숫자 입력만 가능합니다."),
          this.$rules.minLength(10, "10-20자리 비밀번호를 입력해주세요."),
          this.$rules.maxLength(20, "10-20자리 비밀번호를 입력해주세요.")
        );
      } else {
        this.passwordRules = [];
        this.$refs.mypageForm.reset();
      }
    },
    /** 변경하기 버튼 클릭 이벤트 */
    onSubmit() {
      if (!this.$cf.isEmpty(this.oldPassword)) {
        this.$store.commit("setLoading", { isLoading: true });
        let param = {
          email: this.currentUser,
          oldPassword: sha256(this.oldPassword).toString()
        };
        if (this.oldName !== this.memberName) {
          param.memberName = this.memberName;
        }
        if (!this.$cf.isEmpty(this.newPassword)) {
          param.newPassword = sha256(this.newPassword).toString();
        }
        this.$cf.call(
          process.env.API + "/api/auth/update",
          param,
          this.submitCb,
          true
        );
      }
    },
    /** 변경하기 버튼 콜백 함수 */
    submitCb(response) {
      this.getInfo();
      this.oldPassword = "";
      this.newPassword = "";
      this.passwordCheck = "";
      this.$refs.mypageForm.reset();
      if (response.success === "Y") {
        this.$store.commit("setNotification", {
          color: "positive",
          textColor: "white",
          message: "변경사항이 저장되었습니다."
        });
      } else {
        this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "기존 비밀번호를 확인해주세요."
        });
      }
    }
  },
  computed: {
    /** 현재 로그인 한 사용자 */
    currentUser: {
      get() {
        // return this.$store.getters.currentUser;
        return "kimdevtest@naver.com";
      }
    }
  }
};
</script>

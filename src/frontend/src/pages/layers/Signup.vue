<template>
  <q-page class="flex column flex-center">
    <h5>회원가입</h5>
    <q-form
      class="flex column flex-center q-px-sm q-py-none fit"
      @submit.prevent="onSubmit"
    >
      <div class="q-px-md q-py-none fit">
        <span class="text-subtitle1">아이디</span>
        <span v-if="!isSocialId" class="q-ml-sm text-subtitle2 text-red-14"
          >*필수</span
        >
      </div>
      <q-input
        v-model="userId"
        type="email"
        placeholder="이메일"
        dense
        class="q-px-md q-py-sm fit"
        :readonly="isSocialId"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.email('올바른 이메일이 아닙니다.')
        ]"
        @keyup="onIdKeyup"
      >
        <template v-slot:append v-if="!isSocialId">
          <q-btn
            unelevated
            dense
            :color="idChkColor"
            :icon="idChkIcon"
            label="중복확인"
            @click="onIdChkClick"
          />
        </template>
      </q-input>
      <div class="flex column flex-center fit" v-if="!isSocialUser">
        <div class="q-px-md q-pt-md q-pb-none fit">
          <span class="text-subtitle1">비밀번호</span>
          <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
        </div>
        <q-input
          v-model="userPw"
          placeholder="비밀번호"
          :type="isPwd ? 'password' : 'text'"
          class="q-px-md q-py-sm fit"
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
        <q-input
          v-model="userPwChk"
          placeholder="비밀번호 확인"
          :type="isPwdChk ? 'password' : 'text'"
          class="q-px-md q-py-sm fit"
          :rules="[
            $rules.required('필수 항목입니다.'),
            $rules.sameAs(userPw, '입력하신 비밀번호와 일치하지 않습니다.')
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
      </div>
      <div class="q-px-md q-pt-md q-pb-none fit">
        <span class="text-subtitle1">닉네임</span>
        <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
      </div>
      <q-input
        v-model="userNm"
        placeholder="닉네임"
        dense
        class="q-px-md q-py-sm fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.minLength(3, '3자리 이상 입력해주세요.'),
          $rules.maxLength(20, '20자리 이하 입력해주세요.')
        ]"
      />

      <div class="q-px-md q-pt-md q-pb-none fit">
        <p class="text-subtitle1 no-padding no-margin">선호지역</p>
      </div>
      <div class="flex row flex-center q-gutter-x-md fit">
        <q-select
          outlined
          v-model="userSido"
          label="특별시도"
          :options="sidoList"
          option-value="cdVal"
          option-label="cdNm"
          class="q-mt-none q-pt-none col-5"
        />
        <q-select
          outlined
          v-model="userSigg"
          label="시군구"
          :options="siggList"
          option-value="cdVal"
          option-label="cdNm"
          class="q-mt-none q-pt-none col-5"
        />
      </div>

      <div class="flex column flex-center q-py-lg fit">
        <span>
          <q-checkbox
            v-model="trmAgree"
            label="서비스 이용약관 동의"
            color="primary"
          />
          <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
        </span>
        <q-checkbox
          v-model="mktAgree"
          label="마케팅 정보제공 동의"
          color="primary"
        />
      </div>
      <div class="q-px-md q-pb-xl full-width">
        <q-btn
          rounded
          unelevated
          class="full-width"
          color="primary"
          label="가입"
          type="submit"
        />
      </div>
    </q-form>
  </q-page>
</template>

<script>
import sha256 from "crypto-js/sha256";

export default {
  name: "LayerSignup",
  data() {
    return {
      /** 사용자 가입 데이터 */
      userId: "",
      isIdChk: false,
      userPw: "",
      userPwChk: "",
      userNm: "",
      userSido: "",
      userSigg: "",
      trmAgree: false,
      mktAgree: false,
      /** 화면 제어 데이터 */
      isSocialUser: false,
      isSocialId: false,
      checkedId: "",
      idChkIcon: "email",
      idChkColor: "warning",
      isPwd: true,
      isPwdChk: true,
      /** 화면 출력 데이터 */
      sidoList: [],
      siggList: [
        {
          cdVal: "",
          cdNm: "특별시도를 먼저 선택해주세요"
        }
      ]
    };
  },
  mounted() {
    this.getSido();
    if (!this.$cf.isEmpty(this.newSocialUser.id)) {
      this.isSocialUser = true;
    }
    if (!this.$cf.isEmpty(this.newSocialUser.email)) {
      this.isSocialId = true;
      this.userId = this.newSocialUser.email;
    }
    if (!this.$cf.isEmpty(this.newSocialUser.name)) {
      this.userNm = this.newSocialUser.name;
    }
  },
  watch: {
    userSido(newSido) {
      (this.userSigg = ""), this.getSigg(newSido.cdVal);
    }
  },
  methods: {
    /** 시도 list 호출 함수 */
    getSido() {
      this.$cf.call(
        process.env.API + "/api/common/sido",
        {},
        this.sidoCb,
        false
      );
    },
    /** 시도 list 콜백 함수 */
    sidoCb(response) {
      this.sidoList = response.sidoList;
    },
    /** 시군구 list 호출 함수 */
    getSigg(sidoCd) {
      this.$cf.call(
        process.env.API + "/api/common/sigg",
        { sidoCd: sidoCd },
        this.siggCb,
        true
      );
    },
    /** 시군구 list 콜백 함수 */
    siggCb(response) {
      this.siggList = response.siggList;
    },
    /** id값 변경 시 중복확인 완료된 email값과 대조 */
    onIdKeyup() {
      if (this.userId !== this.checkedId) {
        this.isIdChk = false;
        this.idChkIcon = "email";
        this.idChkColor = "warning";
      }
    },
    /** email 중복확인 click 이벤트 함수 */
    onIdChkClick() {
      if (!this.$cf.isEmpty(this.userId)) {
        this.$cf.call(
          process.env.API + "/api/auth/registered",
          { email: this.userId },
          this.afterIdChk,
          true
        );
      }
    },
    afterIdChk(response) {
      this.isIdChk = !response;
      if (!response) {
        this.idChkIcon = "check";
        this.idChkColor = "positive";
        this.$store.commit("setNotification", {
          color: "positive",
          textColor: "white",
          message: "이메일 중복확인이 완료되었습니다.",
          caption: ""
        });
      } else {
        this.idChkIcon = "error";
        this.idChkColor = "negative";
        this.$store.commit("setNotification", {
          color: "negative",
          textColor: "white",
          message: "이미 가입된 이메일입니다.",
          caption: "다른 이메일을 입력해주세요."
        });
      }
      this.checkedId = this.userId;
    },
    /** form submit 이벤트 함수 */
    onSubmit() {
      /** 아래 외 나머지 항목은 form 자체 validation 이용 */
      let warningItems = [];
      if (!this.isIdChk && !this.isSocialUser) {
        warningItems.push("이메일 중복확인");
      }
      if (!this.trmAgree) {
        warningItems.push("서비스 이용약관 동의");
      }
      if (warningItems.length !== 0) {
        let warningCaption = "";
        for (let n in warningItems) {
          if (parseInt(n, 10) === 0) {
            warningCaption += this.warningItems[n];
          } else {
            warningCaption += ", " + this.warningItems[n];
          }
        }
        this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "아래 항목을 완료해주세요.",
          caption: warningCaption
        });
        return;
      }

      let param = {
        memberId: this.userId,
        memberNickname: this.userNm,
        memberLocationSido: this.userSido.cdNm,
        memberLocationSigg: this.userSigg.cdNm,
        isMarketingAgreed: this.mktAgree ? "Y" : "N"
      };

      /** 일반, 소셜 가입 구분 */
      if (!this.isSocialUser) {
        param.passwordPin = sha256(this.userPw).toString();
        param.isSocialLogin = "N";
        param.isEmailAuthenticated = "N";
      } else {
        param.isSocialLogin = "Y";
        param.socialService = this.newSocialUser.service
          .slice(0, 3)
          .toUpperCase();
        param.socialId = this.newSocialUser.id;
        param.isEmailAuthenticated = "Y";
      }

      this.$cf.call(
        process.env.API + "/api/auth/signup",
        param,
        this.afterSubmit,
        true
      );
    },
    afterSubmit(response) {
      let msgPrefix = "";
      let cptPrefix = "";
      if (this.isSocialUser) {
        msgPrefix = "소셜로그인 ";
      } else {
        cptPrefix = "이메일 인증 후 ";
      }
      this.$router.push({ path: "/main" });
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: msgPrefix + "가입이 완료되었습니다.",
        caption: cptPrefix + "다시 로그인 해주세요."
      });
    }
  },
  computed: {
    newSocialUser: {
      get() {
        return this.$store.getters.newSocialUser;
      }
    }
  }
};
</script>

<template>
  <q-page class="flex column flex-center">
    <h5>회원가입</h5>
    <q-form
      class="flex column flex-center q-px-sm q-py-none fit"
      ref="joinForm"
      @submit.prevent="onSubmit"
    >
      <div class="q-px-lg q-py-none fit">
        <span class="text-subtitle1">아이디</span>
        <span v-if="!isSocialId" class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
      </div>
      <q-input
        v-model="userId"
        type="email"
        placeholder="이메일"
        dense
        class="q-px-lg q-py-sm fit"
        :readonly="isSocialId"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.email('올바른 이메일이 아닙니다.')
        ]"
        @keyup="onIdKeyup"
      >
        <template v-slot:append v-if="!isSocialId">
          <q-btn dense :color="idChkColor" :icon="idChkIcon" label="중복확인" @click="onIdChkClick" />
        </template>
      </q-input>
      <div class="flex column flex-center fit" v-if="!isSocialUser">
        <div class="q-px-lg q-pt-md q-pb-none fit">
          <span class="text-subtitle1">비밀번호</span>
          <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
        </div>
        <q-input
          v-model="userPw"
          placeholder="비밀번호"
          :type="isPwd ? 'password' : 'text'"
          class="q-px-lg q-py-sm fit"
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
          class="q-px-lg q-py-sm fit"
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
      <div class="q-px-lg q-pt-md q-pb-none fit">
        <span class="text-subtitle1">닉네임</span>
        <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
      </div>
      <q-input
        v-model="userNm"
        placeholder="닉네임"
        dense
        class="q-px-lg q-py-sm fit"
        :rules="[
          $rules.required('필수 항목입니다.'),
          $rules.minLength(3, '3자리 이상 입력해주세요.'),
          $rules.maxLength(20, '20자리 이하 입력해주세요.')
        ]"
      />

      <div class="q-px-lg q-pt-md q-pb-none fit">
        <p class="text-subtitle1 no-padding no-margin">선호지역</p>
      </div>
      <div class="flex row flex-center q-gutter-x-md fit">
        <q-select
          v-model="userSido"
          :options="sidoList"
          label="특별시도"
          class="q-mt-none q-pt-none col-5"
        />
        <q-select
          v-model="userSigg"
          :options="siggList"
          label="시군구"
          class="q-mt-none q-pt-none col-5"
        />
      </div>

      <div class="flex column flex-center q-py-lg fit">
        <span>
          <q-checkbox v-model="trmAgree" label="서비스 이용약관 동의" color="primary" />
          <span class="q-ml-sm text-subtitle2 text-red-14">*필수</span>
        </span>
        <q-checkbox v-model="mktAgree" label="마케팅 정보제공 동의" color="primary" />
      </div>
      <div class="q-pb-xl">
        <q-btn color="primary" label="가입" type="submit" />
      </div>
    </q-form>
  </q-page>
</template>

<script>
export default {
  name: "PageSignup",
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
      sidoList: ["서울시", "경기도", "부산시"],
      siggList: ["중구", "노원구", "서대문구", "마포구", "용산구"],
    };
  },
  mounted() {
    if (this.newSocialUser.id !== "") {
      this.isSocialUser = true;
    }
    if (
      this.newSocialUser.email !== "" &&
      this.newSocialUser.email !== "null"
    ) {
      this.isSocialId = true;
      this.userId = this.newSocialUser.email;
    }
    if (this.newSocialUser.name !== "" && this.newSocialUser.name !== "null") {
      this.userNm = this.newSocialUser.name;
    }
  },
  methods: {
    /** 페이지 공통 notify */
    signupNotify(clr, msg, cpt) {
      this.$q.notify({
        group: false,
        color: clr,
        message: msg,
        caption: cpt,
      });
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
      let self = this;
      if (self.userId !== "") {
        self.$axios
          .post("/api/user/registered", {
            email: self.userId,
          })
          .then(function (response) {
            self.isIdChk = !response.data;
            if (!response.data) {
              self.idChkIcon = "check";
              self.idChkColor = "positive";
              self.signupNotify(
                "positive",
                "이메일 중복확인이 완료되었습니다."
              );
            } else {
              self.idChkIcon = "error";
              self.idChkColor = "negative";
              self.signupNotify(
                "negative",
                "이미 가입된 이메일입니다.",
                "다른 이메일을 입력해주세요."
              );
            }
            self.checkedId = self.userId;
          })
          .catch(function (error) {
            console.log(error);
            self.signupNotify(
              "negative",
              "오류가 발생했습니다.",
              "관리자에게 문의하세요."
            );
          });
      }
    },
    /** form submit 이벤트 함수 */
    onSubmit() {
      let self = this;
      let param = {};

      /** 아래 외 나머지 항목은 form 자체 validation 이용 */
      if (!self.isIdChk && !self.isSocialUser) {
        self.signupNotify("warning", "이메일 중복확인을 완료해주세요.");
        return;
      }
      if (!self.trmAgree) {
        self.signupNotify("warning", "서비스 이용약관에 동의해주세요.");
        return;
      }

      param = {
        memberId: self.userId,
        memberNickname: self.userNm,
        memberLocationSido: self.userSido,
        memberLocationSigg: self.userSigg,
        isMarketingAgreed: self.mktAgree ? "Y" : "N",
      };

      /** 일반, 소셜 가입 구분 */
      if (!self.isSocialUser) {
        param.passwordPin = self.userPw;
      } else {
        param.isSocialLogin = "Y";
        param.socialService = self.newSocialUser.service
          .slice(0, 3)
          .toUpperCase();
        param.socialId = self.newSocialUser.id;
        param.isEmailAuthenticated = "Y";
      }

      console.log(param);

      self.$axios
        .post("/api/user/signup", param)
        .then(function (response) {
          console.log(response.data);
          let msgPrefix = "";
          let cptPrefix = "";
          if (self.isSocialUser) {
            msgPrefix = "소셜로그인 ";
          } else {
            cptPrefix = "이메일 인증 후 ";
          }
          self.$router.push({ path: "main" });
          self.signupNotify(
            "positive",
            msgPrefix + "가입이 완료되었습니다.",
            cptPrefix + "다시 로그인 해주세요."
          );
        })
        .catch(function (error) {
          console.log(error);
          self.signupNotify(
            "negative",
            "오류가 발생했습니다.",
            "관리자에게 문의하세요."
          );
        });
    },
  },
  computed: {
    newSocialUser: {
      get() {
        return this.$store.getters.newSocialUser;
      },
    },
  },
};
</script>

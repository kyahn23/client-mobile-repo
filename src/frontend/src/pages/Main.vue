<template>
  <div class="q-gutter-lg q-mt-md">
    <div class="row q-gutter-md q-mr-md" style="height: 90px;">
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="blue-13"
          label="로그인"
          to="/layer/login"
        />
      </div>
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="blue-12"
          label="회원가입"
          to="/layer/signup"
        />
      </div>
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="blue-11"
          label="최저가 조회"
          to="/price"
        />
      </div>
    </div>
    <div class="row q-gutter-md q-mr-md" style="height: 90px;">
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="light-blue-13"
          label="상담등록"
          to="/register"
        />
      </div>
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="light-blue-12"
          label="상담현황"
          to="/status"
        />
      </div>
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="light-blue-11"
          label="고객센터"
          to="/customer"
        />
      </div>
    </div>
    <div class="row q-gutter-md q-mr-md" style="height: 90px;">
      <div class="column col">
        <q-btn
          push
          class="fit"
          color="cyan-13"
          label="마이페이지"
          to="/mypage"
        />
      </div>
      <div class="column col"></div>
      <div class="column col"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PageMain"
};
</script>

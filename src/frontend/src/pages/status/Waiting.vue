<template>
  <div>
    <q-card flat>
      <q-card-section horizontal class="q-ml-md q-my-sm">
        <q-img
          class="col-4"
          style="max-width: 100px;"
          src="/images/phone_thumbnail.jpg"
          contain
        />
        <q-card-section class="self-center full-width q-pl-sm">
          <div class="row">
            <div class="col text-subtitle1 text-weight-bold">
              삼성 갤럭시 S20
            </div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">통신사</div>
            <div class="col">SKT</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">가입유형</div>
            <div class="col">번호이동</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">요금제</div>
            <div class="col">5GX 플래티넘</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">예상개통일</div>
            <div class="col">2020-11-11</div>
          </div>
        </q-card-section>
      </q-card-section>
      <q-card-section class="q-py-none bg-blue-2" horizontal>
        <q-card-section class="q-py-none text-caption text-weight-bold">
          기타 요청사항
        </q-card-section>
        <q-card-section class="q-py-none text-caption">
          색상 블랙 / 전화는 오후 3시 이후
        </q-card-section>
      </q-card-section>
    </q-card>
    <q-tabs
      dense
      align="justify"
      class="bg-white text-black full-width"
      active-color="primary"
    >
      <q-route-tab label="상담진행" to="/ongoing/100000000001" />
      <q-route-tab label="상담대기" to="/waiting/100000000001" />
    </q-tabs>
    <q-page-container>
      <router-view />
    </q-page-container>
  </div>
</template>

<script>
export default {
  name: "Waiting",
  props: {
    dealno: {
      type: String,
      required: true
    }
  },
  mounted() {}
};
</script>

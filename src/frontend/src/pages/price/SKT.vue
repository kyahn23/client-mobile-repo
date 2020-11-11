<template>
  <div class="q-pa-md q-my-lg q-pt-lg">
    <div id="top">
        <!-- Your content goes here -->
        <div class="full-width">
          <div class="row q-mb-sm">
            <div class="col-3 text-weight-bold self-center"><span>가입유형</span></div>
            <div class="col-9 self-center">
<!--                <q-option-group-->
<!--                  size="xs"-->
<!--                  v-model="signType"-->
<!--                  :options="signOpts"-->
<!--                  color="primary"-->
<!--                  inline-->
<!--                  dense>-->
<!--                </q-option-group>-->
              <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs" v-model="signType" val="newSign" label="신규가입"></q-radio>
              <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs" v-model="signType" val="moveCarr" label="번호이동"></q-radio>
              <q-radio class="text-caption" style="width: 32%" dense size="xs" v-model="signType" val="chgDev" label="기기변경"></q-radio>
              </div>
          </div>
          <div class="row q-mb-sm">
            <span class="col-3 text-weight-bold self-center">제조사</span>
            <q-select
              class="col-9 self-center"
              outlined
              v-model="pnMkr"
              :options="pnMkrList"
              dense
              emit-value
              map-options
            >
            </q-select>
          </div>
          <div class="row q-mb-sm">
            <span class="col-3 text-weight-bold self-center">모델선택</span>
            <q-select
              class="col-9 self-center"
              outlined
              v-model="pnMkr"
              :options="pnMkrList"
              dense
              emit-value
              map-options
            >
            </q-select>
          </div>
          <div class="row q-mb-sm">
            <span class="col-3 text-weight-bold self-center">요금제</span>
            <q-select
              class="col-9 self-center"
              outlined
              v-model="pnMkr"
              :options="pnMkrList"
              dense
              emit-value
              map-options
            >
            </q-select>
          </div>
          <div class="row q-mb-sm">
            <span class="col-3 text-weight-bold self-center">기간입력</span>
            <q-select
              class="col-9 self-center"
              outlined
              v-model="pnMkr"
              :options="pnMkrList"
              dense
              emit-value
              map-options
            >
            </q-select>
          </div>
          <div class="text-center q-mb-md">
            <q-btn unelevated color="primary" label="검색" size="md" class="q-px-lg q-py-xs"/>
          </div>
        </div>
        <div class="q-mb-xs">
          <q-btn outline color="white" text-color="black" rounded size="sm" class="q-px-xs q-mr-xs q-py-xs" label="전체"/>
          <q-btn outline color="primary" rounded size="sm" class="q-px-xs q-py-xs" label="SKT 최저가"/>
        </div>
        <div class="q-pa-xs q-mb-sm">
          <q-table class="no-shadow no-outline" :data="priceList" :columns="columns" :hide-bottom="true" bordered>
          </q-table>
        </div>
        <div>
          <q-btn unelevated class="full-width bg-blue-1 no-border text-blue-6 text-weight-bold" label="더보기 +"></q-btn>
        </div>
        <div
          id="scroll-top"
          class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect"
          @click="scrollTop"
        >
          <i class="material-icons">arrow_upward</i>
        </div>
    </div>
  </div>
</template>

<script>
import { scroll } from "quasar";
const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "SKT",
  data() {
    return {
      signType: null,
      signOpts: [
        {
          label: "신규가입",
          value: "newSign"
        },
        {
          label: "번호이동",
          value: "moveCarr"
        },
        {
          label: "기기변경",
          value: "chgDev"
        }
      ],
      pnMkr: "all",
      pnMkrList: [
        {
          label: "선택",
          value: "all"
        },
        {
          label: "삼성전자",
          value: "SAM"
        },
        {
          label: "LG전자",
          value: "LGE"
        },
        {
          label: "APPLE",
          value: "APP"
        },
        {
          label: "샤오미",
          value: "XIA"
        }
      ],
      selectedMdl: "all",
      mdlList: [],
      columns: [
        {
          name: "priceDate",
          required: true,
          label: "일자",
          align: "center",
          field: row => row.priceDate,
          format: val => `${val}`,
          headerClasses: "bg-blue-2 text-dark text-weight-bold"
        },
        {
          name: "ofclDv",
          align: "center",
          label: "공시지원금",
          field: "ofclDv",
          headerClasses: "bg-blue-2 text-dark text-weight-bold"
        },
        {
          name: "ofclRt",
          align: "center",
          label: "요금할인",
          field: "ofclRt",
          headerClasses: "bg-blue-2 text-dark text-weight-bold"
        }
      ],
      priceList: [
        { priceDate: "20.11.01", ofclDv: "370000원", ofclRt: "610000원" },
        { priceDate: "20.11.02", ofclDv: "320000원", ofclRt: "620000원" },
        { priceDate: "20.11.03", ofclDv: "310000원", ofclRt: "630000원" },
        { priceDate: "20.11.04", ofclDv: "350000원", ofclRt: "640000원" },
        { priceDate: "20.11.05", ofclDv: "120000원", ofclRt: "650000원" },
        { priceDate: "20.11.06", ofclDv: "230000원", ofclRt: "670000원" }
      ]
    };
  },
  methods: {
    scrollTop() {
      const ele = document.getElementById("top");
      const target = getScrollTarget(ele);
      const offset = ele.offsetTop - 130;
      const duration = 100;
      setScrollPosition(target, offset, duration);
    }
  }
};
</script>
<style>
.q-radio--dense .q-radio__label {
  padding-left: .4rem;
}
</style>

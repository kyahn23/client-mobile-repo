<template>
  <div class="q-pa-md q-mt-lg q-pt-lg">
    <div id="top">
      <!-- Your content goes here -->
      <div class="full-width">
        <div class="row q-mb-sm">
          <div class="col-3 text-weight-bold self-center"><span>가입유형</span></div>
          <div class="col-9 self-center">
            <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs" v-model="signType" val="newSign"
                     label="신규가입"></q-radio>
            <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs" v-model="signType" val="moveCarr"
                     label="번호이동"></q-radio>
            <q-radio class="text-caption" style="width: 32%" dense size="xs" v-model="signType" val="chgDev"
                     label="기기변경"></q-radio>
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
            v-model="selected"
            :options="phoneList"
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
            v-model="selectedMntRt"
            :options="mntRtList"
            dense
            emit-value
            map-options
          >
          </q-select>
        </div>
        <div class="row q-mb-sm">
          <span class="col-3 text-weight-bold self-center">기간선택</span>
          <q-select
            class="col-9 self-center"
            outlined
            v-model="searchPeriod"
            :options="period"
            dense
            emit-value
            map-options
          >
          </q-select>
        </div>
        <div class="text-center q-mb-md">
          <q-btn unelevated color="primary" label="검색" size="md" class="q-px-lg q-py-xs" @click="searchValidate"/>
        </div>
      </div>
      <!--      <div class="q-mb-xs">-->
      <!--        <q-btn outline color="white" text-color="black" rounded size="sm" class="q-px-xs q-mr-xs q-py-xs" label="전체"/>-->
      <!--        <q-btn outline color="primary" rounded size="sm" class="q-px-xs q-py-xs" label="SKT 최저가"/>-->
      <!--      </div>-->
      <div class="q-pa-xs q-mb-sm" v-if="priceList.length !== 0">
        <q-table class="no-shadow no-outline" :data="priceList" :columns="columns" :hide-bottom="true"
                 :pagination.sync="pricePagination">
        </q-table>
      </div>
      <div v-if="priceList.length > 10 && pricePagination.rowsPerPage !== 15">
        <q-btn unelevated class="full-width bg-blue-1 no-border text-blue-6 text-weight-bold" label="더보기 +"
               @click="moreBtn"></q-btn>
      </div>
      <div id="scroll-top" @click="scrollTop" class="q-mb-lg q-mr-md"
           style="position: fixed; display: block; right: 0; bottom: 0;">
        <q-btn class="bg-grey-1 q-mb-xl" text-color="black" round size="md">
          <i class="material-icons">arrow_upward</i>
        </q-btn>
      </div>
    </div>
  </div>
</template>

<script>
import {scroll} from "quasar";

const {getScrollTarget, setScrollPosition} = scroll;

export default {
  name: "KT",
  data() {
    return {
      pricePagination: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 7
      },
      carr: "S",
      signType: null,
      signOpts: [
        {label: "신규가입", value: "newSign"},
        {label: "번호이동", value: "moveCarr"},
        {label: "기기변경", value: "chgDev"}
      ],
      pnMkr: "all",
      pnMkrList: [
        {label: "선택", value: "all"},
        {label: "삼성전자", value: "SAM"},
        {label: "LG전자", value: "LGE"},
        {label: "애플", value: "APP"},
        {label: "샤오미", value: "XIA"}
      ],
      searchPeriod: 'all',
      period: [
        {label: '선택', value: 'all'},
        {label: '1일', value: 1},
        {label: '3일', value: 3},
        {label: '7일', value: 7},
        {label: '15일', value: 15},
      ],
      selected: {label: '선택', pnMdlNo: 'all'},
      phoneList: [],
      selectedMntRt: {label: '선택', pnMntRtNo: 'all'},
      mntRtList: [],
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
        // {priceDate: "20.11.01", ofclDv: "370000원", ofclRt: "610000원"},
        // {priceDate: "20.11.02", ofclDv: "320000원", ofclRt: "620000원"},
        // {priceDate: "20.11.03", ofclDv: "310000원", ofclRt: "630000원"},
        // {priceDate: "20.11.04", ofclDv: "350000원", ofclRt: "640000원"},
        // {priceDate: "20.11.05", ofclDv: "120000원", ofclRt: "650000원"},
        // {priceDate: "20.11.06", ofclDv: "230000원", ofclRt: "670000원"},
        // {priceDate: "20.11.07", ofclDv: "230000원", ofclRt: "670000원"},
        // {priceDate: "20.11.08", ofclDv: "340000원", ofclRt: "670000원"},
        // {priceDate: "20.11.09", ofclDv: "450000원", ofclRt: "670000원"},
        // {priceDate: "20.11.10", ofclDv: "120000원", ofclRt: "670000원"},
        // {priceDate: "20.11.11", ofclDv: "500000원", ofclRt: "670000원"},
        // {priceDate: "20.11.12", ofclDv: "210000원", ofclRt: "670000원"},
        // {priceDate: "20.11.13", ofclDv: "220000원", ofclRt: "670000원"},
        // {priceDate: "20.11.14", ofclDv: "220000원", ofclRt: "670000원"},
        // {priceDate: "20.11.15", ofclDv: "220000원", ofclRt: "670000원"}
      ],
      tempList: [
        {priceDate: "20.11.01", ofclDv: "370000원", ofclRt: "610000원"},
        {priceDate: "20.11.02", ofclDv: "320000원", ofclRt: "620000원"},
        {priceDate: "20.11.03", ofclDv: "310000원", ofclRt: "630000원"},
        {priceDate: "20.11.04", ofclDv: "350000원", ofclRt: "640000원"},
        {priceDate: "20.11.05", ofclDv: "120000원", ofclRt: "650000원"},
        {priceDate: "20.11.06", ofclDv: "230000원", ofclRt: "670000원"},
        {priceDate: "20.11.07", ofclDv: "230000원", ofclRt: "670000원"},
        {priceDate: "20.11.08", ofclDv: "340000원", ofclRt: "670000원"},
        {priceDate: "20.11.09", ofclDv: "450000원", ofclRt: "670000원"},
        {priceDate: "20.11.10", ofclDv: "120000원", ofclRt: "670000원"},
        {priceDate: "20.11.11", ofclDv: "500000원", ofclRt: "670000원"},
        {priceDate: "20.11.12", ofclDv: "210000원", ofclRt: "670000원"},
        {priceDate: "20.11.13", ofclDv: "220000원", ofclRt: "670000원"},
        {priceDate: "20.11.14", ofclDv: "220000원", ofclRt: "670000원"},
        {priceDate: "20.11.15", ofclDv: "220000원", ofclRt: "670000원"}
      ]
    };
  },
  watch: {
    pnMkr: function (newValue, oldValue) {
      // console.log(newValue)
      if (newValue !== oldValue) {
        this.selected = {
          label: '선택',
          pnMdlNo: 'all'
        }
        this.phoneList = []
        this.mntRtList = []
        this.getPhoneList(newValue)
      }
    },
    'selected.pnMdlNo': function (newValue, oldValue) {
      if (newValue !== oldValue) {
        this.selectedMntRt = {
          label: '선택',
          pnMntRtNo: 'all'
        }
        this.mntRtList = []
        this.getMntRtList(newValue)
      }
    }
  },
  methods: {
    scrollTop() {
      const ele = document.getElementById("top");
      const target = getScrollTarget(ele);
      const offset = ele.offsetTop - 130;
      const duration = 100;
      setScrollPosition(target, offset, duration);
    },
    getPhoneList(val) {
      const pnMkr = val
      let param = {
        mntCarr: this.carr,
        pnMkr: this.pnMkr
      }
      this.$cf.call(
        process.env.API + "/api/price/phoneList",
        param,
        this.getPhoneListCB,
        true
      )
    },
    getPhoneListCB(response) {
      const obj = {
        label: '선택',
        pnMdlNo: 'all'
      }
      this.phoneList.push(obj)
      for (let n in response.phoneList) {
        this.phoneList.push(response.phoneList[n])
      }
    },
    getMntRtList() {
      let carr = ''
      switch (this.carr) {
        case "S":
          carr = 'SKT'
          break
        case "K":
          carr = 'KT'
          break
        case "L":
          carr = 'LGU'
          break
      }
      let param = {
        mntCarr: carr,
        pnNetType: this.selected.pnNetType
      }
      this.$cf.call(
        process.env.API + "/api/price/mntRtList",
        param,
        this.getMntRtListCB,
        true
      )
    },
    getMntRtListCB(response) {
      const obj = {
        label: '선택',
        pnMntRtNo: 'all'
      }
      this.mntRtList.push(obj)
      for (let n in response.mntRtList) {
        response.mntRtList[n].label = response.mntRtList[n].pnMntRtNm.concat(" ", Number(response.mntRtList[n].pnMntAmt).toLocaleString(), "원")
        this.mntRtList.push(response.mntRtList[n])
      }
    },
    searchValidate() {
      let valChk = false
      if (this.signType === null) {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "가입유형을 선택해주세요."
        });
        valChk = true
      } else if (this.pnMkr === 'all') {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "제조사를 선택해주세요."
        });
        valChk = true
      } else if (this.selected.pnMdlNo === 'all') {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "스마트폰을 선택해주세요."
        });
        valChk = true
      } else if (this.selectedMntRt.pnMntRtNo === 'all') {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "요금제를 선택해주세요."
        });
        valChk = true
      } else if (this.searchPeriod === 'all') {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "기간을 선택해주세요."
        });
        valChk = true
      }
      if (!valChk) {
        this.searchPrice()
      }
    },
    searchPrice() {
      this.priceList = this.tempList
      let param = {
        signType: this.signType,
        pnMkr: this.pnMkr,
        pnMdlNo: this.selected.pnMdlNo,
        pnStor: this.selected.pnStor,
        pnMntRtNo: this.selectedMntRt.pnMntRtNo,
        searchPeriod: this.searchPeriod
      }
      // this.$cf.call(
      //   process.env.API + "/api/price/최저가검색",
      //   param,
      //   this.searchPriceCB,
      //   true
      // )
    },
    searchPriceCB(response) {
    },
    moreBtn() {
      this.pricePagination.rowsPerPage = 15
    }
  }
};
</script>
<style>
.q-radio--dense .q-radio__label {
  padding-left: .3rem;
}

table.q-table {
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
}
</style>

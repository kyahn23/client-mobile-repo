<template>
  <div class="q-pa-md q-mt-lg q-pt-lg">
    <div id="top">
      <!-- Your content goes here -->
      <div class="full-width">
        <div class="row justify-center q-mt-sm q-mb-lg">
          <q-img :src="pnMdlImg" style="width: 128px;" />
        </div>
        <div class="row q-mb-sm">
          <div class="col-3 text-weight-bold self-center">
            <span>가입유형</span>
          </div>
          <div class="col-9 self-center">
            <q-radio
              class="text-caption q-mr-xs"
              style="width: 32%"
              dense
              size="xs"
              v-model="signType"
              val="newSign"
              label="신규가입"
            ></q-radio>
            <q-radio
              class="text-caption q-mr-xs"
              style="width: 32%"
              dense
              size="xs"
              v-model="signType"
              val="moveCarr"
              label="번호이동"
            ></q-radio>
            <q-radio
              class="text-caption"
              style="width: 32%"
              dense
              size="xs"
              v-model="signType"
              val="chgDev"
              label="기기변경"
            ></q-radio>
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
          <q-btn
            unelevated
            color="primary"
            label="검색"
            size="md"
            class="q-px-lg q-py-xs"
            @click="searchValidate"
          />
        </div>
      </div>
      <div class="q-py-lg" v-if="priceSearched && priceList.length === 0">
        <q-item>
          <q-item-section class="text-grey-5">
            <q-item-label class="self-center">
              <q-icon name="error" size="xl" />
            </q-item-label>
            <q-item-label class="q-pt-sm self-center text-subtitle1">
              해당 상품의 최저가가 없습니다
            </q-item-label>
          </q-item-section>
        </q-item>
      </div>
      <div class="q-pa-xs q-mb-sm" v-if="priceList.length !== 0">
        <q-table
          class="no-shadow no-outline"
          :data="priceList"
          :columns="columns"
          :hide-bottom="true"
          :pagination.sync="pricePagination"
        >
        </q-table>
      </div>
      <div v-if="priceList.length > 10 && pricePagination.rowsPerPage !== 15">
        <q-btn
          unelevated
          class="full-width bg-blue-1 no-border text-blue-6 text-weight-bold"
          label="더보기 +"
          @click="moreBtn"
        ></q-btn>
      </div>
      <div
        id="scroll-top"
        @click="scrollTop"
        class="q-mb-lg q-mr-md"
        style="position: fixed; display: block; right: 0; bottom: 0;"
      >
        <q-btn class="bg-grey-1 q-mb-xl" text-color="black" round size="md">
          <i class="material-icons">arrow_upward</i>
        </q-btn>
      </div>
    </div>
  </div>
</template>

<script>
import { scroll, date } from "quasar";

const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "PagePriceSearch",
  props: {
    carrier: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      pricePagination: {
        sortBy: "desc",
        descending: false,
        page: 1,
        rowsPerPage: 7
      },
      signType: null,
      signOpts: [
        { label: "신규가입", value: "newSign" },
        { label: "번호이동", value: "moveCarr" },
        { label: "기기변경", value: "chgDev" }
      ],
      pnMkr: "all",
      pnMkrList: [
        { label: "선택", value: "all" },
        { label: "삼성전자", value: "SAM" },
        { label: "LG전자", value: "LGE" },
        { label: "애플", value: "APP" },
        { label: "샤오미", value: "XIA" }
      ],
      searchPeriod: "all",
      period: [
        { label: "선택", value: "all" },
        { label: "1일", value: 1 },
        { label: "3일", value: 3 },
        { label: "7일", value: 7 },
        { label: "15일", value: 15 }
      ],
      selected: { label: "선택", pnMdlNo: "all" },
      pnMdlImg: "images/phone_search.png",
      phoneList: [],
      selectedMntRt: { label: "선택", pnMntRtNo: "all" },
      mntRtList: [],
      priceSearched: false,
      columns: [
        {
          name: "priceDate",
          required: true,
          label: "일자",
          align: "center",
          field: row => row.priceDate,
          format: val => `${val}`,
          headerClasses: "bg-blue-1 text-dark text-weight-bold"
        },
        {
          name: "pnSubsdDv",
          align: "center",
          label: "공시지원금",
          field: "pnSubsdDv",
          headerClasses: "bg-blue-1 text-dark text-weight-bold"
        },
        {
          name: "pnSubsdRt",
          align: "center",
          label: "요금할인",
          field: "pnSubsdRt",
          headerClasses: "bg-blue-1 text-dark text-weight-bold"
        }
      ],
      priceList: []
    };
  },
  watch: {
    carrier: function(newValue, oldValue) {
      if (newValue !== oldValue) {
        this.pricePagination = {
          sortBy: "desc",
          descending: false,
          page: 1,
          rowsPerPage: 7
        };
        this.signType = null;
        this.pnMkr = "all";
        this.searchPeriod = "all";
        this.selected = { label: "선택", pnMdlNo: "all" };
        this.phoneList = [];
        this.selectedMntRt = { label: "선택", pnMntRtNo: "all" };
        this.mntRtList = [];
        this.priceSearched = false;
        this.priceList = [];
      }
    },
    pnMkr: function(newValue, oldValue) {
      if (newValue !== oldValue) {
        this.selected = {
          label: "선택",
          pnMdlNo: "all"
        };
        this.phoneList = [];
        this.mntRtList = [];
        this.getPhoneList(newValue);
      }
    },
    "selected.pnMdlNo": function(newValue, oldValue) {
      if (newValue !== oldValue) {
        if (
          newValue !== undefined &&
          newValue !== null &&
          newValue !== "" &&
          newValue !== "all"
        ) {
          this.pnMdlImg = this.$cf.imagePath(this.selected.pnImg);
        } else {
          this.pnMdlImg = "images/phone_search.png";
        }
        this.selectedMntRt = {
          label: "선택",
          pnMntRtNo: "all"
        };
        this.mntRtList = [];
        this.getMntRtList(newValue);
      }
    }
  },
  computed: {
    /** 통신사 변수 */
    carr() {
      return this.carrier.charAt(0);
    },
    carrLong() {
      return this.carrier;
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
      const pnMkr = val;
      let param = {
        mntCarr: this.carr,
        pnMkr: this.pnMkr
      };
      this.$cf.call(
        process.env.API + "/api/price/phoneList",
        param,
        this.getPhoneListCB,
        true
      );
    },
    getPhoneListCB(response) {
      const obj = {
        label: "선택",
        pnMdlNo: "all"
      };
      this.phoneList.push(obj);
      for (let n in response.phoneList) {
        this.phoneList.push(response.phoneList[n]);
      }
    },
    getMntRtList() {
      let param = {
        mntCarr: this.carrLong,
        pnNetType: this.selected.pnNetType
      };
      this.$cf.call(
        process.env.API + "/api/price/mntRtList",
        param,
        this.getMntRtListCB,
        true
      );
    },
    getMntRtListCB(response) {
      const obj = {
        label: "선택",
        pnMntRtNo: "all"
      };
      this.mntRtList.push(obj);
      for (let n in response.mntRtList) {
        response.mntRtList[n].label = response.mntRtList[n].pnMntRtNm.concat(
          " ",
          Number(response.mntRtList[n].pnMntAmt).toLocaleString(),
          "원"
        );
        this.mntRtList.push(response.mntRtList[n]);
      }
    },
    searchValidate() {
      let valChk = false;
      if (this.signType === null) {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "가입유형을 선택해주세요."
        });
        valChk = true;
      } else if (this.pnMkr === "all") {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "제조사를 선택해주세요."
        });
        valChk = true;
      } else if (this.selected.pnMdlNo === "all") {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "스마트폰을 선택해주세요."
        });
        valChk = true;
      } else if (this.selectedMntRt.pnMntRtNo === "all") {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "요금제를 선택해주세요."
        });
        valChk = true;
      } else if (this.searchPeriod === "all") {
        this.$store.commit("setNotification", {
          color: "grey-8",
          textColor: "white",
          message: "기간을 선택해주세요."
        });
        valChk = true;
      }
      if (!valChk) {
        this.searchPrice();
      }
    },
    searchPrice() {
      // this.priceList = this.tempList

      let curDate = new Date();
      curDate.setDate(curDate.getDate() - this.searchPeriod);
      let sinceDate = "";
      sinceDate = date.formatDate(curDate, "YYYYMMDD");

      let param = {
        lpCarr: this.carrLong,
        signType: this.signType,
        pnMkr: this.pnMkr,
        pnMdlNo: this.selected.pnMdlNo,
        pnStor: this.selected.pnStor,
        pnMntRtNo: this.selectedMntRt.pnMntRtNo,
        sinceDt: sinceDate
      };
      this.$cf.call(
        process.env.API + "/api/price/priceList",
        param,
        this.searchPriceCB,
        true
      );
    },
    searchPriceCB(response) {
      this.priceSearched = true;
      for (let n in response.priceList) {
        response.priceList[n].priceDate = date.formatDate(
          response.priceList[n].inpDt,
          "YY.MM.DD"
        );
        response.priceList[n].pnSubsdDv = Number(
          response.priceList[n].pnSubsdDv
        )
          .toLocaleString()
          .concat("원");
        response.priceList[n].pnSubsdRt = Number(
          response.priceList[n].pnSubsdRt
        )
          .toLocaleString()
          .concat("원");
      }
      this.priceList = response.priceList;
    },
    moreBtn() {
      this.pricePagination.rowsPerPage = 15;
    }
  }
};
</script>
<style>
.q-radio--dense .q-radio__label {
  padding-left: 0.3rem;
}

table.q-table {
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
}
</style>

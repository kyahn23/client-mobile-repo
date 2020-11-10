<template>
  <q-page class="flex flex-center">
    <main class="mdl-layout__content">
      <div id="top">
        <div class="page-content">
          <!-- Your content goes here -->
          <div class="pentas-layout__price-search-box">
            <div class="pentas-layout__price-search-row">
              <span class="pentas-layout__price-search-label">가입유형</span>
              <span class="pentas-layout__price-search-radio">
                <q-option-group v-model="signType"
                                :options="signOpts"
                                color="primary"
                                inline
                                dense>
                </q-option-group>
              </span>
            </div>
            <div class="pentas-layout__price-search-row">
              <span class="pentas-layout__price-search-label">제조사</span>
              <q-select class="pentas-layout__price-search-select" outlined v-model="pnMkr" :options="pnMkrList" dense emit-value map-options>
              </q-select>
            </div>
            <div class="pentas-layout__price-search-row">
              <span class="pentas-layout__price-search-label">모델선택</span>
              <q-select class="pentas-layout__price-search-select" outlined v-model="pnMkr" :options="pnMkrList" dense emit-value map-options>
              </q-select>
            </div>
            <div class="pentas-layout__price-search-row">
              <span class="pentas-layout__price-search-label">요금제</span>
              <q-select class="pentas-layout__price-search-select" outlined v-model="pnMkr" :options="pnMkrList" dense emit-value map-options>
              </q-select>
            </div>
            <div class="pentas-layout__price-search-row">
              <span class="pentas-layout__price-search-label">기간입력</span>
              <q-select class="pentas-layout__price-search-select" outlined v-model="pnMkr" :options="pnMkrList" dense emit-value map-options>
              </q-select>
            </div>
            <div class="pentas-layout__price-search-row">
              <button class="mdl-button mdl-js-button mdl-js-ripple-effect pentas-button__search">
                검색
              </button>
            </div>
          </div>
          <div class="pentas-layout__price-chip-box">
            <span class="mdl-chip pentas-chip-all">
              <span class="mdl-chip__text">전체</span>
            </span>
            <span class="mdl-chip pentas-chip-one q-ml-sm">
              <span class="mdl-chip__text">SKT 최저가</span>
            </span>
          </div>
          <div class="pentas-layout__price-table-box q-pa-md">
            <q-table :data="priceList"
                     :columns="columns"
                     :hide-bottom="true">
            </q-table>
<!--            <table class="mdl-data-table mdl-js-data-table pentas-layout__price-table-self">-->
<!--              <thead>-->
<!--              <tr>-->
<!--                <th class="mdl-data-table__cell&#45;&#45;non-numeric">일자</th>-->
<!--                <th class="mdl-data-table__cell&#45;&#45;non-numeric">공시지원금</th>-->
<!--                <th class="mdl-data-table__cell&#45;&#45;non-numeric">요금할인</th>-->
<!--              </tr>-->
<!--              </thead>-->
<!--              <tbody>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.10</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.09</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.08</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.07</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.06</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.05</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.04</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              <tr>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric">20.09.03</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">30,000원</td>-->
<!--                <td class="mdl-data-table__cell&#45;&#45;non-numeric pentas-layout__price-table-num-col">10,000원</td>-->
<!--              </tr>-->
<!--              </tbody>-->
<!--            </table>-->
          </div>
          <div class="pentas-layout__price-see-more">
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect pentas-button__see-more">
              더보기 +
            </button>
          </div>
          <div id="scroll-top" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect" @click="scrollTop">
            <i class="material-icons">arrow_upward</i>
          </div>
        </div>
      </div>
    </main>
  </q-page>
</template>

<script>
export default {
  name: "LGU",
  data(){
    return{
      signType: null,
      signOpts: [
      {
        label:'신규가입',
        value:'newSign'
      },
      {
        label:'번호이동',
        value:'moveCarr'
      },
      {
        label:'기기변경',
        value:'chgDev'
      }
    ],
      pnMkr: 'all',
      pnMkrList: [
        {
          label:'선택',
          value:'all'
        },
        {
        label:'삼성전자',
        value:'SAM'
      },
        {
          label:'LG전자',
          value:'LGE'
        },
        {
          label:'APPLE',
          value:'APP'
        },
        {
          label:'샤오미',
          value:'XIA'
        }
      ],
      selectedMdl : 'all',
      mdlList : [],
      columns:[
        { name:'priceDate',
          required:true,
          label:'일자',
          align:'center',
          field: row=> row.priceDate,
          format: val => `${val}`,
          headerClasses:'tbl-header-bg-color'
        },
        { name: 'ofclDv', align:'center', label: '공시지원금', field: 'ofclDv', headerClasses:'tbl-header-bg-color'},
        { name: 'ofclRt', align:'center', label: '요금할인', field: 'ofclRt', headerClasses:'tbl-header-bg-color'}
      ],
      priceList:[
        {priceDate:'20.11.01',
        ofclDv:'370000원',
        ofclRt:'670000원'},
        {priceDate:'20.11.02',
          ofclDv:'370000원',
          ofclRt:'670000원'},
        {priceDate:'20.11.03',
          ofclDv:'370000원',
          ofclRt:'670000원'},
        {priceDate:'20.11.04',
          ofclDv:'370000원',
          ofclRt:'670000원'},
        {priceDate:'20.11.05',
          ofclDv:'370000원',
          ofclRt:'670000원'},
        {priceDate:'20.11.06',
          ofclDv:'370000원',
          ofclRt:'670000원'},
      ]
  }
  },
  methods:{
    scrollTop(){
      document.querySelector('#top').scrollIntoView({
        behavior: 'smooth'
      });
    }
  }
};
</script>
<style>
.pentas-layout__header-back-btn .material-icons {
  vertical-align: middle;
}

.pentas-layout__price-search-box {
  width: 100%;
  display: flex;
  flex-direction: column;
  /*background-color: #FDFAFA;*/
  padding-top: 3.2rem;
  margin-top: 10rem;
}

.pentas-layout__price-search-row {
  display: flex;
  flex-direction: row;
  width: 90%;
  margin: 0 auto 2.4rem;
}

.pentas-layout__price-search-label {
  width: 25%;
  margin: auto 0;
  font-size: 3.6rem;
  font-weight: bold;
}

.pentas-layout__price-search-radio {
  width: 75%;
  margin: auto 0;
  font-size: 3rem;
}

.mdl-radio {
  font-size: 3rem;
  margin-right: 2.2rem;
}

.pentas-layout__price-search-select {
  width: 75%;
  background-color: #ffffff;
  /*padding: 1.2rem 2.2rem;*/
  /*border: 2px solid #858585;*/
  /*border-radius: 4px;*/
  margin: auto 0;
  font-size: 3rem;
}

.pentas-layout__price-search-select i {
  float: right;
  margin: auto 0;
}

.pentas-button__search {
  background-color: #2d90f5;
  color: #ffffff;
  font-size: 3.2rem;
  font-weight: bold;
  width: 40%;
  vertical-align: middle;
  margin: 2.4rem auto;
}

.pentas-layout__price-chip-box {
  width: 100%;
  padding: 2.4rem 3.2rem;
  border-bottom: 1px solid #000000;
}

.mdl-chip {
  font-family: "Nanum Gothic", "Roboto", sans-serif !important;
  height: 6.4rem;
  line-height: 6.4rem;
  padding: 0 6rem;
  border-radius: 10px;
  background-color: #ffffff;
}

.pentas-chip-all {
  border: 1px solid #000000;
}

.pentas-chip-one {
  border: 1px solid #2d90f5;
  color: #2d90f5;
}

.pentas-layout__price-table-self {
  border: none;
  width: 90%;
  margin: 4.8rem auto;
}

.pentas-layout__price-table-self thead,
.pentas-layout__price-table-self tbody tr {
  line-height: 6rem;
  height: 6rem;
}

.mdl-data-table__cell--non-numeric {
  margin: 0 !important;
  padding: 0 !important;
  height: 6rem !important;
  line-height: 6rem !important;
  text-align: center !important;
}

th.mdl-data-table__cell--non-numeric {
  background-color: #E9E9EA;
  color: #000000;
}

.pentas-layout__price-table-num-col {
  color: #2d90f5;
}

.pentas-layout__price-see-more {
  width: 90%;
  margin: 4.8rem auto;
  margin-bottom: 10rem;
}

.pentas-button__see-more {
  background-color: #E9F0F8;
  color: #2d90f5;
  font-size: 3.2rem;
  font-weight: bold;
  width: 100%;
  vertical-align: middle;
}
.q-table__card {
  box-shadow: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
}
th.tbl-header-bg-color {
  color: rgba(0,0,0,.54);
  background-color: #eaf0f7;
}

.q-table thead tr, .q-table tbody td {
  height: 10rem;
}
</style>

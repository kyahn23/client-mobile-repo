<template>
  <div class="q-mt-lg q-pt-lg">
    <div class="full-width full-height">
      <q-stepper
        class="no-box-shadow"
        v-model="step"
        ref="stepper"
        animated
        contracted
        header-class="no-header"
      >
        <q-step
          class="no-padding no-margin"
          :name="1"
          title="step 1"
          :done="step > 1"
          style="min-height: 250px"
        >
          <div class="step1content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs flex justify-between">
              <div class="col" style="overflow-x: visible; height: 132px;">
                <div style="position: absolute;">
                  <p class="text-subtitle1 text-weight-bold">
                    원하는 조건을 입력하시면
                  </p>
                  <p class="text-subtitle1 text-weight-bold">
                    <span style="color: cornflowerblue;">지역별 시세정보</span
                    >와
                  </p>
                  <p class="text-subtitle1 text-weight-bold">
                    <span style="color: cornflowerblue;">상담</span>을 받으실 수
                    있습니다.
                  </p>
                </div>
              </div>
              <div class="col" style="overflow-x: visible;">
                <q-img
                  src="images/intro-icon1_bg.png"
                  style="height: 132px;"
                  contain
                />
              </div>
            </div>
            <div class="q-px-md">
              <p
                class="text-body2 text-weight-medium"
                style="word-break: keep-all;"
              >
                이곳 저곳 방문하지 않고 간단한 조건 등록을 통해서 원하는
                대리점을 추천해 드립니다.
              </p>
            </div>
            <div>
              <div class="q-mx-md text-subtitle1 text-weight-bold">
                상담등록 절차
              </div>
              <div class="row q-mt-md q-mx-md text-center items-center">
                <div
                  class="col q-px-xs q-py-sm bg-light-blue-10"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step1" style="font-size: 2em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-9"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step2" style="font-size: 2em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-8"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step3" style="font-size: 2em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step4" style="font-size: 2em;" />
                </div>
              </div>
              <div class="row q-px-md text-center">
                <div class="col-auto text-caption text-weight-bold">
                  가입유형
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  스마트폰<br />선택
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  기타<br />상담정보
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  정보<br />확인/등록
                </div>
              </div>
            </div>
          </div>
        </q-step>

        <q-step
          class="no-padding no-margin"
          :name="2"
          title="step2"
          style="min-height: 250px"
        >
          <div class="step2content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">
                STEP 1. 가입유형
              </p>
            </div>

            <div class="no-margin">
              <div class="row q-mt-xs q-mx-md text-center items-center">
                <div
                  class="col q-px-xs q-py-sm bg-light-blue-10"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step1" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step2" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step3" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step4" style="font-size: 1.6em;" />
                </div>
              </div>
              <div class="row q-mb-xs text-center" style="padding: 0 20px;">
                <div class="col-auto text-caption text-weight-bold">
                  STEP 1
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 2
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 3
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  FINAL
                </div>
              </div>
            </div>
            <q-separator class="q-mb-xs" />
            <div class="q-ma-sm">
              <div class="row q-mb-sm">
                <div class="col-3 items-center">
                  <span class="text-subtitle2 text-weight-bold q-pl-sm"
                    >가입유형</span
                  >
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
                <span class="col-3 text-weight-bold self-center q-pl-sm"
                  >지원구분</span
                >
                <q-select
                  class="col-9 self-center"
                  outlined
                  v-model="saleType"
                  :options="saleOpts"
                  dense
                  emit-value
                  map-options
                >
                </q-select>
              </div>
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm"
                  >요금제</span
                >
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
            </div>
            <div>
              <ul>
                <li>
                  <span style="color: lightgray;">데이터 :</span>
                  <span v-if="selectedMntRt.pnMntRtNo === 'all'" class="q-pl-xs"
                    >-</span
                  >
                  <span v-else class="q-pl-xs">{{
                    selectedMntRt.pnMntData
                  }}</span>
                </li>
                <li>
                  <span style="color: lightgray;">음성 :</span>
                  <span v-if="selectedMntRt.pnMntRtNo === 'all'" class="q-pl-xs"
                    >-</span
                  >
                  <span v-else class="q-pl-xs">{{
                    selectedMntRt.pnMntCall
                  }}</span>
                </li>
                <li>
                  <span style="color: lightgray;">문자 :</span>
                  <span v-if="selectedMntRt.pnMntRtNo === 'all'" class="q-pl-xs"
                    >-</span
                  >
                  <span v-else class="q-pl-xs">{{
                    selectedMntRt.pnMntSms
                  }}</span>
                </li>
              </ul>
            </div>
          </div>
        </q-step>

        <q-step :name="3" title="step3" style="min-height: 250px">
          <div class="step3content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">
                STEP 2. 스마트폰 선택
              </p>
            </div>
            <div class="no-margin">
              <div class="row q-mt-xs q-mx-md text-center items-center">
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step1" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-9"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step2" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step3" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step4" style="font-size: 1.6em;" />
                </div>
              </div>
              <div class="row q-mb-xs text-center" style="padding: 0 20px;">
                <div class="col-auto text-caption text-weight-bold text-grey-7">
                  STEP 1
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 2
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 3
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  FINAL
                </div>
              </div>
            </div>
            <q-separator class="q-mb-xs" />
            <div class="q-ma-sm">
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm"
                  >제조사</span
                >
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
                <span class="col-3 text-weight-bold self-center q-pl-sm"
                  >모델명</span
                >
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
            </div>
          </div>
        </q-step>

        <q-step
          class="no-border"
          :name="4"
          title="step4"
          style="min-height: 200px"
        >
          <div class="page-content q-mb-xl">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">
                STEP 3. 기타 상담정보
              </p>
            </div>
            <div class="no-margin">
              <div class="row q-mt-xs q-mx-md text-center items-center">
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step1" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step2" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-8"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step3" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step4" style="font-size: 1.6em;" />
                </div>
              </div>
              <div class="row q-mb-xs text-center" style="padding: 0 20px;">
                <div class="col-auto text-caption text-weight-bold text-grey-7">
                  STEP 1
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 2
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 3
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  FINAL
                </div>
              </div>
            </div>
            <q-separator class="q-mb-xs" />
            <div class="q-ma-sm">
              <div class="flex row q-ml-xs q-mb-xs fit">
                <span class="text-weight-bold text-caption text-left"
                  >희망 구매지역</span
                >
              </div>
              <div class="flex row q-mb-md fit">
                <q-select
                  class="col-6 self-center"
                  outlined
                  label="특별시도"
                  v-model="sido"
                  :options="sidoOpt"
                  option-value="cdVal"
                  option-label="cdNm"
                  dense
                  emit-value
                  map-options
                >
                </q-select>
                <q-select
                  class="col-6 self-center q-pl-xs"
                  outlined
                  label="시군구"
                  v-model="sigg"
                  :options="siggOpt"
                  option-value="cdVal"
                  option-label="cdNm"
                  dense
                  emit-value
                  map-options
                >
                </q-select>
              </div>
              <div class="row q-ml-xs q-mb-xs">
                <span class="text-weight-bold text-caption text-left"
                  >예상 구매일</span
                >
              </div>
              <div class="row q-mb-md">
                <q-input
                  class="col self-center input-buy-date"
                  outlined
                  dense
                  v-model="hopeDt"
                  mask="date"
                  :rules="['date']"
                >
                  <template v-slot:append>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy
                        ref="qDateProxy"
                        transition-show="scale"
                        transition-hide="scale"
                        v-model="calendarPopup"
                      >
                        <q-date
                          v-model="hopeDt"
                          :options="calendarOpt"
                          subtitle="​​​​​"
                          title="예상 구매일"
                          first-day-of-week="1"
                        />
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
              </div>
              <div>
                <div class="q-ml-xs">
                  <span class="text-caption text-weight-bold full-width"
                    >기타 요청사항</span
                  >
                </div>
                <q-input
                  style="height: 100px"
                  class="full-width"
                  v-model="etc"
                  outlined
                  type="textarea"
                  maxlength="30"
                  :rules="[
                    val =>
                      val.length <= 30 || '요청사항은 30자 이내로 작성해주세요'
                  ]"
                  hint="요청사항은 30자 이내로 작성해주세요"
                />
              </div>
            </div>
          </div>
        </q-step>
        <q-step
          class="no-border"
          :name="5"
          title="step5"
          style="min-height: 250px"
        >
          <div class="page-content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">
                FINAL. 상담등록 정보 확인/등록
              </p>
            </div>
            <div class="no-margin">
              <div class="row q-mt-xs q-mx-md text-center items-center">
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step1" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step2" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-grey-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step3" style="font-size: 1.6em;" />
                </div>
                <div class="col steps-line">
                  <q-icon name="arrow_forward_ios" class="text-light-blue-10" />
                </div>
                <div
                  class="col q-px-xs q-py-sm bg-blue-7"
                  style="max-width: 45px; border-radius: 4px;"
                >
                  <q-icon name="app:step4" style="font-size: 1.6em;" />
                </div>
              </div>
              <div class="row q-mb-xs text-center" style="padding: 0 20px;">
                <div class="col-auto text-caption text-weight-bold text-grey-7">
                  STEP 1
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 2
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold text-grey-7"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  STEP 3
                </div>
                <div class="col"></div>
                <div
                  class="col-auto text-caption text-weight-bold"
                  style="padding-top: 2px; line-height: 14px;"
                >
                  FINAL
                </div>
              </div>
            </div>
            <q-separator class="q-mb-xs" />
            <div class="q-pa-sm">
              <table style="width: 100%; border-spacing: 0px;">
                <tr style="height: 2.5rem">
                  <th
                    class="bg-blue-1"
                    style="width: 40%; border-top: 2px solid lightgrey;"
                  >
                    통신사
                  </th>
                  <td style="width: 60%; border-top: 2px solid lightgrey;;">
                    <span class="q-pl-md">{{ carrLong }}</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem">
                  <th class="bg-blue-1" style="width: 40%;">가입유형</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">{{ signTypeLabel }}</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">지원구분</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">{{ saleTypeLabel }}</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">요금제</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">
                      {{ selectedMntRt.pnMntRtNm }}
                    </span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">선택모델</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">{{ selected.label }}</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">희망 구매지역</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">{{ sidolabel }} {{ sigglabel }}</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th
                    class="bg-blue-1"
                    style="width: 40%; border-bottom: 2px solid lightgrey;"
                  >
                    예상 구매일
                  </th>
                  <td style="width: 60%; border-bottom: 2px solid lightgrey;">
                    <span class="q-pl-md">{{ hopeDt }}</span>
                  </td>
                </tr>
              </table>

              <div class="q-my-xs" style="margin-bottom: 40px">
                <span class="q-mt-sm q-pl-sm text-weight-bold full-width"
                  >기타 요청사항</span
                >
                <p
                  style="height: 60px; border: 1px solid lightgrey; border-radius: 5px"
                  class="full-width text-caption q-pa-xs q-mb-lg"
                >
                  {{ etc }}
                </p>
              </div>
            </div>
          </div>
        </q-step>

        <template v-slot:navigation>
          <q-stepper-navigation
            class="no-margin no-padding fixed-bottom"
            style="margin-bottom: 70px !important;"
          >
            <q-btn
              v-if="step === 1"
              @click="$refs.stepper.next()"
              color="primary"
              padding="sm"
              :label="carrLong + ' 상담등록 시작하기 >>'"
              class="full-width no-border-radius"
            >
            </q-btn>
            <q-btn
              v-if="step === 2"
              class="no-border-radius"
              color="grey"
              padding="sm"
              @click="$refs.stepper.previous()"
              label="취소"
              style="width: 30%"
            >
            </q-btn>
            <q-btn
              v-if="step > 2"
              class="no-border-radius"
              color="grey"
              padding="sm"
              @click="$refs.stepper.previous()"
              label="이전"
              style="width: 30%"
            >
            </q-btn>
            <q-btn
              v-if="step < 5 && step !== 1"
              class="no-border-radius"
              @click="nextStep"
              label="다음"
              padding="sm"
              color="primary"
              style="width: 70%"
            >
            </q-btn>
            <q-btn
              v-if="step === 5"
              class="no-border-radius"
              @click="registerCs"
              label="등록하기 >>"
              padding="sm"
              color="primary"
              style="width: 70%"
            >
            </q-btn>
          </q-stepper-navigation>
        </template>
      </q-stepper>
    </div>
  </div>
</template>

<script>
import { date } from "quasar";

export default {
  name: "PageRegisterForm",
  props: {
    carrier: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      step: 1,
      signType: null,
      signOpts: [
        { label: "신규가입", value: "newSign" },
        { label: "번호이동", value: "moveCarr" },
        { label: "기기변경", value: "chgDev" }
      ],
      saleType: "all",
      saleOpts: [
        { label: "선택", value: "all" },
        { label: "공시지원", value: "ofclDv" },
        { label: "요금할인", value: "ofclRt" }
      ],
      selectedMntRt: { label: "선택", pnMntRtNo: "all" },
      mntRtList: [],
      pnMkr: "all",
      pnMkrList: [
        { label: "선택", value: "all" },
        { label: "삼성전자", value: "SAM" },
        { label: "LG전자", value: "LGE" },
        { label: "애플", value: "APP" },
        { label: "샤오미", value: "XIA" }
      ],
      selected: { label: "선택", pnMdlNo: "all" },
      phoneList: [],
      sido: null,
      sidoOpt: [],
      sigg: null,
      siggOpt: [{ cdNm: "선택", cdVal: "all" }],
      hopeDt: "",
      etc: "",
      calendarPopup: false
    };
  },
  watch: {
    pnMkr: function(newValue, oldValue) {
      if (newValue !== oldValue) {
        this.selected = {
          label: "선택",
          pnMdlNo: "all"
        };
        this.phoneList = [];
        this.getPhoneList();
      }
    },
    step: function(value) {
      if (value === 3) {
        this.getSido();
      }
    },
    sido(newSido) {
      if (this.sido === "all") {
        this.sigg = "all";
        this.siggOpt = [{ cdNm: "선택", cdVal: "all" }];
      } else {
        this.sigg = "all";
        this.getSigg(newSido);
      }
    },
    hopeDt(newDt) {
      if (!this.$cf.isEmpty(newDt)) {
        this.calendarPopup = false;
      }
    }
  },
  mounted() {
    this.getMntRtList();
  },
  computed: {
    /** 통신사 변수 */
    carr() {
      return this.carrier.charAt(0);
    },
    carrLong() {
      return this.carrier;
    },
    /** 현재 로그인 한 사용자 */
    currentUser: {
      get() {
        return this.$store.getters.currentUser;
      }
    },
    signTypeLabel() {
      let signlabel = "";
      switch (this.signType) {
        case "newSign":
          signlabel = "신규가입";
          break;
        case "moveCarr":
          signlabel = "번호이동";
          break;
        case "chgDev":
          signlabel = "기기변경";
          break;
      }
      return signlabel;
    },
    saleTypeLabel() {
      let salelabel = "";
      for (let n in this.saleOpts) {
        if (this.saleOpts[n].value === this.saleType) {
          salelabel = this.saleOpts[n].label;
        }
      }
      return salelabel;
    },
    sidolabel() {
      let sido = "";
      for (let n in this.sidoOpt) {
        if (this.sidoOpt[n].cdVal === this.sido) {
          sido = this.sidoOpt[n].cdNm;
        }
      }
      return sido;
    },
    sigglabel() {
      let sigg = "";
      for (let n in this.siggOpt) {
        if (this.siggOpt[n].cdVal === this.sigg) {
          sigg = this.siggOpt[n].cdNm;
        }
      }
      return sigg;
    },
    currentUser: {
      get() {
        return this.$store.getters.currentUser;
      }
    }
  },
  methods: {
    getMntRtList() {
      let param = {
        mntCarr: this.carrLong
      };
      this.$cf.call(
        process.env.API + "/api/regCounsel/mntRtList",
        param,
        this.getMntRtListCB,
        false
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
    getPhoneList() {
      let param = {
        mntCarr: this.carr,
        pnMkr: this.pnMkr,
        pnNetType: this.selectedMntRt.pnNetType
      };
      this.$cf.call(
        process.env.API + "/api/regCounsel/phoneList",
        param,
        this.getPhoneListCB,
        false
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
    nextStep() {
      // step 1 은 상담 등록 시작화면
      switch (this.step) {
        case 1:
        case 2:
          let step2Chk = false;
          if (this.signType === null) {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "가입유형을 선택해주세요."
            });
            step2Chk = true;
          } else if (this.saleType === "all") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "지원구분을 선택해주세요."
            });
            step2Chk = true;
          } else if (this.selectedMntRt.pnMntRtNo === "all") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "요금제를 선택해주세요."
            });
            step2Chk = true;
          }
          if (!step2Chk) {
            this.$refs.stepper.next();
          }
          break;
        case 3:
          let step3Chk = false;
          if (this.pnMkr === "all") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "제조사를 선택해주세요."
            });
            step3Chk = true;
          } else if (this.selected.pnMdlNo === "all") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "스마트폰을 선택해주세요."
            });
            step3Chk = true;
          }
          if (!step3Chk) {
            this.$refs.stepper.next();
          }
          break;
        case 4:
          let step4Chk = false;
          if (this.sido === "all" || this.sido === null) {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "시/도를 선택해주세요."
            });
            step4Chk = true;
          } else if (this.sigg === "all" || this.sigg === null) {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "시/군/구를 선택해주세요."
            });
            step4Chk = true;
          } else if (this.hopeDt === "") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "예상구매일을 선택해주세요."
            });
            step4Chk = true;
          } else if (this.etc === "") {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "기타요청사항을 작성해주세요."
            });
            step4Chk = true;
          }
          if (!step4Chk) {
            this.$refs.stepper.next();
          }
          break;
      }
    },
    calendarOpt(dt) {
      let timeStamp = Date.now();
      let formattedString = date.formatDate(timeStamp, "YYYY/MM/DD");
      return dt >= formattedString;
    },
    getSido() {
      this.$cf.call(
        process.env.API + "/api/common/sido",
        {},
        this.sidoCb,
        false
      );
    },
    sidoCb(response) {
      const obj = {
        cdNm: "선택",
        cdVal: "all"
      };
      this.sidoOpt.push(obj);
      for (let n in response.sidoList) {
        this.sidoOpt.push(response.sidoList[n]);
      }
    },
    /** 시군구 list 호출 함수 */
    getSigg(sidoCd) {
      this.$cf.call(
        process.env.API + "/api/common/sigg",
        { sidoCd: sidoCd },
        this.siggCb,
        false
      );
    },
    /** 시군구 list 콜백 함수 */
    siggCb(response) {
      this.siggOpt = [];
      const obj = {
        cdNm: "선택",
        cdVal: "all"
      };
      this.siggOpt.push(obj);
      for (let n in response.siggList) {
        this.siggOpt.push(response.siggList[n]);
      }
    },
    registerCs() {
      let param = {
        mntCarr: this.carrLong,
        pnRegDis: this.signType,
        saleType: this.saleType,
        pnMntRtNo: this.selectedMntRt.pnMntRtNo,
        pnMdlNo: this.selected.pnMdlNo,
        pnStor: this.selected.pnStor,
        dealLoc: this.sidolabel.concat(" ", this.sigglabel),
        dealDt: this.hopeDt.replaceAll("/", ""),
        dealReq: this.etc,
        clMbrId: this.currentUser
      };
      this.$cf.call(
        process.env.API + "/api/regCounsel/registerCs",
        param,
        this.registerCsCB,
        false
      );
    },
    registerCsCB() {
      this.step = 1;
      this.signType = null;
      this.saleType = "all";
      this.selectedMntRt = { label: "선택", pnMntRtNo: "all" };
      this.mntRtList = [];
      this.pnMkr = "all";
      this.phoneList = [];
      this.selected = { label: "선택", pnMdlNo: "all" };
      this.sido = null;
      this.sidoOpt = [];
      this.sigg = null;
      this.siggOpt = [{ cdNm: "선택", cdVal: "all" }];
      this.hopeDt = "";
      this.etc = "";

      // 상담등록 후 상담현황으로 이동
      this.$router.push({ path: "/status" });
    }
  }
};
</script>
<style>
.no-header {
  display: none;
}

.q-stepper--horizontal .q-stepper__step-inner {
  padding: unset;
}

.input-buy-date {
  padding-bottom: unset;
}

.q-field__bottom {
  padding: 4px 8px 0;
}

.q-textarea .q-field__native {
  padding-top: 8px;
}

.steps-line {
  position: relative;
}

.steps-line:after {
  position: absolute;
  left: 0;
  top: 50%;
  height: 1px;
  background: #01579b;
  content: "";
  width: 100%;
  display: block;
}
</style>

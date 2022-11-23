<template>

  <section>

    <div class="container mt-3 d-print-inline-block" ref="fitSchedule">

      <h4>운동 스케줄</h4>

      <div>

        <b-row>
          <b-col md="auto">
            <b-calendar v-model="value" @context="onContext" locale="en-US"></b-calendar>
          </b-col>
          <b-col>
            <p>Value: <b>'{{ value }}'</b></p>
            <p class="mb-0">Context:</p>
            <pre class="small">{{ context }}</pre>
          </b-col>
        </b-row>

      </div>



      <div class="form-outline align-co">
        <button class="btn btn-sm btn-outline-info">스케줄 등록</button>
      </div>

      <calendar
          :first-day="1"
          :all-events="events"
      ></calendar>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.10.31</h5>
          <p class="card-text">달려라 와드! 크로스핏</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.11.01</h5>
          <p class="card-text">하체 털기</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.11.02</h5>
          <p class="card-text">등 밥주기</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.11.03</h5>
          <p class="card-text">이두삼두 키우기</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.11.04</h5>
          <p class="card-text">벤치후라쓰</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

      <div class="card w-50 d-print-inline-block">
        <div class="card-body">
          <h5 class="card-title">2022.11.05</h5>
          <p class="card-text">유산소산소</p>
          <a href="#" class="btn btn-primary">상세보기</a>
        </div>
      </div>

    </div>

  </section>

</template>

<script>

  export default {
    name: "AppFitSchedule",
    data() {
      return {
        value: '',
        context: null
      }
    },
    compatConfig: { MODE: 3 },
    methods: {
      onContext(ctx) {
        this.context = ctx
      }
    }
  }

</script>

<style scoped>
  .card{ display: inline-block; }
</style>
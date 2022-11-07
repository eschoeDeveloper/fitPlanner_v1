<template>

  <section>

    <div class="container mt-4">

      <form>
<!--        <div>{{memberInfo}}</div>-->
        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="inputId">아이디</label>
            <input type="text" class="form-control" id="inputId" placeholder="아이디" :value="memberInfo.id" readonly>
          </div>

          <div class="form-group col-md-12">
            <label for="inputEmail">이메일</label>
            <input type="email" class="form-control" id="inputEmail" placeholder="이메일" :value="memberInfo.email">
          </div>

          <div class="form-group col-md-12">
            <label for="inputPassword">비밀번호 변경</label>
            <input type="password" class="form-control" id="inputPassword" placeholder="비밀번호">
          </div>

          <div class="form-group col-md-12">
            <label for="inputPassword2">비밀번호 변경확인</label>
            <input type="password" class="form-control" id="inputPassword2" placeholder="비밀번호 변경확인">
          </div>

        </div>

        <hr class="hr hr-blurry">

        <div class="form-row">

          <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" class="form-control" id="inputName" placeholder="이름" :value="memberInfo.name">
          </div>

          <div class="form-group">
            <label for="inputBirthday">생년월일</label>
            <input type="text" class="form-control" id="inputBirthday" placeholder="생년월일" :value="memberInfo.birthday">
          </div>

          <div class="form-group">
            <label for="inputAge">나이</label>
            <input type="text" class="form-control" id="inputAge" placeholder="나이" :value="memberInfo.age">
          </div>

        </div>


        <hr class="hr hr-blurry">

        <div class="form-group">
          <label for="inputPhone">전화번호</label>
          <input type="text" class="form-control" id="inputPhone" placeholder="전화번호" :value="memberInfo.phone">
        </div>

        <button type="button" class="btn btn-primary mt-4" @click="">저장</button>
      </form>

    </div>

  </section>

</template>

<script>
export default {

  name: "AppMemberEdit",
  data() {
    return {
      memberInfo : {}
    }
  },
  methods: {

    getMemberInfo() {

      this.axios.post("/api/member/get", {}, {
        withCredentials: true
      })
      .then((response) => {
        let respJson = JSON.parse(JSON.stringify(response.data));
        this.memberInfo = JSON.parse(respJson.data);
        console.log(this.memberInfo);
      }).catch((error) => {
        console.log(error);
      });

    }

  },
  created() {
    this.getMemberInfo();
  }

}
</script>

<style scoped>

</style>
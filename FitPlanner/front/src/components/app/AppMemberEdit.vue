<template>

  <section>

    <div class="container mt-4">

      <form name="frmMember" method="post">

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="inputId">아이디</label>
            <input type="text" class="form-control" ref="inputId" id="inputId" placeholder="아이디" :value="memberInfo.id" readonly>
          </div>

          <div class="form-group col-md-12">
            <label for="inputEmail">이메일</label>
            <input type="email" class="form-control" ref="inputEmail" id="inputEmail" placeholder="이메일" :value="memberInfo.email">
          </div>

          <div class="form-group col-md-12">
            <label for="inputPassword">비밀번호 변경</label>
            <input type="password" class="form-control" ref="newPassword" id="inputPassword" placeholder="비밀번호">
          </div>

          <div class="form-group col-md-12">
            <label for="inputPassword2">비밀번호 변경확인</label>
            <input type="password" class="form-control" ref="checkNewPassword" id="inputPassword2" placeholder="비밀번호 변경확인">
          </div>

        </div>

        <hr class="hr hr-blurry mt-4">

        <div class="form-row">

          <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" class="form-control" ref="inputName" id="inputName" placeholder="이름" :value="memberInfo.name">
          </div>

          <div class="form-group">
            <label for="inputBirthday">생년월일</label>
            <input type="text" class="form-control" ref="inputBirthday" id="inputBirthday" placeholder="생년월일" :value="memberInfo.birthday">
          </div>

          <div class="form-group">
            <label for="inputGender">성별</label>
            <select ref="inputGender" class="form-control" id="inputGender" name="inputGender" :value="memberInfo.gender">
              <option value="G01">남성</option>
              <option value="G02">여성</option>
            </select>
          </div>

        </div>


        <hr class="hr hr-blurry mt-4">

        <div class="form-group">
          <label for="inputPhone">전화번호</label>
          <input type="text" class="form-control" ref="inputPhone" id="inputPhone" placeholder="전화번호" :value="memberInfo.phone">
        </div>

        <button type="button" class="btn btn-primary mt-4 mb-4" @click="getMemberEdit();">저장</button>

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

    getMemberEdit() {

      const newPassword = this.$refs.newPassword.value;
      const checkNewPassword = this.$refs.checkNewPassword.value;

      if(
          (newPassword && !checkNewPassword) || (!newPassword && checkNewPassword)
      ) {
        this.$toast.show("변경할 비밀번호를 정확하게 입력해주세요.");
        setTimeout(() => {
          this.$toast.clear;
        }, 3000);
        return false;
      }

      if(
          (newPassword && checkNewPassword) && (newPassword != checkNewPassword)
      ) {
        this.$toast.show("변경하실 비밀번호가 일치하지 않습니다.");
        setTimeout(() => {
          this.$toast.clear;
        }, 3000);
        return false;
      }

      let formData = new FormData();

      formData.append("id", this.$refs.inputId.value);
      formData.append("name", this.$refs.inputName.value);
      if(newPassword) formData.append("password", newPassword);
      formData.append("phone", this.$refs.inputPhone.value);
      formData.append("gender", this.$refs.inputGender.value);
      formData.append("birthday", this.$refs.inputBirthday.value);
      formData.append("email", this.$refs.inputEmail.value);
      formData.append("updateId", this.$refs.inputId.value);

      this.axios.post("/api/member/update", formData, {})
      .then((response) => {

        let respJson = JSON.parse(JSON.stringify(response.data));
        let updateYn = respJson.data;

        if(updateYn == "Y" && Number(respJson.code) == 200) {

          if(newPassword && checkNewPassword) {

            this.$toast.show("비밀번호가 변경되어 다시 로그인해주세요.");

            setTimeout(() => {

              this.$toast.clear;

              this.axios.post("/api/member/logout", JSON.stringify({}), {})
                  .then((response) => {

                    let respJson = JSON.parse(JSON.stringify(response.data));
                    let respCode = respJson.code;

                    if (Number(respCode) < 400) {
                      this.$cookies.remove("ssoLogin");
                      this.$store.dispatch("resetApiToken");
                      this.$router.push({path: '/app/login'});
                    }

                  }).catch((error) => {
                console.log(error);
              });

            }, 1000);

          } else {

            this.$toast.show("변경된 정보가 저장되었습니다.");

            setTimeout(() => {
              this.$toast.clear;
            }, 2000);

          }

        } else {
          this.$toast.show("오류가 발생하였습니다. 지속되면 관리자에게 문의해주세요.");

          setTimeout(() => {
            this.$toast.clear;
          }, 2000);
        }

      }).catch((error) => {
        console.log(error);
      });

    },
    getMemberInfo() {

      this.axios.post("/api/member/get", {}, {})
      .then((response) => {
        let respJson = JSON.parse(JSON.stringify(response.data));

        alert(JSON.stringify(response.data));

        this.memberInfo = JSON.parse(respJson.data);
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
label { font-weight: bold; margin: 1vh; }
</style>
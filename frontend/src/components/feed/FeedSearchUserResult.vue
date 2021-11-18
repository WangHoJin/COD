<template>
  <v-container>
    <v-flex style="padding: 12px 12px 8px 12px">
      <div v-for="user in userList.result" :key="user.userId" class="profileInfo">
        <v-row>
          <v-col style="padding: 20px 12px 20px 12px" cols="auto">
            <v-img class="profileImg" src="../../assets/logo/login.png"> </v-img>
          </v-col>
          <v-col style="padding: 28px 0px 20px 12px">
            <h4 @click="userClick(user.userId)" style="text-align: left">{{ user.nickname }}</h4>
          </v-col>
        </v-row>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  props: { status: { type: String } },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["userList", "codies"]),
  },
  watch: {
    userList: function () {},
  },
  created() {},
  methods: {
    ...mapActions(["getUsers"]),
    search() {
      let payload = { nickname: this.input, page: 1, size: 10 };
      this.getUsers(payload);
      // this.$router.push({
      //   name: "search",
      // });
      console.log(this.input);
      console.log(this.userList);
    },
    userClick(userId) {
      console.log("유저클릭" + userId);
      this.$router.push({
        path: `../mypage/` + userId,
      });
    },
  },
};
</script>

<style scoped>
.profileImg {
  border-style: solid;
  border-color: #857db1;
  border-radius: 50px;
  max-height: 45px;
  max-width: 45px;
}
</style>

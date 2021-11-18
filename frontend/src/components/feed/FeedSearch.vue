<template>
  <div>
    <v-container>
      <v-text-field
        v-model="input"
        type="text"
        append-icon="mdi-magnify"
        solo
        dense
        hide-details=""
        @click:append="search()"
        v-on:keyup.enter="search()"
      >
      </v-text-field>
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    status: { type: String },
  },
  data() {
    return {
      input: "",
    };
  },
  computed: {
    ...mapGetters(["userList", "codies"]),
  },
  watch: {},
  created() {
    // this.search();
  },
  methods: {
    ...mapActions(["getUsers", "getCodies"]),
    search() {
      let payload = { nickname: this.input, tag: this.input, page: 1, size: 10 };
      this.getUsers(payload);
      this.getCodies(payload);
      console.log("status:" + this.status);
      if (this.status == "main") {
        this.$router.push({
          name: "search",
        });
      }
      console.log(this.$store.state.feed.userList);
    },
  },
};
</script>

<style scoped></style>

<template>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col class="inputForm" cols="12" md="4">
          <v-textarea label="코디명" auto-grow outlined rows="1"></v-textarea>
          <v-textarea label="태그" auto-grow outlined rows="1" row-height="15"></v-textarea>
          <v-textarea outlined label="설명"></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-col class="switchBtn" cols="4" md="4"><v-switch label="공개"></v-switch></v-col>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-col class="" cols="4" md="4">
          <v-btn class="mr-4" @click="submit"> submit </v-btn></v-col
        >
      </v-row>
    </v-container>
  </v-form>
</template>
<script>
export default {};
</script>
<style scoped>
.inputForm {
  padding-top: 30px;
  padding-bottom: 0px;
}
.switchBtn {
  padding-top: 0px;
}
</style>

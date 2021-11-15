<template lang="">
  <div class="text-center my-3">
    <v-btn
      @click="click"
      elevation="1"
      style="padding: 20px; color: white; width: 100%"
      color="rgb(133 125 177 / 80%)"
    >
      {{ msg }}
    </v-btn>
  </div>
</template>
<script>
export default {
  props: {
    msg: String,
    click: Function,
  },
};
</script>
<style lang=""></style>

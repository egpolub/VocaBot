<template>
  <div class="user2 panel">
    <div id="lft">
      <span id="userName"> Hello, {{ username }}!</span><br />
      <span id="welcome"> Welcome Back!</span>
    </div>
    <div :class="{ rghtM: mobile, rght: !mobile }">
      <img id="pfp" :src="photo_url" alt="" />
    </div>
    <button v-if="!mobile" id="box" v-on:click="pin">
      <font-awesome-icon
        class="icon"
        :class="{ pinned: pinned_class }"
        id="tack"
        :icon="['fas', 'thumbtack']"
      />
    </button>
  </div>
</template>

<script>
export default {
  props: ['username', 'photo_url', 'pinned_class'],
  data () {
    return {}
  },
  methods: {
    pin () {
      this.$emit('pinned')
    }
  },
  computed: {
    mobile () {
      return this.$isMobile()
    }
  }
}
</script>

<style scoped>
#box {
  margin-top: -50px;
  height: 35px;
  width: 35px;
  background-color: #ffffff00;
  float: right;
  margin-right: 16px;
  border-radius: 12px;
  outline: 0;
  border: 0;
}

#welcome {
  font-size:22px;
  font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
}

.rght {
  text-align: right;
  position: relative;
  width: 0;
  float: right;
  height: 80px;
  top: -60px;
  right: 117px;
}
.rghtM {
   text-align: right;
  position: relative;
  width: 0;
  float: right;
  height: 80px;
  top: -60px;
  right: 77px;
}

#lft {
  line-height: 1.3;
  white-space: nowrap;
  text-align: left;
  position: relative;
  width: 0;
  margin-left: 32px;
  top: 17px;
  height: 80px;
}

#userName {
  font-size: 27px;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}
#pfp {
  border-radius: 50%;
  height: 56px;
  position: relative;

  box-shadow: 0 0px 10px 0 rgba(0, 0, 0, 0.07);
}
#user3 {
  z-index: 2;
  width: calc(100% - 600px);
  height: 100%;
  background: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(15px);
  -webkit-backdrop-filter: blur(15px);
  border-radius: 30px;
  float: right;
  right: 100px;
}

.user2 {
  margin-bottom: 60px;
  width: 100%;
  height: 95px;
  background: rgba(255, 255, 255, 0.9);
  overflow: hidden;
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.05);
}
@media only screen and (max-width:394px){
#box{
  display: none;
}
.rght {
  right: 77px;
}
}
@media only screen and (max-width:320px){
#pfp{
  display: none;
}
}
</style>

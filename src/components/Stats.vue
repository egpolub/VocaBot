<template>
   <div id="user3" >
   <div class="user panel"><span id="dash">Dashboard <br>
    <span id="description">View your statistics.</span>
    </span>
    <button id="box" v-on:click="pin"><font-awesome-icon class="icon" :class={pinned:pinned_class} id="tack" :icon="['fas', 'thumbtack']"/>
    </button>
   </div>
 </div>
</template>

<script>
export default {
  props: ['pinned_class'],
  data () {
    return {
      pinned: false
    }
  },
  methods: {
    pin () {
      this.$emit('pinned')
    }
  }
}
</script>

<style scoped>
#box{
  margin-top:29px;
  height:35px;
width:35px;
background-color:#ffffff00;
float:right;
margin-right:16px;
border-radius:12px;
outline:0;
border:0px;
}

#description{
font-size:25px;
font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
}
#dash{
text-align: left;
float:left;
margin-top:21px;
margin-left:30px;
font-size:22px;
font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}
.user{
  height: 95px;
  background: rgba( 255, 255, 255, 0.6 );
 }
 #user3{
  z-index: 2;
  width:calc(100vw - 730px);
  height: calc(100vh - 120px);
  top:0px;
  background: rgba( 255, 255, 255, 0.4 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.05 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
  position: relative;
  top:60px;
  margin-left:60px;
  min-height:680px;
  min-width: 340px;
 }
</style>

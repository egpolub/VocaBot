<template>
  <div class="dashboard">
  <!--  <div v-if="user!=null" id="user">
       <img id="pfp" :src="user.photo_url" alt="">
       <span> {{user.username}}</span>
    </div>-->
  <div id="tabs">
    <button class="tab" id="tab1" v-on:click="dashboard">
       <font-awesome-icon class="icon" id="menu" :icon="['fas', 'bars']"/>
    </button>
     <button class="tab" id="tab2" v-on:click="game">
       <font-awesome-icon class="icon" id="gamepad" :icon="['fas', 'gamepad']"/>
    </button>
     <button class="tab" id="tab3" v-on:click="settings">
       <font-awesome-icon class="icon" id="cog" :icon="['fas', 'cog']"/>
    </button>
  </div>
  <router-view>

  </router-view>
 </div>
   <!-- <img id="candy-1" :src="candy1" alt=""> -->

</template>

<script>
export default {
  data () {
    return {
    }
  },
  methods: {
    game () {
      this.$router.push({ name: 'Game' })
    },
    settings () {
      this.$router.push({ name: 'Settings' })
    },
    dashboard () {
      this.$router.push({ name: 'Dashboard' })
    }

  }
}
</script>

<style scoped>
.icon{
  margin-left:2px;
  margin-top:0px;
  font-size: 40px;
}
#tab3{
    bottom:20%;
     color:#f07b7b;
}
#tab2{
    top:calc(50% - 50px);
      color:#6bd672;
}
#tab1{
    top:20%;
    background-color: #706ed4;
    color:white;
    box-shadow: 0 0px 20px 0 rgba(135, 31, 112, 0.3);
}
#tab1:hover{
  background-color: #6765c2;
}
#tab1:active{
  background-color: #5857a8;
}
#tabs{

  overflow: hidden;
  border-radius:30%;
  width:160px;
  height:100%;
  position:absolute;
   background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 135, 31, 112, 0.1 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 0px 30px 30px 0px;
}
.tab:hover{
  background-color: #f7f7f7;
}
.tab:active{
  background-color: #f0f0f0;
}
.tab{
   transition: 0.15s;
  border:0px;
  outline:0px;
  overflow: hidden;
  width:100px;
  height:100px;
  margin-left:-50px;
  box-shadow: 0 8px 32px 0 rgba( 135, 31, 112, 0.05 );
  border-radius: 30px 30px 30px 30px;
  background-color: white;
  position:absolute;
  z-index:0;
}

 .dashboard{
   font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
   background: linear-gradient(150deg,#edddee, #ecd7db);
   height:100%;
 }
</style>

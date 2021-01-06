<template>
 <div id="screen">
  <div class="dashboard">
  <div id="tabs">
    <button v-bind:class="{activeButton:blue}" class="tab" id="tab1" v-on:click="dashboard">
       <font-awesome-icon class="icon" id="menu" :icon="['fas', 'bars']"/>
    </button>
     <button v-bind:class="{activeButton:green}" class="tab" id="tab2" v-on:click="game">
       <font-awesome-icon class="icon" id="gamepad" :icon="['fas', 'gamepad']"/>
    </button>
     <button v-bind:class="{activeButton:red}" class="tab" id="tab3" v-on:click="settings">
       <font-awesome-icon class="icon" id="cog" :icon="['fas', 'cog']"/>
    </button>
  </div>

  </div>
  <router-view>

  </router-view>

 </div>

</template>

<script>
export default {
  data () {
    return {
      blue: true,
      green: false,
      red: false
    }
  },
  created () {
    if (this.$router.currentRoute.path === '/game') {
      this.blue = false
      this.green = true
      this.red = false
    } else if (this.$router.currentRoute.path === '/settings') {
      this.blue = false
      this.green = false
      this.red = true
    }
  },
  methods: {
    game () {
      if (!this.green) {
        this.$router.push({ name: 'Game' })
        this.blue = false
        this.green = true
        this.red = false
      }
    },
    settings () {
      if (!this.red) {
        this.$router.push({ name: 'Settings' })
        this.blue = false
        this.green = false
        this.red = true
      }
    },
    dashboard () {
      if (!this.blue) {
        this.$router.push({ name: 'Dashboard' })
        this.blue = true
        this.green = false
        this.red = false
      }
    }

  }
}
</script>

<style scoped>

#screen{
  height:100%;
}
.icon{
  margin-left:2px;
  margin-top:0px;
  font-size: 40px;
}

#tab3{
    bottom:calc(20% - 50px);
     color:#f07b7b;
}
#tab3.activeButton{
    background-color: #f07b7b;
    color:white;
}

#tab3:active.activeButton{
  background-color: #d36b6b;
}

#tab2{
    top:calc(50% - 50px);
      color:#6bd672;
}
#tab2.activeButton{
    background-color: #6bd672;
    color:white;
}

#tab2:active.activeButton{
  background-color: #5fbe66;
}

#tab1{
    top:calc(20% - 50px);
    color: #706ed4;
}
#tab1.activeButton{
    background-color: #706ed4;
    color:white;
}

#tab1:active.activeButton{
  background-color: #5857a8;
}

#tabs{

  overflow: hidden;
  border-radius:30%;
  width:160px;
  top:60px;
  /*height:calc(100% - 120px);*/
  height:450px;
  position:fixed;
  background: rgba( 255, 255, 255, 0.4 );
  box-shadow: 0 8px 32px 0 rgba( 135, 31, 112, 0.07 );
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
   width:100%;
   position: fixed;
 }

</style>

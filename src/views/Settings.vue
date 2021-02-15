<template>
    <div id="settings">
<div id="block">
    <span id="title">Settings</span>
    <button id="logout">
       Log Out <font-awesome-icon id="signout" :icon="['fas', 'sign-out-alt']"/>
    </button>
    <button id="darkMode" v-on:click="DarkMode">
       <font-awesome-icon class="icon" :icon="['fas', 'moon']"/>
    </button>
    <button id="lightMode">
       <font-awesome-icon class="icon" :icon="['fas', 'lightbulb']"/>
    </button>
 </div>
    </div>
</template>

<script>
export default {
  methods: {
    DarkMode () {
      this.$emit('darkMode')
    },
    Logout () {
      this.$store.dispatch('logout').then(() => {
      }).catch(err => {
        console.log(err.response.data.error)
      })
    }
  }
}
</script>

<style  scoped>
#title{
 font-size: 35px;
 z-index:999;
 right:150px;
  position:absolute;
 top:50px;
}
#signout{
    margin-top:3px;
   right:12px;
    position:absolute;
     font-size: 23px;
}
#logout{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:260px;
  border-radius: 10px;
  font-size: 23px;
  color:#d87081;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  bottom:60px;
  box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.03);
  outline:0px;
  margin-left:-130px;
  transition: 0.15s;
  position:absolute;
}
#logout:hover{
  background-color:#f8f8f8;

}
#logout:active{
  background-color:#f1f1f1;
}
.icon{
  margin-left:2px;
  margin-top:0px;
  font-size: 40px;
}
#lightMode{
   transition: 0.15s;
  border:0px;
  outline:0px;
  overflow: hidden;
  width:100px;
  height:100px;
  margin-left:30px;
  box-shadow: 0 8px 32px 0 rgba( 135, 31, 112, 0.05 );
  border-radius: 30px 30px 30px 30px;
  background-color: white;
  position:absolute;
  bottom:165px;
}
#darkMode{
   transition: 0.15s;
  border:0px;
  outline:0px;
  overflow: hidden;
  width:100px;
  height:100px;
  margin-left:-130px;
  box-shadow: 0 8px 32px 0 rgba( 135, 31, 112, 0.05 );
  border-radius: 30px 30px 30px 30px;
  background-color: white;
  position:absolute;
  bottom:165px;
}
#block{
  z-index: 2;
  width:450px;
  height: calc(100% - 220px);
  background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.05 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
  margin:60px calc(50% - 225px);
  margin-bottom:0;
  position:absolute;
 }
 #settings{
     margin:0;
     width:100%;
     height:100%;
     overflow: hidden;
     position:absolute;
 }
</style>

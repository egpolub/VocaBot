<template>
  <div class="home" style="-moz-user-select: none; -webkit-user-select: none; -ms-user-select:none; user-select:none;-o-user-select:none;" onselectstart="return false;" unselectable="on">
    <img id="logo" src="@/assets/logo.png" alt="">
    <kinesis-container :duration="800" id="kinesis-cont">
      <div id="candy">
        <div id="candy-1"></div>
       <div id="candy-cont">
         <div id="candy-wraper">
         <kinesis-element :strength="-7" type="translate" class="candy" id="candy-2">
           <img id="candy-2a" :src="candy1" alt="">
          </kinesis-element >
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-3">
         <img id="candy-3a" :src="candy1" alt="">
          </kinesis-element >
         <kinesis-element :strength="7" type="translate" class="candy" id="candy-4">
          <img id="candy-4a" :src="candy2" alt="">
          </kinesis-element >
         <kinesis-element :strength="10" type="translate" class="candy" id="candy-5">
           <img id="candy-5a" :src="candy2" alt="">
          </kinesis-element >
         <kinesis-element :strength="10" type="translate" class="candy" id="candy-6">
         <img id="candy-6a" :src="candy1" alt="">
          </kinesis-element >
         <kinesis-element :strength="4" type="translate" class="candy" id="candy-7">
          <img id="candy-7a" :src="candy2" alt="">
         </kinesis-element >
         <kinesis-element :strength="9" type="translate" class="candy" id="candy-8">
          <img id="candy-8a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element :strength="-7" type="translate" class="candy" id="candy-9">
          <img id="candy-9a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element  :strength="8" type="translate" class="candy" id="candy-10">
          <img id="candy-10a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element :strength="3" type="translate" class="candy" id="candy-11">
          <img id="candy-11a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element :strength="-1" type="translate" class="candy" id="candy-12">
          <img id="candy-12a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element :strength="2"  type="translate" class="candy" id="candy-13">
          <img id="candy-13a" :src="candy3" alt="">
         </kinesis-element >
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-14">
          <img id="candy-14a" :src="candy2" alt="">
         </kinesis-element >
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-15">
          <img id="candy-15a" :src="candy2" alt="">
         </kinesis-element >
         <kinesis-element :strength="6" type="translate" class="candy" id="candy-16">
          <img id="candy-16a" :src="candy2" alt="">
         </kinesis-element >
         <kinesis-element :strength="10" type="translate" class="candy" id="candy-17">
          <img id="candy-17a" :src="candy2" alt="">
         </kinesis-element >
          </div>
        </div>
         <div id="low-page">
          <div id="login-box">
           <div id="login-text-box">
            <div id="login-text">
              Ready to start? <br>
              To use Voca™ you have to login with your <br><span id="telegram-text"><font-awesome-icon :icon="['fab', 'telegram-plane']"/> Telegram</span> account.
            </div>
           </div>
            <button v-on:click="login">
              Login
            </button>
            <br>
            <span id="learn"> Learn more <span id="here"> here </span> </span>
          </div>
         </div>
      </div>
    </kinesis-container>
 <div id="design">
   Design by SilentGarden.studio © 2020 VocaBot Inc.
  </div>
    <div id="bg-blur">
    </div>
  </div>
</template>
<script>
import candy1 from '@/assets/candy2.png'
import candy2 from '@/assets/candy3.png'
import candy3 from '@/assets/candy4.png'

export default {
  name: 'Home',
  data () {
    return {
      candy1,
      candy2,
      candy3,
      code: ''
    }
  },
  methods: {
    login () {
      this.$store.dispatch('login').then(() => {
      }).catch(err => {
        console.log(err.response.data.error)
      })
    }
  }
}
</script>
<style scoped>
#logo{
  position:relative;
  z-index:9;
   margin-top:-5px;
   width:200px;
   margin-left:20px;
   float:left;
}
#candy-wraper{
  width:300px;
  height: 340px;
   top:calc(50% - 150px);
  margin: 0 auto;
  position: relative;
}
#candy-cont{
 overflow:hidden;
 position: fixed;
 height: 100vh;
 top:0;
 left:-190px;
 right:0;
 bottom:0;
 z-index: 2;
 margin: 0;
 padding:0;
}
#kinesis-cont{
  position:absolute;
  width:100%;
  height:100%;
  z-index:5;
}
#here:hover{
  color:#0055a5;
}
#here{
  color:#0088cc;
  text-decoration: underline;
}
#learn{
  line-height: 5.7;
  z-index:99;
  font-size: 20px;
  font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
}
.candy{
  position:absolute;
  text-align: center;
}

#candy-17{
  left:500px;
  top:-50px;
}
#candy-17a{
  width:60px;
  transform: rotatez(-15deg);
}

#candy-16{
  left:460px;
  top:295px;
}
#candy-16a{
  width:60px;
 transform: rotatez(70deg);
}

#candy-15{
  left:-40px;
  top:290px;
}
#candy-15a{
  width:60px;
 transform: rotatez(-15deg);
}

#candy-14{
  left:-60px;
  top:-40px;
}
#candy-14a{
  width:80px;
 transform: rotatez(70deg);
}

#candy-13{
  left:525px;
  top:75px;
}
#candy-13a{
  width:55px;
  transform: rotatez(28deg);
}

#candy-12{
  left:-30px;
  top:85px;
}
#candy-12a{
  width:55px;
 transform: rotatez(28deg);
}

#candy-11{
  left:590px;
  top:200px;
}
#candy-11a{
  width:70px;
 transform: rotatez(28deg);
}

#candy-10{
  left:-90px;
  top:190px;
}
#candy-10a{
  width:65px;
 transform: rotatez(28deg);
}

#candy-9{
  left:430px;
  top:-20px;
}
#candy-9a{
  width:60px;
 transform: rotatez(28deg);
}

#candy-8{
  left:-150px;
  top:50px;
}
#candy-8a{
  width:70px;
 transform: rotatez(28deg);
}

#candy-7{
  left:-130px;
  top:60px;
}
#candy-7a{
 width:260px;
 transform: rotatez(27deg);
}

#candy-6{
  left:-130px;
  top:205px;
}
#candy-6a{
  width:180px;
 transform: rotatez(10deg);
}

#candy-5{
  left:440px;
  top:195px;
}
#candy-5a{
  width:200px;
 transform: rotatez(55deg);
}

#candy-4{
  left:440px;
  top:-40px;
}
#candy-4a{
  width:200px;
 transform: rotatez(-10deg);
}

#candy-3{
  left:440px;
  top:60px;
}
#candy-3a{
width:250px;
 transform: rotatez(20deg);
}

#candy-2{
  left:-140px;
  top:-35px;
}
#candy-2a{
  width:200px;
  transform: rotatez(60deg);
}

#candy-1{
 width:500px;
 position:relative;
}

#candy{
  width: 100%;
  height:100%;
  position:absolute;
  z-index: 2;
}

#bg-blur{
  backdrop-filter: blur( 50px );
 -webkit-backdrop-filter: blur( 50px );
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: 1;
}

#low-page{
 overflow:hidden;
 position: fixed;
 height: 100vh;
 top:0;
 left:0;
 right:0;
 bottom:0;
 z-index: 2;
 margin: 0;
 padding:0;
}
#title{

  font-size: 19vw;
  color:rgba(57,60,101);
  opacity: 0.05;
  height: 100vh;
}
#login-text-box{
 background: rgba( 255, 255, 255, 0.60 );
 text-align: center;
 border-radius: 30px 30px 0px 0px;
 border: 1px solid rgba( 255, 255, 255, 0 );
 height:240px;
}
#telegram-text{
 color:#0088cc;
}
.home{
  background: linear-gradient(150deg,#ebd0ec, #eeccd1);
  height: 100%;
  overflow:hidden;
}
#login-text{
  font-size: 24px;
  margin-top:12%;
  margin-left:7.5%;
  margin-right:7.5%;
  line-height: 1.7;
  margin-top:41px;
}
#login-box{
  z-index: 2;
  width:300px;
  height: 340px;
  background: rgba( 255, 255, 255, 0.4 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.1 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
  top:50%;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  margin: 0 auto;
  position: relative;
}
#icon{
  width:24px;
  height: 24px;
}
button{
  background-color: white;
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:75%;
  border-radius: 10px;
  font-size: 23px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:25px;
  outline:none;
  transition: 0.2s;
}
button:hover{
  background-color:#f6f6f6;

}
button:active{
  background-color:#ececec;
}

</style>

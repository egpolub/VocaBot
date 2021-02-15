<template>
  <div class="home" style="-moz-user-select: none; -webkit-user-select: none; -ms-user-select:none; user-select:none;-o-user-select:none;" onselectstart="return false;" unselectable="on">

    <kinesis-container :duration="800" id="kinesis-cont">
      <div id="candy">
        <div id="candy-1"></div>
       <div id="candy-cont">
         <div id="candy-wraper">
           <div id="left-side">
          <kinesis-element :strength="-7" type="translate" class="candy" id="candy-2">
           <img id="candy-2a" :src="squiglyBigBlue" alt="">
          </kinesis-element >
           <kinesis-element :strength="10" type="translate" class="candy" id="candy-6">
         <img id="candy-6a" :src="squiglyBigViolet" alt="">
          </kinesis-element >
           <kinesis-element :strength="4" type="translate" class="candy" id="candy-7">
          <img id="candy-7a" :src="squiglySmallOrange" alt="">
         </kinesis-element >
          <kinesis-element :strength="9" type="translate" class="candy" id="candy-8">
          <div id="candy-8a"/>
         </kinesis-element >
          <kinesis-element  :strength="8" type="translate" class="candy" id="candy-10">
          <div id="candy-10a"/>
         </kinesis-element >
          <kinesis-element :strength="-1" type="translate" class="candy" id="candy-12">
          <div id="candy-12a"/>
         </kinesis-element >
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-14">
          <img id="candy-14a" :src="squiglySmallGreen" alt="">
         </kinesis-element >
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-15">
          <img id="candy-15a" :src="squiglySmallYellow" alt="">
         </kinesis-element >
           </div>

       <div id="right-side">
         <kinesis-element :strength="-4" type="translate" class="candy" id="candy-3">
         <img id="candy-3a" :src="squiglyBigYellow" alt="">
          </kinesis-element >
         <kinesis-element :strength="7" type="translate" class="candy" id="candy-4">
          <img id="candy-4a" :src="squiglyBigGreen" alt="">
          </kinesis-element >
         <kinesis-element :strength="10" type="translate" class="candy" id="candy-5">
           <img id="candy-5a" :src="squiglyBigOrange" alt="">
          </kinesis-element >

         <kinesis-element :strength="-7" type="translate" class="candy" id="candy-9">
          <div id="candy-9a"/>
         </kinesis-element >

         <kinesis-element :strength="3" type="translate" class="candy" id="candy-11">
          <div id="candy-11a"/>
         </kinesis-element >

         <kinesis-element :strength="2"  type="translate" class="candy" id="candy-13">
          <div id="candy-13a"/>
         </kinesis-element >

         <kinesis-element :strength="6" type="translate" class="candy" id="candy-16">
          <img id="candy-16a" :src="squiglySmallViolet" alt="">
         </kinesis-element >
         <kinesis-element :strength="10" type="translate" class="candy" id="candy-17">
          <img id="candy-17a" :src="squiglySmallBlue" alt="">
         </kinesis-element >
         </div>
          </div>
        </div>
         <div id="low-page">
          <div id="login-box">
           <div id="login-text-box">
            <div id="login-text">
              Ready to start? <br>
              To use Voca™ you have to log in with your <br><span id="telegram-text"><font-awesome-icon :icon="['fab', 'telegram-plane']"/> Telegram</span> account.
            </div>
           </div>
            <button v-on:click="login">
              Login
            </button>
            <br>
            <span id="learn"> Learn more <a href="/" id="here"> here </a> </span>
          </div>
         </div>
      </div>
    </kinesis-container>

     <img id="logo" src="@/assets/logo2.png">
     <img src="../assets/blobRed.svg" id="blob">
     <img src="../assets/blobRed.svg" id="blob2">

  </div>
</template>
<script>
import squiglyBigBlue from '@/assets/squigly-big-blue.png'
import squiglyBigOrange from '@/assets/squigly-big-orange.png'
import squiglyBigYellow from '@/assets/squigly-big-yellow.png'
import squiglyBigGreen from '@/assets/squigly-big-green.png'
import squiglyBigViolet from '@/assets/squigly-big-violet.png'

import squiglySmallBlue from '@/assets/squigly-small-blue.png'
import squiglySmallOrange from '@/assets/squigly-small-orange.png'
import squiglySmallYellow from '@/assets/squigly-small-yellow.png'
import squiglySmallGreen from '@/assets/squigly-small-green.png'
import squiglySmallViolet from '@/assets/squigly-small-violet.png'

export default {
  name: 'Home',
  data () {
    return {

      squiglyBigBlue,
      squiglyBigOrange,
      squiglyBigYellow,
      squiglyBigGreen,
      squiglyBigViolet,
      squiglySmallOrange,
      squiglySmallYellow,
      squiglySmallGreen,
      squiglySmallViolet,
      squiglySmallBlue
      /* code: '' */
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

#blob2{
  position: fixed;
  left: -1012px;
  top:-1214px;
  opacity:0.25;
   width:1920px;
   pointer-events: none;
}

#logo{
  position: fixed;
  left: 10px;
  top:-15px;
  width:220px;
  z-index: 9;
   pointer-events: none;
}
#blob{
  position: fixed;
  left: -1170px;
  top:-1420px;
  width:1920px;
  z-index: 8;
   pointer-events: none;
}

#candy-wraper{
  width:300px;
  height: 340px;
   top:calc(50% - 150px);
  margin: 0 auto;
  position: relative;
}
#login-box{
  z-index: 2;
  width:300px;
  height: 340px;
  background: rgba( 255, 255, 255, 0.5 );

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
#login-text{
  font-size: 25.5px;
  margin-left:5.3%;
  margin-right:5%;
  line-height: 1.7;
  margin-top:38px;
}
#login-text-box{
 background: rgba( 255, 255, 255, 1 );
 text-align: center;
 border-radius: 30px 30px 0px 0px;
 border: 1px solid rgba( 255, 255, 255, 0 );
 height:240px;
}
button{
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.03 );
  background-color: white;
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:75%;
  border-radius: 10px;
  font-size: 25px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:24px;
  outline:none;
  transition: 0.2s;
}
#learn{
  line-height: 5.7;
  z-index:99;
  font-size: 20px;
  font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
}
@media screen and (max-width: 900px){
#candy-wraper{

   top:calc(50% - 135px);
}
#right-side{
  transform: scale(0.9);
  margin-left:20px;
}
#left-side{
  transform: scale(0.9);
}
}
@media screen and (max-width: 817px){
#candy-wraper{

   top:calc(50% - 130px);
}
#right-side{
  transform: scale(0.8);
  margin-left:40px;
}
#left-side{
  transform: scale(0.8);
}
}
@media screen and (max-width: 749px){
#candy-wraper{

   top:calc(50% - 115px);
}
#right-side{
  transform: scale(0.7);
  margin-left:80px;
}
#left-side{
  transform: scale(0.7);
   margin-left:-10px;
}
}

@media screen and (max-width: 700px){
#candy-wraper{
  display: none;
}
}
@media screen and (max-width: 550px){
#login-box{
margin-top:10px;
}
}
@media screen and (max-width: 500px){
#login-box{
margin-top:13px;
}
}
@media screen and (max-width: 356px){
#login-box{
width:270px;
  height: 305px;
}
#login-text{
   margin-left:5%;
  margin-right:5%;
  font-size: 23px;
   margin-top:28px;
}
#login-text-box{
  height:205px;
}
}

@media screen and (max-width: 303px){
#login-box{
  width:245px;
  height: 282px;
}
#login-text{
   margin-left:2.5%;
  margin-right:2.5%;
  font-size: 22px;
   margin-top:25px;
}
#login-text-box{
  height:193px;
}
button{
  margin-top:20px;
}
}

@media screen and (max-height:570px){
  #blob{
     width:1890px;
  /*display: none;*/
  }
  #blob2{
     width:1890px;
  /*display: none;*/
  }
  #logo{
  width:180px;
}
}
@media screen and (max-height:538px){
  #blob{
     width:1880px;
  }
  #blob2{
     width:1880px;
  }
  #logo{
     width:160px;
  }
}
@media screen and (max-height:490px){
  #blob{
     width:1870px;
  }
  #blob2{
     width:1870px;
  }
  #logo{
     width:140px;
  }
}
@media screen and (max-height:490px){
  #blob{
     width:1870px;
  }
  #blob2{
     width:1870px;
  }
  #logo{
     width:140px;
  }
}
@media screen and (max-height:473px){
  #learn{
    display: none;
  }

}
@media screen and (max-height:410px){
#candy-wraper{

   top:calc(50% - 130px);
}
#right-side{
  transform: scale(0.8);
  margin-left:40px;
}
#left-side{
  transform: scale(0.8);
}
}
@media screen and (max-height:385px){
#login-box{
width:270px;
  height: 305px;
}
#login-text{
   margin-left:5%;
  margin-right:5%;
  font-size: 23px;
   margin-top:28px;
}
#login-text-box{
  height:205px;
}
#login-box{
margin-top:2px;
}
 #blob{
     width:1860px;
  }
  #blob2{
     width:1860px;
  }
  #logo{
     width:120px;
  left: 7px;
  top:-10px;
  }
#candy-wraper{

   top:calc(50% - 110px);
}
#right-side{
  transform: scale(0.7);
  margin-left:80px;
}
#left-side{
  transform: scale(0.7);
   margin-left:-10px;
}
}

@media screen and (max-height:343px){
#login-box{
  width:245px;
  height: 282px;
}
#login-text{
   margin-left:2.5%;
  margin-right:2.5%;
  font-size: 22px;
   margin-top:25px;
}
#login-text-box{
  height:193px;
}
button{
  margin-top:20px;
}
}
@media screen and (max-height:315px){
#login-box{
  width:245px;
  height: 252px;
}
#login-text{
   margin-left:2.5%;
  margin-right:2.5%;
  font-size: 22px;
   margin-top:20px;
}
#login-text-box{
  height:183px;
}
button{
  margin-top:14px;
  height:40px;
}
  #candy-wraper{
  display: none;
}
}

#candy-cont{
 overflow:hidden;
 position: fixed;
 height: 100vh;
 top:0;
 left:-200px;
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
  transition: 0.15s;
  text-decoration: underline;
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
  border-radius: 50%;
   width:33px;
  height:33px;
   background-color:#bf9efc ;
}

#candy-12{
  left:-30px;
  top:85px;
}
#candy-12a{
  border-radius: 50%;
   width:33px;
  height:33px;
   background-color:#fcdd97 ;
}

#candy-11{
  left:590px;
  top:200px;
}
#candy-11a{
  border-radius: 50%;
   width:43px;
  height:43px;
   background-color:#84c2f1 ;
}

#candy-10{
  left:-90px;
  top:190px;
}
#candy-10a{

  border-radius: 50%;
   width:40px;
  height:40px;
   background-color:#84c2f1 ;
}

#candy-9{
  left:430px;
  top:-20px;
}
#candy-9a{
  border-radius: 50%;
   width:40px;
  height:40px;
   background-color:#fc9c76 ;
}

#candy-8{
  left:-150px;
  top:50px;
}
#candy-8a{
  background-color:#91d4d6 ;
  border-radius: 50%;
  width:45px;
  height:45px;
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

#telegram-text{
 color:#0088cc;
}
.home{
  background: linear-gradient(0deg, #f1e6f1, #edd8ee);
  height: 100%;
  overflow:hidden;
}

#icon{
  width:24px;
  height: 24px;
}

button:hover{
  background-color:#f6f6f6;

}
button:active{
  background-color:#ececec;
}

</style>

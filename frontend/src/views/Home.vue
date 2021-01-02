<template>
  <div class="home">
    <div>

    </div>
   <!-- <div id="title">
      <div id="bg-text">
     VOCA <br>
      </div>
    </div>-->
    <div id="candy">
        <img id="candy-1" src="@/assets/candy2.png" alt="">
        <img id="candy-2" class="candy" src="@/assets/candy2.png" alt="">
        <img id="candy-3" class="candy" src="@/assets/candy2.png" alt="">
        <img id="candy-4" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-5" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-6" class="candy" src="@/assets/candy2.png" alt="">
        <img id="candy-7" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-8" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-9" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-10" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-11" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-12" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-13" class="candy" src="@/assets/candy4.png" alt="">
        <img id="candy-14" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-15" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-16" class="candy" src="@/assets/candy3.png" alt="">
        <img id="candy-17" class="candy" src="@/assets/candy3.png" alt="">
    </div>
    <div id="bg-blur">
    </div>
   <img id="bg-img" src="@/assets/candy2.png" alt="">
 <div id="low-page">
    <div id="login-box">
      <div id="login-text-box">
      <div id="login-text">
     Ready to start? <br>
     To use Voca™ you have to login with your <br><span id="telegram-text"><font-awesome-icon :icon="['fab', 'telegram-plane']"/> Telegram</span> account.
     </div>
    </div>
     <button>
      Login
     </button>
    </div>
    </div>
  </div>
</template>
<script>

// @ is an alias to /src
export default {
  name: 'Home',
  components: {

  },
  data () {
    return {

    }
  }
}
</script>
<style scoped>
.candy{
  position:absolute;
}

#candy-17{
  left:500px;
  top:-50px;
  width:60px;
 transform: rotatez(-15deg);
}
#candy-16{
  left:460px;
  top:295px;
  width:60px;
 transform: rotatez(70deg);
}
#candy-15{
  left:-40px;
  top:295px;
  width:60px;
 transform: rotatez(-15deg);
}
#candy-14{
  left:-60px;
  top:-40px;
  width:80px;
 transform: rotatez(70deg);
}
#candy-13{
  left:525px;
  top:75px;
  width:55px;
 transform: rotatez(28deg);
}
#candy-12{
  left:-30px;
  top:85px;
  width:55px;
 transform: rotatez(28deg);
}
#candy-11{
  left:590px;
  top:200px;
  width:70px;
 transform: rotatez(28deg);
}
#candy-10{
  left:-90px;
  top:190px;
  width:65px;
 transform: rotatez(28deg);
}
#candy-9{
  left:430px;
  top:-20px;
  width:60px;
 transform: rotatez(28deg);
}
#candy-8{
  left:-150px;
  top:50px;
  width:70px;
 transform: rotatez(28deg);
}
#candy-7{
  left:-130px;
  top:60px;
  width:260px;
 transform: rotatez(27deg);
}
#candy-6{
  left:-130px;
  top:205px;
  width:180px;
 transform: rotatez(10deg);
}
#candy-5{
  left:440px;
  top:195px;
  width:200px;
 transform: rotatez(55deg);
}
#candy-4{
  left:440px;
  top:-40px;
  width:200px;
 transform: rotatez(-10deg);
}
#candy-3{
  left:440px;
  top:60px;
  width:250px;
 transform: rotatez(20deg);
}
#candy-2{
  left:-140px;
  top:-35px;
  width:200px;
 transform: rotatez(60deg);
}
#candy-1{
 width:500px;
 position:relative;
 opacity:0;
}
#candy{
  position:fixed;
   left: 50%;
   top:50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  -ms-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
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
 #bg-img{display: none;}
@media (max-aspect-ratio: 3840/2160){
  #bg-img{

 position:fixed;
height:100%;
width:auto;
left:0;
right:0;
top:0;
bottom:0;
margin:0;
}
}
@media (min-aspect-ratio: 3840/2160){
#bg-img{

position:fixed;
width:100%;
height: auto;
left:0;
right:0;
top:0;
bottom:0;
margin:0;
}
}

#bg-text{
position: relative;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);

}
#low-page{
 height: 100vh;
 z-index: 2;
}
#title{

  font-size: 19vw;
  color:rgba(57,60,101);
  opacity: 0.05;
  height: 100vh;
}
#login-text-box{
  background: rgba( 255, 255, 255, 0.27 );

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
}
#login-text{
  font-size: 24px;
  margin-top:12%;
  margin-left:7.5%;
  margin-right:7.5%;
  line-height: 1.7;
}
#login-box{
  z-index: 2;
  width:300px;
  height: 340px;
  background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.1 );
 backdrop-filter: blur( 15px );
 -webkit-backdrop-filter: blur( 15px );
 border-radius: 30px;
 /*border: 1px solid rgba( 255, 255, 255, 0.18 );*/
top: 50%;
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
margin-top:23px;

}
button:hover{
  background-color:#f5f5f5;
}
button:focus{
  outline:none;
}
</style>

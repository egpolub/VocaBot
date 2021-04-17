<template>
  <div class="about">

  <img src="../assets/blobRed.svg" id="blob9">

 <div class="card card-big">

<img src="../assets/blobOrange.svg" id="blob">
<img src="../assets/blobBlue.svg" id="blob2">
<img src="../assets/blobYellow.svg" id="blob3">
<img src="../assets/blobGreen.svg" id="blob5">
<img src="../assets/blobViolet.svg" id="blob6">

    <div id="title">
      Gamified <br> Language Learning
      </div>

     <div id="title2">
      Learn Languages by Contributing to the Community.
      </div>
      <button id="use-voca" v-on:click="redirect">
        Use Voca™
      </button>

</div>

<div id="cards">
<div v-on:mouseover="mouseOver" class="card card-small" >
  <div class="card-title">Telegram Integration
    <div class="card-text">
    Sign in using Telegram.
  </div>
  </div>

  <br>

     <font-awesome-icon id="tg" v-bind:class ="[{ tghover:tgAnim}, classObject ]" :icon="['fab', 'telegram-plane']"/>

  </div>

  <div v-on:mouseover="mouseOverUi" class="card card-medium">
  <div class="card-title"> Customizable UI
    <div class="card-text">
    Designed with user experience in mind.
  </div>
  </div>

  <div id="block-container">
    <div id="preview-block1" v-bind:class ="{ box1anim:uiAnim }">
      <div class="header">
        <div class="dot">
        </div>
      </div>
      <div class="text-example">
      </div>
       <div class="text-example">
      </div>
    </div>
    <div id="preview-block2" v-bind:class ="{ box2anim:uiAnim }">
      <div class="header">
         <div class="dot">
        </div>
      </div>
       <div class="text-example">
      </div>
       <div class="text-example">
      </div>
    </div>
    <div id="preview-block3" v-bind:class ="{ box3anim:uiAnim }">
      <div class="header">
         <div class="dot">
        </div>
      </div>
    <line-chart class="chart-example" :chartData="chartdata" :options="options"/>
    </div>
     <div id="preview-block4" v-bind:class ="{ box4anim:uiAnim }">
      <div class="header">
         <div class="dot">
        </div>
      </div>
      <div id="example-txt">
       <div class="text-example example-margin">
      </div>
       <div class="text-example example-margin">
      </div>
       <div class="text-example example-margin">
      </div>
       <div class="text-example example-margin">
      </div>
       <div class="text-example example-margin">
      </div>
       <div class="text-example example-margin">
      </div>
      </div>

    </div>
  </div>
  </div>

  <div v-on:mouseover="chartAnim" class="card card-long">
  <div class="card-title"> <span id="statistics">Statistics</span>
    <div class="card-text">
     Track your progress in learning.
  </div>
  </div>
<br>

<line-chart class="chart-example2" :chartData="chartdata2" :options="options"/>

  </div>

   <div class="card card-small-long">
  <div class="card-title">Expandable Dictionary
    <div class="card-text">
     Add or edit words and translations.
  </div>
  </div>
  <div id="row1">
  <div class="word word1 color3">
  ручка
  </div>
    <div class="word word1 translation">
  pen
  </div>
<div class="word word2 color2">
  книга
  </div>
   <div class="word word2 translation">
 book
  </div>
  <div class="word word3 color5">
  дом
  </div>
    <div class="word word3 translation">
  home
  </div>
  </div>

   <div id="row2">
  <div class="word word1 color4">
  хлеб
  </div>
 <div class="word word1 translation" >
  bread
  </div>
 <div class="word word2 color5">
  снег
  </div>
  <div class="word word2 translation" >
  snow
  </div>
 <div class="word word3 color1">
  ключ
  </div>
  <div class="word word3 translation" >
  key
  </div>
  </div>

 <div id="row3">
    <div class="word word1 color2">
  крыша
  </div>
  <div class="word word1 translation" >
  roof
  </div>
   <div class="word word2 color1">
  кошка
  </div>
 <div class="word word2 translation" >
  cat
  </div>
 <div class="word word3 color3">
  яблоко
  </div>
  <div class="word word3 translation">
  apple
  </div>
</div>
  </div>

</div>

<div id="design">Design by <a href="http://silentgarden.studio/">Silentgarden.studio</a> © 2020 VocaBot</div>
 <div id="blob-container">
     <img id="logo" src="@/assets/logo2.png">
     <img src="../assets/blobRed.svg" id="blob7">
  </div>
 </div>

</template>
<script>
import LineChart from '../components/Chart.vue'
import router from '@/router/index.js'
var colorArray = [
  'rgba(252,156,118,255)',
  'rgba(132,194,241,255)',
  'rgba(252,221,151,255)',
  'rgba(145,212,214,255)',
  'rgba(191,158,252,255)',
  'rgba(252,156,118,255)',
  'rgba(132,194,241,255)',
  'rgba(252,221,151,255)',
  'rgba(145,212,214,255)',
  'rgba(191,158,252,255)'
]
export default {
  components: { LineChart },
  created () {
    this.mouseOver()
    this.mouseOverUi()
    this.chartAnim()
  },
  computed: {
    classObject: function () {
      return 'tg' + this.tgColor
    }
  },
  methods: {
    redirect () {
      router.push({ name: 'Dashboard' }, () => {})
    },
    chartAnim () {
      if (this.graphAnim === false) {
        this.graphAnim = true
        setTimeout(() => { this.graphAnim = false }, 500)
        var arr = ''
        if (window.innerWidth >= 900) {
          arr = Array.from({ length: 10 }, () => Math.random() * 6 + 1.5)
        } else if (window.innerWidth >= 550) {
          arr = Array.from({ length: 7 }, () => Math.random() * 6 + 1.5)
        } else {
          arr = Array.from({ length: 5 }, () => Math.random() * 6 + 1.5)
        }
        this.chartdata2 = Object.assign({})
        this.$set(this.chartdata2, 'labels', arr)
        this.$set(this.chartdata2, 'datasets', [{
          type: 'line',
          backgroundColor: 'rgba(255,230,255, 0)',
          borderColor:
            'rgba(255,99,32, 0.16)',
          pointBorderColor: colorArray,
          pointBorderWidth: 6,
          borderWidth: 5,
          data: arr,
          order: 2
        },
        {
          type: 'bar',
          backgroundColor: colorArray,
          order: 1,
          data: arr.map(function (item) {
            return item - 1
          })
        }])
      }
    },
    mouseOver () {
      if (this.tgAnim === false) {
        this.tgAnim = true
        setTimeout(() => { this.tgColor = this.tgColor + 1 === 5 ? 0 : this.tgColor + 1 }, 1800)
        setTimeout(() => { this.tgAnim = false }, 2000)
      }
    },
    mouseOverUi () {
      if (this.uiAnim === false) {
        this.uiAnim = true
        const timeOut = (window.innerWidth >= 1170 || window.innerWidth <= 868) ? 2000 : 3000
        setTimeout(() => { this.uiAnim = false }, timeOut)
      }
    }
  },
  data: () => ({

    uiAnim: false,
    tgAnim: false,
    graphAnim: false,
    tgColor: 1,
    chartdata: {
      labels: 'labels',
      datasets: [
        {
          type: 'line',
          backgroundColor: 'rgba(255,230,255, 0)',
          borderColor: [
            'rgba(255,230,255, 1)'
          ],
          pointBorderColor: [
            'rgba(255,99,32, 0.7)',
            'rgba(54, 162, 235, 0.7)',
            'rgba(255, 206, 86, 0.7)',
            'rgba(75, 192, 192, 0.7)',
            'rgba(153, 102, 255, 0.7)'
          ],
          pointBorderWidth: 6,
          borderWidth: 5,
          data: [1.3, 3, 2, 4, 4.8]
        },
        {
          type: 'bar',
          backgroundColor: [
            'rgba(255,99,32, 0.6)',
            'rgba(54, 162, 235, 0.6)',
            'rgba(255, 206, 86, 0.6)',
            'rgba(75, 192, 192, 0.6)',
            'rgba(153, 102, 255, 0.6)'
          ],
          /* backgroundColor: 'rgba(255,230,255, 1)', */
          data: [0.9, 2.2, 1.5, 3.2, 4]
        }
      ]
    },
    options: {
      layout: {
        padding: {
          top: 10
        }
      },
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        yAxes: [{

          gridLines: {
            display: false
          },
          ticks: {
            display: false,

            min: 0
          }
        }],
        xAxes: [{
          gridLines: {
            display: false
          },
          /* type: 'linear', */
          position: 'bottom',
          ticks: {
            display: false
          },
          offset: true
        }]
      },
      /* animation: {
        duration: 0 // general animation time
      }, */
      hover: {
        mode: null,
        animationDuration: 0 // duration of animations when hovering an item
      },
      responsiveAnimationDuration: 0, // animation duration after a resize
      elements: {
        line: {
          tension: 0.3 // disables bezier curves
        }
      },
      legend: { display: false },
      tooltips: { enabled: false }
    },

    chartdata2: { }

  })

}
</script>
<style scoped>
#example-txt{
   margin-top:75px;
}
.example-margin{
  margin-top:40px !important;
     margin-left:20% !important;
   width:60% !important;
}
#blob9{
  position: fixed;
  left: -1012px;
  top:-1214px;
  opacity:0.25;
   width:1920px;
}

#logo{
  position: absolute;
  left: 10px;
  top:-15px;
  width:220px;
  z-index: 9;
}
#blob7{
  position: fixed;
  left: -1170px;
  top:-1420px;
 width:1920px;
}
#blob-container{
  width: 100vw;
  height: 100%;
  position: fixed;
  pointer-events: none;
}
#blob6{
  position:absolute;
  width:2500px;
  height:2500px;
  top:-1000px;
  z-index: -6;
  left:calc(40% - 130px);
  animation: rotate 76s infinite;
}
#blob5{
 position:absolute;
  width:2000px;
   height:2000px;
  top:-300px;
  z-index: -5;
  left:calc(40% + 57px);
   animation: rotate 88s infinite;
}

#blob2{
  position:absolute;
  width:2000px;
    height:2000px;
  top:-100px;
  z-index: -4;
  left:calc(40% + 85px);
   animation: rotate 60s infinite;
}
#blob3{
  position:absolute;
  width:2000px;
    height:2000px;
  top:30px;
  z-index: -3;
  left:calc(40% + 62px);
   animation: rotate 80s infinite;
}
#blob{

  position:absolute;
  width:2000px;
    height:2000px;
  top:160px;
  left:calc(40% + 14px);
 z-index: -2;
 animation: rotate 72s infinite;
}

 @keyframes rotate {
    0% { transform: rotateZ(0deg) scale(1.2); }
    50% { transform: rotateZ(180deg) scale(1.1); }
    100% { transform: rotateZ(0deg) scale(1.2); }
   }
.tg4{
  color:rgba(153, 102, 255, 0.67) !important;
}
.tg3{
 color:rgba(75, 192, 192, 0.8) !important;
}
.tg2{
  color: rgba(54, 162, 235, 0.7)!important;
}
.tg1{
 color:rgba(255, 80, 211, 0.6) !important;
}
.tg0{
  color:rgba(255,99,32, 0.45) !important;
}
.color5{
  background-color:rgba(153, 102, 255, 0.6) !important;
}
.color4{
  background-color:rgba(75, 192, 192, 0.6) !important;
}
.color3{
  background-color:rgba(255, 206, 86, 0.6) !important;
}
.color2{
  background-color:rgba(54, 162, 235, 0.6) !important;
}
.color1{
  background-color:rgba(255,99,32, 0.6) !important;
}
#row3{
  position: absolute;
  top:225px;
  left:66.75%;
   width: 100%;
}
#row2{
  position: absolute;
  top:225px;
  left:37.5%;
   width: 100%;
}
#row1{
  position: absolute;
  top:225px;
  left:8.25%;
   width: 100%;
}
.arrow{
    top:213px;
    position: absolute;
    font-size: 50px;
    left:243px;
    color: #70407c30
}

.word3{
  top:55px;
}

.word2{
  top:-55px;
}

.word1{
  top:0;
}

.translation:hover{
 opacity:0;
}
.word:not(.translation){
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.05);
}
.word{
transition: 0.2s;
  background:rgba(255, 255, 255, 1);
 /* background: rgba( 255, 255, 255, 0.4);*/
  border-radius: 15px ;
  position: absolute;
 font-size:29px;

  z-index: 0;
  font-family: ComposeRegular;
  width: 25%;
 /* width:135px;*/
  height:40px;
  padding-top:5px;
}
.chart-example2{
  position: relative;
  width:89%;
  height:55%;
  top:0px;
  margin-left:5%;
}

.chart-example{
  position: relative;
  width:100%;
  height:50%;
  top:13.3%;
  margin-left:6%;
}
.text-example{
   height:9px;
  width:50%;
  background-color:rgb(241, 221, 238);
  border-radius:30px /*30px 0px 0px;*/;
  margin-left:25%;
  margin-top:24px;
  }
.dot{
  position:relative;
   border-radius:50%;
   height:20px;
   width:20px;
   background-color:white;
   top:15px;
   right:15px;
   float:right;
}
.header{
  height:50px;
  width:100%;
  background-color:rgb(245, 226, 241);
  border-radius:30px 30px 0px 0px;
}
#block-container{
  height:100%;
  position:relative;
  top:70px;
  margin-left:calc(50% - 177px);
}

@keyframes move3 {
    0% {  top:175px; }
    10% { top:175px; }
    25% { top:175px; }
    35% {  top:0px; }
    50% { top:0px; }
    60% {  top:0px; }
    75% { top:0px; }
    85% {  top:175px; }
   }
 @keyframes move2 {
    0% { left:155px; top:0px;}
    10% { left:0px;  top:0px;}
    25% { left:0px; top:0px;}
    35% { left:0px; top:325px;}
    50% { left:0px;  top:325px;}
    60% { left:155px; top:325px;}
    75% { left:155px;  top:325px;}
    85% { left:155px; top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:225px;top:0px; }
    25% { left:225px;top:0px; }
    35% { left:225px;top:325px; }
    50% { left:225px;top:325px; }
    60% { left:0px;top:325px; }
    75% { left:0px;top:325px; }
    85% { left:0px;top:0px; }
}

#preview-block4{
  display:none;
  overflow:hidden;
}

#preview-block3{
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:300px;
  width:355px;
  left:0px;
  top:175px;
  border-radius: 30px;
  position:absolute;
}

#preview-block2{
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:150px;
  width:200px;
  left:155px;
  top:0px;
  border-radius: 30px;
  position:absolute;
}

#preview-block1{
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:150px;
  width:130px;
  left:0px;
  top:0px;
  border-radius: 30px;
  position:absolute;
}

.box1anim{
  animation: move1 2s;
}
.box2anim{
  animation: move2 2s;
}
.box3anim{
  animation: move3 2s;
}
.box4anim{
  animation: move4 2s;
}

@keyframes fly {
    92.001% { top:117px; left:-120px;}
    100% { top:12px;  left:-2px;}
    27% { top:13px;  left:1px;}
    52% { top:6px; left:-5px; }
    67% { top:14px; left:-10px; }
     76% { top:14px; left:-10px; }
    86% {  top:-170px;  left:186px;  }
    92% {  top:-170px; left:186px; }
   }
.tghover{
   animation: fly 2s;
 }
#tg{
  position: relative;
  font-size: 90px;
   top:12px;  left:-2px;
  color:rgba(54, 162, 235, 0.8);
  z-index:-5;
}
#cards{
  width:100%;
  top:50px;
  position: relative;
}
#design{
  position: absolute;
  z-index:99999;
  font-size: 15px;
  color:#9c478d80;
  right:20px;
   font-family:  Compose, Avenir, Helvetica, Arial, sans-serif;
  top: 1700px;
}

.card-text{
   font-family: ComposeRegular;
  font-size: 22px;
 margin-top:10px;
  margin-left:5%;
  margin-right:5%;
}

#title{
  font-family: Compose;
  font-size: 90px;
   z-index:1;
  padding-top:75px;
  margin-left:70px;

}
#title2{
  z-index:1;
  font-family: ComposeRegular;
  font-size: 30px;
  padding-top:40px;
  margin-left:70px;
}

.card-big{
   width: 80%;
  height: 600px;
   top:130px;
   margin-left:10%;
  text-align: left;
  background-color: #ffffff  !important;
}

.card-small-long{
  text-align: center;
  width: 535px;
  height: 350px;
  background: rgba( 255, 255, 255, 0.4 );
  top:800px;
  margin-left:calc(10% + 611px);
}
.card-long {
  text-align: center;
  width: 910px;
  height: 350px;
  background: rgba( 255, 255, 255, 0.4 );
  top:1230px;
  margin-left:calc(10% + 603px);
}
.card-medium {
  text-align: center;
  width: 529px;
  height: 780px;
  background: rgba( 255, 255, 255, 0.4 );
  top:800px;
  margin-left:10%;
}
.card-small {
  text-align: center;
  width: 300px;
  height: 350px;
  background: rgba( 255, 255, 255, 0.4 );
  top:800px;
  margin-left:calc(10% + 1219px);
}
.card-title{
  background-color: white;
   border-radius: 30px 30px 0px 0px;
   padding-bottom:30px;
   font-family: Compose;
  font-size: 40px;
  padding-top:30px;
  z-index:90;
}
.about{
 /* height: 1730px;*/
  width: 100%;
  height: 1730px;
  background: linear-gradient(0deg, #f1e6f1, #edd8ee);
 /* color:rgb(223, 163, 186);*/
}
#use-voca{
  /*background: rgb(202, 111, 255);*/
  background: rgb(135, 133, 255);
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.12);
  border: 0px solid rgba(255, 0, 255, 0.4);
  height:80px;
  width:190px;
  border-radius: 15px;
  font-size: 30px;
  color:#ffffff;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:67px;
  outline:none;
  transition: 0.2s;
  margin-left:70px;
  padding-top:3px;
}
#use-voca:hover{
  background-color:rgb(114, 112, 236);

}
#use-voca:active{
  background-color:rgb(101, 99, 212);
}
@media screen and (min-width:1920px){
  .card-big{
   width: 1536px;
   margin-left:calc(50% - 768px);
}
.card-small-long{
  width: 525px;
   margin-left:calc(50% - 146px);
}
.card-long {
  margin-left:calc(50% - 146px);
}
.card-medium {

  margin-left:calc(50% - 768px);
}
.card-small {
  margin-left:calc(50% + 466px);
}
}
@media screen and (max-width:1920px){

.card-small-long{
    margin-left:calc(10% + 622px);
    width: calc(80% - 1010px);
}
.card-long {
  width: calc(80% - 625px);
  margin-left:calc(10% + 622px);
}
.card-medium {

 margin-left:10%;
}
.card-small {
  margin-left:calc(90% - 300px);
}
}
@media screen and (max-width:1875px) {
.card-title{
   font-size: 33px;
   margin-bottom:8px;
}
}
@media screen and (max-width:1745px) {
.card-small-long{
    margin-left:calc(10% + 622px);
    width: 393px;
}
.card-small {
  width:  calc(80% - 1102px);
  margin-left:calc(10% + 1100px);
}
}
@media screen and (max-width:1700px) {
  .card-small-long{
    margin-left:calc(90% - 740px);
}
.card-small {
   width:  258px;
   margin-left:calc(90% - 262px);
}
.card-medium {
  width: calc(80% - 831px);
}
.card-long {
  width: 735px;
  margin-left:calc(90% - 740px);
}
}
@media screen and (max-width:1578px){
  #block-container{
  top:55px;
}
}
@media screen and (max-width:1572px) {
.card-small-long{
    margin-left:calc(90% - 740px);
    width:calc(-80% + 1650px);
}
.card-medium {
  width: 427px;
}
.card-long {
 height:calc(-90vw + 1760px);
  top: calc(90vw - 180px);
}
.chart-example2{
  top:calc(-40.5vw + 634px);
}

}

@media screen and (max-width:1530px) {
  .card-small {
   width: calc(80% - 622px);
  top:1228px;
  margin-left:calc(10% + 622px);
}
.card-small-long{
    margin-left:calc(10% + 622px);
    width: calc(80% - 622px);
}
.card-long {
 margin-left:10%;
  height: 400px;
  top:1670px;
  width: 80%;
}
.card-medium {
  width: 529px;
}
.card-title{
   font-size: 40px;
   margin-bottom:0px;
}
  #block-container{
  top:70px;
}
#tg{
  font-size: 120px;
  margin-top:6px;
}
.about{
  height: 2210px;
}
#design{
  top: 2180px;
}
.chart-example2{
  top:22px;
}
}
@media screen and (max-width:1372px) {
.card-title{
   font-size: 33px;
   margin-bottom:8px;
}
#statistics{
  font-size: 40px;
}
.chart-example2{
  top:14px;
}
}
@media screen and (max-width:1296px) {
  .card-small {
   width: 415px;
  margin-left:calc(90% - 415px);
}
.card-small-long{
    margin-left:calc(90% - 415px);
    width:  415px;
}
.card-medium {
  width: calc(80% - 508px);
}
}
@media screen and (max-width:1208px) {
  .card-medium {
  width: 458px;
}
.card-small {

   height:calc(-90vw + 1430px);
   top: calc(90vw + 150px);
}
#tg{
  font-size: calc(-80vw + 1075px);
}
.card-long {
  top:calc(80vw + 700px);
}
.about{
  height: calc(80vw + 1240px);
}
#design{
  top:  calc(80vw + 1210px);
}
}
@media screen and (max-width:1170px) {
  .card-title{
   font-size: 38px;
   margin-bottom:3px;
}
#title{
  font-size: 85px;
  padding-top:85px;
}

.card-medium {
  width: 80%;
}
.card-small-long{
   height: 350px;
  top:1635px;
  margin-left:10%;
  width: calc(80% - 360px);
}
.card-long {
  height: 350px;
  top:2043px;
}
.card-small {
  width: 300px;
  height: 350px;
  top:1635px;
  margin-left:calc(90% - 300px);
}
.about{
  height: 2540px;
}
#design{
  top: 2510px;
}
.chart-example2{
  top:-4px;
}
#tg{
  font-size: 90px;
  margin-top:0px;
}

#block-container{
  position:relative;
  top:70px;
  margin-left:calc(10% + 10px);
}
@keyframes move4 {
    0% { left:383px; }
    6.7% { left:0px; }
     83.3% { left:0px; }
    90% { left:383px; }
   }
@keyframes move3 {
    0%    {  top:175px;left:0px; }
    6.7%  { top:175px;left:calc(90% - 375px); }
    33.3% { top:175px;left:calc(90% - 375px);}
    40%   {  top:0px;left:calc(90% - 375px); }
    66.7% { top:0px;left:calc(90% - 375px);}
    73.3% {  top:175px;left:calc(90% - 375px);}
    83.3% { top:175px;left:calc(90% - 375px);}
    90%   {  top:175px;left:0px;}
   }
 @keyframes move2 {
    0% { left:155px; top:0px;}
    6.7% { left:calc(90% - 220px);  top:0px;}
    16.7% { left:calc(90% - 220px); top:0px;}
    23.3%{ left:calc(90% - 375px);  top:0px;}
    33.3%  { left:calc(90% - 375px); top:0px;}
    40% { left:calc(90% - 375px); top:325px;}
    50%{ left:calc(90% - 375px);  top:325px;}
    56.7% { left:calc(90% - 220px); top:325px;}
    66.7% { left:calc(90% - 220px);  top:325px;}
    73.3% { left:calc(90% - 220px); top:0px;}
    83.3% { left:calc(90% - 220px);top:0px; }
    90%   { left:155px;top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    6.7% { left:calc(90% - 375px); top:0px; }
    16.7% {left:calc(90% - 375px); top:0px;}
     23.3%{ left:calc(90% - 155px);top:0px; }
   33.3% { left:calc(90% - 155px);top:0px; }
    40% { left:calc(90% - 155px);top:325px; }
    50% { left:calc(90% - 155px);top:325px; }
     56.7% { left:calc(90% - 375px);top:325px; }
    66.7%  { left:calc(90% - 375px);top:325px; }
    73.3%{ left:calc(90% - 375px);top:0px; }
    83.3%  { left:calc(90% - 375px);top:0px;}
    90%   { left:0px;top:0px;}
   }
.box1anim{
  animation: move1 3s;
}
.box2anim{
  animation: move2 3s;
}
.box3anim{
  animation: move3 3s;
}
.box4anim{
  animation: move4 3s;
}
#preview-block4{
  display:initial;
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:475px;
  width:calc(90% - 405px);
  left:383px;
  top:0px;
  border-radius: 30px;
  position:absolute;
}

}
@media screen and (max-width:1120px) {
#title{
  font-size: 80px;
  padding-top:95px;
  margin-left:60px;
}
#title2{
  font-size: 28px;
  margin-left:60px;
}
#use-voca{
  margin-left:60px;
}
}
@media screen and (max-width:1024px) {
#title{
  font-size: 75px;
  padding-top:90px;
  margin-left:60px;
}
#title2{
  font-size: 30px;
  margin-left:60px;
  margin-right:60px;
}
#use-voca{
   margin-top:50px;
  margin-left:60px;
}
.card-title{
   font-size: 35px;
   margin-bottom:8px;
}
}
@media screen and (max-width:976px) {
  #title{
  font-size: 70px;
  padding-top:90px;
}
.card-small {
  width: calc(80% - 480px);
  margin-left:calc(10% + 480px);
}
.card-small-long{

  margin-left:10%;
  width: 420px;
}
}
@media screen and (max-width:935px) {
  #title{
  font-size: 70px;
  padding-top:90px;
  margin-left:40px;

}
  #title2{
  margin-left:42px;
}
#use-voca{
   margin-top:50px;
  margin-left:40px;
}
.card-medium{
top:740px;
}
.card-long{
   top:1990px;
}
.card-small {
  width:80%;
  margin-left:10%;
  top:2405px;
  height: 310px;
}
.card-small-long{
  margin-left:10%;
  width: 80%;
  top:1580px;
}
.card-title{
   font-size: 40px;
   margin-bottom:3px;
}
.about{
  height: 2845px;
}
#design{
  top: 2815px;
}
}
@media screen and (max-width:868px) {

  @keyframes move3 {
    0% {  top:175px; }
    10% { top:175px; }
    25% { top:175px; }
    35% {  top:0px; }
    50% { top:0px; }
    60% {  top:0px; }
    75% { top:0px; }
    85% {  top:175px; }
   }
 @keyframes move2 {
    0% { left:155px; top:0px;}
    10% { left:0px;  top:0px;}
    25% { left:0px; top:0px;}
    35% { left:0px; top:325px;}
    50% { left:0px;  top:325px;}
    60% { left:155px; top:325px;}
    75% { left:155px;  top:325px;}
    85% { left:155px; top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:225px;top:0px; }
    25% { left:225px;top:0px; }
    35% { left:225px;top:325px; }
    50% { left:225px;top:325px; }
    60% { left:0px;top:325px; }
    75% { left:0px;top:325px; }
    85% { left:0px;top:0px; }
   }
   .box1anim{
  animation: move1 2s;
}
.box2anim{
  animation: move2 2s;
}
.box3anim{
  animation: move3 2s;
}
.box4anim{
  animation: move4 2s;
}
  #preview-block4{
  display:none;
}
#block-container{
  position:relative;
  top:70px;
  margin-left:calc(50% - 177px);
}
  #title{
    padding-top:110px;
  font-size: 60px;
}
  #title2{
     margin-right:120px;
}
}
@media screen and (max-width:756px) {

  #title{
     line-height:1.1;
    margin-right:40px;
    margin-left:50px;
    font-size: 80px;
     padding-top:80px;
 }
  #title2{
     margin-left:50px;
     margin-right:40px;
 }
 #use-voca{
  margin-left:50px;
}
.card-big{
  height:680px;
}
.card-medium {
 top:822px;
}
.card-long{
top:2073px;
}
.card-small {
  top:2485px;
}
.card-small-long{
top:1662px;
}
.about{
  height: 2925px;
}
#design{
  top: 2895px;
}
}
@media screen and (max-width:655px) {
    #blob6{
 display:none;
}
#blob5{
 display:none;
}

#blob2{
 display:none;
}
#blob3{
  display:none;
}
#blob{
display:none;
}

   #title{
 line-height:1.1;

  margin-left:0px;
  margin-right:0px;
 }
  #title2{
     line-height:1.5;
     padding-top:30px;
      margin-left:25px;
     margin-right:25px;
 }
 #use-voca{
  margin-top:60px;
  margin-left:0px;
  margin-right:0px;
}
.card-big{
  text-align: center;

}
}
@media screen and (max-width:587px) {
#block-container{
  position:relative;
  top:30px;
  width:80%;
  margin-left:10%;
}

#preview-block3{
  width:100%;
}

#preview-block2{
  width:calc(100% - 155px);
}

#preview-block1{
  width:130px;
}

  @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:calc(100% - 130px);top:0px; }
    25% { left:calc(100% - 130px);top:0px; }
    35% { left:calc(100% - 130px);top:325px; }
    50% { left:calc(100% - 130px);top:325px; }
    60% { left:0px;top:325px; }
    75% { left:0px;top:325px; }
    85% { left:0px;top:0px; }
   }

#title{
  padding-top:70px;
}

#use-voca{
  margin-top:33px;
}

.card-title{
   font-size: 33px;
   margin-bottom:8px;
}
.chart-example2{
  top:-9px;
}
.card-medium {
 height:700px;
}

.card-long{
top:1998px;
}
.card-small {
  top:2413px;
}
.card-small-long{
top:1585px;
}
.about{
  height: 2843px;
}
#design{
  top: 2813px;
}
}
@media screen and (max-width:542px) {
    #title{
      font-size:calc(11vw + 10px);
    padding-top:80px;
   }
    #title2{
      font-size:calc(2vw + 15px);
   }

#use-voca{
  margin-top:40px;
}

  .chart-example2{
  top:-10px;
}
.card-title{
   font-size: 30px;
   margin-bottom:12px;
}
.card-text{
  font-size: 20px;
 margin-top:10px;

}
.word:not(.translation){
 font-size:25px;
padding-top:7px;
height:38px;
}
#row3{
  top:217px;
}
#row2{
 top:217px;
}
#row1{
  top:217px;
}
}
@media screen and (max-width:500px) {
.card-medium {
 height:600px;
}
#preview-block3{
 height:calc(100% - 400px);
}
#block-container{
  top:15px;
}
 @keyframes move2 {
    0% { left:155px; top:0px;}
    10% { left:0px;  top:0px;}
    25% { left:0px; top:0px;}
    35% { left:0px; top:calc(100% - 375px);}
    50% { left:0px;  top:calc(100% - 375px);}
    60% { left:155px; top:calc(100% - 375px);}
    75% { left:155px;  top:calc(100% - 375px);}
    85% { left:155px; top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:calc(100% - 130px);top:0px; }
    25% { left:calc(100% - 130px);top:0px; }
    35% { left:calc(100% - 130px);top:calc(100% - 375px); }
    50% { left:calc(100% - 130px);top:calc(100% - 375px); }
    60% { left:0px;top:calc(100% - 375px); }
    75% { left:0px;top:calc(100% - 375px); }
    85% { left:0px;top:0px; }
   }
.card-title{
   font-size: 30px;
   margin-bottom:27px;
   padding-bottom: 20px;
   padding-top:25px;
}
#tg{
  margin-top:-5px;
}
#row3{
  top:190px;
}
#row2{
 top:190px;
}
#row1{
  top:190px;
}
.card-text{
  font-size: 18px;
}
  .chart-example2{
  top:-29px;
}
.card-big{
  height:555px;
}
.card-long{
top:1693px;
height:300px;
}
.card-small {
  top:2045px;
}
.card-medium{
  top:685px;
}
.card-small-long{
top:1335px;
height:310px;
}
.about{
  height: 2475px;
}
#design{
  top: 2445px;

  font-size:13px;

}
 #title{

    padding-top:60px;
   }
    #title2{
       margin-top:-5px;
       margin-left:15px;
       margin-right:15px;
   }

#use-voca{
  margin-top:30px;
}
}
@media screen and (max-width:420px) {
  .card-text{
  font-size: 20px;
 margin-top:5px;

}
  #statistics{
    font-size: 33px;
  }
  #preview-block3{
 height:200px;
}
 @keyframes move2 {
    0% { left:155px; top:0px;}
    10% { left:0px;  top:0px;}
    25% { left:0px; top:0px;}
    35% { left:0px; top:220px;}
    50% { left:0px;  top:220px;}
    60% { left:155px; top:220px;}
    75% { left:155px;  top:220px;}
    85% { left:155px; top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:calc(100% - 130px);top:0px; }
    25% { left:calc(100% - 130px);top:0px; }
    35% { left:calc(100% - 130px);top:220px; }
    50% { left:calc(100% - 130px);top:220px; }
    60% { left:0px;top:220px; }
    75% { left:0px;top:220px; }
    85% { left:0px;top:0px; }
   }
     .chart-example2{
  top:-3px;
}
  .card-title{
   font-size: 28px;
   margin-bottom:12px;
padding-bottom: 15px;
padding-top:20px;
}
  .card-small {
    top:1960px;
  margin-left:7%;
  width:86%;
  height:270px;
}
#row3{
  top:194px;
}
#row2{
 top:194px;
}
#row1{
  top:194px;
}
  .card-small-long {
    top:1275px;
  margin-left:7%;
  width:86%;
  height:310px;
}
  .card-medium {
     top:655px;
  margin-left:7%;
  width:86%;
 height:570px;
}
  .card-long {
    top:1633px;
  margin-left:7%;
  width:86%;
  height:280px;
}
  .card-big {
  margin-left:7%;
  width:86%;
  height:520px;
}
.about{
  height: 2350px;
}
#design{
  top: 2320px;
}
.word{
 font-size:26px;
  height:38px;
  padding-top:7px;
}
.word:not(.translation){
 font-size:22px;
padding-top:9px;
height:36px;
}
}
@media screen and (max-width:370px) {

  #preview-block2{
  width:100px;
   left:calc(100% - 100px);
}

#preview-block1{
  width:calc(100% - 125px);
}
@keyframes move2 {
    0% { left:calc(100% - 100px); top:0px;}
    10% { left:0px;  top:0px;}
    25% { left:0px; top:0px;}
    35% { left:0px; top:220px;}
    50% { left:0px;  top:220px;}
    60% { left:calc(100% - 100px); top:220px;}
    75% { left:calc(100% - 100px);  top:220px;}
    85% { left:calc(100% - 100px); top:0px;}
   }
 @keyframes move1 {
    0% { left:0px; top:0px;}
    10% { left:125px;top:0px; }
    25% { left:125px;top:0px; }
    35% { left:125px;top:220px; }
    50% { left:125px;top:220px; }
    60% { left:0px;top:220px; }
    75% { left:0px;top:220px; }
    85% { left:0px;top:0px; }
   }

  #row3{
  top:191px;
}
#row2{
 top:191px;
}
#row1{
  top:191px;
}
  .card-small-long {
    top:1255px;
}
  .card-medium {
     top:635px;
}
  .card-long {
    top:1613px;
}

    .card-small {
        top:1940px;
  height:260px;
}
   .card-big {
  height:500px;
}
    .chart-example2{
  top:-24px;
}
  .card-title{
    font-size: 22px;
}
.word:not(.translation){
 font-size:20px;
padding-top:10px;
height:35px;
}
.word{
 font-size:24px;
  height:37px;
  padding-top:8px;
}
#statistics{
  font-size: 30px;
}
.about{
  height: 2320px;
}
#design{
  top: 2290px;
}
}
@media screen and (max-width:320px) {
.about{
  height: 2300px;
}
#design{
 display:none;
}
.word:not(.translation){
 font-size:18px;
padding-top:11.5px;
height:33.5px;
}
.word{
 font-size:23px;
  height:37px;
  padding-top:8px;
}
}
@media screen and (max-height:570px){
  #blob9{
     width:1890px;
  /*display: none;*/
  }
  #blob7{
     width:1890px;
  /*display: none;*/
  }
  #logo{
  width:180px;
}
}
@media screen and (max-height:538px){
  #blob9{
     width:1880px;
  }
  #blob7{
     width:1880px;
  }
  #logo{
     width:160px;
  }
}
@media screen and (max-height:490px){
  #blob9{
     width:1870px;
  }
  #blob7{
     width:1870px;
  }
  #logo{
     width:140px;
  }
}
@media screen and (max-height:490px){
  #blob9{
     width:1870px;
  }
  #blob7{
     width:1870px;
  }
  #logo{
     width:140px;
  }
}

@media screen and (max-height:385px){
 #blob9{
     width:1860px;
  }
  #blob7{
     width:1860px;
  }
  #logo{
     width:120px;
  left: 7px;
  top:-10px;
  }
}
.card {
  overflow:hidden;
   background: rgba( 255, 255, 255, 0.55 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: absolute;

  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.05);
  z-index: 0;
}
</style>

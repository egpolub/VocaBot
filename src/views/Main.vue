<template>
  <div class="about">
    <div class="home">
  <img src="../assets/blobRed.svg" id="blob9">

 <div class="card card-big">
   <div id="grad"></div>

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
  </div>
  </div>

  <div v-on:mouseover="chartAnim" class="card card-long">
  <div class="card-title"> Statistics
    <div class="card-text">
     Track your progress in learning.
  </div>
  </div>
<br>
<div >
<line-chart class="chart-example2" :chartData="chartdata2" :options="options"/>
</div>
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
        /* this.chartdata2.datasets[0].data = Array.from({ length: 10 }, () => Math.floor(Math.random() * 6 + 1)) */
        var arr = Array.from({ length: 10 }, () => Math.random() * 6 + 1.5)

        this.chartdata2 = Object.assign({})
        this.$set(this.chartdata2, 'labels', '1234567891')
        this.$set(this.chartdata2, 'datasets', [])
        this.chartdata2.datasets.push({
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
          /* backgroundColor: 'rgba(255,230,255, 1)', */
          data: arr.map(function (item) {
            return item - 1
          })
        })
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
        setTimeout(() => { this.uiAnim = false }, 2000)
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
      responsiveAnimationDuration: 2, // animation duration after a resize
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
#blob9{
  position: fixed;
  left: -1000px;
  top:-1200px;
  opacity:0.25;
   width:1920px;
}

#logo{
  position: absolute;
  left: 10px;
  top:-495px;
  width:220px;
  z-index: 9;

}
#blob7{
  position: relative;
  left: -1170px;
  top:-1900px;
 width:1920px;
}
#blob-container{
  width: 100vw;
  height: 100%;
  position: fixed;
  pointer-events: none;
}
#grad{
   z-index:-10;
   position:absolute;
   width:80%;
   height:100%;
   background-color: #ffffff;
  /* background: linear-gradient(90deg, #fff5ffff,#fff5ffff,#ffffff00);*/
}
#blob6{
 position:absolute;
  width:2500px;
  top:-1000px;
  z-index: -6;
  left:260px;
   animation: rotate 76s infinite;
}
#blob5{
 position:absolute;
  width:2000px;
  top:-300px;
  z-index: -5;
  left:670px;
   animation: rotate 88s infinite;
}

#blob2{
  position:absolute;
  width:2000px;
  top:-100px;
  z-index: -4;

  left:700px;
   animation: rotate 60s infinite;
}
#blob3{
  position:absolute;
  width:2000px;
  top:30px;
  z-index: -3;

  left:680px;
   animation: rotate 80s infinite;
}
#blob{
  position:absolute;
  width:2000px;
  top:160px;
  left:600px;
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
  left:350px;
}
#row2{
  position: absolute;
  top:225px;
  left:200px;
}
#row1{
  position: absolute;
  top:225px;
  left:50px;
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
  width:135px;
  height:40px;
  padding-top:5px;
}
.chart-example2{
  position: relative;
  width:89%;
  height:192px;
  top:0px;
  margin-left:5%;
}

.chart-example{
  position: relative;
  width:100%;
  height:150px;
  top:40px;
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
  position:relative;
  top:70px;
  margin-left:-2px;
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
    0% { left:245px; top:0px;}
    10% { left:90px;  top:0px;}
    25% { left:90px; top:0px;}
    35% { left:90px; top:325px;}
    50% { left:90px;  top:325px;}
    60% { left:245px; top:325px;}
    75% { left:245px;  top:325px;}
    85% { left:245px; top:0px;}
   }
 @keyframes move1 {
    0% { left:90px; top:0px;}
    10% { left:315px;top:0px; }
    25% { left:315px;top:0px; }
    35% { left:315px;top:325px; }
    50% { left:315px;top:325px; }
    60% { left:90px;top:325px; }
    75% { left:90px;top:325px; }
    85% { left:90px;top:0px; }
   }
#preview-block3{
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:300px;
  width:355px;
  left:90px;
  top:175px;
  border-radius: 30px;
  position:absolute;
  transition: all  1s;

}
#preview-block2{
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:150px;
  width:200px;
  left:245px;
  top:0px;
  border-radius: 30px;
  position:absolute;
  transition: all  1s;

}
#preview-block1{
 box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.02 );
  background-color: white;
  height:150px;
  width:130px;
  left:90px;
  top:0px;
  border-radius: 30px;
  position:absolute;
  transition: all 1s;

}
.box1anim{
/*animation: move3 4s infinite;*/
  animation: move1 2s;
}
.box2anim{
/*animation: move2 4s infinite;*/
  animation: move2 2s;
}
.box3anim{
/*animation: move1 4s infinite;*/
  animation: move3 2s;
}

@keyframes fly {
    92.001% { top:117px; left:-120px;}
    100% { top:12px;  left:-2px;}
    27% { top:13px;  left:1px;}
    52% { top:6px; left:-5px; }
    67% { top:14px; left:-10px; }
     76% { top:14px; left:-10px; }
    86% {  top:-133px;  left:180px;  }
    92% {  top:-133px; left:180px; }
   }
.tghover{
   animation: fly 2s;
 }
#tg{
  position: relative;
  font-size: 90px;
   top:12px;  left:-2px;
  /*margin-left:-4px;
  margin-top:11px;*/
  color:rgba(54, 162, 235, 0.8);
  /*animation: fly 3s  infinite;*/

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
.card-title{
  background-color: white;
   border-radius: 30px 30px 0px 0px;
   padding-bottom:30px;
   font-family: Compose;
  font-size: 40px;
  padding-top:30px;
  z-index:90;

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

}

.card-small-long{
  text-align: center;
  width: 535px;
  height: 350px;
  background: rgba( 255, 255, 255, 0.4 );
  top:800px;
  margin-left:calc(50% - 150px);
}
.card-long {
  text-align: center;
  width: 910px;
  height: 350px;
  background: rgba( 255, 255, 255, 0.4 );
  top:1230px;
  margin-left:calc(50% - 150px);
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
  margin-left:calc(50% + 460px);
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
.home {
  /*background: linear-gradient(150deg, #edd8ee, #ebd1d3);*/
height:480px;
  width: 100%;
  top:0;
}

.about{
 /* height: 1730px;*/
  width: 100%;
  height: 1730px;
  background: linear-gradient(0deg, #f1e6f1, #edd8ee);
 /* color:rgb(223, 163, 186);*/
}

#login-button{
  background-color: white;
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:75%;
  border-radius: 10px;
  font-size: 25px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:56px;
  outline:none;
  transition: 0.2s;
}
#login-button:hover{
  background-color:#f6f6f6;

}
#login-button:active{
  background-color:#ececec;
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

</style>

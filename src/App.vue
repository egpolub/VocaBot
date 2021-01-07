<template>
  <div id="app">

      <router-link :to="{name: 'Login'}">
      </router-link>
    <router-view/>
  </div>
</template>

<style>
.shake {
  animation: shake 0.25s cubic-bezier(.36,.07,.19,.97) both;
  transform: translate3d(0, 0, 0);
}
@keyframes shake {
  20% {
    transform: translate3d(-2px, 0, 0);
  }
  40% {
    transform: translate3d(2px, 0, 0);
  }
  60%{
    transform: translate3d(-4px, 0, 0);
  }
  80%{
    transform: translate3d(4px, 0, 0);
  }
  100%{
    transform: translate3d(0px, 0, 0);
  }
}
#tack{
  transform: rotateZ(45deg);
  margin-top:2px;
  margin-left:-2px;
  font-size:20px;
  color: rgb(236, 217, 231);
}
#tack.pinned{
   color: rgb(218, 150, 202);
}
#design{
  position: absolute;
  z-index:99999;
bottom:0;
font-size: 15px;
color:#0d0f4280;
right:20px;
bottom:21px;
   font-family:  Compose, Avenir, Helvetica, Arial, sans-serif;
}
.panel{

    cursor: move;
    z-index: 2;
  width:100%;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
 }
*{
   margin: 0;
}
#app {
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #45547c;
  height: 100vh;
}
@font-face {
  font-family: "Compose";
  src: local("Compose"),
  url(./assets/Compose/Compose-Bold.ttf) format("truetype");
}
@font-face {
  font-family: "ComposeRegular";
  src: local("ComposeRegular"),
  url(./assets/Compose/Compose-Regular.ttf) format("truetype");
}

</style>

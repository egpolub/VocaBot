<template>
  <div id="app">
   <div id="logo">VOCA™</div>
      <router-link to="/">
      </router-link>

    <router-view/>
  </div>
</template>

<style>
 * {
       margin: 0;
   }
#app {
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100vh;
}
@font-face {
  font-family: "Compose";
  src: local("Compose"),
   url(./assets/Compose/Compose-Bold.ttf) format("truetype");
}
#logo{
  position:fixed;
  font-size:50px;
  z-index:999;
  margin-left:27px;
   margin-top:15px;
   color: #ecadd2;
}
</style>

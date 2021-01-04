<template>
  <div id="app">

      <router-link :to="{name: 'Login'}">
      </router-link>
    <router-view/>
  </div>
</template>

<style>
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

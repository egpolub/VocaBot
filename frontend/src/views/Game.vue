<template>
    <div id="game">
<div id="block">
<span>Game</span><br>
 </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  #block{
  z-index: 2;
  width:calc(100% - 280px);
  margin-top:60px;
  height: calc(100% - 120px);
  background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.05 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
  float: right;
  margin-right:60px;
 }
 #game{
    font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
    font-size:200px;
    width: 100%;
    height:100%;
    z-index:1;
    position:absolute;
    overflow: hidden;
 }
</style>

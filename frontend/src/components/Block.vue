<template>
    <div id="block" ></div>
</template>

<script>
export default {

}
</script>

<style scoped>
#block{
  margin-top:60px;
  width:390px;
  height:calc(100vh - 700px);
  min-height:100px;
  background: rgba( 255, 255, 255, 0.4 );
  z-index: 2;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
}
</style>

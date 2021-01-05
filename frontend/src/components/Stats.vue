<template>
   <div id="user3" >
   <div class="user panel"><span id="dash">Dashboard <br>
    <span id="description">View your statistics.</span>
    </span><br>
   </div>
 </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#description{
font-size:25px;
font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
}
#dash{
text-align: left;
float:left;
margin-top:21px;
margin-left:30px;
font-size:22px;
font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}
.user{
  height: 95px;
  background: rgba( 255, 255, 255, 0.6 );
 }
 #user3{
  z-index: 2;
  width:calc(100vw - 730px);
  height: calc(100vh - 120px);
  top:0px;
  background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.1 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
  position: relative;
  top:60px;
  margin-left:60px;
    min-height:680px;
 /*border: 1px solid rgba( 255, 255, 255, 0.18 );*/
 }
</style>

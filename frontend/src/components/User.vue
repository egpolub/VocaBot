<template>
    <div class="user2 panel">
      <div id="lft"> <span id="userName"> Hello, {{username}}!</span><br>
      <span id="welcome"> Welcome Back!</span>
      </div>
      <div id="rght"><img id="pfp" :src="photo_url" alt=""></div>
    </div>
</template>

<script>
export default {
  props: ['username', 'photo_url']

}
</script>

<style scoped>
#welcome{
  font-size: 25px;
}

#rght{
  text-align: right;
  position: relative;
  width:0;
  float:right;
  height: 80px;
  top:-60px;
  right:80px
  }

#lft{
  line-height:1.3;
  white-space: nowrap;
  text-align:left;
  position: relative;
  width:0;
  margin-left:32px;
  top:21px;
  height: 80px;
}

#userName{
 font-size: 22px;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}
 #pfp{
   border-radius:50%;
   height:56px;
   position:relative;

  box-shadow: 0 0px 15px 0 rgba(0, 0, 0, 0.1);
 }
  #user3{
  z-index: 2;
  width:calc(100% - 600px);
  height: 100%;
  background: rgba( 255, 255, 255, 0.3 );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.1 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
 /*border: 1px solid rgba( 255, 255, 255, 0.18 );*/
  float: right;
  right:100px;
 }

 .user2{
  margin-top:60px;
  width:390px;
  height: 95px;
  background: rgba( 255, 255, 255, 0.9 );
  overflow: hidden;
 }

</style>

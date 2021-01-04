<template>
    <div id="blocks">
<div id="user2">
<div class="user2 panel" v-if="user!=null">
      <div id="lft"> <span id="userName"> Hello, {{user.username}}!</span><br>
      <span id="welcome"> Welcome Back!</span>
      </div>
      <div id="rght"><img id="pfp" :src="user.photo_url" alt=""></div>
 </div>
 <div class="user2 panel" id="" v-else>
      <div id="lft"> <span id="userName"> Hello, User!</span><br>
      <span id="welcome"> Welcome Back!</span>
      </div>
      <div id="rght"><img id="pfp" src="https://vignette.wikia.nocookie.net/wagnerthewalrus/images/7/79/Square-xxl.png/revision/latest?cb=20200701182833" alt=""></div>
 </div>
 <div id="block" class="panel">
    <div class="user1 panel"> <span id="dash"> Add new words:</span> </div>
   <div id="add-translation">
     <span class="add">Word:</span><br>
     <input class="wordInput" type="text">
     <br><br>
     <span class="add">Translation:</span><br>
     <input class="wordInput" type="text"><br>
     <button id="submit">Submit</button>
   </div>
 </div>
 <div id="block2" class="panel">

 </div>
 </div>
 <div id="user3">
   <div class="user panel"><span id="dash">Dashboard <br>
    <span id="description">View your statistics.</span>
   </span><br>
   </div>
 </div>
 </div>
</template>

<script>
export default {
  computed: {
    user () {
      return this.$store.state.user
    }
  }
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
#candy-1{
  position:absolute;
  top:0px;
  z-index:0;
  height:400px;
}
#blocks{
  width:calc(100% - 100px);
  height: calc(100% - 120px);
  float: right;
  margin-right:50px;
  margin-top:60px;
}

.add{
 font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;

}

#submit{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:75%;
  border-radius: 10px;
  font-size: 23px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:48px;
  box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.07);
  outline:0px;
  margin-left:12%;
   transition: 0.15s;
}
#submit:hover{
  background-color:#f8f8f8;

}
#submit:active{
  background-color:#f1f1f1;
}
.wordInput{
  font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
  border-radius: 10px ;
  border:0px;
  height:35px;
  font-size: 22px;
  outline: 0px;
  width:calc(100% - 14px);
   box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.07);
   padding-left: 10px;
   padding-top:3px;
}
#add-translation{
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  text-align: left;
  font-size: 20px;
  margin-left:32px;
  margin-right:32px;
  top:20px;
  position: relative;

}
#block2{
  margin-top:45px;
  max-width: 500px;
  height: calc(100% - 550px);
  background: rgba( 255, 255, 255, 0.4 );
}
#block{
  margin-top:45px;
  max-width: 500px;
  height: 365px;
  background: rgba( 255, 255, 255, 0.6 );
  text-align: center;
}
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
 #user2{
  z-index: 2;
  width:80%;
  max-width: 390px;
  height: 100%;
 /*border: 1px solid rgba( 255, 255, 255, 0.18 );*/

  position: relative;
  float: left;
  left:160px;
 }
.user1{
  height: 65px;
  background: rgba( 255, 255, 255, 0.6 );
 }
 .user{
  height: 95px;
  background: rgba( 255, 255, 255, 0.6 );
 }
 .user2{
  height: 95px;
  background: rgba( 255, 255, 255, 0.9 );
 }
 .panel{
    z-index: 2;
  width:100%;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
 }
</style>

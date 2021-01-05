<template>
    <div id="block" >
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
</template>

<script>
export default {

}
</script>

<style  scoped>
#dash{
text-align: left;
float:left;
margin-top:21px;
margin-left:30px;
font-size:22px;
font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
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
#block{
  margin-top:60px;
  width:390px;
  height: 365px;
  background: rgba( 255, 255, 255, 0.6 );
    z-index: 2;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
}
.user1{
  height: 65px;
  background: rgba( 255, 255, 255, 0.6 );
 }

</style>

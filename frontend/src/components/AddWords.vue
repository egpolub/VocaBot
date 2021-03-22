<template>
    <div id="block" >
        <div class="user1 panel">
           <span id="dash"> Add new words</span>
           <button v-if="!$isMobile()" id="box" v-on:click="pin"><font-awesome-icon :class={pinned:pinned_class}  id="tack" :icon="['fas', 'thumbtack']"/>
           </button>
           </div>
          <div id="add-translation">
            <div  class="border">
            <span class="add">Word</span><br>
            <input class="wordInput" placeholder="Книга" type="text">
            </div>

            <font-awesome-icon class="arrow" :icon="['fas', 'arrow-down']"/>
            <br>
            <div  class="border">
            <span class="add">Translation</span><br>
            <input class="wordInput" placeholder="Book" type="text">
            </div><br>
            <button id="submit">Submit</button>

          </div>

    </div>
</template>

<script>
export default {
  props: ['pinned_class'],
  data () {
    return {
    }
  },
  methods: {
    pin () {
      this.$emit('pinned')
    }
  }

}
</script>

<style  scoped>
.arrow{
padding-top:12px;
padding-bottom:7px;
font-size: 24px;
margin-left:calc(50% - 12px);
  color: rgb(221, 201, 216);
}
::placeholder {
  color: rgba(126, 126, 126, 0.4);

}
.border{
  border: 1px solid rgba(0,0,0,0.05);
  border-radius: 15px;
  padding-top:8px;
  padding-bottom:2px;
  padding-left:15px;
  background-color: white;
}
#box{
  margin-top:16px;
  height:35px;
width:35px;
background-color:#ffffff00;
float:right;
margin-right:16px;
border-radius:12px;
outline:0;
border:0px ;

}

#dash{
text-align: left;
float:left;
margin-top:19px;
margin-left:30px;
font-size:27px;
font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}
.add{
 font-family: ComposeRegular, Avenir, Helvetica, Arial, sans-serif;
 font-size:22px;
}

#submit{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:80%;
  border-radius: 13px;
  font-size: 25px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:-7px;
  box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.03);
  outline:0px;
  margin-left:9%;
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
  height:30px;
  font-size: 25px;
  outline: 0px;
  width:calc(100% - 14px);
  /* box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.03);*/
   padding-left: 0px;
   padding-top:0px;
   background-color: rgba(0,0,0,0);
}
#add-translation{
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  text-align: left;
  font-size: 20px;
  margin-left:32px;
  margin-right:32px;
  top:27px;
  position: relative;

}
#block{
  margin-bottom:60px;
   width:100%;
  height: 365px;
  background: rgba( 255, 255, 255, 0.55 );
    z-index: 2;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.05 );
}
.user1{
   border-radius: 30px 30px 0 0;
  height: 65px;
  background: rgba( 255, 255, 255, 0.6 );
 }
@media only screen and (max-width:394px){
#box{
  display: none;
}
}
</style>

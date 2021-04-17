<template>
    <div id="block" >
      <div class="panel">
        <button v-if="!$isMobile()" id="box" v-on:click="pin"><font-awesome-icon class="icon" :class={pinned:pinned_class} id="tack" :icon="['fas', 'thumbtack']"/>
        </button>
      </div>
      <button v-on:click="send">Send http:// request</button>
      {{response}}
    </div>
</template>

<script>
export default {
  props: ['pinned_class'],
  data () {
    return {
      response: ''
    }
  },
  methods: {
    pin () {
      this.$emit('pinned')
    },
    send () {
      this.axios.get('/dashboard/user').then((response) => {
        this.response = response
        console.log(response)
      })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
#box{
  margin-top:16px;
  height:35px;
  width:35px;
  background-color:#ffffff00;
  float:right;
  margin-right:16px;
  border-radius:12px;
  outline:0;
  border:0px;
}

.panel{
  border-radius: 30px 30px 0 0;
  height: 65px;
  background: rgba( 255, 255, 255, 0.6 );
}
button{
  background-color: white;
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  height:45px;
  width:75%;
  border-radius: 10px;
  font-size: 23px;
  color:#393c65;
  font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
  margin-top:25px;
  outline:none;
   transition: 0.2s;
}
button:hover{
  background-color:#f6f6f6;

}
button:active{
  background-color:#ececec;
}
@media only screen and (max-width:394px){
#box{
  display: none;
}
}
#block{
  margin-bottom:60px;
  width:100%;
  height:calc(100vh - 700px);
  min-height:100px;
  background: rgba( 255, 255, 255, 0.55 );
  z-index: 2;
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.05 );
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px ;
  position: relative;
}
</style>

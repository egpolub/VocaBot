<template>
    <div id="block" >
        <div class="addWordMenu" v-if="currentState==='add'">
              <div id="header">
                <div id="triangle">
                </div>
               <span id="addText">Add new word</span>
              </div>
              <form @submit.prevent="addWord()">
                <input class="wordInput" type="text" placeholder="Word" v-model="word" required="true">
                <input class="wordInput" type="text" placeholder="Translation" v-model="translation" required="true">
                <button class="submitButton" type="submit">Submit</button>
              </form>
         </div>
        <div class="user1 panel">
           <span id="dash">Your dictionary</span>
           <button class="add" v-on:click="toggleState('add')">+</button>
           <button class="trashcan" v-on:click="toggleState('delete')"><font-awesome-icon :icon="['fas', 'trash-alt']"/></button>
           <button v-if="!$isMobile()" id="box" v-on:click="pin"><font-awesome-icon :class={pinned:pinned_class}  id="tack" :icon="['fas', 'thumbtack']"/>
           </button>
        </div>
        <div class="wordsContainer">
          <div v-if="wordsList.length===0">
           <span id="noWords">
           You don't have any words<br>
           yet... Add new ones using<br>
           the plus button above.
           </span>
          </div>
        <div v-for="word in wordsList" :key="word" :class="[word.id===editWordId ? 'wordsEdit' : 'wordsNormal','words']">
          <div v-if="word.id!==editWordId">
            <button class="pencil" v-if="currentState!=='delete'" v-on:click="editWord(word.id,word.word,word.translation)" :disabled="currentState!==''"><font-awesome-icon :icon="['fas', 'pencil-alt']"/></button>
            <button class="close shake" v-if="currentState==='delete'" v-on:click="deleteWord(word.id)"><font-awesome-icon :icon="['fas', 'times']"/></button>
            <span class="wordSpan">
            {{word.word}} - {{word.translation}}
            </span>
           </div>
           <div v-else>
              <form @submit.prevent="confirmEdit(word.id, word.word, word.translation)">
                <span class="wordSpan">
                  <span v-if="editedWord===''">{{word.word}}</span><span v-else>{{editedWord}}</span> - <span v-if="editedTranslation===''">{{word.translation}}</span><span v-else>{{editedTranslation}}</span>
                </span>
                 <button class="close closeEdit" v-on:click="editWord('','','')"><font-awesome-icon :icon="['fas', 'times']"/></button>
                <input class="wordInput" type="text" placeholder="Word" v-model="editedWord">
                <input class="wordInput" type="text" placeholder="Translation" v-model="editedTranslation">
                <button class="submitButton editButton" type="submitedit">Edit translation</button>
              </form>
           </div>
        </div>
        </div>
    </div>
</template>

<script>
export default {
  props: ['pinned_class', 'id'],
  data () {
    return {
      wordsList: [],
      currentState: '',
      word: '',
      translation: '',
      editWordId: '',
      editedWord: '',
      editedTranslation: ''
    }
  },
  created () {
    this.axios.get(`/word/${this.id}`).then((res) => {
      if (res.data !== '') {
        this.wordsList = res.data
      }
    }).catch((err) => { console.log(err) })
  },
  methods: {
    editWord (id, word, translation) {
      this.editWordId = id
      this.currentState = ''
      this.editedWord = word
      this.editedTranslation = translation
    },
    toggleState (state) {
      this.editWordId = ''
      if (this.currentState === state) {
        this.currentState = ''
        return
      }
      this.currentState = state
    },
    confirmEdit (id, word, translation) {
      if (this.editedWord === '') {
        this.editedWord = word
      }
      if (this.editedTranslation === '') {
        this.editedTranslation = translation
      }
      const data = {
        id: id,
        chatId: this.id,
        word: this.editedWord,
        translation: this.editedTranslation
      }
      this.axios({
        method: 'PATCH',
        url: `/word/${id}`,
        data: data
      }).then(() => {
        const index = this.wordsList.findIndex((word) => word.id === id)
        this.wordsList.splice(index, index + 1, data)
        this.currentState = ''
        this.editWordId = ''
      }).catch((err) => {
        console.log(err)
      })
    },
    addWord () {
      const data = {
        chatId: this.id,
        word: this.word,
        translation: this.translation
      }
      this.axios({
        method: 'POST',
        url: '/word',
        data: data
      }).then((res) => {
        data.id = res.data.id
        this.wordsList.push(data)
        this.currentState = ''
        this.word = ''
        this.translation = ''
      }).catch((err) => {
        console.log(err)
      })
    },
    deleteWord (id) {
      this.axios({
        method: 'DELETE',
        url: `/word/${id}`
      }).then(() => {
        this.wordsList = this.wordsList.filter((x) => x.id !== id)
      }).catch((err) => {
        console.log(err)
      })
    },
    pin () {
      this.$emit('pinned')
    }
  }

}
</script>

<style  scoped>
#noWords{
      color: #7d7f9c;
    font-size: 25px;
    font-family: ComposeRegular;
    position: absolute;
    margin-top: -55px;
    display: flex;
    justify-content: center;
    flex-direction: column;
    height: 100%;
    margin-left: calc(50% - 150px);
}
.closeEdit{
   color: #ffd089 !important;
}
.editButton{
  background-color: #ffa631!important;
    position: relative!important;
    top: 145px;
    margin-left: -107%;
}
.submitButton{
    bottom: 18px;
    position: absolute;
    left: 7%;
    width: 86%;
    height: 40px;
    border-radius: 9px;
    border: 0;
    font-family: 'COMPOSE';
    font-size: 20px;
    color: white;
    background-color: #319bff;
}
.wordInput{
    font-family: ComposeRegular;
    font-size: 20px;
    margin-left: 7%;
    float: left;
    width: calc(86% - 10px);
    background-color: #ffffff;
    border: 1px solid #e2e2e2;
    margin-top: 20px;
    height: 37px;
    padding-left: 10px;
    border-radius: 9px;
}
#addText{
  font-family: Compose;
  font-size:25px;
  float:left;
  margin-top: 10px;
  margin-left: 20px;
  color:#45547c;
}
#header{
  position:realtive;
  left:0px;
  height:50px;
  background: rgb( 255, 255, 255);
  width:100%;
  border-radius:20px 20px 0 0;
  overflow: hidden;
}
#triangle{
  width:50px;
  height:50px;
  transform: rotate(45deg);
  position:absolute;
  background: rgb( 255, 255, 255);
      top: -10px;
    z-index: -1;
    left: 125px;

}
.addWordMenu{
  border: 1px solid rgba( 255, 255, 255, 0.18 );
  background: rgba( 255, 255, 255, 0.65 );
  backdrop-filter: blur( 5px );
  -webkit-backdrop-filter: blur( 5px );
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.12 );

  width:300px;
  height:250px;
  z-index:9999;
  position:absolute;
      margin-left: 105px;
    margin-top: 65px;
  border-radius:20px;
}
.shake {
  animation: shake-animation 1.5s ease infinite;
  transform-origin: 50% 50%;
}
@keyframes shake-animation {
   0% { transform:rotate(0deg) }
  5.78571% { transform:rotate(5deg) }
  15.57143% { transform:rotate(-5deg) }
  25.35714% { transform:rotate(3deg) }
  35.14286% { transform:rotate(-3deg) }
  40.92857% { transform:rotate(2deg) }
  50.92857% { transform:rotate(0deg) }
  100% { transform:rotate(0deg) }
}
.wordSpan{
    left: 20px;
    float: left;
    position: relative;
}
.add{
 position: absolute;
    right: 85px;
    border: 0px;
    background: transparent;
    font-size: 38px;
    margin-top: 3px;
    top: 7px;
    color: rgb(54, 162, 235)
}
.trashcan{
    position: absolute;
    right: 55px;
    border: 0px;
    background: transparent;
    font-size: 17px;
    margin-top: 3px;
    top: 20px;
    color: rgb(255,99,32)
}
.close{
    position: relative;
    float:right;
    right: 15px;
    border: 0px;
    background: transparent;
    font-size: 17px;
    margin-top: 3px;
    color: rgb(255,99,32)
}
.pencil{
    position: relative;
    float:right;
    right: 15px;
    border: 0px;
    background: transparent;
    font-size: 17px;
    margin-top: 3px;
    color: #ffd089;
}

.wordsContainer{
  padding-bottom: 25px;
    padding-top: 10px;
    overflow-y: scroll;
    height: calc(100% - 100px);
}
.wordsContainer::-webkit-scrollbar {
  width: 4px;
  height: 4px;
}

.wordsContainer::-webkit-scrollbar-thumb {
  background: #ffd7ec;
  border: 0px;
  border-radius: 20px;
}

.wordsContainer::-webkit-scrollbar-track {
  background: #fff4fc;
  border: 0px ;
  border-radius: 100px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.wordsContainer::-webkit-scrollbar-corner {
  background: transparent;
}
.wordsEdit{
    height: 225px;
}
.wordsNormal{
    height: 30px;
}
.words{
      padding-top: 14px;
    background-color: #fff;
    border-radius: 10px;
    margin-top: 15px;
    font-size: 20px;
    font-family: ComposeRegular;
    padding-bottom: 7px;
    width: 80%;
    margin-left: calc(10% + 2px);
}
.arrow{
padding-top:12px;
padding-bottom:7px;
font-size: 24px;
margin-left:calc(50% - 12px);
  color: rgb(221, 201, 216);
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
  border:0px;

}

#dash{
text-align: left;
float:left;
margin-top:19px;
margin-left:30px;
font-size:27px;
font-family: Compose, Avenir, Helvetica, Arial, sans-serif;
}

#block{
  margin-bottom:60px;
   width:100%;
   height: calc(100vh - 275px);
    min-height: 525px;
  background: rgba( 255, 255, 255, 0.55 );
    z-index: 2;
  backdrop-filter: blur( 15px );
  -webkit-backdrop-filter: blur( 15px );
  border-radius: 30px;
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

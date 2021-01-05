<template>
    <div id="blocks">
      <div id="menu" class="flex">

      </div>
  <draggable
        v-model="list"
        @start="drag = true"
        @end="saveLayout"
        v-bind="dragOptions"
      >
       <transition-group type="transition" :name="!drag ? 'flip-list' : null">
       <div v-for="element in list"
          :key="element" class="flex">
          <Draggables v-bind:username="user.username" v-bind:photo_url="user.photo_url" v-if="element=='Draggables'" @click="element.fixed = !element.fixed"/>
          <Stats v-else-if="element=='Stats'" @click="element.fixed = !element.fixed"/>
        </div>
        </transition-group>
    </draggable>
 </div>
</template>

<script>
import draggable from 'vuedraggable'
import Draggables from '@/components/Draggables.vue'
import Stats from '@/components/Stats.vue'
export default {
  components: {
    Stats,
    Draggables,
    draggable
  },
  data () {
    return {
      list: ['Draggables', 'Stats'],
      drag: false,
      user: {
        username: 'User',
        photo_url: 'https://vignette.wikia.nocookie.net/wagnerthewalrus/images/7/79/Square-xxl.png/revision/latest?cb=20200701182833'
      }
    }
  },
  created () {
    var user = this.$store.state.user
    if (user) {
      this.user = this.$store.state.user
    }
    if (localStorage.getItem('horList')) {
      this.list = JSON.parse(localStorage.getItem('horList'))
    }
  },

  computed: {
    dragOptions () {
      return {
        animation: 200,
        group: 'blocks2',
        disabled: false
      }
    }
  },
  methods: {
    saveLayout () {
      this.drag = false
      localStorage.setItem('horList', JSON.stringify(this.list))
    }
  }
}
</script>

<style scoped>
#menu{
  width:160px;
  height:100%;
  position: relative;
  z-index: -30;
}
.flip-list-move {
  transition: transform 0.5s;
}
#blocks{
  width:100%;
  height: calc(100% - 120px);
  }

.flex{
  white-space: nowrap;
    /*display: flex;
     flex-direction: row;
      flex-wrap: nowrap;
      position: relative;*/
      float:left;
}

</style>

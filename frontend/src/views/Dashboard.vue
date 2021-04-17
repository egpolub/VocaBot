<template>
  <div id="blocks">
    <draggable
      v-model="list"
      @start="drag = true"
      @end="saveLayout"
      v-bind="dragOptions"
      :move="checkMove"
      :disabled="$isMobile()"
    >
      <transition-group tag ="div" type="transition" :name="!drag ? 'flip-list' : null">
        <div v-for="element in list" :key="element.item" class="flex">
          <Draggables
            @pinned="pinItem(element.item, $event)"
            v-bind:dragAnim="dragAnim"
            v-bind:username="user.username"
            v-bind:id="user.id"
            v-bind:photo_url="user.photo_url"
            v-if="element.item == 'Draggables'"
          />
          <Stats
            @pinned="pinItem(element.item, !element.fixed)"
            :class="{ shake: animated }"
            v-bind:pinned_class="element.fixed"
            v-else-if="element.item == 'Stats'"
          />
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
      list: [
        { item: 'Draggables', fixed: false },
        { item: 'Stats', fixed: false }
      ],
      animated: false,
      drag: false,
      stopAnim: false,
      dragAnim: false,
      user: {
        username: 'User',
        photo_url:
          'https://api-private.atlassian.com/users/756e85b64322972fbe6f40fa056ae878/avatar',
        id: 337616608
      }
    }
  },
  created () {
    var user = this.$store.state.user
    if (user) {
      this.user = user
    }

    if (localStorage.getItem('horList') && !this.$isMobile()) {
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
    checkMove (e) {
      if (!this.stopAnim) {
        if (this.list.find((item) => item.item === 'Stats').fixed) {
          this.animated = true
          setTimeout(() => {
            this.animated = false
          }, 250)
          this.stopAnim = true
        } else if (this.list.find((item) => item.item === 'Draggables').fixed) {
          this.dragAnim = true
          setTimeout(() => {
            this.dragAnim = false
          }, 250)
          this.stopAnim = true
        }
      }

      return this.isDraggable(e.draggedContext)
    },
    isDraggable (context) {
      const { index, futureIndex } = context
      return !(this.list[index].fixed || this.list[futureIndex].fixed)
    },
    pinItem (name, boolean) {
      this.list.find((item) => item.item === name).fixed = boolean
      localStorage.setItem('horList', JSON.stringify(this.list))
    },
    saveLayout () {
      this.stopAnim = false
      this.drag = false
      localStorage.setItem('horList', JSON.stringify(this.list))
    }
  }
}
</script>

<style scoped>

.flip-list-move {
  transition: transform 0.3s;
}
#blocks {
  width: calc(100% - 160px);
  margin-left:160px;
  padding-top:60px;
  height: calc(100% - 120px);
  z-index:1;
  transition:0.2s
}

.flex {
  white-space: nowrap;
  float: left;

}

@media only screen and (max-width:638px), (max-height:700px) {
  #blocks{
    padding-top:200px;
    margin-left:0px;
 }
}
@media only screen and (max-width:450px), (max-height:700px) {
  #blocks{
    padding-top:150px;
 }
}
@media only screen and (max-width:437px), (max-height:700px) and (max-width:437px){
  #blocks{
    margin-left:-30px;
 }
}
@media only screen and (max-width:359px), (max-height:700px) and (max-width:359px){
  #blocks{
    margin-left:-45px;
 }
}
</style>

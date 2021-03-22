<template>
  <draggable
    v-model="list"
    @start="drag = true"
    @end="saveLayout"
    v-bind="dragOptions"
    id="drag"
    :move="checkMove"
    :disabled="$isMobile()"
  >
    <transition-group type="transition" :name="!drag ? 'flip-list' : null">
      <div v-for="(element, index) in list" :key="element.item">
        <HelloUser
          @pinned="pinItem(element.item)"
          :class="{
            shake:
              (dragAnim && element.fixed) ||
              (dragAnim2[index] && element.fixed),
          }"
          v-bind:username="username"
          v-bind:photo_url="photo_url"
          v-bind:pinned_class="element.fixed"
          v-if="element.item == 'HelloUser'"
        />
        <AddWords
          @pinned="pinItem(element.item)"
          :class="{
            shake:
              (dragAnim && element.fixed) ||
              (dragAnim2[index] && element.fixed),
          }"
          v-bind:pinned_class="element.fixed"
          v-else-if="element.item == 'AddWords'"
        />
        <Block
          @pinned="pinItem(element.item)"
          :class="{
            shake:
              (dragAnim && element.fixed) ||
              (dragAnim2[index] && element.fixed),
          }"
          v-bind:pinned_class="element.fixed"
          v-else-if="element.item == 'Block'"
        />
      </div>
    </transition-group>
  </draggable>
</template>

<script>
import draggable from 'vuedraggable'
import HelloUser from '@/components/User.vue'
import AddWords from '@/components/AddWords.vue'
import Block from '@/components/Block.vue'
export default {
  props: ['username', 'photo_url', 'dragAnim'],
  components: {
    draggable,
    HelloUser,
    AddWords,
    Block
  },
  data () {
    return {
      list: [
        { item: 'HelloUser', fixed: false },
        { item: 'AddWords', fixed: false },
        { item: 'Block', fixed: false }
      ],
      drag: false,
      stopAnim: false,
      dragAnim1: false,
      dragAnim2: []
    }
  },
  computed: {
    dragOptions () {
      return {
        animation: 200,
        group: 'blocks',
        disabled: false
      }
    }
  },
  created () {
    if (localStorage.getItem('verList') && !this.$isMobile()) {
      this.list = JSON.parse(localStorage.getItem('verList'))
      this.list.forEach((element) => {
        this.dragAnim2.push(false)
      })
    }
  },
  methods: {
    checkMove (e) {
      return this.isDraggable(e.draggedContext)
    },
    isDraggable (context) {
      const { index, futureIndex } = context
      if (!this.stopAnim) {
        if (this.list[futureIndex].fixed || this.list[index].fixed) {
          this.dragAnim2.splice(futureIndex, 1, true)
          this.dragAnim2.splice(index, 1, true)
          setTimeout(() => {
            this.dragAnim2.splice(futureIndex, 1, false)
            this.dragAnim2.splice(index, 1, false)
          }, 250)
          this.stopAnim = true
        } else if (this.list[1].fixed) {
          this.dragAnim2.splice(1, 1, true)
          setTimeout(() => {
            this.dragAnim2.splice(1, 1, false)
          }, 250)
          this.stopAnim = true
        }
      }
      return !(
        this.list[index].fixed ||
        this.list[futureIndex].fixed ||
        this.list[1].fixed
      )
    },
    pinItem (name) {
      this.list.find((item) => item.item === name).fixed = !this.list.find(
        (item) => item.item === name
      ).fixed
      if (
        this.list[0].fixed === true ||
        this.list[1].fixed === true ||
        this.list[2].fixed === true
      ) {
        this.$emit('pinned', true)
      } else {
        this.$emit('pinned', false)
      }
      localStorage.setItem('verList', JSON.stringify(this.list))
    },
    saveLayout () {
      this.drag = false
      this.stopAnim = false
      localStorage.setItem('verList', JSON.stringify(this.list))
    }
  }
}
</script>

<style scoped>
.flip-list-move {
  transition: transform 0.3s;
}
#drag {
  position: relative;
  margin-left: 60px;
  width:390px;
}
@media only screen and (max-width:1120px){
  #drag {
  width: calc(100vw - 280px);
  }
}
@media only screen and (max-width:638px), (max-height:700px) {
  #drag {
  width: calc(100vw - 120px);
  }
}
@media only screen and (max-width:437px),(max-height:700px) and (max-width:437px) {
   #drag {
  width: calc(100vw - 60px);
  }
}
@media only screen and (max-width:359px),(max-height:700px) and (max-width:359px){
   #drag {
  width: calc(100vw - 30px);
  }
}
</style>

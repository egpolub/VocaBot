<template>
 <draggable
        v-model="list"
        @start="drag = true"
        @end="saveLayout"
        v-bind="dragOptions"
        id="drag"
        :move="checkMove"
      >
       <transition-group type="transition" :name="!drag ? 'flip-list' : null">
          <div v-for="element in list"
              :key="element.item">
              <HelloUser @pinned='pinItem(element.item)' :class="{'shake' : dragAnim&&element.fixed || dragAnim2 && element.fixed}" v-bind:username="username" v-bind:photo_url="photo_url" v-bind:pinned_class="element.fixed" v-if="element.item=='HelloUser'"/>
              <AddWords @pinned='pinItem(element.item)'  :class="{'shake' : dragAnim&&element.fixed || dragAnim2 && element.fixed}" v-bind:pinned_class="element.fixed" v-else-if="element.item=='AddWords'"/>
              <Block @pinned='pinItem(element.item)'     :class="{'shake' : dragAnim&&element.fixed || dragAnim2 && element.fixed}" v-bind:pinned_class="element.fixed" v-else-if="element.item=='Block'" />
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
      dragAnim2: false

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
    if (localStorage.getItem('verList')) {
      this.list = JSON.parse(localStorage.getItem('verList'))
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
          this.dragAnim2 = true
          setTimeout(() => {
            this.dragAnim2 = false
          }, 250)
          this.stopAnim = true
        }
      }
      return !(this.list[index].fixed || this.list[futureIndex].fixed || this.list[1].fixed)
    },
    pinItem (name) {
      // this.pinnedItems.push({ name: name, id: this.list.indexOf(this.list.find(item => item.item === name)) })
      this.list.find(item => item.item === name).fixed = !this.list.find(item => item.item === name).fixed
      if (this.list[0].fixed === true || this.list[1].fixed === true || this.list[2].fixed === true) {
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
#drag{
    position: relative;
    margin-left:60px;
    margin-bottom:60px;
}
</style>

<template>
 <draggable
        v-model="list"
        @start="drag = true"
        @end="drag = false"
        v-bind="dragOptions"
        id="drag"
      >
       <transition-group type="transition" :name="!drag ? 'flip-list' : null">
       <div v-for="element in list"
          :key="element.order">
          <HelloUser v-bind:username="username" v-bind:photo_url="photo_url" v-if="element.name=='HelloUser'" @click="element.fixed = !element.fixed"/>
          <AddWords v-else-if="element.name=='AddWords'" @click="element.fixed = !element.fixed"/>
          <Block v-else-if="element.name=='Block'" @click="element.fixed = !element.fixed"/>
        </div>
        </transition-group>
    </draggable>
</template>

<script>
import draggable from 'vuedraggable'
import HelloUser from '@/components/User.vue'
import AddWords from '@/components/AddWords.vue'
import Block from '@/components/Block.vue'
const message = ['HelloUser', 'AddWords', 'Block']
export default {
  props: ['username', 'photo_url'],
  components: {
    draggable,
    HelloUser,
    AddWords,
    Block
  },
  data () {
    return {
      list: message.map((name, index) => {
        return { name, order: index + 1 }
      }),
      drag: false
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
  }

}
</script>

<style scoped>
.flip-list-move {
  transition: transform 0.5s;
}
#drag{
    position: relative;
    margin-left:60px;
    margin-bottom:60px;
}
</style>

<template>
  <div id="blocks">
    <div id="menu" class="flex"></div>
    <draggable
      v-model="list"
      @start="drag = true"
      @end="saveLayout"
      v-bind="dragOptions"
      :move="checkMove"
    >
      <transition-group type="transition" :name="!drag ? 'flip-list' : null">
        <div v-for="element in list" :key="element.item" class="flex">
          <Draggables
            @pinned="pinItem(element.item, $event)"
            v-bind:dragAnim="dragAnim"
            v-bind:username="user.username"
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
          'https://api-private.atlassian.com/users/756e85b64322972fbe6f40fa056ae878/avatar'
      }
    }
  },
  created () {
    var user = this.$store.state.user
    console.log(JSON.stringify(user))
    if (user) {
      this.user = user
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
#menu {
  width: 160px;
  height: 100%;
  position: relative;
  z-index: -30;
}
.flip-list-move {
  transition: transform 0.3s;
}
#blocks {
  width: 100%;
  height: calc(100% - 120px);
}

.flex {
  white-space: nowrap;
  float: left;
}
</style>

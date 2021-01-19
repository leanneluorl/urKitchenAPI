<template>
  <div>
    <label for="number"></label>
    <input
      type="text"
      name="number"
      placeholder="Enter a number"
      v-model="number"
    />
    <button @click="addNumber">Add number</button>
  </div>
</template>

<script>
import {store} from '../store.js'
export default {
  data() {
    return {
      number: 0
    }
  },
  methods: {
    addNumber() {
      console.log(this.number)
      store.addNumber(this.number)
    }
  }
}
</script>
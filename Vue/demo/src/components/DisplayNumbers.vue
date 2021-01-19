<template>
  <div>
    <h2>{{ storeState }}</h2>
  </div>
</template>

<script>
import {store} from '../store.js'

export default {
    data() {
        return {
            storeState: store.state.numbers
            }
    },
    methods: {
       
    },
    created(){ 
        console.log(store)
    }
}
</script>
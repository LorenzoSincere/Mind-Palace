<template>
  <a-layout-footer style="text-align: center">
    Mind Palace<span v-show="user.id">，欢迎：{{user.name}}</span>
  </a-layout-footer>
</template>

<script lang="ts">
  import { defineComponent, computed, onMounted } from 'vue';
  import store from "@/store";
  import { notification } from 'ant-design-vue';

  export default defineComponent({
    name: 'the-footer',

  });
</script>

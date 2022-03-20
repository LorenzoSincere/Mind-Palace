<template>
  <a-layout-footer style="text-align: center">
    <span v-show="user.id">欢迎{{user.name}}创作者，开始分享你的专业知识吧！</span>
    Mind Palace ©2022 Created by Lorenzo
  </a-layout-footer>
</template>

<script lang="ts">
import store from '@/store';
import { Tool } from '@/util/tool';
import { notification } from 'ant-design-vue';
import {computed, defineComponent, onMounted} from 'vue';

  export default defineComponent({
    name: 'Footer',
    setup() {
      const user = computed(() => store.state.user);

      return {
      user
      }
    }
  });
</script>

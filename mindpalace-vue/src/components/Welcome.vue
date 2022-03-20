<template>
  <div>
    <div class="tip">
      <div>1. 统计数据是真实的，一分钟左右延时，<b>用到了定时器、复杂SQL统计、echarts</b></div>
      <div>2. 有文档被别人点赞，你也会收到实时通知哦！<b>用到了websocket、异步化、RocketMQ、防重设计</b></div>
      <div>3. 文档树可无限级扩展，支持文字、图片、视频。<b>用到了无限级树设计知识</b></div>
      <div>4. 登录后可看到更多菜单。<b>用到了单点登录、前后端登录拦截、安全性设计</b></div>
    </div>

    <br>
    <br>
  </div>
</template>

<script lang="ts">
  import { defineComponent, ref, onMounted } from 'vue'

  export default defineComponent({
    name: 'Welcome',
  });
</script>
<style scoped>

</style>

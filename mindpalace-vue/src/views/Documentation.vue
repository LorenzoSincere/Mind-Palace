<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <a-row>
        <a-col :span="6">
          <a-tree
              v-if="level1.length > 0"
              :tree-data="level1"
              @select=""
              :replaceFields="{title: 'name', key: 'id', value: 'id'}"
              :defaultExpandAll="true"
              :defaultSelectedKeys=""
          >
          </a-tree>
        </a-col>
        <a-col :span="18">
        </a-col>
      </a-row>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref, createVNode } from 'vue';
  import axios from 'axios';
  import {message} from 'ant-design-vue';
  import {Tool} from "@/util/tool";
  export default defineComponent({
    name: 'Documentation'
  })
</script>

<style>

</style>

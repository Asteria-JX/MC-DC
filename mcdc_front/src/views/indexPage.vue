<template>
  <div class="layout-demo">
    <a-layout>
      <a-layout-header>
        <a-space style="margin:1.5vh 0 0 2vw">
          <a-button type="primary" style="margin-right: 10px">
            <template #icon>
              <icon-plus />
            </template>
          </a-button>

          <a-button type="primary" status="success">运行</a-button>
        </a-space>
      </a-layout-header>

      <a-layout>
<!--        坐滑块-->
        <a-layout-sider :resize-directions="['right']">
          <a-tree
              :default-selected-keys="['0-0-1']"
              :data="treeData"
              :show-line="showLine"
          />
        </a-layout-sider>



        <a-layout-content>Content</a-layout-content>


<!--        右滑块-->
        <a-layout-sider :resize-directions="['left']">
          Sider
        </a-layout-sider>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
import {IconPlus} from '@arco-design/web-vue/es/icon';
import { ref } from 'vue';
export default ({
  components: {
    IconPlus,
  },
  methods: {

  },
  setup(){
    const showLine = ref(true);
    return {
      showLine,
      treeData,
    };
  }
});

const treeData = [
  {
    title: 'Trunk 1',
    key: '0-0',
    children: [
      {
        title: 'Trunk 1-000000000',
        key: '0-0-0',
        children: [
          { title: 'leaf000000', key: '0-0-0-0' },
          {
            title: 'leaf',
            key: '0-0-0-1',
            children: [{ title: 'leaf', key: '0-0-0-1-0' }],
          },
          { title: 'leaf', key: '0-0-0-2' },
        ],
      },
      {
        title: 'Trunk 1-1',
        key: '0-0-1',
      },
      {
        title: 'Trunk 1-2',
        key: '0-0-2',
        children: [
          { title: 'leaf', key: '0-0-2-0' },
          {
            title: 'leaf',
            key: '0-0-2-1',
          },
        ],
      },
    ],
  },
  {
    title: 'Trunk 2',
    key: '0-1',
  },
  {
    title: 'Trunk 3',
    key: '0-2',
    children: [
      {
        title: 'Trunk 3-0',
        key: '0-2-0',
        children: [
          { title: 'leaf', key: '0-2-0-0' },
          { title: 'leaf', key: '0-2-0-1' },
        ],
      },
    ],
  },
];
</script>

<style scoped>
.layout-demo :deep(.arco-layout-header),
.layout-demo :deep(.arco-layout-sider-children),
.layout-demo :deep(.arco-layout-content) {
  display: flex;
  flex-direction: column;
  color: var(--color-white);
  font-size: 20px;
  font-stretch: condensed;
  text-align: center;
}

.layout-demo :deep(.arco-layout-sider-children) {
  justify-content: flex-start; /* 改为顶部对齐 */
  margin: 10px;
}

.layout-demo :deep(.arco-tree) {
  font-family: 'Microsoft YaHei', sans-serif; /* 设置 a-tree 字体为微软雅黑 */
}

.layout-demo :deep(.arco-layout-header){
  height: 7vh;
  background-color: rgba(1, 25, 85, 0.8);
}

.layout-demo :deep(.arco-layout-sider) {
  width: 206px;
  min-width: 150px;
  max-width: 30vw;
  height: 93vh;
  background-color: rgba(246, 246, 246, 0.8);
}

.layout-demo :deep(.arco-layout-content) {
  background-color: rgb(var(--arcoblue-6));
}
</style>
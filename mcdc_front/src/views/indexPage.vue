<template>
  <div class="layout-demo">
    <a-layout>
      <a-layout-header>
        <a-space style="margin:1.5vh 0 0 1vw">
<!--          <a-button @click="triggerUpload" style="margin-right: 10px">-->
<!--            <a-icon type="upload" />上传</a-button>-->
            <a-button  @click="triggerUpload" style="margin-right: 10px">
              <a-icon type="upload"/>上传项目</a-button>
            <input
                type="file"
                ref="fileInput"
                style="display: none"
                webkitdirectory
                multiple
                @change="handleFileUpload"
            />
<!--          <a-button type="primary" status="success">运行</a-button>-->
        </a-space>
      </a-layout-header>

      <a-layout>
<!--        左滑块-->
        <a-layout-sider :resize-directions="['right']">
          Sider
        </a-layout-sider>
        <!--        右滑块-->
        <a-layout-sider :resize-directions="['right']">
          <a-tree
              :default-selected-keys="['0-0-1']"
              :data="treeData"
              :show-line="showLine"
          />
        </a-layout-sider>


        <a-layout-content>Content</a-layout-content>

      </a-layout>
    </a-layout>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from "axios";
import { Message} from '@arco-design/web-vue';
export default ({
  components: {

  },
  data() {
    return {
      treeData : [
        {
          title: 'Trunk 1',
          key: '0-0',
          children: [
            {
              title: 'Trunk 1',
              key: '0-0-0',
              children: [
                { title: 'leaf', key: '0-0-0-0' },
                {
                  title: 'leaf',
                  key: '0-0-0-1',
                  children: [{ title: 'leaf', key: '0-0-0-1-0' }],
                },
                { title: 'leaf', key: '0-0-0-2' },
              ],
            },
          ],
        },
      ],
    };
  },
  methods: {
  },

  setup(){
    const showLine = ref(true);
    return {
      showLine,
      //treeData,
      triggerUpload,
      handleFileUpload,
      loadPrograms,
      programList,

    };
  }
});

let programList = []

const loadPrograms=async ()=> {
  const res = await axios.get('/all');
  programList = res.data;
}

const triggerUpload = () => {
  document.querySelector('input[type="file"]').click();
}

const handleFileUpload = async(event)=> {
  const files = event.target.files;
  if (!files.length) return;

  const formData = new FormData();
  const programName = prompt("请输入项目名称");
  if (!programName) return;

  formData.append("programName", programName);
  for (let file of files) {
    formData.append("files", file, file.webkitRelativePath);
  }

  try {
    await axios.post('/uploadProject', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
    Message.success('上传成功！');
    location.reload(); // 或调用 vm.$refs.tree.loadFileTree()
  } catch (e) {
    console.log(e)
    Message.error('上传失败');
  }
}


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
  max-width: 20vw;
  height: 93vh;
  background-color: rgba(246, 246, 246, 0.8);
}

.layout-demo :deep(.arco-layout-content) {
  background-color: rgb(var(--arcoblue-6));
}
</style>
<template>
  <div class="layout-demo">
    <a-layout>
      <a-layout-header>
        <a-space style="margin:1.5vh 0 0 1vw">
            <a-button type="primary" @click="triggerUpload" style="margin-right: 10px">
              <a-icon type="upload"/>上传项目</a-button>
            <input
                type="file"
                ref="projectInput"
                style="display: none"
                webkitdirectory
                multiple
                @change="handleFileUpload"
            />

          <a-button type="primary" status="success" @click="triggerTestUpload" style="margin-right: 10px">
            <a-icon type="upload"/>上传测试用例</a-button>
          <input
              type="file"
              ref="testInput"
              style="display: none"
              webkitdirectory
              multiple
              @change="handleTestFileUpload"
          />

<!--          <a-button type="primary" status="success">运行</a-button>-->
        </a-space>
      </a-layout-header>

      <a-layout>
        <!-- 左侧Sider：项目列表,测试用例 -->
        <a-layout-sider :resize-directions="['right']">
          <a-split direction="vertical" :default-size="300" style="height: 100%">
            <template #first>
              <a-menu
                  :selected-keys="[selectedProjectId]"
                  @menu-item-click="handleProjectClick"
              >
                <a-menu-item
                    v-for="item in projectList"
                    :key="item.programId"
                >
                  {{ item.programName }}
                </a-menu-item>
              </a-menu>
            </template>

            <template #second>
              <div style="padding: 10px; background: #fafafa">
                <a-menu
                    :selected-keys="[selectedTestProjectId]"
                    @menu-item-click="handleTestProjectClick"
                >
                  <a-menu-item
                      v-for="item in projectTestList"
                      :key="item.id"
                  >
                    {{ item.name }}
                  </a-menu-item>
                </a-menu>
              </div>
            </template>
          </a-split>

        </a-layout-sider>

        <!-- 右侧Sider：文件结构 -->
        <a-layout-sider :resize-directions="['right']">
          <a-tree
              :data="fileTree"
              :default-expand-all="true"
              :field-names="{ title: 'title', key: 'key', children: 'children' }"
              :show-line="showLine"
          />
        </a-layout-sider>

        <!-- 主内容区域 -->
        <a-layout-content>Content</a-layout-content>

      </a-layout>
    </a-layout>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from "axios";
import { Message} from '@arco-design/web-vue';

//响应式变量
const projectList = ref([]);
const projectTestList=ref([]);

const selectedProjectId = ref(null);
const selectedTestProjectId = ref(null);

const fileTree = ref([]);
const showLine = ref(true);

const projectInput = ref(null);
const testInput = ref(null);

//加载项目
const fetchProjects = async () => {
  try {
    const res = await axios.get('/all',{
      params: {
        userId: 2 // 默认用户ID为2
      }
    })
    projectList.value = res.data
  } catch (err) {
    Message.error('项目列表加载失败')
  }
}

//加载测试用例
const fetchTestProjects = async () => {
  try {
    const res = await axios.get('/testall',{
      params: {
        user_id: 2,
        program_id: selectedProjectId.value
      }
    });
    console.log("测试用例加载：",res.data)
    projectTestList.value = res.data

  } catch (err) {
    Message.error('测试用例加载失败')
  }
}

// 点击项目加载文件结构
const handleProjectClick = async (key) => {
  selectedProjectId.value = key;
  try {
    const res = await axios.get('/flat', {
      params: { programId: key },
    });
    fetchTestProjects()//获取对应的测试用例列表
    fileTree.value = buildTreeFromPaths(res.data);
    Message.success('文件结构加载成功');
  } catch (err) {
    Message.error('文件结构加载失败');
  }
};

// // 点击测试用例项目加载测试用例文件结构
// const handleTestProjectClick = async (key) => {
//   selectedTestProjectId.value = key;
//   try {
//     const res = await axios.get('/testflat', {
//       params: { programTestId: key },
//     });
//     //console.log("点击测试用例项目加载文件结构:",res.data);
//     fileTree.value = buildTreeFromPaths(res.data);
//     Message.success('文件结构加载成功');
//   } catch (err) {
//     Message.error('文件结构加载失败');
//   }
// };

// 路径数组转 treeData
const buildTreeFromPaths=(paths)=> {
  const root = []
  for (const path of paths) {
    const parts = path.split('/')
    let current = root
    for (let i = 0; i < parts.length; i++) {
      const name = parts[i]
      const key = parts.slice(0, i + 1).join('/')
      let existing = current.find(node => node.key === key)
      if (!existing) {
        existing = { title: name, key, children: [] }
        current.push(existing)
      }
      current = existing.children
    }
  }
  return root
}

//触发上传
const triggerUpload = () => {
  projectInput.value && projectInput.value.click();
  //document.querySelector('input[type="file"]').click();
}

//测试用例触发上传
const triggerTestUpload = () => {
  testInput.value && testInput.value.click();
  //document.querySelector('input[type="file"]').click();
};

//上传项目
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
    location.reload();
  } catch (e) {
    console.log(e)
    Message.error('上传失败');
  }
}

//上传测试用例
const handleTestFileUpload=async(event)=> {
  const files = Array.from(event.target.files);

  console.log("files:",files)
  console.log("selectedProjectId.value:",selectedProjectId.value)

  if (!selectedProjectId.value) {
    Message.warning('请先选择一个项目以匹配测试用例');
    return;
  }
  if (files.length === 0) {
    Message.warning('请选择至少一个文件上传');
    return;
  }

  const formData = new FormData();
  files.forEach(file => formData.append("files", file));
  formData.append("program_id", selectedProjectId.value);
  formData.append("user_id", 2); // 默认用户ID为2

  try {
    await axios.post("/uploadTestResources", formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
    Message.success("测试用例上传成功");
  } catch (err) {
    console.log("测试用例上传失败：",err)
    Message.error("上传失败");
  }
}

onMounted(() => {
  fetchProjects()
})

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
  max-width: 25vw;
  height: 93vh;
  background-color: rgba(246, 246, 246, 0.8);
  font-family: 'Microsoft YaHei', sans-serif;
}

.layout-demo :deep(.arco-layout-content) {
  background-color: rgb(var(--arcoblue-6));
}
</style>
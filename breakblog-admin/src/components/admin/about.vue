<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>博客管理</el-breadcrumb-item>
      <el-breadcrumb-item>关于页面</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-form
        :model="aboutForm"
        label-width="80px"
        :rules="aboutFormRules"
        ref="aboutFormRef"
      >
        <el-form-item label-width="0" prop="about">
          <editor v-model="aboutForm.about" :init="init" :disabled="disabled">
          </editor>
        </el-form-item>
      </el-form>
      <div class="tinymce-button">
        <el-button type="primary" @click="updateAbout">保存</el-button>
        <el-button>取消</el-button>
      </div>
    </el-card>
  </div>
</template>

<style lang="less" scoped>
.tinymce-button {
  //   text-align: center;
  margin-top: 16px;
}
</style>

<script>
import tinymce from 'tinymce/tinymce'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/themes/silver'
// 编辑器插件plugins
// 更多插件参考：https://www.tiny.cloud/docs/plugins/
import 'tinymce/plugins/image' // 插入上传图片插件
import 'tinymce/plugins/table' // 插入表格插件
import 'tinymce/plugins/lists' // 列表插件
import 'tinymce/plugins/autolink' // 自动链接插件
import 'tinymce/plugins/wordcount' // 字数统计插件
import 'tinymce/plugins/code' // 编辑源码插件
import 'tinymce/plugins/hr' // 分割线插件
import 'tinymce/plugins/codesample' // 编辑源码插件
import 'tinymce/plugins/fullscreen' // 表情插件
import 'tinymce/icons/default' //默认图标
import 'tinymce/plugins/help' //帮助
import '../../style/upload.css'
export default {
  components: {
    Editor
  },
  props: {
    // 基本路径，默认为空根目录，如果你的项目发布后的地址为目录形式，
    // 即 twhuang.top/tinymce，baseUrl需要配置成 /tinymce，不然发布后资源会找不到
    // 部署在二级目录需要配置
    baseUrl: {
      type: String,
      default: '/breakblog-admin'
    },
    disabled: {
      type: Boolean,
      default: false
    },
    plugins: {
      type: [String, Array],
      default:
        'lists image table wordcount autolink code codesample hr fullscreen help'
    },
    toolbar: {
      type: [String, Array],
      default:
        'undo redo | formatselect| bold italic forecolor strikethrough hr | bullist numlist outdent indent | alignleft aligncenter alignright alignjustify | image  table | removeformat  codesample  code fullscreen | help'
    }
  },
  data() {
    return {
      aboutForm: {
        id: '',
        about: ''
      },
      init: {
        language_url: `${this.baseUrl}/tinymce/langs/zh_CN.js`,
        language: 'zh_CN',
        skin_url: `${this.baseUrl}/tinymce/skins/ui/oxide`,
        content_css: `${this.baseUrl}/tinymce/skins/content/default/content.css`,
        height: 500,
        plugins: this.plugins,
        toolbar: this.toolbar,
        branding: false,
        menubar: false,
        images_upload_handler: async function(blobInfo, succFun, failFun) {
          var xhr, formData
          var file = blobInfo.blob() //转化为易于理解的file对象
          xhr = new XMLHttpRequest()
          xhr.withCredentials = false
          const token = sessionStorage.getItem('token')
          xhr.open('POST', 'https://twhuang.top/api/breakblog/file')
          xhr.setRequestHeader('Token', token)
          xhr.onload = function() {
            var json
            if (xhr.status !== 200) {
              failFun('HTTP Error: ' + xhr.status)
              return
            }
            json = JSON.parse(xhr.responseText)
            // console.log(json);
            if (!json || json.code !== 1) {
              failFun('Invalid JSON: ' + xhr.responseText)
              return
            }
            succFun(json.data.url)
          }
          formData = new FormData()
          formData.append('file', file, file.name) //此处与源文档不一样
          formData.append('path', 'image')
          xhr.send(formData)
        }
      },
      aboutFormRules: {
        about: [
          {
            required: true,
            message: '请输入关于内容',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  mounted() {
    tinymce.init({})
  },
  created() {
    this.getAbout()
  },
  methods: {
    updateAbout() {
      this.$refs.aboutFormRef.validate(async (valid) => {
        // console.log(valid);
        if (!valid) return
        const { data: res } = await this.$http.put('/admin', this.aboutForm)
        if (res.code === 0) {
          this.$message.error(res.msg)
        } else {
          this.$message.success(res.msg)
          await this.$router.go(0)
        }
      })
    },
    //初始化关于页面数据
    async getAbout() {
      const { data: res } = await this.$http.get('/currentUser')
      if (res.code !== 1) {
        this.$message.error('获取关于页面数据失败')
        return
      }
      this.aboutForm.id = res.data.id
      this.aboutForm.about = res.data.about
    }
  }
}
</script>

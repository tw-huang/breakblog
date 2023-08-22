<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>博客管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理博客</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-form
        :model="adminForm"
        label-width="80px"
        :rules="adminFormRules"
        ref="adminFormRef"
      >
        <el-form-item label="头像" prop="avatar">
          <el-upload
            action="https://twhuang.top/api/breakblog/file"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :limit="1"
            :headers="tokenHeaders"
            :data="uploadPath"
            :class="{ hide: hideUpload }"
            :on-success="handleUploadSuccess"
            :file-list="avatarList"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="" />
          </el-dialog>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="adminForm.username"
            placeholder="请输入用户名"
            class="admin-input"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="原密码" prop="password">
          <el-input
            v-model="adminForm.password"
            placeholder="若修改密码请输入原密码，否则留空"
            class="admin-input"
            type="password"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input
            v-model="adminForm.newPassword"
            placeholder="请输入新密码"
            class="admin-input"
            type="password"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="newPassword2">
          <el-input
            v-model="adminForm.newPassword2"
            placeholder="请输入确认密码"
            class="admin-input"
            type="password"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="标题" prop="blogTitle">
          <el-input
            v-model="adminForm.blogTitle"
            placeholder="请输入标题"
            class="admin-input"
          ></el-input>
        </el-form-item>
        <el-form-item label="副标题" prop="blogSubtitle">
          <el-input
            v-model="adminForm.blogSubtitle"
            placeholder="请输入副标题"
            class="admin-input"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model="adminForm.name"
            placeholder="请输入姓名"
            class="admin-input"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮件" prop="email">
          <el-input
            v-model="adminForm.email"
            placeholder="请输入邮件"
            class="admin-input"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input
            v-model="adminForm.phone"
            placeholder="请输入手机"
            class="admin-input"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="admin-btn">
        <el-button type="primary" @click="updateAdmin">修改</el-button>
        <el-button>取消</el-button>
      </div>
    </el-card>
  </div>
</template>

<style lang="less" scoped>
.admin-input {
  width: 300px;
}

.admin-btn {
  margin: 40px 0 0 80px;
}
</style>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (this.adminForm.password !== '' && this.adminForm.password !== ' ') {
        if (value === '' || value === ' ') {
          callback(new Error('请输入新密码'))
        }
        if (value.length < 6) {
          callback(new Error('密码长度最少为6位'))
        }
      }
      callback()
    }
    var validatePass2 = (rule, value, callback) => {
      if (this.adminForm.password !== '' && this.adminForm.password !== ' ') {
        if (
          this.adminForm.newPassword === '' ||
          this.adminForm.newPassword === ' '
        ) {
          callback(new Error('请先输入新密码'))
        }
        if (value === '' || value === ' ') {
          callback(new Error('请输入确认密码'))
        }
        if (value !== this.adminForm.newPassword) {
          callback(new Error('两次密码输入不一致'))
        }
      }
      callback()
    }

    return {
      // 上传图片token
      tokenHeaders: { Token: sessionStorage.getItem('token') },
      // 上传图片路径
      uploadPath: { path: 'breakblog' },
      // 上传单张图片隐藏状态
      hideUpload: false,
      // 限制图片数量
      limitCount: 1,
      dialogImageUrl: '',
      dialogVisible: false,
      avatarList: [],

      adminForm: {
        id: '',
        avatar: '',
        username: '',
        password: '',
        newPassword: '',
        newPassword2: '',
        blogTitle: '',
        blogSubtitle: '',
        name: '',
        email: '',
        phone: ''
      },
      adminFormRules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }
        ],
        newPassword: [
          {
            validator: validatePass,
            trigger: 'blur'
          }
        ],
        newPassword2: [
          {
            validator: validatePass2,
            trigger: 'blur'
          }
        ],
        blogTitle: [
          {
            required: true,
            message: '请输入博客标题',
            trigger: 'blur'
          }
        ],
        blogSubtitle: [
          {
            required: true,
            message: '请输入博客副标题',
            trigger: 'blur'
          }
        ],
        name: [
          {
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }
        ],
        email: [
          {
            required: true,
            message: '请输入邮件',
            trigger: 'blur'
          }
        ],
        phone: [
          {
            required: true,
            message: '请输入手机',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getCurrentUser()
  },
  methods: {
    handleRemove(file, fileList) {
      // console.log(file, fileList)
      this.hideUpload = fileList.length >= this.limitCount
      this.postForm.image = ''
    },
    handleChange(file, fileList) {
      // console.log(file, fileList)
      this.hideUpload = fileList.length >= this.limitCount
      this.hideUpload = true
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleUploadSuccess(response) {
      // console.log(response)
      this.postForm.image = response?.data?.url
    },

    updateAdmin() {
      // console.log(this.adminForm)
      //校验输入内容
      this.$refs.adminFormRef.validate(async (valid) => {
        // console.log('valid:' + valid)
        if (!valid) return
        const { data: res } = await this.$http.put('/admin', this.adminForm)
        if (res.code === 0) {
          this.$message.error(res.msg)
        }else {
          this.$message.success(res.msg)
          await this.$router.go(0)
        }
      })
    },

    //初始化当前用户数据
    async getCurrentUser() {
      const { data: res } = await this.$http.get('/currentUser')
      if (res.code !== 1) {
        this.$message.error('获取当前用户数据失败')
        return
      }
      this.adminForm.id = res.data.id
      this.adminForm.avatar = res.data.avatar
      this.adminForm.username = res.data.username
      this.adminForm.password = res.data.password
      this.adminForm.password = res.data.password
      this.adminForm.blogTitle = res.data.blogTitle
      this.adminForm.blogSubtitle = res.data.blogSubtitle
      this.adminForm.name = res.data.name
      this.adminForm.email = res.data.email
      this.adminForm.phone = res.data.phone
      // 图片回显示
      if (res.data.avatar && res.data.avatar !== '') {
        this.avatarList.push({ url: res.data.avatar })
        this.hideUpload = true
      }
    }
  },
}
</script>

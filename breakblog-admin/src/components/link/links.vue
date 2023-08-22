<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>友链管理</el-breadcrumb-item>
      <el-breadcrumb-item>友链列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 搜索于添加区域 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            placeholder="请输入内容"
            v-model="queryInfo.keyword"
            clearable
            @clear="getLinkList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getLinkList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="addDialogVisible = true"
            >添加友链</el-button
          >
        </el-col>
      </el-row>
      <!-- 友链列表区域 -->
      <el-table :data="linkList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="友链名称" prop="name"></el-table-column>
        <el-table-column label="友链地址" prop="url"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteLink(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[10, 15, 25]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>
    <!-- 添加友链对话框 -->
    <el-dialog
      title="添加友链"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!-- 内容主体区域 -->
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="70px"
      >
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="url">
          <el-input v-model="addForm.url"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addLink">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改友链对话框 -->
    <el-dialog
      title="修改友链"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!-- 内容主体区域 -->
      <el-form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        label-width="70px"
      >
        <el-form-item label="名称" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="url">
          <el-input v-model="editForm.url"></el-input></el-form-item
      ></el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editCategory">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //获取友链列表的参数对象
      queryInfo: {
        keyword: '',
        //当前页数
        pageNum: 1,
        //当前每页显示数据
        pageSize: 10
      },
      linkList: [],
      total: 0,
      //控制添加对话框的显示与隐藏
      addDialogVisible: false,
      //添加表单数据
      addForm: {
        name: '',
        url: ''
      },
      //添加表单验证规则对象
      addFormRules: {
        name: [
          {
            required: true,
            message: '请输入友链名称',
            trigger: 'blur'
          }
        ],
        url: [
          {
            required: true,
            message: '请输入友链地址',
            trigger: 'blur'
          }
        ]
      },

      //控制修改对话框的显示与隐藏
      editDialogVisible: false,
      //查询到的表单数据
      editForm: {
        name: '',
        url: ''
      },
      //修改表单验证规则对象
      editFormRules: {
        name: [
          {
            required: true,
            message: '请输入友链名称',
            trigger: 'blur'
          }
        ],
        url: [
          {
            required: true,
            message: '请输入友链地址',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getLinkList()
  },
  methods: {
    async getLinkList() {
      const { data: res } = await this.$http.get('/links', {
        params: this.queryInfo
      })
      if (res.code !== 1) {
        return this.$message.error('获取友链列表失败')
      }
      this.linkList = res.data.records
      this.total = res.data.total
    },
    //监听pagesize改变事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getLinkList()
    },
    //监听页码值改变事件
    handleCurrentChange(newPage) {
      // console.log(newPage);
      this.queryInfo.pageNum = newPage
      this.getLinkList()
    },

    //监听用户对话框关闭事件
    addDialogClosed() {
      //还原对话框数据
      this.$refs.addFormRef.resetFields()
    },
    //确定添加友链
    addLink() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return
        //发起请求
        const { data: res } = await this.$http.post('/link', this.addForm)
        if (res.code === 0) {
          this.$message.error('添加失败')
        }
        this.$message.success('添加成功')
        //隐藏添加对话框
        this.addDialogVisible = false
        //刷新页面，重新获取列表
        this.getLinkList()
      })
    },
    async showEditDialog(id) {
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/link/' + id)
      if (res.code === 0) return
      this.editForm = res.data
    },
    //监听用户对话框关闭事件
    editDialogClosed() {
      //还原对话框数据
      this.$refs.editFormRef.resetFields()
    },
    //确定修改友链
    editCategory() {
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return
        //发起请求
        // console.log(this.editForm);
        const { data: res } = await this.$http.put('/link', this.editForm)
        if (res.code === 0) {
          this.$message.error('修改失败')
        }
        this.$message.success('修改成功')
        //隐藏添加对话框
        this.editDialogVisible = false
        //刷新页面，重新获取列表
        this.getLinkList()
      })
    },
    //删除友链
    deleteLink(id) {
      this.$confirm('删除操作, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          //发起删除请求
          const { data: res } = await this.$http.delete('/link/' + id)
          if (res.code === 0) {
            this.$message.error('删除失败')
          }
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getLinkList()
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  }
}
</script>

<style lang="less" scoped></style>

<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>文章管理</el-breadcrumb-item>
      <el-breadcrumb-item>文章列表</el-breadcrumb-item>
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
            @clear="getPostList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getPostList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addPost">编写文章</el-button>
        </el-col>
      </el-row>
      <!-- 文章列表区域 -->
      <el-table :data="PostList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="文章标题" prop="title"></el-table-column>
        <el-table-column label="文章分类" prop="categoryName"></el-table-column>
        <el-table-column label="文章插图" prop="image">
          <template slot-scope="scope">
            <el-image
              style="width: 50px"
              :src="scope.row.image"
              :preview-src-list="[scope.row.image]"
              v-if="scope.row.image"
            >
            </el-image>
          </template>
        </el-table-column>
        <el-table-column label="浏览次数" prop="views"></el-table-column>
        <el-table-column label="评论数量" prop="counts"></el-table-column>
        <el-table-column label="评论状态">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.canComment"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="开启"
              inactive-text="关闭"
              @change="updateCanComment(scope.row.id, scope.row.canComment)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="editPost(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deletePost(scope.row.id, scope.row.title)"
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      //获取文章列表的参数对象
      queryInfo: {
        keyword: '',
        //当前页数
        pageNum: 1,
        //当前每页显示数据
        pageSize: 10
      },
      PostList: [],
      total: 0,
      //状态
      canComment: ''
    }
  },
  created() {
    this.getPostList()
  },
  methods: {
    async getPostList() {
      const { data: res } = await this.$http.get('/posts', {
        params: this.queryInfo
      })
      if (res.code !== 1) {
        return this.$message.error('获取文章列表失败')
      }
      this.PostList = res.data.list
      this.total = res.data.total
      // console.log(res.data.list);
    },
    //监听pagesize改变事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getPostList()
    },
    //监听页码值改变事件
    handleCurrentChange(newPage) {
      // console.log(newPage);
      this.queryInfo.pageNum = newPage
      this.getPostList()
    },
    //更新是否开启评论状态
    async updateCanComment(id, canComment) {
      const { data: res } = await this.$http.put('/post', {
        id: id,
        canComment: canComment
      })
      console.log(res)
    },
    //删除文章
    deletePost(id, title) {
      // console.log(title);
      this.$confirm('删除< ' + title + ' >这一篇文章？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          //发起删除请求
          const { data: res } = await this.$http.delete('/post/' + id)
          if (res.code === 0) {
            this.$message.error('删除失败')
          }
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getPostList()
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    addPost() {
      this.$router.push('/post')
    },
    editPost(id) {
      this.$root.$emit('next', '/post')
      this.$router.push('/post?id=' + id)
    }
  }
}
</script>

<style lang="less" scoped></style>

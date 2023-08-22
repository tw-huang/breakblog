<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>评论管理</el-breadcrumb-item>
      <el-breadcrumb-item>评论列表</el-breadcrumb-item>
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
            @clear="getCommentList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getCommentList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="8">
          <el-select
            v-model="queryInfo.postId"
            placeholder="请选择评论所属的文章"
            size="medium"
            clearable
            @change="getCommentList"
            value=""
          >
            <el-option
              v-for="(item, index) in posts"
              :label="item.title"
              :value="item.id"
              :key="index"
            ></el-option>
          </el-select>
        </el-col>
      </el-row>
      <!-- 评论列表区域 -->
      <el-table :data="commentList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="作者" prop="author"></el-table-column>
        <el-table-column label="email" prop="email"></el-table-column>
        <el-table-column label="网站" prop="site">
          <template slot-scope="scope">
            <a
              :href="scope.row.site"
              target="_blank"
              rel="noopener noreferrer"
              >{{ scope.row.site }}</a
            >
          </template>
        </el-table-column>
        <el-table-column label="文章" prop="postTitle"></el-table-column>
        <el-table-column label="内容" prop="body"></el-table-column>
        <el-table-column label="已读状态">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.reviewed"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="已读"
              inactive-text="未读"
              @change="updateReviewed(scope.row.id, scope.row.reviewed)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteComment(scope.row.id)"
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
      //获取评论列表的参数对象
      queryInfo: {
        keyword: '',
        postId: '',
        //当前页数
        pageNum: 1,
        //当前每页显示数据
        pageSize: 10
      },
      commentList: [],
      posts: [],
      total: 0,
      //状态
      reviewed: ''
    }
  },
  created() {
    this.getPosts()
    this.getCommentList()
  },
  methods: {
    async getPosts() {
      const { data: res } = await this.$http.get('/posts/list')
      // console.log(res);
      if (res.code !== 1) {
        return this.$message.error('获取文章列表失败')
      }
      this.posts = res.data
      // console.log(this.categories);
    },
    async getCommentList() {
      const { data: res } = await this.$http.get('/comments', {
        params: this.queryInfo
      })
      if (res.code !== 1) {
        return this.$message.error('获取评论列表失败')
      }
      this.commentList = res.data.list
      this.total = res.data.total
      // console.log(res.data.list);
    },
    //监听pagesize改变事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getCommentList()
    },
    //监听页码值改变事件
    handleCurrentChange(newPage) {
      // console.log(newPage);
      this.queryInfo.pageNum = newPage
      this.getCommentList()
    },
    //更新是否已读评论状态
    async updateReviewed(id, reviewed) {
      const { data: res } = await this.$http.put('/comment', {
        id: id,
        reviewed: reviewed
      })
      console.log(res)
    },
    //删除评论
    deleteComment(id) {
      this.$confirm('删除操作, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          //发起删除请求
          const { data: res } = await this.$http.delete('/comment/' + id)
          if (res.code === 0) {
            this.$message.error('删除失败')
          }
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getCommentList()
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

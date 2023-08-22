<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据统计</el-breadcrumb-item>
      <el-breadcrumb-item>数据报告</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card>
      <div id="categoryReport" style="width: 600px;height:400px;"></div>
    </el-card>
  </div>
</template>
<script>
import echarts from 'echarts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/title'
export default {
  mounted() {
    this.categoryReport()
  },
  methods: {
    async categoryReport() {
      const { data: res } = await this.$http.get('/category/report')

      let categoryReportEcharts = echarts.init(
        document.getElementById('categoryReport')
      )
      // 绘制图表
      categoryReportEcharts.setOption({
        title: { text: '分类-文章点击次数' },
        tooltip: {},
        xAxis: {
          data: res.data.name
        },
        yAxis: {},
        series: [
          {
            name: '点击量',
            type: 'bar',
            data: res.data.click,
            color: '#409eff'
          }
        ]
      })
    }
  }
}
</script>
<style lang="less" scoped></style>

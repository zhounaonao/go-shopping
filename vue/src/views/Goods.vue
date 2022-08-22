<template>
  <div class="home" style="padding: 10px">
    <!--  功能区域  -->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">
        添加</el-button>
      <el-button type="primary" @click="importExcelVisible = true">导入Excel</el-button>

      <el-dialog v-model="importExcelVisible" title="导入Excel">
        <el-form :model="excelForm" label-width="120px">
          <el-form-item label="上传Excel">
            <el-upload
                ref="upload"
                action="/api/goods/excel"
                :on-success="importSuccess"
                :before-upload="onBeforeUpload">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
<!--        <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="importExcelVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="saveExcel"-->
<!--        >确定</el-button>-->
<!--      </span>-->
<!--        </template>-->
      </el-dialog>

      <el-button type="primary">导出</el-button>
    </div>
    <!--  搜索区域  -->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">
        <el-icon style="vertical-align: middle">
          <Search />
        </el-icon>
        <span style="vertical-align: middle">查询</span>
      </el-button>
    </div>

    <el-table
        class="tableClass"
        :data="tableData"
              border
              stripe
              style="width: 100%"
              :row-style="{height:'60px'}" :cell-style="{padding: '0'}"
        :header-row-style="{height:'30px'}">
      <!--   el-table-column是列的定义{prop:传数据的名字; label:显示出来的名字; width:宽度，不写就自适应}   -->
      <el-table-column
          v-for="(value, key) in formColumns"
          :prop="key"
          :label="value.name"
          :width="value.width"
          align="center"/>

      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button style="padding: 0px" type="primary" text @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.goodsId)">
            <template #reference>
              <el-button style="padding: 0px" type="danger" text>删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />

      <el-dialog v-model="dialogFormVisible" title="新增">
        <el-form :model="form" label-width="120px">
          <el-form-item label="商品名称">
            <el-input v-model="form.goodsName" autocomplete="off" style="width: 80%" />
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="form.price" autocomplete="off" style="width: 80%" />
          </el-form-item>
          <el-form-item label="商品规格">
            <el-input v-model="form.specification" autocomplete="off" style="width: 80%" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.remark" autocomplete="off" style="width: 80%" />
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save"
        >确定</el-button>
      </span>
        </template>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";
import {readFile} from "@/utils/util";
import * as xlsx from "xlsx";

const formLabelWidth = '140px'
export default {
  name: 'Goods',
  components: {
    goodsId: ""
  },
  data(){
    return {
      form: {},
      excelForm: {},
      formColumns: {
        goodsId: {name: "商品编号"}, goodsName: {name: "商品名称"}, price: {name: "商品价格"},
        specification: {name: "商品规格"}, typeName: {name: "商品类型"}, status: {name: "商品状态"},
        updateTime: {name: "更新时间"}, createTime: {name: "创建时间"}, remark: {name: "备注"}
      },
      dialogFormVisible: false,
      importExcelVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total:10,
      tableData : [
        {goodsId: 100, goodsName: "小米10", price: 3999, specification: "个", typeName: "手机", status: "0", remark: "备注"}
      ]
    }
  },
  created() {
    this.load()
  },
  methods: {
    onBeforeUpload(file) {
      console.log(file)
    },

    importSuccess() {
      this.load()
    },
    load() {
      request.get("/goods/list", {
        params: { // 加上这个以保证我们的查询可以加入search
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }

      }).then(res => {
        console.log(res)
        this.tableData = res.data,
        this.total = res.total
      })
    },
    add() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      if (this.form.goodsId){ // 有id就更新
        request.put("/goods/update", this.form).then(res => { // => es6的语法
          console.log(res)
          if (res.code === 200){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
        })

      } else { // 没id就新增
        request.post("/goods/add", this.form).then(res => { // => es6的语法
          console.log(res)
          if (res.code === 200){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
        })

      }

      this.dialogFormVisible = false
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row)) // 避免深拷贝，取消后还在
      this.dialogFormVisible = true
    },
    handleDelete(goodsId){
      request.delete("/goods/delete?goodsId=" + goodsId).then(res => {
        console.log(res)
        if (res.code === 200){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })

    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      this.load()
    }
  },
}
</script>

<style>

</style>

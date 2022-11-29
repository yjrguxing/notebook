<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="id" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入分类名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类层级" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入分类层级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreatedTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdatedTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="禁用人" prop="deleteBy">
        <el-input
          v-model="queryParams.deleteBy"
          placeholder="请输入禁用人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="禁用时间">
        <el-date-picker
          v-model="daterangeDeleteTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="已禁用" prop="deleteFlag">
        <el-radio-group v-model="queryParams.deleteFlag">
          <el-radio-button label="0">否</el-radio-button>
          <el-radio-button label="1">是</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['menu:categroy:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['menu:categroy:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['menu:categroy:remove']"
        >禁用</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['menu:categroy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categroyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="分类名称" align="center" prop="name" />
      <el-table-column label="分类层级" align="center" prop="type" />
      <el-table-column label="父分类ID" align="center" prop="fatherId" />
      <el-table-column label="logo" align="center" prop="logo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.logo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="禁用人" align="center" prop="deleteBy" />
      <el-table-column label="禁用时间" align="center" prop="deleteTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deleteTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否已禁用" align="center" prop="deleteFlag" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.deleteFlag === 0" type="success">否</el-tag>
          <el-tag v-else type="danger">是</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['menu:categroy:edit']"
          >修改</el-button>
          <el-button
            v-if="scope.row.deleteFlag === 0"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['menu:categroy:remove']"
          >禁用</el-button>
          <el-button
            v-else
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleRecovery(scope.row)"
            v-hasPermi="['menu:categroy:recovery']"
          >恢复</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改多级菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <!--选择父级分类-->
        <el-form-item label="父级分类" prop="fatherId">
          <el-select v-model="form.fatherId" placeholder="请选择父级分类">
            <!--顶级分类-->
            <el-option label="顶级分类" :value="0"></el-option>
            <el-option
              v-for="item in categroyList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="分类层级" prop="type">
          <el-input v-model="form.type" placeholder="请输入分类层级" />
        </el-form-item>
        <el-form-item label="logo">
          <image-upload :limit="1" v-model="form.logo"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCategroy, getCategroy, delCategroy, addCategroy, updateCategroy, recoverCategroy } from "@/api/menu/categroy";

export default {
  name: "Categroy",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 多级菜单表格数据
      categroyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // logo图片链接;图标链接时间范围
      daterangeCreatedTime: [],
      // logo图片链接;图标链接时间范围
      daterangeUpdatedTime: [],
      // logo图片链接;图标链接时间范围
      daterangeDeleteTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        type: null,
        fatherId: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deleteBy: null,
        deleteTime: null,
        deleteFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "分类层级不能为空", trigger: "blur" }
        ],
        fatherId: [
          { required: true, message: "父级分类不能为空", trigger: "change" }
        ],
        deleteFlag: [
          { required: true, message: "是否已禁用不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询多级菜单列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreatedTime && '' != this.daterangeCreatedTime) {
        this.queryParams.params["beginCreatedTime"] = this.daterangeCreatedTime[0];
        this.queryParams.params["endCreatedTime"] = this.daterangeCreatedTime[1];
      }
      if (null != this.daterangeUpdatedTime && '' != this.daterangeUpdatedTime) {
        this.queryParams.params["beginUpdatedTime"] = this.daterangeUpdatedTime[0];
        this.queryParams.params["endUpdatedTime"] = this.daterangeUpdatedTime[1];
      }
      if (null != this.daterangeDeleteTime && '' != this.daterangeDeleteTime) {
        this.queryParams.params["beginDeleteTime"] = this.daterangeDeleteTime[0];
        this.queryParams.params["endDeleteTime"] = this.daterangeDeleteTime[1];
      }
      listCategroy(this.queryParams).then(response => {
        this.categroyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        type: null,
        fatherId: null,
        logo: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deleteBy: null,
        deleteTime: null,
        deleteFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreatedTime = [];
      this.daterangeUpdatedTime = [];
      this.daterangeDeleteTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加多级菜单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCategroy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改多级菜单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 表单格式化
      this.formFormatter(this.form);
      // 表单校验
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCategroy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            // 新增时，禁用标识为0
            this.form.deleteFlag = 0;
            addCategroy(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 禁用按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认禁用多级菜单编号为"' + ids + '"的数据项？').then(function() {
        return delCategroy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("禁用成功");
      }).catch(() => {});
    },
    /** 恢复按钮操作 */
    handleRecovery(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认恢复多级菜单编号为"' + ids + '"的数据项？').then(function() {
        return recoverCategroy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("恢复成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('menu/categroy/export', {
        ...this.queryParams
      }, `categroy_${new Date().getTime()}.xlsx`)
    },
    formFormatter(formData) {
      if(formData?.type == 1){
        this.form.fatherId = 0;
      }
    }
  }
};
</script>

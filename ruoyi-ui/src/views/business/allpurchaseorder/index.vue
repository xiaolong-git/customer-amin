<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户名称" prop="customerName" >
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单编号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="订单编号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="品牌名称" prop="brandName">
        <el-input
          v-model="queryParams.brandName"
          placeholder="请输入品牌名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->

      <!--<el-form-item label="字典类型" prop="dictType">
        <el-input
          v-model="queryParams.dictType"
          placeholder="请输入字典类型"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="字典状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--<el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:purchaseorder:add']"
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
          v-hasPermi="['system:dict:edit']"
        >修改</el-button>
      </el-col>-->
      <!--<el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:dict:remove']"
        >删除</el-button>
      </el-col>-->
      <!--<el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dict:export']"
        >导出</el-button>
      </el-col>-->
      <!--<el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-refresh"
          size="mini"
          @click="handleClearCache"
          v-hasPermi="['system:dict:remove']"
        >清理缓存</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="code" />
      <!--<el-table-column label="吨位" align="center" prop="tonnage" />
      <el-table-column label="单价" align="center" prop="unitPrice" />-->
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <!--<el-table-column label="品牌名称" align="center" prop="brandName" :formatter="brandFormat"/>
      <el-table-column label="产品名称" align="center" prop="productName" :formatter="productFormat"/>-->
      <el-table-column label="付款方式" align="center" prop="payType" :formatter="payTypeFormat"/>
      <!--<el-table-column label="是否开票" align="center" prop="makeInvoice" :formatter="makeInvoiceFormat"/>
      <el-table-column label="开票类型" align="center" prop="invoiceType" :formatter="invoiceTypeFormat"/>
      <el-table-column label="提货方式" align="center" prop="pickType" :formatter="pickTypeFormat"/>
      <el-table-column label="合同方" align="center" prop="contractOwnership" :formatter="contractOwnershipFormat"/>-->
      <el-table-column label="订单状态" align="center" prop="status" :formatter="purchaseOrderStatusFormat"/>
      <!--<el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />-->
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDetail(scope.row)"
          >查看</el-button>
          <el-button
            v-if="scope.row.status === '2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleShowContractDialog(scope.row)"
          >查看合同</el-button>
          <!--<el-button
            v-if="scope.row.status === '0'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:purchaseorder:edit']"
          >修改</el-button>
          <el-button
            v-if="scope.row.status === '0' || scope.row.status === '3'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateStatus(scope.row, '1')"
            v-hasPermi="['business:purchaseorder:updateStatus']"
          >提交</el-button>
          <el-button
            v-if="scope.row.status === '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateStatus(scope.row, '0')"
            v-hasPermi="['business:purchaseorder:updateStatus']"
          >撤回</el-button>
          <el-button
            v-if="scope.row.status === '0' || scope.row.status === '3'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:purchaseorder:remove']"
          >删除</el-button>-->
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1400px" append-to-body>
      <!--<basic-info-form ref="basicInfo" :info="info" />-->
      <purchaseorder-info-form ref="purchaseorderInfo" :key="timer" :orderInfo="orderInfo" />

      <!--<Order ref="orderRef" v-on:getSupplierData="getSupplierDataClick" :supplier-param={} enabled="true"/>-->


      <!--<el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="订单编号" prop="code">
              <el-input v-model="form.code" placeholder="订单编号" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="提货方式" prop="pickType">
              <el-select v-model="form.pickType" placeholder="提货方式" clearable size="small" :disabled="isDisabled">
                <el-option
                  v-for="dict in pickTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="付款方式" prop="payType">
              <el-select v-model="form.payType" placeholder="付款方式" clearable size="small" :disabled="isDisabled">
                <el-option
                  v-for="dict in payTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户" prop="customerName">
              <el-input v-model="form.customerName" placeholder="客户" :disabled="isDisabled"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="运费" prop="freight">
              <el-input v-model="form.freight" placeholder="运费" :disabled="isDisabled"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-button
              type="success"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAddProduct"
              v-if="!isDisabled"
            >增加商品</el-button>
          </el-col>
        </el-row>
        <el-row >
          <el-card class="box-card" shadow="never">
            <div v-for="(row,index) in form.productItems">
              <el-row type="flex" justify="center"  >
                <el-col :span="5">
                  <el-form-item label="品牌" prop="brandId">
                    <el-select filterable v-model="form.productItems[index].brandId" placeholder="品牌" clearable size="small" :disabled="isDisabled" @change="handleChooseBrand(index)">
                      <el-option
                        v-for="brand in brandList"
                        :key="brand.name"
                        :label="brand.name"
                        :value="brand.id"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="5">
                  <el-form-item label="产品" prop="productId">
                    <el-select filterable v-model="form.productItems[index].productId" placeholder="产品" clearable size="small" :disabled="isDisabled">
                      <el-option
                        v-for="product in productList[index]"
                        :key="product.name"
                        :label="product.name"
                        :value="product.id"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="5">
                  <el-form-item label="吨位" prop="tonnage">
                    <el-input v-model="form.productItems[index].tonnage" placeholder="吨位" :disabled="isDisabled"/>
                  </el-form-item>
                </el-col>
                <el-col :span="5">
                  <el-form-item label="单价" prop="unitPrice">
                    <el-input v-model="form.productItems[index].unitPrice" placeholder="单价" :disabled="isDisabled"/>
                  </el-form-item>
                </el-col>
                <el-col :offset="2" :span="4" >
                  <el-button v-if="!isDisabled" type="danger" plain size="medium" @click="handleDelProduct(index)">删除</el-button>
                </el-col>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="合同方" prop="contractOwnership">
              <el-radio-group v-model="form.contractOwnership" size="small" :disabled="isDisabled">
                <el-radio
                  v-for="dict in contractOwnershipOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否开票" prop="makeInvoice">
              <el-radio-group v-model="form.makeInvoice" size="small" :disabled="isDisabled">
                <el-radio
                  v-for="dict in makeInvoiceOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开票类型" prop="invoiceType">
              <el-select v-model="form.invoiceType" placeholder="开票类型" clearable size="small" :disabled="isDisabled">
                <el-option
                  v-for="dict in invoiceTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" :disabled="isDisabled"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="!isDisabled" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>-->
    </el-dialog>

    <!-- 展示合同对话框 -->
    <el-dialog :title="showTitle" :visible.sync="openShowDialog" width="1000px" append-to-body>
      <el-form ref="showForm" :model="showForm" label-width="80px">
        <div v-for="(row,index) in showFileList">
          <el-row type="flex" justify="center"  >
            <el-col :span="20">
              <img :src="row.pic" alt="light">
            </el-col>
          </el-row>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import { listPurchaseorderByUser, listPurchaseorder, getPurchaseorder, delPurchaseorder, addPurchaseorder, updatePurchaseorder, exportPurchaseorder, updateStatusPurchaseorder } from "@/api/business/purchaseorder/purchaseorder";
  import { listBrand } from "@/api/business/brand/brand";
  import { listProductByProperty } from "@/api/business/product/product";
  import { listOrderContractRelation } from "@/api/business/orderContractRelation/orderContractRelation";
  import basicInfoForm from "../../tool/gen/basicInfoForm";
  import purchaseorderInfoForm from "../../tool/business/purchaseorderForm";

  export default {
    name: "allpurchaseorder",
    components: {
      basicInfoForm,
      purchaseorderInfoForm
    },
    data() {
      return {
        // 表详细信息
        orderInfo: {},
        timer: '',
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
        // 订单表格数据
        typeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据订单
        statusOptions: [],
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          dictName: undefined,
          dictType: undefined,
          status: undefined
        },
        //添加是否禁用
        isDisabled: false,
        //采购订单状态字典
        purchaseOrderStatusOptions: [],
        // 提货方式字典
        pickTypeOptions: [],
        //支付方式字典
        payTypeOptions: [],
        //客户字典
        //customerOptions: [],
        //品牌字典
        brandOptions: [],
        //产品字典
        productOptions: [],
        //合同方方字典
        contractOwnershipOptions: [],
        //开票类型字典
        invoiceTypeOptions: [],
        //是否开票字典
        makeInvoiceOptions: [],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          dictName: [
            { required: true, message: "字典名称不能为空", trigger: "blur" }
          ],
          dictType: [
            { required: true, message: "字典类型不能为空", trigger: "blur" }
          ]
        },
        //品牌列表
        brandList: [],
        //商品列表
        productList: [],
        //弹出展示合同对话框
        openShowDialog: false,
        showTitle: '合同列表',
        //展示合同文件集合
        showFileList: [],
        showForm: {}
      };
    },
    created() {
      this.brandAllList();
      this.getList();
      /*this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });*/
      this.getDicts("purchase_order_status_options").then(response => {
        this.purchaseOrderStatusOptions = response.data;
      });
      this.getDicts("pick_type_options").then(response => {
        this.pickTypeOptions = response.data;
      });
      this.getDicts("pay_type_options").then(response => {
        this.payTypeOptions = response.data;
      });
      /*this.getDicts("customer_options").then(response => {
        this.customerOptions = response.data;
      });*/
      this.getDicts("brand_options").then(response => {
        this.brandOptions = response.data;
      });
      this.getDicts("product_options").then(response => {
        this.productOptions = response.data;
      });
      this.getDicts("contract_ownership_options").then(response => {
        this.contractOwnershipOptions = response.data;
      });
      this.getDicts("invoice_type_options").then(response => {
        this.invoiceTypeOptions = response.data;
      });
      this.getDicts("make_invoice_options").then(response => {
        this.makeInvoiceOptions = response.data;
      });

    },
    methods: {
      /** 查询订单类型列表 */
      getList() {
        this.loading = true;
        listPurchaseorder(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.typeList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      /** 查询品牌列表 */
      brandAllList() {
        listBrand().then(response => {
          this.brandList = response.rows;
        });
      },
      /*// 订单状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },*/
      // 订单状态字典翻译
      purchaseOrderStatusFormat(row, column) {
        return this.selectDictLabel(this.purchaseOrderStatusOptions, row.status);
      },
      // 合同方字典翻译
      contractOwnershipFormat(row, column) {
        return this.selectDictLabel(this.contractOwnershipOptions, row.contractOwnership);
      },
      // 客户字典翻译
      /*customerFormat(row, column) {
        return this.selectDictLabel(this.customerOptions, row.customerId);
      },*/
      // 品牌字典翻译
      brandFormat(row, column) {
        return this.selectDictLabel(this.brandOptions, row.brandId);
      },
      // 产品字典翻译
      productFormat(row, column) {
        return this.selectDictLabel(this.productOptions, row.productId);
      },
      // 付款方式字典翻译
      payTypeFormat(row, column) {
        return this.selectDictLabel(this.payTypeOptions, row.payType);
      },
      // 是否开票字典翻译
      makeInvoiceFormat(row, column) {
        return this.selectDictLabel(this.makeInvoiceOptions, row.makeInvoice);
      },
      // 是否开票字典翻译
      invoiceTypeFormat(row, column) {
        return this.selectDictLabel(this.invoiceTypeOptions, row.invoiceType);
      },
      // 是否开票字典翻译
      pickTypeFormat(row, column) {
        return this.selectDictLabel(this.pickTypeOptions, row.pickType);
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          code: undefined,
          unitPrice: undefined,
          customerName: undefined,
          brandId: undefined,
          productId: undefined,
          payType: undefined,
          makeInvoice: undefined,
          pickType: undefined,
          contractOwnership: undefined,
          invoiceType: undefined,
          freight: undefined,
          remark: undefined,
          productItems: undefined
        };
        this.resetForm("form");
      },
      /** 添加商品选择品牌 */
      handleChooseBrand(index){
        let param = {}
        param.brandId = this.form.productItems[index].brandId
        listProductByProperty(param).then(response => {
          let productItemList = []
          if(response.rows){
            productItemList = response.rows
          }
          console.log(this.productList)
          this.productList[index] = productItemList
          this.$set(this.productList, index, productItemList)
          console.log(this.productList)
        });
      },
      /** 添加商品按钮 */
      handleAddProduct(){
        if(!this.form.productItems){
          this.form.productItems = []
        }

        let brandProductItem = {}
        this.form.productItems.push(brandProductItem)
      },
      /** 删除商品按钮 */
      handleDelProduct(index){
        this.form.productItems.splice(index, 1);

        //删除对应商品列表
        this.productList.splice(index, 1)
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.isDisabled = false
        this.reset();
        this.open = true;
        this.title = "添加采购订单";

        this.form.productItems = []
        let brandProductItem = {}
        this.form.productItems.push(brandProductItem)

      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleDetail(row) {
        /*this.isDisabled = true
        this.reset();
        const id = row.id || this.ids
        getPurchaseorder(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看采购订单";
        });*/
        this.timer = new Date().getTime()

        this.open = true;
        this.orderInfo = {}
        this.orderInfo.id = row.id

        //this.open = false;
        //this.$refs.purchaseorderInfo.$refs.handleDetail(row.id)
        //this.$refs.purchaseorderInfo.handleDetail(row.id)
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isDisabled = false
        this.reset();
        const id = row.id || this.ids
        getPurchaseorder(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改采购订单";
        });
      },
      handleUpdateStatus(row, status) {
        const id = row.id || this.ids
        let param = {}
        param.id = row.id
        param.status = status
        updateStatusPurchaseorder(param).then(response => {
          this.msgSuccess("操作成功");
          this.getList();
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            debugger
            let productItems = this.form.productItems
            if(!productItems || productItems.length<1){
              this.msgInfo("请添加商品");
              return false;
            }

            if(!this.form){
              this.msgInfo("请添加商品");
              return false;
            }

            for (let i = 0; i < productItems.length; i++) {
              let item = productItems[i]
              if(!item.brandId){
                this.msgInfo("请选择商品品牌");
                return false;
              }
              if(!item.productId){
                this.msgInfo("请选择商品");
                return false;
              }
              if(!item.tonnage){
                this.msgInfo("请填写商品吨位");
                return false;
              }
              if(!item.unitPrice){
                this.msgInfo("请填写商品单价");
                return false;
              }
            }
            /*productItems.forEach(function(item, index) {
              //item 就是当日按循环到的对象
              //index是循环的索引，从0开始

            })*/

            if (this.form.id != undefined) {
              updatePurchaseorder(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addPurchaseorder(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPurchaseorder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /**展示合同*/
      handleShowContractDialog(row){
        //重置展示集合
        this.showFileList = []
        this.openShowDialog = true

        //根据订单展示合同
        let param = {}
        param.orderId = row.id
        listOrderContractRelation(param).then(response => {
          if(response.rows){
            this.showFileList = response.rows
            if(this.showFileList && this.showFileList.length>0){
              for (var i = 0; i<this.showFileList.length; i++) {
                var showItem = this.showFileList[i]
                if(showItem.pic){
                  this.showFileList[i].pic = process.env.VUE_APP_BASE_API+showItem.pic
                }
              }
            }
          }
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有采购订单数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPurchaseorder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      },
      /** 清理缓存按钮操作 */
      /*handleClearCache() {
        clearCache().then(response => {
          this.msgSuccess("清理成功");
        });
      }*/
    }
  };
</script>

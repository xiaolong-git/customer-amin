<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="订单编号" prop="code">
            <el-input v-model="form.code" placeholder="订单编号" disabled/>
          </el-form-item>
        </el-col>
      </el-row>
      <!--<el-row>
        <el-col :span="12">
          <el-form-item label="吨位" prop="tonnage">
            <el-input v-model="form.tonnage" placeholder="吨位" :disabled="isDisabled"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单价" prop="unitPrice">
            <el-input v-model="form.unitPrice" placeholder="单价" :disabled="isDisabled"/>
          </el-form-item>
        </el-col>
      </el-row>-->
      <el-row>
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
        <el-col :span="12">
          <el-form-item label="付款备注" prop="payType">
            <el-input v-model="form.payRemark" placeholder="付款备注" :disabled="isDisabled"/>
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
          <el-form-item label="客户" prop="customerName">
            <el-input v-model="form.customerName" placeholder="客户" :disabled="isDisabled"/>
            <!--<el-select filterable v-model="form.customerId" placeholder="客户" clearable size="small" :disabled="isDisabled">
              <el-option
                v-for="dict in customerOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>-->
          </el-form-item>
        </el-col>
        <!--<el-col :span="12">
          <el-form-item label="运费" prop="freight">
            <el-input v-model="form.freight" placeholder="运费" :disabled="isDisabled"/>
          </el-form-item>
        </el-col>-->
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
              <el-col :span="4">
                <el-form-item label="单位" prop="unit">
                  <el-select filterable v-model="form.productItems[index].unit" placeholder="单位" clearable size="small" :disabled="isDisabled">
                    <el-option
                      v-for="dict in unitOptions"
                      :key="dict.dictValue"
                      :label="dict.dictLabel"
                      :value="dict.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="数量" prop="quantity">
                  <el-input v-model="form.productItems[index].quantity" placeholder="数量" :disabled="isDisabled"/>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="单价" prop="unitPrice">
                  <el-input v-model="form.productItems[index].unitPrice" placeholder="单价" :disabled="isDisabled"/>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="运费" prop="freight">
                  <el-input v-model="form.productItems[index].freight" placeholder="运费" :disabled="isDisabled"/>
                </el-form-item>
              </el-col>
              <el-col :offset="2" :span="2" >
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
            <!--<el-radio-group v-model="form.makeInvoice">
              <el-radio
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>-->
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
    <!--<div style="text-align: right">
      <el-button v-if="!isDisabled" type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>-->
  </div>
</template>
<script>


import { listPurchaseorderByUser, listPurchaseorder, getPurchaseorder, delPurchaseorder, addPurchaseorder, updatePurchaseorder, exportPurchaseorder, updateStatusPurchaseorder } from "@/api/business/purchaseorder/purchaseorder";
import { listBrand } from "@/api/business/brand/brand";
import { getToken } from "@/utils/auth";
import { listProductByProperty } from "@/api/business/product/product";
import { listOrderContractRelation } from "@/api/business/orderContractRelation/orderContractRelation";
import axios from "axios";

export default {
  name: "purchaseorderInfoForm",
  props: {
    orderInfo: {
      id: null
    }
  },
  data() {
    return {
      //添加是否禁用
      isDisabled: false,
      //采购订单状态字典
      purchaseOrderStatusOptions: [],
      //单位字典
      unitOptions: [],
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
      //品牌列表
      brandList: [],
      //商品列表
      productList: [],
      //单位列表
      unitList: [],
      // 表单参数
      form: {
        productItems:[]
      },
      // 表单校验
      rules: {
        dictName: [
          { required: true, message: "字典名称不能为空", trigger: "blur" }
        ],
        dictType: [
          { required: true, message: "字典类型不能为空", trigger: "blur" }
        ]
      },
    };
  },
  created() {
    debugger
    //重置页面
    this.reset()

    this.brandAllList();
    this.getDicts("purchase_order_status_options").then(response => {
      this.purchaseOrderStatusOptions = response.data;
    });
    this.getDicts("pick_type_options").then(response => {
      this.pickTypeOptions = response.data;
    });
    this.getDicts("unit_options").then(response => {
      this.unitOptions = response.data;
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

    if(this.orderInfo && this.orderInfo.id){
      this.handleDetail(this.orderInfo.id)
      this.isDisabled = true
    }

  },
  // 初始化-挂载后
  mounted() {
    console.log("mounted")
  },
  // 监听
  watch: {
  },
  // 计算属性
  computed: {},
  // 自定义组件-按钮权限
  directives: {},
  methods: {
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

      this.isDisabled = false
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
    /** 查看订单详情 */
    handleDetail(id){
      getPurchaseorder(id).then(response => {
        this.form = response.data;
        this.isDisabled = true
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
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
            if(!item.unitPrice){
              this.msgInfo("请填写商品单价");
              return false;
            }
            if(!item.quantity){
              this.msgInfo("请填写商品数量");
              return false;
            }
            if(!item.unit){
              this.msgInfo("请选择商品数量单位");
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
  }
};
</script>

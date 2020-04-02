<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      <#list table.fields as field>
        <#if field.keyFlag == false>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="${field.comment}">
            <#if field.propertyType =='Date'>
              <a-date-picker v-decorator="[ '${field.propertyName}', <#if field.convert == false>validatorRules.${field.propertyName} <#else>{}</#if>]" />
            <#elseif field.propertyType =='Datetime'>
              <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ '${field.propertyName}', <#if field.convert == false>validatorRules.${field.propertyName} <#else>{}</#if>]" />
            <#elseif "Integer,Decimal,Double,"?contains(field.propertyType)>
              <a-input-number v-decorator="[ '${field.propertyName}', <#if field.convert == false>validatorRules.${field.propertyName} <#else>{}</#if>]" />
            <#else>
              <a-input placeholder="请输入${field.comment!}" v-decorator="['${field.propertyName}', <#if field.convert == false>validatorRules.${field.propertyName} <#else>{}</#if>]" />
            </#if>
          </a-form-item>
        </#if>
      </#list>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "${entity}Modal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 }
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 }
        },
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
          <#list table.fields as field>
          <#if field.keyFlag == false>
          <#if field.convert == false>
          ${field.propertyName}:{rules: [{ required: true, message: '请输入${field.comment!}!' }]},
          </#if>
          </#if>
          </#list>
        },
        url: {
          add: "/${table.entityPath}/add",
          edit: "/${table.entityPath}/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model<#list table.fields as field><#if field.keyFlag == false && field.propertyType?index_of("Date")==-1>,'${field.propertyName}'</#if></#list>))
          //时间格式化
          <#list table.fields as field>
          <#if field.keyFlag == false && field.propertyType?index_of("Date")!=-1>
          this.form.setFieldsValue({${field.propertyName}:this.model.${field.propertyName}?moment(this.model.${field.propertyName}):null})
          </#if>
          </#list>
        });
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
              method = 'put';
            }
            let formData = Object.assign(this.model, values);
            // 时间格式化
            <#list table.fields as field>
            <#if field.keyFlag == false && field.propertyType =='Date'>
            formData.${field.propertyName} = formData.${field.propertyName}?formData.${field.propertyName}.format():null;
            <#elseif field.keyFlag == false && field.propertyType =='Datetime'>
            formData.${field.propertyName} = formData.${field.propertyName}?formData.${field.propertyName}.format('YYYY-MM-DD HH:mm:ss'):null;
            </#if>
            </#list>

            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
        })
      },
      handleCancel () {
        this.close()
      }

    }
  }
</script>

<style>

</style>
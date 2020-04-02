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
                <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="登录账号">
              <a-input placeholder="请输入登录账号" v-decorator="['username', validatorRules.username ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="真实姓名">
              <a-input placeholder="请输入真实姓名" v-decorator="['realname', validatorRules.realname ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="密码">
              <a-input placeholder="请输入密码" v-decorator="['password', validatorRules.password ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="md5密码盐">
              <a-input placeholder="请输入md5密码盐" v-decorator="['salt', validatorRules.salt ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="头像">
              <a-input placeholder="请输入头像" v-decorator="['avatar', validatorRules.avatar ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="生日">
              <a-date-picker v-decorator="[ 'birthday', validatorRules.birthday ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="性别(0-默认未知,1-男,2-女)">
              <a-input placeholder="请输入性别(0-默认未知,1-男,2-女)" v-decorator="['sex', validatorRules.sex ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="电子邮件">
              <a-input placeholder="请输入电子邮件" v-decorator="['email', validatorRules.email ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="电话">
              <a-input placeholder="请输入电话" v-decorator="['phone', validatorRules.phone ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="机构编码">
              <a-input placeholder="请输入机构编码" v-decorator="['orgCode', validatorRules.orgCode ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="性别(1-正常,2-冻结)">
              <a-input placeholder="请输入性别(1-正常,2-冻结)" v-decorator="['status', validatorRules.status ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="删除状态(0-正常,1-已删除)">
              <a-input placeholder="请输入删除状态(0-正常,1-已删除)" v-decorator="['delFlag', validatorRules.delFlag ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="同步工作流引擎(1-同步,0-不同步)">
              <a-input placeholder="请输入同步工作流引擎(1-同步,0-不同步)" v-decorator="['activitiSync', validatorRules.activitiSync ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="工号，唯一键">
              <a-input placeholder="请输入工号，唯一键" v-decorator="['workNo', validatorRules.workNo ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="职务，关联职务表">
              <a-input placeholder="请输入职务，关联职务表" v-decorator="['post', validatorRules.post ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="座机号">
              <a-input placeholder="请输入座机号" v-decorator="['telephone', validatorRules.telephone ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="创建人">
              <a-input placeholder="请输入创建人" v-decorator="['createBy', validatorRules.createBy ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="创建时间">
              <a-date-picker v-decorator="[ 'createTime', validatorRules.createTime ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="更新人">
              <a-input placeholder="请输入更新人" v-decorator="['updateBy', validatorRules.updateBy ]" />
          </a-form-item>
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="更新时间">
              <a-date-picker v-decorator="[ 'updateTime', validatorRules.updateTime ]" />
          </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "AuthInfoAdminModal",
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
          username:{rules: [{ required: true, message: '请输入登录账号!' }]},
          realname:{rules: [{ required: true, message: '请输入真实姓名!' }]},
          password:{rules: [{ required: true, message: '请输入密码!' }]},
          salt:{rules: [{ required: true, message: '请输入md5密码盐!' }]},
          avatar:{rules: [{ required: true, message: '请输入头像!' }]},
          birthday:{rules: [{ required: true, message: '请输入生日!' }]},
          sex:{rules: [{ required: true, message: '请输入性别(0-默认未知,1-男,2-女)!' }]},
          email:{rules: [{ required: true, message: '请输入电子邮件!' }]},
          phone:{rules: [{ required: true, message: '请输入电话!' }]},
          orgCode:{rules: [{ required: true, message: '请输入机构编码!' }]},
          status:{rules: [{ required: true, message: '请输入性别(1-正常,2-冻结)!' }]},
          delFlag:{rules: [{ required: true, message: '请输入删除状态(0-正常,1-已删除)!' }]},
          activitiSync:{rules: [{ required: true, message: '请输入同步工作流引擎(1-同步,0-不同步)!' }]},
          workNo:{rules: [{ required: true, message: '请输入工号，唯一键!' }]},
          post:{rules: [{ required: true, message: '请输入职务，关联职务表!' }]},
          telephone:{rules: [{ required: true, message: '请输入座机号!' }]},
          createBy:{rules: [{ required: true, message: '请输入创建人!' }]},
          createTime:{rules: [{ required: true, message: '请输入创建时间!' }]},
          updateBy:{rules: [{ required: true, message: '请输入更新人!' }]},
          updateTime:{rules: [{ required: true, message: '请输入更新时间!' }]},
        },
        url: {
          add: "/authInfoAdmin/add",
          edit: "/authInfoAdmin/edit",
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
          this.form.setFieldsValue(pick(this.model,'username','realname','password','salt','avatar','sex','email','phone','orgCode','status','delFlag','activitiSync','workNo','post','telephone','createBy','updateBy'))
          //时间格式化
          this.form.setFieldsValue({birthday:this.model.birthday?moment(this.model.birthday):null})
          this.form.setFieldsValue({createTime:this.model.createTime?moment(this.model.createTime):null})
          this.form.setFieldsValue({updateTime:this.model.updateTime?moment(this.model.updateTime):null})
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
            formData.birthday = formData.birthday?formData.birthday.format():null;
            formData.createTime = formData.createTime?formData.createTime.format():null;
            formData.updateTime = formData.updateTime?formData.updateTime.format():null;

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
<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
                      <a-col :md="6" :sm="8">
              <a-form-item label="主键id">
                <a-input placeholder="请输入主键id" v-model="queryParam.id"></a-input>
              </a-form-item>
            </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="登录账号">
              <a-input placeholder="请输入登录账号" v-model="queryParam.username"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="真实姓名">
              <a-input placeholder="请输入真实姓名" v-model="queryParam.realname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="密码">
              <a-input placeholder="请输入密码" v-model="queryParam.password"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="md5密码盐">
              <a-input placeholder="请输入md5密码盐" v-model="queryParam.salt"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="头像">
              <a-input placeholder="请输入头像" v-model="queryParam.avatar"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="生日">
              <a-input placeholder="请输入生日" v-model="queryParam.birthday"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="性别(0-默认未知,1-男,2-女)">
              <a-input placeholder="请输入性别(0-默认未知,1-男,2-女)" v-model="queryParam.sex"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="电子邮件">
              <a-input placeholder="请输入电子邮件" v-model="queryParam.email"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="电话">
              <a-input placeholder="请输入电话" v-model="queryParam.phone"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="机构编码">
              <a-input placeholder="请输入机构编码" v-model="queryParam.orgCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="性别(1-正常,2-冻结)">
              <a-input placeholder="请输入性别(1-正常,2-冻结)" v-model="queryParam.status"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="删除状态(0-正常,1-已删除)">
              <a-input placeholder="请输入删除状态(0-正常,1-已删除)" v-model="queryParam.delFlag"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="同步工作流引擎(1-同步,0-不同步)">
              <a-input placeholder="请输入同步工作流引擎(1-同步,0-不同步)" v-model="queryParam.activitiSync"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="工号，唯一键">
              <a-input placeholder="请输入工号，唯一键" v-model="queryParam.workNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="职务，关联职务表">
              <a-input placeholder="请输入职务，关联职务表" v-model="queryParam.post"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="座机号">
              <a-input placeholder="请输入座机号" v-model="queryParam.telephone"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="创建人">
              <a-input placeholder="请输入创建人" v-model="queryParam.createBy"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="创建时间">
              <a-input placeholder="请输入创建时间" v-model="queryParam.createTime"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="更新人">
              <a-input placeholder="请输入更新人" v-model="queryParam.updateBy"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="更新时间">
              <a-input placeholder="请输入更新时间" v-model="queryParam.updateTime"></a-input>
            </a-form-item>
          </a-col>
            </template>

          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <authInfoAdmin-modal ref="modalForm" @ok="modalFormOk"></authInfoAdmin-modal>

  </a-card>
</template>

<script>
  import AuthInfoAdminModal from './modules/AuthInfoAdminModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "AuthInfoAdminList",
    mixins: [JeecgListMixin],
    components: {
      AuthInfoAdminModal
    },
    data () {
      return {
        description: '用户表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '登录账号',
            align:"center",
            dataIndex: 'username'
          },
          {
            title: '真实姓名',
            align:"center",
            dataIndex: 'realname'
          },
          {
            title: '密码',
            align:"center",
            dataIndex: 'password'
          },
          {
            title: 'md5密码盐',
            align:"center",
            dataIndex: 'salt'
          },
          {
            title: '头像',
            align:"center",
            dataIndex: 'avatar'
          },
          {
            title: '生日',
            align:"center",
            dataIndex: 'birthday'
          },
          {
            title: '性别(0-默认未知,1-男,2-女)',
            align:"center",
            dataIndex: 'sex'
          },
          {
            title: '电子邮件',
            align:"center",
            dataIndex: 'email'
          },
          {
            title: '电话',
            align:"center",
            dataIndex: 'phone'
          },
          {
            title: '机构编码',
            align:"center",
            dataIndex: 'orgCode'
          },
          {
            title: '性别(1-正常,2-冻结)',
            align:"center",
            dataIndex: 'status'
          },
          {
            title: '删除状态(0-正常,1-已删除)',
            align:"center",
            dataIndex: 'delFlag'
          },
          {
            title: '同步工作流引擎(1-同步,0-不同步)',
            align:"center",
            dataIndex: 'activitiSync'
          },
          {
            title: '工号，唯一键',
            align:"center",
            dataIndex: 'workNo'
          },
          {
            title: '职务，关联职务表',
            align:"center",
            dataIndex: 'post'
          },
          {
            title: '座机号',
            align:"center",
            dataIndex: 'telephone'
          },
          {
            title: '创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title: '创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title: '更新人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title: '更新时间',
            align:"center",
            dataIndex: 'updateTime'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/authInfoAdmin/list",
          delete: "/authInfoAdmin/delete",
          deleteBatch: "/authInfoAdmin/deleteBatch"
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import '~@/assets/less/common.less';
</style>
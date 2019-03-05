<template>
    <el-row class="wrap">
        <el-col :span="24" class="warp-breadcrum">
        <el-breadcrumb>
            <el-breadcrumb-item>首页</el-breadcrumb-item>
            <el-breadcrumb-item>错误日志</el-breadcrumb-item>
        </el-breadcrumb>
        </el-col>
        <el-col :span="24" class="wrap-main">
             <el-form size="small" :inline="true">
                <el-form-item>
                <el-input></el-input>
                </el-form-item>
                <el-form-item>
                <el-button type="primary" @click="search(10)" >查询</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="rows" style="width: 100%"  @row-dblclick="rowclick">
                <el-table-column label="日志编号" prop="logid"></el-table-column>
                <el-table-column label="操作时间" prop="logtime"></el-table-column>
                <el-table-column label="地址" prop="url"></el-table-column>
                <el-table-column label="异常异常" prop="exceptiontype"></el-table-column>
                <el-table-column label="ip" prop="ipaddr"></el-table-column>
                <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="remove(scope.row.logid)">删除</el-button>
                </template>
                </el-table-column>
            </el-table>
        </el-col>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-pagination layout="total,prev, pager, next" @current-change="search" :page-size="limit"
                :total="total"
                style="float:right;">
            </el-pagination>
        </el-col>
    </el-row>
</template>
<script>
import API from '../../api/api_log'
export default {
    name:"List",
    data(){
        return{
            limit:10,
            total: 0,
            rows: []
        }
    },
    methods:{
        show:function(param){
            this.$message.success(param)
        },
        search:function(val){
            let that=this;
            that.page=val;
            let params={
               limit: that.limit,
               curpage: val
            }
            API.list(params).then(res=>{
                if (res && res.syscode == 200) {
                    that.rows=res.obj.list;
                    that.total = res.obj.total;
                }
            })
        },
        rowclick:function(row, column, event){
            console.log(row.logid);
        },
        remove: function (logid) {
            let that = this;
            this.$confirm("确认删除该记录吗?", "提示", {type: "warning"})
            .then(() => {
                that.loading = false;
                API.remove({logid:logid}).then(res=>{
                    if (res && res.syscode == 200) {
                        that.$message.success('操作成功');
                        that.search(that.page);
                    }
               })
           })
        }
    },
    beforeMount(){
       this.show('beforeMount');
    },
    mounted(){
       this.search('0');
    },
    beforeCreate(){
        this.show('beforeCreate')
    },
    created(){
        this.show('created');
    }
}
</script>



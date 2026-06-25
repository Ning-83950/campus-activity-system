<template>
  <el-main class="bg table_wrap comtabel_t">
    <el-form label-position="right" :model="query" class="form p_4" label-width="120">
      <el-row class="rows row1">
        <el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
          <el-form-item label="活动名称">
            <el-input v-model="query.event_name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
          <el-form-item label="学生姓名">
            <el-input v-model="query.student_name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
          <el-form-item label="签到方式">
            <el-select v-model="query.checkin_type">
              <el-option value="">全部</el-option>
              <el-option value="手动签到">手动签到</el-option>
              <el-option value="二维码签到">二维码签到</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row class="rows row2">
        <el-col :xs="24" :sm="24" :lg="24" class="search_btn_wrap search_btns">
          <el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
          <el-button @click="reset()" class="search_btn_reset">重置</el-button>
        </el-col>
      </el-row>
    </el-form>

    <el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
      <el-table-column fixed type="selection" tooltip-effect="dark" width="55"></el-table-column>
      <el-table-column prop="activity_number" label="活动编号" min-width="160"></el-table-column>
      <el-table-column prop="event_name" label="活动名称" min-width="180"></el-table-column>
      <el-table-column prop="activity_type" label="活动类型" min-width="140"></el-table-column>
      <el-table-column prop="activity_time" label="活动时间" min-width="160"></el-table-column>
      <el-table-column prop="activity_location" label="活动地点" min-width="160"></el-table-column>
      <el-table-column prop="student_name" label="学生姓名" min-width="140"></el-table-column>
      <el-table-column prop="checkin_type" label="签到方式" min-width="140"></el-table-column>
      <el-table-column prop="checkin_status" label="签到状态" min-width="120"></el-table-column>
      <el-table-column prop="certificate_no" label="证明编号" min-width="220"></el-table-column>
      <el-table-column sortable prop="checkin_time" label="签到时间" min-width="180">
        <template slot-scope="scope">
          {{ $toTime(scope.row["checkin_time"], "yyyy-MM-dd hh:mm:ss") }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" min-width="120">
        <template slot-scope="scope">
          <el-button class="e-button el-button--small is-plain el-button--primary" size="small" @click="downloadCertificate(scope.row)">
            <span>证明</span>
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="mt text_center">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
        layout="total, sizes, prev, pager, next, jumper" :total="count">
      </el-pagination>
    </div>
  </el-main>
</template>

<script>
import mixin from "@/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      url_get_list: "~/api/activity_checkin/get_list?like=0",
      url_del: "~/api/activity_checkin/del?",
      field: "activity_checkin_id",
      query: {
        size: 7,
        page: 1,
        event_name: "",
        student_name: "",
        checkin_type: "",
        orderby: "checkin_time desc"
      },
      list: []
    };
  },
  methods: {
    get_list_before(param) {
      var user_group = this.user.user_group;
      if (user_group !== "管理员") {
        var sqlwhere = "(";
        if (user_group === "教师用户") {
          sqlwhere += "teacher_user = " + this.user.user_id + " or ";
        }
        if (user_group === "学生用户") {
          sqlwhere += "student_users = " + this.user.user_id + " or ";
        }
        if (sqlwhere.length > 1) {
          sqlwhere = sqlwhere.substr(0, sqlwhere.length - 4);
          sqlwhere += ")";
          param.sqlwhere = sqlwhere;
        }
      }
      return param;
    },
    downloadCertificate(row) {
      var url = "~/api/activity_checkin/certificate?activity_checkin_id=" + row.activity_checkin_id;
      window.open(this.$fullUrl(url), "_blank");
    }
  }
};
</script>

<style type="text/css">
.bg {
  background: white;
}
.form.p_4 {
  padding: 1rem;
}
.mt {
  margin-top: 1rem;
}
.text_center {
  text-align: center;
}
</style>

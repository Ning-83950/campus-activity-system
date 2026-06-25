<template>
  <div class="page_checkin_scan">
    <div class="scan_panel">
      <h2>活动签到</h2>
      <p class="activity_no">活动编号：{{ activity_number || '-' }}</p>
      <p class="status" :class="{ success: success, error: !!error }">{{ message }}</p>
      <div class="actions">
        <button v-if="!success" class="btn_item" @click="submitCheckin">重新签到</button>
        <router-link class="btn_link" to="/user_center/index">返回个人中心</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      activity_number: "",
      message: "正在签到...",
      success: false,
      error: ""
    };
  },
  mounted() {
    this.activity_number = this.$route.query.activity_number || "";
    this.submitCheckin();
  },
  methods: {
    submitCheckin() {
      if (!this.activity_number) {
        this.message = "缺少活动编号，无法签到";
        this.error = this.message;
        return;
      }
      var token = this.$store.state.user.token || $.db.get("token");
      if (!token) {
        this.message = "请先登录后再扫码签到";
        this.error = this.message;
        this.goLogin();
        return;
      }
      this.message = "正在签到...";
      this.error = "";
      this.$post("~/api/activity_checkin/scan", {
        activity_number: this.activity_number
      }, (json) => {
        if (json.result) {
          this.success = true;
          this.message = "签到成功，参与证明可在签到记录中下载。";
        } else if (json.error) {
          this.success = false;
          this.error = json.error.message;
          this.message = json.error.message;
          if (json.error.code === 10000) {
            $.db.del("token");
            this.goLogin();
          }
        }
      });
    },
    goLogin() {
      var redirect = this.$route.fullPath;
      sessionStorage.setItem("checkin_redirect", redirect);
      this.$router.replace({
        path: "/account/login",
        query: {
          redirect: redirect
        }
      });
    }
  }
};
</script>

<style scoped>
.page_checkin_scan {
  min-height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 16px;
}
.scan_panel {
  width: min(520px, 100%);
  background: #fff;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  padding: 32px;
  text-align: center;
}
.scan_panel h2 {
  font-size: 26px;
  margin-bottom: 16px;
}
.activity_no {
  color: #555;
}
.status {
  margin: 22px 0;
  font-size: 18px;
}
.status.success {
  color: #16a34a;
}
.status.error {
  color: #dc2626;
}
.actions {
  display: flex;
  justify-content: center;
  gap: 12px;
}
.btn_item,
.btn_link {
  display: inline-block;
  border: 1px solid #2563eb;
  background: #2563eb;
  color: #fff;
  padding: 8px 16px;
  border-radius: 4px;
  text-decoration: none;
}
.btn_link {
  background: #fff;
  color: #2563eb;
}
</style>

<template>
  <div class="list_menu diy_menu">
    <router-link
            to="/user_center/index"
            class="menu_item"
            :class="{ selected: this.$route.path=== '/user_center/index'}"
     >
      <span class="left_span">个人首页</span>
      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
    </router-link>
          <router-link
          to="/student_users/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/student_users/table'}"
          v-if="$check_action('/student_users/table','get')">
        <span class="left_span">学生用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/teacher_user/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/teacher_user/table'}"
          v-if="$check_action('/teacher_user/table','get')">
        <span class="left_span">教师用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/activity_classification/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/activity_classification/table'}"
          v-if="$check_action('/activity_classification/table','get')">
        <span class="left_span">活动分类</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/activity_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/activity_information/table'}"
          v-if="$check_action('/activity_information/table','get')">
        <span class="left_span">活动信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/enrollment_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/enrollment_information/table'}"
          v-if="$check_action('/enrollment_information/table','get')">
        <span class="left_span">报名信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/activity_notification/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/activity_notification/table'}"
          v-if="$check_action('/activity_notification/table','get')">
        <span class="left_span">活动通知</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/activity_costs/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/activity_costs/table'}"
          v-if="$check_action('/activity_costs/table','get')">
        <span class="left_span">活动费用</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/feedback_evaluation/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/feedback_evaluation/table'}"
          v-if="$check_action('/feedback_evaluation/table','get')">
        <span class="left_span">反馈评价</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
          <router-link
              to="/message/table"
              class="menu_item"
              :class="{ selected: this.$route.path=== '/message/table'}"
              v-if="$check_action('/message/table','get')">
        <span class="left_span">{{$page_title("/message/table") || "投诉反馈"}}</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
  <router-link
              to="/user/collect"
              class="menu_item"
              :class="{ selected: this.$route.path=== '/user/collect'}"
              v-if="$check_action('/collect/list','get')">
        <span class="left_span">收藏</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
  </router-link>
	<router-link
	            to="/comment/table"
	            class="menu_item"
	            :class="{ selected: this.$route.path=== '/comment/table'}"
	            v-if="$check_action('/comment/table','get')">
	      <span class="left_span">评论管理</span>
	      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
	</router-link>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";
export default {
  mixins: [mixin],
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  },

  components: {
  },
};
</script>

<style scoped>
.list_menu {
  flex: 0 0 25%;
  border-bottom: 1px solid #eee;
}
.menu_item {
  display: flex;
  justify-content: space-between;
  padding: 15px 20px;
  border: 1px solid #eee;
  border-bottom: none;
  background-color: #fff;
}
.menu_item.selected {
  background-color: #000;
}
.menu_item.selected span {
  color: #fff ;
}
.content {
  flex: 0 0 74%;
}
</style>

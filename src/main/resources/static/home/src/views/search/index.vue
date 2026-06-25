<template>
  <div class="page_search search_index">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/student_users/list', 'get')"
				:list="result_student_users_student_no"
				title="学生用户学生学号"
				source_table="student_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/student_users/list', 'get')"
				:list="result_student_users_student_name"
				title="学生用户学生姓名"
				source_table="student_users"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/teacher_user/list', 'get')"
				:list="result_teacher_user_teacher_no"
				title="教师用户教师工号"
				source_table="teacher_user"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/teacher_user/list', 'get')"
				:list="result_teacher_user_teachers_name"
				title="教师用户教师姓名"
				source_table="teacher_user"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/activity_classification/list', 'get')"
				:list="result_activity_classification_activity_type"
				title="活动分类活动类型"
				source_table="activity_classification"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/activity_information/list', 'get')"
				:list="result_activity_information_event_name"
				title="活动信息活动名称"
				source_table="activity_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/activity_information/list', 'get')"
				:list="result_activity_information_activity_type"
				title="活动信息活动类型"
				source_table="activity_information"
			  ></list_result_search>
																																													  <list_result_search
				v-if="$check_action('/enrollment_information/list', 'get')"
				:list="result_enrollment_information_event_name"
				title="报名信息活动名称"
				source_table="enrollment_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/enrollment_information/list', 'get')"
				:list="result_enrollment_information_activity_type"
				title="报名信息活动类型"
				source_table="enrollment_information"
			  ></list_result_search>
																																							  <list_result_search
				v-if="$check_action('/activity_notification/list', 'get')"
				:list="result_activity_notification_event_name"
				title="活动通知活动名称"
				source_table="activity_notification"
			  ></list_result_search>
																							  <list_result_search
				v-if="$check_action('/activity_notification/list', 'get')"
				:list="result_activity_notification_notification_title"
				title="活动通知通知标题"
				source_table="activity_notification"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/activity_costs/list', 'get')"
				:list="result_activity_costs_event_name"
				title="活动费用活动名称"
				source_table="activity_costs"
			  ></list_result_search>
																										  <list_result_search
				v-if="$check_action('/activity_costs/list', 'get')"
				:list="result_activity_costs_expense_item"
				title="活动费用费用项目"
				source_table="activity_costs"
			  ></list_result_search>
																					  <list_result_search
				v-if="$check_action('/feedback_evaluation/list', 'get')"
				:list="result_feedback_evaluation_event_name"
				title="反馈评价活动名称"
				source_table="feedback_evaluation"
			  ></list_result_search>
																													  <list_result_search
				v-if="$check_action('/feedback_evaluation/list', 'get')"
				:list="result_feedback_evaluation_activity_rating"
				title="反馈评价活动评分"
				source_table="feedback_evaluation"
			  ></list_result_search>
												</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_student_users_student_no":[],
								"result_student_users_student_name":[],
									"result_teacher_user_teacher_no":[],
								"result_teacher_user_teachers_name":[],
									"result_activity_classification_activity_type":[],
															"result_activity_information_event_name":[],
								"result_activity_information_activity_type":[],
																																													"result_enrollment_information_event_name":[],
								"result_enrollment_information_activity_type":[],
																																							"result_activity_notification_event_name":[],
																							"result_activity_notification_notification_title":[],
																								"result_activity_costs_event_name":[],
																										"result_activity_costs_expense_item":[],
																					"result_feedback_evaluation_event_name":[],
																													"result_feedback_evaluation_activity_rating":[],
										};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取student_no
	 */
	get_student_users_student_no(){
		let url = "~/api/student_users/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "student_no": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_users_student_no = json.result.list;
			result_student_users_student_no.map(o => o.title = o['student_no'])
	  			this.result_student_users_student_no = result_student_users_student_no
		 	}
		});
	},
						/**
	 * 获取student_name
	 */
	get_student_users_student_name(){
		let url = "~/api/student_users/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_users_student_name = json.result.list;
			result_student_users_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_users_student_name = result_student_users_student_name
		 	}
		});
	},
							/**
	 * 获取teacher_no
	 */
	get_teacher_user_teacher_no(){
		let url = "~/api/teacher_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teacher_no": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_user_teacher_no = json.result.list;
			result_teacher_user_teacher_no.map(o => o.title = o['teacher_no'])
	  			this.result_teacher_user_teacher_no = result_teacher_user_teacher_no
		 	}
		});
	},
						/**
	 * 获取teachers_name
	 */
	get_teacher_user_teachers_name(){
		let url = "~/api/teacher_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_user_teachers_name = json.result.list;
			result_teacher_user_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_teacher_user_teachers_name = result_teacher_user_teachers_name
		 	}
		});
	},
							/**
	 * 获取activity_type
	 */
	get_activity_classification_activity_type(){
		let url = "~/api/activity_classification/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "activity_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_classification_activity_type = json.result.list;
			result_activity_classification_activity_type.map(o => o.title = o['activity_type'])
	  			this.result_activity_classification_activity_type = result_activity_classification_activity_type
		 	}
		});
	},
													/**
	 * 获取event_name
	 */
	get_activity_information_event_name(){
		let url = "~/api/activity_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "event_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_information_event_name = json.result.list;
			result_activity_information_event_name.map(o => o.title = o['event_name'])
	  			this.result_activity_information_event_name = result_activity_information_event_name
		 	}
		});
	},
						/**
	 * 获取activity_type
	 */
	get_activity_information_activity_type(){
		let url = "~/api/activity_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "activity_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_information_activity_type = json.result.list;
			result_activity_information_activity_type.map(o => o.title = o['activity_type'])
	  			this.result_activity_information_activity_type = result_activity_information_activity_type
		 	}
		});
	},
																																											/**
	 * 获取event_name
	 */
	get_enrollment_information_event_name(){
		let url = "~/api/enrollment_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "event_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_enrollment_information_event_name = json.result.list;
			result_enrollment_information_event_name.map(o => o.title = o['event_name'])
	  			this.result_enrollment_information_event_name = result_enrollment_information_event_name
		 	}
		});
	},
						/**
	 * 获取activity_type
	 */
	get_enrollment_information_activity_type(){
		let url = "~/api/enrollment_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "activity_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_enrollment_information_activity_type = json.result.list;
			result_enrollment_information_activity_type.map(o => o.title = o['activity_type'])
	  			this.result_enrollment_information_activity_type = result_enrollment_information_activity_type
		 	}
		});
	},
																																					/**
	 * 获取event_name
	 */
	get_activity_notification_event_name(){
		let url = "~/api/activity_notification/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "event_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_notification_event_name = json.result.list;
			result_activity_notification_event_name.map(o => o.title = o['event_name'])
	  			this.result_activity_notification_event_name = result_activity_notification_event_name
		 	}
		});
	},
																					/**
	 * 获取notification_title
	 */
	get_activity_notification_notification_title(){
		let url = "~/api/activity_notification/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "notification_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_notification_notification_title = json.result.list;
			result_activity_notification_notification_title.map(o => o.title = o['notification_title'])
	  			this.result_activity_notification_notification_title = result_activity_notification_notification_title
		 	}
		});
	},
																						/**
	 * 获取event_name
	 */
	get_activity_costs_event_name(){
		let url = "~/api/activity_costs/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "event_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_costs_event_name = json.result.list;
			result_activity_costs_event_name.map(o => o.title = o['event_name'])
	  			this.result_activity_costs_event_name = result_activity_costs_event_name
		 	}
		});
	},
																								/**
	 * 获取expense_item
	 */
	get_activity_costs_expense_item(){
		let url = "~/api/activity_costs/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "expense_item": this.query.word }, (json) => {
		  if (json.result) {
			var result_activity_costs_expense_item = json.result.list;
			result_activity_costs_expense_item.map(o => o.title = o['expense_item'])
	  			this.result_activity_costs_expense_item = result_activity_costs_expense_item
		 	}
		});
	},
																			/**
	 * 获取event_name
	 */
	get_feedback_evaluation_event_name(){
		let url = "~/api/feedback_evaluation/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "event_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_feedback_evaluation_event_name = json.result.list;
			result_feedback_evaluation_event_name.map(o => o.title = o['event_name'])
	  			this.result_feedback_evaluation_event_name = result_feedback_evaluation_event_name
		 	}
		});
	},
																											/**
	 * 获取activity_rating
	 */
	get_feedback_evaluation_activity_rating(){
		let url = "~/api/feedback_evaluation/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "activity_rating": this.query.word }, (json) => {
		  if (json.result) {
			var result_feedback_evaluation_activity_rating = json.result.list;
			result_feedback_evaluation_activity_rating.map(o => o.title = o['activity_rating'])
	  			this.result_feedback_evaluation_activity_rating = result_feedback_evaluation_activity_rating
		 	}
		});
	},
									
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_student_users_student_no();
							this.get_student_users_student_name();
								this.get_teacher_user_teacher_no();
							this.get_teacher_user_teachers_name();
								this.get_activity_classification_activity_type();
														this.get_activity_information_event_name();
							this.get_activity_information_activity_type();
																																												this.get_enrollment_information_event_name();
							this.get_enrollment_information_activity_type();
																																						this.get_activity_notification_event_name();
																						this.get_activity_notification_notification_title();
																							this.get_activity_costs_event_name();
																									this.get_activity_costs_expense_item();
																				this.get_feedback_evaluation_event_name();
																												this.get_feedback_evaluation_activity_rating();
									  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_student_users_student_no();
						  this.get_student_users_student_name();
							  this.get_teacher_user_teacher_no();
						  this.get_teacher_user_teachers_name();
							  this.get_activity_classification_activity_type();
													  this.get_activity_information_event_name();
						  this.get_activity_information_activity_type();
																																											  this.get_enrollment_information_event_name();
						  this.get_enrollment_information_activity_type();
																																					  this.get_activity_notification_event_name();
																					  this.get_activity_notification_notification_title();
																						  this.get_activity_costs_event_name();
																								  this.get_activity_costs_expense_item();
																			  this.get_feedback_evaluation_event_name();
																											  this.get_feedback_evaluation_activity_rating();
										},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>

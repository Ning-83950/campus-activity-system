<template>
	<div class="page_user my_home" id="user_address">
		<div class="warp">
			<div class="container">
				<div class="row justify-content-between">
					<div class="col-12 col-md-3">
						<div class="card_menu">
							<!-- 左侧边栏 -->
							<list_admin_menu_user></list_admin_menu_user>
						</div>
					</div>
					<div class="col-12 col-md-9">
						<div class="card_addres pl-2">
							<div class="warp">
								<div class="container-fluid">
									<el-row>
										<div>欢迎来到个人中心</div>
									</el-row>
									<el-row>
															<el-col v-if="$check_figure('/enrollment_information/table')" :span="8">
											<div class="card chart">
														<newBarChart v-if="bar_obj_enrollment_information.values.length > 0" id="bar_obj_enrollment_information" :vm="bar_obj_enrollment_information" :title="'报名信息统计'">
												</newBarChart>
												<div v-if="!bar_obj_enrollment_information.values.length">报名信息没有符合条件的数据</div>
													</div>
										</el-col>
														<el-col v-if="$check_figure('/feedback_evaluation/table')" :span="8">
											<div class="card chart">
														<newBarChart v-if="bar_obj_feedback_evaluation.values.length > 0" id="bar_obj_feedback_evaluation" :vm="bar_obj_feedback_evaluation" :title="'反馈评价统计'">
												</newBarChart>
												<div v-if="!bar_obj_feedback_evaluation.values.length">反馈评价没有符合条件的数据</div>
													</div>
										</el-col>
										</el-row>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_admin_menu_user from "@/components/diy/list_admin_menu_user.vue";
	import newBarChart from "@/components/charts/new_bar_chart";
	export default {
		data() {
			return {
													bar_obj_enrollment_information: {
					names:[],
					xAxis: [],
					values:[]
				},
										bar_obj_feedback_evaluation: {
					names:[],
					xAxis: [],
					values:[]
				},
				};
		},
		mounted() {
		},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
												// 获取报名信息统计图
			async get_list_enrollment_information() {
				let name_list = [];
				let query_str = "";
																									let group_by_value = "event_name";
								let flag = false;
												let date_flag = "其他"
																																																																						name_list.push("报名人数");
				query_str = query_str+"number_of_enrolment"+","
																									this.bar_obj_enrollment_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
														if (user_group=="教师用户"){
						sqlwhere+= "teacher_user = " + user_id + " or ";
					}
																																																																if (user_group=="学生用户"){
						sqlwhere+= "student_users = " + user_id + " or ";
					}
																																																											if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/enrollment_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_enrollment_information.xAxis = xAxis;
								this.bar_obj_enrollment_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_enrollment_information.xAxis,true);
							}
						});
			},
									// 获取反馈评价统计图
			async get_list_feedback_evaluation() {
				let name_list = [];
				let query_str = "";
																																																																	let group_by_value = "student_name";
								let flag = false;
												let date_flag = "其他"
																																						name_list.push("活动评分");
				query_str = query_str+"activity_rating"+","
																									this.bar_obj_feedback_evaluation.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
														if (user_group=="教师用户"){
						sqlwhere+= "teacher_user = " + user_id + " or ";
					}
																																																																if (user_group=="学生用户"){
						sqlwhere+= "student_users = " + user_id + " or ";
					}
																																																																				if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/feedback_evaluation/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_feedback_evaluation.xAxis = xAxis;
								this.bar_obj_feedback_evaluation.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_feedback_evaluation.xAxis,true);
							}
						});
			},
			},
		created() {
			setTimeout(() => {
								// 执行报名信息数据获取
			this.get_list_enrollment_information();
							// 执行反馈评价数据获取
			this.get_list_feedback_evaluation();
				}, 1000);
		},
		components: {
			newBarChart,
			list_admin_menu_user
		},
	};
</script>

<style scoped>
	.container {
		min-height: 800px;
	}
</style>

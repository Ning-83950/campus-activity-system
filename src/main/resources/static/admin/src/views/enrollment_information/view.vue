<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="$check_field('get','teacher_user') || $check_field('add','teacher_user') || $check_field('set','teacher_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="教师用户" prop="teacher_user">
																<el-select v-if="(form['enrollment_information_id'] && $check_field('set','teacher_user')) || (!form['enrollment_information_id'] && $check_field('add','teacher_user'))" id="teacher_user" v-model="form['teacher_user']" :disabled="disabledObj['teacher_user_isDisabled']">
							<el-option v-for="o in list_user_teacher_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','teacher_user')" id="teacher_user" v-model="form['teacher_user']" :disabled="true">
							<el-option v-for="o in list_user_teacher_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_number') || $check_field('add','activity_number') || $check_field('set','activity_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动编号" prop="activity_number">
															<el-input id="activity_number" v-model="form['activity_number']" placeholder="请输入活动编号"
							  v-if="(form['enrollment_information_id'] && $check_field('set','activity_number')) || (!form['enrollment_information_id'] && $check_field('add','activity_number'))" :disabled="disabledObj['activity_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_number')">{{form['activity_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','event_name') || $check_field('add','event_name') || $check_field('set','event_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动名称" prop="event_name">
															<el-input id="event_name" v-model="form['event_name']" placeholder="请输入活动名称"
							  v-if="(form['enrollment_information_id'] && $check_field('set','event_name')) || (!form['enrollment_information_id'] && $check_field('add','event_name'))" :disabled="disabledObj['event_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','event_name')">{{form['event_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_type') || $check_field('add','activity_type') || $check_field('set','activity_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动类型" prop="activity_type">
															<el-input id="activity_type" v-model="form['activity_type']" placeholder="请输入活动类型"
							  v-if="(form['enrollment_information_id'] && $check_field('set','activity_type')) || (!form['enrollment_information_id'] && $check_field('add','activity_type'))" :disabled="disabledObj['activity_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_type')">{{form['activity_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_time') || $check_field('add','activity_time') || $check_field('set','activity_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动时间" prop="activity_time">
															<el-input id="activity_time" v-model="form['activity_time']" placeholder="请输入活动时间"
							  v-if="(form['enrollment_information_id'] && $check_field('set','activity_time')) || (!form['enrollment_information_id'] && $check_field('add','activity_time'))" :disabled="disabledObj['activity_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_time')">{{form['activity_time']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_location') || $check_field('add','activity_location') || $check_field('set','activity_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动地点" prop="activity_location">
															<el-input id="activity_location" v-model="form['activity_location']" placeholder="请输入活动地点"
							  v-if="(form['enrollment_information_id'] && $check_field('set','activity_location')) || (!form['enrollment_information_id'] && $check_field('add','activity_location'))" :disabled="disabledObj['activity_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_location')">{{form['activity_location']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','student_users') || $check_field('add','student_users') || $check_field('set','student_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="学生用户" prop="student_users">
																					<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_users(form['student_users']) }}
							<el-select v-if="(form['enrollment_information_id'] && $check_field('set','student_users')) || (!form['enrollment_information_id'] && $check_field('add','student_users'))" id="student_users" v-model="form['student_users']" :disabled="disabledObj['student_users_isDisabled']">
								<el-option v-for="o in list_user_student_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_users')" id="student_users" v-model="form['student_users']" :disabled="true">
								<el-option v-for="o in list_user_student_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_users" v-model="form['student_users']" :disabled="disabledObj['student_users_isDisabled']">
							<el-option v-for="o in list_user_student_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="学生姓名" prop="student_name">
															<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="(form['enrollment_information_id'] && $check_field('set','student_name')) || (!form['enrollment_information_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','registration_date') || $check_field('add','registration_date') || $check_field('set','registration_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="报名日期" prop="registration_date">
											<el-date-picker :disabled="disabledObj['registration_date_isDisabled']" v-if="(form['enrollment_information_id'] && $check_field('set','registration_date')) || (!form['enrollment_information_id'] && $check_field('add','registration_date'))" id="registration_date"
						v-model="form['registration_date']" type="date" placeholder="选择日期" :picker-options="registration_date_disableOptions" value-format="yyyy-MM-dd">
					</el-date-picker>
					<div v-else-if="$check_field('get','registration_date')">{{form['registration_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','number_of_enrolment') || $check_field('add','number_of_enrolment') || $check_field('set','number_of_enrolment')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item prop="number_of_enrolment">
					<div slot="label">
						<i style="color: red;">*</i>
						报名人数
					</div>
											<el-select id="number_of_enrolment" v-model="form['number_of_enrolment']"
						v-if="(form['enrollment_information_id'] && $check_field('set','number_of_enrolment')) || (!form['enrollment_information_id'] && $check_field('add','number_of_enrolment'))">
						<el-option v-for="o in list_number_of_enrolment" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','number_of_enrolment')">{{form['number_of_enrolment']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','application_for_registration') || $check_field('add','application_for_registration') || $check_field('set','application_for_registration')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="报名申请" prop="application_for_registration">
											<el-input type="textarea" id="application_for_registration" v-model="form['application_for_registration']" placeholder="请输入报名申请"
						v-if="(form['enrollment_information_id'] && $check_field('set','application_for_registration')) || (!form['enrollment_information_id'] && $check_field('add','application_for_registration'))" :disabled="disabledObj['application_for_registration_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','application_for_registration')">{{form['application_for_registration']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_progress') || $check_field('add','activity_progress') || $check_field('set','activity_progress')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="活动进度" prop="activity_progress">
															<el-input id="activity_progress" v-model="form['activity_progress']" placeholder="请输入活动进度"
							  v-if="(form['enrollment_information_id'] && $check_field('set','activity_progress')) || (!form['enrollment_information_id'] && $check_field('add','activity_progress'))" :disabled="disabledObj['activity_progress_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_progress')">{{form['activity_progress']}}</div>
											</el-form-item>
			</el-col>
								<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="(form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())" :disabled="true">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
					<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="(form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())" :disabled="true"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
	
	
	
		
		
						<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 0 > 0">
			  <el-form-item label="通知限次">
				<el-input id="limit_times" v-model="form['activity_notification_limit_times']" placeholder="通知限制次数，0为不限"
						  v-if="$check_option('/enrollment_information/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/enrollment_information/view','get')" v-html="form['activity_notification_limit_times']"></div>
			  </el-form-item>
			</el-col>
					<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 0 > 0">
			  <el-form-item label="费用限次">
				<el-input id="limit_times" v-model="form['activity_costs_limit_times']" placeholder="费用限制次数，0为不限"
						  v-if="$check_option('/enrollment_information/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/enrollment_information/view','get')" v-html="form['activity_costs_limit_times']"></div>
			  </el-form-item>
			</el-col>
					<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 0 > 0">
			  <el-form-item label="评价限次">
				<el-input id="limit_times" v-model="form['feedback_evaluation_limit_times']" placeholder="评价限制次数，0为不限"
						  v-if="$check_option('/enrollment_information/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/enrollment_information/view','get')" v-html="form['feedback_evaluation_limit_times']"></div>
			  </el-form-item>
			</el-col>
			
	
	
		
		
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/enrollment_information/view','set') || $check_action('/enrollment_information/view','add') || $check_option('/enrollment_information/table','examine')">
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
				<el-form-item v-else>
					<el-button @click="cancel()">返回</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		data() {
			return {
				field: "enrollment_information_id",
				url_add: "~/api/enrollment_information/add?",
				url_set: "~/api/enrollment_information/set?",
				url_get_obj: "~/api/enrollment_information/get_obj?",
				url_upload: "~/api/enrollment_information/upload?",

				query: {
					"enrollment_information_id": 0,
				},

				form: {
								"teacher_user": 0, // 教师用户
										"activity_number":  '', // 活动编号
										"event_name":  '', // 活动名称
										"activity_type":  '', // 活动类型
										"activity_time":  '', // 活动时间
										"activity_location":  '', // 活动地点
										"student_users": 0, // 学生用户
										"student_name":  '', // 学生姓名
										"registration_date":  '', // 报名日期
										"number_of_enrolment": null, // 报名人数
										"application_for_registration":  '', // 报名申请
										"activity_progress":  '', // 活动进度
									"examine_state": "未审核",
							"examine_reply": "",
							"enrollment_information_id": 0, // ID
													"activity_notification_limit_times": 0, // 通知限制次数
							"activity_costs_limit_times": 0, // 费用限制次数
							"feedback_evaluation_limit_times": 0, // 评价限制次数
												},
				disabledObj:{
								"teacher_user_isDisabled": false,
										"activity_number_isDisabled": false,
										"event_name_isDisabled": false,
										"activity_type_isDisabled": false,
										"activity_time_isDisabled": false,
										"activity_location_isDisabled": false,
										"student_users_isDisabled": false,
										"student_name_isDisabled": false,
										"registration_date_isDisabled": false,
										"number_of_enrolment_isDisabled": false,
										"application_for_registration_isDisabled": false,
										"activity_progress_isDisabled": false,
										},

	
					// 用户列表
				list_user_teacher_user: [],
					
			
			
			
			
			
					// 用户列表
				list_user_student_users: [],
						// 用户组
				group_user_student_users: "",
					
			
					registration_date_disableOptions: {
				disabledDate(time) {
					return time.getTime() < Date.now() - 8.64e7;
				},
			},
								// 报名人数选项列表
				list_number_of_enrolment: ['1'],
	
			
			
		
			}
		},
		methods: {

	
	
				/**
			 * 获取教师用户用户列表
			 */
			async get_list_user_teacher_user() {
                var json = await this.$get("~/api/user/get_list?user_group=教师用户");
                if(json.result && json.result.list){
                    this.list_user_teacher_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_teacher_user(id){
				var obj = this.list_user_teacher_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
			
	
				/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_users() {
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取学生用户用户组
			 */
			async get_group_user_student_users() {
							this.form["student_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_student_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_users.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["student_users"] = id
									_this.disabledObj['student_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "student_users") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_student_users(id){
				var obj = this.list_user_student_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
										// 获取缓存数据附加
				form = $.db.get("form");
						if(form != null){
					if("examine_state" in form){
						delete form.examine_state
					}
								if("examine_reply" in form){
						delete form.examine_reply
					}
							}
									$.push(this.form ,form);
																			
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
								this.form[key] = form[dbKey]
							}
							if(dbKey === "source_table"){
								this.form['source_table'] = form[dbKey];
							}
							if(dbKey === "source_id"){
								this.form['source_id'] = form[dbKey];
							}
							if(dbKey === "source_user_id"){
								this.form['source_user_id'] = form[dbKey];
							}
						})
					})
				}
																		        if (this.form["registration_date"] && this.form["registration_date"].indexOf("-")===-1){
          this.form["registration_date"] = this.$toTime(parseInt(this.form["registration_date"]),"yyyy-MM-dd")
        }
											$.db.del("form");

				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
																																														if(this.form["registration_date"]=="0000-00-00"){
				  this.form["registration_date"] = null;
				}
				if(parseInt(this.form["registration_date"]) > 9999){
					this.form["registration_date"] = this.$toTime(parseInt(this.form["registration_date"]),"yyyy-MM-dd")
				}
																			

			},

																																																																																																async submit(param, func){
				if (!param) {
					param = this.form;
				}
						
				var pm = this.events("submit_before", Object.assign({}, param)) || param;
				var msg = await this.events("submit_check", pm);
				var ret;
				if (msg) {
					this.$toast(msg, 'danger');
				} else {
																																																																																												ret = this.events("submit_main", pm, func);
				}
				return ret;
			},
			
			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
						submit_check(param) {
					
																																																																																															if (!param.registration_date){
					return "报名日期不能为空";
				}
																	if (!param.number_of_enrolment){
					return "报名人数不能为空";
				}
																																						return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/enrollment_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/enrollment_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/enrollment_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/enrollment_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/enrollment_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
					this.get_list_user_teacher_user();
																		this.get_list_user_student_users();
					this.get_group_user_student_users();
															},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
	
	.img_multiple{
		overflow: hidden;
	}
	.img_multiple .img_block{
		float: left;
		margin-right: 5px;
		margin-bottom: 5px;
		position: relative;
	}
	.img_multiple .img_block img{
		height: 100px;
		width: auto;
	}
	.img_multiple .img_del{
		position: absolute;
		top: 5px;
		right: 5px;
		width: 20px;
		height: 20px;
		background: #0000008a;
		color: #fff;
		line-height: 20px;
		text-align: center;
		border-radius: 100%;
		cursor: pointer;
	}




	
</style>

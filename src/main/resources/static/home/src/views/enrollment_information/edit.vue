<template>
	<div class="diy_edit page_enrollment_information" id="enrollment_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','teacher_user') || $check_field('add','teacher_user') || $check_field('get','teacher_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								教师用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_teacher_user" :disabled="disabledObj['teacher_user_isDisabled']" v-model="form['teacher_user']" v-if="(form['teacher_user'] && $check_field('set','teacher_user')) || (!form['teacher_user'] && $check_field('add','teacher_user'))" >
								<option v-for="o in list_user_teacher_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','teacher_user')">{{ get_user_info("teacher_user", form['teacher_user']) }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','activity_number') || $check_field('add','activity_number') || $check_field('get','activity_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动编号:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_activity_number" v-model="form['activity_number']" placeholder="请输入活动编号" v-if="(form['activity_number'] && $check_field('set','activity_number')) || (!form['activity_number'] && $check_field('add','activity_number'))"  :disabled="disabledObj['activity_number_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_number')">{{ form['activity_number'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','event_name') || $check_field('add','event_name') || $check_field('get','event_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_event_name" v-model="form['event_name']" placeholder="请输入活动名称" v-if="(form['event_name'] && $check_field('set','event_name')) || (!form['event_name'] && $check_field('add','event_name'))"  :disabled="disabledObj['event_name_isDisabled']"/>
							<span v-else-if="$check_field('get','event_name')">{{ form['event_name'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','activity_type') || $check_field('add','activity_type') || $check_field('get','activity_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_activity_type" v-model="form['activity_type']" placeholder="请输入活动类型" v-if="(form['activity_type'] && $check_field('set','activity_type')) || (!form['activity_type'] && $check_field('add','activity_type'))"  :disabled="disabledObj['activity_type_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_type')">{{ form['activity_type'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','activity_time') || $check_field('add','activity_time') || $check_field('get','activity_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动时间:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_activity_time" v-model="form['activity_time']" placeholder="请输入活动时间" v-if="(form['activity_time'] && $check_field('set','activity_time')) || (!form['activity_time'] && $check_field('add','activity_time'))"  :disabled="disabledObj['activity_time_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_time')">{{ form['activity_time'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','activity_location') || $check_field('add','activity_location') || $check_field('get','activity_location')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动地点:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_activity_location" v-model="form['activity_location']" placeholder="请输入活动地点" v-if="(form['activity_location'] && $check_field('set','activity_location')) || (!form['activity_location'] && $check_field('add','activity_location'))"  :disabled="disabledObj['activity_location_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_location')">{{ form['activity_location'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','student_users') || $check_field('add','student_users') || $check_field('get','student_users')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								学生用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_users" :disabled="disabledObj['student_users_isDisabled']" v-model="form['student_users']" v-if="(form['student_users'] && $check_field('set','student_users')) || (!form['student_users'] && $check_field('add','student_users'))" >
								<option v-for="o in list_user_student_users" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student_users')">{{ get_user_info("student_users", form['student_users']) }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','student_name') || $check_field('add','student_name') || $check_field('get','student_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								学生姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_student_name" v-model="form['student_name']" placeholder="请输入学生姓名" v-if="(form['student_name'] && $check_field('set','student_name')) || (!form['student_name'] && $check_field('add','student_name'))"  :disabled="disabledObj['student_name_isDisabled']"/>
							<span v-else-if="$check_field('get','student_name')">{{ form['student_name'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','registration_date') || $check_field('add','registration_date') || $check_field('get','registration_date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								报名日期:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<el-date-picker :disabled="disabledObj['registration_date_isDisabled']" v-if="(form['registration_date'] && $check_field('set','registration_date')) || (!form['registration_date'] && $check_field('add','registration_date'))" id="form_registration_date"
								v-model="form['registration_date']" type="date" placeholder="选择日期" :picker-options="registration_date_disableOptions" @change="registration_dateDateChange">
							</el-date-picker>
							<span v-else-if="$check_field('get','registration_date')">{{ form['registration_date'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','number_of_enrolment') || $check_field('add','number_of_enrolment') || $check_field('get','number_of_enrolment')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								报名人数:
							</span>
						</div>
								<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_number_of_enrolment" v-model="form['number_of_enrolment']" v-if="(form['number_of_enrolment'] && $check_field('set','number_of_enrolment')) || (!form['number_of_enrolment'] && $check_field('add','number_of_enrolment'))" >
								<option v-for="o in list_number_of_enrolment" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','number_of_enrolment')">{{ form['number_of_enrolment'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','application_for_registration') || $check_field('add','application_for_registration') || $check_field('get','application_for_registration')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								报名申请:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_application_for_registration" v-model="form['application_for_registration']" v-if="(form['application_for_registration'] && $check_field('set','application_for_registration')) || (!form['application_for_registration'] && $check_field('add','application_for_registration'))" :disabled="disabledObj['application_for_registration_isDisabled']" />
							<span v-else-if="$check_field('get','application_for_registration')">{{ form['application_for_registration'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','activity_progress') || $check_field('add','activity_progress') || $check_field('get','activity_progress')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								活动进度:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_activity_progress" v-model="form['activity_progress']" placeholder="请输入活动进度" v-if="(form['activity_progress'] && $check_field('set','activity_progress')) || (!form['activity_progress'] && $check_field('add','activity_progress'))"  :disabled="disabledObj['activity_progress_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_progress')">{{ form['activity_progress'] }}</span>
						</div>
										</div>
	


					<div v-if="$check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/enrollment_information/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="$check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/enrollment_information/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>


				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/enrollment_information/get_obj?",
				url_add: "~/api/enrollment_information/add?",
				url_set: "~/api/enrollment_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"teacher_user": 0,
							"activity_number": "",
							"event_name": "",
							"activity_type": "",
							"activity_time": "",
							"activity_location": "",
							"student_users": 0,
							"student_name": "",
							"registration_date": "",
							"number_of_enrolment": "",
							"application_for_registration": "",
							"activity_progress": "",
						"enrollment_information_id": 0,
				},

				obj: {
						"teacher_user": 0, // 教师用户
							"activity_number":  '', // 活动编号
							"event_name":  '', // 活动名称
							"activity_type":  '', // 活动类型
							"activity_time":  '', // 活动时间
							"activity_location":  '', // 活动地点
							"student_users": 0, // 学生用户
							"student_name":  '', // 学生姓名
							"registration_date": new Date().getTime(),
							"number_of_enrolment": null, // 报名人数
							"application_for_registration":  '', // 报名申请
							"activity_progress":  '', // 活动进度
						"examine_state": "未审核",
					"examine_reply": "",
					"enrollment_information_id": 0,
				},

				// 表单字段
				form: {
						"teacher_user": 0, // 教师用户
							"activity_number":  '', // 活动编号
							"event_name":  '', // 活动名称
							"activity_type":  '', // 活动类型
							"activity_time":  '', // 活动时间
							"activity_location":  '', // 活动地点
							"student_users": 0, // 学生用户
							"student_name":  '', // 学生姓名
							"registration_date": new Date().getTime(),
							"number_of_enrolment":  '', // 报名人数
							"application_for_registration":  '', // 报名申请
							"activity_progress":  '', // 活动进度
						"examine_state": "未审核",
					"examine_reply": "",
					"enrollment_information_id": 0,
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
												registration_date_disableOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					},
				},
								// 报名人数选项列表
				list_number_of_enrolment: ['1'],
									
				// ID字段
				field: "enrollment_information_id",

			}
		},
		methods: {
																																																																													 registration_dateDateChange(value) {
			      if (value) {
			        // 转换为只有日期的值
			        this.form.registration_date = this.formatDateValue(value);
			      }
			    },
			    
																															formatDateValue(value) {
		  const date = new Date(value);
		  return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
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
					async get_user_session_student_users(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student_users"] = user_id
								_this.disabledObj['student_users' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "student_users") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
				
				
		  		get_user_info(name,id){
				var obj = null;
  				  if (name == 'teacher_user'){
					  obj = this.list_user_teacher_user.getObj({"user_id":id});
				  }
              				  if (name == 'student_users'){
					  obj = this.list_user_student_users.getObj({"user_id":id});
				  }
            				var ret = "";
				if(obj){
				  ret = obj.nickname+"-"+obj.username;
				}
				return ret;
			},
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/enrollment_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},
			
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file_multiple(files, str) {
				let _this = this;
				var form = new FormData();
				for (var i = 0; i < files.length; i++) {
					form.set("file", files[i]);
					_this.$post("~/api/enrollment_information/upload?", form, (res) => {
						if (res.result) {
							if (_this.form[str].length > 0) {
								_this.form[str].push(res.result.url);
							} else {
								_this.form[str] = [res.result.url];
							}
						} else if (res.error) {
							_this.$toast(res.error.message);
						}
					});
				}
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
			  if(arr[i] === "source_table"){
			  	this.form['source_table'] = form[arr[i]]
			  }
			  if(arr[i] === "source_id"){
			  	this.form['source_id'] = form[arr[i]]
			  }
			  if(arr[i] === "source_user_id"){
			  	this.form['source_user_id'] = form[arr[i]]
			  }
            }
          }
        }
																										        if (this.form["registration_date"] && JSON.stringify(this.form["registration_date"]).indexOf("-")===-1) {
          this.form["registration_date"] = this.$toTime(parseInt(this.form["registration_date"]), "yyyy-MM-dd")
        }
											
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
						this.get_list_user_teacher_user();
																					this.get_user_session_student_users();
					this.get_list_user_student_users();
																		},
	}
</script>

<style>
	.diy_compute{
		line-height: 40px;
	}
	.diy_field.diy_img_multiple{
		margin: 0;
	}
	.diy_field.diy_img_multiple div{
		float: left;
		position: relative;
		margin: 0 10px 10px 0;
	}
	.diy_field.diy_img_multiple img{
		height: 100px;
		width: auto;
	}
	.diy_field.diy_img_multiple span{
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

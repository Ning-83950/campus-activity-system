<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','teacher_user') || $check_field('add','teacher_user') || $check_field('set','teacher_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="教师用户" prop="teacher_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_teacher_user(form['teacher_user']) }}
							<el-select v-if="(form['activity_information_id'] && $check_field('set','teacher_user')) || (!form['activity_information_id'] && $check_field('add','teacher_user'))" id="teacher_user" v-model="form['teacher_user']" :disabled="disabledObj['teacher_user_isDisabled']">
								<el-option v-for="o in list_user_teacher_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','teacher_user')" id="teacher_user" v-model="form['teacher_user']" :disabled="true">
								<el-option v-for="o in list_user_teacher_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="teacher_user" v-model="form['teacher_user']" :disabled="disabledObj['teacher_user_isDisabled']">
							<el-option v-for="o in list_user_teacher_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_number') || $check_field('add','activity_number') || $check_field('set','activity_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动编号" prop="activity_number">
												<el-input id="activity_number" v-model="form['activity_number']" placeholder="请输入活动编号"
							  v-if="(form['activity_information_id'] && $check_field('set','activity_number')) || (!form['activity_information_id'] && $check_field('add','activity_number'))" :disabled="disabledObj['activity_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_number')">{{form['activity_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','event_name') || $check_field('add','event_name') || $check_field('set','event_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动名称" prop="event_name">
												<el-input id="event_name" v-model="form['event_name']" placeholder="请输入活动名称"
							  v-if="(form['activity_information_id'] && $check_field('set','event_name')) || (!form['activity_information_id'] && $check_field('add','event_name'))" :disabled="disabledObj['event_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','event_name')">{{form['event_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_type') || $check_field('add','activity_type') || $check_field('set','activity_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动类型" prop="activity_type">
								<el-select id="activity_type" v-model="form['activity_type']"						v-if="(form['activity_information_id'] && $check_field('set','activity_type')) || (!form['activity_information_id'] && $check_field('add','activity_type'))">
						<el-option v-for="o in list_activity_type" :key="o['activity_type']" :label="o['activity_type']"
							:value="o['activity_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','activity_type')">{{form['activity_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_time') || $check_field('add','activity_time') || $check_field('set','activity_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动时间" prop="activity_time">
												<el-input id="activity_time" v-model="form['activity_time']" placeholder="请输入活动时间"
							  v-if="(form['activity_information_id'] && $check_field('set','activity_time')) || (!form['activity_information_id'] && $check_field('add','activity_time'))" :disabled="disabledObj['activity_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_time')">{{form['activity_time']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_location') || $check_field('add','activity_location') || $check_field('set','activity_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动地点" prop="activity_location">
												<el-input id="activity_location" v-model="form['activity_location']" placeholder="请输入活动地点"
							  v-if="(form['activity_information_id'] && $check_field('set','activity_location')) || (!form['activity_information_id'] && $check_field('add','activity_location'))" :disabled="disabledObj['activity_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_location')">{{form['activity_location']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_cover') || $check_field('add','activity_cover') || $check_field('set','activity_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动封面" prop="activity_cover">
								<el-upload :disabled="disabledObj['activity_cover_isDisabled']" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_activity_cover"
						:show-file-list="false" v-if="(form['activity_information_id'] && $check_field('set','activity_cover')) || (!form['activity_information_id'] && $check_field('add','activity_cover'))">
						<img id="activity_cover" v-if="form['activity_cover']" :src="$fullUrl(form['activity_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','activity_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['activity_cover'])" :preview-src-list="[$fullUrl(form['activity_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_objectives') || $check_field('add','activity_objectives') || $check_field('set','activity_objectives')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动目标" prop="activity_objectives">
												<el-input id="activity_objectives" v-model="form['activity_objectives']" placeholder="请输入活动目标"
							  v-if="(form['activity_information_id'] && $check_field('set','activity_objectives')) || (!form['activity_information_id'] && $check_field('add','activity_objectives'))" :disabled="disabledObj['activity_objectives_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_objectives')">{{form['activity_objectives']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','limit_the_number_of_people') || $check_field('add','limit_the_number_of_people') || $check_field('set','limit_the_number_of_people')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="限制人数" prop="limit_the_number_of_people">
								<el-input-number id="limit_the_number_of_people" v-model.number="form['limit_the_number_of_people']"
						v-if="(form['activity_information_id'] && $check_field('set','limit_the_number_of_people')) || (!form['activity_information_id'] && $check_field('add','limit_the_number_of_people'))" :disabled="disabledObj['limit_the_number_of_people_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','limit_the_number_of_people')">{{form['limit_the_number_of_people']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','whether_to_charge') || $check_field('add','whether_to_charge') || $check_field('set','whether_to_charge')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="是否收费" prop="whether_to_charge">
								<el-select id="whether_to_charge" v-model="form['whether_to_charge']"
						v-if="(form['activity_information_id'] && $check_field('set','whether_to_charge')) || (!form['activity_information_id'] && $check_field('add','whether_to_charge'))">
						<el-option v-for="o in list_whether_to_charge" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','whether_to_charge')">{{form['whether_to_charge']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','deadline') || $check_field('add','deadline') || $check_field('set','deadline')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="截止时间" prop="deadline">
								<el-date-picker :disabled="disabledObj['deadline_isDisabled']" v-if="(form['activity_information_id'] && $check_field('set','deadline')) || (!form['activity_information_id'] && $check_field('add','deadline'))" id="deadline"
						v-model="form['deadline']" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
					</el-date-picker>
					<div v-else-if="$check_field('get','deadline')">{{form['deadline']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','conditions_for_participation') || $check_field('add','conditions_for_participation') || $check_field('set','conditions_for_participation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参与条件" prop="conditions_for_participation">
								<el-input type="textarea" id="conditions_for_participation" v-model="form['conditions_for_participation']" placeholder="请输入参与条件"
						v-if="(form['activity_information_id'] && $check_field('set','conditions_for_participation')) || (!form['activity_information_id'] && $check_field('add','conditions_for_participation'))" :disabled="disabledObj['conditions_for_participation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','conditions_for_participation')">{{form['conditions_for_participation']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','activity_content') || $check_field('add','activity_content') || $check_field('set','activity_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="活动内容" prop="activity_content">
								<el-input type="textarea" id="activity_content" v-model="form['activity_content']" placeholder="请输入活动内容"
						v-if="(form['activity_information_id'] && $check_field('set','activity_content')) || (!form['activity_information_id'] && $check_field('add','activity_content'))" :disabled="disabledObj['activity_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','activity_content')">{{form['activity_content']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','event_details') || $check_field('add','event_details') || $check_field('set','event_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="活动详情" prop="event_details">
					<quill-editor v-model.number="form['event_details']"
						v-if="(form['activity_information_id'] && $check_field('set','event_details')) || (!form['activity_information_id'] && $check_field('add','event_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','event_details')" v-html="form['event_details']"></div>
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
		
	
	
		
		
						<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 1 > 0">
			  <el-form-item label="报名限次">
				<el-input id="limit_times" v-model="form['enrollment_information_limit_times']" placeholder="报名限制次数，0为不限"
						  v-if="$check_option('/activity_information/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/activity_information/view','get')" v-html="form['enrollment_information_limit_times']"></div>
			  </el-form-item>
			</el-col>
			
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/activity_information/view','set') || $check_action('/activity_information/view','add') || $check_option('/activity_information/table','examine')">
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
				field: "activity_information_id",
				url_add: "~/api/activity_information/add?",
				url_set: "~/api/activity_information/set?",
				url_get_obj: "~/api/activity_information/get_obj?",
				url_upload: "~/api/activity_information/upload?",

				query: {
					"activity_information_id": 0,
				},

				form: {
								"teacher_user": 0, // 教师用户
										"activity_number":  '', // 活动编号
										"event_name":  '', // 活动名称
										"activity_type":  '', // 活动类型
										"activity_time":  '', // 活动时间
										"activity_location":  '', // 活动地点
										"activity_cover":  '', // 活动封面
										"activity_objectives":  '', // 活动目标
										"limit_the_number_of_people":  0, // 限制人数
										"whether_to_charge":  '', // 是否收费
										"deadline":  '', // 截止时间
										"conditions_for_participation":  '', // 参与条件
										"activity_content":  '', // 活动内容
										"event_details":  '', // 活动详情
									"examine_state": "未审核",
								"activity_information_id": 0, // ID
													"enrollment_information_limit_times": 1, // 报名限制次数
										},
				disabledObj:{
								"teacher_user_isDisabled": false,
										"activity_number_isDisabled": false,
										"event_name_isDisabled": false,
										"activity_type_isDisabled": false,
										"activity_time_isDisabled": false,
										"activity_location_isDisabled": false,
										"activity_cover_isDisabled": false,
										"activity_objectives_isDisabled": false,
					          			"limit_the_number_of_people_isDisabled": false,
										"whether_to_charge_isDisabled": false,
										"deadline_isDisabled": false,
										"conditions_for_participation_isDisabled": false,
										"activity_content_isDisabled": false,
										"event_details_isDisabled": false,
										},

	
					// 用户列表
				list_user_teacher_user: [],
						// 用户组
				group_user_teacher_user: "",
						
				
										// 活动类型选项列表
				list_activity_type: [""],
	
				
				
				
				
				
										// 是否收费选项列表
				list_whether_to_charge: ['是','否'],
	
				
				
				
				
			
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
					/**
			 * 获取教师用户用户组
			 */
			async get_group_user_teacher_user() {
							this.form["teacher_user"] = this.$store.state.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=教师用户");
				if(json.result && json.result.obj){
					this.group_user_teacher_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_teacher_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_teacher_user.source_table+"/get_obj?"
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
												_this.form["teacher_user"] = id
									_this.disabledObj['teacher_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "teacher_user") {
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
			 * 获取活动类型列表
			 */
			async get_list_activity_type() {
				var json = await this.$get("~/api/activity_classification/get_list?");
				if(json.result && json.result.list){
					this.list_activity_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
					
			
	
			
	
						/**
			 * 上传活动封面
			 * @param {Object} param 图片参数
			 */
			upload_activity_cover(param){
									this.uploadFile(param.file, "activity_cover");
								},
	
	
			
	
			
	
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
																						        if (this.form["deadline"] && this.form["deadline"].indexOf("-")===-1){
          this.form["deadline"] = this.$toTime(parseInt(this.form["deadline"]),"yyyy-MM-dd")
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

																																																								if(json.result.obj["deadline"]=="0000-00-00"){
				  json.result.obj["deadline"] = null;
				}
				if(parseInt(json.result.obj["deadline"]) > 9999){
					json.result.obj["deadline"] = this.$toTime(parseInt(json.result.obj["deadline"]),"yyyy-MM-dd")
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
																																																																																																																									if (!param.deadline){
					return "截止时间不能为空";
				}
																																																	return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/activity_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/activity_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/activity_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/activity_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/activity_information/view','get');
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
					this.get_group_user_teacher_user();
											this.get_list_activity_type();
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

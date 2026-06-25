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
					<el-form-item label="费用项目">
									<el-input v-model="query.expense_item"></el-input>
								</el-form-item>
				</el-col>
													<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="支付状态">
						<el-select v-model="query.pay_state">
							<el-option value="">全部</el-option>
							<el-option value="未支付">未支付</el-option>
							<el-option value="已支付">已支付</el-option>
						</el-select>
					</el-form-item>
				</el-col>

	</el-row>
	<el-row class="rows row2">

		<el-col :xs="24" :sm="24" :lg="24" class="search_btn_wrap search_btns">

				<el-col :xs="24" :sm="10" :lg="8" class="search_btn_1 search_btn_wrap_1 btns">

										<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
																		

						<el-button v-if="$check_action('/activity_costs/table','del') || $check_action('/activity_costs/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
								
				</el-col>
		</el-col>
	</el-row >

		</el-form>
				<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
					<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
				<el-table-column prop="teacher_user" @sort-change="$sortChange" label="教师用户" 				v-if="$check_field('get','teacher_user')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_teacher_user(scope.row['teacher_user']) }}
				</template>
					</el-table-column>
					<el-table-column prop="activity_number" @sort-change="$sortChange" label="活动编号" 				v-if="$check_field('get','activity_number')" min-width="200">
					</el-table-column>
					<el-table-column prop="event_name" @sort-change="$sortChange" label="活动名称" 				v-if="$check_field('get','event_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="activity_type" @sort-change="$sortChange" label="活动类型" 				v-if="$check_field('get','activity_type')" min-width="200">
					</el-table-column>
					<el-table-column prop="activity_time" @sort-change="$sortChange" label="活动时间" 				v-if="$check_field('get','activity_time')" min-width="200">
					</el-table-column>
					<el-table-column prop="activity_location" @sort-change="$sortChange" label="活动地点" 				v-if="$check_field('get','activity_location')" min-width="200">
					</el-table-column>
					<el-table-column prop="student_users" @sort-change="$sortChange" label="学生用户" 				v-if="$check_field('get','student_users')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_student_users(scope.row['student_users']) }}
				</template>
					</el-table-column>
					<el-table-column prop="student_name" @sort-change="$sortChange" label="学生姓名" 				v-if="$check_field('get','student_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="number_of_enrolment" @sort-change="$sortChange" label="报名人数" 				v-if="$check_field('get','number_of_enrolment')" min-width="200">
					</el-table-column>
					<el-table-column prop="expense_item" @sort-change="$sortChange" label="费用项目" 				v-if="$check_field('get','expense_item')" min-width="200">
					</el-table-column>
					<el-table-column prop="amount_of_expenses" @sort-change="$sortChange" label="费用金额" 				v-if="$check_field('get','amount_of_expenses')" min-width="200">
					</el-table-column>
					<el-table-column prop="expense_details" @sort-change="$sortChange" label="费用明细" 				v-if="$check_field('get','expense_details')" min-width="200">
					</el-table-column>
	

			<el-table-column label="支付状态" prop="pay_state">
			</el-table-column>

			<el-table-column label="支付类型" prop="pay_type">
			</el-table-column>

            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>







			<el-table-column fixed="right" label="操作" min-width="200" v-if="$check_action('/activity_costs/table','set') || $check_action('/activity_costs/view','set') || $check_action('/activity_costs/view','get') 
						" >


				<template slot-scope="scope">
					<div class="view_a">
					<router-link class="e-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="$check_action('/activity_costs/table','set') || $check_action('/activity_costs/view','set') || $check_action('/activity_costs/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
					<el-button class="e-button el-button--small is-plain el-button--primary" style="margin: 5px !important;" size="small" @click="openPayModal(scope.row)" v-if="scope.row.pay_state==='未支付' && ($check_pay('/activity_costs/table'))">
						<span>支付</span>
					</el-button>
				</div>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->
														<el-dialog
				title="支付"
				:visible.sync="payModalVisible"
				width="500px"
		>
			<el-tabs v-model="pay_obj.payActiveName" :stretch="true">
				<el-tab-pane label="微信" name="微信">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="require('../../../public/wx.png')"
									  :preview-src-list="[require('../../../public/wx.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="支付宝" name="支付宝">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="require('../../../public/alipay.png')"
									  :preview-src-list="[require('../../../public/alipay.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="网银" name="网银">
					<div style="width: 80%;margin: 0 auto;margin-top: 30px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入网银账号：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input v-model="pay_obj.account" placeholder="请输入网银账号"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 20px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入支付密码，6位数字：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input placeholder="请输入密码" v-model="pay_obj.password" show-password maxlength="6"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 40px;">
						<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
					</div>
				</el-tab-pane>
			</el-tabs>
		</el-dialog>

		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<p class="modal_box_text">{{ message }}</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/activity_costs/get_list?like=0",
				url_del: "~/api/activity_costs/del?",

				// 字段ID
				field: "activity_costs_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
										"event_name": "",
																	"expense_item": "",
										"pay_state":"",
					"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},

				// 数据
				list: [],
				payModalVisible:false,
				pay_obj:{
					payActiveName:"微信",
					account:"",
					password:"",
					id:""
				},
																				// 用户列表
				list_user_teacher_user: [],
																			// 用户列表
				list_user_student_users: [],
															message: '',
			}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},
			/**
			 * @description 获取到列表事件
			 * @param {Object} res 响应结果
			 */
			get_list_after: function get_list_after(res, func, url) {
				let _this = this
																																																																			},
			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
						let sqlwhere = "(";
										if(user_group=="教师用户"){
						sqlwhere+= "teacher_user = " + this.user.user_id + " or ";
					}
																																						if(user_group=="学生用户"){
						sqlwhere+= "student_users = " + this.user.user_id + " or ";
					}
																																			if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						param["sqlwhere"] = sqlwhere;
					}
					}
				return param;
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

			get_user_teacher_user(id){
				var obj = this.list_user_teacher_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
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

			get_user_student_users(id){
				var obj = this.list_user_student_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
															openPayModal(v){
				this.pay_obj.id = v.activity_costs_id
				this.payModalVisible = true
			},
			submitPay(){
				let _this = this;
				if(_this.pay_obj.payActiveName == "网银"){
					if(_this.pay_obj.account == ""){
						_this.$toast("请输入网银账号", 'danger');
						return false
					}
					if(_this.pay_obj.password.length < 6){
						_this.$toast("请输入6位数的支付密码", 'danger');
						return false
					}
				}
				let url = this.$toUrl(this.query, "~/api/activity_costs/set?activity_costs_id="+this.pay_obj.id);
				let param = {"pay_state":"已支付","pay_type":this.pay_obj.payActiveName}
				this.$post(url, param, function(json, status) {
					console.log("提交结果：" ,json);
					if (json.result) {
						_this.$toast('支付成功！', 'success');
						_this.payModalVisible = false;
						_this.get_list();
					} else if (json.error) {
						_this.$toast(json.error.message, 'danger');
					}
				});
			},
		},
				created() {
							this.get_list_user_teacher_user();
																		this.get_list_user_student_users();
													}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: auto;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		padding: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
		text-align: center;
		font-size: 14px;
		color: #fff;
		margin: 5px auto;
		width: 90%;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		margin-bottom: 20px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
	.el-date-editor .el-range-separator{
		width: 10% !important;
	}
</style>

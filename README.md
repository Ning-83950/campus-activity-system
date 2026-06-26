# 🎯 校园活动报名系统

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.5-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MyBatis%20Plus-3.0.4-blue" alt="MyBatis Plus">
  <img src="https://img.shields.io/badge/MySQL-8.0-orange" alt="MySQL">
  <img src="https://img.shields.io/badge/Redis-✅-red" alt="Redis">
  <img src="https://img.shields.io/badge/Vue.js-2.x-42b883" alt="Vue">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

## 📖 项目简介

校园活动报名系统是一个功能完整的活动管理平台，支持教师发布活动、学生在线报名、二维码扫码签到、签到证书 PDF 导出、活动热度排行以及站内信/邮件通知等功能。系统采用前后端分离架构，前端基于 Vue.js，后端基于 Spring Boot。

---

## 🚀 核心功能

| 模块 | 功能描述 |
|------|----------|
| **📋 活动管理** | 活动发布、编辑、删除、审核；活动分类管理；支持活动封面图上传 |
| **📝 报名管理** | 学生在线报名/退报；人数限制校验；截止时间校验；报名审核 |
| **📱 二维码签到** | 为每个活动生成签到二维码，学生扫码即可完成签到；支持手动签到 |
| **📄 签到证书** | 签到成功后自动生成 PDF 参与证明，包含活动名称、时间、地点等信息 |
| **🔥 热度排行** | 多维度热度计算（浏览×1 + 点赞×3 + 收藏×5 + 评论×2 + 报名×4 + 签到×6），支持 TOP N 排行 |
| **📬 通知系统** | 活动发布、审核、报名、签到等节点自动推送站内信；支持群发通知给已报名学生 |
| **💬 互动社区** | 活动文章发布、评论、点赞、收藏 |
| **👥 用户系统** | 教师/学生多角色登录；Token 认证 + Redis 会话管理 |
| **📊 反馈评价** | 活动结束后学生反馈评价 |

---

## 🛠️ 技术栈

### 后端
- **Spring Boot 2.2.5** — 核心框架
- **MyBatis Plus 3.0.4 + Spring Data JPA** — 双 ORM 持久层
- **MySQL 8.0** — 关系型数据库
- **Redis** — 缓存与 Token 管理
- **Druid** — 数据库连接池
- **WebSocket** — 实时通信
- **ZXing** — 二维码生成
- **OpenPDF** — PDF 证书生成
- **JavaMail** — 邮件通知
- **Apache POI** — Excel 导入导出
- **FastJSON** — JSON 序列化

### 前端
- **Vue.js 2.x** — 前端框架
- **Element UI** — UI 组件库
- **ECharts** — 数据可视化
- **Axios** — HTTP 请求

---

## 📂 项目目录结构

```
project/
├── src/main/java/com/project/demo/
│   ├── Application.java              # 启动类
│   ├── config/                       # 配置类
│   │   └── WebAppConfig.java
│   ├── controller/                   # 控制器层（26 个）
│   │   ├── ActivityCheckinController.java    # 签到管理
│   │   ├── ActivityInformationController.java # 活动信息
│   │   ├── EnrollmentInformationController.java # 报名管理
│   │   ├── AuthController.java        # 登录认证
│   │   ├── UserController.java        # 用户管理
│   │   └── ...                        # 其他控制器
│   ├── dao/                          # 数据访问层
│   ├── entity/                       # 实体类
│   ├── service/                      # 业务逻辑层
│   └── constant/                     # 常量配置
├── src/main/resources/
│   ├── application.yml               # 主配置文件
│   └── static/                       # 前端静态资源
│       └── admin/                    # Vue 管理后台
├── pom.xml                           # Maven 依赖
├── build.sh                          # Linux 构建脚本
└── 运行.bat                          # Windows 一键运行
```

---

## 💻 本地运行

### 环境要求

- **JDK 1.8+**
- **Maven 3.6+**
- **MySQL 8.0**
- **Redis**（可选，用于 Token 管理）

### 步骤

#### 1. 创建数据库

```sql
CREATE DATABASE project_db DEFAULT CHARACTER SET utf8mb4;
```

#### 2. 导入数据库表结构

执行项目根目录下的 SQL 文件（`project.sql` / `新增签到热度通知功能.sql`）

#### 3. 修改配置

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/project_db?serverTimezone=GMT%2B8&useSSL=false&characterEncoding=utf-8
    username: root
    password: 你的密码
  redis:
    host: 127.0.0.1
    port: 6379

app:
  checkin:
    host: 你的本机IP   # 手机扫码签到需要改为局域网 IP
```

#### 4. 启动项目

```bash
# Windows 双击运行
运行.bat

# 或使用 Maven
mvn spring-boot:run
```

#### 5. 访问系统

- **后端 API**：`http://localhost:5000/api`
- **管理后台**：`http://localhost:8081`
- **学生端**：`http://localhost:8081`（手机扫码可在同一 WiFi 下访问）

---

## 🏗️ 数据库表结构

| 表名 | 说明 |
|------|------|
| `activity_information` | 活动信息表 |
| `enrollment_information` | 报名信息表 |
| `activity_checkin` | 签到记录表 |
| `activity_classification` | 活动分类表 |
| `activity_notification` | 通知记录表 |
| `student_users` | 学生用户表 |
| `teacher_user` | 教师用户表 |
| `article` | 文章表 |
| `comment` | 评论表 |
| `collect` | 收藏表 |
| `praise` | 点赞表 |
| `hits` | 浏览记录表 |
| `access_token` | 登录令牌表 |

---

## 📄 License

MIT License

---

> 💡 本项目为大学课程作业，如有问题欢迎提 Issue！

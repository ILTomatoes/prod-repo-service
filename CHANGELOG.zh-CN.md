# 更新日志
这个文件记录prod-repo-service所有版本的重大变动。

## [0.23.1-RELEASE] 2020-08-04

#### 修复
- Harbor创建机器人账号报错`409 conflict`

## [0.23.0-RELEASE] 2020-07-31

#### 新增
- 与devops-service融合：查询Harbor镜像仓库、镜像列表、镜像版本列表
- 与devops-service融合：查询Maven/npm仓库、包列表

#### 修复
- Harbor仓库编码修改为：`组织编码-项目编码`的小写

#### 优化
- 使用主键加密策略
- 使用雪花ID生成算法 
- 表结构&&数据初始化脚本

## [0.23.0-alpha.1] 2020-06-23

#### 新增
- 项目层新增"制品库管理"模块，包括创建制品库、自定义nexus服务、仓库总览、镜像/包列表、用户权限、操作日志等功能
- 组织层新增"制品库管理"模块，包括仓库列表、镜像/包列表、用户权限列表、操作日志列表等功能
- 平台层新增"制品库管理"模块，包括为默认的nexus服务上，已有仓库的分配功能
- 自定义nexus服务功能: 支持添加默认外自己安装的nexus服务。创建maven/npm仓库时，是在对应启用的nexus服务下
- 创建制品库功能: 支持在当前项目下创建/更新制品仓库
- 镜像/包管理功能: 支持查看与发布仓库下镜像/包列表
- 用户权限功能: 支持管理项目成员对该仓库的权限
- 操作日志功能: 记录了权限分配/镜像操作的操作日志
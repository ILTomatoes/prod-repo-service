package org.hrds.rdupm.harbor.infra.constant;

import org.springframework.http.HttpMethod;

/**
 * description
 *
 * @author chenxiuhong 2020/04/21 9:58 上午
 */
public interface HarborConstants {

	String TRUE = "true";

	String FALSE = "false";

	String KB = "KB";

	String MB = "MB";

	String GB = "GB";

	String TB = "TB";

	String USER_NAME = "admin";

	String PASSWORD = "Harbor12345";

	String DEFAULT_PASSWORD = "Abcd1234";

	enum HarborApiEnum{

		COUNT("/api/statistics", HttpMethod.GET,"获取所有项目数量、镜像数量"),

		SEARCH("/api/search", HttpMethod.GET,"获取所有项目、所有镜像"),

		/**
		* 用户API
		* */
		CREATE_USER("/api/users", HttpMethod.POST,"创建用户"),

		SELECT_USER_BY_USERNAME("/api/users/search", HttpMethod.GET,"根据用户名查询用户信息"),

		/**
		* 项目API
		* */
		CREATE_PROJECT("/api/projects", HttpMethod.POST,"创建项目"),

		DETAIL_PROJECT("/api/projects/%s", HttpMethod.GET,"查询项目详情-项目ID"),

		DELETE_PROJECT("/api/projects/%s", HttpMethod.DELETE,"删除项目-项目ID"),

		UPDATE_PROJECT("/api/projects/%s", HttpMethod.PUT,"修改项目-项目ID"),

		CHECK_PROJECT_NAME("/api/projects", HttpMethod.HEAD,"检查项目名称是否存在"),

		LIST_PROJECT("/api/projects", HttpMethod.GET,"查询项目列表"),

		/**
		 * 元数据API
		 *
		 * */
		GET_PROJECT_METADATA("/api/projects/%s/metadatas/%s", HttpMethod.GET,"根据项目ID和元数据名称 获取元数据值"),

		UPDATE_PROJECT_METADATA("/api/projects/%s/metadatas/%s", HttpMethod.PUT,"根据项目ID和元数据名称 更新元数据值"),    //有问题

		DELETE_PROJECT_METADATA("/api/projects/%s/metadatas/%s", HttpMethod.DELETE,"根据项目ID和元数据名称 更新元数据值"), //有问题

		/**
		* 项目概览
		* */
		GET_PROJECT_SUMMARY("/api/projects/%s/summary", HttpMethod.GET,"获取存储容量使用情况--项目ID"),

		/**
		* 项目资源API
		* */
		GET_PROJECT_QUOTA("/api/quotas/%s", HttpMethod.GET,"获取项目资源使用情况--项目ID"),

		UPDATE_PROJECT_QUOTA("/api/quotas/%s", HttpMethod.PUT,"更新项目资源使用情况--项目ID"),

		/**
		* 镜像API
		* */
		LIST_IMAGE("/api/repositories", HttpMethod.GET,"查询镜像列表"),

		UPDATE_IMAGE_DESC("/api/repositories/%s", HttpMethod.PUT,"更新镜像描述--镜像名称"),

		DELETE_IMAGE("/api/repositories/%s", HttpMethod.DELETE,"删除镜像--镜像名称"),

		/**
		* 日志API
		* */
		LOGS_PROJECT("/api/projects/%s/logs", HttpMethod.GET,"查询项目日志-项目ID");

		String apiUrl;

		HttpMethod httpMethod;

		String apiDesc;

		public String getApiUrl() {
			return apiUrl;
		}

		public void setApiUrl(String apiUrl) {
			this.apiUrl = apiUrl;
		}

		public HttpMethod getHttpMethod() {
			return httpMethod;
		}

		public void setHttpMethod(HttpMethod httpMethod) {
			this.httpMethod = httpMethod;
		}

		public String getApiDesc() {
			return apiDesc;
		}

		public void setApiDesc(String apiDesc) {
			this.apiDesc = apiDesc;
		}

		HarborApiEnum(String apiUrl, HttpMethod method, String apiDesc) {
			this.apiUrl = apiUrl;
			this.httpMethod = method;
			this.apiDesc = apiDesc;
		}

	}

	interface ErrorMessage{
		String HARBOR_SERVER_ERROR = "error.harbor.service";
	}

}

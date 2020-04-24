package org.hrds.rdupm.harbor.infra.feign.fallback;

import java.util.List;
import java.util.Set;

import io.choerodon.core.exception.CommonException;
import org.hrds.rdupm.harbor.infra.feign.BaseFeignClient;
import org.hrds.rdupm.harbor.infra.feign.dto.ProjectDTO;
import org.hrds.rdupm.harbor.infra.feign.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * BaseFeignClientFallBack
 * @author chenxiuhong
 */
@Component
public class BaseFeignClientFallBack implements BaseFeignClient {

	@Override
	public ResponseEntity<UserDTO> query(String loginName) {
		throw new CommonException("error.feign.user.select");
	}

	@Override
	public ResponseEntity<UserDTO> queryInfo(Long id) {
		throw new CommonException("error.feign.user.select");
	}

	@Override
	public ResponseEntity<List<UserDTO>> listUsersByIds(Long[] ids, Boolean onlyEnabled) {
		throw new CommonException("error.feign.user.batch.select");
	}

	@Override
	public ResponseEntity<List<UserDTO>> listUsersByLoginNames(String[] loginNames, Boolean onlyEnabled) {
		throw new CommonException("error.feign.user.batch.select");
	}

	@Override
	public ResponseEntity<List<UserDTO>> listUsersByName(Long prjectId, String param) {
		throw new CommonException("error.feign.user.selectUserByProjectId");
	}

	@Override
	public ResponseEntity<List<ProjectDTO>> listProjectsWithLimit(Long organizationId, String name) {
		throw new CommonException("error.feign.project.select");
	}

	@Override
	public ResponseEntity<List<ProjectDTO>> listProjectsByOrgId(Long organizationId) {
		throw new CommonException("error.feign.project.select");
	}

	@Override
	public ResponseEntity<ProjectDTO> query(Long id) {
		throw new CommonException("error.feign.project.select");
	}

	@Override
	public ResponseEntity<List<ProjectDTO>> queryByIds(Set<Long> ids) {
		throw new CommonException("error.feign.project.select");
	}

	@Override
	public ResponseEntity<List<UserDTO>> listProjectOwnerById(Long projectId) {
		throw new CommonException("error.feign.user.select");
	}

}
package org.hrds.rdupm.common.api.controller.v1;

import io.choerodon.swagger.annotation.Permission;
import io.choerodon.core.iam.ResourceLevel;
import io.swagger.annotations.ApiParam;
import org.hrds.rdupm.api.vo.ProductLibraryDTO;
import org.hrds.rdupm.common.app.service.ProdUserService;
import org.hrds.rdupm.harbor.app.service.HarborAuthService;
import org.hrds.rdupm.harbor.domain.entity.HarborAuth;
import org.hrds.rdupm.harbor.domain.repository.HarborAuthRepository;
import org.hrds.rdupm.nexus.domain.repository.NexusAuthRepository;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hrds.rdupm.common.domain.entity.ProdUser;
import org.hrds.rdupm.common.domain.repository.ProdUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 制品库-制品用户表 管理 API
 *
 * @author xiuhong.chen@hand-china.com 2020-05-21 15:47:14
 */
@RestController("prodUserController.v1")
@RequestMapping("/v1/prod-users")
public class ProdUserController extends BaseController {

    @Autowired
    private ProdUserRepository prodUserRepository;

    @Autowired
	private ProdUserService prodUserService;
    @Autowired
	private NexusAuthRepository nexusAuthRepository;
    @Autowired
	private HarborAuthRepository harborAuthRepository;

    @ApiOperation(value = "个人层--查询制品库用户信息")
	@Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{userId}")
    public ResponseEntity<ProdUser> detail(@PathVariable @ApiParam("猪齿鱼用户ID") Long userId) {
        ProdUser prodUser = prodUserRepository.select(ProdUser.FIELD_USER_ID,userId).stream().findFirst().orElse(null);
        if(prodUser != null && prodUser.getPwdUpdateFlag().intValue()==1){
        	prodUser.setPassword(null);
		}
        return Results.success(prodUser);
    }

	@ApiOperation(value = "个人层--修改制品库用户默认密码")
	@Permission(level = ResourceLevel.ORGANIZATION)
	@PostMapping("/updatePwd")
	public ResponseEntity<ProdUser> updatePwd(@RequestBody @ApiParam("必输字段用户IDuserId、旧密码oldPassword、新密码password、确认密码rePassword") ProdUser prodUser) {
		prodUserService.updatePwd(prodUser);
		return Results.success();
	}

	@ApiOperation(value = "项目层--获取当前用户，对应仓库分配的权限")
	@Permission(level = ResourceLevel.ORGANIZATION)
	@PostMapping("/getRoleList")
	public ResponseEntity<List<String>> getRoleList(@ApiParam(value = "仓库Id、项目Id", required = true) @RequestParam Long id,
													@ApiParam(value = "类型：MAVEN、NPM、DOCKER", required = true) @RequestParam String productType) {

		List<String> roleCode = new ArrayList<>();
    	if (productType.equals(ProductLibraryDTO.TYPE_DOCKER)) {
			roleCode = harborAuthRepository.getHarborRoleList(id);
		} else if (productType.equals(ProductLibraryDTO.TYPE_NPM) || productType.equals(ProductLibraryDTO.TYPE_MAVEN)) {
			roleCode = nexusAuthRepository.getRoleList(id);
		}
		return Results.success(roleCode);
	}

}

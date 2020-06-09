package org.hrds.rdupm.harbor.api.controller.v1;

import java.util.List;
import java.util.Set;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.swagger.annotations.ApiParam;
import org.hrds.rdupm.harbor.domain.entity.HarborCustomRepoDTO;
import org.hrds.rdupm.harbor.domain.entity.HarborRepoDTO;
import org.hrds.rdupm.harbor.infra.feign.dto.AppServiceDTO;
import org.hrds.rdupm.harbor.app.service.HarborCustomRepoService;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hrds.rdupm.harbor.domain.entity.HarborCustomRepo;
import org.hrds.rdupm.harbor.domain.repository.HarborCustomRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 制品库-harbor自定义镜像仓库表 管理 API
 *
 * @author mofei.li@hand-china.com 2020-06-02 09:51:58
 */
@RestController("harborCustomRepoController.v1")
@RequestMapping("/v1/{organizationId}/harbor-custom-repos")
public class HarborCustomRepoController extends BaseController {
    @Autowired
    private HarborCustomRepoService harborCustomRepoService;
    @Autowired
    private HarborCustomRepoRepository harborCustomRepoRepository;


    @ApiOperation(value = "校验自定义镜像仓库信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/check/custom-repo")
    public ResponseEntity<?> checkCustomRepo(@ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo) {
        validObject(harborCustomRepo);
        return Results.success(harborCustomRepoService.checkCustomRepo(harborCustomRepo));
    }

    @ApiOperation(value = "项目层-查询自定义仓库列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/list-project/{projectId}")
    public ResponseEntity<List<HarborCustomRepoDTO>> listRepoByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable(value = "projectId") Long projectId) {
        return Results.success(harborCustomRepoService.listByProjectId(projectId));
    }

    @ApiOperation(value = "组织层-查询自定义仓库列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/list-org")
    public ResponseEntity<Page<HarborCustomRepo>> listRepoByOrg(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable(value = "organizationId") Long organizationId,
                                           @ApiIgnore @SortDefault(value = HarborCustomRepo.FIELD_PROJECT_ID, direction = Sort.Direction.DESC) PageRequest pageRequest) {
        HarborCustomRepo repo = new HarborCustomRepo();
        repo.setOrganizationId(organizationId);
        return Results.success(harborCustomRepoService.listByOrg(repo, pageRequest));
    }

    @ApiOperation(value = "项目层-创建时查询所有自定义仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/list-all-services/{projectId}")
    public ResponseEntity<List<AppServiceDTO>> listAllAppServiceByCreate(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId) {
        return Results.success(harborCustomRepoService.listAllAppServiceByCreate(projectId));
    }


    @ApiOperation(value = "项目层-创建harbor自定义镜像仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/create/{projectId}")
    public ResponseEntity createByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                          @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo) {
        validObject(harborCustomRepo);
        harborCustomRepoService.createByProject(projectId, harborCustomRepo);
        return Results.success();
    }

    @ApiOperation(value = "项目层-查询自定义仓库明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/detail/project/{customRepoId}")
    public ResponseEntity<HarborCustomRepo> detailByProject(@ApiParam(value = "自定义仓库ID", required = true) @PathVariable("customRepoId") Long customRepoId) {
        return Results.success(harborCustomRepoService.detailByRepoId(customRepoId));
    }

    @ApiOperation(value = "项目层-修改自定义仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/update/{projectId}")
    public ResponseEntity updateByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                          @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        harborCustomRepoService.updateByProject(projectId, harborCustomRepo);
        return Results.success();
    }

    @ApiOperation(value = "项目层-删除harbor自定义镜像仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity deleteByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                          @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        harborCustomRepoService.deleteByProject(projectId,harborCustomRepo);
        return Results.success(harborCustomRepo);

    }

    @ApiOperation(value = "项目层-关联应用服务")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/relate-service/{projectId}")
    public ResponseEntity relateServiceByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                 @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo,
                                                 @ApiParam(value = "关联应用服务ID", required = true) @RequestParam Set<Long> appServiceIds) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        harborCustomRepoService.relateServiceByProject(projectId, harborCustomRepo, appServiceIds);
        return Results.success();
    }

    @ApiOperation(value = "项目层-查询关联应用服务列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/relate-service/{projectId}")
    public ResponseEntity<Page<AppServiceDTO>> pageRelatedServiceByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                                           @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo,
                                                                           @ApiIgnore PageRequest pageRequest) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        Page<AppServiceDTO> page = harborCustomRepoService.pageRelatedServiceByProject(projectId, harborCustomRepo, pageRequest);
        return Results.success(page);
    }

    @ApiOperation(value = "项目层-查询未关联应用服务列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/no-relate-service/{repoId}")
    public ResponseEntity<List<AppServiceDTO>> pageNoRelatedService(@ApiParam(value = "自定义仓库id", required = true) @PathVariable("repoId") Long repoId,
                                                                    @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        List<AppServiceDTO> unRelatedServices = harborCustomRepoService.getNoRelatedAppService(repoId);
        return Results.success(unRelatedServices);
    }

    @ApiOperation(value = "组织层-查询自定义仓库明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/detail/org/{customRepoId}")
    public ResponseEntity<HarborCustomRepo> detailByOrg(@ApiParam(value = "自定义仓库ID", required = true) @PathVariable("customRepoId") Long customRepoId) {
        return Results.success(harborCustomRepoService.detailByRepoId(customRepoId));
    }

    @ApiOperation(value = "组织层-查询关联应用服务列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/relate-service")
    public ResponseEntity<Page<AppServiceDTO>> pageRelatedServiceByOrg(@ApiParam(value = "猪齿鱼组织ID", required = true) @PathVariable("organizationId") Long organizationId,
                                                                       @ApiParam(value = "自定义镜像仓库", required = true) @RequestBody HarborCustomRepo harborCustomRepo,
                                                                       @ApiIgnore PageRequest pageRequest) {
        SecurityTokenHelper.validToken(harborCustomRepo);
        Page<AppServiceDTO> page = harborCustomRepoService.pageRelatedServiceByOrg(organizationId, harborCustomRepo, pageRequest);
        return Results.success(page);
    }



    @ApiOperation(value = "查询项目下所有自定义仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/project/{projectId}/list_all_custom_repo")
    public ResponseEntity<List<HarborCustomRepo>> listAllCustomRepoByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId) {
        List<HarborCustomRepo> list = harborCustomRepoService.listAllCustomRepoByProject(projectId);
        return Results.success(list);
    }

    @ApiOperation(value = "查询应用服务关联的自定义仓库，否则返回默认仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/project/{projectId}/{appServiceId}/list_related_custom_repo_or_default")
    public ResponseEntity<HarborRepoDTO> listRelatedCustomRepoOrDefaultByService(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                                                 @ApiParam(value = "应用服务ID", required = true) @PathVariable("appServiceId") Long appServiceId) {
        HarborRepoDTO harborRepoDTO = harborCustomRepoService.listRelatedCustomRepoOrDefaultByService(projectId, appServiceId);
        return Results.success(harborRepoDTO);
    }
}

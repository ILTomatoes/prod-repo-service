package org.hrds.rdupm.harbor.api.controller.v1;

import java.util.List;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hrds.rdupm.harbor.app.service.HarborCustomRepoService;
import org.hrds.rdupm.harbor.domain.entity.HarborCustomRepo;
import org.hrds.rdupm.harbor.domain.entity.HarborRepoDTO;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 制品库-Harbor猪齿鱼 API
 *
 * @author mofei.li@hand-china.com 2020/06/11 10:42
 */
@RestController("harborChoerodonRepoController.v1")
@RequestMapping("/v1/harbor-choerodon-repos")
public class HarborChoerodonRepoController extends BaseController {
    @Autowired
    private HarborCustomRepoService harborCustomRepoService;

    @ApiOperation(value = "应用服务-查询项目下所有自定义仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/project/{projectId}/list_all_custom_repo")
    public ResponseEntity<List<HarborCustomRepo>> listAllCustomRepoByProject(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId) {
        List<HarborCustomRepo> list = harborCustomRepoService.listAllCustomRepoByProject(projectId);
        return Results.success(list);
    }

    @ApiOperation(value = "应用服务-查询关联的自定义仓库")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/project/{projectId}/{appServiceId}/list_related_custom_repo")
    public ResponseEntity<HarborCustomRepo> listRelatedCustomRepoByService(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                                           @ApiParam(value = "应用服务ID", required = true) @PathVariable("appServiceId") Long appServiceId) {
        return Results.success(harborCustomRepoService.listRelatedCustomRepoOrDefaultByService(projectId, appServiceId));
    }

    @ApiOperation(value = "应用服务-保存关联关系")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/project/{projectId}/{appServiceId}/save_relation")
    public ResponseEntity saveRelationByService(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                @ApiParam(value = "应用服务ID", required = true) @PathVariable("appServiceId") Long appServiceId,
                                                @ApiParam(value = "自定义仓库ID", required = true) @RequestParam Long customRepoId) {
        harborCustomRepoService.saveRelationByService(projectId, appServiceId, customRepoId);
        return Results.success();
    }

    @ApiOperation(value = "应用服务-删除关联关系")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/project/{projectId}/{appServiceId}/delete_relation")
    public ResponseEntity deleteRelationByService(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                  @ApiParam(value = "应用服务ID", required = true) @PathVariable("appServiceId") Long appServiceId,
                                                  @ApiParam(value = "自定义仓库ID", required = true) @RequestParam Long customRepoId) {
        harborCustomRepoService.deleteRelationByService(projectId, appServiceId, customRepoId);
        return Results.success();
    }

    @ApiOperation(value = "仓库配置查询接口")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/project/{projectId}/{appServiceId}/harbor_repo_config")
    public ResponseEntity<HarborRepoDTO> queryHarborRepoConfig(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                               @ApiParam(value = "应用服务ID", required = true) @PathVariable("appServiceId") Long appServiceId){
        return Results.success(harborCustomRepoService.getHarborRepoConfig(projectId, appServiceId));
    }

    @ApiOperation(value = "根据Harbor仓库ID查询仓库配置")
    @Permission(level = ResourceLevel.ORGANIZATION,permissionPublic = true)
    @GetMapping("/project/{projectId}/harbor_config_by_id")
    public ResponseEntity<HarborRepoDTO> queryHarborRepoConfigById(@ApiParam(value = "猪齿鱼项目ID", required = true) @PathVariable("projectId") Long projectId,
                                                                   @ApiParam(value = "仓库ID",required = false) @RequestParam(required = false) Long repoId,
                                                                   @ApiParam(value = "仓库类型", required = true) @RequestParam String repoType){
        return Results.success(harborCustomRepoService.getHarborRepoConfigByRepoId(projectId, repoId, repoType));
    }

}

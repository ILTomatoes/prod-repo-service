package org.hrds.rdupm.nexus.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hrds.rdupm.nexus.client.nexus.model.NexusServerRepository;
import org.hrds.rdupm.nexus.domain.entity.NexusRepository;
import org.hrds.rdupm.nexus.domain.entity.NexusUser;
import org.hrds.rdupm.nexus.infra.util.VelocityUtils;
import org.hrds.rdupm.util.DESEncryptUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置信息父类
 * @author weisen.yang@hand-china.com 2020/5/13
 */
@ApiModel("nexus maven-配置信息父类")
public class NexusBaseGuideDTO {


    /**
     * 值设置
     * @param nexusServerRepository nexus服务,仓库信息
     * @param nexusRepository 数据库表，仓库信息
     * @param nexusUser 仓库默认管理用户
     */
    public void handlePullGuideValue(NexusServerRepository nexusServerRepository,
                                     NexusRepository nexusRepository,
                                     NexusUser nexusUser){
        // 拉取配置，仓库信息
        Map<String, Object> map = new HashMap<>(16);
        map.put("versionPolicy", nexusServerRepository.getVersionPolicy());
        map.put("repositoryName", nexusServerRepository.getName());
        map.put("url", nexusServerRepository.getUrl());
        map.put("type", nexusServerRepository.getType());


        // 拉取信息
        this.setPullServerFlag(nexusRepository != null && nexusRepository.getAllowAnonymous() != 1);
        if (this.getPullServerFlag() && nexusUser != null) {
            // 要显示的时候，返回数据
            String nePullUserPassword = DESEncryptUtil.decode(nexusUser.getNePullUserPassword());

            map.put("username", nexusUser.getNePullUserId());
            this.setPullServerInfo(VelocityUtils.getJsonString(map, VelocityUtils.SET_SERVER_FILE_NAME));
            this.setPullPassword(nePullUserPassword);
            this.setPullServerInfoPassword(this.getPullServerInfo().replace("[password]", this.getPullPassword()));
            this.setPullServerInfoPassword(this.getPullServerInfoPassword().replace("[username]", nexusUser.getNePullUserId()));
        }
        this.setPullPomRepoInfo(VelocityUtils.getJsonString(map, VelocityUtils.POM_REPO_FILE_NAME));

    }

    @ApiModelProperty(value = "拉取配置：server配置是否显示")
    private Boolean pullServerFlag;
    @ApiModelProperty(value = "拉取配置：server配置信息")
    private String pullServerInfo;
    @ApiModelProperty(value = "拉取配置：server配置信息(包含密码)")
    private String pullServerInfoPassword;
    @ApiModelProperty(value = "拉取配置：密码")
    private String pullPassword;

    @ApiModelProperty(value = "拉取配置：pom文件，仓库配置")
    private String pullPomRepoInfo;
    @ApiModelProperty(value = "拉取配置：拉取配置是否显示")
    private Boolean showPushFlag;

    public Boolean getPullServerFlag() {
        return pullServerFlag;
    }

    public NexusBaseGuideDTO setPullServerFlag(Boolean pullServerFlag) {
        this.pullServerFlag = pullServerFlag;
        return this;
    }

    public String getPullServerInfo() {
        return pullServerInfo;
    }

    public NexusBaseGuideDTO setPullServerInfo(String pullServerInfo) {
        this.pullServerInfo = pullServerInfo;
        return this;
    }

    public String getPullServerInfoPassword() {
        return pullServerInfoPassword;
    }

    public NexusBaseGuideDTO setPullServerInfoPassword(String pullServerInfoPassword) {
        this.pullServerInfoPassword = pullServerInfoPassword;
        return this;
    }

    public String getPullPassword() {
        return pullPassword;
    }

    public NexusBaseGuideDTO setPullPassword(String pullPassword) {
        this.pullPassword = pullPassword;
        return this;
    }

    public String getPullPomRepoInfo() {
        return pullPomRepoInfo;
    }

    public NexusBaseGuideDTO setPullPomRepoInfo(String pullPomRepoInfo) {
        this.pullPomRepoInfo = pullPomRepoInfo;
        return this;
    }

    public Boolean getShowPushFlag() {
        return showPushFlag;
    }

    public NexusBaseGuideDTO setShowPushFlag(Boolean showPushFlag) {
        this.showPushFlag = showPushFlag;
        return this;
    }
}

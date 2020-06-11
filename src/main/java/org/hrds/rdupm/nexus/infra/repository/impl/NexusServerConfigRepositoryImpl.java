package org.hrds.rdupm.nexus.infra.repository.impl;

import org.hrds.rdupm.nexus.infra.mapper.NexusProjectServiceMapper;
import org.hrds.rdupm.nexus.infra.mapper.NexusServerConfigMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hrds.rdupm.nexus.domain.entity.NexusServerConfig;
import org.hrds.rdupm.nexus.domain.repository.NexusServerConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 制品库_nexus服务信息配置表 资源库实现
 *
 * @author weisen.yang@hand-china.com 2020-03-27 11:42:59
 */
@Component
public class NexusServerConfigRepositoryImpl extends BaseRepositoryImpl<NexusServerConfig> implements NexusServerConfigRepository {

    @Autowired
    private NexusServerConfigMapper nexusServerConfigMapper;
    @Override
    public NexusServerConfig queryServiceConfig(Long configId, Long projectId) {
        return nexusServerConfigMapper.queryServiceConfig(configId, projectId);
    }

    @Override
    public List<NexusServerConfig> queryList(Long organizationId, Long projectId) {
        return nexusServerConfigMapper.queryList(organizationId, projectId);
    }
}

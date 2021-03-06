package org.hrds.rdupm.nexus.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;
import org.hrds.rdupm.nexus.domain.entity.NexusUser;

import java.util.List;

/**
 * 制品库_nexus仓库默认用户信息表资源库
 *
 * @author weisen.yang@hand-china.com 2020-03-27 11:42:59
 */
public interface NexusUserRepository extends BaseRepository<NexusUser> {
}

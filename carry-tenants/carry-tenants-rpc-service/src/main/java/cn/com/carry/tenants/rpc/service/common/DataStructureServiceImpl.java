package cn.com.carry.tenants.rpc.service.common;

import cn.com.carry.common.model.base.TenantsRegion;
import cn.com.carry.tenants.api.common.DataStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("dataStructureService")
public class DataStructureServiceImpl implements DataStructureService{

    Logger logger = LoggerFactory.getLogger(DataStructureServiceImpl.class);

    @Override
    public List<TenantsRegion> 读取组织框架(Long id) {

        return null;
    }

    @Override
    public TenantsRegion 读取组织框架_单树结构(Long id) {

        return null;
    }

    public static TenantsRegion 读取子组织树(List<TenantsRegion> regionTreeList, Long regionId) {
        for (TenantsRegion regionTree: regionTreeList) {
            if (regionTree.getId().equals(regionId)) {
                return regionTree;
            } else if (regionTree.getChildren() != null) {
                TenantsRegion tenantsRegion = 读取子组织树(regionTree.getChildren(), regionId);
                if (tenantsRegion != null) {
                    return tenantsRegion;
                }
            }
        }
        return null;
    }

    public static List<Long> getDescendantRegionIdList(TenantsRegion regionTree) {
        List<Long> idList = new ArrayList<>();
        idList.add(regionTree.getId());
        if (regionTree.getChildren() != null) {
            for (TenantsRegion region: regionTree.getChildren()) {
                idList.addAll(getDescendantRegionIdList(region));
            }
        }
        return idList;
    }

}

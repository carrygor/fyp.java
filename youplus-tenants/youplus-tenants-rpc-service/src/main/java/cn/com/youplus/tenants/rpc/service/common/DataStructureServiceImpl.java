package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.TenantsRegion;
import cn.com.youplus.common.model.base.TenantsRegionNode;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.DataStructureService;
import cn.com.youplus.tenants.common.form.RegionAddForm;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("dataStructureService")
public class DataStructureServiceImpl implements DataStructureService{

    Logger logger = LoggerFactory.getLogger(DataStructureServiceImpl.class);

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Override
    public List<TenantsRegion> 读取组织框架(Long id) {

        logger.debug("读取组织框架-开始"  + new Date().toString());
        // 一定要注意排序
        List<UpTenantsRegion> list = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, id)
                        .orderBy(UpTenantsRegion.LEVEL)
                        .orderBy(UpTenantsRegion.PARENT_ID)
                        .orderBy(UpTenantsRegion.ID)
        );

        logger.debug("读取组织框架-查询完成"  + new Date().toString());

        Map<Long, TenantsRegion> fastMap = new HashMap<>();
        List<TenantsRegion> regions = new ArrayList<>();
        regions.add(new TenantsRegion()
                .setId(0L)
                .setLevel(0)
                .setLabel("全部")
                .setQuickTag("")
                .setParentId(0L));
        list.forEach(region -> {
            if (region.getLevel() == 0) {
                TenantsRegion regionL0 = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(0)
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(0L);
                regions.add(regionL0);
                fastMap.put(region.getId(), regionL0);
            } else {
                //get parent
                if (region.getParentId() <= 0) {
                    return;
                }
                TenantsRegion regionParent = fastMap.get(region.getParentId());
                if (regionParent.getChildren() == null) {
                    regionParent.setChildren(new ArrayList<>());
                }
                TenantsRegion regionLx = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(region.getLevel())
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(region.getParentId());
                regionParent.getChildren().add(regionLx);
                fastMap.put(region.getId(), regionLx);
            }
        });

        logger.debug("读取组织框架-组装"  + new Date().toString());

        return regions;
    }

    @Override
    public TenantsRegion 读取组织框架_单树结构(Long id) {
        // 一定要注意排序
        logger.debug("读取组织框架-开始"  + new Date().toString());
        List<UpTenantsRegion> list = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, id)
                        .orderBy(UpTenantsRegion.LEVEL)
                        .orderBy(UpTenantsRegion.PARENT_ID)
                        .orderBy(UpTenantsRegion.ID)
        );
        logger.debug("读取组织框架-查询完成"  + new Date().toString());
        Map<Long, TenantsRegion> fastMap = new HashMap<>();
        TenantsRegion regions = new TenantsRegion();
        list.forEach(region -> {
            if (region.getLevel() == 0) {
                //level为0的region只有一个
                regions.setId(region.getId())
                        .setLevel(region.getLevel())
                        .setLabel(region.getName())
                        .setRegionCode(region.getRegionCode())
                        .setQuickTag(region.getQuickTag())
                        .setIsStore(region.getIsStore())
                        .setProvince(region.getProvince())
                        .setCity(region.getCity())
                        .setDistrict(region.getDistrict())
                        .setAddress(region.getAddress())
                        .setChildren(new ArrayList<>())
                        .setParentId(0L);
                fastMap.put(region.getId(), regions);
            } else {
                //get parent
                if (region.getParentId() <= 0) {
                    return;
                }
                TenantsRegion regionParent = fastMap.get(region.getParentId());
                if (regionParent == null) {
                    return;
                }
                if (regionParent.getChildren() == null) {
                    regionParent.setChildren(new ArrayList<>());
                }
                TenantsRegion regionLx = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(region.getLevel())
                        .setLabel(region.getName())
                        .setRegionCode(region.getRegionCode())
                        .setQuickTag(region.getQuickTag())
                        .setIsStore(region.getIsStore())
                        .setParentId(region.getParentId())
                        .setProvince(region.getProvince())
                        .setCity(region.getCity())
                        .setDistrict(region.getDistrict())
                        .setAddress(region.getAddress());
                regionParent.getChildren().add(regionLx);
                if (region.getIsStore() == 0) {
                    fastMap.put(region.getId(), regionLx);
                }
            }
        });
        logger.debug("读取组织框架-jisuan完成"  + new Date().toString());
        return regions;
    }

    @Override
    public List<TenantsRegionNode> getTenantsRegionList(long companyId) {

        TenantsRegion regionTree = 读取组织框架_单树结构(companyId);
        List<TenantsRegionNode> list = getTenantsRegionNodeList(regionTree);

        return list;
    }

    public static List<TenantsRegionNode> getTenantsRegionNodeList(TenantsRegion regionTree) {
        List<TenantsRegionNode> list = new ArrayList<>();
        list.add(new TenantsRegionNode(regionTree));
        if (regionTree.getChildren() != null) {
            regionTree.getChildren().forEach(child -> {
                list.addAll(getTenantsRegionNodeList(child));
            });
        }

        return list;
    }

    @Override
    public void addNewRegion(RegionAddForm form, UpTenantsCompany company) throws AlertException {

        int num = upTenantsRegionService.selectCount(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );
        if (num > company.getMaxSiteNum()) {
            throw new AlertException("您的企业网点数目已经超过" + company.getMaxSiteNum() + "个网点数量限制。请联系优加工作人员进行升级");
        }

        UpTenantsRegion checkRegion = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.NAME, form.getName())
        );
        if (checkRegion != null) {
            throw new AlertException("已存在相同名称的层级或节点");
        }

        UpTenantsRegion parent = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                .eq(UpTenantsRegion.ID, form.getParentId())
        );
        if (parent == null) {
            throw new AlertException("父节点不存在");
        }
        UpTenantsRegion region = new UpTenantsRegion();
        region.setName(form.getName())
                .setTenantsCompanyId(company.getId())
                .setParentId(form.getParentId())
                .setIsStore(form.getIsStore())
                .setLevel(parent.getLevel() + 1)
                .setRegionCode(form.getRegionCode())
                .setProvince(form.getProvince())
                .setCity(form.getCity())
                .setDistrict(form.getDistrict())
                .setAddress(form.getAddress())
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        region = upTenantsRegionService.mInsert(region);

        region.setQuickTag(parent.getQuickTag() + region.getId().toString() + ",");

        if (!upTenantsRegionService.updateById(region)) {
            throw new AlertException("插入数据异常");
        }

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

    @Override
    public List<Long> getDescendantRegionIdList(Long companyId, long regionId) {
        TenantsRegion regionTreeList = 读取组织框架_单树结构(companyId);
        TenantsRegion regionTree;
        if (regionTreeList.getId().equals(regionId)) {
            regionTree = regionTreeList;
        } else {
            regionTree = 读取子组织树(regionTreeList.getChildren(), regionId);
        }
        List<Long> idList = getDescendantRegionIdList(regionTree);

        return idList;
    }

}

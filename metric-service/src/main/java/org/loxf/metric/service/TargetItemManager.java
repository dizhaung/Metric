package org.loxf.metric.service;

import org.loxf.metric.common.dto.BaseResult;
import org.loxf.metric.common.dto.TargetItemDto;
import org.loxf.metric.dal.dao.interfaces.TargetDao;
import org.loxf.metric.dal.po.TargetItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by caiyang on 2017/5/4.
 */
@Component
public class TargetItemManager {
    @Autowired
    private TargetDao targetDao;

    public List<TargetItemDto> getTargetItemList(TargetItemDto targetItemDtoDto) {
        TargetItem ti = new TargetItem();
        List<TargetItemDto> resultlist = new ArrayList<>();
        BeanUtils.copyProperties(targetItemDtoDto, ti);
        List<TargetItem> targetItemList = targetDao.getTargetItemList(ti);
        if (!CollectionUtils.isEmpty(targetItemList)) {
            for (TargetItem targetItem : targetItemList) {
                TargetItemDto targetdto = new TargetItemDto();
                BeanUtils.copyProperties(targetItem, targetdto);
                resultlist.add(targetdto);
            }
        }
        return resultlist;
    }

    @Transactional
    public BaseResult<String> createTargetItems(List<TargetItemDto> targetItemDtos, String targetId) {
        List<TargetItem> datalist = new ArrayList<>();
        if (!CollectionUtils.isEmpty(targetItemDtos)) {
            for (TargetItemDto tid : targetItemDtos) {
                TargetItem ti = new TargetItem();
                BeanUtils.copyProperties(tid, ti);
                ti.setCreatedAt(new Date());
                ti.setUpdatedAt(new Date());
                ti.setTargetId(targetId);
                datalist.add(ti);
            }
            targetDao.batchInsert(datalist);
        }
        return new BaseResult<String>();
    }

    @Transactional
    public void deleteByTargetId(String targetId) {
        Map target = new HashMap();
        target.put("targetId", targetId);
        targetDao.remove(target);
    }
}

package org.loxf.metric.service.base;

import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.loxf.metric.api.IBaseService;
import org.loxf.metric.base.exception.MetricException;
import org.loxf.metric.common.constants.ResultCodeEnum;
import org.loxf.metric.common.constants.UserTypeEnum;
import org.loxf.metric.common.dto.BaseResult;
import org.loxf.metric.common.dto.PageData;
import org.loxf.metric.common.dto.Pager;
import org.loxf.metric.core.mongo.IBaseDao;
import org.loxf.metric.core.mongo.MongoDaoBase;
import org.loxf.metric.dal.dao.interfaces.UserDao;
import org.loxf.metric.dal.po.BasePO;
import org.loxf.metric.dal.po.Common;
import org.loxf.metric.dal.po.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by caiyang on 2017/3/27.
 */
public class BaseService {
    Logger logger = Logger.getLogger(this.getClass());

    public PageData getPageResult(Class<? extends Object> daoClazz, Map<String, Object> params,int start,int pageSize) {
        IBaseDao dao = (IBaseDao)SpringApplicationContextUtil.getBean(daoClazz);
        return getPageResult(dao, params, start, pageSize);
    }


    public String validPager(Pager pager){
        String result;
        if(pager==null){
            result="分页信息为空";
        }else{
            int pageSize=pager.getRownum();
            int currentPage=pager.getCurrentPage();
            if(pageSize<0||currentPage<0){
                result="分页信息参数错误！";
            }else{
                result="SUCCESS";
            }
        }
        return result;
    }


    public PageData getPageResult(IBaseDao dao, Map<String, Object> params, int start, int pageSize) {
        try {
            long totalCount = (long) dao.countByParams(params);
            if (totalCount <= 0) {
                return null;
            }
            List pageResult = dao.findByPager(params, start, pageSize);
            PageData pageData=new PageData();
            pageData.setRows(pageResult);
            pageData.setTotalRecords(totalCount);
            pageData.setCurrentPage(start/pageSize+1);
            pageData.setRownum(pageSize);
            pageData.setTotalPage(pageData.calculateTotalPage());
            return pageData;
        }catch (Exception e){
            logger.error("查询分页异常！",e);
            return null;
        }
    }

}

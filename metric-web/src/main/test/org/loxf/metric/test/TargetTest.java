package org.loxf.metric.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.loxf.metric.api.ITargetService;
import org.loxf.metric.base.ItemList.TargetItem;
import org.loxf.metric.base.constants.VisibleTypeEnum;
import org.loxf.metric.common.constants.StandardState;
import org.loxf.metric.common.dto.TargetDto;
import org.loxf.metric.test.core.JUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luohj on 2017/7/12.
 */
@RunWith(JUnit4ClassRunner.class)
@ActiveProfiles("rd")
@ContextConfiguration(locations = {"classpath*:root-test.xml"})
public class TargetTest {
    private static Logger logger = LoggerFactory.getLogger(TargetTest.class);
    private static final String uniqueCode = "LCUkdguGTKSjstlwLT983Nkkfux";
    @Autowired
    private ITargetService targetService;

    @Test
    public void createTarget() throws ParseException {
        TargetDto targetDto = new TargetDto();
        targetDto.setTargetName("测试目标");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        targetDto.setTargetStartTime(sf.parse("2017-07-01 00:00:00"));
        targetDto.setTargetEndTime(sf.parse("2017-09-31 00:00:00"));
        targetDto.setState(StandardState.AVAILABLE.getValue());
        targetDto.setVisibleType(VisibleTypeEnum.ALL.name());
        List<TargetItem> list = new ArrayList<>();
        TargetItem item = new TargetItem();
        item.setQuotaCode("QUOTA001");
        item.setQuotaName("指标1测试");
        item.setTargetValue("10000");
        item.setWeight(80);
        list.add(item);
        TargetItem item1 = new TargetItem();
        item1.setQuotaCode("QUOTA002");
        item1.setQuotaName("指标2测试");
        item1.setTargetValue("10000");
        item1.setWeight(20);
        list.add(item1);
        targetDto.setItemList(list);
        targetDto.setHandleUserName("admin");
        logger.debug(JSON.toJSONString(targetService.insertItem(targetDto)));
    }
    @Test
    public void queryTarget(){
        TargetDto targetDto = new TargetDto();
        TargetItem item = new TargetItem();
        item.setQuotaCode("QUOTA001");
        List<TargetItem> list = new ArrayList<>();
        list.add(item);
        targetDto.setItemList(list);
        targetDto.setTargetName("测试");
        targetDto.setTargetEndTime(new Date());
        logger.debug(JSON.toJSONString(targetService.queryTarget(targetDto)));
    }

}

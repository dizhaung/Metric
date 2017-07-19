package org.loxf.metric.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.loxf.metric.base.ItemList.TargetItem;
import org.loxf.metric.base.ItemList.VisibleItem;

import java.util.Date;
import java.util.List;
@ApiModel("目标")
public class TargetDto extends BaseDto{
    @ApiModelProperty("目标编码")
    private String targetCode;

    @ApiModelProperty("目标名称")
    private String targetName;

    @ApiModelProperty("目标描述")
    private String targetDesc;

    @ApiModelProperty("状态:AVAILABLE(生效)/DISABLED(失效)")
    private String state;

    @ApiModelProperty("目标开始时间")
    private Date targetStartTime;

    @ApiModelProperty("目标结束时间")
    private Date targetEndTime;

    @ApiModelProperty("团队码")
    private String uniqueCode;

    @ApiModelProperty("可见范围:SPECIFICRANGE/ALL")
    private String visibleType;

    @ApiModelProperty("可见列表")
    private List<VisibleItem> visibleList;

    @ApiModelProperty("目标项")
    private List<TargetItem> itemList;

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName == null ? null : targetName.trim();
    }

    public String getTargetDesc() {
        return targetDesc;
    }

    public void setTargetDesc(String targetDesc) {
        this.targetDesc = targetDesc == null ? null : targetDesc.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTargetStartTime() {
        return targetStartTime;
    }

    public void setTargetStartTime(Date targetStartTime) {
        this.targetStartTime = targetStartTime;
    }

    public Date getTargetEndTime() {
        return targetEndTime;
    }

    public void setTargetEndTime(Date targetEndTime) {
        this.targetEndTime = targetEndTime;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(String visibleType) {
        this.visibleType = visibleType;
    }

    public List<TargetItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TargetItem> itemList) {
        this.itemList = itemList;
    }

    public List<VisibleItem> getVisibleList() {
        return visibleList;
    }

    public void setVisibleList(List<VisibleItem> visibleList) {
        this.visibleList = visibleList;
    }
}
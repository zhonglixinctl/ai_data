package org.jeecg.modules.zhy_ai.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * (Data)实体类
 *
 * @author 钟腾
 * @since 2021-03-30 14:24:31
 */
@lombok.Data
@TableName("data")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "data对象", description = "data")
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 操作选项
     */
    @Excel(name = "操作选项", width = 15)
    @ApiModelProperty(value = "操作选项")
    private String operationOptions;
    /**
     * 操作意见
     */
    @Excel(name = "操作意见", width = 15)
    @ApiModelProperty(value = "操作意见")
    private String operationalOpinions;
    /**
     * 审核意见
     */
    @Excel(name = "审核意见", width = 15)
    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;
    /**
     * 处理状态
     */
    @Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
    private String processingStatus;
    /**
     * 序号
     */
    @Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private String categoryCode;
    /**
     * 类别编码
     */
    @Excel(name = "类别编码", width = 15)
    @ApiModelProperty(value = "类别编码")
    private String serialNumber;
    /**
     * 类别名称
     */
    @Excel(name = "类别名称", width = 15)
    @ApiModelProperty(value = "类别名称")
    private String classificationName;
    /**
     * 物料编码
     */
    @Excel(name = "物料编码", width = 15)
    @ApiModelProperty(value = "物料编码")
    private String materialCoding;
    /**
     * 长描述
     */
    @Excel(name = "长描述", width = 15)
    @ApiModelProperty(value = "长描述")
    private String longDescription;
    /**
     * 短描述
     */
    @Excel(name = "短描述", width = 15)
    @ApiModelProperty(value = "短描述")
    private String shortDescription;
    /**
     * 物资名称
     */
    @Excel(name = "物资名称", width = 15)
    @ApiModelProperty(value = "物资名称")
    private String nameOfGoods;
    /**
     * 基本单位
     */
    @Excel(name = "基本单位", width = 15)
    @ApiModelProperty(value = "基本单位")
    private String basicUnit;
    /**
     * 物料组
     */
    @Excel(name = "物料组", width = 15)
    @ApiModelProperty(value = "物料组")
    private String materialGroup;
    /**
     * 属性1
     */
    @Excel(name = "属性1", width = 15)
    @ApiModelProperty(value = "属性1")
    private String property1;
    /**
     * 属性2
     */
    @Excel(name = "属性2", width = 15)
    @ApiModelProperty(value = "属性2")
    private String property2;
    /**
     * 属性3
     */
    @Excel(name = "属性3", width = 15)
    @ApiModelProperty(value = "属性3")
    private String property3;
    /**
     * 属性4
     */
    @Excel(name = "属性4", width = 15)
    @ApiModelProperty(value = "属性4")
    private String property4;
    /**
     * 属性5
     */
    @Excel(name = "属性5", width = 15)
    @ApiModelProperty(value = "属性5")
    private String property5;
    /**
     * 属性6
     */
    @Excel(name = "属性6", width = 15)
    @ApiModelProperty(value = "属性6")
    private String property6;
    /**
     * 属性7
     */
    @Excel(name = "属性7", width = 15)
    @ApiModelProperty(value = "属性7")
    private String property7;
    /**
     * 属性8
     */
    @Excel(name = "属性8", width = 15)
    @ApiModelProperty(value = "属性8")
    private String property8;
    /**
     * 属性9
     */
    @Excel(name = "属性9", width = 15)
    @ApiModelProperty(value = "属性9")
    private String property9;
    /**
     * 属性10
     */
    @Excel(name = "属性10", width = 15)
    @ApiModelProperty(value = "属性10")
    private String property10;
    /**
     * 属性11
     */
    @Excel(name = "属性11", width = 15)
    @ApiModelProperty(value = "属性11")
    private String property11;
    /**
     * 属性12
     */
    @Excel(name = "属性12", width = 15)
    @ApiModelProperty(value = "属性12")
    private String property12;
    /**
     * 属性13
     */
    @Excel(name = "属性13", width = 15)
    @ApiModelProperty(value = "属性13")
    private String property13;
    /**
     * 属性14
     */
    @Excel(name = "属性14", width = 15)
    @ApiModelProperty(value = "属性14")
    private String property14;
    /**
     * 属性15
     */
    @Excel(name = "属性15", width = 15)
    @ApiModelProperty(value = "属性15")
    private String property15;
    /**
     * 属性16
     */
    @Excel(name = "属性16", width = 15)
    @ApiModelProperty(value = "属性16")
    private String property16;
    /**
     * 属性17
     */
    @Excel(name = "属性17", width = 15)
    @ApiModelProperty(value = "属性17")
    private String property17;
    /**
     * 属性18
     */
    @Excel(name = "属性18", width = 15)
    @ApiModelProperty(value = "属性18")
    private String property18;
    /**
     * 属性19
     */
    @Excel(name = "属性19", width = 15)
    @ApiModelProperty(value = "属性19")
    private String property19;
    /**
     * 属性20
     */
    @Excel(name = "属性20", width = 15)
    @ApiModelProperty(value = "属性20")
    private String property20;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    /**
     * 提报单位
     */
    @Excel(name = "提报单位", width = 15)
    @ApiModelProperty(value = "提报单位")
    private String reportingUnit;
    /**
     * 提报时间
     */
    @Excel(name = "提报时间", width = 15)
    @ApiModelProperty(value = "提报时间")
    private String reportingTime;
    /**
     * 物料类型
     */
    @Excel(name = "物料类型", width = 15)
    @ApiModelProperty(value = "物料类型")
    private String materialType;
    /**
     * 产品组
     */
    @Excel(name = "产品组", width = 15)
    @ApiModelProperty(value = "产品组")
    private String productGroup;
    /**
     * 普通项目类别组
     */
    @Excel(name = "普通项目类别组", width = 15)
    @ApiModelProperty(value = "普通项目类别组")
    private String projectCategoryGroup;
    /**
     * 运输组
     */
    @Excel(name = "运输组", width = 15)
    @ApiModelProperty(value = "运输组")
    private String transportGroup;
    /**
     * 质量单位
     */
    @Excel(name = "质量单位", width = 15)
    @ApiModelProperty(value = "质量单位")
    private String massUnit;
    /**
     * 启用批次管理
     */
    @Excel(name = "启用批次管理", width = 15)
    @ApiModelProperty(value = "启用批次管理")
    private String enableBatchManagement;
    /**
     * 数据初始化
     */
    @Excel(name = "数据初始化", width = 15)
    @ApiModelProperty(value = "数据初始化")
    private String dataInitialization;
    /**
     * 旧物料号
     */
    @Excel(name = "旧物料号", width = 15)
    @ApiModelProperty(value = "旧物料号")
    private String oldMaterialNumber;
    /**
     * DG标识参数文件
     */
    @Excel(name = "DG标识参数文件", width = 15)
    @ApiModelProperty(value = "DG标识参数文件")
    private String dgParameterFile;
    /**
     * 最小剩余货架寿命
     */
    @Excel(name = "最小剩余货架寿命", width = 15)
    @ApiModelProperty(value = "最小剩余货架寿命")
    private String minimumShelfLife;
    /**
     * 总货架寿命
     */
    @Excel(name = "总货架寿命", width = 15)
    @ApiModelProperty(value = "总货架寿命")
    private String totalShelfLife;
    /**
     * SLED 的期间标识
     */
    @Excel(name = "SLED 的期间标识", width = 15)
    @ApiModelProperty(value = "SLED 的期间标识")
    private String sledPeriodIdentification;
    /**
     * 存储条件
     */
    @Excel(name = "存储条件", width = 15)
    @ApiModelProperty(value = "存储条件")
    private String storageConditions;
    /**
     * 助记码
     */
    @Excel(name = "助记码", width = 15)
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;
    /**
     * 实体模型版本号
     */
    @Excel(name = "实体模型版本号", width = 15)
    @ApiModelProperty(value = "实体模型版本号")
    private String entityModelVersion;
    /**
     * 类别版本号
     */
    @Excel(name = "类别版本号", width = 15)
    @ApiModelProperty(value = "类别版本号")
    private String categoryVersion;
    /**
     * 备注
     */
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 校验信息
     */
    @Excel(name = "校验信息", width = 15)
    @ApiModelProperty(value = "校验信息")
    private String checkInformation;
    /**
     * 包装物料类型
     */
    @Excel(name = "包装物料类型", width = 15)
    @ApiModelProperty(value = "包装物料类型")
    private String packagingMaterialType;
    /**
     * 流程环节
     */
    @Excel(name = "流程环节", width = 15)
    @ApiModelProperty(value = "流程环节")
    private String processLink;
    /**
     * 处理时间
     */
    @Excel(name = "处理时间", width = 15)
    @ApiModelProperty(value = "处理时间")
    private String processingTime;
    /**
     * 停用标志
     */
    @Excel(name = "停用标志", width = 15)
    @ApiModelProperty(value = "停用标志")
    private String disableSign;
    /**
     * 状态
     */
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 版本
     */
    @Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private String version;
    /**
     * 采购长文本
     */
    @Excel(name = "采购长文本", width = 15)
    @ApiModelProperty(value = "采购长文本")
    private String procurementLongText;
    /**
     * 已处理人
     */
    @Excel(name = "已处理人", width = 15)
    @ApiModelProperty(value = "已处理人")
    private String processedPerson;
    /**
     * 数据类型
     */
    @Excel(name = "数据类型", width = 15)
    @ApiModelProperty(value = "数据类型")
    private String typeOfData;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 最后修改人
     */
    @Excel(name = "最后修改人", width = 15)
    @ApiModelProperty(value = "最后修改人")
    private String lastModifiedBy;
    /**
     * 校验状态
     */
    @Excel(name = "校验状态", width = 15)
    @ApiModelProperty(value = "校验状态")
    private String checkStatus;

    /**
     * 附件状态
     */
    @Excel(name = "附件状态", width = 15)
    @ApiModelProperty(value = "附件状态")
    private String attachmentStatus;
    /**
     * 数据来源
     */
    @Excel(name = "数据来源", width = 15)
    @ApiModelProperty(value = "数据来源")
    private String dataSources;
    /**
     * 同步系统
     */
    @Excel(name = "同步系统", width = 15)
    @ApiModelProperty(value = "同步系统")
    private String synchronizationSystem;
    /**
     * 文件名称
     */
    @Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private String file;

    public Data(String id, String operationOptions, String operationalOpinions, String auditOpinion, String processingStatus, String categoryCode, String serialNumber, String classificationName, String materialCoding, String longDescription, String shortDescription, String nameOfGoods, String basicUnit, String materialGroup, String property1, String property2, String property3, String property4, String property5, String property6, String property7, String property8, String property9, String property10, String property11, String property12, String property13, String property14, String property15, String property16, String property17, String property18, String property19, String property20, String createTime, String reportingUnit, String reportingTime, String materialType, String productGroup, String projectCategoryGroup, String transportGroup, String massUnit, String enableBatchManagement, String dataInitialization, String oldMaterialNumber, String dgParameterFile, String minimumShelfLife, String totalShelfLife, String sledPeriodIdentification, String storageConditions, String mnemonicCode, String entityModelVersion, String categoryVersion, String remarks, String checkInformation, String packagingMaterialType, String processLink, String processingTime, String disableSign, String status, String version, String procurementLongText, String processedPerson, String typeOfData, String createBy, String lastModifiedBy, String checkStatus, String attachmentStatus, String dataSources, String synchronizationSystem, String file) {
        this.id = id;
        this.operationOptions = operationOptions;
        this.operationalOpinions = operationalOpinions;
        this.auditOpinion = auditOpinion;
        this.processingStatus = processingStatus;
        this.categoryCode = categoryCode;
        this.serialNumber = serialNumber;
        this.classificationName = classificationName;
        this.materialCoding = materialCoding;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.nameOfGoods = nameOfGoods;
        this.basicUnit = basicUnit;
        this.materialGroup = materialGroup;
        this.property1 = property1;
        this.property2 = property2;
        this.property3 = property3;
        this.property4 = property4;
        this.property5 = property5;
        this.property6 = property6;
        this.property7 = property7;
        this.property8 = property8;
        this.property9 = property9;
        this.property10 = property10;
        this.property11 = property11;
        this.property12 = property12;
        this.property13 = property13;
        this.property14 = property14;
        this.property15 = property15;
        this.property16 = property16;
        this.property17 = property17;
        this.property18 = property18;
        this.property19 = property19;
        this.property20 = property20;
        this.createTime = createTime;
        this.reportingUnit = reportingUnit;
        this.reportingTime = reportingTime;
        this.materialType = materialType;
        this.productGroup = productGroup;
        this.projectCategoryGroup = projectCategoryGroup;
        this.transportGroup = transportGroup;
        this.massUnit = massUnit;
        this.enableBatchManagement = enableBatchManagement;
        this.dataInitialization = dataInitialization;
        this.oldMaterialNumber = oldMaterialNumber;
        this.dgParameterFile = dgParameterFile;
        this.minimumShelfLife = minimumShelfLife;
        this.totalShelfLife = totalShelfLife;
        this.sledPeriodIdentification = sledPeriodIdentification;
        this.storageConditions = storageConditions;
        this.mnemonicCode = mnemonicCode;
        this.entityModelVersion = entityModelVersion;
        this.categoryVersion = categoryVersion;
        this.remarks = remarks;
        this.checkInformation = checkInformation;
        this.packagingMaterialType = packagingMaterialType;
        this.processLink = processLink;
        this.processingTime = processingTime;
        this.disableSign = disableSign;
        this.status = status;
        this.version = version;
        this.procurementLongText = procurementLongText;
        this.processedPerson = processedPerson;
        this.typeOfData = typeOfData;
        this.createBy = createBy;
        this.lastModifiedBy = lastModifiedBy;
        this.checkStatus = checkStatus;
        this.attachmentStatus = attachmentStatus;
        this.dataSources = dataSources;
        this.synchronizationSystem = synchronizationSystem;
        this.file = file;
    }
}

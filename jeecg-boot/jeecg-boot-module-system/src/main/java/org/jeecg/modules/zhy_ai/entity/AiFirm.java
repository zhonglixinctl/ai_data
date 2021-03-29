package org.jeecg.modules.zhy_ai.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 企业和品牌(AiFirm)实体类
 *
 * @author 钟腾
 * @since 2021-03-24 09:45:59
 */
@Data
@TableName("ai_firm")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ai_firm对象", description="企业和品牌")
public class AiFirm implements Serializable {
    private static final long serialVersionUID = 1L;
    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 公司名称
     */
    @Excel(name = "公司名称", width = 15)
    @ApiModelProperty(value = "公司名称")
    private String name;
    /**
     * 公司编号
     */
    @Excel(name = "公司编号", width = 15)
    @ApiModelProperty(value = "公司编号")
    private String no;
    /**
     * 信用代码
     */
    @Excel(name = "信用代码", width = 15)
    @ApiModelProperty(value = "信用代码")
    private String creditCode;
    /**
     * 法人
     */
    @Excel(name = "法人", width = 15)
    @ApiModelProperty(value = "法人")
    private String operName;
    /**
     * 状态
     */
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 是否找到
     */
    @Excel(name = "是否找到", width = 15)
    @ApiModelProperty(value = "是否找到")
    private String found;

}

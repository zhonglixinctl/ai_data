package org.jeecg.modules.zhy_ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.zhy_ai.entity.AiData;
import org.jeecg.modules.zhy_ai.entity.AiFirm;

import java.util.List;

public interface AIMapper extends BaseMapper<AiData> {
    /**
     * 检验
     *
     * @return
     */
    AiData check(@Param("param") String param, @Param("serialNumber") String serialNumber, @Param("field") String field);

    /**
     * 通过名称查找是否有该企业或品牌
     *
     * @param name
     * @return
     */
    AiFirm selectFirm(@Param("name") String name);

    /**
     * @param value
     * @param materialCoding
     * @param field
     * @param property5
     * @return
     */
    AiData checkPartNum(@Param("value") String value, @Param("materialCoding") String materialCoding, @Param("field") String field, @Param("property5") String property5);

    /**
     * @param value
     * @param materialCoding
     * @param property8
     * @return
     */
    AiData checkDevice(@Param("value") String value, @Param("materialCoding") String materialCoding, @Param("property8") String property8);

    /**
     * @param value
     * @param materialCoding
     * @param property8
     * @return
     */
    AiData checkDeviceNum(@Param("value") String value, @Param("materialCoding") String materialCoding, @Param("property8") String property8);

    /**
     * 通过长描述和短描述查询类别编码
     *
     * @param longDescription
     * @param shortDescription
     */
    String selectCoding(@Param("long") String longDescription, @Param("shor") String shortDescription);

    /**
     * 模糊查询
     * @param param
     * @param serialNumber
     * @param field
     */
    AiData like(@Param("param") String param, @Param("serialNumber") String serialNumber, @Param("field") String field);
}

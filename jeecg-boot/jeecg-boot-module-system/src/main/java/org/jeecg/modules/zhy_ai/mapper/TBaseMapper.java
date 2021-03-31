package org.jeecg.modules.zhy_ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface TBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);

    /**
     * 批量更新 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer alwaysUpdateSomeColumnById(@Param(Constants.ENTITY)Collection<T> entityList);


}

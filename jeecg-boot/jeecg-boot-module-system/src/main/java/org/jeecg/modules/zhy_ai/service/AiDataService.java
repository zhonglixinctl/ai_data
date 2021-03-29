package org.jeecg.modules.zhy_ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.zhy_ai.entity.AiData;

import java.util.List;

public interface AiDataService extends IService<AiData> {
    /**
     * 处理数据
     * @return
     */
    List<AiData> dataProcessing();
}

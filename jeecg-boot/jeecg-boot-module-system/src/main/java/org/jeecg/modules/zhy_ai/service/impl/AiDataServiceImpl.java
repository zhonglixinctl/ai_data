package org.jeecg.modules.zhy_ai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.zhy_ai.entity.AiData;
import org.jeecg.modules.zhy_ai.mapper.AiDataMapper;
import org.jeecg.modules.zhy_ai.service.AiDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiDataServiceImpl extends ServiceImpl<AiDataMapper, AiData> implements AiDataService {


    @Override
    public List<AiData> dataProcessing() {
        return null;
    }
}

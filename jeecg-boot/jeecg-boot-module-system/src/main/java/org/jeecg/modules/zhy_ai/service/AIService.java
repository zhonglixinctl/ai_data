package org.jeecg.modules.zhy_ai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.modules.zhy_ai.entity.AiData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface AIService extends IService<AiData> {
    /**
     * 检查数据
     * @param request
     * @param response
     * @return
     */
    Map<String,Object> checkData(HttpServletRequest request, HttpServletResponse response);

}

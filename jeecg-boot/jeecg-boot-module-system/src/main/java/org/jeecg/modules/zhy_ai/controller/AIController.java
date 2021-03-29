package org.jeecg.modules.zhy_ai.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.zhy_ai.entity.AiData;
import org.jeecg.modules.zhy_ai.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/zhy")
public class AIController extends JeecgController<AiData, AIService> {

    @Autowired
    private AIService aiService;

    /**
     * 导出excel
     *
     * @param request
     * @param aiData
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiData aiData) {
        return super.exportXls(request, aiData, AiData.class, "ai");
    }

    /**
     * 通过excel导入数据,在进行处理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, AiData.class);
        Map<String,Object> map = aiService.checkData(request, response);
        return Result.OK(map);
    }
}

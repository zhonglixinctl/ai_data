package org.jeecg.modules.zhy_ai.controller;

import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.zhy_ai.entity.AiData;
import org.jeecg.modules.zhy_ai.service.AiDataService;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/zhy")
public class AiDataController extends JeecgController<AiData, AiDataService> {

    @Autowired
    private AiDataService service;

    @RequestMapping(value = "/ai", method = RequestMethod.POST)
    public Result<?> dataProcessing() {
        AiData aiData = new AiData();


        return Result.OK();
    }

}

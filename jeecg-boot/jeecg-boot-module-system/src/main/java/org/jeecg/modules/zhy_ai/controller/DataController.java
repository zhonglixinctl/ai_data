package org.jeecg.modules.zhy_ai.controller;

import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.zhy_ai.entity.Data;
import org.jeecg.modules.zhy_ai.entity.Data1;
import org.jeecg.modules.zhy_ai.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/zhy")
public class DataController extends JeecgController<Data, DataService> {

    @Autowired
    private DataService service;

    /**
     * 导出excel
     *
     * @param request
     * @param data
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Data data,String fileName) {
       return service.exportXls(request,data,Data.class,"ai",fileName);
//        return super.exportXls(request, data, Data1.class, "ai");
    }

}

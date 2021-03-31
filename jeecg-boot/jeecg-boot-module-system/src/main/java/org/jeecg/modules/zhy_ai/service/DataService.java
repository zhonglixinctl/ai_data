package org.jeecg.modules.zhy_ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.modules.zhy_ai.entity.Data;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface DataService extends IService<Data> {

    ModelAndView exportXls(HttpServletRequest request, Data object, Class<Data> clazz, String title, String fileName);
}

package org.jeecg.modules.zhy_ai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.zhy_ai.entity.Data;
import org.jeecg.modules.zhy_ai.entity.Data1;
import org.jeecg.modules.zhy_ai.mapper.DataMapper;
import org.jeecg.modules.zhy_ai.service.DataService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Value("${jeecg.path.upload}")
    private String upLoadPath;

    @Override
    public ModelAndView exportXls(HttpServletRequest request, Data object, Class<Data> clazz, String title, String fileName) {
        // Step.1 组装查询条件
        QueryWrapper<Data> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        queryWrapper.eq("file",fileName);

        // Step.2 获取导出数据
        List<Data> exportList = dataMapper.selectList(queryWrapper);
//        List<Data> exportList = null;

        // 过滤选中数据
//        String selections = request.getParameter("selections");
//        if (oConvertUtils.isNotEmpty(selections)) {
//            List<String> selectionList = Arrays.asList(selections.split(","));
//            exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
//        } else {
//            exportList = pageList;
//        }

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        //update-begin--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置--------------------
        ExportParams  exportParams=new ExportParams(title + "报表", "导出人: " /*+ sysUser.getRealname()*/, title);
        System.out.println("导出的文件----->>");
        exportParams.setImageBasePath(upLoadPath);
        //update-end--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置----------------------
        mv.addObject(NormalExcelConstants.PARAMS,exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }
}

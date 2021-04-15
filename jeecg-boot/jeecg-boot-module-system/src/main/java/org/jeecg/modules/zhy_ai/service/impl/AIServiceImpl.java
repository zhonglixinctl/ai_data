package org.jeecg.modules.zhy_ai.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jeecg.modules.zhy_ai.entity.AiData;
import org.jeecg.modules.zhy_ai.entity.AiFirm;
import org.jeecg.modules.zhy_ai.entity.Data;
import org.jeecg.modules.zhy_ai.entity.Data1;
import org.jeecg.modules.zhy_ai.mapper.AIFirmMapper;
import org.jeecg.modules.zhy_ai.mapper.AIMapper;
import org.jeecg.modules.zhy_ai.service.AIService;
import org.jeecg.modules.zhy_ai.service.DataService;
import org.jeecg.modules.zhy_ai.util.AIUtil;
import org.jeecg.modules.zhy_ai.util.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Slf4j
public class AIServiceImpl extends ServiceImpl<AIMapper, AiData> implements AIService {

    @Autowired
    private AIMapper aiMapper;
    @Autowired
    private AIFirmMapper aiFirmMapper;
    @Autowired
    private DataService dataService;

    /**
     * 检查数据
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Map<String, Object> checkData(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> file1 = multipartRequest.getMultiFileMap().get("file");
        MultipartFile multipartFile = file1.get(0);
        String name = multipartFile.getOriginalFilename();
        System.out.println("导入的文件名为----->>" + name);
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        //构建返回的对象
        List<String> msgList = new ArrayList<>();
        List<List<String>> data = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();

            try {
                InputStream inputStream = file.getInputStream();
                // 解析不同后缀名的表格 使用不同的对象, Workbook是父类
//                HSSFWorkbook sheets = new HSSFWorkbook(inputStream);
//                XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
                Workbook sheets = WorkbookFactory.create(inputStream);
                //获取第一张表
//                XSSFSheet sheet = sheets.getSheetAt(0);
//                HSSFSheet sheet = sheets.getSheetAt(0);
                Sheet sheet = sheets.getSheetAt(0);
                //获取表格最后一条数据的下标
                int lastRowNum = sheet.getLastRowNum();
                //取出每一条
                for (int i = 0; i <= lastRowNum; i++) {
                    // 获取行
//                    XSSFRow row = sheet.getRow(i);
//                    HSSFRow row = sheet.getRow(i);
                    Row row = sheet.getRow(i);
                    List<String> rowList = new ArrayList<>();
                    for (int j = 0; j < 70; j++) {
                        if (row == null) {
                            break;
                        }
                        rowList.add("null".equals(String.valueOf(row.getCell(j))) ? "" : String.valueOf(row.getCell(j)));
                    }
                    list.add(rowList);
//                    //取出每个字段
//                    String row1 = row.getCell(0).toString();
//                    String row2 = row.getCell(1).toString();
                }
//                System.out.println(list.get(0));
                Map<String, Integer> map = null;
                Map<Integer, String> maps = null;
                for (int i = 0; i < list.size(); i++) {
                    StringBuilder msg = new StringBuilder();
                    List<String> list1 = list.get(i);
                    if (i == 0) {
                        // 处理表头信息 返回以名称为key,下标为value
                        map = parseHeader(list1);
                        // 处理表头信息 返回以标为key,名称为value
                        maps = parseHeaders(list1);
                    } else {
                        if (list1.size() == 0) {
                            break;
                        }
                        String serialNumber = list1.get(map.get("类别编码"));
                        switch (serialNumber) {
                            case "A280101":
                                for (int index = 0; index < list1.size(); index++) {
                                    // 判断不能为空的属性值必须不为空
                                    // 此行的表头名称
                                    String hName = maps.get(index);
                                    // 此行的值
                                    String value = list1.get(index);
                                    if ("物资名称".equals(hName) || "属性5".equals(hName)) {
                                        if (StringUtils.isBlank(value) || "null".equals(value)) {
                                            switch (hName) {
                                                case "物资名称":
                                                    msg.append("物资名称不能为空。");
                                                    break;
                                                case "属性5":
                                                    msg.append("属性5制造厂或品牌不能为空。");
                                                    break;
                                            }
                                        }
                                    }
                                    if (StringUtils.isNotBlank(value)) {
                                        if (!"null".equals(value)) {
                                            String longDescription = list1.get(map.get("长描述"));
                                            String shortDescription = list1.get(map.get("短描述"));
                                            String serialNum = aiMapper.selectCoding(longDescription, shortDescription);
                                            if (!serialNumber.equals(serialNum) && serialNum != null) {
                                                msg.append("归类错误,按照制造厂商协同规范，应归入" + serialNum);
                                                break;
                                            }
                                            switch (hName) {
                                                case "类别名称":
                                                    if (!value.equals("钻井、修井绞车配件")) {
                                                        msg.append("类别名称'").append(value).append("'错误,物料编码'A280101'的类别名称应为'钻井、修井绞车配件'。");
                                                    }
                                                    break;
                                                case "物资名称":
//                                                    AiData goods = aiMapper.check(value, serialNumber, "name_of_goods");
//                                                    if (goods == null) {
//                                                        aiMapper.like(value,serialNumber,"name_of_goods");
//                                                    }
                                                    if (AIUtil.hasNum(value)) {
                                                        msg.append("物资名称'").append(value).append("'错误,请按照规范标准名称，采用中英文简写,不能使用数字。");
                                                    }
                                                    if ("TIME DELAY VALVES".equals(value)) {
                                                        msg.append("属性值错误，物资名称应规范为TIME DELAY VALVE。");
                                                    }
                                                    if ("主板".equals(value) || "显示器".equals(value)) {
                                                        msg.append("属性值错误,根据制造厂商协同规范要求，物资名称应填写英文名称。");
                                                    }
                                                    break;
                                                case "物料组":
                                                    if (!value.equals(serialNumber)) {
                                                        msg.append("物料组'").append(value).append("'错误,因属于A280101。");
                                                    }
                                                    break;
                                                case "属性1":
                                                    // 从数据库中查找是否有该型号
                                                    // 校验属性1型号
                                                    AiData check1 = aiMapper.check(value, serialNumber, "property1");
                                                    if (check1 == null) {
                                                        msg.append("请检查属性1型号'").append(value).append("'是否正确。");
                                                    }
                                                    break;
                                                case "属性2":
                                                    // 校验属性2规格
                                                    AiData check2 = aiMapper.check(value, serialNumber, "property2");
                                                    if (check2 == null) {
                                                        msg.append("请检查属性2规格'").append(value).append("'是否正确。");
                                                    }
                                                    break;
                                                case "属性3":
                                                    // 校验属性3技术参数
                                                    AiData check3 = aiMapper.check(value, serialNumber, "property3");
                                                    if (check3 == null) {
                                                        msg.append("请检查属性3技术参数'").append(value).append("'是否正确。");
                                                    }
                                                    break;
                                                case "属性4":

                                                    break;
                                                case "属性5":
                                                    // 校验属性5制造厂或品牌
                                                    AiFirm firm = hasFirm(value);
                                                    String found = "不存在";
                                                    if (firm == null) {
                                                        AiData check5 = aiMapper.check(value, serialNumber, "property5");
                                                        if (check5 == null) {
                                                            String jsonString = MainApp.findCompany(value);
                                                            log.info("=====调用企查查接口查询======" + new Date());
                                                            // parse String to JSONObject
                                                            JSONObject jsonObject = JSONObject.parseObject(jsonString);
                                                            log.info("=====调用企查查接口查询返回信息======" + jsonString);
                                                            // parse JSONObject to JSONArray
                                                            JSONArray result = jsonObject.getJSONArray("Result");
                                                            boolean flag = false;
                                                            if (result != null) {
                                                                for (int a = 0; a < result.size(); a++) {
                                                                    JSONObject json = result.getJSONObject(a);
                                                                    // 将查询出的所有企业存到数据库
                                                                    firm = new AiFirm();
                                                                    firm.setName((String) json.get("Name"));
                                                                    firm.setCreditCode((String) json.get("CreditCode"));
                                                                    firm.setStatus((String) json.get("Status"));
                                                                    firm.setNo((String) json.get("No"));
                                                                    firm.setOperName((String) json.get("OperName"));
                                                                    addFirm(firm);
                                                                    if (value.equals(json.get("Name"))) {
                                                                        flag = true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if (!flag) {
                                                                msg.append("属性值错误，请确认厂家名称或提供厂家资料。");
                                                                firm = new AiFirm();
                                                                firm.setName(value);
                                                                firm.setFound(found);
                                                                addFirm(firm);
                                                            }
                                                        } else {
                                                            firm = new AiFirm();
                                                            firm.setName(value);
                                                            firm.setFound("存在");
                                                            addFirm(firm);
                                                        }
                                                    } else if (found.equals(firm.getFound())) {
                                                        msg.append("属性值错误，请确认厂家名称或提供厂家资料。");
                                                    }
                                                    if ("TIMRC".equals(value)) {
                                                        msg.append("属性值错误，请确认厂家名称或提供厂家资料。");
                                                    }
                                                    break;
                                                case "属性6":
                                                    // 厂家配件号
//                                                AiData check6 = aiMapper.checkPartNum(value, materialCoding,"property6",list1.get(map.get("属性5")));
                                                    AiData check6 = aiMapper.check(value, serialNumber, "property6");
                                                    if (check6 == null) {
                                                        msg.append("请检查属性6'").append(value).append("'是否正确，对应厂家没有找到该型号。");
                                                    }
                                                    break;
                                                case "属性7":
                                                    // 厂家旧配件号
//                                                AiData check7 = aiMapper.checkPartNum(value, materialCoding,"property7",list1.get(map.get("属性5")));
                                                    AiData check7 = aiMapper.check(value, serialNumber, "property7");
                                                    if (check7 == null) {
                                                        msg.append("请检查属性7'").append(value).append("'是否正确，对应厂家没有找到该配件号。");
                                                    }
                                                    break;
                                                case "属性8":
                                                    // 设备名称
                                                    break;
                                                case "属性9":
                                                    // 设备型号
                                                    AiData check9 = aiMapper.checkDevice(value, serialNumber, list1.get(map.get("属性8")));
                                                    if (check9 == null) {
                                                        msg.append("请检查属性9'").append(value).append("'是否正确，对应设备没有找到该型号。");
                                                    }
                                                    break;
                                                case "属性10":
                                                    // 设备序列号
                                                    AiData check10 = aiMapper.checkDeviceNum(value, serialNumber, list1.get(map.get("属性8")));
                                                    if (check10 == null) {
                                                        msg.append("请检查属性9'").append(value).append("'是否正确，对应设备没有找到该序列号。");
                                                    }
                                                    break;
                                                case "属性11":
                                                    break;
                                                case "属性12":
                                                    break;
                                                case "属性13":
                                                    break;
                                                case "属性14":
                                                    break;
                                            }
                                        }
                                    }
                                }
                                break;
                            case "null":
                                break;
                            default:
                                msg.append("物料编码暂时未提供!!");
                                System.out.println("物料编码暂时未提供!!");
                                break;
                        }
                        if ("".equals(msg.toString())) {
                            list1.set(0, "审核通过");
                            list1.set(1, msg.toString());
                        } else {
                            list1.set(0, "拒绝至提交人");
                            list1.set(1, msg.toString());
                        }
                    }
                    msgList.add(msg.toString());
                    data.add(list1);
                }
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msgList", msgList);
        map.put("data", data);
        addData(data, name);
        return map;
    }

    /**
     * 解析表头
     *
     * @param list
     * @return Map<String, Integer>
     */
    private Map<String, Integer> parseHeader(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            map.put(name, i);
        }
        return map;
    }

    /**
     * 解析表头
     *
     * @param list
     * @return Map<Integer, String>
     */
    private Map<Integer, String> parseHeaders(List<String> list) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            map.put(i, name);
        }
        return map;
    }

    /**
     * 判断表中是否有该企业
     *
     * @param name
     * @return
     */
    private AiFirm hasFirm(String name) {
        return aiMapper.selectFirm(name);
    }

    /**
     * 添加企业
     *
     * @param firm
     */
    private void addFirm(AiFirm firm) {
        aiFirmMapper.insert(firm);
    }

    /**
     * 修改企业
     *
     * @param firm
     */
    private void updateFirm(AiFirm firm) {
        aiFirmMapper.updateById(firm);
    }

    /**
     * 将数据添加到data表,进行导出
     *
     * @param list
     */
    @Transactional
    public void addData(List<List<String>> list, String fileName) {
//        List dataList = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                List<String> list1 = list.get(i);
                Data data = new Data(null, list1.get(0), list1.get(1), list1.get(2), list1.get(3), list1.get(4), list1.get(5), list1.get(6), list1.get(7),
                        list1.get(8), list1.get(9), list1.get(10), list1.get(11), list1.get(12), list1.get(13), list1.get(14), list1.get(15), list1.get(16), list1.get(17),
                        list1.get(18), list1.get(19), list1.get(20), list1.get(21), list1.get(22), list1.get(23), list1.get(24), list1.get(25), list1.get(26), "", "", "", "", "", "", list1.get(27),
                        list1.get(28), list1.get(29), list1.get(30), list1.get(31), list1.get(32), list1.get(33), list1.get(34), list1.get(35), list1.get(36), list1.get(37),
                        list1.get(38), list1.get(39), list1.get(40), list1.get(41), list1.get(42), list1.get(43), list1.get(44), list1.get(45), list1.get(46), list1.get(47),
                        list1.get(48), list1.get(49), list1.get(50), list1.get(51), list1.get(52), list1.get(53), list1.get(54), list1.get(55), list1.get(56), list1.get(57),
                        list1.get(58), list1.get(59), list1.get(60), list1.get(61), list1.get(62), fileName);
//                dataList.add(data);
                dataService.save(data);
            }
//            dataService.saveBatch(dataList);
        }
    }
}

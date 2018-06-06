package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.AwardInof2human;
import com.yukoon.turntablegames.entities.Excel;
import com.yukoon.turntablegames.utils.ExcelUtil;
import org.apache.el.parser.ParseException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class DownloadService {
    @Autowired
    private AwardInfoService awardInfoService;

    public XSSFWorkbook exportExcel(List<AwardInof2human> list) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        List<Excel> excels = new ArrayList<>();
        Map<Integer,List<Excel>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        //设置标题栏
        excels.add(new Excel("用户ID","id",0));
        excels.add(new Excel("用户名","username",0));
        excels.add(new Excel("活动名","act_name",0));
        excels.add(new Excel("奖品名","reward_name",0));
        excels.add(new Excel("状态","is_Cash",0));
        excels.add(new Excel("中奖时间","winning_date",0));
        excels.add(new Excel("兑换日期","cashing_date",0));
        map.put(0,excels);
        String excelName = list.get(0).getAct_name()+"统计";
        xssfWorkbook = ExcelUtil.createExcelFile(AwardInof2human.class,list,map,excelName);
        return xssfWorkbook;
    }

    //根据活动导出中奖名单
    public XSSFWorkbook exportExcelByActid(Integer act_id) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        List<AwardInof2human> list = awardInfoService.findAllByActid(act_id);
        return exportExcel(list);
    }

    //导出具体用户的中间名单
    public XSSFWorkbook exportExcelByUserid(Integer user_id) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        List<AwardInof2human> list = awardInfoService.findAllByUserid(user_id);
        return exportExcel(list);
    }
}

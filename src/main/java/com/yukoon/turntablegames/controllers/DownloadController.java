package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.services.DownloadService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DownloadController {
	@Autowired
	private DownloadService downloadService;

	//批量导出整个活动的中奖情况
	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@ResponseBody
	@GetMapping("/exportallrewardinfo/{act_id}")
	public void exportallrewardinfo(@PathVariable("act_id")Integer act_id, HttpServletRequest request, HttpServletResponse response) {
		response.reset(); //清除buffer缓存
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		response.setHeader("Content-Disposition", "attachment;filename="+sdf.format(new Date())+".xlsx");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		XSSFWorkbook workbook=null;
		try {
			workbook = downloadService.exportExcelByActid(act_id);
			OutputStream out = response.getOutputStream();
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.flush();
			workbook.write(bout);
			bout.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
		}
		System.out.println("成功");
	}

	//导出某一用户的中奖情况
	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@ResponseBody
	@GetMapping("/exportuserrewardinfo/{user_id}")
	public void exportuserrewardinfo(@PathVariable("user_id")Integer user_id, HttpServletRequest request, HttpServletResponse response) {
		response.reset(); //清除buffer缓存
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		response.setHeader("Content-Disposition", "attachment;filename="+sdf.format(new Date())+".xlsx");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		XSSFWorkbook workbook=null;
		try {
			workbook = downloadService.exportExcelByUserid(user_id);
			OutputStream out = response.getOutputStream();
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.flush();
			workbook.write(bout);
			bout.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
		}
		System.out.println("成功");
	}


}

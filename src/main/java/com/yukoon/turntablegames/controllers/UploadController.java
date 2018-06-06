package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.config.PathConfig;
import com.yukoon.turntablegames.services.ExcelUploadService;
import com.yukoon.turntablegames.utils.FileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    private ExcelUploadService excelUploadService;
    @Autowired
    private PathConfig pathConfig;

    //后台前往奖品转盘图片上传
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/touploadimg/{act_id}")
    public String toUploadImg(@PathVariable("act_id") Integer act_id, Map<String,Object> map, String uploadMsg) {
        if (uploadMsg !=null) {
            System.out.println(uploadMsg);
            map.put("uploadMsg",uploadMsg);
        }
        map.put("act_id",act_id);
        return "background/reward_picture_upload";
    }

    //后台奖品转盘图片上传
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/imgupload")
    public String upload(@RequestParam("pic")MultipartFile pic, HttpServletRequest request
            , Integer act_id,RedirectAttributes attributes){
        String filePath = pathConfig.getImagespath();
        String fileName = pic.getOriginalFilename();
        String uploadMsg = "图片上传成功!";
        if (!FileUtil.isImg(fileName)){
            uploadMsg = "该文件不是图片格式,请重新上传!";
            attributes.addFlashAttribute("uploadMsg",uploadMsg);
            return "redirect:/touploadimg/"+act_id;
        }
        //重命名文件
        fileName = "lottery"+act_id+".jpg";
        try {
            FileUtil.uploadFile(pic.getBytes(),filePath,fileName);
        }catch (Exception e) {
            uploadMsg = "图片上传出现错误,请重新上传!";
			attributes.addFlashAttribute("uploadMsg",uploadMsg);
			return "redirect:/touploadimg/"+act_id;
        }
		attributes.addFlashAttribute("uploadMsg",uploadMsg);
		return "redirect:/touploadimg/"+act_id;
    }

    //后台前往客户Excel上传
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/touploadexcel/{act_id}")
    public String toUploadExcel(@PathVariable("act_id") Integer act_id, Map<String,Object> map,HttpServletRequest request) {
		Map<String,?> map1 = RequestContextUtils.getInputFlashMap(request);
        String uploadMsg = null;
		if (map1 != null) {
            uploadMsg = map1.get("uploadMsg").toString();
        }
		if (uploadMsg !=null) {
            map.put("uploadMsg",uploadMsg);
        }
        map.put("act_id",act_id);
        return "background/user_excel_upload";
    }

    //后台进行客户Excel上传
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/excelupload")
    public String uploadExcel(@RequestParam("excel")MultipartFile excel,Integer act_id,RedirectAttributes attributes) {
        try {
            InputStream in = excel.getInputStream();
            excelUploadService.importUserExcel(in,excel,act_id);
            in.close();
        }catch (Exception e){
        	e.printStackTrace();
			System.out.println("失败");
			attributes.addFlashAttribute("uploadMsg","上传失败，请重试！");
			return "redirect:/touploadexcel/"+act_id;
        }
		System.out.println("成功");
        attributes.addFlashAttribute("uploadMsg","上传成功！");
        return "redirect:/touploadexcel/"+act_id;
    }

}

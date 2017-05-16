package com.xsx.ncd.handler;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceType;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.DeviceTypeRepository;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class DeviceTypeHandler {
	
	@Autowired DeviceTypeRepository deviceTypeRepository;
	
	@ResponseBody
	@RequestMapping(value="/SaveDeviceType")
	public DeviceType saveDeviceTypeHandler(@RequestBody DeviceType deviceType) {
		return deviceTypeRepository.save(deviceType);
	}
	
	@ResponseBody
	@RequestMapping(value="/DeleteDeviceType")
	public Boolean deleteDeviceTypeHandler(@RequestBody DeviceType deviceType) {
		 
		deviceTypeRepository.delete(deviceType);
		 
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryAllDeviceType")
	public List<DeviceType> queryAllDeviceTypeHandler() {
		 
		return deviceTypeRepository.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryAllDeviceIcoPath")
	public List<String> queryAllDeviceIcoPathHandler() {
		 
		return deviceTypeRepository.findAllIcoPath();
	}
	
	@ResponseBody
	@RequestMapping("/SaveDeviceTypeAndIco")
	public String saveDeviceTypeAndIcoHandler(@RequestParam("deviceType")String deviceTypeInfo,
			@RequestParam("ico") CommonsMultipartFile offIcoFile){

		try {
			ObjectMapper mapper = new ObjectMapper();
			DeviceType deviceType = mapper.readValue(deviceTypeInfo, DeviceType.class);
			
			//查找是否已存在相同名字和型号的设备类型
			if(deviceTypeRepository.findByNameAndModel(deviceType.getName(), deviceType.getModel()) != null)
				return "Error, This kind of Device already exists!";
			
			String offPath="/var/NCDPOCT/DeviceIco/" + offIcoFile.getOriginalFilename();
			
			File direct = new File("/var/NCDPOCT/");
			if(!direct.exists())
				direct.mkdir();
			
			File direct2 = new File("/var/NCDPOCT/DeviceIco/");
			if(!direct2.exists())
				direct2.mkdir();

			File newFile1=new File(offPath);
			Thumbnails.of(offIcoFile.getInputStream())
			.width(100)
			.toFile(newFile1);
			//offIcoFile.transferTo(newFile1);
			
			deviceType.setIcon(offPath);
			deviceTypeRepository.save(deviceType);
			
			return "Success!";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Fail!"+ e.getMessage();
		}
	}
	
	@RequestMapping("/DownloadDeviceIco")
	public void  downloadDeviceIcoHandler(@RequestBody String icoUrl, 
            HttpServletResponse response) throws IOException{

        BufferedInputStream bis = null; 
        BufferedOutputStream bos = null; 

        //获取下载文件露肩
        String downLoadPath = icoUrl; 
        
        //待下载文件
        File file = new File(downLoadPath);
        
        //获取文件的长度
        long fileLength = file.length(); 
 
        //设置文件输出类型
        response.setContentType("application/octet-stream"); 
        response.setHeader("Content-disposition", "attachment; filename="+icoUrl);
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileLength)); 
        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(file)); 
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream()); 
        byte[] buff = new byte[2048]; 
        int bytesRead; 
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) { 
        	bos.write(buff, 0, bytesRead); 
        }
        //关闭流
        bis.close();
        bos.close();
	}
}

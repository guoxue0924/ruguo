package com.foundation.modules.datasync.organization.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.web.BaseController;
import com.foundation.modules.datasync.organization.entity.OrganizationInfo;
import com.foundation.modules.datasync.organization.service.OrganizationSyncService;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value="organization/sync")
public class OrganizationSyncController extends BaseController{
	
	@Autowired
	private ResponseHelper responseHelper;
	
	@Autowired
	private OrganizationSyncService organizationService;
	
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(新增)
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public ResponseHelper addOrganization(@RequestBody String param){
		
		// 转换参数格式
		JSONArray objArray = JSONArray.parseArray(param);
		
		// 数组长度大于0
		if(objArray.size() > 0){
			
			// 构造参数list
			List<OrganizationInfo> organizationList = Lists.newArrayList();
			
			// 循环入参
			for(int i=0;i<objArray.size();i++){
				
				// 获取当前循环对象
				JSONObject obj = objArray.getJSONObject(i);
				
				// 当前入参对象中有key为"organization"数据
				if(obj.containsKey("organization")){
					
					// 提取参数
					JSONObject organizationObj = obj.getJSONObject("organization");
					
					// organization参数非空
					if(!organizationObj.isEmpty()){
						
						try{
							
							// jsonobject 转为 javabean
							OrganizationInfo o = organizationObj.toJavaObject(OrganizationInfo.class);
							
							// 向list中添加数据
							organizationList.add(o);
							
						}catch(Exception e){
							
							// 打印异常信息
							e.printStackTrace();
							
							// 记录日志
							logger.error("新增机构时出现异常,json映射javaBean时报错", e);
							
							// 设置返回值
							responseHelper.setFail("新增机构失败,入参中不存在机构数据",e.getMessage());
							
							// 返回结果
							return responseHelper;
							
						}
						
					// organization参数为空
					}else{
						
						// 记录日志
						logger.error("新增机构时出现异常,入参中不存在机构数据", obj);
						
						// 设置返回值
						responseHelper.setFail("新增机构失败,入参中不存在机构数据",obj);
						
						// 返回结果
						return responseHelper;
						
					}
					
				// 当前入参对象中不包含"organization"数据
				}else{
					
					// 记录日志
					logger.error("新增机构失败,入参中不存在机构数据", obj);
					
					// 设置返回值
					responseHelper.setFail("新增机构失败,入参中不存在机构数据",obj);
					
					// 返回结果
					return responseHelper;
					
				}
			}
			
			try{
				
				// 判断参数
				if(organizationList.size() > 0){
					
					// 调用新增方法
					organizationService.addOrganization(organizationList);
					
					// 设置返回值
					responseHelper.setSuccess("新增机构成功");
					
				}else{
					
					// 设置返回值
					responseHelper.setFail("无法新增数据", param);
				}
				
			}catch(Exception e){
				
				// 打印异常信息
				e.printStackTrace();
				
				// 记录日志
				logger.error("新增机构时出现异常,新增机构失败", e);
				
				// 设置返回值
				responseHelper.setFail("新增机构时出现异常,新增机构失败",e.getMessage());
				
				// 返回结果
				return responseHelper;
			}	
		
		// 入参为空
		}else{
			
			// 记录日志
			logger.error("新增机构失败,入参为空", param);
			
			// 设置返回参数
			responseHelper.setFail("新增机构失败,入参为空", param);
		}
		
		// 返回结果
		return responseHelper;
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(修改)
	 */
	@RequestMapping(value="modify")
	@ResponseBody
	public ResponseHelper modifyOrganization(@RequestBody String param){
		
		// 转换参数格式
		JSONArray objArray = JSONArray.parseArray(param);
		
		// 判断入参长度
		if(objArray.size() > 0){
			
			// 构造参数list
			List<OrganizationInfo> organizationList = Lists.newArrayList();
			
			// 循环入参
			for(int i=0;i<objArray.size();i++){
			
				// 获取当前循环对象
				JSONObject obj = objArray.getJSONObject(i);
				
				// 当前入参对象中有key为"organization"数据
				if(obj.containsKey("organization")){
					
					// 提取参数
					JSONObject organizationObj = obj.getJSONObject("organization");
					
					// organization参数非空
					if(!organizationObj.isEmpty()){
						
						try{
							
							// jsonobject 转为 javabean
							OrganizationInfo o = organizationObj.toJavaObject(OrganizationInfo.class);
							
							// 向list中添加数据
							organizationList.add(o);
							
						}catch(Exception e){
							
							// 打印异常信息
							e.printStackTrace();
							
							// 记录日志
							logger.error("修改机构时出现异常,json映射javaBean时报错", e);
							
							// 设置返回值
							responseHelper.setFail("修改机构失败,入参中不存在机构数据",e.getMessage());
							
							// 返回结果
							return responseHelper;
							
						}
					
					// organization参数为空
					}else{
						
						// 记录日志
						logger.error("修改机构失败,入参中不存在机构数据", obj);
						
						// 设置返回值
						responseHelper.setFail("修改机构失败,入参中不存在机构数据",obj);
						
						// 返回结果
						return responseHelper;
						
					}
					
				// 当前入参对象中不包含"organization"数据
				}else{
					
					// 记录日志
					logger.error("修改机构失败,入参中不存在机构数据", obj);
					
					// 设置返回值
					responseHelper.setFail("修改机构失败,入参中不存在机构数据",obj);
					
					// 返回结果
					return responseHelper;
				}
			}
			
			try{
				
				// 判断参数
				if(organizationList.size() > 0){
					
					// 调用新增方法
					organizationService.modifyOrganization(organizationList);
					
					// 设置返回值
					responseHelper.setSuccess("修改机构成功");
					
				}else{
					
					// 设置返回值
					responseHelper.setFail("无法更新数据", param);
				}
				
			}catch(Exception e){
				
				// 打印异常信息
				e.printStackTrace();
				
				// 记录日志
				logger.error("修改机构时出现异常,修改机构失败", e);
				
				// 设置返回值
				responseHelper.setFail("修改机构时出现异常,修改机构失败",e.getMessage());
				
				// 返回结果
				return responseHelper;
				
			}
			
		// 入参为空
		}else{
			
			// 记录日志
			logger.error("修改机构失败,入参为空", param);
			
			// 设置返回参数
			responseHelper.setFail("修改机构失败,入参为空", param);
			
		}
		
		// 返回结果
		return responseHelper;
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(删除)
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public ResponseHelper deleteOrganization(@RequestBody String param){
		
		// 转换参数格式
		JSONArray objArray = JSONArray.parseArray(param);
		
		// 判断入参长度
		if(objArray.size() > 0){
			
			// 需要进行物理删除的数据集
			List<OrganizationInfo> physicalList = Lists.newArrayList();
			
			// 需要进行逻辑删除的数据集
			List<OrganizationInfo> logicList = Lists.newArrayList();
			
			
			// 循环入参
			for(int i=0;i<objArray.size();i++){
				
				// 获取当前循环对象
				JSONObject obj = objArray.getJSONObject(i);
				
				// 入参中包含type字段
				if(obj.containsKey("type")){
					
					// 入参中包含organization字段
					if(obj.containsKey("organization")){
						
						// 提取机构数据
						JSONObject organizationObj = obj.getJSONObject("organization");
						
						// organization参数非空
						if(!organizationObj.isEmpty()){
							
							try{
								
								// 参数类型转换
								OrganizationInfo o = organizationObj.toJavaObject(OrganizationInfo.class);
								
								// 若为物理删除
								if(obj.getString("type").equals("physical")){
									
									physicalList.add(o);
									
								// 若为逻辑删除
								}else{
									
									logicList.add(o);
									
								}
								
							}catch(Exception e){
								
								// 打印异常信息
								e.printStackTrace();
								
								// 记录日志
								logger.error("删除机构时出现异常,json映射javaBean时报错", e);
								
								// 设置返回信息
								responseHelper.setFail("删除机构时出现异常,json映射javaBean时报错", e.getMessage());
								
								// 返回结果
								return responseHelper;
							}
						
						// organization参数为空	
						}else{
							
							// 记录日志
							logger.error("删除机构失败,入参中不存在机构数据", obj);
							
							// 设置返回参数
							responseHelper.setFail("删除机构失败,入参中不存在机构数据", obj);
							
							// 返回结果
							return responseHelper;
							
						}
						
					// 入参中不包含organization字段
					}else{
						
						// 记录日志
						logger.error("删除机构失败,入参中不存在机构数据", obj);
						
						// 设置返回参数
						responseHelper.setFail("删除机构失败,入参中不存在机构数据", obj);
						
						// 返回结果
						return responseHelper;
						
					}
					
				// 入参中不包含type字段
				}else{
					
					// 记录日志
					logger.error("删除机构失败,入参不存在type参数", obj);
					
					// 设置返回参数
					responseHelper.setFail("删除机构失败,入参不存在type参数", obj);
					
					// 返回结果
					return responseHelper;
					
				}
			}
			
			try{
				
				/// 当物理删除数据集存在数据时
				if(physicalList.size() > 0){
					
					organizationService.deleteOrganization(physicalList);
					responseHelper.setSuccess("删除机构成功");
					
				// 当逻辑删除数据集存在数据时
				}else if(logicList.size() > 0){
					
					organizationService.deleteOrganization(logicList);
					responseHelper.setSuccess("删除机构成功");
					
				}else{
					
					// 设置返回值
					responseHelper.setFail("无法删除数据", param);
				}
				
			}catch(Exception e){
				
				// 打印异常信息
				e.printStackTrace();
				
				// 记录日志
				logger.error("删除机构时出现异常,删除机构失败", e);
				
				// 设置返回值
				responseHelper.setFail("删除机构时出现异常,删除机构失败",e.getMessage());
				
				// 返回结果
				return responseHelper;
				
			}
			
		
		// 入参为空
		}else{
			
			// 记录日志
			logger.error("删除机构失败,入参为空", param);
			
			// 设置返回参数
			responseHelper.setFail("删除机构失败,入参为空", param);
			
		}
		
		// 返回结果
		return responseHelper;
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-18
	 * desc:机构同步(机构启用)
	 */
	@RequestMapping(value="enable")
	@ResponseBody
	public ResponseHelper enableOrganization(@RequestBody String param){
		
		// 入参格式转换
		JSONArray objArray = JSONArray.parseArray(param);
		
		// 判断入参长度
		if(objArray.size() > 0){
			
			// 构造参数
			List<OrganizationInfo> organizationList = Lists.newArrayList();
			
			// 循环入参
			for(int i=0;i<objArray.size();i++){
				
				// 提取当前循环对象
				JSONObject obj = objArray.getJSONObject(i);
				
				// 判断当前对象是否包含"organization"数据
				if(obj.containsKey("organization")){
					
					// 提取"organization"数据
					JSONObject organizationObj = obj.getJSONObject("organization");
					
					// organization数据不为空
					if(!organizationObj.isEmpty()){
						
						try{
						
							// json转java
							OrganizationInfo o = organizationObj.toJavaObject(OrganizationInfo.class);
							
							// 设置机构启用状态(1为启用) 
							o.setIsEnable("1");
							
							// 向list中添加数据
							organizationList.add(o);
							
						}catch(Exception e){
							
							// 记录日志
							logger.error("停用机构时出现异常,json映射javaBean时报错", e);
							
							// 设置返回信息
							responseHelper.setFail("停用机构时出现异常,json映射javaBean时报错", e.getMessage());
							
							// 返回结果
							return responseHelper;
							
						}
						
					// organization数据为空
					}else{
						
						// 记录日志
						logger.error("启用机构失败,入参中不存在机构数据", obj);
						
						// 设置返回信息
						responseHelper.setFail("启用机构失败,入参中不存在机构数据", obj);
						
						// 返回结果
						return responseHelper;
						
					}
					
				// 当前对象不包含"organization"数据
				}else{
					
					// 记录日志
					logger.error("启用机构失败,入参中不存在机构数据", obj);
					
					// 设置返回信息
					responseHelper.setFail("启用机构失败,入参中不存在机构数据", obj);
					
					// 返回结果
					return responseHelper;
				}
			}
			
			if(organizationList.size() > 0){
				
				try{
					
					organizationService.enableOrganization(organizationList);
					
					responseHelper.setSuccess("启用机构成功");
					
				}catch(Exception e){
					
					// 记录日志
					logger.error("启用机构时出现异常,机构启用失败", e);
					
					// 设置返回值
					responseHelper.setFail("启用机构时出现异常,机构启用失败", e.getMessage());
					
					// 返回结果
					return responseHelper;
					
				}
			}else{
				
				// 设置返回信息
				responseHelper.setFail("无法启用机构", param);
				
			}
			
			
		// 入参长度为0
		}else{
			
			// 记录日志
			logger.error("启用机构失败,入参为空", param);
			
			// 设置返回值
			responseHelper.setFail("启用机构失败,入参为空", param);
			
		}
		
		return responseHelper;
	}
	
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-19
	 * desc:机构同步(机构停用)
	 */
	@RequestMapping(value="disable")
	@ResponseBody
	public ResponseHelper disableOrganization(@RequestBody String param){
		
		// 入参格式转换
		JSONArray objArray = JSONArray.parseArray(param);
		
		// 判断入参长度
		if(objArray.size() > 0){
			
			// 构造参数机构数据集
			List<OrganizationInfo> organizationList = Lists.newArrayList();
			
			// 循环入参
			for(int i=0;i<objArray.size();i++){
				
				// 提取当前循环对象
				JSONObject obj = objArray.getJSONObject(i);
				
				// 当前对象包含"organization"数据
				if(obj.containsKey("organization")){
					
					// 提取organization数据
					JSONObject organizationObj = obj.getJSONObject("organization");
					
					if(!organizationObj.isEmpty()){
						
						try{
							
							// jsonobj 转 javabean
							OrganizationInfo o = organizationObj.toJavaObject(OrganizationInfo.class);
							
							// 设置机构启用状态(0为未启用) 
							o.setIsEnable("0");
							
							// 向list中添加数据
							organizationList.add(o);
							
						}catch(Exception e){
							
							// 打印异常信息
							e.printStackTrace();
							
							// 记录日志
							logger.error("停用机构时出现异常,json映射javaBean时报错", e);
							
							// 设置返回信息
							responseHelper.setFail("停用机构时出现异常,json映射javaBean时报错", e.getMessage());
							
							// 返回结果
							return responseHelper;
							
						}
						
					}else{
						
						// 记录日志
						logger.error("机构停用失败,入参中不存在机构数据", obj);
						
						
						// 设置返回值
						responseHelper.setFail("机构停用失败,入参中不存在机构数据", obj);
					
						// 返回结果
						return responseHelper;
					}
					
				// 当前对象不包含"organization"数据
				}else{
					
					// 记录日志
					logger.error("机构停用失败,入参中不存在机构数据", obj);
					
					
					// 设置返回值
					responseHelper.setFail("机构停用失败,入参中不存在机构数据", obj);
				
					// 返回结果
					return responseHelper;
				}
			}
			
			// list数据不为空时
			if(organizationList.size() > 0){
				
				try{
					
					organizationService.disableOrganization(organizationList);
					
					responseHelper.setSuccess("停用机构成功");
					
				}catch(Exception e){
					
					// 记录日志
					logger.error("停用机构时出现异常,机构停用失败", e);
					
					// 设置返回值
					responseHelper.setFail("停用机构时出现异常,机构停用失败", e.getMessage());
					
					// 返回结果
					return responseHelper;
				}
			
			// list数据为空时
			}else{
				
				// 设置返回值
				responseHelper.setFail("无法停用机构", param);
				
			}
			
		// 入参为空
		}else{
			
			// 记录日志
			logger.error("机构停用失败,入参为空", param);
			
			// 设置返回值
			responseHelper.setFail("机构停用失败,入参为空", param);
		}
		
		return responseHelper;
	}
}
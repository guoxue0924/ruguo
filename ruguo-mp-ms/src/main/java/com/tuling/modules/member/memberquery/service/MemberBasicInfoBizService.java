/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.service.GoodsBizService;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberquery.dao.MemberRelationshipInfoDao;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberAdressList;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResultResult;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;
import com.tuling.modules.member.memberquery.entity.MyConsultation;
import com.tuling.modules.member.memberquery.entity.MyOrder;
import com.tuling.modules.order.orderquery.entity.MyOrderFilter;
import com.tuling.modules.order.orderquery.service.OrderQueryService;





/**
 * 会员个人信息Service
 * 
 * @author wanggang
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class MemberBasicInfoBizService {

	@Autowired
	private MemberBasicInfoService memberBasicInfoService;

	@Autowired
	private PageHelper<MemberBasicInfoResult> pageHelper;
	
	@Autowired
	private PageHelper<MemberRelationshipInfo> pageHelperMemberRelationshipInfo;
	
	@Autowired
	private PageHelper<MemberBasicInfoResultResult> pageHelperResult;
	
	@Autowired
	private PageHelper<MemberAdressFilter> pageHelperAddress;
	
	@Autowired
	private PageHelper<MyConsultation> pageHelperMyConsultation;
	
	@Autowired
	private PageHelper<MyOrder> pageHelperMyOrder;
	
	@Autowired
	private MemberAdressListService memberAddressListService;
	
	@Autowired
	private OrderQueryService orderQueryService;
	
	@Autowired
	private GoodsBizService goodsBizService;
	
	@Autowired
	private MemberRelationshipInfoDao memberRelationshipInfoDao;

	
	/**
	 * 查詢会员消费金额信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResult(MemberRelationshipInfo memberRelationshipInfo){
		MemberBasicInfoResult memberBasicInfo = memberBasicInfoService.queryMemberBasicInfoResult(memberRelationshipInfo);
		return memberBasicInfo;
	}
	
	/**
	 * 根据code查詢会员信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResultByCode(MemberRelationshipInfo memberRelationshipInfo){
		MemberBasicInfoResult memberBasicInfo = memberBasicInfoService.queryMemberBasicInfoResultByCode(memberRelationshipInfo);
		return memberBasicInfo;
	}
	
	/**
	 * 查询会员个人信息
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public MemberBasicInfoResult get(MemberBasicInfoResult memberBasicInfoResult) {
		memberBasicInfoResult = memberBasicInfoService.get(memberBasicInfoResult);
		return memberBasicInfoResult;
	}
	
	/**
	 * 显示当前用户管理-服务机构和管理机构的信息
	 * @author wanggang
	 * @date 2017-06-28
	 */
	public List<Organization> queryOrganizationPullDownList(Organization organization) {
		// 查询数据
		List<Organization> list = memberBasicInfoService.queryOrganizationPullDownList(organization);
		return list;
	}
	
	/**
	 * 查询会员等级名称
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public List<MemberLevelInfo> findList(MemberLevelInfo memberLevelInfo) {
		List<MemberLevelInfo> memberLevelInfoPullList = memberBasicInfoService.findList(memberLevelInfo);
		return memberLevelInfoPullList;
	}

	/**
	 * 
	 * Created on 2017年9月21日 .
	 * <p>
	 * Description {获取收货地址}
	 *
	 * @author guoxue 
	 * @param page
	 * @param memberBasicInfo
	 * @return PageHelper<MemberBasicInfo>
	 */
	public PageHelper<MemberAdressFilter> getMemberAdressFilter(Page page, MemberBasicInfo memberBasicInfo) {
		// 分页查询，需要在filter实体中set分页信息
		memberBasicInfo.setPage(page);
		//通过memberCode查询memberName
		MemberRelationshipInfo memberRelationshipInfo = new MemberRelationshipInfo();
		memberRelationshipInfo.setMemberCode(memberBasicInfo.getCode());
		MemberBasicInfoResult memberBasicInfoEntity = memberBasicInfoService.queryMemberBasicInfoResultByCode(memberRelationshipInfo);
				// 查询数据
		List<MemberAdressFilter> list = memberAddressListService.getMemberAdressFilter(memberBasicInfo);
		List<MemberAdressFilter> newlist = new ArrayList<MemberAdressFilter>();
		for(MemberAdressFilter memberAdressFilter : list){
			memberAdressFilter.setMemberName(memberBasicInfoEntity.getRelaPerName());
			newlist.add(memberAdressFilter);
		}
				// 装载数据
		pageHelperAddress.setRows(page, newlist);

		return pageHelperAddress;
	}
	
	/**
	 * 
	 * Created on 2017年9月21日 .
	 * <p>
	 * Description {获取收货地址}
	 *
	 * @author guoxue 
	 * @param page
	 * @param memberBasicInfo
	 * @return PageHelper<MemberBasicInfo>
	 */
	public PageHelper<MyConsultation> getmyConsultation(Page page, MemberBasicInfo memberBasicInfo) {
		// 分页查询，需要在filter实体中set分页信息
		memberBasicInfo.setPage(page);
				// 查询数据
		List<MyConsultation> list = memberBasicInfoService.getmyConsultation(memberBasicInfo);
				// 装载数据
		pageHelperMyConsultation.setRows(page, list);

		return pageHelperMyConsultation;
	}
	

	
	/**
	 * 
	 * Created on 2017年9月22日 .
	 * <p>
	 * Description {获取我的购买记录}
	 *
	 * @author guoxue 
	 * @param page
	 * @param memberBasicInfo
	 * @return PageHelper<Myorder>
	 * @throws ParseException 
	 */
	public PageHelper<MyOrder> getmyOrder(Page page, MemberBasicInfo memberBasicInfo) throws ParseException  {
		List<MyOrder> list = new ArrayList<MyOrder>();
		List<MyOrderFilter> orderList = orderQueryService.getmyOrder(memberBasicInfo);
		for(MyOrderFilter myOrderFilter : orderList){
			
			if(CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(myOrderFilter.getGoodsType())){
				MyOrder myOrder = new MyOrder();
				myOrder.setGoodsName(myOrderFilter.getGoodsName());
				myOrder.setGoodsServiceNames(myOrderFilter.getGoodsTitle());
				myOrder.setTransactionTime(myOrderFilter.getTransactionTime().substring(0, 19));
				if(CommonConstant.goodsValidityUnit.DICT_GOODS_VALIDITY_UNIT_ZERO.equals(myOrderFilter.getGoodsValidityUnit())){
					myOrder.setGoodsValidity(myOrderFilter.getGoodsValidityNum() + "次");
					myOrder.setEndTime("");
				}else{
					myOrder.setGoodsValidity(myOrderFilter.getGoodsValidityNum() + "月");
					String endTime = addMonth(myOrderFilter.getGoodsValidityNum(),myOrderFilter.getTransactionTime());
					myOrder.setEndTime(endTime);
				}
				list.add(myOrder);
			}else{
				MyOrder myOrder = new MyOrder();
				String goodsCodes = myOrderFilter.getGoodsServiceCodes();
				String[] goodsCodeSplit = goodsCodes.split("#");
				for(int i = 0 ; i < goodsCodeSplit.length ; i++){
					Goods goods =new Goods();
					goods.setGoodsCode(goodsCodeSplit[i]);
					Goods newGoods = goodsBizService.findByGoodsCode(goods);
					myOrder.setGoodsName(newGoods.getGoodsName());
					myOrder.setGoodsServiceNames(newGoods.getGoodsTitle());
					myOrder.setTransactionTime(myOrderFilter.getTransactionTime().substring(0, 19));
					/*if(CommonConstant.goodsValidityUnit.DICT_GOODS_VALIDITY_UNIT_ZERO.equals(newGoods.getGoodsValidityUnit())){
						myOrder.setGoodsValidity(newGoods.getGoodsValidityNum() + "次");
						myOrder.setEndTime("");
					}else{
						myOrder.setGoodsValidity(newGoods.getGoodsValidityNum() + "月");
						String endTime = addMonth(newGoods.getGoodsValidityNum(),myOrderFilter.getTransactionTime());
						myOrder.setEndTime(endTime);
					}*/
					list.add(myOrder);
					
				}
				
			}
			
		}
		
		pageHelperMyOrder.setRows(page, list);
		return pageHelperMyOrder;
	}
	
	
	public String addMonth(String monthCount ,String date) throws ParseException {
		int count = Integer.parseInt(monthCount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, count);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 查询会员个人信息用于导出功能
	 * @author zhuming
	 * @date 2017-09-22
	 */
	public PageHelper<MemberBasicInfoResultResult> queryExportList(Page page, MemberBasicInfoFilter memberBasicInfoFilter) {

		// 分页查询，需要在filter实体中set分页信息
		memberBasicInfoFilter.setPage(page);
		// 查询数据
		List<MemberBasicInfoResultResult> list = memberBasicInfoService.queryExportList(memberBasicInfoFilter);
		//modify by guoxue 2017-10-10
		List<MemberBasicInfoResultResult> newlist = new ArrayList<MemberBasicInfoResultResult>();
		for(MemberBasicInfoResultResult memberBasicInfoResultResult : list){
			memberBasicInfoResultResult.setBirthday(memberBasicInfoResultResult.getRelaPerBirthday());//出生日期
			memberBasicInfoResultResult.setName(memberBasicInfoResultResult.getRelaPerName());//会员名称
			memberBasicInfoResultResult.setGenderName(memberBasicInfoResultResult.getRelaPerGenderName());//会员性别
			memberBasicInfoResultResult.setCertificateName(memberBasicInfoResultResult.getRelaPerCertificateName());//证件名称
			memberBasicInfoResultResult.setCertificateNo(memberBasicInfoResultResult.getRelaPerCertificateNo());//证件号
			if(StringUtils.isNotBlank(memberBasicInfoResultResult.getIsChronicDisease())){
				
				if(CommonConstant.COMMON_YES.equals(memberBasicInfoResultResult.getIsChronicDisease())){
					memberBasicInfoResultResult.setIsChronicDisease("是");
				}else{
					memberBasicInfoResultResult.setIsChronicDisease("否");
				}
			}
			newlist.add(memberBasicInfoResultResult);
		}
		// 装载数据
		pageHelperResult.setRows(page, newlist);

		return pageHelperResult;
	}
}
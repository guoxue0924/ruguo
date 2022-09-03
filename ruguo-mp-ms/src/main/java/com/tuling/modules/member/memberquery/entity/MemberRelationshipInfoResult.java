package com.tuling.modules.member.memberquery.entity;

import com.foundation.common.persistence.DataEntity;

public class MemberRelationshipInfoResult extends DataEntity<MemberRelationshipInfoResult> {
private static final long serialVersionUID = 1L;
	
    private String relaPerCode;      // 会员关系人编码
    private String memberCode;    // 会员编码
	private String relaTypeCode;      // 关系类型id
	private String relaTypeName;      // 关系类型名称
	private String relaPerName;      // 关系人姓名
	private String relaPerGenderCode;       // 关系人性别编码
	private String relaPerGenderName;       // 关系人性别名称
	private String relaPerBirthday;       // 关系人出生年月日
	private String relaPerCertificateType;       // 关系人证件类型
	private String relaPerCertificateName;       // 关系人证件名称
	private String relaPerCertificateNo;       // 关系人证件号码
	private String relaPerMobilePhone;       // 关系人手机号
	private String relaPerEmail;       // 关系人电子邮箱
	
	
}

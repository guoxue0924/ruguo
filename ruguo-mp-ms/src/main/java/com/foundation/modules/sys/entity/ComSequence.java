package com.foundation.modules.sys.entity;


/**
 * 
 * Created on 2017年4月11日
 * <p>
 * Description {描述}
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author liuhuiqun
 */
public class ComSequence{

	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public int getCurrentVal() {
		return currentVal;
	}
	public void setCurrentVal(int currentVal) {
		this.currentVal = currentVal;
	}
	public int getIncrementVal() {
		return incrementVal;
	}
	public void setIncrementVal(int incrementVal) {
		this.incrementVal = incrementVal;
	}
	private String seqName;
	private int currentVal;
	private int incrementVal;
}

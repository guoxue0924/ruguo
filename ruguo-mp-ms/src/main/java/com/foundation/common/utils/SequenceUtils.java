package com.foundation.common.utils;

import com.foundation.modules.sys.dao.ComSequenceDao;
import com.foundation.modules.sys.entity.ComSequence;

/**
 * 
 * Created on 2017年4月11日
 * <p>
 * Description {序列工具类}
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author liuhuiqun
 */
public class SequenceUtils {
	private static ComSequenceDao comSequenceDao = SpringContextHolder.getBean(ComSequenceDao.class);

	public static String getNextSequence(String seqName) {
		ComSequence seq = new ComSequence();
		seq.setSeqName(seqName);
		int rowCount = comSequenceDao.lockComSequence(seqName);
		if (rowCount == 0) {
			seq.setCurrentVal(1);
			seq.setIncrementVal(1);
			comSequenceDao.insert(seq);
		} else {
			seq.setCurrentVal(Integer.parseInt(comSequenceDao.getComSequence(seqName)));
		}
		return seq.getCurrentVal() + "";
	}

	public static void lockNextSequence(String seqName) {
		ComSequence seq = new ComSequence();
		seq.setSeqName(seqName);
		int rowCount = comSequenceDao.lockComSequence(seqName);
		if (rowCount == 0) {
			seq.setCurrentVal(1);
			seq.setIncrementVal(1);
			comSequenceDao.insert(seq);
		} else {
			comSequenceDao.nextComSequence(seqName);
		}
	}

}

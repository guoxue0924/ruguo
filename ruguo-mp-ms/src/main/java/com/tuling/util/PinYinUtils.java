package com.tuling.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

	@SuppressWarnings("deprecation")
    public static String getEname(String name) throws BadHanyuPinyinOutputFormatCombination {
         HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
         pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置样式
         pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
         pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
         
         return PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
    }
	/**
	 * Created on 2017年3月30日 .
	 * <p>
	 * Description 姓、名 英文第一个字母大写
	 *
	 * @author yuelingyun
	 * @param String
	 * @return String
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
    public static String getUpEname(String name) throws BadHanyuPinyinOutputFormatCombination {
         char[] strs = name.toCharArray();
         String newname = null;

          if (strs.length == 2) {    //如果姓名只有两个字
               newname = getEname("" + strs[0]) + getEname("" + strs[1]);
           } else if (strs.length == 3) {    //如果姓名有三个字
               newname = getEname("" + strs[0]) + getEname("" + strs[1] + strs[2]);
           } else if (strs.length == 4) {    //如果姓名有四个字
               newname = getEname("" + strs[0] + strs[1]) + getEname("" + strs[2] + strs[3]);
         } else {
                newname = getEname(name);
         }
         return newname;
    }
}

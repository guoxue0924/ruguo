
package com.tuling.modules.goods.goods.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReduceHtml2Text {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
    
    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }
    
    public static String getTextFromHtml(String htmlStr){
    	htmlStr = htmlStr.replaceAll("&lt;", "<" + ""); 
    	htmlStr = htmlStr.replaceAll("&gt;", ">" + ""); 
    	htmlStr = htmlStr.replaceAll("&amp;nbsp;", ""); 
    	htmlStr = delHTMLTag(htmlStr);
    	htmlStr = htmlStr.replaceAll(" ", "");
//    	htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);y
    	return htmlStr;
    }
    
   /* public static void main(String[] args) {
    	String str = "&lt;p&gt;&lt;span style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;送给父母的最好礼物，提前预知重大疾病的的发病概率，合理管理健康很重要。&lt;/span&gt;&lt;br&gt;&lt;/p&gt;";
		System.out.println(getTextFromHtml(str));
	}*/
}


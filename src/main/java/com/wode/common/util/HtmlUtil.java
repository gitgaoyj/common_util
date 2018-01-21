package com.wode.common.util;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 删除html标签
 * @author mengkaixuan
 *
 */
public class HtmlUtil {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * 返回html长度， 英文返回字母数的一半
     * @param htmlStr
     * @param   isDelHtml
     * @return
     */
    public static  int  htmlLength(String htmlStr,boolean isDelHtml){
        if(isDelHtml){
            htmlStr = delHTMLTag(htmlStr);
        }
        if(htmlStr.length() < stringLength(htmlStr)){
            return  htmlStr.length();
        }
        else  if(htmlStr.length() >= stringLength(htmlStr))
           return  htmlStr.length() / 2;

        return -1;
    }
    /**
     * 截断html字符串，如果是英文按照length自动*2字母个数，如果是汉字按照汉字个数截断。并删除html标签
     * @param htmlStr   原来数据
     * @param length    汉字或字母个数
     * @param isDelHtml   是否删除html标签
     * @return  返回截断后的数据
     */
    public static String htmlSplit(String htmlStr,int length,boolean isDelHtml){
        if(isDelHtml){
            htmlStr = delHTMLTag(htmlStr);
        }
        if(htmlStr.length()<length){
        	length = htmlStr.length();
        }
        //System.out.println(htmlStr.length()+","+stringLength(htmlStr));
        if(htmlStr.length() < stringLength(htmlStr)){
            return   htmlStr.substring(0,length);
        }
        if(htmlStr.length() >= stringLength(htmlStr))
            length *=2;
        String[] rets = htmlStr.split("\\s|&nbsp;");
        StringBuffer ret = new StringBuffer();
//        for(int i=0;i<rets.length;i++){
//            System.out.println(rets[i]);
//        }
        for(int i=0;i<rets.length;i++){
            if(stringLength(ret.toString()) >= length)
                break;

            if(rets[i].equals("")) continue;
            ret.append(" ");
            ret.append(rets[i].trim());
        }
        return ret.toString();
    }
    public static int stringLength(String str){
        String anotherString = null;
        try {
            anotherString = new String(str.getBytes("GBK"), "ISO8859_1");
        }
        catch (UnsupportedEncodingException ex) {
        }
        return      anotherString.length();
    }

///*    public static void main(String[] args){
//         String test = "<p>Hello! My name is Amanda. I am an American from the Southern part of the United States. I was educated in America and graduate attained two different degrees from American universities: a Bachelors of Arts in Communications Studies and a Juris Doctor in Law. I enjoy teaching English as a second language, and truly love helping people improve their English skills. I think the key to improving you language skills is practice, practice, and more practice. In my free time I enjoy reading books, watching movies, and playing with my dog. I love outdoor activities and especially enjoy going for long walks. I hope I can be of help to you in your journey to improve you English abilities, and I wish you all the best of luck in your studies! Cheers!</p>\n";
//        System.out.println(test.length()+","+stringLength(test));
//         System.out.println(htmlSplit(test, 50, true));
//        test = "<p>原新东方教育科技集团旗下&ldquo;精英英语&rdquo;成人中心面授课教师，毕业于首都师范大学，后获得澳大利亚麦考瑞大学中英口笔译硕士学位，<br />\n" +
//                "教学专长:&nbsp;<br />\n" +
//                "- 雅思考试 : 通过典型题型和英语基本功针对性训练，诊断学员备考过程中的错误观念和做法，有效率地提高学员听力和阅读成绩，达到名校要求。<br />\n" +
//                "- VIP 学员个性化课程设计和教学。 通过上百例真实的教学个案,全面深入地了解白领阶层各行各业的英语学习需求，为学员量身定制能够满足生活社交和工作谈判的语言技能整体学习方案；并在针对成人的教学教法上有独到心得和成功战术。&nbsp;<br />\n" +
//                "- 企业培训课程设计和专题教学服务。 金融从业背景，特别擅长制定金融领域的专业英语教案和教学实践。<br />\n" +
//                "- 商务英语教学。商务领域的口笔译实战经验，指导学员避免中式英文，翻译出地道英文。</p>\n";
//        System.out.println(test.length()+","+stringLength(test));
//        System.out.println(htmlSplit(test, 10, true));
//        System.out.println(htmlLength("hehe",false));
//        *//*String aString = "你好,abc";
//        String anotherString = null;
//        try {
//            anotherString = new String(aString.getBytes("GBK"), "ISO8859_1");
//        }
//        catch (UnsupportedEncodingException ex) {
//        }
//        System.out.println(aString.length() + "," + anotherString.length());*//*
//    }*/

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

        return htmlStr.trim(); // 返回文本字符串
    }
    
    public static String delAppointHTMLTag(String str,String tag){
    	 String regex="<"+tag+"[^>]*?>([\\s\\S]*?)<\\/"+tag+">";
         Pattern p =Pattern.compile(regex);
         if(str==null){
        	 str="";
         }
         Matcher m=p.matcher(str);
         while(m.find()){
        	 str = m.group(1);
         }
         return str;
    }
}

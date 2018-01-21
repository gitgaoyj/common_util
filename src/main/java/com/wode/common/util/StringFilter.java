package com.wode.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  ┏┓　　          ┏┓    <br />
 *┏┛┻━━━━━━━━┛┻┓  <br />
 *┃　　　　　           ┃ 　<br />
 *┃　　　   ━　        ┃   <br />
 *┃　┳┛　     ┗┳    ┃   <br />
 *┃　　　　　　         ┃    <br />
 *┃　　　  ┻　　      ┃     <br />
 *┃　　　　　         ┃     <br />
 *┗━┓　　　┏━━━━┛      <br />
 *    ┃　　　┃   神兽保佑　　<br />　　　　　　　
 *    ┃　　　┃   代码无BUG！ <br />
 *    ┃　　　┗━━━━━━━┓<br />
 *    ┃　　　　 　         ┣┓ <br />
 *    ┃　　　    　　      ┏┛ <br />
 *    ┗┓┓┏━━━┳┓  ┏┛    <br />
 *      ┃┫┫      ┃┫┫      <br />
 *      ┗┻┛      ┗┻┛      <br />
 *
 * 敏感词过滤方法
 * User: mengkaixuan
 * Date: 14-8-14
 * Time: 下午3:39
 */
public class StringFilter {
    public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }
    private static String[] splitWord(String str){
        StringBuffer sb = new StringBuffer();
        StringBuffer englishWord = new StringBuffer();
        for(int i=0;i<str.length();i++) {
            String temp = str.substring(i,i+1);
            if(!isChineseChar(temp) && !temp.equals(" "))  {
                englishWord.append(temp);
                continue;                
            }else if(temp.equals(" ")){
            	sb.append(englishWord);
                sb.append(" ");
                englishWord = new StringBuffer();
            }else{
                sb.append(" ");
                sb.append(englishWord);
                sb.append(" ");
                sb.append(temp);
                englishWord = new StringBuffer();
            }
        }
        sb.append(" ");
        sb.append(englishWord);
        return sb.toString().trim().split(" ");
    }

    private static boolean match( String regex ,String str ){
        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher( str );
        return matcher.matches();
    }

    public static String hasNet(String text){
        String net = ".*?http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?.*";
        //String net2 = ".*?[a-zA-z]+://(w+(-w+)*)(.(w+(-w+)*))?.*";
        String net2 = ".*?(http://|ftp://|https://|www){0,1}[^\\u4e00-\\u9fa5\\\\s]*?\\\\.(com|net|cn|me|tw|fr)[^\\u4e00-\\u9fa5\\\\s].*";
        String net3 = ".*www.*?[^\\u4e00-\\u9fa5\\\\s]*?.(com|net|cn|me|tw|fr).*";
        //System.out.println(match(net,text));
        //System.out.println(match(net2,text));
        if(match(net,text)){
            return  text;
        }
        if(match(net2,text)){
            return text;
        }
        if(match(net3,text)){
            return text;
        }
        return null;
    }

    /**
     * 返回是否有敏感词
     * 中间如果夹杂特殊字符也可以验证
     * @param sensitive
     * @param text
     * @return 返回错误的关键字
     */
    public static String hasSensitive(String[] sensitive,String text) {
        if(!StringUtils.isNullOrEmpty(hasNet(text))) return text;
        for(String str:sensitive){
            String[] strs = splitWord(str);// str.split(" ");
            String match = "";
            StringBuffer sb = new StringBuffer();
            sb.append(".*?");
            for(String ss:strs){
                ss = ss.replace("*","");
                ss = ss.replace("[","");
                ss = ss.replace("]","");
                ss = ss.replace("/","");
                ss = ss.replace("\\","");
                ss = ss.replace("?","");
                ss = ss.replace("$","");
                sb.append(ss);
                //sb.append("[!@#$%^&*、()_……@！@#￥%……&*（）]");
                //sb.append(".*?");
                sb.append("[-!@#$%^&*、(){}（）]*");
            }
            sb.append(".*?");
            //sb.append(".*$");

            match = sb.toString();
            //System.out.println(match);
            
            try {
				if(text.matches(match)) {
                    System.out.println(str);
                    return str;
                }
			} catch (Exception e) {
				continue;
			}
            
        }
        return null;
    }

    /**
     * 返回是否有敏感词
     * 中间如果夹杂特殊字符也可以验证
     * @param sensitive
     * @param text
     * @return  如果有返回true,没有返回false
     */
    public static boolean isHasSensitive(String[] sensitive,String text) {
         return !StringUtils.isNullOrEmpty(hasSensitive(sensitive,text));
    }
    public  static  void main(String[] args){
    	String [] str = splitWord("反对 fa 3");
    	//for(String s : str)
    	//    System.out.println(s);
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, "adfwefqasdasdfas23easdf"));
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, " www.ZATZ.com/12NJ "));
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, "  http://www.y108.com/ "));
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, "  asfgfe哈哈发到您撒发热器热风枪 "));
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, "  asfgfe哈哈发到反对发热器热风枪 "));
        System.out.println("============"+hasSensitive(new String[]{"zhuanfalun", "反对", "哥疼"}, "www.ZATZ.com"));
    }
}

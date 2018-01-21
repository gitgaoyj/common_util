package com.wode.common.util;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wode-ui3 on 2014/10/29.
 */
public class ReferUtils {
    private static final String google_url = "googleads.g.doubleclick.net";
    private static final String baidu_url = "pos.baidu.com";
    private static final String baidu_url2 = "cb.baidu.com";
    private static final String baidu_www = "www.baidu.com";
    private static final String so_www = "www.so.com";
    private static final String host = "wode.com";

    public static String referCome(String url){
        if(StringUtils.isNullOrEmpty(url) || "-".equals(url) || "~".equals(url))
            return null;
        if(url.indexOf(google_url) != -1){
             return "googleads";
        }
        if(url.indexOf(baidu_url) != -1){
             return "posbaidu";
        }
        if(url.indexOf(baidu_url2) != -1){
            return "posbaidu";
        }
        if(url.indexOf(baidu_www) != -1){
            return "baidu";
        }
        if(url.indexOf(so_www)!=-1){
            return "360";
        }
        if(url.indexOf(host)!=-1){
            return "wode";
        }
        return "unkown";
    }
    public static String referUrl(String url) throws UnsupportedEncodingException {
        if(StringUtils.isNullOrEmpty(url) || "-".equals(url) || "~".equals(url))
            return null;
        if("googleads".equals(referCome(url))){
            String comeurl = referCome(url);
            String[] comeStr = url.split("&");
            if(comeStr!= null && comeStr.length>0){
                for (String str:comeStr){
                    //System.out.println(str);
                    if(str.toLowerCase().indexOf("url=")!=-1){
                        return URLDecoder.decode(str.toLowerCase().split("=")[1],"UTF-8");
                    }
                }
            }
        }
        if("posbaidu".equals(referCome(url))){
            String comeurl = referCome(url);
            String[] comeStr = url.split("&");
            if(comeStr!= null && comeStr.length>0){
                for (String str:comeStr){

                    if(str.toLowerCase().indexOf("ltu=")!=-1){
                        return URLDecoder.decode(str.toLowerCase().split("=")[1],"UTF-8");
                    }
                }
            }
        }
        if("baidu".equals(referCome(url))){
            return url;//"http://www.baidu.com";
        }
        if("360".equals(referCome(url))){
            return url;//"http://www.baidu.com";
        }

        return url;
    }
    public static String parseDomain(String urlstr) throws IOException {

            //String urlAddress = "http://www.roseindia.net/jsf/JSFLoginApplication.shtml";
            URL url = new URL(urlstr);
        return url.getProtocol()+"://"+url.getHost()+(url.getPort()==-1?"":":"+url.getPort());

    }
    public static String doGet(String url, String queryString) throws IOException {
        HttpClient client = new HttpClient();
        HttpClientParams param = new HttpClientParams();
        HttpMethod method = new GetMethod(parseDomain(url));
        // logger.info("begin to create Snapshoot:"+url);
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(30000);
        client.getHttpConnectionManager().getParams().setSoTimeout(30000);
        int statusCode = client.executeMethod(method);
        byte[] responseBody = null;
        responseBody = method.getResponseBody();
        String charset = "UTF-8";
        if(method instanceof PostMethod){
            charset = ((PostMethod)method).getResponseCharSet();
        }else{
            charset = ((GetMethod)method).getResponseCharSet();
        }
        String tmp = new String(responseBody,"UTF-8");
        String str = new String(responseBody,!StringUtils.isNullOrEmpty(charset)?getContentCharset(tmp):charset);
        tmp = null;
        //charset = getCharSetByBody(body,charset);
        method.releaseConnection();
        return str;
    }
    /**
     * 正则匹配
     * @param s
     * @param pattern
     * @return
     */
    public static boolean matcher(String s, String pattern)
    {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE
                + Pattern.UNICODE_CASE);
        Matcher matcher = p.matcher(s);
        if (matcher.find())
        {
            return true;
        } else
        {
            return false;
        }
    }
    public static String getContentCharset(String response)
    {
        String charset = "ISO_8859-1";
        String header = response;//response.getEntity().getContentType();
        if (header != null)
        {
            String s = header;//.getValue();
            if (matcher(s, "(charset)\\s?=\\s?(utf-?8)"))
            {
                charset = "utf-8";
            } else if (matcher(s, "(charset)\\s?=\\s?(gbk)"))
            {
                charset = "gbk";
            } else if (matcher(s, "(charset)\\s?=\\s?(gb2312)"))
            {
                charset = "gb2312";
            }
            else{
                charset = "utf-8";
            }
        }
        return charset;

    }

//    public String getContentCharset(HttpResponse response)
//    {
//        String charset = "ISO_8859-1";
//        String header = response.getContentType();//response.getEntity().getContentType();
//        if (header != null)
//        {
//            String s = header;//.getValue();
//            if (matcher(s, "(charset)\\s?=\\s?(utf-?8)"))
//            {
//                charset = "utf-8";
//            } else if (matcher(s, "(charset)\\s?=\\s?(gbk)"))
//            {
//                charset = "gbk";
//            } else if (matcher(s, "(charset)\\s?=\\s?(gb2312)"))
//            {
//                charset = "gb2312";
//            }
//        }
//        return charset;
//
//    }
    public static String searchKeyWord(String url) throws UnsupportedEncodingException {
        if(StringUtils.isNullOrEmpty(url) || "-".equals(url) || "~".equals(url))
            return null;
        if(referCome(url).equals("baidu")) {
            String[] comeStr = url.split("&");
            if (comeStr != null && comeStr.length > 0) {
                for (String str : comeStr) {
                    if (str.toLowerCase().indexOf("wd=") != -1) {
                        return URLDecoder.decode(str.toLowerCase().split("=")[1], "UTF-8");
                    }
                }
            }
        }
        else if("360".equals(referCome(url))){
            String[] comeStr = url.split("&");
            if (comeStr != null && comeStr.length > 0) {
                for (String str : comeStr) {
                    if (str.toLowerCase().indexOf("q=") != -1) {
                        return URLDecoder.decode(str.toLowerCase().split("=")[1], "UTF-8");
                    }
                }
            }
        }
        return "unkown";
    }
    public static String urlTilte(String url) throws IOException {
        if(StringUtils.isNullOrEmpty(url) || "-".equals(url) || "~".equals(url))
            return null;
        String ret = doGet(url,null);
        Pattern pattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = pattern.matcher(ret);
        if(matcher.find())          {
            //System.out.println(matcher.group(1));
            return matcher.group(1);
        }
        return "";
    }


    public static UrlParserModel urlParser(String url){
        UrlParserModel model = new UrlParserModel();
        try {
            String come = referCome(url);
            model.setSe(come);
            model.setOrgRefer(referUrl(url));
            model.setRefer(url);
            if (!come.equals("unkown") && !come.equals("googleads") && !come.equals("posbaidu")){
                model.setDomian(parseDomain(url));
                model.setKeywords(searchKeyWord(url));
                model.setTitle(come);
            }else if(come.equals("googleads") || come.equals("posbaidu")) {
                String title = urlTilte(ReferUtils.referUrl(url));
                model.setTitle(title);
                model.setDomian(parseDomain(referUrl(url)));
            }else if(come.equals("wode")){
                model.setTitle("wode");
                model.setDomian(parseDomain(referUrl(url)));
            }else {
                String title = urlTilte(ReferUtils.referUrl(url));
                model.setTitle(title);
                model.setDomian(parseDomain(url));
            }

        }catch (Exception e){

        }
//        System.out.println(ReferUtils.referCome(x));
//        System.out.println(ReferUtils.referUrl(x));
//        //System.out.println(ReferUtils.urlTilte(ReferUtils.referUrl(x)));
//        System.out.println(ReferUtils.searchKeyWord(x));
        return model;
    }

    public  static void main(String[] args) throws IOException {
        String x ="http://googleads.g.doubleclick.net" +
                "/pagead/ads?client=ca-pub-1753366286882929&output=html&h=90&slotname=4473118294&" +
                "adk=3957992701&w=970&lmt=1414544656&ea=0&flash=15.0.0.189&url=http%3A%2F%2Fedu.i" +
                "feng.com%2Fa%2F20141029%2F40850315_0.shtml&dt=1414544656281&shv=r20141023&cbv=r2" +
                "0140417&saldr=sb&correlator=2268316655981&frm=23&ga_vid=573915794.1411437202&ga_" +
                "sid=1414544656&ga_hid=2049459578&ga_fc=0&u_tz=480&u_his=0&u_java=1&u_h=900&u_w=1" +
                "440&u_ah=870&u_aw=1440&u_cd=32&u_nplug=0&u_nmime=0&dff=times%20new%20roman&dfs=1" +
                "6&adx=485&ady=36&biw=771&bih=437&isw=970&ish=90&ifk=886736107&eid=317150304%2C82" +
                "8064100&oid=3&ref=http%3A%2F%2Fedu.ifeng.com%2F&loc=EMPTY&top=http%3A%2F%2Fedu.i" +
                "feng.com%2Fa%2F20141029%2F40850315_0.shtml&rx=0&eae=6&brdim=555%2C413%2C%2C%2C14" +
                "40%2C%2C%2C%2C%2C&osd=1&vis=0&abl=CA&ppjl=u&fu=4&ifi=1&dtd=156";
        System.out.println(referCome(x));
        System.out.println(referUrl(x));
        //System.out.println(urlTilte(referUrl(x)));
        String title = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=utf-8 />\n" +
                "<title>凤凰教育_教育频道_凤凰网</title>\n" +
                "<meta name=\"keywords\" content=\"凤凰教育精品二级栏目 小升初 中小学 高考 考研 公务员考试 大学校园 托福 雅思 英语 出国留学 移民 MBA 商学院 职业培训、教育评论、教育论坛、教育投诉、曝光\" />\n" +
                "<meta name=\"description\" content=\"凤凰网教育频道是唯一以全球视野报道中国教育新闻、提供教育资讯和服务产品的综合教育门户。为考生中小学升学、高考指导、考研培训、公务员培训、出国、留学及职业培训提供专业的行业资讯及培训咨询服务，给教育企业提供有价值的宣传渠道，助推中国教育行业向上发展的媒体平台！\" />\n" +
                "<link media=\"screen\" href=\"http://y1.ifengimg.com/css/min/basic_new_v2.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "<link media=\"screen\"href='http://y1.ifengimg.com/a6ad1771d0cc4da6/2014/0411/eduHpV3.css' rel=\"stylesheet\" type=\"text/css\">\n" +
                "<script src=\"http://y1.ifengimg.com/BX/min/BX.1.0.1.U.js\"></script>\n" +
                "<script src=\"http://y1.ifengimg.com/JCore/min/TabControl.1.2.js\"></script>\n" +
                "<script src=\"http://y1.ifengimg.com/scripts/min/ifeng_common_v1.2.js\"></script>\n" +
                "<script src=\"http://y2.ifengimg.com/scripts/min/js/jquery-1.5.1.min.js\"></script>\n" +
                "<script>var jq=jQuery.noConflict();</script>\n" +
                "<script src=\"http://y2.ifengimg.com/JCore/min/scroll.v.1.2.js\"></script> \n" +
                "<script src=\"http://y0.ifengimg.com/38716b164e0f5e63/2013/1213/md5.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<!-----页首begin------->\t\n" +
                "<link rel=\"stylesheet\" href=\"http://y0.ifengimg.com/9949a678442334cc/2013/1118/f.header.css\" />\n" +
                "<div class=\"h_mainNavNew cDGray\" id=\"f-header\">\n" +
                "    <ul class=\"list_menu\">\n" +
                "        <li style=\"background:none; padding-left:2px;\"><a href=\"http://www.ifeng.com\" target=\"_blank\">首页</a></li>\n" +
                "        <li><a href=\"http://news.ifeng.com/\" target=\"_blank\">资讯</a></li>\n" +
                "        <li><a href=\"http://finance.ifeng.com/\" target=\"_blank\">财经</a></li>\n" +
                "        <li><a href=\"http://ent.ifeng.com/\" target=\"_blank\">娱乐</a></li>\n" +
                "        <li><a href=\"http://sports.ifeng.com/\" target=\"_blank\">体育</a></li>\n" +
                "        <li><a href=\"http://fashion.ifeng.com/\" target=\"_blank\">时尚</a></li>\n" +
                "        <li><a href=\"http://auto.ifeng.com/\" target=\"_blank\">汽车</a></li>\n" +
                "        <li><a href=\"http://house.ifeng.com/\" target=\"_blank\">房产</a></li>\n" +
                "        <li><a href=\"http://tech.ifeng.com/\" target=\"_blank\">科技</a></li>\n" +
                "        <li><a href=\"http://book.ifeng.com/\" target=\"_blank\">读书</a></li>\n" +
                "        <li><a href=\"http://edu.ifeng.com/\" target=\"_blank\">教育</a></li>\n" +
                "        <li><a href=\"http://culture.ifeng.com/\" target=\"_blank\">文化</a></li>\n" +
                "        <li><a href=\"http://news.ifeng.com/history/\" target=\"_blank\">历史</a></li>\n" +
                "        <li><a href=\"http://news.ifeng.com/mil/\" target=\"_blank\">军事</a></li>\n" +
                "        <li><a href=\"http://blog.ifeng.com/\" target=\"_blank\">博客</a></li>\n" +
                "        <li><a href=\"http://zx.cp.ifeng.com/?aid=45\" target=\"_blank\">彩票</a></li>\n" +
                "        <li><a href=\"http://fo.ifeng.com/\" target=\"_blank\">佛教</a></li>\n" +
                "        <li><a href=\"http://phtv.ifeng.com/\" target=\"_blank\">凤凰卫视</a></li>\n" +
                "    </ul>\n" +
                "    <div class=\"morehNew\" id=\"f-more\">\n";
        Pattern pattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = pattern.matcher(title);
        if(matcher.find())          {
            System.out.println(matcher.group(1));
            //return matcher.group(1);
        }
    }
}

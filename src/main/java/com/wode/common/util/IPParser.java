package com.wode.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 转换IP
 *
 * @author mengkaixuan
 */
public class IPParser {
    private Logger logger = LoggerFactory.getLogger(IPParser.class);

    private String DbPath = "/var/www/resources.wode.com/resources/QQWry.dat"; //纯真IP库

    private String Country, LocalStr;

    private long IPN;

    private int RecordCount, CountryFlag;

    private long RangE, RangB, OffSet, StartIP, EndIP, FirstStartIP,
            LastStartIP, EndIPOff;

    private RandomAccessFile fis;

    private String[] provinces = new String[]{"北京", "天津", "上海", "重庆", "广东", "青海", "四川", "海南", "陕西", "甘肃", "云南",
            "湖南", "湖北", "黑龙江", "贵州", "山东", "江西", "河南", "河北", "山西", "安徽", "福建", "浙江", "江苏",
            "吉林", "辽宁", "台湾", "新疆", "香港", "澳门", "广西", "宁夏", "内蒙古", "西藏"};

    private String[] citys = new String[]{"巴音郭楞蒙古自治州", "和田地区", "哈密地区", "阿克苏地区", "阿勒泰地区", "喀什地区", "塔城地区",
            "昌吉回族自治州", "克孜勒苏柯尔克孜自治州", "吐鲁番地区", "伊犁哈萨克自治州", "博尔塔拉蒙古自治州", "乌鲁木齐市", "克拉玛依市",
            "阿拉尔市", "图木舒克市", "五家渠市", "石河子市", "那曲地区", "阿里地区", "日喀则地区",
            "林芝地区", "昌都地区", "山南地区", "拉萨市", "呼伦贝尔市", "阿拉善盟", "锡林郭勒盟",
            "鄂尔多斯市", "赤峰市", "巴彦淖尔市", "通辽市", "乌兰察布市", "兴安盟", "包头市",
            "呼和浩特市", "乌海市", "海西蒙古族藏族自治州", "玉树藏族自治州", "果洛藏族自治州", "海南藏族自治州", "海北藏族自治州",
            "黄南藏族自治州", "海东地区", "西宁市", "甘孜藏族自治州", "阿坝藏族羌族自治州", "凉山彝族自治州", "绵阳市",
            "达州市", "广元市", "雅安市", "宜宾市", "乐山市", "南充市", "巴中市",
            "泸州市", "成都市", "资阳市", "攀枝花市", "眉山市", "广安市", "德阳市",
            "内江市", "遂宁市", "自贡市", "黑河市", "大兴安岭地区", "哈尔滨市", "齐齐哈尔市",
            "牡丹江市", "绥化市", "伊春市", "佳木斯市", "鸡西市", "双鸭山市", "大庆市",
            "鹤岗市", "七台河市", "酒泉市", "张掖市", "甘南藏族自治州", "武威市", "陇南市",
            "庆阳市", "白银市", "定西市", "天水市", "兰州市", "平凉市", "临夏回族自治州",
            "金昌市", "嘉峪关市", "普洱市", "红河哈尼族彝族自治州", "文山壮族苗族自治州", "曲靖市", "楚雄彝族自治州",
            "大理白族自治州", "临沧市", "迪庆藏族自治州", "昭通市", "昆明市", "丽江市", "西双版纳傣族自治州",
            "保山市", "玉溪市", "怒江傈僳族自治州", "德宏傣族景颇族自治州", "百色市", "河池市", "桂林市",
            "南宁市", "柳州市", "崇左市", "来宾市", "玉林市", "梧州市", "贺州市",
            "钦州市", "贵港市", "防城港市", "北海市", "怀化市", "永州市", "邵阳市",
            "郴州市", "常德市", "湘西土家族苗族自治州", "衡阳市", "岳阳市", "益阳市", "长沙市",
            "株洲市", "张家界市", "娄底市", "湘潭市", "榆林市", "延安市", "汉中市",
            "安康市", "商洛市", "宝鸡市", "渭南市", "咸阳市", "西安市", "铜川市",
            "清远市", "韶关市", "湛江市", "梅州市", "河源市", "肇庆市", "惠州市",
            "茂名市", "江门市", "阳江市", "云浮市", "广州市", "汕尾市", "揭阳市",
            "珠海市", "佛山市", "潮州市", "汕头市", "深圳市", "东莞市", "中山市",
            "延边朝鲜族自治州", "吉林市", "白城市", "松原市", "长春市", "白山市", "通化市",
            "四平市", "辽源市", "承德市", "张家口市", "保定市", "唐山市", "沧州市",
            "石家庄市", "邢台市", "邯郸市", "秦皇岛市", "衡水市", "廊坊市", "恩施土家族苗族自治州",
            "十堰市", "宜昌市", "襄樊市", "黄冈市", "荆州市", "荆门市", "咸宁市",
            "随州市", "孝感市", "武汉市", "黄石市", "神农架林区", "天门市", "仙桃市",
            "潜江市", "鄂州市", "遵义市", "黔东南苗族侗族自治州", "毕节地区", "黔南布依族苗族自治州", "铜仁地区",
            "黔西南布依族苗族自治州", "六盘水市", "安顺市", "贵阳市", "烟台市", "临沂市", "潍坊市", "青岛市", "菏泽市", "济宁市",
            "德州市", "滨州市", "聊城市", "东营市", "济南市", "泰安市", "威海市",
            "日照市", "淄博市", "枣庄市", "莱芜市", "赣州市", "吉安市", "上饶市",
            "九江市", "抚州市", "宜春市", "南昌市", "景德镇市", "萍乡市", "鹰潭市",
            "新余市", "南阳市", "信阳市", "洛阳市", "驻马店市", "周口市", "商丘市",
            "三门峡市", "新乡市", "平顶山市", "郑州市", "安阳市", "开封市", "焦作市",
            "许昌市", "濮阳市", "漯河市", "鹤壁市", "大连市", "朝阳市", "丹东市",
            "铁岭市", "沈阳市", "抚顺市", "葫芦岛市", "阜新市", "锦州市", "鞍山市",
            "本溪市", "营口市", "辽阳市", "盘锦市", "忻州市", "吕梁市", "临汾市",
            "晋中市", "运城市", "大同市", "长治市", "朔州市", "晋城市", "太原市",
            "阳泉市", "六安市", "安庆市", "滁州市", "宣城市", "阜阳市", "宿州市",
            "黄山市", "巢湖市", "亳州市", "池州市", "合肥市", "蚌埠市", "芜湖市",
            "淮北市", "淮南市", "马鞍山市", "铜陵市", "南平市", "三明市", "龙岩市",
            "宁德市", "福州市", "漳州市", "泉州市", "莆田市", "厦门市", "丽水市",
            "杭州市", "温州市", "宁波市", "舟山市", "台州市", "金华市", "衢州市",
            "绍兴市", "嘉兴市", "湖州市", "盐城市", "徐州市", "南通市", "淮安市",
            "苏州市", "宿迁市", "连云港市", "扬州市", "南京市", "泰州市", "无锡市",
            "常州市", "镇江市", "吴忠市", "中卫市", "固原市", "银川市", "石嘴山市",
            "儋州市", "文昌市", "乐东黎族自治县", "三亚市", "琼中黎族苗族自治县", "东方市", "海口市",
            "万宁市", "澄迈县", "白沙黎族自治县", "琼海市", "昌江黎族自治县", "临高县", "陵水黎族自治县",
            "屯昌县", "定安县", "保亭黎族苗族自治县", "五指山市", "东城区", "西城区", "朝阳区",
            "海淀区", "宣武区", "崇文区", "石景山区", "丰台区", "昌平区", "通州区",
            "大兴区", "房山区", "门头沟区", "顺义区", "平谷区", "密云县", "怀柔区",
            "延庆县", "河东区", "河西区", "河北区", "和平区", "南开区", "红桥区",
            "北辰区", "东丽区", "西青区", "津南区", "静海县", "大港区", "塘沽区",
            "汉沽区", "宁河县", "武清区", "宝坻区", "蓟县", "黄浦区", "浦东新区",
            "徐汇区", "长宁区", "静安区", "普陀区", "闸北区", "虹口区", "杨浦区",
            "闵行区", "宝山区", "嘉定区", "金山区", "松江区", "青浦区", "奉贤区",
            "崇明县", "卢湾区", "万州区", "黔江区", "涪陵区", "渝中区", "大渡口区",
            "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "渝北区", "巴南区",
            "长寿区", "江津区", "合川区", "永川区", "南川区", "綦江区", "大足区",
            "铜梁区", "璧山区", "潼南县", "荣昌县", "梁平县", "城口县", "丰都县",
            "垫江县", "武隆县", "忠县", "开县", "云阳县", "奉节县", "巫山县",
            "巫溪县", "石柱土家族自治县", "秀山土家族苗族自治县", "酉阳土家族苗族自治县", "彭水苗族土家族自治县"};

    private byte[] buff;

    private long B2L(byte[] b) {
        long ret = 0;
        for (int i = 0; i < b.length; i++) {
            long t = 1L;
            for (int j = 0; j < i; j++)
                t = t * 256L;
            ret += ((b[i] < 0) ? 256 + b[i] : b[i]) * t;
        }
        return ret;
    }

    private long ipToInt(String ip) {
        String[] arr = ip.split("\\.");
        long ret = 0;
        for (int i = 0; i < arr.length; i++) {
            long l = 1;
            for (int j = 0; j < i; j++)
                l *= 256;
            try {
                ret += Long.parseLong(arr[arr.length - i - 1]) * l;
            } catch (Exception e) {
                ret += 0;
            }
        }
        return ret;
    }

    public void seek(String ip) throws Exception {
        try {
            this.IPN = ipToInt(ip);
            logger.info("DbPath:"+this.DbPath);
            fis = new RandomAccessFile(this.DbPath, "r");
            buff = new byte[4];
            fis.seek(0);
            fis.read(buff);
            FirstStartIP = this.B2L(buff);
            fis.read(buff);
            LastStartIP = this.B2L(buff);
            RecordCount = (int) ((LastStartIP - FirstStartIP) / 7);
            if (RecordCount <= 1) {
                LocalStr = Country = "δ֪";
                throw new Exception();
            }

            RangB = 0;
            RangE = RecordCount;
            long RecNo;

            do {
                RecNo = (RangB + RangE) / 2;
                getStartIP(RecNo);
                if (IPN == StartIP) {
                    RangB = RecNo;
                    break;
                }
                if (IPN > StartIP)
                    RangB = RecNo;
                else
                    RangE = RecNo;
            } while (RangB < RangE - 1);

            getStartIP(RangB);
            getEndIP();
            getCountry(IPN);

            fis.close();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private String getFlagStr(long OffSet) throws IOException {
        int flag = 0;
        do {
            fis.seek(OffSet);
            buff = new byte[1];
            fis.read(buff);
            flag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
            if (flag == 1 || flag == 2) {
                buff = new byte[3];
                fis.read(buff);
                if (flag == 2) {
                    CountryFlag = 2;
                    EndIPOff = OffSet - 4;
                }
                OffSet = this.B2L(buff);
            } else
                break;
        } while (true);

        if (OffSet < 12) {
            return "";
        } else {
            fis.seek(OffSet);
            return getStr();
        }
    }

    private String getStr() throws IOException {
        long l = fis.length();
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        byte c = fis.readByte();
        do {
            byteout.write(c);
            c = fis.readByte();
        } while (c != 0 && fis.getFilePointer() < l);
        return byteout.toString("gbk");
    }

    private void getCountry(long ip) throws IOException {
        if (CountryFlag == 1 || CountryFlag == 2) {
            Country = getFlagStr(EndIPOff + 4);
            if (CountryFlag == 1) {
                LocalStr = getFlagStr(fis.getFilePointer());
                if (IPN >= ipToInt("255.255.255.0")
                        && IPN <= ipToInt("255.255.255.255")) {
                    LocalStr = getFlagStr(EndIPOff + 21);
                    Country = getFlagStr(EndIPOff + 12);
                }
            } else {
                LocalStr = getFlagStr(EndIPOff + 8);
            }
        } else {
            Country = getFlagStr(EndIPOff + 4);
            LocalStr = getFlagStr(fis.getFilePointer());
        }
    }

    private long getEndIP() throws IOException {
        fis.seek(EndIPOff);
        buff = new byte[4];
        fis.read(buff);
        EndIP = this.B2L(buff);
        buff = new byte[1];
        fis.read(buff);
        CountryFlag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
        return EndIP;
    }

    private long getStartIP(long RecNo) throws IOException {
        OffSet = FirstStartIP + RecNo * 7;
        fis.seek(OffSet);
        buff = new byte[4];
        fis.read(buff);
        StartIP = this.B2L(buff);
        buff = new byte[3];
        fis.read(buff);
        EndIPOff = this.B2L(buff);
        return StartIP;
    }

    public String getLocal() {
        return this.LocalStr;
    }

    public String getCountry() {
        return this.Country;
    }

    public String getDbPath() {
        return DbPath;
    }

    public void setDbPath(String dbPath) {
        DbPath = dbPath;
    }

    public String[] getProvinceAndCity(String country) {
        String[] strs = new String[2];
        for (String str : provinces) {
            if (country.contains(str)) {
                strs[0] = str;
            }
        }
        for (String str : citys) {
            if (str.contains("自治县") || str.contains("自治州")) {
                if (country.contains(str.substring(0, 2))) {
                    strs[1] = str;
                }
            } else {
                if (country.contains(str)) {
                    strs[1] = str;
                }
            }
        }
        return strs;
    }

    public static void main(String[] args) throws Exception {

        long initUsedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        long start = System.currentTimeMillis();

        IPParser w = new IPParser();
        // w.setPath(new File("QQWry2.Dat").getAbsolutePath());
        w.seek("220.182.20.156");
        System.out.println(w.getCountry() + " " + w.getLocal());
        System.out.println(w.getProvinceAndCity(w.getCountry())[1]);
        long end = System.currentTimeMillis();

        long endUsedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        System.out.println("time spent:" + (end - start) + " ns");
        System.out.println("memory consumes:" + (endUsedMemory - initUsedMemory));
    }
}
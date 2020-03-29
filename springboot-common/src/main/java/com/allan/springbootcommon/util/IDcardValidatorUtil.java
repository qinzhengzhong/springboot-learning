package com.allan.springbootcommon.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * 身份证号校验正确性工具类:
 * <p>
 * 1、地址码（ABCDEF）：表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
 * 2、出生日期码（YYYYMMDD）：表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日分别用4位、2位（不足两位加0）、2位（同上）数字表示，之间不用分隔符。
 * 3、顺序码（XXX）：表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
 * 4、校验码（R）：一位数字，通过前17位数字根据一定计算得出。
 * 5、第17位数字是表示在前16位数字完全相同时，某个公民的顺序号，并且单数用于男性，双数用于女性。如果前16位数字均相同的同性别的公民超过5人，
 * 则可以“进位”到第16位。比如：有6位女性公民前16位数字均相同，并假设第16位数是7，则这些女性公民的末两位编号分别为72，74，76，78，80，82。
 * 另外，还特殊规定，最后三位数为996，997，998，999这4个号码为百岁老人的代码，这4个号码将不再分配给任何派出所。
 * <p>
 * 身份证前六位是地区代码，用ABCDEF表示。 代码的解释规则如下：
 * A:国内区域
 * 1 华北三省二市
 * 2 东北三省
 * 3 华东六省一市
 * 4 华南六省
 * 5 西南四省一市
 * 6 西北五省
 * 7 台湾
 * 8 港澳
 * <p>
 * B:具体省（直辖市，自治区，特别行政区）代码如下：
 * 11-15 京 津 冀 晋 蒙
 * 21-23 辽 吉 黑
 * 31-37 沪 苏浙 皖 闽 赣 鲁
 * 41-46 豫 鄂 湘 粤 桂 琼
 * 50-54 渝 川 贵 云 藏
 * 61-65 陕 甘青 宁 新
 * 81-82 港 澳
 * <p>
 * CD:城市代码 从01开始排，对于直辖市，CD=01表示市辖区，CD=02表示辖县；省的城市代码从省会开始排，
 * 比如2101=沈阳 2102=大连…… 只有地级城市有独立的城市代码，县级市没有。
 * EF:市辖区、郊区、郊县、县级市代码 如果EF=00，指代这个城市，不特定区县；对于非直辖市，如EF=01，指代市辖区（任意一个区），02开始指代特定的区。
 * 其中： E=0代表市辖区， E=1代表郊区， E=2代表郊县， E=8代表县级市。 对于直辖市，从01开始就依次排区，没有市区和郊区的代码区分。
 */
public class IDcardValidatorUtil {

    /**
     * 中国公民身份证号码最小长度。
     */
    public final static int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    public final static int CHINA_ID_MAX_LENGTH = 18;

    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    private static String codeAndCity[][] = {{"11", "北京"}, {"12", "天津"},
            {"13", "河北"}, {"14", "山西"}, {"15", "内蒙古"}, {"21", "辽宁"},
            {"22", "吉林"}, {"23", "黑龙江"}, {"31", "上海"}, {"32", "江苏"},
            {"33", "浙江"}, {"34", "安徽"}, {"35", "福建"}, {"36", "江西"},
            {"37", "山东"}, {"41", "河南"}, {"42", "湖北"}, {"43", "湖南"},
            {"44", "广东"}, {"45", "广西"}, {"46", "海南"}, {"50", "重庆"},
            {"51", "四川"}, {"52", "贵州"}, {"53", "云南"}, {"54", "西藏"},
            {"61", "陕西"}, {"62", "甘肃"}, {"63", "青海"}, {"64", "宁夏"},
            {"65", "新疆"}, {"71", "台湾"}, {"81", "香港"}, {"82", "澳门"},
            {"91", "国外"}};

    private static String cityCode[] = {"11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91"};

    // 每位加权因子
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 第18位校检码
    private static String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard
     * @return
     */
    public static boolean isValidatedAllIdcard(String idcard) {
        if (idcard.length() == CHINA_ID_MIN_LENGTH) {
            //将15位的身份证转成18位身份证在进行校验
            idcard = convertIdcarBy15bit(idcard);
        }
        return isValidate18Idcard(idcard);
    }

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码；
     * 2.第3、4位数字表示：所在城市的代码；
     * 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日；
     * 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard 身份证号
     * @return true 合法，
     *         false 不合法
     */
    public static boolean isValidate18Idcard(String idcard) {
        /*** 非18位为假*/
        if (idcard.length() != CHINA_ID_MAX_LENGTH) {
            return false;
        }
        /*** 获取前17位*/
        String idcard17 = idcard.substring(0, 17);

        /*** 获取第18位*/
        String idcard18Code = idcard.substring(17, 18);
        char c[] = null;
        String checkCode = "";

        /*** 是否都为数字*/
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        if (null != c) {
            int bit[] = new int[idcard17.length()];

            bit = converCharToInt(c);
            /**
             * 将身份证的前17位数字本体码加权求和,得到和值
             */
            int sum17 = getPowerSum(bit);
            /**
             * 将和值与11取模得到余数进行校验码判断
             * 通俗解释：用和值除以11，看最后的余数
             */
            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }
            /**
             * 将身份证的第18位与算出来的校码进行匹配，不相等就为假
             */
            if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
     *
     * @param idcard
     * @return
     */
    public static boolean isValidate15Idcard(String idcard) {
        // 非15位为假
        if (idcard.length() != CHINA_ID_MIN_LENGTH) {
            return false;
        }

        // 是否全都为数字
        if (isDigital(idcard)) {
            String provinceid = idcard.substring(0, 2);
            String birthday = idcard.substring(6, 12);
            int year = Integer.parseInt(idcard.substring(6, 8));
            int month = Integer.parseInt(idcard.substring(8, 10));
            int day = Integer.parseInt(idcard.substring(10, 12));

            // 判断是否为合法的省份
            boolean flag = false;
            for (String id : cityCode) {
                if (id.equals(provinceid)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
            // 该身份证生出日期在当前日期之后时为假
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate == null || new Date().before(birthdate)) {
                return false;
            }

            // 判断是否为合法的年份
            GregorianCalendar curDay = new GregorianCalendar();
            int curYear = curDay.get(Calendar.YEAR);
            int year2bit = Integer.parseInt(String.valueOf(curYear)
                    .substring(2));

            // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
            if ((year < 50 && year > year2bit)) {
                return false;
            }

            // 判断是否为合法的月份
            if (month < 1 || month > 12) {
                return false;
            }

            // 判断是否为合法的日期
            boolean mflag = false;
            curDay.setTime(birthdate); // 将该身份证的出生日期赋于对象curDay
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    mflag = (day >= 1 && day <= 31);
                    break;
                case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                    if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                        mflag = (day >= 1 && day <= 29);
                    } else {
                        mflag = (day >= 1 && day <= 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    mflag = (day >= 1 && day <= 30);
                    break;
            }
            if (!mflag) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard 身份证号
     * @return
     */
    public static String convertIdcarBy15bit(String idcard) {
        String idcard17 = null;
        // 非15位身份证
        if (idcard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode = "";

            if (null != c) {
                int bit[] = new int[idcard17.length()];

                // 将字符数组转为整型数组
                bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);

                // 获取和值与11取模得到余数进行校验码
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位
                if (null == checkCode) {
                    return null;
                }

                // 将前17位与第18位校验码拼接
                idcard17 += checkCode;
            }
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    /**
     * 15位和18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean isIdcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
    }

    /**
     * 15位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is15Idcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
                idcard);
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is18Idcard(String idcard) {
        return Pattern
                .matches(
                        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                        idcard);
    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    public static boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     * <p>
     * 校验码是通过一系列数学计算得出来的，具体校验的计算方式如下：
     * 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i = 0, ... , 16 其中Ai表示第i位置上的身份证号码数字值，Wi表示第i位置上的加权因子，
     * 其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 通俗解释：
     * 身份证第一位数字*7+第二位*9+第三位*10+第四位*5+第五位*5+第六位*8+第七位*4+第八位*1+第九位*6+第十位*3+十一位*7+十二位*9+十三位*10+十四位*5+十五位*8+十六位*4+十七位*2；
     * 计算出总和（用S）表示。
     * 2. 以11对计算结果取模 Y = mod(S, 11) 通俗解释：用S除以11，看最后的余数。如果除尽，为0；
     * 余数为1，则计为1；最大余数为10；全部数字为0-10共11个数字。（用Y表示）。
     * 3. 根据模的值得到对应的校验码
     *
     * @param bit
     * @return
     */
    public static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     * <p>
     * 校验码是通过一系列数学计算得出来的，具体校验的计算方式如下：
     * 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i = 0, ... , 16 其中Ai表示第i位置上的身份证号码数字值，Wi表示第i位置上的加权因子，
     * 其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 通俗解释：
     * 身份证第一位数字*7+第二位*9+第三位*10+第四位*5+第五位*5+第六位*8+第七位*4+第八位*1+第九位*6+第十位*3+十一位*7+十二位*9+十三位*10+十四位*5+十五位*8+十六位*4+十七位*2；
     * 计算出总和（用S）表示。
     * 2. 以11对计算结果取模 Y = mod(S, 11) 通俗解释：用S除以11，看最后的余数。如果除尽，为0；
     * 余数为1，则计为1；最大余数为10；全部数字为0-10共11个数字。（用Y表示）。
     * 3. 根据模的值得到对应的校验码
     * <p>
     * 对应关系为：
     * Y 值： 0 1 2 3 4 5 6 7 8 9 10
     * 校验码： 1 0 X 9 8 7 6 5 4 3 2
     * <p>
     * 通俗解释：
     * 余数为0，则校验码为1；依次类推：余数为1，则校验码对应0；以下：2–X；3–9；4–8；5–7；6–6；7–5；8–4；9-3；10-2。
     * 如果校验码不符合这个规则，则肯定是假号码。 关于18位身份证号码尾数是“X”的一种解释：因为按照上面的规则，校验码有11个，而不是10个，
     * 所以不能用0-9表示。所以如果尾号是10，那么就得用X来代替，因为如果用10做尾号，那么此人的身份证就变成了19位，
     * 而19位的号码违反了国家标准，并且我国的计算机应用系统也不承认19位的身份证号码。Ⅹ是罗马数字的10，用X来代替10，可以保证公民的身份证符合国家标准。
     *
     * @param sum17
     * @return 校验位
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        /**
         * 以11对计算结果取模 Y = mod(S, 11) 通俗解释：用S除以11，看最后的余数。
         * 根据模的值得到对应的校验码
         */
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }


    /**
     * main 函数测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String idcard15 = "";
        String idcard18 = "";
        IDcardValidatorUtil iv = new IDcardValidatorUtil();
        boolean flag = false;
        flag = iv.isValidate18Idcard(idcard18);
        System.out.println(flag);

        flag = iv.isValidate15Idcard(idcard15);
        System.out.println(flag);

        System.out.println(iv.convertIdcarBy15bit(idcard15));
        flag = iv.isValidate18Idcard(iv.convertIdcarBy15bit(idcard15));
        System.out.println(flag);

        System.out.println(iv.isValidatedAllIdcard(idcard18));

    }
}

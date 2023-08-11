package com.example.temple.utils;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * 将中文标点替换为英文标点
     *
     * @param text 要替换的文本
     * @return 替换后的文本
     */
    public static String replacePunctuation(String text) {
        text = text.replace('，', ',').replace('。', '.').replace('【', '[').replace('】', ']')
                .replace('？', '?').replace('！', '!').replace('（', '(').replace('）', ')').replace
                        ('“', '"').replace('”', '"');
        return text;
    }


    /**
     * null --> ""
     *
     * @param str
     * @return
     */
    public static String toBlank(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str;
    }

    /**
     * 非Int转成 --> 0
     *
     * @param str
     * @return
     */
    public static int toInteger(String str) {
        if (!isInteger(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    /**
     * 非double转成 --> 0
     *
     * @param str
     * @return
     */
    public static int toIntegerTwo(String str) {
        if (!isDouble(str)) {
            return 0;
        }
        return Integer.parseInt(MathUtils.roundingMode(Double.parseDouble(str)));
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        //noinspection StringEquality
        if (s1 == s2) {
            return true;
        } else if (s1 != null) {
            return s1.equalsIgnoreCase(s2);
        } else {
            return false;
        }
    }

    public static String formatUs(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }


    /**
     * 给填写搜索单词的关键词显示 特殊颜色
     *
     * @param word
     * @return
     */
    public static void setTextHighLight(TextView textView, String word, String input, int color) {
        if (!TextUtils.isEmpty(word) && !TextUtils.isEmpty(input)) {
            SpannableStringBuilder style = new SpannableStringBuilder(word);
            for (int i = 0; i < input.length(); i++) {
                String s = String.valueOf(input.charAt(i));
                // 正则匹配
                Pattern p = Pattern.compile(input);
                Matcher m = p.matcher(style);
                // 查找下一个
                while (m.find()) {
                    // 字符开始下标
                    int start = m.start();
                    // 字符结束下标
                    int end = m.end();
                    // 设置高亮
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), color));
                    style.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            textView.setText(style);
        } else {
            textView.setText(word);
        }
    }

    /**
     * 给填写搜索单词的关键词显示 特殊颜色
     *
     * @param word
     * @return
     */
    public static SpannableStringBuilder setTextHighLight(String word, String input, int color, Context context) {
        int len = input.length();
        int start = 0;
        int end = 0;
        int position = 0;
        SpannableStringBuilder style = new SpannableStringBuilder(word);
        while (true) {
            position = word.indexOf(input, end);
            if (-1 == position) {
                break;
            }
            start = position;
            end = start + len;
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, color));
            style.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return style;
    }

    /**
     * 描述：将null转化为“”.
     *
     * @param str 指定的字符串
     * @return 字符串的String类型
     */
    public static String parseEmpty(String str) {
        if (str == null || "null".equals(str.trim())) {
            str = "";
        }
        return str.trim();
    }

    /**
     * null string to empty string
     * <p/>
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }

    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmptyNull(String str) {
        return str == null || str.trim().length() == 0 || str.equalsIgnoreCase("null");
    }

    /**
     * is null or its length is 0 or it is made by space
     * <p/>
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return true, else return
     * false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     * <p/>
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * compare two string
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */
    public static boolean isEquals(String actual, String expected) {
        return ObjectUtils.isEquals(actual, expected);
    }


    /**
     * capitalize first letter
     * <p/>
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(
                str.length()).append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     * <p/>
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException if an error occurs
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     * <p/>
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     *
     * @param href
     * @return <ul>
     * <li>if href is null, return ""</li>
     * <li>if not match regx, return source</li>
     * <li>return the last string that match regx</li>
     * </ul>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * process special char in html
     * <p/>
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * transform half width char to full width char
     * <p/>
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * transform full width char to half width char
     * <p/>
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 去掉双引号
     *
     * @param string
     * @return
     */
    public static String removeDoubleQuotes(String string) {
        if (string == null) {
            return null;
        }
        int length = string.length();
        if ((length > 1) && (string.charAt(0) == '"') && (string.charAt(length - 1) == '"')) {
            return string.substring(1, length - 1);
        }
        return string;
    }

    /**
     * 添加双引号
     *
     * @param string
     * @return
     */
    public static String convertToQuotedString(String string) {
        if (string == null) {
            return null;
        }
        return "\"" + string + "\"";
    }

    /**
     * 去掉字符串中的标点符号和空格
     *
     * @param input
     * @return
     */
    public static String deleteSpaceAndPunct(String input) {
        if (isEmpty(input)) {
            return "";
        }
        return input.replaceAll("[\\p{Punct}\\p{Space}]+", "");
    }

    /**
     * splitToArray({a,b,c}, ',')     =   "{a,b,c}";
     *
     * @param source    分割的字符串
     * @param separator 分割标识
     * @return
     */
    public static String[] splitToArray(String source, String separator) {
        if (isEmpty(source)) {
            return null;
        }
        return source.split(separator);
    }

    /**
     * splitToList({a,b,c}, ',')     =   "[a,b,c]";
     *
     * @param source    分割的字符串,"a,b,c"
     * @param separator 分割标识
     * @return
     */
    public static ArrayList<String> splitToList(String source, String separator) {
        if (isEmpty(source)) {
            return null;
        }
        String[] arr = source.split(separator);
        List<String> list = Arrays.asList(arr);
        ArrayList<String> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
//            result.addAll(list);
            //修复标签空白框
            for (String s : list) {
                if (!TextUtils.isEmpty(s.trim())) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    /**
     * join list to string, separator is ","
     * <p/>
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     *
     * @param source
     * @return join list to string, separator is ",". if list is empty, return ""
     */
    public static String join(int[] source) {
        return join(source, ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * join list to string
     * <p/>
     * <pre>
     * join(null, '#')     =   "";
     * join({}, '#')       =   "";
     * join({a,b,c}, ' ')  =   "abc";
     * join({a,b,c}, '#')  =   "a#b#c";
     * </pre>
     *
     * @param source
     * @param separator
     * @return join list to string. if list is empty, return ""
     */
    public static String join(int[] source, char separator) {
        return join(source, new String(new char[]{separator}));
    }

    /**
     * join list to string. if separator is null, use DEFAULT_JOIN
     * <p/>
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param source
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(int[] source, String separator) {
        if (source == null || source.length == 0) {
            return "";
        }
        if (separator == null) {
            separator = ListUtils.DEFAULT_JOIN_SEPARATOR;
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (int s : source) {
            sb.append(s);
            if (i != source.length - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * join list to string. if separator is null, use DEFAULT_JOIN
     * <p/>
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param source
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(List<Integer> source, String separator) {
        if (ListUtils.isEmpty(source)) {
            return "";
        }
        if (separator == null) {
            separator = ListUtils.DEFAULT_JOIN_SEPARATOR;
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Integer s : source) {
            sb.append(s);
            if (i != source.size() - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * join list to string. if separator is null, use DEFAULT_JOIN
     * <p/>
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param source
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(String[] source, String separator) {
        if (source == null || source.length <= 0) {
            return "";
        }
        if (separator == null) {
            separator = ListUtils.DEFAULT_JOIN_SEPARATOR;
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String s : source) {
            sb.append(s);
            if (i != source.length - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * join list to string. if separator is null, use DEFAULT_JOIN
     * <p/>
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param source
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String joinStr(List<String> source, String separator) {
        if (ListUtils.isEmpty(source)) {
            return "";
        }
        if (separator == null) {
            separator = ListUtils.DEFAULT_JOIN_SEPARATOR;
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String s : source) {
            sb.append(s);
            if (i != source.size() - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }


    // 判断是否为手机号 只校验手机号是否为11位
    public static boolean isPhone(String inputText) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(inputText);
        if (!isNum.matches() || inputText.length() != 11) {
            return false;
        }
        return true;
//        Pattern p = Pattern.compile("^(1)[3,5,7,8]\\d{9}$");
//        Matcher m = p.matcher(inputText);
//        return m.matches();
    }

    /**
     * 6-20位包含数字字母
     *
     * @param pawd
     * @return
     */
    public static boolean isPSWD(String pawd) {
        String regex = "[a-zA-Z]+";
        String regex1 = "[0-9]+";
        if (find(pawd, regex) && find(pawd, regex1)) {
            return pawd.length() >= 6 && pawd.length() <= 20;

        } else {
            return false;
        }
    }

    public static boolean find(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            return matcher.find();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取字符串中文字符的长度（每个中文算2个字符）.
     *
     * @param str 指定的字符串
     * @return 中文字符的长度
     */
    public static int chineseLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        if (!isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                /* 获取一个字符 */
                String temp = str.substring(i, i + 1);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    valueLength += 2;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    //中文字符长度为2
                    valueLength += 2;
                } else {
                    //其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            //获取一个字符
            String temp = str.substring(i, i + 1);
            //判断是否为中文字符
            if (temp.matches(chinese)) {
                //中文字符长度为2
                valueLength += 2;
            } else {
                //其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public static Boolean isMobileNo(String str) {
        Boolean isMobileNo = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobileNo;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public static Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }

    /**
     * 描述：是否是邮箱.
     *
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    /**
     * 描述：是否是中文.
     *
     * @param str 指定的字符串
     * @return 是否是中文:是为true，否则false
     */
    public static Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 是否包含中文数字字母的用户名
     * 长度1-20
     *
     * @param str
     * @return
     */
    public static boolean isConintChinseUser(String str) {
        /**此正则表达式将上面二者结合起来进行判断，中文、大小写字母和数字**/
        String all = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matcher(str).matches();
    }

    /**
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            //最后一个\n删除
            if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：标准化日期时间类型的数据，不足两位的补0.
     *
     * @param dateTime 预格式的时间字符串，如:2012-3-2 12:2:20
     * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
     */
    public static String dateTimeFormat(String dateTime) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(dateTime)) {
                return null;
            }
            String[] dateAndTime = dateTime.split(" ");
            if (dateAndTime.length > 0) {
                for (String str : dateAndTime) {
                    if (str.indexOf("-") != -1) {
                        String[] date = str.split("-");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] date = str.split(":");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * 描述：不足2个字符的在前面补“0”.
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符串
     */
    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                //size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    //size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16 | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * @param str
     * @return
     */
    public static float strToFloat(String str) {
        if (!isEmpty(str)) {
            try {
                return Float.parseFloat(str);
            } catch (Exception e) {

            }
        }
        return 0;
    }

    /**
     * 判断字符串是否是整型
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     * @since [产品/模块版本]
     */
    public static boolean isInteger(String str) {
        try {
            if (!StringUtils.isBlank(str)) {
                parseInt(str);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断字符串是否是Long
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     * @since [产品/模块版本]
     */
    public static boolean isLong(String str) {
        try {
            if (!StringUtils.isBlank(str)) {
                Long.parseLong(str);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * 判断字符串是否是Double
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     * @since [产品/模块版本]
     */
    public static boolean isDouble(String str) {
        try {
            if (!StringUtils.isBlank(str)) {
                Double.parseDouble(str);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String getRandomString(String rangeStr, int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(rangeStr.length());

            sb.append(rangeStr.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    public static String UrlPage(String strURL) {
        if (TextUtils.isEmpty(strURL)) {
            return strURL;
        }

        strURL = strURL.trim();
        String[] arrSplit = strURL.split("[?]");
        return arrSplit[0];
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    public static String TruncateUrlPage(String strURL) {
        if (TextUtils.isEmpty(strURL)) {
            return null;
        }
        strURL = strURL.trim();
        String strAllParam = null;
        String[] arrSplit = strURL.split("[?]");
        if (arrSplit.length > 1) {
            strAllParam = arrSplit[1];
        }
        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String strUrlParam = TruncateUrlPage(URL);
        if (TextUtils.isEmpty(strUrlParam)) {
            return mapRequest;
        }
        //每个键值为一组
        String[] arrSplit = strUrlParam.split("[&]");
        String[] arrSplitEqual;
        for (String strSplit : arrSplit) {
            arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!arrSplitEqual[0].equals("")) {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 计算指定画笔下指定字符串需要的宽度
     */
    public static int getTheTextNeedWidth(Paint thePaint, String text) {
        float[] widths = new float[text.length()];
        thePaint.getTextWidths(text, widths);
        int length = widths.length, nowLength = 0;
        for (int i = 0; i < length; i++) {
            nowLength += widths[i];
        }
        return nowLength;
    }


    //去除富文本解析
    public static String deleteHtml(String data) {
        try {

            String regEx_script = "<[^>]+>"; // 定义script的正则表达式
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(data);
            String txt = m_script.replaceAll(""); // 过滤script标签
            return txt;
        } catch (Exception e) {
            return "";

        }
    }


    //地址 url校验
    public static boolean isUrl(String httpUrl) {
        if (Patterns.WEB_URL.matcher(httpUrl).matches()) {
            //符合标准
            return true;
        } else {
            //不符合标准
            return false;
        }
    }

    public static boolean isGoodJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }


    public static boolean isJSONArrayJson(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    //去除json中html标签
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    public static int getKeyTime(String str, String key) {
        int index = 0; //定义变量。记录每一次找到的key的位置。
        int count = 0; //定义变量，记录出现的次数。
        //定义循环。只要索引到的位置不是-1，继续查找。
        while ((index = str.indexOf(key, index)) != -1) {
            //每循环一次，就要明确下一次查找的起始位置。
            index = index + key.length();
            //每查找一次，count自增。
            count++;
        }
        return count;
    }

    //str：目标字符串，indexStr:要查询的字符串, num:第几次
    public static int getIndex(String str, String indexStr, int num) {
        int rtn = -1;
        if (str == null || indexStr == null || num <= 0) {
            return rtn;
        }
        for (int i = 0; i < num; i++) {
            rtn = str.indexOf(indexStr);
            if (rtn == -1) {
                return rtn;
            }
            str = str.substring(num + indexStr.length(), str.length());
        }
        return rtn;
    }


    //str：去掉字符串中的换行符后是否字符串是否为空
    public static boolean delectLineFeed(String str) {
        if (isEmpty(str)) {
            return true;
        } else {
            if (isEmpty(str.replaceAll("\n", ""))) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 正则表达式过滤<img > 标签
     * @param str
     * @return
     */
    public static String cutOutImgPrefix(String str){
        String regex = "<img[^>]*>";
        return str.replaceAll(regex, "[图片]");
    }
    public static boolean isNetworkUrl(String url) {
        if (url == null || url.length() == 0) {
            return false;
        }
        return (null != url) &&
                (url.length() > 6) &&
                url.substring(0, 7).equalsIgnoreCase("http://")
                || (null != url) &&
                (url.length() > 7) &&
                url.substring(0, 8).equalsIgnoreCase("https://");
    }


    /**
     * 带有\n换行符的字符串都可以用此方法显示2种颜色
     * @param text
     * @param color1
     * @param color2
     * @param fontSize1
     * @param fontSize2
     * @return
     */
    public static SpannableStringBuilder highlight(String text, int color1, int color2, int fontSize1, int fontSize2){
        SpannableStringBuilder spannable=new SpannableStringBuilder(text);//用于可变字符串
        CharacterStyle span_0=null,span_1=null,span_2;
        int end=text.indexOf("\n");
        if(end==-1){//如果没有换行符就使用第一种颜色显示
            span_0=new ForegroundColorSpan(color1);
            spannable.setSpan(span_0, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            span_0=new ForegroundColorSpan(color1);
            span_1=new ForegroundColorSpan(color2);
            spannable.setSpan(span_0, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(span_1, end+1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            span_1=new AbsoluteSizeSpan(fontSize1);//字体大小
            spannable.setSpan(span_1, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            span_2=new AbsoluteSizeSpan( fontSize2);//字体大小
            spannable.setSpan(span_2, end+1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }


}

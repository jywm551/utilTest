
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created By Yu On 2018/5/11
 * Description：
 **/
public class tes {
    public static void main(String[] args) throws Exception {
        System.out.println(1);
    }

    /**
     * 验证手机号
     *
     * @param str 手机号
     * @return 手机号正确, true, 手机号不正确 false
     */
    public static boolean isChinaPhoneLegal(String str) {
        String regExp = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证时间是否为 yyyy/MM/dd
     *
     * @param str 时间
     * @return true, false
     */
    public static boolean isPayTimeLegal(String str) {
        String regExp = "^\\d{4}([-/.])\\d{1,2}\\1\\d{1,2}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


}

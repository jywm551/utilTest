
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created By Yu On 2018/5/11
 * Description：
 **/
public class timeUtil {
    public static void main(String[] args) throws Exception {
        Instant instant = Instant.now();
        System.out.println(instant);

    }

    // 将构造器私有化
    private timeUtil() {
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

    /**
     * 毫秒数转localDateTime
     * @param timeStamp 时间戳
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromTimeStamp(Long timeStamp) {
        if (timeStamp <= 0)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
    }

    /**
     * 获取毫秒数
     * @param localDateTime localDateTime
     * @return 毫秒数
     */
    public static Long getTimeStampFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}

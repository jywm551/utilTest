import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Yu On 2018/5/29
 * Description：
 **/
public class test {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\DELL\\Desktop\\日志\\ABTest-2018-06-08.log");
        UV(file);
        pageConversion(file, "QueryPay");
        pageConversion(file, "AppointmentComplate");
        System.out.println();
    }

    private static void pageConversion(File file, String secondPage) throws Exception {
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt;
        List<String> list = new ArrayList<String>();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            String[] split = lineTxt.split("ABTest... {2}");
            if (split[1].contains(secondPage)) {
                String[] split1 = split[1].split(" \\|\\| ");
                if (!lineTxt.contains("userId : 1 ") && !list.contains(split1[0])) {
                    System.out.println(split1[0]);
                    list.add(split1[0]);
                }
            }
        }
        bufferedReader.close();
        read.close();
        System.out.println(secondPage + " : " + list.size());
    }


    private static void UV(File file) throws Exception {
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt;
        List<String> list = new ArrayList<String>();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            String[] split = lineTxt.split("ABTest... {2}");
            String[] split1 = split[1].split(" \\|\\| ");
            if (!lineTxt.contains("userId : 1 ") && !list.contains(split1[0])) {
//                System.out.println(split1[0]);
                list.add(split1[0]);
            }
        }
        bufferedReader.close();
        read.close();
        System.out.println("UV: " + list.size());
    }

}

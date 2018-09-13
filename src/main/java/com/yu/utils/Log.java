package com.yu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By Yu On 2018/9/3
 * Description：
 **/
public class Log {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\DELL\\Desktop\\日志\\ABTest-2018-09-13.log");
//        File file = new File("C:\\Users\\DELL\\Desktop\\test.txt");
        UV(file, "UV");
        UV(file, "LoginPage");
        UV(file, "VIPCenter");
        UV(file, "PersonCenter");
        UV(file, "MengzhuaSocial");
        UV(file, "MengzhuaFM");
        UV(file, "Lesson");
        UV(file, "MengzhuaShop");
        UV(file, "saveVipInfo");
        UV(file, "StartPlaying");
//        UV(file, "StartQuery");

//        PV(file,"VIPCenter");
//        UV(file,"enterPayPage");


    }

    /**
     * 计算页面访问人数 UV
     *
     * @param file       课程
     * @param secondPage 页面名称
     */
    private static void UV(File file, String secondPage) throws Exception {
        // 读取文件
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);

        String lineTxt;
        Set<String> markList = new HashSet<>();
        // 读取每一行数据
        while ((lineTxt = bufferedReader.readLine()) != null) {
            // lineTxt= 13:59:50.608 - ABTest...  userId : 223690 || message : LoginPage
            String substring = lineTxt.substring(26);
            // substring = "userId : 223690 || message : LoginPage || mark : 18位"
            if (secondPage.equals("UV")) {
                method(substring, markList);
                markList = method(substring, markList);
            } else {
                if (substring.contains(secondPage)) {
                    markList = method(substring, markList);
                }
            }

        }
        bufferedReader.close();
        read.close();
        System.out.println(secondPage + ":" + markList.size());

    }

    private static void PV(File file, String secondPage) throws Exception {
        // 读取文件
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);

        String lineTxt;
        int count = 0;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            if (lineTxt.contains(secondPage)) {
                count++;
            }
        }
        System.out.println(secondPage + ": PV :" + count);
    }

    private static Set<String> method(String message, Set<String> markList) {
        String[] split = message.split(" \\|\\| ");
        if (message.contains("mark")) {
            if (!markList.contains(split[2].trim())) {
                markList.add(split[2].trim());
            }
        }
        return markList;
    }

    private static Set<String> method2(String message, Set<String> markList) {
        String[] split = message.split(" \\|\\| ");
        if (!message.contains("userId : 0") && !message.contains("userId : null")) {
            if (!markList.contains(split[0])) {
                markList.add(split[0]);
            }
        }
        return markList;
    }



}

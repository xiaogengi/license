package com.xg.license;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController {

    @Value("${Key1}")
    private String Key;//读取config文件中的key值
    //解密
    private Boolean desDecode(String str) {
        String t = "";
        //System.out.println("加密x后：" + EncryUtil.encrypt(t));
        t = EncryUtil.decrypt(str);
        //System.out.println("解密后：" + t);
        if(t.equals("perpetual license")) {
            return true;
        }else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String nowDate = format.format(date);
            Integer result = EncryUtil.compareDate(t,nowDate);
            if(result == -1) {
                return false;
            }
        }

        return true;
    }
    //加密
    private String desCode(String str) {
        //str为加密的截止日期
        String t = EncryUtil.encrypt(str);
        //System.out.println("加密后：" + t);
        return t;
    }


//    public static void main(String[] args) {
//        SimpleDateFormat sim = new SimpleDateFormat("yyyy-mm-dd");
////        String s = new UserController().desCode(sim.format(new Date()));
//        String s = new UserController().desCode("2019-10-21");
//        System.out.println("加密"+s);
//        System.out.println("==================================================");
//        Boolean aBoolean = new UserController().desDecode(s);
//        System.out.println("解密"+aBoolean);
//    }


    public static void main(String[] args)  {
        BufferedReader in = null;
        Process pr = null;
        try {
           String exUrl = "C:cc";
           String commandStr = new String(
                   "python C:/Users/1115393087/Desktop/qwe.py " + exUrl);
           pr = Runtime.getRuntime().exec(commandStr);
           in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
           String line = null;
           String result = "";
           while ((line = in.readLine()) != null) {
               System.err.println(line);
               result = line;
           }
           System.out.println(result);
            pr.waitFor();
            in.close();
       } catch (Exception e){
           e.printStackTrace();
       }
    }


}

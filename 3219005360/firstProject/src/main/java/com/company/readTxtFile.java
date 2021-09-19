package com.company;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*读取文件
*清除标点符号
 */
public class readTxtFile {


    public static String readfile(String s ) {

        //读取文件

        if(s==null){
            System.out.println("文本为空");
            return null;
        }
        BufferedReader br = null;
        StringBuffer sb = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(s),"UTF-8")); //这里可以控制编码
            sb = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String data =  new String(sb); //StringBuffer ==> String
        //去除标点符号
        data = data.replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥× ]" , "");

        return data;

    }

}

package me.quxiu.orderReturn.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class UDFQxEncrypt {

    public static String qxEncrypt(String data, String key) {
        //key = EncoderHandler.encodeByMD5(key);
        if (data == null || data.equals("")) {
            return null;
        }
        int x = 0;
        byte[] data_str_utf8 = null;
        try {
            data_str_utf8 = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int len = data_str_utf8.length;
        int l = key.length();
        String chr = "";

        ArrayList<Byte> li = new ArrayList<Byte>();
        for (int i = 0; i < len; i++) {
            if (x == l) {
                x = 0;
            }
            chr = chr + key.charAt(x);
            x++;
        }
        for (int i = 0; i < len; i++) {
            li.add((byte) ((data_str_utf8[i] + chr.charAt(i)) % 256));
        }
        byte[] data_bytes = new byte[li.size()];

        for (int i = 0; i < li.size(); i++) {
            data_bytes[i] = li.get(i);
        }


        return EncoderHandler.encodeBase64(data_bytes);
    }

    public static String qxEncrypt(String data) {
        return qxEncrypt(data, "a8edf25e0e86d001b079ecab5d7edb96");
    }

    public static void main(String[] args) {
        System.out.println(qxEncrypt("fenzqu@126.com"));
        System.out.println(qxEncrypt("晴雨"));
        System.out.println();
    }
}

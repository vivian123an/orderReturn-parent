package me.quxiu.orderReturn.util;

import java.io.UnsupportedEncodingException;

public class UDFQxDecrypt {
    public static String qxDecrypt(String data, String key) {
        //key = EncoderHandler.encodeByMD5(key);
        if (data == null || data.equals("")) {
            return null;
        }
        int x = 0;
        byte[] data_bits = EncoderHandler.decodeBase64(data);
        int len = data_bits.length;
        int l = key.length();
        String chr = "";
        String str = "";
        for (int i = 0; i < len; i++) {
            if (x == l) {
                x = 0;
            }
            chr = chr + key.charAt(x);
            x++;
        }
        byte[] data_str_utf8 = new byte[len];
        for (int i = 0; i < len; i++) {
            if (data_bits[i] < chr.charAt(i)) {
                data_str_utf8[i] = (byte) ((data_bits[i] + 256) - chr.charAt(i));
            } else {
                data_str_utf8[i] = (byte) ((data_bits[i]) - chr.charAt(i));
            }
        }

        try {
            str = new String(data_str_utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return str;
    }

    public static String qxDecrypt(String data) {
        return qxDecrypt(data, "a8edf25e0e86d001b079ecab5d7edb96");
    }

    public static void main(String[] args) {
        System.out.println(qxDecrypt("uGmdnJ1raJ5nmGlv", "a8edf25e0e86d001b079ecab5d7edb96"));
        System.out.println(qxDecrypt("dc483e80a7a0bd9ef71d8cf973673924"));
        System.out.println("你好".charAt(0));
        System.out.println("你好".charAt(1));
    }

}

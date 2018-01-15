package cn.edu.zucc.uvtp.control;

import cn.edu.zucc.uvtp.itf.IOthers;
import net.sourceforge.pinyin4j.PinyinHelper;

public class OthersManager implements IOthers {
	public boolean judge(boolean n){
		return n;
	}
	 public static String getPinYinHeadChar(String str) {

         String convert = "";
         for (int j = 0; j < str.length(); j++) {
             char word = str.charAt(j);
             String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
             if (pinyinArray != null) {
                 convert += pinyinArray[0].charAt(0);
             } else {
                 convert += word;
             }     
         }
         convert=convert.toUpperCase();
         return convert.substring(0, 1).trim();
     }
}

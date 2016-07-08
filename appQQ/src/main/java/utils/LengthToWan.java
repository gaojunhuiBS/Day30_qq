package utils;

/**
 * Created by Administrator on 2016/6/30.
 */
public class LengthToWan {
    public static String lengByWan(int count){
        String str=count+"";
        int length=str.length();
        if (length<=4){
            return str;
        }else {
        String num1=(count/10000)+"";
            String num2=((count%10000)/1000)+"";
            return num1+"."+num2+"万";
        }

    }
}

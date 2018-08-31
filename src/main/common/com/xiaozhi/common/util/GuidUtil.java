package com.xiaozhi.common.util;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by bxl on 2015/9/18.
 */
public class GuidUtil {
    protected final Logger logger = Logger.getLogger(GuidUtil.class.getName());
    static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    public static int num = 0;
    private static Object obj = new Object();
    public static String fnstr = "00000000000000";

    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    private static String getId() {
        return GuidUtil.getUUID();
    }

    public static String getGuid() {
        return GuidUtil.getUUID();
    }
    
    public static String getTimestapId() {
    	String id = GuidUtil.getUUID();
    	id=	df.format(System.currentTimeMillis())+id.substring(14);
        return id;
    }

    public static String getFileName() {
        //SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        synchronized (obj) {
            /* if (!df.format(new Date()).equals(fnstr.substring(0, 14))) {
                num = 0;
            }*/
            if (num >= 99999) {
                num = 0;
            }
            num++;
        }
        fnstr = df.format(System.currentTimeMillis()) + new DecimalFormat("00000").format(num);
        return fnstr;
    }

    public static void main(String[] args) throws InterruptedException {
    	
    	System.out.println(GuidUtil.getGuid());
    	System.out.println(GuidUtil.getGuid());
    	
        System.out.println(System.currentTimeMillis() + "");
        System.out.println(getFileName());
        
        System.out.println(getTimestapId());
        /* for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            System.out.println(getFileName());
        }*/
    }
}

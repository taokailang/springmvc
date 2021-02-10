package com.controller.util;

public class OsNameUtil {
    /**
     * 获取当前系统名称，全部小写
     * @return
     */
    public static String getSystemOSName()
    {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取当前系统类型
     * @return OSType.OS_LINUX:Linux系统
     * 		   OSType.OS_WINDOWS:Windows系统
     * 		   OSType.OS_MAC:Mac系统
     * 		   OSType.OS_OTHERS:其他系统
     */
    public static int getCurrentOS()
    {
        String osname = getSystemOSName();
        if(osname.indexOf("linux") >= 0) {
            return 2;
        } else if(osname.indexOf("windows") >= 0) {
            return 1;
        } else if(osname.indexOf("mac") >= 0 && osname.indexOf("os") >= 0) {
            return 3;
        } else {
            return 4;
        }
    }
}

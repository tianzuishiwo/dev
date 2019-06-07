package com.wsh.thirdlibrary.utils;

import java.util.List;

/**
 * Author:      wuShaoHua
 * Email:       525427151@qq.com | wushaohua0521@163.com
 * GitHub:      https://github.com/tianzuishiwo
 * Date:        2019/6/7 12:55
 * Description: dev
 */
public class ListUtil {
    public static boolean isListEmpty(List list){
        if (list!=null&& !list.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isArrEmpty(String[] list){
        if (list!=null&& list.length>0){
            return false;
        }
        return true;
    }
}

package com.yangchun.tokenandfiletry.utils;

import java.util.Random;

/**
 * Copy from 廖师兄
 * @author tianyi
 * @date 2018-02-15 16:46
 */
public class KeyUtil {
    /**
     * 生成唯一的主键 TODO 欠synchronized理解
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}

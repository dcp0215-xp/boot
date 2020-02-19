package com.mybatis.boot.util;

import com.mybatis.boot.vo.LayuiResult;

/**
 * @Author LX
 * @Date 2020/2/19 21:29
 * @Description ���ع�����
 */
public class ResultUtil {

    public static LayuiResult success() {
        return new LayuiResult(200, "�ɹ�", null);
    }

    public static LayuiResult success(String msg) {
        return new LayuiResult(200, msg, null);
    }

    public static LayuiResult success(Object data) {
        return new LayuiResult(200, "�ɹ�", data);
    }

    public static LayuiResult error(Integer code, String msg) {
        return new LayuiResult(code, msg, null);
    }
}

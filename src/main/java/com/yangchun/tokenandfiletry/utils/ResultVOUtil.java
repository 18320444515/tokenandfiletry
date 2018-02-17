package com.yangchun.tokenandfiletry.utils;

import com.yangchun.tokenandfiletry.VO.ResultVO;

/**
 * Copy from 廖师兄
 * @author tianyi
 * @date 2018-02-15 16:50
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

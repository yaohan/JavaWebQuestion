package com.webapp.model.Resp;

import com.webapp.model.table.Clazz;

import java.util.List;
import java.util.Map;

/**
 * Created by yao_han on 2015/12/25.
 */
public class GetClazzResp extends BaseResp{
    public static String DESC_SUCCESS = "获取成功";

    private List<Clazz> classList;

    public GetClazzResp() {
    }

    public List<Clazz> getClassList() {
        return classList;
    }

    public void setClassList(List<Clazz> classList) {
        this.classList = classList;
    }
}

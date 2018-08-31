package com.xiaozhi.common.domain;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.common.interceptor.Pager;

import java.util.HashMap;
import java.util.List;

public class BaseJsonResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 2499993132072019199L;

    public BaseJsonResult() {
    }

    public static BaseJsonResult success() {
        BaseJsonResult result = new BaseJsonResult();
        result.put("status", true);
        return result;
    }

    public static BaseJsonResult success(Object value) {
        if (value instanceof Pager) {
            return success("pager", value).put("list", ((Pager) value).getList());
        } else if (value instanceof List) {
            return success("list", value);
        }
        return success("data", value);
    }

    public static BaseJsonResult success(String key, Object value) {
        BaseJsonResult result = success();
        result.put(key, value);
        return result;
    }


    public static BaseJsonResult error() {
        return error("系统异常");
    }

    public static BaseJsonResult error(String msg) {
        BaseJsonResult result = new BaseJsonResult();
        result.put("status", false);
        result.put("msg", msg);
        return result;
    }

    public boolean getStatus() {
        return (boolean) this.get("status");
    }

    @Override
    public BaseJsonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static void main(String[] args) {
		/*String item = "item";
		
		ArrayList<String> list  =new ArrayList<String>();
		list.add("list");
		
		BaseJsonResult result1 = BaseJsonResult.success("list", list);
		System.out.println(JsonUtil.getJsonString(result1));
		
		BaseJsonResult result2 = BaseJsonResult.success("item", item);
		System.out.println(JsonUtil.getJsonString(result2));
		
		BaseJsonResult result3 = BaseJsonResult.success("item", item).put("list", list);
	
		System.out.println(JsonUtil.getJsonString(result3));*/
        BaseJsonResult succ = BaseJsonResult.success();
        System.out.println(JSON.toJSON(succ));


        System.out.println(JSON.toJSON(BaseJsonResult.error()));
    }
}

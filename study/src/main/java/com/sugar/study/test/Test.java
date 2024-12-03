package com.sugar.study.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
public class Test {
    public static void main(String[] args) {
//        strContains();
//        contains$();

        JSONObject BUSI_INFO = new JSONObject();
        JSONObject PUB_INFO = new JSONObject();
        JSONObject REQ_PARAM = new JSONObject();
        JSONObject json = new JSONObject();
        BUSI_INFO.put("clientId",null);
        REQ_PARAM.put("BUSI_INFO",BUSI_INFO);
        REQ_PARAM.put("PUB_INFO",PUB_INFO);

        json.put("REQ_PARAM",REQ_PARAM);
//        String jsonStringWithNull = json.toJSONString(SerializerFeature.WriteMapNullValue);

        log.info("{}", json);

        // 创建一个 JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Alice");
        jsonObject.put("age", null);
        jsonObject.put("city", "New York");

        // 默认情况下 toJSONString 会忽略值为 null 的键
        String defaultJsonString = jsonObject.toJSONString();
        System.out.println("默认 JSON 字符串: " + defaultJsonString);

        // 使用 SerializerFeature.WriteMapNullValue 保留值为 null 的键
        String jsonStringWithNull = jsonObject.toString(SerializerFeature.WriteMapNullValue);
        System.out.println("保留 null 值的 JSON 字符串: " + jsonStringWithNull);

    }

    private static void contains$() {
        String exp = "if(${1012.在网月数}==${1022.在网天数}) return ture";
        List<String> list=new ArrayList<>();
        Map<Integer, Integer> map = indexItem(exp + "e");
        System.out.println(map);
        map.forEach((i,k)->list.add(exp.substring(i, k+1))
        );
        System.out.println(list);
        String join = StringUtils.join(list, ",");
        System.out.println(join);
    }

    private static Map<Integer,Integer> indexItem(String expExtension) {
        Map<Integer,Integer> map=new HashMap<>();
        if (expExtension.contains("${") && expExtension.contains("}")){
            char[] chars = expExtension.toCharArray();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i]=='$'&& chars[i+1]=='{'){
                    stack.push(i);
                }
                if (chars[i]=='}'&&!stack.isEmpty()){
                    map.put(stack.pop(),i);
                }
            }
        }
        return map;
    }

    private static void strContains() {
        String filename="我方开具CN_DN统计表-202405_一卡多号.xlsx";
        String filenamePrefix="一卡多号我方开具CN_DN统计表";
        System.out.println(filename.contains(filenamePrefix));

        List<Integer> list=new ArrayList();
        list.add(1);
        System.out.println(CollectionUtils.isEmpty(list));
    }
}

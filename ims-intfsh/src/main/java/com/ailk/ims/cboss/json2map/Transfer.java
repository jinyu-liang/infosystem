package com.ailk.ims.cboss.json2map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jef.tools.JsonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;

public class Transfer {

    private transient static Log log = LogFactory.getLog(Transfer.class);

    public static Map parseData(Object object) throws IMSException {
        Map param = null;
        try {
            log.error("手机支付上行cboss接口调用返回值为：" + object);
            Map obj=JsonUtil.toMap(object.toString().trim());
            if (obj != null) {
                param = (Map) Transfer.transfer(obj);
            }
        } catch (Exception e) {
        	throw new IMSException(ErrorCodeDefine.PARSE_DATA_ERROR);
        }

        return param;
    }

    public static Object transfer(Object object) throws Exception {
        Object retObj = null;

        if (object != null) {
            if (object instanceof Map) {
                Iterator iterator = ((Map) object).entrySet().iterator();
                Map map = new HashMap();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    map.put(key, transfer(value));
                }
                retObj = map;
            } else if (object instanceof List) {
                List list = new ArrayList();
                for (int i = 0; i < ((List) object).size(); i++) {
                    Object value = ((List) object).get(i);
                    list.add(transfer(value));
                }
                retObj = list;
            } else {
                if (object instanceof String) {
                    retObj = object;
                } else {
                    retObj = String.valueOf(object);
                }
            }
        }

        return retObj;
    }
}

package common_util;

import com.wode.common.util.ActResult;
import com.wode.common.util.JsonUtil;

public class TestJson {

	
	public static void main(String[] args) {
		String str="{\"@type\":\"com.wode.model.ActResult\",\"msg\":\"非法的来源:218.247.254.133\",\"success\":false}";
		ActResult ret=JsonUtil.getObject(str, ActResult.class);
		System.out.println(ret.getMsg());
	}
}

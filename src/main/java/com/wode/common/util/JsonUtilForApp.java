package com.wode.common.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 

public class JsonUtilForApp implements Serializable{
	private final String status = "status";//״̬
	private final String msg="message";//��Ϣ
	private JSONObject json = new JSONObject();
	private JSONArray array = new JSONArray();
	private List list = new ArrayList();
	
	public JSONArray getArray() {
		return array;
	}
	public void setArray(JSONArray array) {
		this.array = array;
	}
	public List getList() {
		return list;
	}
	public String getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	
	public JSONObject getJson() {
		return json;
	}
	//ֻ����״̬��״̬��Ϣ
	public String json(int status,String message){
		json.put(this.getStatus(), status);
		json.put(this.getMsg(), message);
		return json.toString();
	}
//	public List<OrderInfo> jsonExpress(String jsonStr){
//		List<OrderInfo> order = new ArrayList<OrderInfo>();
//		JSONObject js = json.fromObject(jsonStr.toString());
//		//��ݹ�˾���
//		String expressFirm = js.getString("com");
//		String expressNumber = js.getString("nu");
//		JSONArray jsonarr = array.fromObject(js.get("data"));
//		for(int i =(jsonarr.size()-1); i > -1;i--){
//			OrderInfo or = new OrderInfo();
//			//�����Ϣ
//			String orderInfo = jsonarr.getJSONObject(i).getString("context");
//			//�����תʱ��
//			String expressTime  = jsonarr.getJSONObject(i).getString("time");
//			or.setOrderNumber(expressNumber);
//			or.setOrderInfo(orderInfo);
//			or.setOrderfirm(expressFirm);
//			or.setOrderTime(expressTime);
//			order.add(or);
//		}
//		return order;
//	}
	
	
//	public String jsonJsessionId(int status,String jsessionId,String message){
//		json.put(this.getStatus(), status);
//		json.put(this.getMsg(), message);
//		json.put(jsessionId, jsessionId);
//		return json.toString();
//	}
//	
	
	
	public String jsonObjectInfo(int status,String message,Object obj){
		json.put(this.status,status);
		json.put(this.msg,message);
		json.put("info",obj);
		return json.toString();
	}
	public String jsonCoupon(int status,String message,Object obj,Object obj2){
		json.put(this.status,status);
		json.put(this.msg,message);
		json.put("info",obj);
		json.put("coupon",obj2);
		return json.toString();
	}
	public String jsonRestaurantCoupon(int status,String message,Object obj,Object obj2){
		json.put(this.status,status);
		json.put(this.msg,message);
		json.put("RestaurantCoupon",obj);
		json.put("coupon",obj2);
		return json.toString();
	}
	public String jsonVipCardCoupon(int status,String message,Object info ,Object RestaurantCoupon,Object coupon){
		json.put(this.status,status);
		json.put(this.msg,message);
		json.put("card",info);
		json.put("restaurantCoupon",RestaurantCoupon);
		json.put("coupon",coupon);
		return json.toString();
	}
	public String jsonVersion(String version,String appUrl){
		json.put("version", version);
		json.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		json.put("appUrl", appUrl);
		return json.toString();
	}
	
	public String json(String status,String msg,Map map){
		list.add(map);
		json.put("status", status);
		json.put("msg", msg);
		json.put("list", list);
//		json.put(key, value);
		return json.toString();
	}
	public String json(String status,String msg,int Number){
		json.put("status", status);
		json.put("msg", msg);
		json.put("list", Number);
//		json.put(key, value);
		return json.toString();
	}
	public String json(String status,String msg,List list,List topPhoto_list){
		json.put("status", status);
		json.put("msg", msg);
		json.put("list",list);
		json.put("topPhoto_list", topPhoto_list);
		return json.toString();
	}
	public String json(String status,String msg,List list){
		json.put("status", status);
		json.put("msg", msg);
		json.put("list",list);
		return json.toString();
	}
	public String json(String status,String msg,List list,int number){
		json.put("status", status);
		json.put("msg", msg);
		json.put("list", list);
		json.put("number", number);
//		json.put(key, value);
		return json.toString();
	}
	public String versionUpdate(String str){
		json.put("VersionInfo", str);
		return json.toString();
	}
	public String guanYuWo(String str){
		json.put("info", str);
		return json.toString();
	}
	public String json(String boo,int id,String username,String time){
		json.put("status", boo);
		json.put("id",id);
		json.put("userName",username);
		json.put("registertime",time);
		return json.toString();
	}
}

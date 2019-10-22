package cn.net.zhangyibing.utils;


import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

final public class StringUtils {

	private StringUtils(){}
	
	public static Object getIfString(Object o ){
		if( o != null && o instanceof String)
			return (o+"").trim();
		return o;
	}
	public static int toInt(Object value,int defaultValue){
		if(StringUtils.isNull(value)) return defaultValue;
		try{
			return Integer.parseInt(value+"");
		}catch (Exception e) {
			return defaultValue;
		}
	}
	public static double toFloat(Object value,double defaultValue){
		if(StringUtils.isNull(value)) return defaultValue;
		try{
			return Double.parseDouble(value+"");
		}catch (Exception e) {
			return defaultValue;
		}
	}
	/**
	 * 方法说明：将字符串分隔组装成Map
	 * @param str 要分割的字符串 ；
	 * key分隔符为 :   
	 * value分隔符为 ;
	 * @return
	 */
	public static LinkedHashMap<String, String> str2Map(String str){
		return str2Map(str,":",";");
	}
	/**
	 * 方法说明：将字符串分隔组装成Map
	 * @param str 要分割的字符串
	 * @param keySpilt key分隔符
	 * @param valueSpilt value分隔符
	 * @return
	 */
	public static LinkedHashMap<String, String> str2Map(String str,String keySpilt,String valueSpilt){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if(str != null){
			String[] conds = str.split(valueSpilt);
			for(String cond:conds){
				String[] ss = cond.split(keySpilt);
				if(ss.length>=2){
					map.put(ss[0], ss[1]);
				}else
					map.put(ss[0], ss[0]);
			}
		}
		return map;
	}
	
	public static Map<String,Map<String,Object>> list2Map(List<Map<String,Object>> list,String keyField){
		Map<String,Map<String,Object>> map = new HashMap<String, Map<String,Object>>();
		if(StringUtils.isNull(list)) return map;
		for(Map<String,Object> temp: list)
			map.put(temp.get(keyField)+"", temp);
		return map;
	}
	public static Map<String,Object> list2Map(List<Map<String,Object>> list,String keyField,String valueField){
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNull(list)) return map;
		for(Map<String,Object> temp: list)
			map.put(temp.get(keyField)+"", temp.get(valueField));
		return map;
	}
	
	
	/**
	 * 方法说明：判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		boolean flag = false;
		if(obj==null)//一般对象
			flag = true;
		else if(obj instanceof Collection && ((Collection)obj).isEmpty())//没装数据的集合
			flag = true;
		else if(obj instanceof Map && ((Map)obj).isEmpty())//没装数据的Map
			flag = true;
		else if("".equals((obj+"").trim()) || "null".equals((obj+"").trim()))//字符串判断
			flag = true;
		else if(obj.getClass().isArray() && Array.getLength(obj)<=0){//数组判断
			flag = true;
		}
		return flag;
	}
	public static boolean isTrue(String flag){
		return "true".equalsIgnoreCase(flag) ? true : false;
	}
	public static boolean isFalse(String flag){
		return "false".equalsIgnoreCase(flag) ? false : true;
	}
	/**
	 * 方法说明：字符串 倒序输出
	 * @param str
	 * @return
	 */
	public static String reverse(String str){
		if(isNull(str)) return str;
		String result = "";
		for(int i=0;i<str.length();i++){
			result = str.charAt(i)+result;
		}
		return result;
	}
	public static  String generatoryId(){
		return UUID.randomUUID().toString().substring(0, 30).toString().replace("-", "");
	}
	public static  String generatoryId32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static  String set2String(Set<String> set,String file){
		return List2String(new ArrayList<String>(set),file);
	}
	public static  String List2String(List<String> list,String file){
		if(list == null || list.isEmpty())
			return "where 1=1";
		String str=" where ";
		int length = list.size();
		if(length < 1000)
			str = str + file + " in "+ List2String(list);
		else{
			int size = 998;
			int count = (length / size) + 1;
			for(int i=0;i<count-1;i++){
				str = str + file + " in " + List2String(list.subList(i*size, (i+1)*size)) + " or ";
			}
			str = str + file + " in " + List2String(list.subList((count-1)*size, list.size()));
		}
		return str;
	}
	
	public static  String Array2String(String[] ids,String file){
		return List2String(Arrays.asList(ids),file);
	}
		
	private static String List2String(List<String> list){
		if(list==null || list.isEmpty())
			return "";
		StringBuffer str = new StringBuffer("(");
		for(String key : list){
			str.append("'").append(key).append("',");
		}
		String ret = str.substring(0, str.length()-1);
		return ret + ")";
//		return list.toString().replace("[", "('").replace(",", "','").replace(" ", "").replace("]", "')");//值中如果包含空格，此种处理方式会过滤掉空格
	}
	public static  String List2StringNoWhere(List<String> list,String file){
		if(list == null || list.isEmpty())
			return "";
		String str=" ";
		int length = list.size();
		if(length < 1000)
			str = str + file + " not in "+ List2String(list);
		else{
			int size = 998;
			int count = (length / size) + 1;
			for(int i=0;i<count-1;i++){
				str = str + file + " not in " + List2String(list.subList(i*size, (i+1)*size)) + " or ";
			}
			str = str + file + " not in " + List2String(list.subList((count-1)*size, list.size()));
		str="("+str+")";
		}
		System.out.println("List2StringNoWhere>>>>>  "+str);
		return str;
	}
	/**
	 * 组装order by语句
	 * @param orderMap
	 * @return
	 */
	public static String map2OrderBy(LinkedHashMap<String, String> orderMap){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderMap!=null && orderMap.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderMap.keySet()){
				orderbyql.append(key).append(" ").append(orderMap.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	public static Collection<List<String>> listZhuanzhi (List<List<String>> values) {
		Map<Integer,List<String>> map = new LinkedHashMap<Integer,List<String>>();
		for(List<String> value : values)
			for(int i=0;i<value.size();i++){
				List<String> temp = map.get(i);
				if(temp==null)
					temp= new ArrayList<String>();
				temp.add(value.get(i));
				map.put(i, temp);
			}
		return map.values();
	}
	
	public static String getCurrentTime(){
		return new Timestamp(System.currentTimeMillis()).toString().substring(0, 19);
	}
	
}

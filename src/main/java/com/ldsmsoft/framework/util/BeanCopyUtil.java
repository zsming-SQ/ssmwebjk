package com.ldsmsoft.framework.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/***
 * JavaBean��HashMap������
 */

public class BeanCopyUtil {
	
	/**
	 * ��ӡ����
	 * @param o
	 * @throws Exception
	 */
	public static void print(Object o)  throws Exception{
		BeanInfo beanInfo=Introspector.getBeanInfo(o.getClass(),Object.class);
		PropertyDescriptor[] prop=beanInfo.getPropertyDescriptors();
		for (int i=0;i<prop.length;i++){
			System.out.println("�ֶ�Ϊ:"+prop[i].getName()+",����Ϊ��"+prop[i].getPropertyType());
			System.out.println("��ֵΪ:"+prop[i].getReadMethod().invoke(o, new Object[] {}));
		}
	}
	
	/**
	 * ��ӡHashMap
	 * @param hm
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void printHm(HashMap hm)  throws Exception{
		int size=hm.keySet().size();
		for (Object o : hm.keySet()) {
			System.out.println("����Ϊ��"+o.toString());
			System.out.println("��ֵΪ��"+hm.get(o));
		}
	}
	

	/**
	 * ���󿽱�������ģʽ��
	 * @param fromObj
	 * @param toObj
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void SingleCopy(Object fromObj,Object toObj) throws Exception {
		BeanInfo fromBean=Introspector.getBeanInfo(fromObj.getClass(),Object.class);
		BeanInfo toBean=Introspector.getBeanInfo(toObj.getClass(),Object.class);
		PropertyDescriptor[] fromprops=fromBean.getPropertyDescriptors();
		PropertyDescriptor[] toprops=toBean.getPropertyDescriptors();
		
		for (int i = 0; i < fromprops.length; i++){
			for (int j = 0; j < toprops.length; j++){
				//������ͬ  ������ͬ
				if (fromprops[i].getName().equals(toprops[j].getName()) && fromprops[i].getPropertyType()==toprops[j].getPropertyType() ){
					Object fromValue=fromprops[i].getReadMethod().invoke(fromObj,new Object[] {});
					toprops[j].getWriteMethod().invoke(toObj, new Object[]{fromValue});
				}
			}
		}
	}
	
	/**
	 * �����󿽱���HashMap��
	 * @param fromObj
	 * @param hm
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void CopytoHashMap(Object fromObj,HashMap hm) throws Exception {
		BeanInfo fromBean=Introspector.getBeanInfo(fromObj.getClass(),Object.class);
		PropertyDescriptor[] fromprops=fromBean.getPropertyDescriptors();
		for (int i = 0; i < fromprops.length; i++){
			Object fromValue=fromprops[i].getReadMethod().invoke(fromObj,new Object[] {});
			String text="";
			if (fromprops[i].getPropertyType() == Date.class) {
				text = fromValue==null?"":DateUtil.formatDate((Date) fromValue);
			} else {
				text = fromValue==null?"":fromValue.toString();
			}
			hm.put(fromprops[i].getName(),text);
		}
	}
	
	/**
	 * ��HashMap������������
	 * @param Obj
	 * @param hm
	 * @throws Exception
	 */
	public static void CopyfromHashMap(Object obj,HashMap hm) throws Exception {
		CopyIgnoreProperty.copyHashMap(hm, obj);
	}
	
}

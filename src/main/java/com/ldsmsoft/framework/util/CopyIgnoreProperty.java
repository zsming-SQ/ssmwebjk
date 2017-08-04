package com.ldsmsoft.framework.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.common.webconfig.AppException;

/**
 * ʵ����������������ͬ������ֵ�Ŀ��������ÿ�����������
 * @updated 2008-04-15
 */

public class CopyIgnoreProperty extends CopyEventBean{

    /**
     * @$comment ʵ����������������ͬ������ֵ�Ŀ��������ÿ�����������
     * ǰ������������ͬ���������Ե��ֶ�������ֵ��
     * ���������͵�ע��:�������������ָ������CopyEventBean.isBasicClass������У�������,�Ժ�����������Ҫ�����͡�
     * ���ܣ�
     * 1������������ͬ�����Ե�ֵ�Ļ�����
     * 2���������͵�String�͵�ֵ�Ļ�����
     * 3��String�͵������������͵�ֵ�Ļ�����
     * 4��Date�͵�Long�͵ĵ����Ե�ֵ�Ļ�����
     * 5��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Long�͵����Ե�ֵ�Ŀ���������ģ�����ʵ��Long��String�������͡�
     * 6��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Date�͵����Ե�ֵ�Ŀ���������ģ�����ʵ��Date��String�������͡�
     * 7���ȵȡ�
     * ȱ����
     * 1���޷�ʵ�ֱ�����Double��Long��ת����
     * 2���Լ����޷�ʵ�ֵ����ͻ�����ֻ����ʹ�ó���ʱ�ټ���ȡ��
     * @param fromObj �����ƶ���
     * @param toObj ���ƶ���
     * @throws AppException
	 * @code
	 * public class Ab01 {
          private String eab001;
          private Long eab002;
          private Double eab003;
          ......
        }
        public class Test {
          private String eab001;
          private String eab002;
          private String eab003;
          ......
        }
	    Ab01 ab01=new Ab01();
        Test test=new Test();
		test.setEab001("1");
		test.setEab002(Long.valueOf("3333333333"));
		test.setEab003(Double.valueOf("22.0"));
        copyIgnoreProperty.Copy(test,ab01);
     */
	public static void copy(Object fromObj, Object toObj) throws AppException{
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;
		try {
			fromBeanInfo = Introspector.getBeanInfo(fromObj.getClass(),
					Object.class);
			toBeanInfo = Introspector.getBeanInfo(toObj.getClass(),
					Object.class);

			PropertyDescriptor[] fromProperties = fromBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo
					.getPropertyDescriptors();
   
			for (int i = 0; i < fromProperties.length; i++){
				for (int j = 0; j < toProperties.length; j++){
					if (fromProperties[i].getName().equals(toProperties[j].getName())) {
						Object fromValue=fromProperties[i].getReadMethod().invoke(fromObj,new Object[] {});
						if(fromValue!=null&&!fromValue.toString().equalsIgnoreCase("")){
							if (isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType()){
								toProperties[j].getWriteMethod().invoke(toObj,new Object[] { fromValue });
								break;
							}
							else if (isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&&fromProperties[i].getPropertyType()!=toProperties[j].getPropertyType()) {
								if(toProperties[j].getPropertyType()== Long.class||toProperties[j].getPropertyType()== long.class)
							    {
							    	
								    if(fromValue.toString().indexOf("-")>0)
								    {
								    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(fromValue.toString().substring(0, 10)) });
								    	break;
								    }
								    if(fromValue.getClass()==Date.class)
								    {
								    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(DateUtil.formatDate((Date)fromValue)) });
								    	break;
								    }
								    toProperties[j].getWriteMethod().invoke(toObj,new Object[] { Long.valueOf(fromValue.toString()) });
								    break;
							    }
							    if(toProperties[j].getPropertyType()== Integer.class||toProperties[j].getPropertyType()== int.class)
						     	{
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { Integer.valueOf(fromValue.toString()) });
							    	break;
						     	}
							    if(toProperties[j].getPropertyType()== Float.class||toProperties[j].getPropertyType()== float.class)
							    {
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { Float.valueOf(fromValue.toString()) });
							    	break;
							    }
							    if(toProperties[j].getPropertyType()== Date.class)
							    {
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { DateUtil.stringToDate(fromValue.toString(),DateUtil.NORMAL_DATE_FORMAT) });
							    	break;
							    }
							    if(toProperties[j].getPropertyType()== Short.class||toProperties[j].getPropertyType()== short.class)
							    {
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { Short.valueOf(fromValue.toString()) });
							    	break;
							    }
							    if(toProperties[j].getPropertyType()== Double.class||toProperties[j].getPropertyType()== double.class)
							    {
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { Double.valueOf(fromValue.toString()) });
							    	break;
							    }
							    if(toProperties[j].getPropertyType()== String.class)
							    {
							    	toProperties[j].getWriteMethod().invoke(toObj,new Object[] { fromValue.toString() });
							    	break;
							    }
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new AppException("���Ը���ʧ��(" + e.getMessage() + ")");
		}
	}
	
	/**
     * @$comment ʵ���˰Ѵ����HashMap���������ݿ���������ʵ����
     * ���ܣ�
     * 1������ʱ���뿼��toObj�����Ե����͡�
     * 2�����͵�ת����
     *    2.1������ת����Long,Integer,Float,Date,Short,Double,String,Boolean��
     *    2.2��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Long�͵����Ե�ֵ��ת����
     *    2.3��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Date�͵����Ե�ֵ��ת����
     *    2.4���ȵȡ�
	 * @param fromHashMap �����Ƶ�HashMap
	 * @param toObj ���ƵĶ���
	 * @throws AppException
	 * @code
	 * 	CommonQueryBS query_ac58_money=new CommonQueryBS();
		query_ac58_money.setConnection(sess.connection());	
	    query_ac58_money.setQuerySQL("select sum(akc087) as akc087,sum(ekc001) as ekc001,sum(eac005) as eac005 from ac58  where aac001="+aac001+"");
		Vector<?> vector_medical=query_ac58_money.query();
		Iterator<?> iterator_medical = vector_medical.iterator();
		ArchiveQueryMedicalPara medicalPara=dto.new ArchiveQueryMedicalPara();
		while (iterator_medical.hasNext()){
			HashMap tmp= (HashMap)iterator_medical.next();
			CopyIgnoreProperty.copyHashMap(tmp, medicalPara);
		}       
     */
	@SuppressWarnings("unchecked")
	public static  void copyHashMap(HashMap fromHashMap, Object toObj) throws AppException{
		BeanInfo toBeanInfo = null;
		Integer hashMapSize=fromHashMap.keySet().size();
		try {
			toBeanInfo = Introspector.getBeanInfo(toObj.getClass(),Object.class);
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			for (PropertyDescriptor toPropertie:toProperties)
			{
				Integer toPropertieSize=0;
				String Property= toPropertie.getName();
		    	Object fromValue=fromHashMap.get(Property);
				if(fromValue!=null&&!fromValue.toString().equalsIgnoreCase("")){
					    if(hashMapSize.toString().equals(toPropertieSize.toString())){
					    	return;
					    }
					    toPropertieSize++;
						if(toPropertie.getPropertyType()== Long.class||toPropertie.getPropertyType()== long.class)
					    {
					    	
						    if(fromValue.toString().indexOf("-")>0)
						    {
						    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(fromValue.toString().substring(0, 10)) });
						    	continue;
						    }
						    if(fromValue.getClass()==Date.class)
						    {
						    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(DateUtil.formatDate((Date)fromValue)) });
						    	continue;
						    }
						    toPropertie.getWriteMethod().invoke(toObj,new Object[] { Long.valueOf(fromValue.toString()) });
						    continue;
					    }
					    if(toPropertie.getPropertyType()== Integer.class||toPropertie.getPropertyType()== int.class)
				     	{
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { Integer.valueOf(fromValue.toString()) });
					    	continue;
				     	}
					    if(toPropertie.getPropertyType()== Float.class||toPropertie.getPropertyType()== float.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { Float.valueOf(fromValue.toString()) });
					    	continue;
					    }
					    if(toPropertie.getPropertyType()== Date.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { DateUtil.stringToDate(fromValue.toString(),DateUtil.NORMAL_DATE_FORMAT) });
					    	continue;
					    }
					    if(toPropertie.getPropertyType()== Short.class||toPropertie.getPropertyType()== short.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { Short.valueOf(fromValue.toString()) });
					    	continue;
					    }
					    if(toPropertie.getPropertyType()== Double.class||toPropertie.getPropertyType()== double.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { Double.valueOf(fromValue.toString()) });
					    	continue;
					    }
					    if(toPropertie.getPropertyType()== String.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { fromValue.toString() });
					    	continue;
					    }
					    if(toPropertie.getPropertyType()== Boolean.class||toPropertie.getPropertyType()== boolean.class)
					    {
					    	toPropertie.getWriteMethod().invoke(toObj,new Object[] { Boolean.parseBoolean(fromValue.toString()) });
					    	continue;
					    }
					}
			}
			   
		}catch (Exception e) {
		   throw new AppException("���Ը���ʧ��(" + e.getMessage() + ")"); 
		 }
	}
		
    /**
     * @$comment ʵ���˰Ѵ�����������������ݿ���������ʵ���У����ض����б�
     * ʹ��˵����������һ��Ҫ��һ��ʵ����󣬲���ֱ�Ӹ�ֵ������
     * 1������ĸ�ֵ������Object[] obj=new Object[10];obj[0]="��";��
     * 2����ȷ�ĸ�ֵ������Ac01 ac01=new Ac01();Object[] obj=new Object[10];ac01.setAac003("��");obj[0]=ac01;��
     * ���ܣ�
     * 1������ʱ���뿼��toObj�����Ե����͡�
     * 2�����͵�ת����
     *    2.1������ת����Long,Integer,Float,Date,Short,Double,String,Boolean��
     *    2.2��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Long�͵����Ե�ֵ��ת����
     *    2.3��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Date�͵����Ե�ֵ��ת����
     *    2.4���ȵȡ�
     * @param <typeName> �Զ�������
     * @param fromObj ��Ŷ��������
     * @param toObj ��
     * @return List<typeName> �Զ�������б�
     * @throws AppException
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @code
     * public List GetSysCodeType() {
			Iterator lists = session
					.createQuery(
							"select new Kc05(aaz265,eac071,aaz308,aaz002) from Kc05 k where k.aaz001="+aaz001+"")
					.iterate();
			List kc05lst=new java.util.ArrayList(); 
			while (lists.hasNext())
            {
				Object[] tmp = (Object[])lists.next();
			    List<Kc05> lst=CopyIgnoreProperty.copyObjectArray(tmp, Kc05.class);
			    for(int i=0;i<lst.size();i++){
			      kc05lst.add((Kc05)lst.get(i));
			    }		
			}
			return kc05lst;
	    }     
     */
	public static <typeName> List<typeName> copyObjectArray(Object[] fromObj, Class<typeName> toObj) throws AppException{	
		
		LinkedList<typeName> list = new LinkedList<typeName>();
		try {
			for(int i=0;i<fromObj.length;i++){
				typeName obj =  toObj.newInstance();
				CopyIgnoreProperty.copy(fromObj[i], obj);
				list.add(obj);
			}	

		}catch(Exception e){
			throw new AppException("���Ը���ʧ��(" + e.getMessage() + ")"); 
		}
		return list;		
	}

	/**
	 * @deprecated
	 * @$comment ʵ���˰Ѵ����Object[]�����ݿ���������ʵ����
	 * ���ܣ�
     * 1������ʱ���뿼��toObj�����Ե����͡�
     * 2�����͵�ת����
     *    2.1������ת����Long,Integer,Float,Date,Short,Double,String,Boolean��
     *    2.2��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Long�͵����Ե�ֵ��ת����
     *    2.3��String������ı������ڵ��ַ���ʽ��"2008-04-01"����Date�͵����Ե�ֵ��ת����
     *    2.4���ȵȡ�
     * ȱ����
     * 1���޷�ʵ�ֱ�����Double��Long��ת����
     * 2��HQL�������Ե�˳������toObj�����ƵĶ����������Ҫһ��,�����Ƶ����ݾ��ǲ���ȷ��,��Ϊ���󴴽�ʱ�����ǰ��ַ����е�,���磺
     *    2.1��aab001��eab001ǰ
     *    2.2��aab002��aab003ǰ
     * 3���������ֶ�˳�������ȷ�������ᡣ
	 * @param fromObjs �����Ƶ�����
	 * @param toObj ���ƵĶ���
	 * @throws AppException
	 * @code
	 * public class Ab01 {
          private String eab001;
          private Long aaz001;
          private Double aab019;
          private String aaa148;
          ......
        }
       public List GetSysCodeType(Long aaz001) {
			Iterator lists = session
					.createQuery(
							"select a.aaa148,a.aaz001,a.aab019,a.eab001 from Ab01 a where a.aaz001="+aaz001+"")
					.iterate();
			List ab01lst=new java.util.ArrayList(); 
			while (lists.hasNext())
            {
				Object[] tmp = (Object[])lists.next();
			    copyIgnoreProperty.copyObjectArrayForHQL(tmp,ab01);
			    for(int i=0;i<lst.size();i++){
			      ab01lst.add((Ab01)lst.get(i));
			    }		
			}
			return ab01lst;
	    }        
     */
	public static void copyObjectArrayForHQL(Object[] fromObjs, Object toObj) throws AppException{	
		BeanInfo toBeanInfo = null;	
		Integer i = 0;
		try {
			toBeanInfo = Introspector.getBeanInfo(toObj.getClass(),
					Object.class);
			PropertyDescriptor[] toProperties = toBeanInfo
					.getPropertyDescriptors();
			for (Object fromObj:fromObjs)
			{
				for ( ; i < toProperties.length; )
				{
					Object fromValue=fromObj;
					if(fromValue!=null&&!fromValue.toString().equalsIgnoreCase("")){
						if (isBasicClass(fromValue.getClass())&& isBasicClass(toProperties[i].getPropertyType())&& fromValue.getClass() == toProperties[i].getPropertyType()){
							toProperties[i].getWriteMethod().invoke(toObj,new Object[] { fromValue });
							break;
						}
						else if (isBasicClass(fromValue.getClass())&& isBasicClass(toProperties[i].getPropertyType())&&fromValue.getClass()!=toProperties[i].getPropertyType()) {
							if(toProperties[i].getPropertyType()== Long.class||toProperties[i].getPropertyType()== long.class)
						    {
						    	
							    if(fromValue.toString().indexOf("-")>0)
							    {
							    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(fromValue.toString().substring(0, 10)) });
							    	break;
							    }
							    if(fromValue.getClass()==Date.class)
							    {
							    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { DateUtil.strDateToNum(DateUtil.formatDate((Date)fromValue)) });
							    	break;
							    }
							    toProperties[i].getWriteMethod().invoke(toObj,new Object[] { Long.valueOf(fromValue.toString()) });
							    break;
						    }
						    if(toProperties[i].getPropertyType()== Integer.class||toProperties[i].getPropertyType()== int.class)
					     	{
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { Integer.valueOf(fromValue.toString()) });
						    	break;
					     	}
						    if(toProperties[i].getPropertyType()== Float.class||toProperties[i].getPropertyType()== float.class)
						    {
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { Float.valueOf(fromValue.toString()) });
						    	break;
						    }
						    if(toProperties[i].getPropertyType()== Date.class)
						    {
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { DateUtil.stringToDate(fromValue.toString(),DateUtil.NORMAL_DATE_FORMAT) });
						    	break;
						    }
						    if(toProperties[i].getPropertyType()== Short.class||toProperties[i].getPropertyType()== short.class)
						    {
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { Short.valueOf(fromValue.toString()) });
						    	break;
						    }
						    if(toProperties[i].getPropertyType()== Double.class||toProperties[i].getPropertyType()== double.class)
						    {
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { Double.valueOf(fromValue.toString()) });
						    	break;
						    }
						    if(toProperties[i].getPropertyType()== String.class)
						    {
						    	toProperties[i].getWriteMethod().invoke(toObj,new Object[] { fromValue.toString() });
						    	break;
						    }
						}
					}else{
						break;
					}
					
				}
				i++;
			}
		}catch(Exception e){
			throw new AppException("���Ը���ʧ��(" + e.getMessage() + ")"); 
		}
	}
}

package com.ldsmsoft.framework.util;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

import com.common.webconfig.AppException;



/**
 * ���ݱ�ֵ�Ļ��࿽��(����������ݱ��¼���)
 */

public class CopyEventBean 
{

	/**
	 * @$comment �ж��Ƿ��ǻ�������
	 * @param cls 
	 * @return true �������� false ���ǻ�������
	 */
	@SuppressWarnings("unchecked")
	public static boolean isBasicClass(Class cls) {
		if (cls == String.class) {
			return true;
		} else if (cls == Integer.class || cls == int.class) {
			return true;
		} else if (cls == Short.class || cls == short.class) {
			return true;
		} else if (cls == Float.class || cls == float.class) {
			return true;
		} else if (cls == Double.class || cls == double.class) {
			return true;
		} else if (cls == Long.class || cls == long.class) {
			return true;
		} else if (cls == Date.class) {
			return true;
		} else if (cls == Boolean.class || cls ==boolean.class) {
			return true;
		}

		return false;
	}
	/**
	 * @$comment ʵ�����¼�������DTO���ߵ�������ʵ��(�ʺ����ж�������հ汾)
	 * @param basicTable Ŀ�����
	 * @param property ������
	 * @param value ����ֵ
	 * @throws AppException
	 * @code
	 *  Ab01 ab01=new Ab01();
	    for (int i = 0; i < ae53Lst.size(); i++) {
			try {
				CopyEventBean.eventCopyToBasicFinally(ab01,
						((Ae53) ae53Lst.get(i)).getAaz312(),(Ae53) ae53Lst.get(i).getAaz313());
			} catch (Exception e) {
				throw new AppException("�¼����浽��λ�����!", e);
			}
		}
	 */
	public static void eventCopyToBasicFinally(Object basicTable,String property,String value) throws AppException 
	{
		BeanInfo toBeanInfo = null;	
		try {
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();

					for (PropertyDescriptor toPropertie:toProperties)
					{
						String newProperty= toPropertie.getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(value!=null){
								if(value.equals("")){
									value="331023413400";//���ֱ��֤�������¼�������""��ʽ����
								}
								if(isBasicClass(toPropertie.getPropertyType())&& toPropertie.getPropertyType()==value.getClass())
									{
										toPropertie.getWriteMethod().invoke(basicTable,new Object[] { value });
									    return;
									}
									
									if(toPropertie.getPropertyType()== Long.class||toPropertie.getPropertyType()== long.class)
								    {
								    	
									    if(value.indexOf("-")>0)
									    {
									    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum(value) });
		                                    return;
									    }
									    toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf(value) });
								    }
								    if(toPropertie.getPropertyType()== Integer.class||toPropertie.getPropertyType()== int.class)
							     	{
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf(value) });
								    	return;
							     	}
								    if(toPropertie.getPropertyType()== Float.class||toPropertie.getPropertyType()== float.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf(value) });
								    	return;
								    }
								    if(toPropertie.getPropertyType()== Date.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum(value) });
									    return;
								    }
								    if(toPropertie.getPropertyType()== Short.class||toPropertie.getPropertyType()== short.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf(value) });
									    return;
								    }
								    if(toPropertie.getPropertyType()== Double.class||toPropertie.getPropertyType()== double.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf(value) });
									    return;
								    }	
							  }
							
					}
				  }
			   
		}
		catch(Exception e)
		{
			 throw new AppException("�������ݳ���(" + e.getMessage() + ")");
		}
	}
	/**
	 * @deprecated
	 * @$comment ��eventTable(�¼���)�д�����ݶ�Ӧ�ı��浽basicTable(������)
	 * @param eventTable �¼���
	 * @param basicTable ������
	 * @param property   �ֶ���
	 * @throws AppException
	 */
	public static void eventCopyToBasic(Object eventTable,Object basicTable,String property) throws AppException 
	{
		
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {
			fromBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			for (int i = 0; i < fromProperties.length; i++)
			{
				if(i==fromProperties.length-2){//�����Ӧ���Ե�ֵ �����θ�ֵ
					for (int j = 0; j < toProperties.length; j++)
					{
						String newProperty= toProperties[j].getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							{
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}) });
							    return;
							}
								
							if(toProperties[j].getPropertyType()== Long.class)
						    {
						    	
							    if((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}).toString()).indexOf("-")>0)
							    {
							    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString() ) });
                                    return;
							    }
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    }
						    if(toProperties[j].getPropertyType()== Integer.class)
					     	{
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
					     	}
						    if(toProperties[j].getPropertyType()== Float.class)
						    {
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
						    }
						    if(toProperties[j].getPropertyType()== Date.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Short.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Double.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }					    

					}
				  }
			   }
			}
		}
		catch(Exception ex)
		{
			 throw new AppException("�������ݳ���",ex);
		}
	}

	/**
	 * @$comment ʵ�ִӻ������¼���ֵ�Ŀ�����
	 *           �˷���ֻ�����ڵ�λģ�飬ԭ�����¼�����ֶα������ˡ�
	 *           �����Ƕ�AE53��Ĳ��������Զ�������Ĳ�����Ҫ����Ӧ���޸ģ��д������С�
	 * @param basicTable ������
	 * @param eventId �¼�ID
	 * @param keyId �¼��������
	 * @param orgId ��֯ID
	 * @return 
	 * @throws AppException
	 */
/*
    public static List<Ae53> basicCopyToEvent(Object basicTable,Long eventId,String keyId,String orgId) throws AppException 
    {
    	Ae53 eventTable=new Ae53();
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {			
			fromBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			List<Ae53> eventLst=new java.util.ArrayList<Ae53>();
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			Object newProValue=null;
			for (int i = 0; i < fromProperties.length; i++)
			{
                    if(fromProperties[i].getName().equalsIgnoreCase("aaz001")){newProValue=orgId;}
                    else{newProValue=fromProperties[i].getReadMethod().invoke(basicTable,new Object[] {});}            
                    if(newProValue==null||newProValue.equals("")||newProValue.equals("null")){continue;}	
				    Ae53 ae53=new Ae53();				    
					for (int j = 0; j < toProperties.length; j++)
					{
                        if(fromProperties[i].getName().equalsIgnoreCase("ab02Data")||fromProperties[i].getName().equalsIgnoreCase("ab31Set")||fromProperties[i].getName().equalsIgnoreCase("ae52Data"))
                        {continue;}			
						String oldProperty=fromProperties[i].getName();
						String fromProperty=toProperties[j].getName();
						if(fromProperty.equalsIgnoreCase("eaz069")){//��֯�Ǽ���ϸid
							String seqcode=HBUtil.getSequence(keyId);
							ae53.setEaz069(Long.parseLong(seqcode));
						}
						if(fromProperty.equalsIgnoreCase("aaz308")){//�¼�ID
							ae53.setAaz308(eventId);
						}
						if(fromProperty.equalsIgnoreCase("aaz312")){//������
							ae53.setAaz312(oldProperty.toUpperCase());
						}
						if(fromProperty.equalsIgnoreCase("aaz313")){//����ֵ
							ae53.setAaz313(newProValue.toString());
						}
						if(j==toProperties.length-1){
							eventLst.add(ae53);
						}
																
					}
			}
			return eventLst;
		}
		catch(Exception e)
		{
			 throw new AppException("�������ݳ���(" + e.getMessage() + ")");
		}    	
    }
	
*/
    /**
	 * @$comment ʵ�ִӻ������ݱ�ֵ�Ŀ���
	 *           �˷�������ֻ�����ڵ�λģ�飬ԭ�����¼�����ֶα������ˡ�
	 *           �����Ƕ�AE19��Ĳ��������Զ�������Ĳ�����Ҫ����Ӧ���޸ģ��д������С�
     * @param basicTable_Old �����ݿ��������޸�ǰ�Ķ���
     * @param basicTable_New �ӽ��������Ա�����Ķ���
     * @param eventId �¼�ID
     * @param tableName ������ı���
     * @return List<Ae19> 
     * @throws AppException
     */
	@SuppressWarnings("unchecked")
/*
	public static List<Ae19> basicCopyToEventForUpdate(Object basicTable_Old,Object basicTable_New,Long eventId,String tableName) throws AppException
	{
		Ae19 eventTable=new Ae19();
		BeanInfo oldBeanInfo = null;
		BeanInfo newBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		
		try {
			oldBeanInfo = Introspector.getBeanInfo(basicTable_Old.getClass(),
					Object.class);
			newBeanInfo = Introspector.getBeanInfo(basicTable_New.getClass(),
					Object.class);
			toBeanInfo  = Introspector.getBeanInfo(eventTable.getClass(),
					Object.class);
			List<Ae19> eventLst=new java.util.ArrayList<Ae19>();
			PropertyDescriptor[] oleProperties = oldBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] newProperties = newBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] toProperties  = toBeanInfo
			        .getPropertyDescriptors();

			for (int i = 0; i < oleProperties.length; i++)
				for (int j = 0; j < newProperties.length; j++)
					if (oleProperties[i].getName().equals(newProperties[j].getName())) 
					{
						if(oleProperties[i].getName().equalsIgnoreCase("aaz308"))
						{
							break;
						}
						if (isBasicClass(oleProperties[i].getPropertyType())&& isBasicClass(newProperties[j].getPropertyType()))
						{
							Object oldValue=oleProperties[i].getReadMethod().invoke(basicTable_Old, new Object[]{});
							Object newValue=newProperties[j].getReadMethod().invoke(basicTable_New, new Object[]{});
							if(oldValue==null){
								oldValue="";
							}
							if(newValue!=null&&newValue.toString().equalsIgnoreCase("1000057863456")){
								newValue="";							
							}
							if(newValue!=null&&!newValue.toString().equalsIgnoreCase("null")&&!newValue.toString().equalsIgnoreCase(oldValue.toString())){
								CommonQueryBS query=new CommonQueryBS();
								query.setConnection(HBUtil.getHBSession().connection());
								String column_name=newProperties[j].getName().toUpperCase();
								query.setQuerySQL("select s.comments from user_col_comments s where s.table_name ='"+tableName+"' and s.column_name ='"+column_name+"'");
								Vector<?> vector=query.query();
								String comments=null;
								Iterator<?> iterator = vector.iterator();
								if (iterator.hasNext())
					            {
									HashMap tmp= (HashMap)iterator.next();
									comments=(String)tmp.get("comments");
								}							
							    Ae19 ae19=new Ae19();
							    String aaz019=HBUtil.getSequence("SQ_AAZ019");
								for (int k = 0; k < toProperties.length; k++)
								{				
									String fromProperty=toProperties[k].getName();
									if(fromProperty.equalsIgnoreCase("aaz019")){//��֯��Ϣ�����ϸID
										ae19.setAaz019(Long.parseLong(aaz019));
									}
									if(fromProperty.equalsIgnoreCase("eae001")){//����
										ae19.setEae001(tableName);
									}
									if(fromProperty.equalsIgnoreCase("aae122")){//�����Ŀ
										ae19.setAae122(oleProperties[i].getName().toUpperCase());
									}
									if(fromProperty.equalsIgnoreCase("aae123")){//���ǰ��Ϣ
										ae19.setAae123(oldValue.toString());
									}
									if(fromProperty.equalsIgnoreCase("aae124")){//�������Ϣ
										ae19.setAae124(newValue.toString());
									}
									if(fromProperty.equalsIgnoreCase("aaz308")){//��֯��Ϣ����¼�ID
										ae19.setAaz308(eventId);
									}
									if(fromProperty.equalsIgnoreCase("aae121")){//��������ĺ���
										ae19.setAae121(comments);
									}
									if(k==toProperties.length-1){
									    eventLst.add(ae19);	
									}
								}	
								
							}								
							break;
						}
					}	
						
			return eventLst;
		} catch (Exception e) {
			throw new AppException("���Ը���ʧ��(" + e.getMessage() + ")");
		}
		
	}
	
*/
	/**
	 * @deprecated
	 * @$comment ��eventTable(�¼���)�д�����ݶ�Ӧ�ı��浽basicTable(������)
	 * @param eventTable �¼���
	 * @param basicTable ������
	 * @param property �¼����д������Ե��ֶ�ֵ
	 * @throws AppException
	 */
	public static void eventCopyToBasicForUpdate(Object eventTable,Object basicTable,String property) throws AppException 
	{
		
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {
			fromBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			for (int i = 0; i < fromProperties.length; i++)
			{
				if(i==fromProperties.length-4){//�����Ӧ���Ե�ֵ �����θ�ֵ
					for (int j = 0; j < toProperties.length; j++)
					{
						String newProperty= toProperties[j].getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							{
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}) });
							    return;
							}
								
							if(toProperties[j].getPropertyType()== Long.class)
						    {
						    	
							    if((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}).toString()).indexOf("-")>0)
							    {
							    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString() ) });
                                    return;
							    }
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    }
						    if(toProperties[j].getPropertyType()== Integer.class)
					     	{
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
					     	}
						    if(toProperties[j].getPropertyType()== Float.class)
						    {
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
						    }
						    if(toProperties[j].getPropertyType()== Date.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Short.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Double.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }					    

					}
				  }
			   }
			}
		}
		catch(Exception ex)
		{
			 throw new AppException("�������ݳ���(" + ex.getMessage() + ")");
		}
	}
	/**
	 * @$comment ͨ�����Ե�ƥ�䣬��fromObj�в�Ϊnullֵ�����Ե�ֵ������toObj(�����ݿ��в�����޸�ǰ�Ķ���)��Ϊ�������޸��ṩ��������
	 *           ����ÿ�����Զ������Ƿ������޸���ǿյ��жϲ�����
	 * @param fromObj ��Ž������ֵ��DTO
	 * @param toObj �����ݿ��в�����޸�ǰ�Ķ���
	 * @throws AppException
	 */
	public static void copy(Object fromObj, Object toObj) throws AppException {
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
			Object fromValue=null;
			Object toValue=null;
			for (int i = 0; i < fromProperties.length; i++)
				for (int j = 0; j < toProperties.length; j++)
					if (fromProperties[i].getName().equals(toProperties[j].getName())) {
						if (isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							fromValue=fromProperties[i].getReadMethod().invoke(fromObj, new Object[]{});
						    toValue=toProperties[j].getReadMethod().invoke(toObj, new Object[]{});
						    if(fromValue!=null&&fromValue.toString().equalsIgnoreCase("331023413400")){
						    	fromValue="";
						    }
						    if(toValue==null){
						    	toValue="";
						    }
						    if(fromValue!=null&&!fromValue.toString().equalsIgnoreCase(toValue.toString())&&!fromValue.toString().equalsIgnoreCase("null"))
							{
								toProperties[j].getWriteMethod().invoke(toObj,new Object[] { fromValue });
							}						
				        	break;
					}
		} catch (Exception e) {
			 throw new AppException( "���Ը���ʧ��",e );
		}
	}

}


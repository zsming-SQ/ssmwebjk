package com.common.service;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.common.webconfig.AppException;

/**
 * ServicesXmlParser
 * 
 * @author wengsh
 * @date 2012-3-20
 * 
 */
public class ServicesUtil {

	private static String COMMON_CONFIG_XML_LOCTION = "/common.config.xml";
	
	private XmlParser xmlParser;
	
	private Log log=LogFactory.getLog(ServicesUtil.class);

	public ServicesUtil() throws AppException {
		InputStream in = getClass().getResourceAsStream(COMMON_CONFIG_XML_LOCTION);// ���ط��������ļ�
		xmlParser = new XmlParser(in);
	}
    /**
     * �õ�ҵ��Ĺ�������
     * @param businessTypeNo <code>String</code>
     * @return String
     * @exception AppException ����������ļ����Ҳ�����Ӧ��ҵ����,�׳�ҵ���߼��쳣
     */
	public String getBusinessClassName(String name) throws AppException{
		Element element=xmlParser.selectNode("/service-config/businesses/business[@name='"+name+"']");
	    if(element==null){
	    	log.error("��ҵ���߼����󡿴�����Ϣ����:�Ҳ�������Ϊ��"+name+"����ҵ��xml������Ϣ!");
	    	throw new AppException(getConfigExceptionInfo("401"));
	    }
	    return element.getTextTrim();
	}
	/**
	 * �õ��쳣��Ϣ
	 * @param name
	 * @return
	 */
	public String getConfigExceptionInfo(String name) throws AppException{
		Element element=xmlParser.selectNode("/service-config/exceptions/exception[@name='"+name+"']");
		if(element==null){
	    	log.error("��ҵ���߼����󡿴�����Ϣ����:�Ҳ�������Ϊ��"+name+"�����쳣����������Ϣ!");
	    	throw new AppException(getConfigExceptionInfo("402"));
	    }
	    return element.getTextTrim();
	}
}

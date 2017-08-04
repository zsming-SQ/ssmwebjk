package com.common.service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.common.webconfig.AppException;

/**
 * xml�ļ�����
 * 
 * @author wengweng
 * @date 2012-3-20
 */
public class XmlParser {
	private Document document;

	/**
	 * ���캯��
	 * 
	 * @param in
	 * @throws AppException
	 */
	public XmlParser(InputStream in) throws AppException {
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(in);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}
	/**
	 * ���캯��
	 * 
	 * @param file
	 * @throws AppException
	 */
	public XmlParser(File file) throws AppException {
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(file);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	/**
	 * ���캯��
	 * 
	 * @param url
	 * @throws AppException
	 */
	public XmlParser(URL url) throws AppException {
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(url);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	/**
	 * �õ�xpath��Ӧ��Ԫ��ֵ
	 * @param xpath
	 * @return
	 * @throws AppException
	 */
	public Element selectNode(String xpath) {
		List list = document.selectNodes(xpath);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			return (Element) iterator.next();
		}
		return null;
	}
}

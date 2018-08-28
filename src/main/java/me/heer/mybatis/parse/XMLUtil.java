package me.heer.mybatis.parse;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class XMLUtil {
	/**
	 * 解析Mapper.xml文件
	 * @param resource
	 * @return
	 */
	public static Element parseDocument(String resource) {
		SAXReader saxReader = new SAXReader(true);
		try {
			// 防止出现加载dtd 文件失败
			saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
			// File
			Document doc = saxReader.read(new File(resource));
			return doc.getRootElement();
		} catch (DocumentException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}
}

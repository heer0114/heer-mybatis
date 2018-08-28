package me.heer.mybatis.parse;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import me.heer.mybatis.configuration.Configuration;

/**
 * @author heer_
 * @date 2018��8��9�� - ����3:01:19
 * @Desc ���������ļ�, ��ע�ᵽConfiguration ������.ģʽ
 */
public class YMLConfigBuilder {

	// �����ļ�
	private final String source;
	
	// ȫ��Ψһ������
	private Configuration configuration;
	
	/**
	 * ���캯��
	 */
	public YMLConfigBuilder(String source) {
		this.source = source;
		// ��ʼ��������
		this.configuration = new Configuration();
	}

	@SuppressWarnings("unchecked")
	public Configuration parse() {
		// ���������ļ�Ϊmap
		Map<String, Object> yamlConfig = YamlUtil.parseYaml(source, "heerMybatis");
		Map<String, Object> datasource = (Map<String, Object>) yamlConfig.get("datasource");
		// ����ȡ�������ݿ����÷ŵ�Configuration��
		configuration.setDataproperties(datasource);
		
		String resource = (String)yamlConfig.get("xmlMapperScan");
		
		XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration, resource);
		xmlMapperBuilder.parse();
		
		configuration.getDataproperties().forEach((k, v) -> {
			System.out.println(k + "--" + v);
		});
		
		
		return configuration;
	}

}

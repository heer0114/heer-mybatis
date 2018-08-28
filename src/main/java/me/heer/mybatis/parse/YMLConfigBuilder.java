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
 * @date 2018年8月9日 - 下午3:01:19
 * @Desc 解析配置文件, 并注册到Configuration 建造者.模式
 */
public class YMLConfigBuilder {

	// 配置文件
	private final String source;
	
	// 全局唯一配置类
	private Configuration configuration;
	
	/**
	 * 构造函数
	 */
	public YMLConfigBuilder(String source) {
		this.source = source;
		// 初始化配置类
		this.configuration = new Configuration();
	}

	@SuppressWarnings("unchecked")
	public Configuration parse() {
		// 解析配置文件为map
		Map<String, Object> yamlConfig = YamlUtil.parseYaml(source, "heerMybatis");
		Map<String, Object> datasource = (Map<String, Object>) yamlConfig.get("datasource");
		// 将读取到的数据库配置放到Configuration中
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

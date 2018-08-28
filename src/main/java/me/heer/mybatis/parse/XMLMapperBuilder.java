package me.heer.mybatis.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.mapping.MappedStatement;
import me.heer.mybatis.mapping.ResultMap;
import me.heer.mybatis.mapping.ResultMapping;

/**
 * @author heer_
 * @date 2018年8月10日 - 下午6:03:54
 * @Desc
 */
public class XMLMapperBuilder {

	// 全局配置
	private Configuration configuration;
	// 加载的资源
	private String resource;

	public XMLMapperBuilder(Configuration configuration, String resource) {
		this.configuration = configuration;
		this.resource = resource;
	}

	public void parse() {
		if (!configuration.isResourceLoaded(resource)) {
			String[] split = resource.split(":");
			String resourceFolder = this.getClass().getClassLoader().getResource(split[1]).getFile();
			File folder = new File(resourceFolder);
			if (folder.exists()) {
				// 得到所有sqlMapper文件
				File[] xmlMappers = folder.listFiles();
				Arrays.asList(xmlMappers).forEach(xmlMapper -> {
					// 解析资源
					parseMapperElements(XMLUtil.parseDocument(xmlMapper.getPath()));
					// 标记资源 防止资源重复加载
					configuration.addLoadedResource(xmlMapper.getPath());

				});
			} else {
				try {
					throw new FileNotFoundException();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 解析Mapper。xml
	 * 
	 * @param element
	 */
	private void parseMapperElements(Element element) {
		// 得到命名空间
		String namespace = element.attribute("namespace").getData().toString();
		// System.out.println("mapper.xml -> namespace: " + namespace);

		// 解析resultMap
		parseResultMap(namespace, element);

		// 解析select，insert，update，delete节点
		parseStatement(namespace, element);

	}

	/**
	 * 解析resultMap节点
	 * 
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	private void parseResultMap(String namespace, Element element) {
		List<Element> resultMaps = element.elements("resultMap");
		resultMaps.forEach(resultMap -> {
			String id = resultMap.attribute("id").getData().toString();
			String type = resultMap.attribute("type").getData().toString();

			// 使用此Id来确定唯一的ResultMap
			String resultMapId = namespace + "." + id;

			// 接下result Map中的每行
			List<Element> results = resultMap.elements("result");
			Set<ResultMapping> resultMappings = new HashSet<ResultMapping>();
			results.forEach(result -> {
				String column = result.attribute("column").getData().toString();
				String property = result.attribute("property").getData().toString();
				ResultMapping resultMapping = new ResultMapping.Builder(property, column).build();
				resultMappings.add(resultMapping);
			});

			try {
				// 建造者模式生成ResultMap
				ResultMap rm = new ResultMap.Builder(resultMappings, configuration, resultMapId, Class.forName(type))
						.build();
				System.out.println(rm.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 解析curd元素
	 * 
	 * @param namespace
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void parseStatement(String namespace, Element element) {
		
		// 得到所有select节点
		List<Element> selects = element.elements("select");
		selects.forEach(select -> {
			MappedStatement mappedStatement = new MappedStatement();
			String id = select.attribute("id").getData().toString();
			String resultMap = namespace + "." +select.attribute("resultMap").getData().toString();
			String sql = select.getTextTrim();
			// 唯一id
			String sourceId = namespace + "." + id;
			mappedStatement.setNamespace(namespace);
			mappedStatement.setSourceId(sourceId);
			mappedStatement.setSql(sql);
			mappedStatement.setResultMap(resultMap);
			// 添加到Configuration 中
			configuration.getMappedStatements().put(sourceId, mappedStatement);
		});
		
		// insert update ... 省略 ^_^同上

	}

}

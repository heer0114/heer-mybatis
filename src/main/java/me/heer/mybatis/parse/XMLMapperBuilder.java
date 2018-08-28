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
 * @date 2018��8��10�� - ����6:03:54
 * @Desc
 */
public class XMLMapperBuilder {

	// ȫ������
	private Configuration configuration;
	// ���ص���Դ
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
				// �õ�����sqlMapper�ļ�
				File[] xmlMappers = folder.listFiles();
				Arrays.asList(xmlMappers).forEach(xmlMapper -> {
					// ������Դ
					parseMapperElements(XMLUtil.parseDocument(xmlMapper.getPath()));
					// �����Դ ��ֹ��Դ�ظ�����
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
	 * ����Mapper��xml
	 * 
	 * @param element
	 */
	private void parseMapperElements(Element element) {
		// �õ������ռ�
		String namespace = element.attribute("namespace").getData().toString();
		// System.out.println("mapper.xml -> namespace: " + namespace);

		// ����resultMap
		parseResultMap(namespace, element);

		// ����select��insert��update��delete�ڵ�
		parseStatement(namespace, element);

	}

	/**
	 * ����resultMap�ڵ�
	 * 
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	private void parseResultMap(String namespace, Element element) {
		List<Element> resultMaps = element.elements("resultMap");
		resultMaps.forEach(resultMap -> {
			String id = resultMap.attribute("id").getData().toString();
			String type = resultMap.attribute("type").getData().toString();

			// ʹ�ô�Id��ȷ��Ψһ��ResultMap
			String resultMapId = namespace + "." + id;

			// ����result Map�е�ÿ��
			List<Element> results = resultMap.elements("result");
			Set<ResultMapping> resultMappings = new HashSet<ResultMapping>();
			results.forEach(result -> {
				String column = result.attribute("column").getData().toString();
				String property = result.attribute("property").getData().toString();
				ResultMapping resultMapping = new ResultMapping.Builder(property, column).build();
				resultMappings.add(resultMapping);
			});

			try {
				// ������ģʽ����ResultMap
				ResultMap rm = new ResultMap.Builder(resultMappings, configuration, resultMapId, Class.forName(type))
						.build();
				System.out.println(rm.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * ����curdԪ��
	 * 
	 * @param namespace
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void parseStatement(String namespace, Element element) {
		
		// �õ�����select�ڵ�
		List<Element> selects = element.elements("select");
		selects.forEach(select -> {
			MappedStatement mappedStatement = new MappedStatement();
			String id = select.attribute("id").getData().toString();
			String resultMap = namespace + "." +select.attribute("resultMap").getData().toString();
			String sql = select.getTextTrim();
			// Ψһid
			String sourceId = namespace + "." + id;
			mappedStatement.setNamespace(namespace);
			mappedStatement.setSourceId(sourceId);
			mappedStatement.setSql(sql);
			mappedStatement.setResultMap(resultMap);
			// ��ӵ�Configuration ��
			configuration.getMappedStatements().put(sourceId, mappedStatement);
		});
		
		// insert update ... ʡ�� ^_^ͬ��

	}

}

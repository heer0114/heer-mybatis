package me.heer.mybatis.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.heer.mybatis.binding.MapperProxyFactory;
import me.heer.mybatis.mapping.MappedStatement;
import me.heer.mybatis.mapping.ResultMap;
import me.heer.mybatis.session.SqlSession;

/**
 * @author heer_
 * @date 2018��8��9�� - ����2:21:47
 * @Desc ȫ�ֺ���������
 */
public class Configuration {

	// ���ݿ�����
	protected Map<String, Object> dataproperties = new HashMap<String, Object>();
	// ���ص���Դ
	protected Set<String> loadedResources = new HashSet<String>();
	// resultMap ӳ�伯��
	protected Map<String, ResultMap> resultMaps = new HashMap<String, ResultMap>();
	// sql��Ϣ����
	protected Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	// Ϊmapper�ӿ����ɶ�̬����ķ���
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return MapperProxyFactory.getMapperProxy(sqlSession, type);
	}

	// �����Դ
	public void addLoadedResource(String resource) {
		loadedResources.add(resource);
	}

	// ��Դ�Ƿ��Ѿ����ع���
	public boolean isResourceLoaded(String resource) {
		return loadedResources.contains(resource);
	}

	// ���resultmapӳ��
	public void addResultMap(String id, ResultMap resultMap) {
		resultMaps.put(id, resultMap);
	}

	public ResultMap getResultMap(String id) {
		return resultMaps.get(id);
	}

	public Map<String, Object> getDataproperties() {
		return dataproperties;
	}

	public void setDataproperties(Map<String, Object> dataproperties) {
		this.dataproperties = dataproperties;
	}

	public Map<String, MappedStatement> getMappedStatements() {
		return mappedStatements;
	}

}

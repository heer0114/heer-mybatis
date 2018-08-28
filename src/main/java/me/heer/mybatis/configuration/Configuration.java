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
 * @date 2018年8月9日 - 下午2:21:47
 * @Desc 全局核心配置类
 */
public class Configuration {

	// 数据库配置
	protected Map<String, Object> dataproperties = new HashMap<String, Object>();
	// 加载的资源
	protected Set<String> loadedResources = new HashSet<String>();
	// resultMap 映射集合
	protected Map<String, ResultMap> resultMaps = new HashMap<String, ResultMap>();
	// sql信息集合
	protected Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	// 为mapper接口生成动态代理的方法
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return MapperProxyFactory.getMapperProxy(sqlSession, type);
	}

	// 添加资源
	public void addLoadedResource(String resource) {
		loadedResources.add(resource);
	}

	// 资源是否已经加载过了
	public boolean isResourceLoaded(String resource) {
		return loadedResources.contains(resource);
	}

	// 添加resultmap映射
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

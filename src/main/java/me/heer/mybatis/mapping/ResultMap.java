package me.heer.mybatis.mapping;

import java.util.Collections;
import java.util.Set;

import me.heer.mybatis.configuration.Configuration;

public class ResultMap {

	private String id;

	private Class<?> type;

	private Set<ResultMapping> mappedColumns;

	public ResultMap() {

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<ResultMapping> getMappedColumns() {
		return mappedColumns;
	}

	public void setMappedColumns(Set<ResultMapping> mappedColumns) {
		this.mappedColumns = mappedColumns;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}



	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ResultMap [id=" + id + ", type=" + type + ", mappedColumns=" + mappedColumns + "]";
	}

	// ��̬�ڲ��࣬ ������ģʽ
	public static class Builder {
		private ResultMap resultMap = new ResultMap();
		private Configuration configuration;
		public Builder(Set<ResultMapping> mappedColumns, Configuration configuration, String id, Class<?> type) {
			resultMap.id = id;
			resultMap.type = type;
			resultMap.mappedColumns = mappedColumns;
			this.configuration = configuration;
		}

		public ResultMap build() {
			// ��������
			Collections.unmodifiableCollection(resultMap.mappedColumns);
			
			// ��map��ӵ�Configuration�ļ�����
			configuration.addResultMap(resultMap.id, resultMap);
			
			return resultMap;
		}
	}

}

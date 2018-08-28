package me.heer.mybatis.mapping;

/**
 * @author heer_
 * @date 2018年8月10日 - 下午6:25:53
 * @Desc 存放sql 语句信息
 */
public class MappedStatement {
	private String namespace;

	private String sourceId;

	private String sql;

	private String resultType;

	private String resultMap;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public String toString() {
		return "MappedStatement [namespace=" + namespace + ", sourceId=" + sourceId + ", sql=" + sql + ", resultType="
				+ resultType + ", resultMap=" + resultMap + "]";
	}

}

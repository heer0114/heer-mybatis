package me.heer.mybatis.session;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.excutor.Executor;
import me.heer.mybatis.mapping.MappedStatement;

/**
 * @author heer_
 * @date 2018年8月11日 - 下午1:41:48
 * @Desc
 */
public class DefaultSqlSession implements SqlSession {
	
	private Configuration configuration;
	
	private Executor executor;

	public DefaultSqlSession(Configuration configuration, Executor executor) {
		this.configuration = configuration;
		this.executor = executor;
	}

	public <T> T selectOne(String statement, Object parameter) {
		List<Object> list = this.selectList(statement, parameter);
		if(list != null && !list.isEmpty()) {
			return (T) list.get(0);
		}
		return null;
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		Map<String, MappedStatement> mappedStatements = configuration.getMappedStatements();
		// 得到mapperinterface对应的sql信息
		MappedStatement mappedStatement = mappedStatements.get(statement);
		try {
			// 执行Sql
			return executor.query(mappedStatement, parameter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> T getMapper(Class<T> type) {
		return configuration.<T>getMapper(type, this);
	}

}

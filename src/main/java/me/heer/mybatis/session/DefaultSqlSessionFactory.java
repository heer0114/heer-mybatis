package me.heer.mybatis.session;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.excutor.Executor;
import me.heer.mybatis.excutor.SimpleExecutor;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

	// 全局配置
	private final Configuration configuration;
	
	// 真正访问数据库对象
	private Executor executor;

	// 构造方法
	public DefaultSqlSessionFactory(Configuration cfg) {
		this.configuration = cfg;
		this.executor = new SimpleExecutor(configuration);
	}

	public SqlSession openSqlSession() {
		
		DefaultSqlSession defaultSqlSession = new DefaultSqlSession(configuration, executor);
		
		return defaultSqlSession;
	}

}

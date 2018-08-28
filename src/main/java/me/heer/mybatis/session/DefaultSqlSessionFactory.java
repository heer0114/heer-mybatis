package me.heer.mybatis.session;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.excutor.Executor;
import me.heer.mybatis.excutor.SimpleExecutor;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

	// ȫ������
	private final Configuration configuration;
	
	// �����������ݿ����
	private Executor executor;

	// ���췽��
	public DefaultSqlSessionFactory(Configuration cfg) {
		this.configuration = cfg;
		this.executor = new SimpleExecutor(configuration);
	}

	public SqlSession openSqlSession() {
		
		DefaultSqlSession defaultSqlSession = new DefaultSqlSession(configuration, executor);
		
		return defaultSqlSession;
	}

}

package me.heer.mybatis.session;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.parse.YMLConfigBuilder;

/**
 * @author heer_
 * @date 2018��8��9�� - ����2:11:55
 * @Desc
 * 
 * 		������ģʽ�� ����SelsesionFactory
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(String source) {
		// ί��YMLConfigBuilder �����������ļ�
		YMLConfigBuilder ymlConfig = new YMLConfigBuilder(source);
		return build(ymlConfig.parse());
	}

	public SqlSessionFactory build(Configuration cfg) {
		return new DefaultSqlSessionFactory(cfg);
	}

}

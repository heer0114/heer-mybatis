package me.heer.mybatis.session;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.parse.YMLConfigBuilder;

/**
 * @author heer_
 * @date 2018年8月9日 - 下午2:11:55
 * @Desc
 * 
 * 		建造者模式： 创建SelsesionFactory
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(String source) {
		// 委托YMLConfigBuilder 来解析配置文件
		YMLConfigBuilder ymlConfig = new YMLConfigBuilder(source);
		return build(ymlConfig.parse());
	}

	public SqlSessionFactory build(Configuration cfg) {
		return new DefaultSqlSessionFactory(cfg);
	}

}

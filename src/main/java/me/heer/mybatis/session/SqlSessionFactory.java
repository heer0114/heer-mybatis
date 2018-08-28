package me.heer.mybatis.session;

/**
 * @author heer_
 * @date 2018年8月9日 - 下午2:38:00
 * @Desc
 * 
 * 	创建SqlSession实例
 */
public interface SqlSessionFactory {
	
	SqlSession openSqlSession();
}

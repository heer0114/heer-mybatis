package me.heer.mybatis.session;

/**
 * @author heer_
 * @date 2018��8��9�� - ����2:38:00
 * @Desc
 * 
 * 	����SqlSessionʵ��
 */
public interface SqlSessionFactory {
	
	SqlSession openSqlSession();
}

package me.heer.mybatis.session;

import java.util.List;

/**
 * @author heer_
 * @date 2018��8��9�� - ����2:32:51
 * @Desc �û�����ִ��sql����
 */
public interface SqlSession {

	// ���ݴ����������ѯ���
	<T> T selectOne(String statement, Object parameter);

	// ��������������ѯ�����ط��ͼ���
	<E> List<E> selectList(String statement, Object parameter);

	// ����mapper�ӿڻ�ȡ�ӿڶ�Ӧ�Ķ�̬����ʵ��
	<T> T getMapper(Class<T> type);

}

package me.heer.mybatis.excutor;

import java.sql.SQLException;
import java.util.List;

import me.heer.mybatis.mapping.MappedStatement;

/**
 * @author heer_
 * @date 2018��8��10�� - ����7:08:55
 * @Desc �����������ݿ�Ķ���
 */
public interface Executor {
	 <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;
}

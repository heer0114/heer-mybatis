package me.heer.mybatis.excutor;

import java.sql.SQLException;
import java.util.List;

import me.heer.mybatis.mapping.MappedStatement;

/**
 * @author heer_
 * @date 2018年8月10日 - 下午7:08:55
 * @Desc 真正访问数据库的对象
 */
public interface Executor {
	 <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;
}

package me.heer.mybatis.excutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.excutor.parameter.DefaultParameterHandler;
import me.heer.mybatis.excutor.parameter.ParameterHandler;
import me.heer.mybatis.excutor.resultset.DefaultResultSetHandler;
import me.heer.mybatis.excutor.resultset.ResultSetHandler;
import me.heer.mybatis.excutor.statement.DefaultStatementHandler;
import me.heer.mybatis.excutor.statement.StatementHandler;
import me.heer.mybatis.mapping.MappedStatement;

/**
 * @author heer_
 * @date 2018年8月11日 - 下午12:50:58
 * @Desc 真正访问数据库的对象
 */
public class SimpleExecutor implements Executor {

	private Configuration config;

	public SimpleExecutor() {
		// TODO Auto-generated constructor stub
	}

	public SimpleExecutor(Configuration config) {
		this.config = config;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
		// 1.获取Connection对象
		Connection conn = getConn();

		// 2.实例化StatementHandler对象，准备实例化Statement
		StatementHandler statementHandler = new DefaultStatementHandler(ms);

		// 3.通过statementHandler和Connection获取PreparedStatement
		PreparedStatement prepare = statementHandler.prepare(conn);

		// 4.实例化ParameterHandler对象，对Statement中sql语句的占位符进行处理
		ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);

		parameterHandler.setParameters(prepare);

		// 5.执行查询语句，获取结果集resultSet
		ResultSet resultSet = statementHandler.query(prepare);

		// 6.实例化ResultSetHandler对象，对resultSet中的结果集进行处理，转化成目标对象
		ResultSetHandler resultSetHandler = new DefaultResultSetHandler(ms, config);
		System.out.println("执行的sql  ->  " + ms.getSql());
		return resultSetHandler.handleResultSets(resultSet);

	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection getConn() {
		Map<String, Object> dataproperties = config.getDataproperties();
		String driver = dataproperties.get("driver").toString();
		String url = dataproperties.get("url").toString();
		String name = dataproperties.get("name").toString();
		String passwd = dataproperties.get("passwd").toString();
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, name, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}

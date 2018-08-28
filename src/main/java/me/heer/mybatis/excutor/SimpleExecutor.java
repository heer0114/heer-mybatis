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
 * @date 2018��8��11�� - ����12:50:58
 * @Desc �����������ݿ�Ķ���
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
		// 1.��ȡConnection����
		Connection conn = getConn();

		// 2.ʵ����StatementHandler����׼��ʵ����Statement
		StatementHandler statementHandler = new DefaultStatementHandler(ms);

		// 3.ͨ��statementHandler��Connection��ȡPreparedStatement
		PreparedStatement prepare = statementHandler.prepare(conn);

		// 4.ʵ����ParameterHandler���󣬶�Statement��sql����ռλ�����д���
		ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);

		parameterHandler.setParameters(prepare);

		// 5.ִ�в�ѯ��䣬��ȡ�����resultSet
		ResultSet resultSet = statementHandler.query(prepare);

		// 6.ʵ����ResultSetHandler���󣬶�resultSet�еĽ�������д���ת����Ŀ�����
		ResultSetHandler resultSetHandler = new DefaultResultSetHandler(ms, config);
		System.out.println("ִ�е�sql  ->  " + ms.getSql());
		return resultSetHandler.handleResultSets(resultSet);

	}

	/**
	 * ��ȡ����
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

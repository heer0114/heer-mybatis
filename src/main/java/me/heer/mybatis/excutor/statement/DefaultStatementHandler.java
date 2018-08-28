package me.heer.mybatis.excutor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.heer.mybatis.mapping.MappedStatement;

public class DefaultStatementHandler implements StatementHandler {
	
	private MappedStatement ms;
	
	public DefaultStatementHandler(MappedStatement ms) {
		this.ms = ms;
		
	}

	@Override
	public PreparedStatement prepare(Connection connection) throws SQLException {
		return connection.prepareStatement(ms.getSql());
	}

	@Override
	public ResultSet query(PreparedStatement statement) {
		try {
			return statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

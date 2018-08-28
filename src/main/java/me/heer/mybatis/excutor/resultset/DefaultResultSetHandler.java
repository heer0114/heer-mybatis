package me.heer.mybatis.excutor.resultset;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.heer.mybatis.configuration.Configuration;
import me.heer.mybatis.mapping.MappedStatement;
import me.heer.mybatis.mapping.ResultMap;
import me.heer.mybatis.mapping.ResultMapping;
import me.heer.mybatis.reflection.ReflectionUtil;

public class DefaultResultSetHandler implements ResultSetHandler {

	private MappedStatement mappedStament;
	private Configuration config;

	public DefaultResultSetHandler(MappedStatement mappedStament, Configuration config) {
		super();
		this.mappedStament = mappedStament;
		this.config = config;
	}

	@Override
	public <E> List<E> handleResultSets(ResultSet resultSet) throws SQLException {
		List<E> ret = new ArrayList<E>();
		while (resultSet.next()) {
			Class<?> entityClass;
			try {

				// 
				String resultMap = mappedStament.getResultMap();

				ResultMap rm = config.getResultMap(resultMap);
				// ӳ���ʵ��
				entityClass = rm.getType();
				// ����ʵ��������ֶ�
				Field[] declaredFields = entityClass.getDeclaredFields();
				// ʵ��������
				E entity = (E) entityClass.newInstance();

				for (Field field : declaredFields) {
					// ʵ���е��ֶ���
					String fieldName = field.getName();
					Set<ResultMapping> mappedColumns = rm.getMappedColumns();
					mappedColumns.forEach(mapped -> {
						try {
							if (fieldName.equals(mapped.getProperty())) {
								// ʵ���е��ֶ�ֵ �� mapper�е�Result Map �����õ��ֶαȽ�,
								// ���ݿ��е��ֶ���
								String column = mapped.getColumn();
								if (field.getType().getSimpleName().equals("String")) {
									ReflectionUtil.setPropToBean(entity, fieldName, resultSet.getString(column));
								} else if (field.getType().getSimpleName().equals("Integer")) {
									ReflectionUtil.setPropToBean(entity, fieldName, resultSet.getInt(column));
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
				ret.add(entity);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

}

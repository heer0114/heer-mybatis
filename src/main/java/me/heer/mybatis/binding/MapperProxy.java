package me.heer.mybatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import me.heer.mybatis.session.SqlSession;

/**
 * @author heer_
 * @date 2018��8��11�� - ����1:20:26
 * @Desc 
 * @param <T>
 * 
 * 	
 */
public class MapperProxy<T> implements InvocationHandler {
	
	private SqlSession sqlSession; 
	private Class<T> mapperInterface;

	public MapperProxy() {

	}

	public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		super();
		this.sqlSession = sqlSession;
		this.mapperInterface = mapperInterface;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(Object.class.equals(method.getDeclaringClass())) {
			// Object ���� �ķ�����������ǿ
			return method.invoke(this, args);
		}
		
		// �õ������ķ�������
		Class<?> returnType = method.getReturnType();
		Object ret = null;
		if(isCollection(returnType)) {
			ret = sqlSession.selectList(mapperInterface.getName()+"."+method.getName(), args);
		} else {
			ret = sqlSession.selectOne(mapperInterface.getName()+"."+method.getName(), args);
		}
		return ret;
	}
	
	
	/**
	 * �ж��Ƿ��Ǽ�����
	 * @param returnType
	 * @return
	 */
	private <T> boolean isCollection(Class<T> returnType) {
		
		return Collection.class.isAssignableFrom(returnType);
		
	}

}

package me.heer.mybatis.binding;

import java.lang.reflect.Proxy;

import me.heer.mybatis.session.SqlSession;

/**
 * @author heer_
 * @date 2018年8月11日 - 下午1:08:28
 * @Desc
 */
public class MapperProxyFactory {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getMapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		
		MapperProxy mapperProxy = new MapperProxy(sqlSession, mapperInterface);
		
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] {mapperInterface}, mapperProxy);
		
	}
	
}

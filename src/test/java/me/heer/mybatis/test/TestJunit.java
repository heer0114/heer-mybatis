package me.heer.mybatis.test;

import java.util.List;

import org.junit.Test;

import me.heer.mybatis.entity.TUser;
import me.heer.mybatis.mapper.TUserMapper;
import me.heer.mybatis.session.SqlSession;
import me.heer.mybatis.session.SqlSessionFactory;
import me.heer.mybatis.session.SqlSessionFactoryBuilder;

public class TestJunit {
	
	@Test
	public void test1() {
		String resource = this.getClass().getClassLoader().getResource("heer-mybatis-config.yml").getFile();
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
		SqlSession sqlSession = sessionFactory.openSqlSession();
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		
//		List<TUser> list = mapper.selectAll();
//		list.forEach(System.out::println);
		
//		
		TUser user = mapper.selectById(2);
		System.out.println(user.toString());
	}
	
}

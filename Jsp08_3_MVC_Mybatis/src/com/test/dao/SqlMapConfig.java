package com.test.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		String resource = "com/test/db/config.xml";
		
		// Resources : A class to simplify access to resources through the classloader.
		// getResourceAsReader : Returns a resource on the classpath as a Reader object
		try (Reader reader = Resources.getResourceAsReader(resource)) {
			// SqlSessionFactoryBuilder : Builds SqlSession instances.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}

}

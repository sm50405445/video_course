package com.portfolio.driverTest;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/mvc-config.xml","file:src/main/resources/spring/spring-security.xml",
	"file:src/main/resources/spring/application-config.xml"})
@Log4j
public class DataSourceTests {

	@Setter(onMethod_ = {@Autowired})
	private DataSource datasource;
	
	@Test
	public void testConnection() {
		
		try(Connection conn = datasource.getConnection()){
			log.info(conn);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}

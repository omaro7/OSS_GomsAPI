package kr.co.goms.web.oss;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import io.micrometer.common.util.StringUtils;

@SpringBootApplication
@ConfigurationPropertiesScan	// 이걸 추가해준다
public class OssApplication  extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(OssApplication.class);
	
	public OssApplication() {
		super();
	}
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(OssApplication.class);
		Environment env = app.run(args).getEnvironment();
		startApplication(env);
		
	}


	private static void startApplication(Environment env) {
		String protocal = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key->"https").orElse("http");
		String serverPort = env.getProperty("server.port");
		String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path"))
				.filter(StringUtils::isNotBlank)
				.orElse("/");
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		}catch(UnknownHostException e) {
			logger.warn("The host name could not be determined, using `localhost` as fallback");
		}

		String swagger = "/swagger-ui/index.html";
		
		logger.info(
			"\n*********************************************************************\n" + 
			"\t - APP:\t{}\n" + 
			"\t - 접속:\t{}://localhost:{}{}\n" + 
			"\t - 외부:\t{}://{}:{}{}\n" + 
			"\t - api:\t{}://{}:{}{}\n" + 
			"\t - 프로파일:{}" + 
			"\n*********************************************************************\n",
			env.getProperty("spring.application.name"),
			protocal,
			serverPort,
			contextPath,
			protocal,
			hostAddress,
			serverPort,
			contextPath,
			protocal,
			hostAddress,
			serverPort,
			swagger,
			env.getActiveProfiles().length == 0? env.getDefaultProfiles():env.getActiveProfiles()
		);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OssApplication.class);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Mapper.xml");
		sessionFactory.setMapperLocations(res);
		
		Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
		sessionFactory.setConfigLocation(myBatisConfig);
		
		return sessionFactory.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	
}

package com.minimal;
import com.github.pagehelper.PageHelper;
import com.minimal.common.sdk.sequence.DefaultIdGenerator;
import com.minimal.common.sdk.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import java.util.Properties;

/**
 * @author linzhiqiang
 */

@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.minimal.mapper")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MinimalApplication{
	public static void main(String[] args) {
		SpringApplication.run(MinimalApplication.class, args);
	}

	@Bean
	public IdGenerator idGenerator(@Value("${workerId:0}") long workerId) {
		return new DefaultIdGenerator(workerId);
	}
}

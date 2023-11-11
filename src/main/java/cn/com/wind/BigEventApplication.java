package cn.com.wind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * @author 王叶盛
 */
@SpringBootApplication
public class BigEventApplication {
    public static void main( String[] args ) {
        SpringApplication.run(BigEventApplication.class, args);
        System.out.println( "Hello World!" );
    }
}

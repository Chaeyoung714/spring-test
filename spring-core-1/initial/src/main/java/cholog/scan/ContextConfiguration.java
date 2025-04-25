package cholog.scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
ComponentScan에 대해 학습하고, ComponenetScanBean을 Bean으로 등록하기
*/
@Configuration
public class ContextConfiguration {
    @Bean
    public ComponentScanBean componentScanBean() {
        return new ComponentScanBean();
    }
}

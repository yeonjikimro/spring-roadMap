package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // 해당 패키지에 속한 탐생범위 정함
        basePackageClasses = AutoAppConfig.class, // 지정하지 않으면 default(hello.core를 다 뒤짐)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // @Component를 빈으로 등록
public class AutoAppConfig {

}

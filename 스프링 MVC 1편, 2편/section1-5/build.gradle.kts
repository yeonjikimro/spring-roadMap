plugins {
	java
	war
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "hello"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	//JSP 추가 시작
	implementation ("org.apache.tomcat.embed:tomcat-embed-jasper")
	implementation ("javax.servlet:jstl") //JSP 추가 끝
	//
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
// https://mvnrepository.com/artifact/javax.servlet/jstl
	implementation("javax.servlet:jstl:1.2")
// https://mvnrepository.com/artifact/javax.servlet/jsp-api
	compileOnly("javax.servlet:jsp-api:2.0")




}

tasks.withType<Test> {
	useJUnitPlatform()
}

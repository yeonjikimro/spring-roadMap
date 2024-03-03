# 빌드와 배포
- jar 배포 시 'META-INF/MANIFEST.MF' 파일에 실행할 main() 메서드의 클래스를 지정해주어야 한다.
- 직접 만들지 말고 Gradle의 도움을 받는다.
```
    //일반 Jar 생성
    task buildJar(type: Jar) {
    manifest {
    attributes 'Main-Class': 'hello.embed.EmbedTomcatSpringMain'
    }
    with jar
    }
```

- cmd 에
- ` ./gradlew clean buildJar`
  - 작성하여 빌드
    - 빌드 확인
    - `cd build`
    - `cd libs`
    - `ls`
    - ~~.jar 가 만들어져있음



package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

// 스프링 빈을 등록하는 2가지 방법
// 1. 컴포넌트 스캔과 자동 의존관계 설정
// 2. 자바코드로 직접 스프링 빈 등록

// 컴포넌트 스캔과 자동 의존관계 설정
// @Component : 에노테이션이 있으면 스프링 빈으로 자동 등록된다.
// @Controller : 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
// @Component 를 포함하는 다음 에노테이션도 스프링 빈으로 자동 등록
// 	1. @Controller
// 	2. @Service
// 	3. @Repository

// 우리가 지금 실행하고 있는 건 HelloSpringApplication 인데
// 동일하거나 하위  패키지가 아닐 경우 스프링 빈으로 컴포넌트 스캔 안한다.

// DI에는 필드, setter, 생성자 주입이 있다. 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장.
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔사용
// 정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록
// @Autowired를 통한 DI는 helloController, MemberService 등과 같이 스프링이 관리하는 객체에서만 동작
// 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.



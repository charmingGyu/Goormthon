package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author hyoungmin.park
 */
@Controller // 이 에노테이션을 보고 스프링이 뜰때 이 커트롤러 객체를 생성을 해서 spring이 들고 있는다.(spring 컨테이너에서 빈이 관리된다)
public class MemberController { // 이 컨트롤러가 컨테이너가 뜰때 생성을 한다.
//new를 생성해서 사용할 수도 있지만 spring 이 관리를 하게 되면 컨테이너에 등록을 하고 컨테이너를 통해 받아서 쓰도록 바꿔야함.
    // new를 생성해서 사용하게 되면 다른 컨트롤러들이 가져다가 쓸수 있게된다.
   // private final MemberService memberService = new MemberService();
    private final MemberService memberService;
//    @Autowired private MemberService memberService;
//    --> 필드 주입 --> 지양 --> 왜? 바꿀수 있는 방법이 없다.

//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
//    --> setter 주입 --> 누군가가 memberController 를 호출하려면 public 으로 열려있어야함
    @Autowired // memberService 를 spring이 컨테이너에 있는 memberService 와 연결시켜줌
    // 생성자에게 쓰면 memberController 가 생성이 될 때 Spring bin 에 등록이 되어 있는 memberService 객체를 가져다가 넣어준다(=Dependency Injection[의존관계 주입] )
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm"; // templates 의 createMemberForm.html 로 리턴

    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member); // 회원가입 할때

        return "redirect:/"; // 회원가입이 끝나면 home화면으로 보내는 것

        // http Get, Post 메소드 , 폼태그? 검색

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // findMembers -> member 들을 다 다져올 수 있다.
        model.addAttribute("members", members);
        // model.addAttribute(String name, Object value) --> value 객체를 name 이름으로 추가한다. 뷰 코드에서는 name 으로 지정한 이름을 통해서 value 를 사용한다.
        // model.addAttribute(Object value) -->  value를 추가한다. value의 패키지 이름을 제외한 단순 클래스 이름을 모델 이름으로 사용한다.
        // 이 때 첫 글자는 소문자로 처리한다.
        // value가 배열이거나 컬렉션인 경우 첫 번째 원소의 클래스 이름 뒤에 "List"를 붙인 걸 모델 이름으로 사용한다.
        // 이 경우에도 클래스 이름의 첫자는 소문자로 처리한다.
        return "members/memberList";


    }
}
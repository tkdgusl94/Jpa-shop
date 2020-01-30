package leveloper.jpabook.jpashop.controller;

import leveloper.jpabook.jpashop.controller.dto.MembersResponseDto;
import leveloper.jpabook.jpashop.controller.form.MemberForm;
import leveloper.jpabook.jpashop.domain.Address;
import leveloper.jpabook.jpashop.domain.Member;
import leveloper.jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> memberList = memberService.findMembers();

        // Dto로 변경해서 반환
        List<MembersResponseDto> members = memberList.stream()
                .map(c -> new MembersResponseDto(c))
                .collect(Collectors.toList());

        model.addAttribute("members", members);
        return "members/memberList";
    }
}

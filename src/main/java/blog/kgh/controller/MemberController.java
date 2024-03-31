package blog.kgh.controller;

import blog.kgh.domain.entity.Member;
import blog.kgh.web.form.LoginForm;
import blog.kgh.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") LoginForm member) {
        return "members/addMemberForm";
    }
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("member") LoginForm createMember, BindingResult result) {
        if (result.hasErrors()) {
            return "members/addMemberForm";
        }

        Member member = Member.builder()
                .loginId(createMember.getLoginId())
                .password(createMember.getPassword())
                .username(createMember.getUsername())
                .build();

        boolean isNew = memberService.validateDuplicateMember(member);
        if (!isNew) {
            result.reject("createFail", "아이디 또는 이름이 이미 존재합니다.");
            return "members/addMemberForm";
        }
        memberService.join(member);
        return "redirect:/";
    }
}
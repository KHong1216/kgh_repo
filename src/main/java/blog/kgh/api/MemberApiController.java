package blog.kgh.api;

import blog.kgh.domain.dto.member.*;
import blog.kgh.domain.entity.Member;
import blog.kgh.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result members() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream().map(m -> new MemberDto(m.getUsername())).collect(Collectors.toList());
        return new Result(collect);
    }

    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") String id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getUsername());
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}

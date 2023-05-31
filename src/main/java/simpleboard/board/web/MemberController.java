package simpleboard.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import simpleboard.board.domain.Member;
import simpleboard.board.domain.Role;
import simpleboard.board.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Member member = new Member();
        member.setLoginId(form.getLoginId());
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        member.setPassword(encodedPassword);
        member.setName(form.getName());
        member.setEMail(form.getEMail());
        member.setRole(Role.ROLE_USER);

        try {
            memberService.join(member);
        } catch (Exception e) {
            return "/members/createMemberForm";
        }

        return "redirect:/";
    }
}

package blog.kgh.controller;

import blog.kgh.domain.entity.Member;
import blog.kgh.web.form.LoginForm;
import blog.kgh.web.login.Login;
import blog.kgh.web.login.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model)
    {
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "redirect:/board/list";

    }

}

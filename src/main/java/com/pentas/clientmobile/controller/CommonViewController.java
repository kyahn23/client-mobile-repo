package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.auth.LoginUser;
import com.pentas.clientmobile.common.auth.dto.SessionUser;
import com.pentas.clientmobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Controller
public class CommonViewController {
    private final UserService userService;

    @GetMapping("/ssoproc")
    public RedirectView ssoproc(@LoginUser SessionUser user) throws UnsupportedEncodingException {
        String redirectUrl = "/client-mobile";
        String memberId = "null";
        String memberNickname = "null";

        if (user != null) {
            if (user.getMemberId() != null && !user.getMemberId().equals("null")) {
                memberId = user.getMemberId();
            }
            if (user.getMemberNickname() != null && !user.getMemberNickname().equals("null")) {
                memberNickname = user.getMemberNickname();
            }

            if (userService.isRegisteredUser(memberId)) {
                if (userService.isSocialUser(memberId)) {
                    redirectUrl += "/#/social/login/" + user.getSocialService()
                            + "/" + user.getSocialId()
                            + "/" + memberId
                            + "/" + URLEncoder.encode(memberNickname, "UTF-8");
                } else {
                    redirectUrl += "/#/layer/login?notify=emailAcc";
                }
            } else {
                redirectUrl += "/#/social/signup/" + user.getSocialService()
                        + "/" + user.getSocialId()
                        + "/" + memberId
                        + "/" + URLEncoder.encode(memberNickname, "UTF-8");
            }
        }

        return new RedirectView(redirectUrl);
    }

}

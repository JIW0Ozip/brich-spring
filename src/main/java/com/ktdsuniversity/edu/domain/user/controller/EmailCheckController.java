package com.ktdsuniversity.edu.domain.user.controller;
import java.io.UnsupportedEncodingException;
import java.util.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@RestController
@RequestMapping("/email")
public class EmailCheckController {
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired 
    private UserService userService;

    private static final long EXPIRE_MS = 3 * 60 * 1000L; // 3분

    @PostMapping("/send")
    public AjaxResponse sendCode(@RequestParam String email, HttpSession session) throws MessagingException, UnsupportedEncodingException {
        AjaxResponse ajaxResponse = new AjaxResponse();
    	if(!isValidEmail(email)) {
    		ajaxResponse.setBody(false);
    		return ajaxResponse;
    	}
    		
    	String code = generateSixDigit();
        long expiresAt = System.currentTimeMillis() + EXPIRE_MS;

        Map<String, Object> auth = new HashMap<>();
        auth.put("email", email);
        auth.put("code", code);
        auth.put("expiresAt", expiresAt);
        session.setAttribute("emailAuth", auth);
        ajaxResponse.setBody(auth);

        // 메일함에 보일 인증번호 디자인 구성 
        String subject = "[Brich] 이메일 인증번호 안내";
        String htmlContent = """
            <div style="font-family: Pretendard, sans-serif; max-width: 500px; margin: 0 auto; border: 1px solid #e5e7eb; border-radius: 10px; padding: 30px;">
                <h2 style="color: #2563eb; text-align: center;">Brich 이메일 인증</h2>
                <p style="font-size: 14px; color: #333; text-align: center">아래 인증번호를 입력하여 이메일 인증을 완료해주세요.</p>
                <div style="text-align: center; margin: 20px 0;">
                    <span style="font-size: 24px; font-weight: bold; color: #2563eb; letter-spacing: 3px;">%s</span>
                </div>
                <p style="font-size: 14px; color: #555; text-align: center;">인증번호는 <b>3분</b> 동안만 유효합니다.</p>
                <hr style="margin: 25px 0; border: none; border-top: 1px solid #e5e7eb;">
                <p style="font-size: 12px; color: #888; text-align: center;">본 메일은 발신 전용입니다.</p>
            </div>
        """.formatted(code);

        sendHtmlMail(email, subject, htmlContent);

        return ajaxResponse;
    }

    /*
     * 이메일 중복 체크
     */
    @GetMapping("/duplicate/{email}")
    public AjaxResponse checkDuplicatedEmail(@PathVariable String email) {
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	
    	boolean isDuplicated = this.userService.readEmailByInputEmail(email);
    	ajaxResponse.setBody(isDuplicated);
 
    	return ajaxResponse;
    }
    
    
    @PostMapping("/verify")
    public AjaxResponse verifyCode(@RequestParam String email,
                                   @RequestParam String code,
                                   HttpSession session) {
        Object emailNullCheck = session.getAttribute("emailAuth");
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (emailNullCheck == null) {
        	ajaxResponse.setBody(Map.of("success", false, "message", "인증 요청을 먼저 해주세요."));
            return ajaxResponse;
    	}
        Map<String, Object> auth = (Map<String, Object>) emailNullCheck;
        if (!email.equals(auth.get("email"))) {
        	ajaxResponse.setBody(Map.of("success", false, "message", "이메일이 일치하지 않습니다."));
            return ajaxResponse;
        }
        if (System.currentTimeMillis() > (Long) auth.get("expiresAt")) {
        	ajaxResponse.setBody(Map.of("success", false, "message", "인증시간이 만료되었습니다."));
            return ajaxResponse;
        }
        if (!code.equals(auth.get("code"))) {
        	ajaxResponse.setBody(Map.of("success", false, "message", "인증번호가 일치하지 않습니다."));
            return ajaxResponse;
        }

        session.removeAttribute("emailAuth");
        session.setAttribute("verifiedEmail", email);
        
        ajaxResponse.setBody(Map.of("success", true, "message", "인증 성공!"));
        return ajaxResponse;
    }

    private String generateSixDigit() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    private void sendHtmlMail(String to, String subject, String htmlContent) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // HTML 적용
        helper.setFrom("your.email@gmail.com", "Brich"); // 발신자명 설정
        mailSender.send(message);
    }
    
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}
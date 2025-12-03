package com.ktdsuniversity.edu.domain.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.faq.service.FaqService;
import com.ktdsuniversity.edu.domain.faq.vo.response.ResponseFaqVO;

@Controller
@RequestMapping("/help/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;
    
    //TODO WHERE 조건 걸기
    @GetMapping
    public String viewFaqPage(@RequestParam(required=false) String category
    						, Model model) {
    	
    	ResponseFaqVO faqResponse = this.faqService.selectFaqList(category);
    	
    	model.addAttribute("faqList", faqResponse);
    	
    	return "faq/faq";
    }
    
}
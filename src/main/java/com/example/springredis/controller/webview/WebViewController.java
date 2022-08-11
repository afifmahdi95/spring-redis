package com.example.springredis.controller.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("form")
public class WebViewController {
    @GetMapping
    public String pageForm(){ return "form";}
}

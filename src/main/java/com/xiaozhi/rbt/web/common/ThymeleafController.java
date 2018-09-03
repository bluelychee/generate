package com.xiaozhi.rbt.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by lizhihui on 2018/8/10
 */
@Controller
public class ThymeleafController {
    @RequestMapping("/thymeleaf")
    public String index(String name, ModelMap map) {
        map.addAttribute("name", name);
        map.addAttribute("from", "lqdev.cn");
        // 模版名称，实际的目录为：src/main/resources/templates/head.html
        return "thymeleaf";
    }
}

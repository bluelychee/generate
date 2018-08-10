package com.xiaozhi.generate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by lizhihui on 2018/8/10
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {
    // 正常和springmvc设置返回参数是意义的用法了
    @GetMapping("/map")
    public String index(String name, ModelMap map) {
        map.addAttribute("name", name);
        map.addAttribute("from", "lqdev.cn");
        // 模版名称，实际的目录为：src/main/resources/templates/thymeleaf.html
        return "freemarker";
    }

    @GetMapping("/mv")
    public ModelAndView index(String name) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("from", "lqdev.cn");
        // 模版名称，实际的目录为：src/main/resources/templates/thymeleaf.html
        mv.setViewName("freemarker");
        return mv;
    }
}

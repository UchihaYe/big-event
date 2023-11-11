package cn.com.wind.controller;

import cn.com.wind.pojo.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王叶盛
 */
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @GetMapping("/list")
    public Result list() {
        return Result.success();
    }
}

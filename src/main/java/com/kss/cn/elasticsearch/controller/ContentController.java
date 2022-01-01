package com.kss.cn.elasticsearch.controller;

import com.kss.cn.elasticsearch.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    //http://localhost:9999/parse/java
    @ResponseBody
    @GetMapping("/parse/{keyword}")
    public Boolean parseJd(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword);
    }

    //高亮查询：http://localhost:9999/h_search/java/0/10
    @ResponseBody
    @GetMapping("/h_search/{keyword}/{pageIndex}/{pageSize}")
    public List<Map<String, Object>> searchHig(@PathVariable("keyword") String keyword,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize) throws IOException {
        return contentService.highlightSearch(keyword, pageIndex, pageSize);
    }

}

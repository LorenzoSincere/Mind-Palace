package com.lorenzo.mind_palace.controller;


import com.lorenzo.mind_palace.request.CategoryQueryReq;
import com.lorenzo.mind_palace.request.CategorySaveReq;
import com.lorenzo.mind_palace.response.CommonResp;
import com.lorenzo.mind_palace.response.CategoryQueryResp;
import com.lorenzo.mind_palace.response.PageResp;
import com.lorenzo.mind_palace.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/18 18:06
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> response = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        response.setContent(list);
        return response;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp response = new CommonResp<>();
        categoryService.save(req);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp response = new CommonResp<>();
        categoryService.delete(id);
        return response;
    }
}

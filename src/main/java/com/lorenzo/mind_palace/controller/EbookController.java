package com.lorenzo.mind_palace.controller;


import com.lorenzo.mind_palace.request.EbookReq;
import com.lorenzo.mind_palace.response.CommonResp;
import com.lorenzo.mind_palace.response.EbookResp;
import com.lorenzo.mind_palace.response.PageResp;
import com.lorenzo.mind_palace.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lorenzo
 * @date 2022/03/13 21:04
 **/
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> response = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        response.setContent(list);
        return response;
    }
}

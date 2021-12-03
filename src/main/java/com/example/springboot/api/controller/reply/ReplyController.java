package com.example.springboot.api.controller.reply;

import com.example.springboot.api.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyController {
	private final ReplyService replyService;
}

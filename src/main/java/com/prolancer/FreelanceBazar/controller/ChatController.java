package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chat")
public class ChatController {

    private final ChatService chatService;

}

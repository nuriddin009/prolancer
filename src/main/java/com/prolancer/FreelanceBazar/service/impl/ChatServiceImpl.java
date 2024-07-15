package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.repository.ChatRepository;
import com.prolancer.FreelanceBazar.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserSession userSession;


    @Override
    public ApiResponse getMyChats() {
        User user = userSession.getUser();


        return new ApiResponse();
    }
}

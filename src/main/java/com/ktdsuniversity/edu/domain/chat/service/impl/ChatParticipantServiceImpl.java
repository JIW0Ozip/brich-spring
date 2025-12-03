package com.ktdsuniversity.edu.domain.chat.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.chat.dao.ChatParticipantDao;
import com.ktdsuniversity.edu.domain.chat.service.ChatParticipantService;

@Service
public class ChatParticipantServiceImpl implements ChatParticipantService {

    @Autowired
    private ChatParticipantDao chatParticipantDao;

}
package com.ktdsuniversity.edu.domain.chat.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.chat.dao.ChatRoomDao;
import com.ktdsuniversity.edu.domain.chat.service.ChatRoomService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

}
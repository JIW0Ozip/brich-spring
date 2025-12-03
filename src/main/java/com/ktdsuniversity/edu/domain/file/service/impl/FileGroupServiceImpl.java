package com.ktdsuniversity.edu.domain.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.service.FileGroupService;

@Service
public class FileGroupServiceImpl implements FileGroupService {

    @Autowired
    private FileGroupDao fileGroupDao;

}
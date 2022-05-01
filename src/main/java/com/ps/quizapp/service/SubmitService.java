package com.ps.quizapp.service;




import com.ps.quizapp.repository.SubmitRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubmitService {

    private static final Logger logger = LoggerFactory.getLogger(SubmitService.class);

    private final SubmitRepositoryImpl submitRepository;

    @Autowired
    public SubmitService(SubmitRepositoryImpl submitRepository) {
        this.submitRepository = submitRepository;
    }

    @Transactional
    public int getMark(List<String> ids) {
        return submitRepository.getMarks(ids);


    }

}

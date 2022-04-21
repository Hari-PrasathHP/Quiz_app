package com.ps.service;




import com.ps.repository.SubmitRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubmitService {

    @Autowired
    private SubmitRepositoryImpl submitRepository;

    @Transactional
    public int getMark(List<String> ids) {
        return submitRepository.getMarks(ids);


    }

}

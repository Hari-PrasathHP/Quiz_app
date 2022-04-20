package com.ps.service;


import com.ps.repository.SubmitRepository;
import com.ps.repository.SubmitRepositoryImpl;

import java.util.List;

public class SubmitService {

    public int getMark(List<String> ids) {
        SubmitRepository submitRepository = new SubmitRepositoryImpl();
        return submitRepository.getMarks(ids);


    }

}

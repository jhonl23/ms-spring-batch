package com.intercorp.msspringbatch.services;

import com.intercorp.msspringbatch.models.Root;
import com.intercorp.msspringbatch.repository.IRootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RootServiceImpl implements IRootService {

    @Autowired
    private IRootRepository personRepository;

    @Override
    @Transactional
    public Root save(Root root) {
        return this.personRepository.save(root);
    }

}

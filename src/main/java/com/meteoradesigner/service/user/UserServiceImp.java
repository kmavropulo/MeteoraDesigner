package com.meteoradesigner.service.user;

import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaUserRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO doc
@Service
@Transactional(readOnly = true)
public class UserServiceImp extends GenericAbstractCrudService<User, Integer> implements UserService {

    @Autowired
    private DataJpaUserRepository repository;

    @Override
    protected GenericAbstractCrudRepository<User, Integer> getRepository() {
        return repository;
    }
}

package com.IcecreamApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Role;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.CategoryRepository;
import com.IcecreamApp.repository.RoleRepository;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.systemConstant.ERole;
import com.IcecreamApp.systemConstant.EStatus;
import com.google.common.collect.Sets;

//@Component
public class PriorData implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
    	roleRepository.deleteAll();
    	roleRepository.save(new Role(ERole.ROLE_ADMIN));
    	roleRepository.save(new Role(ERole.ROLE_STAFF));
    	roleRepository.save(new Role(ERole.ROLE_USER));
    	
    	categoryRepository.deleteAll();
    	categoryRepository.save(new Category("Electronics"));
    	categoryRepository.save(new Category("Mathematics"));
    	categoryRepository.save(new Category("Computer science"));
    	categoryRepository.save(new Category("Economics"));
    	
    	userRepository.deleteAll();
    	userRepository.save(new User("SanDang", "sandang@hotmail.com", encoder.encode("1234"), EStatus.AVAILABLE, 
    			Sets.newHashSet(new Role(1L, ERole.ROLE_ADMIN), new Role(2L, ERole.ROLE_STAFF))));
    	userRepository.save(new User("JohnDoe", "johndoe@gmail.com", encoder.encode("1234"), EStatus.AVAILABLE, 
    			Sets.newHashSet(new Role(1L, ERole.ROLE_ADMIN))));
    	userRepository.save(new User("Socrates", "socrates@yahoo.com", encoder.encode("1234"), EStatus.AVAILABLE, 
    			Sets.newHashSet(new Role(3L, ERole.ROLE_USER))));
    	userRepository.save(new User("DavidBowie", "Bowie@gmail.com", encoder.encode("1234"), EStatus.AVAILABLE, 
    			Sets.newHashSet(new Role(2L, ERole.ROLE_STAFF))));
    	
    }
}
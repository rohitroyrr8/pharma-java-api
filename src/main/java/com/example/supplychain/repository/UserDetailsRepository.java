package com.example.supplychain.repository;

import com.example.supplychain.model.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserLoginDetails, Long> {

    public UserLoginDetails getById(Long id);

    public  UserLoginDetails getByUserName(String userName);

    public UserLoginDetails getByEmailId(String emailId);

}

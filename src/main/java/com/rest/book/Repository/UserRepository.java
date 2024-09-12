package com.rest.book.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.book.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT * FROM user_table", nativeQuery = true)
    UserModel user();

    @Query(value = "SELECT * from user_table", nativeQuery = true)
    List<UserModel> getAllUser();

    @Query(value = "SELECT * from user_table where name =:user ", nativeQuery = true)
    List<UserModel> getUsersByName(@Param("user") String user);

    @Query(value = "SELECT * from user_table where name =:userName and age =:userAge", nativeQuery = true)
    List<UserModel> getByNameAndAge(@Param("userName") String userName, @Param("userAge") int userAge);

    @Query(value = "SELECT * FROM user_table where age >=:userAge ", nativeQuery = true)
    List<UserModel> getByAge(@Param("userAge") int userAge);

    @Query(value = "SELECT * FROM user_table ORDER BY age DESC", nativeQuery = true)
    List<UserModel> getUsersOrderedByAge();

    @Query(value = "SELECT * FROM user_table where name =:name", nativeQuery = true)
    UserModel getUserBYName(@Param("name") String name);

}

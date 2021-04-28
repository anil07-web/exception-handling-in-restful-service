package com.stackroute.SoulmateRESTservice.repository;

import com.stackroute.SoulmateRESTservice.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    @Query("From Profile where name=:name")
    List<Profile> getAllProfilesByName(@Param("name") String name);

    @Query("From Profile where gender=:gender")
    List<Profile> getAllProfilesByGender(@Param("gender") String name);

    @Query("From Profile where age=:age")
    List<Profile> getAllProfilesByAge(@Param("age") int age);

}

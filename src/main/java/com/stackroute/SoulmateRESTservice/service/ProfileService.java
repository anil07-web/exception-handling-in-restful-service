package com.stackroute.SoulmateRESTservice.service;

import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import com.stackroute.SoulmateRESTservice.exception.ProfileNotFoundException;
import com.stackroute.SoulmateRESTservice.model.Profile;

import java.util.List;

public interface ProfileService {
    Profile saveProfile(Profile profile) throws ProfileAlreadyExistsException;
    List<Profile> getALLProfiles();
    void delete(int id);
    Profile getProfileById(int id) throws ProfileNotFoundException;
    Profile updateProfile(Profile profile) throws Exception;
    List<Profile> searchProfileByName(String name)  ;
    List<Profile> searchProfileByGender(String gender);
    List<Profile> searchProfileByAge(int age);

}

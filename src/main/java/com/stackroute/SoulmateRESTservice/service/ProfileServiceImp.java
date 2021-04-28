package com.stackroute.SoulmateRESTservice.service;

import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import com.stackroute.SoulmateRESTservice.exception.ProfileNotFoundException;
import com.stackroute.SoulmateRESTservice.model.Profile;
import com.stackroute.SoulmateRESTservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImp implements ProfileService {
    ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImp(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile saveProfile(Profile profile) throws ProfileAlreadyExistsException {
        if(profileRepository.existsById(profile.getId())){
            throw new ProfileAlreadyExistsException();
        }
        Profile savedprofile = profileRepository.save(profile);
        return savedprofile;
    }

    @Override
    public List<Profile> getALLProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

    @Override
    public void delete(int id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getProfileById(int id) throws ProfileNotFoundException {
        Optional<Profile> profileDB =this.profileRepository.findById(id);
        if(profileDB.isPresent()){
            return profileDB.get();
        }else{
            throw new ProfileNotFoundException();
        }
    }


    @Override
    public Profile updateProfile(Profile profile) throws Exception {
        Optional<Profile> userDB = this.profileRepository.findById(profile.getId());
        if (userDB.isPresent()) {
            Profile userUpdate = userDB.get();
            userUpdate.setId(profile.getId());
            userUpdate.setName(profile.getName());
            userUpdate.setGender(profile.getGender());
            userUpdate.setAge(profile.getAge());
            profileRepository.save(userUpdate);
            return userUpdate;
        } else {
            throw new Exception("Record not found with given id");
        }
    }

    @Override
    public List<Profile> searchProfileByName(String name)   {

        return (List<Profile>) profileRepository.getAllProfilesByName(name);
    }

    @Override
    public List<Profile> searchProfileByGender(String gender) {
        return (List<Profile>) profileRepository.getAllProfilesByGender(gender);
    }
    @Override
    public List<Profile> searchProfileByAge(int age) {
        return (List<Profile>) profileRepository.getAllProfilesByAge(age);
    }
}

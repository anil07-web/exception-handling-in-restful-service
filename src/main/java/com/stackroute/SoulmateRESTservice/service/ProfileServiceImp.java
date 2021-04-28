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
        if (profileRepository.existsById(profile.getId())) {
            throw new ProfileAlreadyExistsException();
        }
        Profile savedprofile = profileRepository.save(profile);
        return savedprofile;
    }

    @Override
    public List<Profile> getALLProfiles() throws ProfileNotFoundException{
    List<Profile> userDB= (List<Profile>) this.profileRepository.findAll();
        if(userDB.isEmpty()){
        throw new ProfileNotFoundException();
    }
        return (List<Profile>) profileRepository.findAll();
}
    @Override
    public void delete(int id) throws ProfileNotFoundException {
        Optional<Profile> userDB=this.profileRepository.findById(id);
        if(userDB.isPresent()){
            this.profileRepository.delete(userDB.get());
        }else{
            throw new ProfileNotFoundException();
        }
    }

    @Override
    public Profile getProfileById(int id) throws ProfileNotFoundException {
        Optional<Profile> profileDB = this.profileRepository.findById(id);
        if (profileDB.isPresent()) {
            return profileDB.get();
        } else {
            throw new ProfileNotFoundException();
        }
    }


    @Override
    public Profile updateProfile(Profile user) throws ProfileNotFoundException {
        Optional<Profile> userDB =this.profileRepository.findById(user.getId());
        if(userDB.isPresent()) {
            Profile userUpdate = userDB.get();
            userUpdate.setId(user.getId());
            userUpdate.setName(user.getName());
            userUpdate.setGender(user.getGender());
            userUpdate.setAge(user.getAge());
            profileRepository.save(userUpdate);
            return userUpdate;
        }else{
            throw new ProfileNotFoundException();
        }

    }
    @Override
    public List<Profile> searchProfileByName(String name) throws ProfileNotFoundException {
        List<Profile> userDb=this.profileRepository.getAllProfilesByName(name);
        if(userDb.isEmpty()){
            throw new ProfileNotFoundException();
        }
        return profileRepository.getAllProfilesByName(name);
    }

    @Override
    public List<Profile> searchProfileByGender(String gender) throws ProfileNotFoundException {
        List<Profile> userDB=this.profileRepository.getAllProfilesByGender(gender);
        if(userDB.isEmpty()){
            throw new ProfileNotFoundException();
        }
        return profileRepository.getAllProfilesByGender(gender);
    }
    @Override
    public List<Profile> searchProfileByAge(int age) throws ProfileNotFoundException {
        List<Profile> userDb=this.profileRepository.getAllProfilesByAge(age);
        if(userDb.isEmpty()){
            throw new ProfileNotFoundException();
        }
        return profileRepository.getAllProfilesByAge(age);
    }
}

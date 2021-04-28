package com.stackroute.SoulmateRESTservice.controller;

import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import com.stackroute.SoulmateRESTservice.exception.ProfileNotFoundException;
import com.stackroute.SoulmateRESTservice.model.Profile;
import com.stackroute.SoulmateRESTservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // This method is used for Saving the given profile//
    @PostMapping("/profile")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) throws ProfileAlreadyExistsException, Exception {
        Profile savedprofile = profileService.saveProfile(profile);
        return new ResponseEntity<>(savedprofile, HttpStatus.CREATED);
    }

    // This method is used for Retriving the saved profiles//
    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() throws ProfileNotFoundException, Exception {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.getALLProfiles(), HttpStatus.OK);
    }

    //Deleting the profile Based on Id//
    @DeleteMapping("/profile/{id}")
    private void deleteProfile(@PathVariable("id") int id) throws ProfileNotFoundException, Exception {
        profileService.delete(id);
    }

    //Searching the profile in database based on Id//
    @GetMapping("/profile/{id}")
    private Profile getProfile(@PathVariable("id") int id) throws ProfileNotFoundException, Exception {
        return profileService.getProfileById(id);
    }

    //Updating the profile values Based on Id//
    @PutMapping("/profile/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody Profile profile) throws ProfileNotFoundException, Exception {
        profile.setId(id);
        return ResponseEntity.ok().body(this.profileService.updateProfile(profile));
    }

    //Searching the profile in database based on name//
    @GetMapping("/profiles/name")
    public ResponseEntity<List<Profile>> profileByName(@RequestParam(value = "name") String name) throws ProfileNotFoundException, Exception {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByName(name), HttpStatus.OK);

    }

    //Searching the profile in database based on gender//
    @GetMapping("/profiles/gender")
    public ResponseEntity<List<Profile>> profileByGender(@RequestParam(value = "gender") String gender) throws ProfileNotFoundException, Exception {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByGender(gender), HttpStatus.OK);
    }

    //Searching the profile in database based on age//
    @GetMapping("/profiles/age")
    public ResponseEntity<List<Profile>> profileByAge(@RequestParam(value = "age") int age) throws ProfileNotFoundException, Exception {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByAge(age), HttpStatus.OK);

    }
}

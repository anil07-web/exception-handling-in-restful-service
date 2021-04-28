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

    @PostMapping("/profile")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) throws ProfileAlreadyExistsException {
        Profile savedprofile=profileService.saveProfile(profile);
        return new ResponseEntity<>(savedprofile, HttpStatus.CREATED);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles(){
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.getALLProfiles(),HttpStatus.OK);
    }

    @DeleteMapping("/profile/{id}")
    private void deleteProfile(@PathVariable("id") int id)
    {
        profileService.delete(id);
    }
    @GetMapping("/profile/{id}")
    private Profile getProfile(@PathVariable("id") int id) throws ProfileNotFoundException {
        return profileService.getProfileById(id);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id,@RequestBody Profile profile) throws Exception {
        profile.setId(id);
        return ResponseEntity.ok().body(this.profileService.updateProfile(profile));
    }

    @GetMapping("/profiles/name")
    public ResponseEntity<List<Profile>> profileByName(@RequestParam(value="name") String name)  {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByName(name),HttpStatus.OK);

    }


    @GetMapping("/profiles/gender")
    public ResponseEntity<List<Profile>> profileByGender(@RequestParam(value="gender")String gender)  {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByGender(gender),HttpStatus.OK);

    }

    @GetMapping("/profiles/age")
    public ResponseEntity<List<Profile>> profileByAge(@RequestParam(value="age") int age)  {
        return new ResponseEntity<List<Profile>>((List<Profile>) profileService.searchProfileByAge(age),HttpStatus.OK);

    }
}

package com.stackroute.SoulmateRESTservice.service;

import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import com.stackroute.SoulmateRESTservice.exception.ProfileNotFoundException;
import com.stackroute.SoulmateRESTservice.model.Profile;
import com.stackroute.SoulmateRESTservice.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    private ProfileRepository profileRepository;
    private Profile profile;
    private Profile profile1;
    private List<Profile> profileList;

    @InjectMocks
    private ProfileServiceImp profileService;

    @BeforeEach
    public void setUp() {
        profile1 = new Profile(2, "pranay", "m", 25);
    }

    @Test
    public void givenProfileToSaveShouldReturnSavedProfile() throws ProfileAlreadyExistsException {
        Profile profile = new Profile(1, "Anil", "m", 25);
        when(profileRepository.save(any())).thenReturn(profile);
        profileService.saveProfile(profile);
        verify(profileRepository, times(1)).save(any());

    }

    @Test
    public void givenGetAllProfilesThenShowShouldReturnListOfAllProfiles() throws ProfileNotFoundException {
        profileRepository.save(profile);
        when(profileRepository.findAll()).thenReturn(profileList);
        List<Profile> profileList = profileService.getALLProfiles();
        assertEquals(profileList, profileList);
        verify(profileRepository, times(1)).save(profile);
        verify(profileRepository, times(1)).findAll();
    }

}
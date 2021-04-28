package com.stackroute.SoulmateRESTservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import com.stackroute.SoulmateRESTservice.exception.ProfileNotFoundException;
import com.stackroute.SoulmateRESTservice.model.Profile;
import com.stackroute.SoulmateRESTservice.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ProfileService profileService;
    private Profile profile;
    private List<Profile> profileList;
    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    public void setUp() {
        profile = new Profile(1, "Anil", "male", 21);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void givenProfileToSaveShouldReturnSaveProfile() throws Exception, ProfileAlreadyExistsException {
        when(profileService.saveProfile(any())).thenReturn(profile);
        mockMvc.perform(post("/api/v1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(profile)))
                .andExpect(status().isCreated());
        verify(profileService, times(1)).saveProfile(any());
    }

    @Test
    public void getAllProfilesThenShouldReturnListOfUsers() throws Exception, ProfileNotFoundException {
        when(profileService.getALLProfiles()).thenReturn(profileList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(profile)))
                .andDo(MockMvcResultHandlers.print());
        verify(profileService, times(1)).getALLProfiles();
    }

    @Test
    public void deleteProfile() throws Exception, ProfileNotFoundException {
        profileService.delete(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(profile)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
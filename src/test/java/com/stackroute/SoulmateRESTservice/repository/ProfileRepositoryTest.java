package com.stackroute.SoulmateRESTservice.repository;

import com.stackroute.SoulmateRESTservice.model.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProfileRepositoryTest {
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void givemProfileToSaveShouldReturnSavedProfile() {
        Profile profile = new Profile(1, "Anil", "m", 25);
        profileRepository.save(profile);
        Profile profile1 = profileRepository.findById(profile.getId()).get();
        assertNotNull(profile1);
        assertEquals(profile1.getName(), profile1.getName());
    }

    @Test
    public void givemGetAllProfileToSaveShouldReturnListOfAllProfile() {
        Profile profile = new Profile(2, "Pranay", "m", 25);
        Profile profile1 = new Profile(3, "Tharun", "m", 27);
        profileRepository.save(profile);
        profileRepository.save(profile1);
        List<Profile> profileList = (List<Profile>) profileRepository.findAll();
        assertEquals("Tharun", profileList.get(1).getName());
    }
}
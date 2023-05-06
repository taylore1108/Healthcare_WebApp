package com.team11.hhs;

import com.team11.hhs.DTO.BedDTO;
import com.team11.hhs.model.Bed;
import com.team11.hhs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BedTests {

    // test creating a new bed
    @Test
    public void testCreateBed() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Bed bed = new Bed();
        bed.setName("Test Bed");
        userService.saveBed(bed);
        Mockito.verify(userService).saveBed(bed);
    }

    // test updating a bed
    @Test
    public void testUpdateBed() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Bed bed = new Bed();
        bed.setName("Test Bed");
        userService.saveBed(bed);

        BedDTO bedDTO = new BedDTO();
        bedDTO.setName("Test Bed");

        bedDTO.setUsername("Test User");
        userService.updateBed(bedDTO);
        Mockito.verify(userService).updateBed(bedDTO);
    }

    //test deleting a bed
    @Test
    public void testDeleteBed() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Bed bed = new Bed();
        bed.setName("Test Bed");
        userService.saveBed(bed);
        Mockito.verify(userService).saveBed(bed);
        userService.deleteBed(bed.getName());
        Mockito.verify(userService).deleteBed(bed.getName());
    }

}

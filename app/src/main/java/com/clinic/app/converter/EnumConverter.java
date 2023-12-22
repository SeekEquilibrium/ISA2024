package com.clinic.app.converter;

import com.clinic.app.model.Gender;
import org.springframework.stereotype.Service;

@Service
public class EnumConverter {
    public Gender stringToGender(String input) {
        if(input.trim().equalsIgnoreCase("male")){
            return Gender.MALE;
        }else {
            return Gender.FEMALE;
        }
    }


}

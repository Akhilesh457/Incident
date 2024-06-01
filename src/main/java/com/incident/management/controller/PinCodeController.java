package com.incident.management.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.incident.management.service.PincodeService;

@RestController
public class PinCodeController {
	@Autowired
	private PincodeService pincodeService;

    @GetMapping("/location/{pinCode}")
    public Map<String, String> getLocationByPinCode(@PathVariable String pinCode) {
        //Map<String, LocationDTO> pinCodeToLocation = new HashMap<>();

    	Map<String, String> location = pincodeService.getPincodeDetails(pinCode);
        if (location != null) {
            return location;
        } else {
            return null;
        }
    }
}


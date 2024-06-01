//package com.incident.management.entity;
//
//import com.incident.management.entity.Pincode.PostOffice;
//import com.incident.management.service.PincodeService;
//
//import lombok.NonNull;
//
//
//public class PincodeEntity {
//    // ...
//
//    @NonNull
//    private String pincode;
//
//    @NonNull
//    private String state;
//
//    @NonNull
//    private String country;
//
//    // ...
//
//    public void setPincode(String pincode) {
//        this.pincode = pincode;
//        PincodeService pincodeService = new PincodeService();
//        Pincode response = pincodeService.getPincodeDetails(pincode);
//        if (response != null && response.getPostOffices() != null && !response.getPostOffices().isEmpty()) {
//            PostOffice postOffice = response.getPostOffices().get(0);
//            this.state = postOffice.getState();
//            this.country = postOffice.getCountry();
//        } else {
//            this.state = null;
//            this.country = null;
//        }
//    }
//}

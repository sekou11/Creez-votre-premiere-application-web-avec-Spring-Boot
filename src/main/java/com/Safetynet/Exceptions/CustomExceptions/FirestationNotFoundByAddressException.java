package com.Safetynet.Exceptions.CustomExceptions;

public class FirestationNotFoundByAddressException extends RuntimeException{
    String address;

    public FirestationNotFoundByAddressException(String address) {
        super("No firestation found for this address "+address);
        this.address = address;
    }

   
}

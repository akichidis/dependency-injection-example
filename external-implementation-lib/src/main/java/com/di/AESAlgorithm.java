package com.di;

/**
 * Created by tasos on 25/02/2018.
 */
public class AESAlgorithm implements EncryptionAlgorithm {
    private String parameter1;

    public AESAlgorithm(String parameter1) {
        this.parameter1 = parameter1;
    }

    @Override
    public byte[] encrypt(byte[] payload, byte[] key) {
        System.out.println("Parameter1 value: " + parameter1);
        System.out.println("Got payload size: " + payload.length + " and key size: " + key.length);

        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] payload, byte[] key) {
        System.out.println("Got payload size: " + payload.length + " and key size: " + key.length);

        return new byte[0];
    }
}

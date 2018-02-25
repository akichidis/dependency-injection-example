package com.di;

/**
 * Created by tasos on 25/02/2018.
 */
public interface EncryptionAlgorithm {

    public byte[] encrypt(byte[] payload, byte[] key);

    public byte[] decrypt(byte[] payload, byte[] key);
}

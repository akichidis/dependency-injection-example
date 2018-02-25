package com.di;

/**
 * Created by tasos on 25/02/2018.
 */
public class AESAlgorithmFactory implements EncryptionAlgorithmFactory {

    @Override
    public EncryptionAlgorithm create(Context ctx) {
        return new AESAlgorithm(ctx.getParameter1());
    }
}

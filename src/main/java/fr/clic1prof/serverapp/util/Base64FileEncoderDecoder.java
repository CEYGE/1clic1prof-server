package fr.clic1prof.serverapp.util;

import java.util.Base64;

public class Base64FileEncoderDecoder implements FileEncoderDecoder {

    @Override
    public String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public byte[] decode(String content) {
        return Base64.getDecoder().decode(content);
    }
}

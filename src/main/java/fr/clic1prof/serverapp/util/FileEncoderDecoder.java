package fr.clic1prof.serverapp.util;

public interface FileEncoderDecoder {

    String encode(byte[] bytes);

    byte[] decode(String content);
}

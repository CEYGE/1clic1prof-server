package fr.clic1prof.serverapp.file.model;

import java.util.Date;

public interface DocumentModel {

    int getId();

    int getOwnerId();

    long getSize();

    String getFileId();

    String getName();

    String getMediaType();

    DocumentType getType();

    Date getCreationDate();

    Date getModificationDate();
}

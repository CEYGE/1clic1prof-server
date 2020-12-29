package fr.clic1prof.serverapp.file.model;

import java.util.Date;

public class Document implements DocumentModel {

    private String name, mediaType, fileId;
    private int id, ownerId;
    private long size;

    private DocumentType type;
    private Date creationDate, modificationDate;

    // Used by builder.
    private Document() {}

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getOwnerId() {
        return this.ownerId;
    }

    @Override
    public String getFileId() {
        return this.fileId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMediaType() {
        return this.mediaType;
    }

    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public Date getModificationDate() {
        return this.modificationDate;
    }

    @Override
    public DocumentType getType() {
        return this.type;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    public Builder newBuilder() {

        Builder builder = new Builder();

        builder.name = this.name;
        builder.extension = this.mediaType;
        builder.fileId = this.fileId;
        builder.id = this.id;
        builder.ownerId = this.ownerId;
        builder.size = this.size;
        builder.creationDate = this.creationDate;
        builder.modificationDate = this.modificationDate;
        builder.type = this.type;

        return builder;
    }

    public static Builder builder(DocumentModel model) {

        Builder builder = new Builder();

        builder.name = model.getName();
        builder.extension = model.getMediaType();
        builder.fileId = model.getFileId();
        builder.id = model.getId();
        builder.ownerId = model.getOwnerId();
        builder.size = model.getSize();
        builder.creationDate = model.getCreationDate();
        builder.modificationDate = model.getModificationDate();
        builder.type = model.getType();

        return builder;
    }

    public static class Builder {

        private String name, extension, fileId;
        private int id, ownerId;
        private long size;

        private DocumentType type;
        private Date creationDate, modificationDate;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withExtension(String extension) {
            this.extension = extension;
            return this;
        }

        public Builder withFileId(String fileId) {
            this.fileId = fileId;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withOwnerId(int id) {
            this.ownerId = id;
            return this;
        }

        public Builder withSize(long size) {
            this.size = size;
            return this;
        }

        public Builder withCreationDate(Date date) {
            this.creationDate = date;
            return this;
        }

        public Builder withModificationDate(Date date) {
            this.modificationDate = date;
            return this;
        }

        public Builder withType(DocumentType type) {
            this.type = type;
            return this;
        }

        public Document build() {

            Document document = new Document();

            document.id = this.id;
            document.ownerId = this.ownerId;
            document.name = this.name;
            document.mediaType = this.extension;
            document.fileId = this.fileId;
            document.size = this.size;
            document.creationDate = this.creationDate;
            document.modificationDate = this.modificationDate;
            document.type = this.type;

            return document;
        }
    }
}

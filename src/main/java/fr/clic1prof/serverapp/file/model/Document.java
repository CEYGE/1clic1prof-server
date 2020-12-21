package fr.clic1prof.serverapp.file.model;

import java.util.Date;

public class Document {

    private String name, path;
    private int ownerId;

    private DocumentType type;
    private Date creation, modification;

    private Document() {}

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public DocumentType getType() {
        return this.type;
    }

    public Date getCreation() {
        return this.creation;
    }

    public Date getModification() {
        return this.modification;
    }

    public static class Builder {

        private String name, path;
        private int ownerId;

        private DocumentType type;
        private Date creation, modification;

        public Builder(String name, String path, int ownerId) {
            this.name = name;
            this.path = path;
            this.ownerId = ownerId;
        }

        public Builder withType(DocumentType type) {
            this.type = type;
            return this;
        }

        public Builder withCreationDate(Date creation) {
            this.creation = creation;
            return this;
        }

        public Builder withModificationDate(Date modification) {
            this.modification = modification;
            return this;
        }

        public Document build() {

            Document document = new Document();

            document.name = this.name;
            document.path = this.path;
            document.ownerId = this.ownerId;
            document.type = this.type;
            document.creation = this.creation;
            document.modification = this.modification;

            return document;
        }
    }
}

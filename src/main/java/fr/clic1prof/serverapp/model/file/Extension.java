package fr.clic1prof.serverapp.model.file;

public enum Extension {

    PNG, JPG, PDF, DOC, DOCX, ODT, RTF, PTT, PPTX, XLS, XSLX, ODS, ODP;

    public String getName() {
        return this.name().toLowerCase();
    }
}

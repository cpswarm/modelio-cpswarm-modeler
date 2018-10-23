package org.modelio.module.cpswarm.frevo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("a5faea44-0b0e-493d-8674-946388575c1e")
public abstract class Generator {
    @objid ("78fe22e3-4e5d-4b6a-8d6a-818b5a1a560c")
    protected String currentIndent = "";

    @objid ("17ff0f79-16c8-44c6-914c-dba95b876cdb")
    protected String defaultIndent = "\t";

    @objid ("7bfe7d2b-1fb6-4980-8103-47661e093de3")
    protected StringBuffer content = new StringBuffer();

    @objid ("59481f55-c357-42b0-9fde-e37d31cce481")
    protected void addLine(String line) {
        this.content.append(this.currentIndent + line + "\r\n");
    }

    @objid ("46e0158f-ff24-4203-865e-7e95e7606b41")
    protected void addEmptyLine() {
        this.content.append("\r\n");
    }

    @objid ("04b085f5-995c-48dc-9e83-16ad7ec63e60")
    protected void increaseIndent() {
        this.currentIndent += this.defaultIndent;
    }

    @objid ("573ce404-151a-4004-bcb1-685eb8a3b5d8")
    protected void decreaseIndent() {
        this.currentIndent = this.currentIndent.replaceFirst(defaultIndent, "");
    }

}

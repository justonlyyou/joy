package org.joy.swing;

/**
 * 文档水印状态
 */
public class DocWartermarkStatus {

    private String personName;
    private String status;

    public DocWartermarkStatus(String status, String personName) {
        setStatus(status);
        setPersonName(personName);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public boolean needDraw() {
        return (status != null && !status.trim().isEmpty());
    }
}

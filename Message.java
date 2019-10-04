package models;

import java.io.Serializable;


public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // the message can be image or plain text so if it is an image: content is null, type="image"
    // and if it is plain text: filePath and fileData are null, type="text"
    private String content;
    private String filePath;
    private byte[] fileData;
    private String type;
    private String fileName;
    private User user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}

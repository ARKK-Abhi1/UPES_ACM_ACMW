package org.upesacm.acmacmw;

public class ProjectDetail {
    private Integer image;
    private String name,description;
    public ProjectDetail(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }
    public Integer getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

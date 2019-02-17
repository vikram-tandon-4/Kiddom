package kiddomchallenge.vikramtandonapps.com.model;

import java.io.Serializable;

public class Result implements Serializable {

    String description;
    String name;
    Image image;
    DetailedDescription detailedDescription;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public DetailedDescription getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(DetailedDescription detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
}

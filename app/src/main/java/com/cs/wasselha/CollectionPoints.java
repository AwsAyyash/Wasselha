package com.cs.wasselha;

import android.widget.Button;

public class CollectionPoints {

    int image;
    String collectionPointName;
    Button manageBtn;

    public CollectionPoints(int image, String collectionPointName, Button manageBtn)
    {
        this.image = image;
        this.collectionPointName = collectionPointName;
        this.manageBtn = manageBtn;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCollectionPointName() {
        return collectionPointName;
    }

    public void setCollectionPointName(String collectionPointName) {
        this.collectionPointName = collectionPointName;
    }

    public Button getManageBtn() {
        return manageBtn;
    }

    public void setManageBtn(Button manageBtn) {
        this.manageBtn = manageBtn;
    }
}

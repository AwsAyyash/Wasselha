package com.cs.wasselha.Models;

import android.widget.Button;

public class CollectionPointsModel {

    int image;
    String nameCollectionPoint;
    Button manageBtn;

    public CollectionPointsModel(int image, String nameCollectionPoint, Button manageBtn)
    {
        this.image = image;
        this.nameCollectionPoint = nameCollectionPoint;
        this.manageBtn = manageBtn;
    }

    public int getImage() {
        return image;
    }

    public String getNameCollectionPoint() {
        return nameCollectionPoint;
    }

    public Button getManageBtn() {
        return manageBtn;
    }
}

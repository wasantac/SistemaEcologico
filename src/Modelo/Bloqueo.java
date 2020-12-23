/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Walter Santacruz
 */
public class Bloqueo {
    private ImageView sprite;

    public Bloqueo() {
        this.sprite  =new ImageView( new Image("/Assets/roca.png"));
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
    
    
}

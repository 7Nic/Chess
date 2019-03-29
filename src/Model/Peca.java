/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ModelTabuleiro;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jbatista
 */
public abstract class Peca {
    
    protected final static String imgPath = "img/pecas.png";
    protected static BufferedImage pecasImg = null;    
    protected static ModelTabuleiro model;
    protected Cor cor;
    protected Point quadrante;
    protected int quadranteX;
    protected int quadranteY;
    protected int oldQuadX;
    protected int oldQuadY;
    
    public enum Cor{
        PRETO,
        BRANCO
    }
    
    public Peca(Cor cor, int x, int y)  {
        this.cor = cor;
        this.quadranteX = x;
        this.quadranteY = y;
        this.oldQuadX = x;
        this.oldQuadY = y;
        this.quadrante = new Point(x,y);
        if(pecasImg == null){
            try {
                pecasImg = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Peca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addModel(ModelTabuleiro model) {
        this.model = model;
    }
    
    public boolean inSquare(int x, int y){
        if(x/60 == quadrante.x && y/60 == quadrante.y) 
            return true;
        else 
            return false;
    }
    
    public int getQuadranteX() {
        return this.quadranteX;
    }
    
    public int getQuadranteY() {
        return this.quadranteY;
    }
    
    public int getOldQuadX() {
        return this.oldQuadX;
    }
    
    public int getOldQuadY() {
        return this.oldQuadY;
    }
    
    public void setQuadrante(int quadX, int quadY){
        quadrante.setLocation(quadX, quadY);
        this.quadranteX = quadX;
        this.quadranteY = quadY;
    }
    
    public void alterarOldQuad(int x, int y) {
        this.oldQuadX = x/60;
        this.oldQuadY = y/60;
    }
    
    public abstract void draw(Graphics2D g);
    public abstract boolean validMove(int quadX, int quadY);
}
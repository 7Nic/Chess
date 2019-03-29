/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;

/**
 *
 * @author jbatista
 */
public class Peao extends Peca{

    public Peao(Cor cor, int x, int y)  {
        super(cor, x, y);
    }
    
    @Override
    public boolean validMove(int quadX, int quadY) {
        if(quadX != this.getOldQuadX()) return false;
        
        if(this.cor == Peca.Cor.PRETO){
            //Primeira jogada
            if(this.getOldQuadY() == 1) {
                if(quadY < this.getOldQuadY() + 1 || quadY > this.getOldQuadY() + 2 || (this.model.findPeca(quadX*60, 2*60) != null && this.model.findPeca(quadX*60, 2*60) != this)) return false;
            } else {
                if(quadY != this.getOldQuadY() + 1) return false;
            }
        } else {
            //Primeira jogada
            if(this.getOldQuadY() == 6) {
                if(quadY > this.getOldQuadY() - 1 || quadY < this.getOldQuadY() - 2 || this.model.findPeca(quadX*60, 5*60) != null) return false;
            } else {
                if(quadY != this.getOldQuadY() - 1) return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics2D g) {
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        int x0 = quadrante.x * squareWidth;
        int y0 = quadrante.y * squareHeight;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 20, 360, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 72, 360, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Peao Preta";
        } else {
            return "Peao Branca";
        }
    }
}

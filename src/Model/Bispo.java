/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.lang.Math;
import java.awt.Graphics2D;

/**
 *
 * @author gnicolau
 */
public class Bispo extends Peca {
    
    public Bispo(Cor cor, int x, int y) {
        super(cor, x, y);
    }
    
    @Override
    public boolean validMove(int quadX, int quadY) {
        int oldQuadX = this.getOldQuadX();
        int oldQuadY = this.getOldQuadY();
        if (Math.abs(quadX - oldQuadX) != Math.abs(quadY - oldQuadY)) return false;
        
        int colOffset, rowOffset;
        
        if (quadY - oldQuadY > 0) {
            //Descendo
            rowOffset = 1;
        } else {
            //Subindo
            rowOffset = -1;
        }
        
        if (quadX - oldQuadX > 0) {
            //Direita
            colOffset = 1;
        } else {
            //Esquerda
            colOffset = -1;
        }
        
        int y = oldQuadY + rowOffset;
        for (int x = oldQuadX + colOffset; x != quadX || y != quadY; x += colOffset) {
            System.out.printf("Verificando (%d, %d)\n", x, y);
            if (this.model.findPeca(x*60, y*60) != null){
                System.out.printf("Peca no caminho (%d, %d) %s\n", x, y, model.findPeca(x*60, y*60));
                return false;
            }
            
            y += rowOffset;
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
        
        if (this.cor == Peca.Cor.PRETO) {
            g.drawImage(pecasImg, x0, y0, x1, y1, 201, 20, 241, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 201, 70, 241, 110, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO) {
            return "Bispo Preta";
        } else {
            return "Bispo Branca";
        }
    }
}

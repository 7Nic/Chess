/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.util.Pair ;
import java.awt.Graphics2D;

/**
 *
 * @author gnicolau
 */
public class Rei extends Peca {
    
    public Rei(Cor cor, int x, int y) {
        super(cor, x, y);
    }
    
    @Override
    public boolean validMove(int quadX, int quadY) {
        int oldQuadX = this.getOldQuadX();
        int oldQuadY = this.getOldQuadY();
        Pair[] posValidas = {
            new Pair(oldQuadX-1, oldQuadY-1),
            new Pair(oldQuadX, oldQuadY-1),
            new Pair(oldQuadX-1, oldQuadY),
            new Pair(oldQuadX+1, oldQuadY-1),
            new Pair(oldQuadX+1, oldQuadY),
            new Pair(oldQuadX, oldQuadY+1),
            new Pair(oldQuadX+1, oldQuadY+1),
            new Pair(oldQuadX-1, oldQuadY+1)
        };
        
        Pair posAtual = new Pair(quadX, quadY);
        
        for(Pair posAnalise: posValidas) {
            if(posAtual.equals(posAnalise)) return true;
        }
        
        return false;
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 20, 20, 60, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 20, 72, 60, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO) {
            return "Rei Preta";
        } else {
            return "Rei Branca";
        }
    }
}

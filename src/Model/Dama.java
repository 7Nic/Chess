/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;

/**
 *
 * @author gnicolau
 */
public class Dama extends Peca {
    
    public Dama(Cor cor, int x, int y) {
        super(cor, x, y);
    }
    
    @Override
    public boolean validMove(int quadX, int quadY) {
        int oldQuadX = this.getOldQuadX();
        int oldQuadY = this.getOldQuadY();
        
        System.out.printf("(%d,%d) ==> (%d,%d)\n", this.getOldQuadX(), this.getOldQuadY(), quadX, quadY);
        
        //Se coluna e linha são diferentes ou se o deltaX for diferente do deltaY o movimento é invalido
        if((quadX != oldQuadX && quadY != oldQuadY) && (Math.abs(quadX - oldQuadX) != Math.abs(quadY - oldQuadY))) return false;
        
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
        
//        if (quadX == oldQuadX || quadY == oldQuadY) {
//            //Movimento em linha reta
//            //Se for vertical
            if (quadX == oldQuadX) colOffset = 0;
            ///Se for diagonal
            if (quadY == oldQuadY) rowOffset = 0;
        
        //Verificar se há alguma peça no caminho (excetuando a própria posição inicial)
        int y = oldQuadY + rowOffset;
        for (int x = oldQuadX + colOffset; x != quadX || y != quadY; x += colOffset) {
            System.out.printf("Verificando (%d, %d)\n", x, y);
            if (this.model.findPeca(x*60, y*60) != null){
                System.out.printf("Peca no caminho (%d, %d) %s\n", x, y, model.findPeca(x*60, y*60));
                return false;
            }
            
            y += rowOffset;
        }
            
//        } else {
//            //Movimento em diagonal
//        }
        
        
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 20, 120, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 71, 120, 111, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO) {
            return "Dama Preta";
        } else {
            return "Dama Branca";
        }
    }
}

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
        //===========Verificação de possibilidade de captura (o movimento de captura é diferente do normal)===========
        boolean droppingOnOppositeColor;
        char turno;
        if (this.cor == Peca.Cor.BRANCO) {
            turno = 'B';
        } else {
            turno = 'C';
        }
        
        //Se a posição não estiver vazia e a cor for diferente
        if (this.model.findPecaBasedOnTurn(quadX, quadY, turno) != null && this.cor != this.model.findPecaBasedOnTurn(quadX, quadY, turno).getCor() ) {
            droppingOnOppositeColor = true;
        } else {
            droppingOnOppositeColor = false;
        }

        //Se andou uma coluna para direita ou esquerda
        if (quadX == this.getOldQuadX() + 1 || quadX == this.getOldQuadX() - 1) {
            //Se for branco e estiver uma linha acima e a cor da outra peca for oposta
            if ((this.cor == Peca.Cor.BRANCO && quadY == this.getOldQuadY() - 1) && droppingOnOppositeColor) {
                return true;
            } 
            //Se for preto e estiver uma linha abaixo e a cor da outra peca for oposta
            if ((this.cor == Peca.Cor.PRETO && quadY == this.getOldQuadY() + 1) && droppingOnOppositeColor){
                return true;
            }
        }
        
        //===========Verificação de movimento válido sem captura===========
        if(quadX != this.getOldQuadX()) return false;
        
        if(this.cor == Peca.Cor.PRETO){
            //Primeira jogada preta
            if(this.getOldQuadY() == 1) {
                if(quadY < this.getOldQuadY() + 1 || quadY > this.getOldQuadY() + 2 || (this.model.findPeca(quadX*60, 2*60) != null && this.model.findPeca(quadX*60, 2*60) != this)) return false;
            } else {
                if(quadY != this.getOldQuadY() + 1) return false;
            }
        } else {
            //Primeira jogada branca
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

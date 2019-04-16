/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import Model.Peca;
import View.Tabuleiro;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 *
 * @author felipelageduarte
 */
public class TabuleiroController implements  MouseListener, MouseMotionListener{

  private Tabuleiro view;
  private ModelTabuleiro model;
  private Peca peca;
  private char turno;
  
    public void addView (Tabuleiro view){
        this.view = view;
    }
    
    public void addModel (ModelTabuleiro model){
        this.model = model;
    }
    
    /*
      USe este metodo para iniciar o seu VIEW... neste caso, antes de motra-lo
    na tela, o posicionamos no centro dela....
    */
    public void runTabuleiro() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - view.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - view.getHeight()) / 2);
        view.setLocation(x, y);
        
        view.setVisible(true);
        //Pega uma peça qualquer e adiciona o model na classe abstrata peca
        //Agora a classe Peca tem acesso a todas as peças
        Peca p = model.findPeca(0, 0);
        System.out.println(p);
        p.addModel(model);
        this.turno = 'B';
    }
    
    public char getTurno() {
        return this.turno;
    }
    
    public void trocaTurno() {
        if (this.turno == 'B') {
            this.turno = 'P';
        } else {
            this.turno = 'B';
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Acha-se qual a peça foi selecionada
        if(model.findPeca(e.getX(), e.getY()) == null) return;
        this.peca = model.findPeca(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Jogador " + this.turno + " jogou");
        boolean jogadorCerto;
        if(peca.getCor() != Peca.Cor.BRANCO && this.turno == 'B' || peca.getCor() != Peca.Cor.PRETO && this.turno == 'P') {
            jogadorCerto = false;
        } else {
            jogadorCerto = true;
        }
        
        System.out.println("jogador certo " + jogadorCerto);
        
        //Aqui é feita a verificação
        verifyMovement(e, jogadorCerto);
        
//=====================IMPLEMENTAR ESSA LÓGICA DE BAIXO =====================================
//        if(!(peca.validMove(e.getX()/60, e.getY()/60))) {
//            System.out.println("1");
//            //Movimento inválido
//            peca.setQuadrante(peca.getOldQuadX(), peca.getOldQuadY());
//            view.repaint();
//        } else {
//            if (model.findPeca(e.getX(), e.getY()) == null) {
//                System.out.println("2");
//                //Se não houver peca no local de chegada o movimento pode ocorrer
//                peca.alterarOldQuad(e.getX(), e.getY());
////                view.repaint();
//            } else if (model.findPeca(e.getX(), e.getY()).getCor() == peca.getCor()) {
//                System.out.println("3");
//                //Se as cores forem iguais o movimento não pode ocorrer
//                peca.setQuadrante(peca.getOldQuadX(), peca.getOldQuadY());
//                view.repaint();
//            } else {
//                System.out.println("4");
//                //Se as cores forem diferentes deve-se capturar a peça
//                System.out.println("capturar");
//                model.findPeca(e.getX(), e.getY()).setQuadrante(15, 15);
//                peca.alterarOldQuad(e.getX(), e.getY());
////                view.repaint();
//            }
//            
////            peca.alterarOldQuad(e.getX(), e.getY());
//        }

    }
    
    
    public void verifyMovement(MouseEvent e, boolean jogadorCerto) {
        //Como a peca muda a posicao juntamente com o mouse, no release a peça retornada no findPeca sera ela mesma, mas caso se tente sobrepor alguma peça, a função retornará outra peca, assim o movimento será invalidado
        if((!(peca.validMove(e.getX()/60, e.getY()/60)) || model.findPecaBasedOnTurn(e.getX(), e.getY(), this.turno) !=  peca) || !jogadorCerto) {
            if (model.findPecaBasedOnTurn(e.getX(), e.getY(), this.turno).getCor() == peca.getCor()) {
                System.out.println("Inválido");
                //Se as cores forem iguais o movimento é invalido
                peca.setQuadrante(peca.getOldQuadX(), peca.getOldQuadY());
                view.repaint();
            } else {
                //Se as cores forem diferentes deve-se capturar a peça
                System.out.println("Capturar");
                model.findPecaBasedOnTurn(e.getX(), e.getY(), this.turno).setQuadrante(15, 15);
                peca.alterarOldQuad(e.getX(), e.getY());
                view.repaint();
                this.trocaTurno();
            }
        } else {
            //Movimento válido normal
            System.out.println("Válido");
            peca.alterarOldQuad(e.getX(), e.getY());
            this.trocaTurno();
        }
        
        System.out.println("Sendo que a peça que eu achei foi " + model.findPecaBasedOnTurn(e.getX(), e.getY(), this.turno));
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //Muda a posição da peça conforme o mouse muda a posição, a verificação da posição é feita no release do mouse
        peca.setQuadrante(e.getX()/60, e.getY()/60);
        view.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        view.getCoordenadaLabel().setText("x:"+x+"  y:"+y+"   -   Quadrante: ["+x/60+","+y/60+"]");
        view.getMouseCoord().setLocation(x, y);
        view.repaint();
    }
}

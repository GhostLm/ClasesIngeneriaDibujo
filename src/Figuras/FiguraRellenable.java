/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figuras;

//import ingenieria1202410.Contorno;
import Figuras.Figura;
import java.awt.Color;

/**
 *
 * @author lmpl6
 */
public abstract class FiguraRellenable  extends Figura{
    protected Color colorDeSegundoPlano;
   // protected Contorno contorno;
    
    public FiguraRellenable(Color colorDeSegundoPlano, Color colorDePrimerPlano){
        super(colorDePrimerPlano);
        this.colorDeSegundoPlano = colorDeSegundoPlano;
    }
     public FiguraRellenable(Color colorDeSegundoPlano){
         this.colorDeSegundoPlano = colorDeSegundoPlano;
        
    }
      public FiguraRellenable(){
        this(Color.WHITE);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author lmpl6
 */
public abstract class Figura {
       
    Color colorDePrimerPlano;
    
    
    public Figura(Color colorDePrimerPlano){
        this.colorDePrimerPlano = colorDePrimerPlano;
        
    }

    public Figura() {
        this(Color.BLACK);
    }
    public abstract void dibujar(Graphics g);
    public abstract void actualizar(Point puntoActual);{
        
    }
    
    
}

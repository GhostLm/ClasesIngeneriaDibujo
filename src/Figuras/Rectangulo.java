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
public class Rectangulo  {

    private final Point puntoIncial;
    private Point puntoFinal;

    public Rectangulo(Point puntoI) {
        this.puntoIncial = puntoI;
        this.puntoFinal = puntoI;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        int coordenadax = Math.min(puntoIncial.x, puntoFinal.x);
        int coordenaday = Math.min(puntoIncial.y, puntoFinal.y);
        int ancho = Math.abs(puntoIncial.x - puntoFinal.x);
        int altura = Math.abs(puntoIncial.y - puntoFinal.y);
        g.drawRect(coordenadax, coordenaday, ancho, altura);
    }

    public void actualizar(Point puntoF) {
        this.puntoFinal = puntoF;
    }
}

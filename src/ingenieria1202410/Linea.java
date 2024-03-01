/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ingenieria1202410;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author josearielpereyra
 */
public class Linea {

    private Point puntoInicial;
    private Point puntoFinal;

    public Linea(Point puntoInicial) {
        this(puntoInicial, puntoInicial);
    }

    public Linea(Point puntoInicial, Point puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        if (puntoInicial != puntoFinal) {
            g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
        }
    }

    public void actualizar(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }
}

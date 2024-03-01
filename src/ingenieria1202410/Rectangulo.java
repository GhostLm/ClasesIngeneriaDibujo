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
 * @author lmpl6
 */
public class Rectangulo {

    private final Point puntoI;
    private Point puntoF;

    public Rectangulo(Point puntoI) {
        this.puntoI = puntoI;
        this.puntoF = puntoI;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        // System.out.println(puntoF.x - puntoI.x + " " + (puntoF.y - puntoI.y));
        if (puntoF.x - puntoI.x >= 0 && puntoF.y - puntoI.y >= 0) {
            g.drawRect(puntoI.x, puntoI.y, puntoF.x - puntoI.x, puntoF.y - puntoI.y);
            return;
        }
        if (puntoF.x - puntoI.x <= 0 && puntoF.y - puntoI.y <= 0) {
            g.drawRect(puntoI.x, puntoI.y, 1, 1);
            return;
        }
        if (puntoF.x - puntoI.x <= 0) {
            g.drawRect(puntoI.x, puntoI.y, 1, puntoF.y - puntoI.y);
            return;
        }
        if (puntoF.y - puntoI.y <= 0) {
            g.drawRect(puntoI.x, puntoI.y, puntoF.x - puntoI.x, 1);
        }

    }

    public void actualizar(Point puntoF) {
        this.puntoF = puntoF;
    }
}

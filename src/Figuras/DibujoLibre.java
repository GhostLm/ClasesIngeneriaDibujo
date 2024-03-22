/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author lmpl6
 */
public class DibujoLibre extends Figura{

    ArrayList<Point> puntos = new ArrayList<>();

    public DibujoLibre(Point puntoInicial) {
        puntos.add(puntoInicial);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        if (puntos.size() == 1) {
            g.drawLine(puntos.get(0).x, puntos.get(0).y, puntos.get(0).x, puntos.get(0).y);
        } else {
            for (int i = 1; i < puntos.size(); i++) {
                g.drawLine(puntos.get(i - 1).x, puntos.get(i - 1).y, puntos.get(i).x, puntos.get(i).y);
            }
        }
    }

    @Override
    public void actualizar(Point puntoFinal) {
        puntos.add(puntoFinal);
    }
}

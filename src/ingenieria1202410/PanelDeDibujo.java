/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ingenieria1202410;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel {

    Linea miLinea;
    DibujoLibre miDibujo;
    Rectangulo miRectangulo;
    private boolean actividadboton = false;
    private String datos;

    ArrayList<Linea> lineas = new ArrayList<>();
    ArrayList<DibujoLibre> dibujos = new ArrayList<>();
    ArrayList<Rectangulo> rectangulos = new ArrayList<>();

    // ArrayList<Object> almacenamiento = new ArrayList<>();
    public PanelDeDibujo() {
        this.setBackground(Color.WHITE);

        //Pasos para manejar eventos en java
        //1- Crear una clase para que funcione como manejador de eventos
        //2- Hacer que la clase del paso 1 implemente la interfaz de manejo de evento deseada
        //3- Decirle al objeto para el cual manejaremos el evento a cual objeto de la clase creada debe 
        //  notificarle cuando suceda el evento
        class Manejador extends MouseAdapter {

            @Override
            public void mousePressed(MouseEvent e) {
                //   System.out.println(actividadboton);
                //  System.out.println(datos);
                if (actividadboton == true) {
                    if ("Lapiz".equals(datos)) {
                        miDibujo = new DibujoLibre(e.getPoint());
                        dibujos.add(miDibujo);
                    } else if ("Linea".equals(datos)) {
                        miLinea = new Linea(e.getPoint());
                        lineas.add(miLinea);
                    } else if ("Rectangulo".equals(datos)) {
                        miRectangulo = new Rectangulo(e.getPoint());
                        rectangulos.add(miRectangulo);
                    }
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (actividadboton == true) {
                    if ("Lapiz".equals(datos)) {
                        miDibujo.actualizar(e.getPoint());        
                    }
                    else if ("Linea".equals(datos)) {
                        miLinea.actualizar(e.getPoint());
                    }
                    else  if ("Rectangulo".equals(datos)) {
                        miRectangulo.actualizar(e.getPoint());
                    }
                    repaint();
                }
            }
        }

        Manejador manejador = new Manejador();

        this.addMouseListener(manejador);
        this.addMouseMotionListener(manejador);
    }

    public void recibeDatos(String dato) {
        this.datos = dato;

    }

    public void observaActividad(boolean estado) {
        this.actividadboton = estado;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Rectangulo rectanguloActual : rectangulos) {
            rectanguloActual.dibujar(g);
        }

        for (DibujoLibre dibujoActual : dibujos) {
            dibujoActual.dibujar(g);
        }

        for (Linea lineaActual : lineas) {
            lineaActual.dibujar(g);
        }
    }
}

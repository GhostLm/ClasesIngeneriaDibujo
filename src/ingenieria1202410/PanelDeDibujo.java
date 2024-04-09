/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ingenieria1202410;

import Figuras.DibujoLibre;
import Figuras.Figura;
import Figuras.Linea;
import Figuras.Rectangulo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel {

    Linea miLinea;
    DibujoLibre miDibujo;
    Rectangulo miRectangulo;
    BufferedImage cargadaImage = null;
    private String datos;

    private static final String CARPETA_GUARDADO = "Temporal";
    ArrayList<Figura> listaFiguras = new ArrayList<>();
    Stack almacenamientoFiguras = new Stack<>();

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

                if ("Lapiz".equals(datos)) {
                    miDibujo = new DibujoLibre(e.getPoint(), Color.BLUE,5);
                    listaFiguras.add(miDibujo);

                } else if ("Linea".equals(datos)) {
                    miLinea = new Linea(e.getPoint(), Color.GREEN, 2);
                    listaFiguras.add(miLinea);

                } else if ("Rectangulo".equals(datos)) {
                    miRectangulo = new Rectangulo(e.getPoint());
                    listaFiguras.add(miRectangulo);
                }
                repaint();

            }

            @Override
            public void mouseDragged(MouseEvent e) {

                if ("Lapiz".equals(datos)) {
                    miDibujo.actualizar(e.getPoint());
                } else if ("Linea".equals(datos)) {
                    miLinea.actualizar(e.getPoint());
                } else if ("Rectangulo".equals(datos)) {
                    miRectangulo.actualizar(e.getPoint());
                }
                repaint();
            }
        }

        Manejador manejador = new Manejador();

        this.addMouseListener(manejador);
        this.addMouseMotionListener(manejador);
    }

    public void recibeDatos(String dato) {
        this.datos = dato;
     
    }

    public void guardarImagen() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        paint(g2d);
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filtroDeImagenPNG = new FileNameExtensionFilter("Imagen PNG", "png");
        fileChooser.addChoosableFileFilter(filtroDeImagenPNG);

        FileNameExtensionFilter filtroDeImagenJPG = new FileNameExtensionFilter("Imagen JPG", "jpg", "jpeg", "jpe", "jfif");
        fileChooser.addChoosableFileFilter(filtroDeImagenJPG);

        int resultado = fileChooser.showSaveDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File seleccionArchivo = fileChooser.getSelectedFile();
            FileNameExtensionFilter filtroSeleccionado = (FileNameExtensionFilter) fileChooser.getFileFilter();

            if (seleccionArchivo != null) {
                String filePath = seleccionArchivo.getAbsolutePath();
                String extension = "";

                if (filtroSeleccionado == filtroDeImagenPNG) {
                    extension = "png";
                    if (!filePath.endsWith(".png")) {
                        filePath += "." + extension;
                    }
                } else if (filtroSeleccionado == filtroDeImagenJPG) {
                    extension = "jpg";
                    if (!filePath.endsWith(".jpg")) {
                        filePath += "." + extension;
                    }
                }

                File archivo = new File(filePath);

                if (archivo.exists()) {
                    int opcionVentana = JOptionPane.showConfirmDialog(this, "El archivo ya existe. ¿Desea sobrescribirlo?",
                            "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (opcionVentana != JOptionPane.YES_OPTION) {
                        return; // No sobrescribir el archivo
                    }
                }

                try {
                    ImageIO.write(imagen, extension, archivo);
                    JOptionPane.showMessageDialog(this, "Dibujo guardado correctamente como " + filePath);
                    listaFiguras.clear();
                    cargadaImage = null;
                    guardarImagenTemporal();
                    repaint();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el dibujo.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

 /*   public void cargarImagen() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setAcceptAllFileFilterUsed(false);// Desactivar la opción "Todos los archivos"
        FileNameExtensionFilter filtroDeImagenPNG = new FileNameExtensionFilter("Imagen PNG", "png");
        fileChooser.setFileFilter(filtroDeImagenPNG);
        FileNameExtensionFilter filtroDeImagenJPG = new FileNameExtensionFilter("Imagen JPG", "jpg", "jpeg", "jpe", "jfif");
        fileChooser.addChoosableFileFilter(filtroDeImagenJPG);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                try {
                    if (!listaFiguras.isEmpty()) {
                        int opcionVentana = JOptionPane.showConfirmDialog(this, "¿Desea Guardar los cambios de dibujo sin Titulo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (opcionVentana == JOptionPane.YES_OPTION) {
                            guardarImagen();
                             listaFiguras.clear();
                           
                           cargadaImage = ImageIO.read(selectedFile);
                    Graphics g = this.getGraphics();
                    g.drawImage(cargadaImage, 0, 0, null);
                    guardarDibujo();
                        }
                        
                    
                       
                    }

                   
                    repaint();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }*/
    
    public void cargarImagen() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false); // Desactivar la opción "Todos los archivos"

    FileNameExtensionFilter filtroDeImagenPNG = new FileNameExtensionFilter("Imagen PNG", "png");
    fileChooser.setFileFilter(filtroDeImagenPNG);

    FileNameExtensionFilter filtroDeImagenJPG = new FileNameExtensionFilter("Imagen JPG", "jpg", "jpeg", "jpe", "jfif");
    fileChooser.addChoosableFileFilter(filtroDeImagenJPG);

    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        if (selectedFile != null) {
            try {
                if (listaFiguras.isEmpty()) {
                    // Si listaFiguras está vacía, cargar la imagen directamente
                    cargadaImage = ImageIO.read(selectedFile);
                    Graphics g = this.getGraphics();
                    g.drawImage(cargadaImage, 0, 0, null);
                } else {
                    // Si listaFiguras no está vacía, preguntar si desea guardar antes de cargar la imagen
                    int opcionVentana = JOptionPane.showConfirmDialog(this, "¿Desea Guardar los cambios de dibujo sin Título?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (opcionVentana == JOptionPane.YES_OPTION) {
                        guardarImagen();
                        
                    }

                    // Cargar la imagen después de guardar o si se elige no guardar
                    listaFiguras.clear(); // Limpiar la lista de figuras
                    cargadaImage = ImageIO.read(selectedFile);
                    Graphics g = this.getGraphics();
                    g.drawImage(cargadaImage, 0, 0, null);
                    guardarDibujo();
                }

                repaint();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
    

    public void guardarImagenTemporal() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        paint(g2d);
        g2d.dispose();
       
        File carpeta = new File(CARPETA_GUARDADO);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crear la carpeta si no existe
        }

        File archivoImagen = new File(carpeta, "ImagenTemporal.png");
        try {
            ImageIO.write(imagen, "png", archivoImagen); // Guardar la imagen como PNG
            System.out.println("Imagen guardada en: " + archivoImagen.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error al guardar la imagen: " + ex.getMessage());
        }
    }

    public void cargarImagenTemporal() {
        File archivoImagen = new File(CARPETA_GUARDADO, "ImagenTemporal.png");
        try {
            cargadaImage = ImageIO.read(archivoImagen); // Cargar la imagen desde el archivo
          
        } catch (IOException ex) {
            System.err.println("Error al cargar la imagen: " + ex.getMessage());
        }
    }
    
    public void guardarDibujo() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("dibujoTempolar.dat"))) {
            salida.writeObject(listaFiguras);
            //   JOptionPane.showMessageDialog(this, "Dibujo guardado correctamente como dibujo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el dibujo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarDibujo() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("dibujoTempolar.dat"))) {
            listaFiguras = (ArrayList<Figura>) entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el dibujo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gDibujo = (Graphics2D) g;
        super.paintComponent(gDibujo);

        gDibujo.drawImage(cargadaImage, 0, 0, null);
        for (Figura figuraActual : listaFiguras) {
            figuraActual.dibujar(gDibujo);
        }
    }
}

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

import entidades.TrazoDTO;

public class Dibujo {

    private Nodo cabeza;

    public Dibujo() {
        cabeza = null;
    }

    public void agregar(Nodo nodo) {
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            // recorrer la lista
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nodo;
        }
        nodo.siguiente = null;
    }

    public int getLongitud() {
        int totalNodos = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            totalNodos++;
            actual = actual.siguiente;
        }
        return totalNodos;
    }

    public void dibujar(JPanel pnl) {
        limpiarPanel(pnl);
        // Obtener el objeto graficador
        Graphics g = pnl.getGraphics();
        // recorrer la lista
        Nodo actual = cabeza;
        while (actual != null) {
            actual.getTrazo().dibujar(g, actual.getColor());
            actual = actual.siguiente;
        }
    }

    public boolean guardarJSON(String nombreArchivo){
        TrazoDTO[] trazos = new TrazoDTO[getLongitud()];
        // Recorer lista
        Nodo actual = cabeza;
        int fila = 0;
        while (actual != null) {
            trazos [fila]= actual.toDTO();
            actual = actual.siguiente;
        }
        return Archivo.guardarJson(nombreArchivo, trazos);
    }

    // ********** Metodos Estaticos **********

    public static void limpiarPanel(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, pnl.getWidth(), pnl.getHeight());
    }

}

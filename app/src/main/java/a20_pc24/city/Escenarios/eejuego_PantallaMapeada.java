package a20_pc24.city.Escenarios;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

import a20_pc24.city.sprites.S_Sprite;

public abstract class eejuego_PantallaMapeada {
//Clase que únicamente define el comportamiento general de las clases de los mapas

    private ArrayList<S_Sprite> elementosMapa;
    //Elementos mapa es el array que contiene todos los tiles que habrá en el mapa
    public Bitmap mapaMontado;

    /**
     * Asigna una colección de elementos al atributo elementosMapa
     *
     * @param elementosMapa
     */

    public void setElementosMapa(ArrayList<S_Sprite> elementosMapa) {
        this.elementosMapa = elementosMapa;
    }

    /**
     * Añade un elemento a la colección de elementos del mapa
     *
     * @param elementosMapa
     */

    public void setElementoMapa(S_Sprite elementosMapa) {
        this.elementosMapa.add(elementosMapa);
    }

    /**
     * Devuelve la colección de objetos usados en este mapa
     *
     * @return
     */

    public ArrayList<S_Sprite> getElementosMapa() {
        return elementosMapa;
    }

    /**
     *      Aquí se creará el Bitmap del mapa a partir del array bidimensional correspondiente.
     *      El contenido serán los elementos de @param elementosMapa
     *      el array determinará las posiciones mediante sus índices [0,0],[0,1], etc.
     *      el contenido de las variables del array serán los tiles
     *      los tiles contienen su tamaño, calculado en relación al alto del móvil apaisado
     *
     *      source: http://www.androidsnippets.com/combine-multiple-bitmap-into-one.html
     *
     * @return this.mapaMontado
     */
    public Bitmap unificaMapa(){
        return this.mapaMontado;
    }

    /**
     *      Pintará el bitmap @mapaMontado tomando como referencia la posición
     */

    abstract void dibujaMapa();

    abstract void actualizarMapa();
}
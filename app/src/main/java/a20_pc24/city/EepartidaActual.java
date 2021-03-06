package a20_pc24.city;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import a20_pc24.city.escenarios.eejuegoCallePrincipal;
import a20_pc24.city.escenarios.eejuegoEdificioPB;
import a20_pc24.city.escenarios.eejuego_PantallaMapeada;
import a20_pc24.city.sprites.SpPersonajePrincipal;

//Clase destinada a cargar la partida guardada
//

public class EepartidaActual extends EE_EsquemaEscena{

    public int escenarioID;
    public int idPartida;

    //Sprite
    public SpPersonajePrincipal spPersonajePrincipal;
    public SpPersonajePrincipal[][] spPersonajePrincipalAnda =
            _CharacterMainSprites.spPersonajePrincipalAnda;

    //Mapa
    public eejuego_PantallaMapeada[] escenariosPosibles =
            new eejuego_PantallaMapeada[]{new eejuegoCallePrincipal(), new eejuegoEdificioPB()};
    public eejuego_PantallaMapeada escenarioActual;



    /**
     * Extiende del esquema general de escenas.
     * Carga la partida guardada en el slot correspondiente.
     *
     * @param cntx recursos y datos de la aplicación
     * @param escenarioID identificador correspondiente al escenario
     * @param idPartida corresponde a la partida que estamos seleccionando. TODO REAL IMPLEMENTATION
     *
     *
     * @param anchoPantalla prescindible en un futuro próximo
     * @param altoPantalla prescindible en un futuro próximo
     */

    // El último parámetro corresponde a la id del escenario
    // Los probamos a machete, ya que posteriormente, cuando funcione el método
    // cargar partida, será allí donde se especificará el escenario.
    // No en este contructor

    public EepartidaActual(Context cntx, int idPartida, int anchoPantalla, int altoPantalla, int escenarioID) {
        super(cntx, escenarioID, anchoPantalla, altoPantalla);
        this.idPartida = idPartida;
        this.escenarioID = escenarioID;

        //Buscamos en qué escenario nos encontramos

        for(int i = 0; i< escenariosPosibles.length; i++){
            if(this.escenariosPosibles[i].getEscenarioID()==this.escenarioID){
                this.escenarioActual = this.escenariosPosibles[i];
            }else{
                this.escenarioActual = new eejuegoCallePrincipal();
            }
        }

        //Inicializamos el sprite del personaje principal
        this.spPersonajePrincipal = this.spPersonajePrincipalAnda[0][0];

        //Redefinimos el varlor del botón atrás

        this.btnAtras.setBtnValue(96);      //Necesario para volver a la selección de partida.
                                            // En un futuro dará acceso al menú in-Game

        // TODO Cargar partida
        // this.cargarPartida(idPartida);
    }

    /**
     * Dibuja el mapa indicado por escenarioID
     * @param c
     */

    public void dibujar(Canvas c) {
//        try {
            this.escenarioActual.dibujaMapa(c);               //Dibujamos el escenario actual

//            this.spPersonajePrincipal.spriteDibujar(c,
//                    this.escenarioActual.escalaEscenarioPersonajePrincipal);

//        } catch (Exception e) {
//            Log.i("Error al dibujar", e.getLocalizedMessage());
//        }
            this.btnAtras.dibujaBoton(c);
    }

    /**
     *
     */
    public void actualizarFisica() {

    }

    /**
     *
     * @param event
     * @return
     */
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca

                break;
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;
            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
                    if(this.btnAtras.pulsaBoton(event)){
                        return this.btnAtras.getBtnValue();
                    }
                break;
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
                break;
        }
        return getIdEscena();
    }

    /**
     * TODO Cargar partida
     */
//    public void cargarPartida(int idPartida){
//
//    }
}
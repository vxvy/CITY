package a20_pc24.city.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import a20_pc24.city._DimensionesDispositivo;
import a20_pc24.city._Utiles;
import a20_pc24.city.escenarios.eejuego_PantallaMapeada;

public class SpPersonajePrincipal extends S_Sprite{
        //el sprite en un principio siempre aparecerá enmedio de la pantalla

    public PointF pIni, pFin;
    public RectF spriteColRect;
    public enum Direccion{
        ABAJO(1),ARRIBA(2),DERECHA(3),IZQUIERDA(4);

        private int nDireccion;

        Direccion(int nDireccion){
            this.nDireccion = nDireccion;
        }
        public int getNDireccion(){
            return this.nDireccion;
        }
    }
    public Direccion d;
    public eejuego_PantallaMapeada mapa;

    public SpPersonajePrincipal(Bitmap spriteBm){
        super(spriteBm);
    }

    /**
     * En el caso de que se quiera cambiar la posición del sprite desde el inicio de la escena
     * @param spriteBm
     * @param spritePosX
     * @param spritePosY
     */
    public SpPersonajePrincipal(Bitmap spriteBm, float spritePosX, float spritePosY){
        super(spriteBm, spritePosX, spritePosY);
    }

    /*****************************************************************/
    /*****************************************************************/
    /*****************************************************************/



    /**
     * Cambio de "posición"
     */
    @Override
    public void spriteMovimiento(){
        super.spriteMovimiento();
    }

    /**
     * Sucesión de sprites que conforman la animación
     */
    @Override
    public void spriteAnimacion(){

    }
    /**
     * Dibuja sprite en coordenadas dadas
     */
    @Override
    public void spriteDibujar(Canvas c){
        super.spriteDibujar(c);
    }

    /**
     * Dibuja sprite en coordenadas dadas
     */
    @Override
    public void spriteDibujar(Canvas c, float escala){
        this.setSpritePosX(_Utiles.posicionaSpriteMedioX(this, escala));
        this.setSpritePosY(_Utiles.posicionaSpriteMedioY(this, escala));
        super.spriteDibujar(c, escala);
    }

    /**
     * Dibuja sprite en coordenadas dadas
     */
    @Override
    public void spriteDibujar(Canvas c, float coordX, float coordY, float escala){
        this.setSpritePosX(coordX);
        this.setSpritePosY(coordY);
        super.spriteDibujar(c, escala);
    }
    /*****************************************************************/
    /*****************************************************************/
    /*****************************************************************/

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
                pIni = new PointF(event.getX(),event.getY());
                pFin = new PointF(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;
            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
                break;
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                pFin = new PointF(event.getX(),event.getY());

                float npX, npY;
                if(pFin.x>pIni.x){  //Movimiento Derecha
                    npX =pFin.x;

                }else{              //Movimmiento Izquierda
                    npX =pIni.x;

                }
                if(pFin.y>pIni.y){  //Movimiento arriba
                    npY = pFin.y;

                }else{              //Movimiento abajo
                    npY = pIni.y;

                }

                //El movimiento no tiene diagonales diagonales
                //El pj se mueve en la dirección correspondiente al mayor desplazamiento

                if(npX>npY){        //Desplazamiento horizontal
                    this.spriteMovimiento();
                }else{              //Desplazamiento vertical
                    this.spriteMovimiento();
                }

                pIni = new PointF(event.getX(),event.getY());
                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
                break;
        }
        return 0;
    }
}

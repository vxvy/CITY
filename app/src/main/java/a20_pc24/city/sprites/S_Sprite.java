package a20_pc24.city.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import a20_pc24.city._DimensionesDispositivo;
import a20_pc24.city._Utiles;

// Los tiles y sprites son definidos con las clases que heredan de S_SPRITE
    // y construídos en las clases _Tiles y _CharacterMainSprites,
    // menos los correspondientes al personaje principal que están en SpPersonajePrincipal

public abstract class S_Sprite{

    private PointF spritePos;                       //Posición en que se coloca el sprite
    private float spritePosX, spritePosY;           //Coordenadas que componen spritePos

    private Bitmap spriteBm;                    //Imagen del sprite
    private RectF spriteColisionRect;           //Cuadrado creado a partir del sprite que determina sus límites
    private Paint spritePaint;
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
    Direccion d;
    private int spriteDPsX, spriteDPsY;

    /**
     * Constructor para instanciar el sprite sin darle más propiedades,
     * con el fín de poder dárselas de forma dinámica
     * @param spriteBm imagen que integra el sprite
     */
    public S_Sprite(Bitmap spriteBm){
        this.spriteBm = spriteBm;
        this.spriteDPsX = getSpriteDPsX();
        this.spriteDPsY = getSpriteDPsY();
    }

    public int getSpriteDPsX(){
        return _DimensionesDispositivo.getDpAlto(this.getWidth());
    }

    public int getSpriteDPsY(){
        return _DimensionesDispositivo.getDpAlto(this.getHeight());
    }

    /**
     * A pesar de ser una clase abstracta, declaro el constructor para que sea la base de los hijos
     *
     * @param spriteBm bitmap correspondiente al sprite
     * @param spritePosX posición X donde posicionaremos y dibujaremos
     * @param spritePosY posición Y donde posicionaremos y dibujaremos
     */
    public S_Sprite(Bitmap spriteBm, float spritePosX, float spritePosY){
        this(spriteBm);
        this.spritePosX = spritePosX;
        this.spritePosY = spritePosY;
        this.spritePos = new PointF(spritePosX,spritePosY);
        this.setSpriteColisionRect(new RectF(spritePosX,spritePosY,spriteBm.getWidth(),spriteBm.getHeight()));
        this.spritePaint = new Paint();
    }


    /*****************************************************************/
    /*****************************************************************/
    /*****************************************************************/

    /**
     * Método destinado a definir el cuadro de coolisiones
     */
    public void spriteCuadroColision(){

    }

    /**
     * Cambio de posición en el mapa
     */
    public void spriteMovimiento(){
        this.setSpriteColisionRect(new RectF(this.getSpritePosX(),this.getSpritePosY(),this.getWidth(),this.getHeight()));
    }

    /**
     * Sucesión de sprites que conforman la animación
     */
    public void spriteAnimacion(){

    }

    /**
     *
     * @param c
     */
    public void spriteDibujar(Canvas c){
        Bitmap sprite = Bitmap.createScaledBitmap(this.getspriteBm()
                , this.spriteDPsX
                , this.spriteDPsY
                , false);
        c.drawBitmap(sprite,this.getSpritePosX(),this.getSpritePosY(),this.getSpritePaint());
    }

    /**
     *
     * @param c
     * @param aumento
     */

    public void spriteDibujar(Canvas c, float aumento){
        this.spriteDPsX*=aumento;
        this.spriteDPsY*=aumento;
        Bitmap sprite = Bitmap.createScaledBitmap(this.getspriteBm()
                , (int)(this.getSpriteDPsX()*aumento)
                , (int)(this.getSpriteDPsY()*aumento)
                , false);
        c.drawBitmap(sprite,this.getSpritePosX(),this.getSpritePosY(),this.getSpritePaint());
    }

    /**
     *
     * @param c
     * @param coordX
     * @param coordY
     * @param aumento
     */
    public void spriteDibujar(Canvas c, float coordX, float coordY, float aumento){

        Bitmap sprite = Bitmap.createScaledBitmap(
                this.getspriteBm()
                , (int)(this.getSpriteDPsX()*aumento)
                , (int)(this.getSpriteDPsY()*aumento)
                , false);
        c.drawBitmap(sprite,coordX,coordY,this.getSpritePaint());
    }
    /*****************************************************************/
    /*****************************************************************/
    /*****************************************************************/

    /**
     * @return obtiene el Bitmap del sprite
     */
    public Bitmap getspriteBm() {
        return spriteBm;
    }

    /**
     * Cambia a machete el Bitmap de un sprite
     * @param spriteBm nuevo Bitmap para el sprite
     */
    public void setspriteBm(Bitmap spriteBm) {
        this.spriteBm = spriteBm;
    }

    /**
     * @return el ancho del sprite
     */
    public float getWidth(){
        return this.spriteBm.getWidth();
    }

    /**
     * @return el alto del sprite
     */
    public float getHeight(){
        return this.spriteBm.getHeight();
    }

    /**
     * @return devuelve la posición donde se encuentra el sprite
     */
    public PointF getSpritePos() {
        return spritePos;
    }

    /**
     * Indica una nueva posición para el sprite
     * @param spritePos nueva posición
     */
    public void setSpritePos(PointF spritePos) {
        this.spritePos = spritePos;
    }

    /**
     * @return devuelve la posición X del sprite
     */
    public float getSpritePosX() {
        return spritePosX;
    }

    /**
     * Indica nuevo valor para X (horizontal)
     * @param spritePosX nueva posición X (horizontal)
     */
    public void setSpritePosX(float spritePosX) {
        this.spritePosX = spritePosX;
    }

    /**
     * @return devuelve la posición Y del sprite
     */
    public float getSpritePosY() {
        return spritePosY;
    }

    /**
     * Indica nuevo valor para Y (vertical)
     * @param spritePosY nueva posición Y (vertical)
     */
    public void setSpritePosY(float spritePosY) {
        this.spritePosY = spritePosY;
    }

    /***********************************************************************
     * Métodos destinados a definir y devolver el cuadro de coolisiones
     * Usamos la posición a partir de la que se ha dibujado el sprite,
     * su altura y su ancho.
     */

    public RectF getSpriteColisionRect() {
        return spriteColisionRect;
    }

    /**
     * Determina un nuevo rectángulo de colisiones
     * @param spriteColisionRect
     */
    public void setSpriteColisionRect(RectF spriteColisionRect) {
        this.spriteColisionRect = spriteColisionRect;
    }

    /************************************************************************/

    /**
     * @return el paint de este sprite
     */
    public Paint getSpritePaint() {
        return spritePaint;
    }

}

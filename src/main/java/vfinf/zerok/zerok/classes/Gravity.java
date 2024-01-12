/*package vfinf.zerok.zerok.classes;

public class Gravity {
    private static final double EARTH_GRAVITY = 6.67430e-11;
    private static final double MOON_GRAVITY = 1.6; //se vogliamo mantenere questa bisogna controllare che sia effettivamente corretta

    private MapObject planet; // pianeta fermo

    //F=(G*m1*m2)/r^2  -->  F= forza gravitazionale, G= costante gravitazionale, m1, m2 masse dei punti, r= distanza tra i due punti

    public Gravity(MapObject planet) {
        this.planet= planet;
    }

    public double calcoloGravita(MapObject object){//qua servirebbero le coordinate dell'oggetto in movimento, non so ancora se scriverli così è meglio
       double deltaX=planet.getCoords().getX() - object.getCoords().getX(); //distanza orizzontale punti
       double deltaY=planet.getCoords().getY() - object.getCoords().getY(); //distanza verticale punti

       double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); //teorema di pitagora calcolo ipotenusa

       if(planet.getName().equals("earth"){
           return (EARTH_GRAVITY * planet.getMass()*object.getMass())/distance;
       }
       return (MOON_GRAVITY) * planet.getMass() * object.getMass())/distance;
   }

   public void updateRocketPosition (MapObject rocket){
       rocket.setVelocity(rocket.getVelocity() + (calcoloGravita(rocket)/rocket.getMass());
       rocket.setCoords(rocket.getCoords() + rocket.getVelocity();
   }
}*/
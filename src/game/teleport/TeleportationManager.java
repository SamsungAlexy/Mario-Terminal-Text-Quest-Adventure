package game.teleport;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;

public class TeleportationManager {
    private static TeleportationManager instance = new TeleportationManager();

    //  Amount in wallet
    private int currentMap;
    private GameMap mapOne;
    private GameMap mapTwo;
    private Location mapOneWarpLocation;
    private Location mapTwoWarpLocation;
    private World world;


//  Wallet Manager constructor, initialise wallet amount to 0

    private TeleportationManager(){
        this.currentMap=1;

    }



    public static TeleportationManager getInstance(){
        return instance;
    }

//    setting maps
    public void setMapOne(GameMap mapOne) {
        this.mapOne = mapOne;
    }

    public void setMapTwo(GameMap mapTwo) {
        this.mapTwo = mapTwo;
    }

//    switch map
    public void switchMap(Actor actor){
        if (this.currentMap==1){

//          remove player from map 1
            this.mapOne.removeActor(actor);

//          add player to map 2
            if(mapTwoWarpLocation.containsAnActor())
                mapTwo.removeActor(mapTwoWarpLocation.getActor());

            this.world.addPlayer(actor,mapTwoWarpLocation);

            this.currentMap = 2;

        }else{

//          remove player from map 2
            this.mapTwo.removeActor(actor);
//
//          add player to map 1
            if(mapOneWarpLocation.containsAnActor())
                mapOne.removeActor(mapOneWarpLocation.getActor());

            this.world.addPlayer(actor,mapOneWarpLocation);

            this.currentMap=1;
        }
    }


//    saving warp location for later tp
    public void setWarpLocationOnTeleport(Location warpLocation){
        if (this.currentMap==1){
            this.setMapOneWarpLocation(warpLocation);
        }else{
            this.setMapTwoWarpLocation(warpLocation);

        }

    }

    public void setMapOneWarpLocation(Location mapOneWarpLocation) {
        this.mapOneWarpLocation = mapOneWarpLocation;
    }

    public void setMapTwoWarpLocation(Location mapTwoWarpLocation) {
        this.mapTwoWarpLocation = mapTwoWarpLocation;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public int getNextMap() {
        return currentMap ==1? 2: 1;
    }


//    world setter getter
    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}

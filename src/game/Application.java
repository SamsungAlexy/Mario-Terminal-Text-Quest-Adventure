package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.enemy.Bowser;
import game.enemy.FlyingKoopa;
import game.enemy.Koopa;
import game.ground.*;
import game.magical.PowerStar;
import game.magical.SuperMushroom;
import game.teleport.TeleportationManager;
import game.teleport.WarpPipe;
import game.trading.Toad;
import game.tree.Mature;
import game.tree.Sapling;
import game.tree.Sprout;
import game.water.HealthFountain;
import game.water.PowerFountain;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(),new WarpPipe(), new HealthFountain(), new PowerFountain());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				"..........................................HHAA...##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);

			List<String> mapTwo = Arrays.asList(
					"..............................##............................",
					"................................#...........................",
					"................................#...........................",
					"......................................#.....................",
					".....................................##.....................",
					".....................................#......................",
					"..............................____...#......................",
					"..............................____...LL.....................",
					".............................._____..#......................",
					".............................._____..#......................",
					".........................................#..................",
					"..........................................#.................",
					"...........................................LL...............");

			FancyGroundFactory groundFactoryTwo = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Lava(), new WarpPipe());

			GameMap gameMapTwo = new GameMap(groundFactoryTwo, mapTwo);


//			add gamemaps to world
			world.addGameMap(gameMap);
			world.addGameMap(gameMapTwo);


			Actor mario = new Player("Player", 'm', 100,gameMap,gameMapTwo);
			world.addPlayer(mario, gameMap.at(42, 10));
			mario.addItemToInventory(new PowerStar());
			mario.addItemToInventory(new PowerStar());
			mario.addItemToInventory(new PowerStar());
			mario.addItemToInventory(new PowerStar());
//			mario.addItemToInventory(new Wrench());

			gameMapTwo.addActor(new PrincessPeach(), gameMapTwo.at(0,12));
			gameMap.addActor(new Koopa(), gameMap.at(31,10));
			gameMapTwo.addActor(new Koopa(), gameMapTwo.at(31,10));
			gameMapTwo.addActor(new FlyingKoopa(), gameMapTwo.at(26,10));
			gameMapTwo.addActor(new Bowser(), gameMapTwo.at(1,12));
			gameMap.addActor(new FlyingKoopa(), gameMap.at(38,8));


			TeleportationManager.getInstance().setWorld(world);
			TeleportationManager.getInstance().setMapOne(gameMap);
			TeleportationManager.getInstance().setMapTwo(gameMapTwo);

//			gameMap.at(35, 10).addActor(new Goomba());


			gameMap.at(42, 13).addItem(new PowerStar());
//			gameMap.at(43, 13).addItem(new PowerStar());
			gameMap.at(43, 13).addItem(new SuperMushroom());
			gameMap.at(44, 13).addItem(new Wrench());
			gameMap.at(43, 11).addActor(new Toad());







//			set initial warp location and add warp location to maps
			Location warpLocationOne = gameMap.at(42,8);
			Location warpLocationTwo = gameMapTwo.at(0,0);

			warpLocationOne.setGround(new WarpPipe());
			warpLocationTwo.setGround(new WarpPipe());


//			set initial warp location
			TeleportationManager.getInstance().setMapOneWarpLocation(warpLocationOne);
			TeleportationManager.getInstance().setMapTwoWarpLocation(warpLocationTwo);

//			extras warp locations
			gameMap.at(44,8).setGround(new WarpPipe());





//			X- 0 to 79 MAP 1
//			Y - 0 to 18 MAP 1
//			add 15 random sprouts around the map on fertile ground
			int noOfRandomSprouts =15;
			Random random = new Random();
			while (noOfRandomSprouts>0){
				int randomX = random.nextInt(79);
				int randomY = random.nextInt(18);
				if(gameMap.at(randomX,randomY).getGround().hasCapability(Status.FERTILE)){
					gameMap.at(randomX,randomY).setGround(new Sprout());
					noOfRandomSprouts-=1;
				}
			}

//			X- 0 to 79 MAP 1
//			Y - 0 to 18 MAP 1
	//		add 15 random warp around the map on dirt
			int noOfRandomWarp =15;
			Random randomWarp = new Random();
			while (noOfRandomWarp>0){
				int randomX = randomWarp.nextInt(79);
				int randomY = randomWarp.nextInt(18);
				if(gameMap.at(randomX,randomY).getGround().hasCapability(Status.FERTILE)){
					gameMap.at(randomX,randomY).setGround(new WarpPipe());
					noOfRandomWarp-=1;
				}
			}


//			X - 0 to 59 MAP 2
//			Y - 0 to 12 MAP 2
//			add 40 Lava randomly around the map on dirt
			int noOfRandomLava =40;
				Random randomLava = new Random();
				while (noOfRandomLava>0){
					int randomX = randomLava.nextInt(59);
					int randomY = randomLava.nextInt(12);
					if(gameMapTwo.at(randomX,randomY).getGround().hasCapability(Status.FERTILE)){
						gameMapTwo.at(randomX,randomY).setGround(new Lava());
						noOfRandomLava-=1;
					}
				}


			world.run();

	}
}

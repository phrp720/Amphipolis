import boardgame.amphipolis.Controller.Controller;
import boardgame.amphipolis.Enums.Colors.AmphorasColors;
import boardgame.amphipolis.Enums.Colors.MosaicColor;
import boardgame.amphipolis.Enums.Skeleton_Details.*;
import boardgame.amphipolis.Enums.StatuePeak;
import boardgame.amphipolis.Model.tiles.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
class AmphipolisTest {
    protected static Controller tester;
    @BeforeAll
    static void setUp(){
        tester=new Controller();
    }
    @Test
    @Order(1)
    void playertester(){
        //Initialize tester
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(0).getPlayer_name(),"PLAYER 1");
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(1).getPlayer_name(),"PLAYER 2");
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(2).getPlayer_name(),"PLAYER 3");
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(3).getPlayer_name(),"PLAYER 4");
        tester.getCreatedboard().current_players.get(0).setPlayer_name("Giorgis");
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(0).getPlayer_name(),"Giorgis");
        Assertions.assertEquals(tester.getCreatedboard().current_players.size(),4);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(0).player_Heroes.size(),4);
    }
    @Test
    @Order(2)
    void statue_pos_Tester(){
        tester.getCreatedboard().current_players.get(0).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(0).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(0).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(1).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(1).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(2).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(3).player_items.add(new CaryatidTile());
        tester.pos_order_statues(tester.getCreatedboard().current_players);
        Assertions.assertEquals(tester.getPeak1(0), StatuePeak.bigger);
        Assertions.assertEquals(tester.getPeak1(1), StatuePeak.medium);
        Assertions.assertEquals(tester.getPeak1(2), StatuePeak.smaller);
        Assertions.assertEquals(tester.getPeak1(3), StatuePeak.smaller);
    }
    @Test
    @Order(3)
    void Id_tester(){
        Assertions.assertEquals(tester.getCreatedboard().getPlayer_id(),0);
        tester.getCreatedboard().next_player_id();
        Assertions.assertEquals(tester.getCreatedboard().getPlayer_id(),1);
        tester.getCreatedboard().next_player_id();
        Assertions.assertEquals(tester.getCreatedboard().getPlayer_id(),2);
        tester.getCreatedboard().next_player_id();
        Assertions.assertEquals(tester.getCreatedboard().getPlayer_id(),3);
        tester.getCreatedboard().next_player_id();
        Assertions.assertEquals(tester.getCreatedboard().getPlayer_id(),0);
    }
    @Test
    @Order(4)
    void bagTester(){
        Assertions.assertEquals(tester.getCreatedboard().getbag().tilesinbag.size(),134);
    }

    @Test
    @Order(5)
    void StartofGameTester(){
        tester.getCreatedboard().getAmphoras().add(new AmphoraTile(AmphorasColors.blue));
        tester.getCreatedboard().getMosaic().add(new MosaicTile(MosaicColor.green));
        tester.getCreatedboard().getSkeletons().add(new SkeletonTile(Skeleton_size.big, Skeleton_Side.bottomside));
        Assertions.assertEquals(tester.getCreatedboard().StartofgameCheck( tester.getCreatedboard().getSkeletons(),tester.getCreatedboard().getAmphoras(),tester.getCreatedboard().getStatues(),tester.getCreatedboard().getMosaic()),false);
        tester.getCreatedboard().getStatues().add(new SphinxTile());
        Assertions.assertEquals(tester.getCreatedboard().StartofgameCheck( tester.getCreatedboard().getSkeletons(),tester.getCreatedboard().getAmphoras(),tester.getCreatedboard().getStatues(),tester.getCreatedboard().getMosaic()),true);
        tester.getCreatedboard().getStatues().add(new CaryatidTile());
        Assertions.assertEquals(tester.getCreatedboard().StartofgameCheck( tester.getCreatedboard().getSkeletons(),tester.getCreatedboard().getAmphoras(),tester.getCreatedboard().getStatues(),tester.getCreatedboard().getMosaic()),false);

    }
    @Test
    @Order(6)
    void EndofGameTester(){
        tester.getCreatedboard().getLandslide().add(new Landslide());
      Assertions.assertEquals(tester.getCreatedboard().EndofgameCheck(tester.getCreatedboard().getLandslide()),false);
      for(int i=0;i<15;i++){
          tester.getCreatedboard().getLandslide().add(new Landslide());
      }
        Assertions.assertEquals(tester.getCreatedboard().EndofgameCheck(tester.getCreatedboard().getLandslide()),true);

    }
    @Test
    @Order(7)
    void CharacterTester(){
        tester.getCreatedboard().current_players.get(0).player_Heroes.get(0).setUsed_character(false);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(0).player_Heroes.get(0).getUsed_character(),false);
    }
    @Test
    @Order(8)
    void DrawTileTester(){
        tester.getCreatedboard().getbag().draw_plaque(15, tester.getCreatedboard().getbag().tilesinbag);
        Assertions.assertEquals(tester.getCreatedboard().getbag().tilesinbag.size(),134);
    }
    @Test
    @Order(9)
    void PointsTester(){
        tester.getCreatedboard().current_players.get(0).player_items.add(new AmphoraTile(AmphorasColors.purple));
        tester.getCreatedboard().current_players.get(0).player_items.add(new AmphoraTile(AmphorasColors.brown));
        tester.getCreatedboard().current_players.get(0).player_items.add(new AmphoraTile(AmphorasColors.blue));
        tester.getCreatedboard().current_players.get(0).player_items.add(new AmphoraTile(AmphorasColors.green));
        tester.getCreatedboard().current_players.get(0).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.upperside));
        tester.getCreatedboard().current_players.get(0).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.bottomside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.purple));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.brown));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.blue));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.green));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.red));
        tester.getCreatedboard().current_players.get(1).player_items.add(new AmphoraTile(AmphorasColors.yellow));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.upperside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.bottomside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.upperside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.bottomside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.upperside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.big,Skeleton_Side.bottomside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.small,Skeleton_Side.upperside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new SkeletonTile(Skeleton_size.small,Skeleton_Side.bottomside));
        tester.getCreatedboard().current_players.get(1).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(1).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(1).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(2).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(2).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(2).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(2).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(2).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(2).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(3).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(3).player_items.add(new MosaicTile(MosaicColor.red));
        tester.getCreatedboard().current_players.get(3).player_items.add(new MosaicTile(MosaicColor.green));
        tester.getCreatedboard().current_players.get(3).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(3).player_items.add(new MosaicTile(MosaicColor.yellow));
        tester.getCreatedboard().current_players.get(3).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(3).player_items.add(new CaryatidTile());
        tester.getCreatedboard().current_players.get(3).player_items.add(new SphinxTile());
        tester.getCreatedboard().current_players.get(3).player_items.add(new SphinxTile());
        tester.pos_order_statues(tester.getCreatedboard().current_players);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(0).pointscalculator(tester.getPeak1(0),tester.getPeak2(0)),3);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(1).pointscalculator(tester.getPeak1(1),tester.getPeak2(1)),19);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(2).pointscalculator(tester.getPeak1(2),tester.getPeak2(2)),4);
        Assertions.assertEquals(tester.getCreatedboard().current_players.get(3).pointscalculator(tester.getPeak1(3),tester.getPeak2(3)),8);

    }






}
package core.interfaces;

import common.ConstantMessages;
import models.guns.Gun;
import models.guns.Pistol;
import models.guns.Rifle;
import models.neighbourhood.GangNeighbourhood;
import models.neighbourhood.Neighbourhood;
import models.players.CivilPlayer;
import models.players.MainPlayer;
import models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller{

    Player mainPlayer;
    List<Player> players;
    List<Gun> guns;
    Neighbourhood neighbourhood;

    public ControllerImpl() {
        players = new ArrayList<>();
        guns = new ArrayList<>();
        mainPlayer = new MainPlayer();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        players.add(player);

        return String.format(String.format(ConstantMessages.PLAYER_ADDED, name));
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        if(type.equals("Pistol")){
            gun = new Pistol(name);
        }else if(type.equals("Rifle")){
            gun = new Rifle(name);
        }else {
            return ConstantMessages.GUN_TYPE_INVALID;
        }
        guns.add(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {

        if(guns.size() == 0) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        if(name.equals("Vercetti")){
            mainPlayer.getGunRepository().add(guns.get(guns.size()-1));
            String nameGun = guns.get(guns.size()-1).toString();
            guns.remove(guns.size()-1);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, nameGun);
        }

        for (Player player : players) {
            if(!player.getName().equals(name)){
                return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            }else {
                player.getGunRepository().add(guns.get(guns.size()-1));
                guns.remove(guns.size()-1);
            }
        }
        String nameGun = guns.get(guns.size()-1).toString();
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, nameGun, name);
    }

    @Override
    public String fight() {
        int size = players.size();
        boolean playersBool = true;
        for (Player player : players) {
            if(player.getLifePoints() < 50){
                playersBool = false;
                break;
            }
            if(players.size() == size){
                playersBool = false;
            }
        }
        if(mainPlayer.getLifePoints() < 100){
            playersBool = false;
        }

        neighbourhood.action(mainPlayer, players);
        if(playersBool){
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }else {
            StringBuilder sb = new StringBuilder();

            sb.append(ConstantMessages.FIGHT_HAPPENED).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
            .append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, size-=this.players.size()))
                    .append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.players.size()))
                    .append(System.lineSeparator());
        }
        return null;
    }
}

package models.neighbourhood;

import models.guns.Gun;
import models.players.Player;
import repositories.GunRepository;
import repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood {



    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        if (!mainPlayer.isAlive()) {

            Gun gun = takeGun(mainPlayer);
            Player player = civilians(civilPlayers);

            while (true) {

                player.takeLifePoints(gun.fire());
                if (!gun.canFire()) {
                    gun = takeGun(mainPlayer);
                }
                if (!player.isAlive()) {
                    player = civilians(civilPlayers);
                }

                if(player.isAlive()){
                    Gun civilGun = civilianGuns(player);
                    mainPlayer.takeLifePoints(civilGun.fire());
                    if (!civilGun.canFire()) {
                        civilGun = civilianGuns(player);
                    }
                    if(!mainPlayer.isAlive()){
                        return;
                    }

                }

                if (mainPlayer.getGunRepository().getModels().size() == 0 ||
                        civilPlayers.size() == 0) {
                    break;
                }



            }


        }


    }

    private Gun civilianGuns(Player player){
        Gun gun = null;
        for(Gun model : player.getGunRepository().getModels()){
            gun = model;
            break;
        }
        player.getGunRepository().remove(gun);
        return gun;
    }


    private Gun takeGun(Player mainPlayer) {
        Gun gun = null;
        for (Gun model : mainPlayer.getGunRepository().getModels()) {
            gun = model;
            break;
        }
        mainPlayer.getGunRepository().remove(gun);
        return gun;
    }

    private Player civilians(Collection<Player> civilPlayers) {
        Player player = null;
        for (Player civilPlayer : civilPlayers) {
            player = civilPlayer;
            break;
        }
        civilPlayers.remove(player);
        return player;
    }
}

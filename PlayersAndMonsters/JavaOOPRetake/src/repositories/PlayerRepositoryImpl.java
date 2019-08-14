package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerRepositoryImpl implements PlayerRepository {

    private Map<String, Player> players;

    public PlayerRepositoryImpl() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.players.values().size();
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players.values().stream().collect(Collectors.toList()));
    }

    @Override
    public void add(Player player) {
        if(player == null){
            throw new IllegalArgumentException("Player cannot be null!");
        }

        if(this.players.containsKey(player.getUsername())){
            throw new IllegalArgumentException(String.format("Player %s already exists!", player.getUsername()));
        }

        this.players.put(player.getUsername(), player);
    }

    @Override
    public boolean remove(Player player) {
        if(player == null){
            throw new IllegalArgumentException("Player cannot be null");
        }
        Player removedPlayer = this.players.remove(player.getUsername());
        boolean isRemoved = true;

        if(removedPlayer == null){
            isRemoved = false;
        }

        return isRemoved;
    }

    @Override
    public Player find(String name) {
        Player player = this.players.get(name);
        return player;
    }
}

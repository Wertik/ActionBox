package space.devport.wertik.actionbox.system.box.struct;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class Box {

    @Getter
    private final String name;

    @Getter
    @Setter
    private List<String> commands;

    public Box(String name) {
        this.name = name;
    }

    public void open() {
        commands.forEach(cmd -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd));
    }

    public void open(Player player) {
        commands.forEach(cmd -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", player.getName())));
    }
}
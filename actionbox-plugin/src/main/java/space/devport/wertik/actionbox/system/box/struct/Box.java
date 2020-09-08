package space.devport.wertik.actionbox.system.box.struct;

import lombok.Getter;
import lombok.Setter;
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

    }

    public void open(Player player) {
    }
}
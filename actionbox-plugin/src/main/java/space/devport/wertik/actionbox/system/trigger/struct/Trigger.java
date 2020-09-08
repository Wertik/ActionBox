package space.devport.wertik.actionbox.system.trigger.struct;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Trigger {

    @Getter
    private final String name;
    @Getter
    private final String action;

    @Getter
    private final List<String> boxes = new ArrayList<>();

    public Trigger(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public void addBox(String name) {
        this.boxes.add(name);
    }
}
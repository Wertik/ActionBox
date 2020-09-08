package space.devport.wertik.actionbox.system.box;

import space.devport.utils.configuration.Configuration;
import space.devport.wertik.actionbox.ActionBoxPlugin;
import space.devport.wertik.actionbox.Manager;
import space.devport.wertik.actionbox.system.box.struct.Box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoxManager extends Manager {

    private final Map<String, Box> loadedBoxes = new HashMap<>();

    private final Configuration configuration;

    public BoxManager(ActionBoxPlugin plugin) {
        super(plugin);
        this.configuration = new Configuration(plugin, "boxes");
    }

    @Override
    public void enable() {
        load();
    }

    @Override
    public void reload() {

    }

    @Override
    public void disable() {
    }

    public Box getBox(String name) {
        return this.loadedBoxes.get(name);
    }

    public boolean isLoaded(String name) {
        return this.loadedBoxes.containsKey(name);
    }

    private void load() {
        configuration.load();

        for (String name : configuration.getFileConfiguration().getKeys(false)) {
            Box box = new Box(name);

            box.setCommands(configuration.getStringList(name + ".commands", new ArrayList<>()));
            consoleOutput.debug("Loaded box " + name);
        }
        consoleOutput.info("Loaded " + this.loadedBoxes.size() + " box(es)...");
    }

    public Map<String, Box> getLoadedBoxes() {
        return Collections.unmodifiableMap(loadedBoxes);
    }
}
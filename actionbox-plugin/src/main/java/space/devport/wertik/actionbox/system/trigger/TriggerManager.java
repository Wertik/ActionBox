package space.devport.wertik.actionbox.system.trigger;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import space.devport.utils.configuration.Configuration;
import space.devport.wertik.actionbox.ActionBoxPlugin;
import space.devport.wertik.actionbox.Manager;
import space.devport.wertik.actionbox.system.box.BoxManager;
import space.devport.wertik.actionbox.system.box.struct.Box;
import space.devport.wertik.actionbox.system.trigger.struct.Trigger;
import space.devport.wertik.actionbox.system.trigger.struct.TriggerContext;
import space.devport.wertik.actionbox.system.trigger.struct.TriggerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TriggerManager extends Manager {

    private final Map<String, TriggerListener<? extends Event>> registeredListeners = new HashMap<>();

    private final Map<String, Trigger> loadedTriggers = new HashMap<>();

    private final Configuration configuration;

    public TriggerManager(ActionBoxPlugin plugin) {
        super(plugin);
        this.configuration = new Configuration(plugin, "triggers");
    }

    @Override
    public void enable() {
        new TriggerListener<BlockBreakEvent>("block-break") {
            @Override
            public TriggerContext parseVariables(BlockBreakEvent event) {
                return new TriggerContext()
                        .fromBlock(event.getBlock())
                        .fromPlayer(event.getPlayer())
                        .fromItem(event.getPlayer().getInventory().getItemInMainHand(), "tool");
            }
        }.register();
        //TODO more...

        consoleOutput.info("Registered " + this.registeredListeners.size() + " listener(s)...");

        load();
    }

    @Override
    public void reload() {
        load();
    }

    @Override
    public void disable() {
        registeredListeners.values().forEach(HandlerList::unregisterAll);
    }

    private void load() {
        configuration.load();

        this.loadedTriggers.clear();

        for (String name : configuration.getFileConfiguration().getKeys(false)) {
            String action = configuration.getString("action");

            if (!isRegistered(action)) {
                consoleOutput.warn("Action " + action + " is not registered. Check your spelling.");
                break;
            }

            Trigger trigger = new Trigger(name, action);

            // Boxes
            for (String box : configuration.getStringList("execute", new ArrayList<>())) {
                if (!plugin.getManager(BoxManager.class).isLoaded(box)) {
                    consoleOutput.warn("Box " + box + " is not loaded. Check your spelling.");
                    continue;
                }
                trigger.addBox(box);
            }
            consoleOutput.debug("Loaded trigger " + name);
        }
        consoleOutput.info("Loaded " + this.loadedTriggers.size() + " trigger(s)...");
    }

    public Trigger getTrigger(String name) {
        return this.loadedTriggers.get(name);
    }

    public boolean isLoaded(String name) {
        return this.loadedTriggers.containsKey(name);
    }

    public boolean isRegistered(String name) {
        return this.registeredListeners.containsKey(name);
    }

    public void registerListener(TriggerListener<? extends Event> triggerListener) {
        plugin.registerListener(triggerListener);
        this.registeredListeners.put(triggerListener.getName(), triggerListener);
    }

    public void fire(String name, TriggerContext context) {
        consoleOutput.debug("Trigger " + name + " fired with context " + context.toString());

        if (!isLoaded(name)) return;

        Trigger trigger = getTrigger(name);

        // Open boxes
        for (String boxName : trigger.getBoxes()) {
            Box box = plugin.getManager(BoxManager.class).getBox(boxName);

            if (box == null) continue;

            if (context.getPlayer().isPresent())
                box.open(context.getPlayer().get());
            else box.open();
        }
    }
}
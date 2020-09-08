package space.devport.wertik.actionbox.system.trigger.struct;

import lombok.Getter;
import org.bukkit.event.Event;
import space.devport.wertik.actionbox.ActionBoxPlugin;
import space.devport.wertik.actionbox.system.trigger.TriggerManager;

public abstract class TriggerProcessor<T extends Event> {

    @Getter
    private final String name;

    public TriggerProcessor(String name) {
        this.name = name;
    }

    /**
     * Return true, if the trigger should be fired.
     */
    public boolean shouldFire(T event) {
        return true;
    }

    /**
     * Parse TriggerContext from event.
     */
    public abstract TriggerContext parseVariables(T event);

    public void onAction(T event) {
        if (shouldFire(event))
            fire(event);
    }

    private void fire(T event) {
        ActionBoxPlugin.getInstance().getManager(TriggerManager.class).fire(this.name, parseVariables(event));
    }

    public void register() {
        ActionBoxPlugin.getInstance().getManager(TriggerManager.class).registerProcessor(this);
    }
}
package space.devport.wertik.actionbox.system.trigger.struct;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import space.devport.wertik.actionbox.ActionBoxPlugin;
import space.devport.wertik.actionbox.system.trigger.TriggerManager;

public abstract class TriggerExecutor<T extends Event> implements Listener {

    @Getter
    private final String name;

    protected final TriggerManager triggerManager;

    public TriggerExecutor(String name) {
        this.name = name;
        this.triggerManager = ActionBoxPlugin.getInstance().getManager(TriggerManager.class);
    }

    /**
     * Parse TriggerContext from event.
     */
    public abstract TriggerContext parseVariables(T event);

    public abstract void onEvent(T event);

    /**
     * Trigger the Executor, checks if it should fire.
     */
    public void trigger(T event) {
        if (shouldFire(event))
            fire(event);
    }

    /**
     * Return true, if the trigger should be fired.
     */
    public boolean shouldFire(T event) {
        return true;
    }

    /**
     * Fires the event directly without any checks.
     */
    private void fire(T event) {
        ActionBoxPlugin.getInstance().getManager(TriggerManager.class).fire(this.name, parseVariables(event));
    }

    public void register() {
        ActionBoxPlugin.getInstance().getManager(TriggerManager.class).registerProcessor(this);
    }
}
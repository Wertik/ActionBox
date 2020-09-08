package space.devport.wertik.actionbox.system.trigger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class TriggerListener implements Listener {

    private final TriggerManager triggerManager;

    public TriggerListener(TriggerManager triggerManager) {
        this.triggerManager = triggerManager;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        triggerManager.process("block-break", event);
    }
}
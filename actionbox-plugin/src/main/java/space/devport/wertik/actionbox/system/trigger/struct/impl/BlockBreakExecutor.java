package space.devport.wertik.actionbox.system.trigger.struct.impl;

import org.bukkit.event.block.BlockBreakEvent;
import space.devport.wertik.actionbox.system.trigger.struct.TriggerContext;
import space.devport.wertik.actionbox.system.trigger.struct.TriggerExecutor;

public class BlockBreakExecutor extends TriggerExecutor<BlockBreakEvent> {

    public BlockBreakExecutor(String name) {
        super(name);
    }

    @Override
    public void onEvent(BlockBreakEvent event) {
        this.trigger(event);
    }

    @Override
    public TriggerContext parseVariables(BlockBreakEvent event) {
        return new TriggerContext()
                .from(context -> context.add("%eventName%", event.getEventName()))
                .fromBlock(event.getBlock())
                .fromPlayer(event.getPlayer())
                .fromItem(event.getPlayer().getInventory().getItemInMainHand(), "tool");
    }
}
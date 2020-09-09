package space.devport.wertik.actionbox.system.trigger.struct;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import space.devport.utils.text.Placeholders;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class TriggerContext extends Placeholders {

    private Player player;

    public TriggerContext from(Consumer<TriggerContext> context) {
        context.accept(this);
        return this;
    }

    public TriggerContext fromBlock(Block block) {
        add("block", block.getType().toString(), "type");
        return this;
    }

    public TriggerContext fromPlayer(Player player) {
        add("player", player.getName());
        this.player = player;
        return this;
    }

    public TriggerContext fromItem(ItemStack item, String... modifiers) {
        add("type", item.getType().toString(), modifiers);
        return this;
    }

    public TriggerContext add(String name, Object value, String... modifiers) {
        add("%" + name + (modifiers.length > 0 ? "_" + String.join("_", Arrays.asList(modifiers)) : "") + "%", value);
        return this;
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(player);
    }
}
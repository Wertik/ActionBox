package space.devport.wertik.actionbox.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.wertik.actionbox.ActionBoxPlugin;
import space.devport.wertik.actionbox.commands.ActionBoxSubCommand;

public class ReloadSubCommand extends ActionBoxSubCommand {

    public ReloadSubCommand(ActionBoxPlugin plugin) {
        super("reload", plugin);
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {
        plugin.reload(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @NotNull String getDefaultUsage() {
        return "/%label% reload";
    }

    @Override
    public @NotNull String getDefaultDescription() {
        return "Reload the plugin.";
    }

    @Override
    public @NotNull ArgumentRange getRange() {
        return new ArgumentRange(0);
    }
}
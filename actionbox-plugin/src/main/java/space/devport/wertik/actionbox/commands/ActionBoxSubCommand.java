package space.devport.wertik.actionbox.commands;

import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.SubCommand;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.wertik.actionbox.ActionBoxPlugin;

public abstract class ActionBoxSubCommand extends SubCommand {

    protected final ActionBoxPlugin plugin;

    public ActionBoxSubCommand(String name, ActionBoxPlugin plugin) {
        super(name);
        setPermissions();
        this.plugin = plugin;
    }

    @Override
    public @Nullable
    abstract String getDefaultUsage();

    @Override
    public @Nullable
    abstract String getDefaultDescription();

    @Override
    public @Nullable
    abstract ArgumentRange getRange();
}

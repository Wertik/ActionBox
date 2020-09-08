package space.devport.wertik.actionbox;

import space.devport.utils.DevportPlugin;
import space.devport.utils.UsageFlag;
import space.devport.wertik.actionbox.commands.ActionBoxCommand;
import space.devport.wertik.actionbox.commands.subcommands.ReloadSubCommand;
import space.devport.wertik.actionbox.system.box.BoxManager;
import space.devport.wertik.actionbox.system.trigger.TriggerManager;

import java.util.HashMap;
import java.util.Map;

public class ActionBoxPlugin extends DevportPlugin {

    public static ActionBoxPlugin getInstance() {
        return getPlugin(ActionBoxPlugin.class);
    }

    private final Map<Class<? extends Manager>, Manager> managers = new HashMap<>();

    @Override
    public void onPluginEnable() {
        registerManager(new BoxManager(this));
        registerManager(new TriggerManager(this));

        addMainCommand(new ActionBoxCommand())
                .addSubCommand(new ReloadSubCommand(this));

        this.managers.values().forEach(Manager::enable);
    }

    @Override
    public void onPluginDisable() {
        this.managers.values().forEach(Manager::disable);
    }

    @Override
    public void onReload() {
        this.managers.values().forEach(Manager::reload);
    }

    @Override
    public UsageFlag[] usageFlags() {
        return new UsageFlag[]{UsageFlag.CONFIGURATION, UsageFlag.LANGUAGE, UsageFlag.COMMANDS};
    }

    private void registerManager(Manager manager) {
        this.managers.put(manager.getClass(), manager);
        consoleOutput.info("Registered manager " + manager.getClass().getSimpleName());
    }

    public <T extends Manager> T getManager(Class<T> clazz) {
        return (T) this.managers.get(clazz);
    }
}
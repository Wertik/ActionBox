package space.devport.wertik.actionbox;

import space.devport.utils.ConsoleOutput;

public abstract class Manager {

    protected final ActionBoxPlugin plugin;
    protected final ConsoleOutput consoleOutput;

    public Manager(ActionBoxPlugin plugin) {
        this.plugin = plugin;
        this.consoleOutput = plugin.getConsoleOutput();
    }

    public abstract void enable();

    public abstract void reload();

    public abstract void disable();
}
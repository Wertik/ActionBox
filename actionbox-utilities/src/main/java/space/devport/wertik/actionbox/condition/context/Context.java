package space.devport.wertik.actionbox.condition.context;

import space.devport.utils.text.Placeholders;

public class Context<T> extends Placeholders {

    private final T t;

    public Context(T t) {
        super();
        this.t = t;
    }

    public ContextWrapper toWrapper() {
        return new ContextWrapper().add(this);
    }

    public T get() {
        return t;
    }
}
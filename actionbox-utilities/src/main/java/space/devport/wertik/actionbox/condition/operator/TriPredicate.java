package space.devport.wertik.actionbox.condition.operator;

public interface TriPredicate<S, U, V> {
    boolean process(S s, U u, V v);
}
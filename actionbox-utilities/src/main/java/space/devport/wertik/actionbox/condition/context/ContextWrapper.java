package space.devport.wertik.actionbox.condition.context;

import com.google.common.base.Strings;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class ContextWrapper {

    private final Set<Context<?>> contexts = new HashSet<>();

    @Nullable
    public String parse(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) return str;

        for (Context<?> context : contexts) {
            str = context.parse(str);
        }
        return str;
    }

    public ContextWrapper add(Context<?> context) {
        this.contexts.add(context);
        return this;
    }
}
package space.devport.wertik.actionbox.condition;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class ParserUtil {

    @Nullable
    public Object parseObject(String input) {

        if (Strings.isNullOrEmpty(input))
            return null;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
        }

        try {
            return Boolean.valueOf(input);
        } catch (IllegalArgumentException ignored) {
        }

        return input;
    }
}
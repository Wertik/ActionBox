import org.junit.Test;
import space.devport.wertik.actionbox.condition.Condition;
import space.devport.wertik.actionbox.condition.Rule;
import space.devport.wertik.actionbox.condition.context.ContextWrapper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConditionTest {

    @Test
    public void ruleShouldNotBeNull() {
        String[] examples = new String[]{"5>6", "5", "true", "false"};

        for (String example : examples) {
            Rule rule = Rule.fromString(example);
            assertNotNull(rule);
        }
    }

    @Test
    public void rulesShouldReturnCorrectly() {
        Map<String, Boolean> examples = new HashMap<>();

        examples.put("5>6", false);
        examples.put("true", true);
        examples.put("false", false);
        examples.put("5=5", true);
        examples.put("5>4", true);

        for (Map.Entry<String, Boolean> example : examples.entrySet()) {
            Rule rule = Rule.fromString(example.getKey());
            assertEquals(rule.process(new ContextWrapper()), example.getValue());
            System.out.println(rule.toString() + " -> " + rule.process(new ContextWrapper()));
        }
    }

    @Test
    public void conditionsShouldNotBeNull() {

        String[] examples = new String[]{"true", "false", "5>4", "true&&5>6"};

        for (String example : examples) {
            Condition condition = Condition.fromString(example);
            assertNotNull(condition);
        }
    }

    @Test
    public void conditionsShouldReturnCorrectly() {
        Map<String, Boolean> examples = new HashMap<>();

        examples.put("5>6&&4>3", false);
        examples.put("true//false", true);
        examples.put("false&&true", false);
        examples.put("5=5&&6=6", true);
        examples.put("5>4&&4<6", true);

        for (Map.Entry<String, Boolean> example : examples.entrySet()) {
            Condition condition = Condition.fromString(example.getKey());
            assertEquals(condition.process(new ContextWrapper()), example.getValue());
            System.out.println(condition.toString() + " -> " + condition.process(new ContextWrapper()));
        }
    }
}
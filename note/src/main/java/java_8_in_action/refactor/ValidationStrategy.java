package java_8_in_action.refactor;

/**
 * Created by yaojie.hou on 2017/4/17.
 */
@FunctionalInterface
public interface ValidationStrategy {
	boolean execute(String s);
}

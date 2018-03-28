package java_8_in_action.refactor;

/**
 * Created by yaojie.hou on 2017/4/17.
 */
public class Validator {
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy v) {
		this.strategy = v;
	}

	public boolean validate(String s) {
		return strategy.execute(s);
	}
}

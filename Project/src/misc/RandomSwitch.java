package misc;

public interface RandomSwitch {

	default boolean randSwitch() {

		int rand = (int) (Math.random() * 2);

		if (rand == 0) {

			return false;

		} else {
			return true;
		}
	}

}

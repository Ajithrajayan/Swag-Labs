package config;

public enum TimeOut {

	LOW(10), MID(30), HIGH(60);

	private final int value;

	TimeOut(final int newValue) {
		value = newValue;

	}

	public int getValue() {
		return value;
	}
}

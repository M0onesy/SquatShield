package com.squatshield.input;

public final class SneakShieldContext {
	private static final ThreadLocal<Boolean> SNEAK_TRIGGERED = ThreadLocal.withInitial(() -> false);

	private SneakShieldContext() {
	}

	public static void setSneakTriggered(boolean sneakTriggered) {
		SNEAK_TRIGGERED.set(sneakTriggered);
	}

	public static boolean isSneakTriggered() {
		return SNEAK_TRIGGERED.get();
	}
}

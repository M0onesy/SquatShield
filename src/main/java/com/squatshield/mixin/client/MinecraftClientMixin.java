package com.squatshield.mixin.client;

import com.squatshield.input.SneakShieldContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.GameOptions;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Shadow
	@Final
	public GameOptions options;

	@Shadow
	public ClientPlayerEntity player;

	@Shadow
	@Final
	public ClientPlayerInteractionManager interactionManager;

	@Inject(method = "handleInputEvents", at = @At("TAIL"))
	private void squatshield$triggerShieldFromSneak(CallbackInfo ci) {
		if (this.player == null || !this.options.sneakKey.isPressed() || this.player.isUsingItem()) {
			return;
		}

		Hand hand = this.squatshield$getPreferredShieldHand();
		if (hand == null) {
			return;
		}

		SneakShieldContext.setSneakTriggered(true);
		try {
			ActionResult actionResult = this.interactionManager.interactItem(this.player, hand);
			if (actionResult instanceof ActionResult.Success success && success.swingSource() == ActionResult.SwingSource.CLIENT) {
				this.player.swingHand(hand);
			}
		} finally {
			SneakShieldContext.setSneakTriggered(false);
		}
	}

	@Redirect(
		method = "handleInputEvents",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;stopUsingItem(Lnet/minecraft/entity/player/PlayerEntity;)V")
	)
	private void squatshield$keepShieldUsingWhileSneaking(ClientPlayerInteractionManager interactionManager, net.minecraft.entity.player.PlayerEntity player) {
		if (this.options.sneakKey.isPressed() && player.isUsingItem() && player.getActiveItem().getItem() instanceof ShieldItem) {
			return;
		}

		interactionManager.stopUsingItem(player);
	}

	private Hand squatshield$getPreferredShieldHand() {
		if (this.squatshield$isShield(this.player.getOffHandStack())) {
			return Hand.OFF_HAND;
		}

		if (this.squatshield$isShield(this.player.getMainHandStack())) {
			return Hand.MAIN_HAND;
		}

		return null;
	}

	private boolean squatshield$isShield(ItemStack stack) {
		return stack.getItem() instanceof ShieldItem;
	}
}

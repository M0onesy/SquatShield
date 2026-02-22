package com.squatshield.mixin.client;

import com.squatshield.input.SneakShieldContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
	@Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
	private void squatshield$blockRightClickShield(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		if (SneakShieldContext.isSneakTriggered()) {
			return;
		}

		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() instanceof ShieldItem) {
			cir.setReturnValue(ActionResult.PASS);
		}
	}
}

package com.islamozroko.antislowfall.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AntiSlowFallMixin {
    
    @Inject(method = "addEffect", at = @At("HEAD"), cancellable = true)
    private void onAddEffect(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        // Проверяем, что это наш игрок
        if ((Object) this == Minecraft.getInstance().player) {
            // Если эффект - это плавное падение (Slow Falling)
            if (effect.getEffect() == MobEffects.SLOW_FALLING) {
                // Отменяем добавление эффекта
                cir.setReturnValue(false);
            }
        }
    }
}

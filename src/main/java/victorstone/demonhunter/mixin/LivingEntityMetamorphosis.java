package victorstone.demonhunter.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;

import victorstone.demonhunter.demonhunter.DemonhunterSounds;
import victorstone.demonhunter.effect.DemonhunterEffects;
import victorstone.demonhunter.effect.MetamorphosisStatusEffect;

@Mixin(LivingEntity.class)
public class LivingEntityMetamorphosis {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void damage_HEAD_METAMORPHOSIS(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		Entity attacker = source.getAttacker();

		if (source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)
				|| source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)
				|| attacker == null
				|| amount <= 0
				|| entity.getWorld().isClient()) {
			return;
		}

		if (entity.hasStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry)) {
			cir.cancel(); // Block the damage

			// Optional: play visuals and sound
			MetamorphosisStatusEffect.pop(entity);
//			DemonhunterSounds.play(entity.getWorld(), entity, DemonhunterSounds.METAMORPHOSIS.soundEvent());


			// ✨ NOTE: The effect is NOT removed — it will expire on its own after 10 seconds
		}
	}
}
//package victorstone.demonhunter.mixin;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.damage.DamageSource;
//import net.minecraft.entity.effect.StatusEffectInstance;
//import net.minecraft.registry.tag.DamageTypeTags;
//
//import victorstone.demonhunter.effect.DemonhunterEffects;
//import victorstone.demonhunter.effect.MetamorphosisStatusEffect;
/// / import victorstone.demonhunter.demonhunter.DemonhunterSounds; // Uncomment if using sounds
//
//@Mixin(LivingEntity.class)
//public class LivingEntityMetamorphosis {
//    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
//    private void damage_HEAD_METAMORPHOSIS(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        LivingEntity entity = (LivingEntity) (Object) this;
//        Entity attacker = source.getAttacker();
//
//        if (source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)
//                || source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)
//                || attacker == null
//                || amount <= 0
//                || entity.getWorld().isClient()) {
//            return;
//        }
//
//        if (entity.hasStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry)) {
//            cir.cancel(); // Block the damage
//
//            StatusEffectInstance instance = entity.getStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry);
//            if (instance != null) {
//                // Remove current instance
//                entity.removeStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry);
//
//                // Downgrade amplifier if > 0
//                if (instance.getAmplifier() > 0) {
//                    entity.addStatusEffect(
//                        new StatusEffectInstance(
//                            DemonhunterEffects.METAMORPHOSIS.entry,
//                            instance.getDuration(),
//                            instance.getAmplifier() - 1,
//                            instance.isAmbient(),
//                            instance.shouldShowParticles(),
//                            instance.shouldShowIcon()
//                        )
//                    );
//                }
//            }
//
//            MetamorphosisStatusEffect.pop(entity);
//
//            // Optionally play a sound
//            // DemonhunterSounds.play(entity.getWorld(), entity, DemonhunterSounds.METAMORPHOSIS.soundEvent());
//        }
//    }
//}
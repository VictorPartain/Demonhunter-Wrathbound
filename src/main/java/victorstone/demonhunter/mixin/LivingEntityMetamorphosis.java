package victorstone.demonhunter.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
			cir.cancel();
			var instance = entity.getStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry);
			if (instance != null) {
				// Remove current instance
				entity.removeStatusEffect(DemonhunterEffects.METAMORPHOSIS.entry);
				if (instance.getAmplifier() > 0) {
					// Add a new instance with a lower amplifier
					entity.addStatusEffect(
							new StatusEffectInstance(DemonhunterEffects.METAMORPHOSIS.entry,
									instance.getDuration(),
									instance.getAmplifier() - 1,
									instance.isAmbient(),
									instance.shouldShowParticles(),
									instance.shouldShowIcon())
					);
				}
			}
			MetamorphosisStatusEffect.pop(entity);
			DemonhunterSounds.playSoundEvent(entity.getWorld(), entity, DemonhunterSounds.metamorphosis.soundEvent());
		}
	}
}

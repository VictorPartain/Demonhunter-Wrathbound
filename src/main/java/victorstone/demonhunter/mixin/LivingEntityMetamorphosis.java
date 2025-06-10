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


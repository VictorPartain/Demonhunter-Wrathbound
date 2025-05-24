package victorstone.demonhunter.effect;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.fx.ParticleHelper;

public class MetamorphosisStatusEffect extends StatusEffect {
    public static final float multiplier = 0.5f;
    public MetamorphosisStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);


        this.addAttributeModifier(
                EntityAttributes.GENERIC_MAX_HEALTH,
                Identifier.of("demonhunter:metamorphosis_max_health"),
                10,
                EntityAttributeModifier.Operation.ADD_VALUE);

        this.addAttributeModifier(
                EntityAttributes.GENERIC_ARMOR,
                Identifier.of("demonhunter:metamorphosis_armor"),
                4.0,
                EntityAttributeModifier.Operation.ADD_VALUE
                );
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                Identifier.of("demonhunter:metamorphosis_dmg"),
                5.0,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
    }
    private static final ParticleBatch particles = new ParticleBatch(
            "spell_engine:holy_spark",
            ParticleBatch.Shape.PILLAR,
            ParticleBatch.Origin.CENTER,
            null,
            25,
            0.01f,
            0.2f,
            0
    );
    public static void pop(Entity centerEntity) {
        ParticleHelper.sendBatches(centerEntity, new ParticleBatch[]{particles});
    }
//    @Override
//    public void onApplied(net.minecraft.entity.LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
//        // Apply absorption
//        entity.setAbsorptionAmount(4 + amplifier * 2);
//    }
//
//    @Override
//    public void onRemoved(net.minecraft.entity.LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
//        // Remove absorption
//        entity.setAbsorptionAmount(0);
//    }

}

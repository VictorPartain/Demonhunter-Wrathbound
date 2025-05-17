package victorstone.demonhunter.spell;

import net.spell_engine.api.spell.SpellCast;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class FelRushSpell {
    public static void onCast(LivingEntity caster, SpellCast cast) {
        Vec3d forward = caster.getRotationVec(1.0F).multiply(5); // 5 blocks forward
        caster.setVelocity(forward);
        caster.velocityModified = true;
    }
}

package victorstone.demonhunter.effect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.spell_engine.api.config.AttributeModifier;
import net.spell_engine.api.config.ConfigFile;
import net.spell_engine.api.config.EffectConfig;
import net.spell_engine.api.effect.*;
import net.spell_power.api.SpellPowerMechanics;
import victorstone.demonhunter.Demonhunter;

import java.util.ArrayList;
import java.util.List;
public class DemonhunterEffects {
    public static final List<Effects.Entry> entries = new ArrayList<>();
    private static Effects.Entry add(Effects.Entry entry) {
        entries.add(entry);
        return entry;

    }
    public static final Effects.Entry METAMORPHOSIS = add(new Effects.Entry(
            Identifier.of(Demonhunter.MOD_ID, "metamorphosis"),
            "Metamorphosis",
            "Feel the rage of the demon inside",
            new MetamorphosisStatusEffect(StatusEffectCategory.BENEFICIAL, 0x66ccff)));

    public static void register(ConfigFile.Effects config) {
        Synchronized.configure(METAMORPHOSIS.effect, true);
        Effects.register(entries, config.effects);
    }

}






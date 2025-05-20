package victorstone.demonhunter.registry;

import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.minecraft.util.Identifier;
import victorstone.demonhunter.spell.FelRushSpell;

public class DemonhunterSpells {
    public static Spell FEL_RUSH;

    public static void register() {
        FEL_RUSH = SpellRegistry.register(
                new Identifier("demonhunter", "fel_rush"),
                new Spell()
                        .onCast(FelRushSpell::onCast)
        );
    }
}

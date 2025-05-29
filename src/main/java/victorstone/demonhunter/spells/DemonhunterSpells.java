package victorstone.demonhunter.spells;

import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.Sound;
import victorstone.demonhunter.Demonhunter;
import victorstone.demonhunter.demonhunter.DemonhunterSounds;

import java.util.ArrayList;
import java.util.List;

public class DemonhunterSpells {
    public record Entry(Identifier id, Spell spell) {}

    public static final List<Entry> entries = new ArrayList<>();
    private static Entry add(Entry entry) {
        entries.add(entry);
        return entry;
    }

    public static final Entry METAMORPHOSIS = add(createMetamorphosis());

    private static Entry createMetamorphosis() {
        var id = Identifier.of(Demonhunter.MOD_ID, "metamorphosis");
        var spell = new Spell();

        spell.type = Spell.Type.ACTIVE;
        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 1;
        spell.active.cast.animation = "spell_engine:self_cast";

        spell.release = new Spell.Release();
        spell.release.animation = "spell_engine:self_cast_release";
        spell.release.sound = new Sound(DemonhunterSounds.METAMORPHOSIS.id());

        spell.cost = new Spell.Cost();
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = 10;

        spell.impacts = List.of();  // Add any status effect application logic here

        return new Entry(id, spell);
    }
}

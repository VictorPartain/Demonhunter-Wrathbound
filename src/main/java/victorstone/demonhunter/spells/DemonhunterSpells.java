package victorstone.demonhunter.spells;

import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.*;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
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
        spell.school = SpellSchools.ARCANE;
        spell.group = "primary";

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

        spell.target = new Spell.Target();  // ✅ Required for self-casting effects

        // Status effect
        Spell.Impact impact = new Spell.Impact();
        impact.action = new Spell.Impact.Action();
        impact.action.type = Spell.Impact.Action.Type.STATUS_EFFECT;

        Spell.Impact.Action.StatusEffect status = new Spell.Impact.Action.StatusEffect();
        status.effect_id = "demonhunter:metamorphosis";
        status.duration = 10.0F;
        status.amplifier = 1;
        status.show_particles = true;

        impact.action.status_effect = status;
        spell.impacts = List.of(impact);

        return new Entry(id, spell);
    }
    public static final Entry FEL_DASH = add(createFelDash());

    private static Entry createFelDash() {
        Identifier id = Identifier.of(Demonhunter.MOD_ID, "fel_dash");
        Spell spell = new Spell();

        spell.type = Spell.Type.ACTIVE;
        spell.school = SpellSchools.ARCANE;
        spell.group = "primary";

        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 0;
        spell.active.cast.animation = "spell_engine:dash_forward";

        spell.release = new Spell.Release();
        spell.release.animation = "spell_engine:dash_forward";

        spell.cost = new Spell.Cost();
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = 5.0F;

        spell.target = new Spell.Target(); // defaults to caster

        // Dash forward
        Spell.Impact impact = new Spell.Impact();
        impact.action = new Spell.Impact.Action();
        impact.action.type = Spell.Impact.Action.Type.TELEPORT;

        Spell.Impact.Action.Teleport teleport = new Spell.Impact.Action.Teleport();
        teleport.mode = Spell.Impact.Action.Teleport.Mode.FORWARD;
        teleport.forward = new Spell.Impact.Action.Teleport.Forward();
        teleport.forward.distance = 10.0F;

        impact.action.teleport = teleport;

        spell.impacts = List.of(impact);

        return new Entry(id, spell);
    }




}

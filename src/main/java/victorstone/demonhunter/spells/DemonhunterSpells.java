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
    public static final Entry ESSENCE_BREAK = add(createEssenceBreak());

    private static Entry createEssenceBreak() {
        Identifier id = Identifier.of(Demonhunter.MOD_ID, "essence_break");
        Spell spell = new Spell();

        spell.type = Spell.Type.ACTIVE;
        spell.school = SpellSchools.ARCANE;
        spell.group = "primary";

        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 1;
        spell.active.cast.animation = "spell_engine:slam"; // visually aggressive

        spell.release = new Spell.Release();
        spell.release.animation = "spell_engine:slam";

        spell.cost = new Spell.Cost();
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = 12;

        // Targeting setup
        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;

        // Area targeting config (wide frontal cone)
        Spell.Target.Area area = new Spell.Target.Area();
        area.horizontal_range_multiplier = 4.0F; // wide
        area.vertical_range_multiplier = 1.0F;
        area.angle_degrees = 100.0F;             // wide cone
        area.include_caster = false;

        spell.target.area = area;

        // Impact: apply Weakness
        Spell.Impact impact = new Spell.Impact();
        impact.action = new Spell.Impact.Action();
        impact.action.type = Spell.Impact.Action.Type.STATUS_EFFECT;

        Spell.Impact.Action.StatusEffect status = new Spell.Impact.Action.StatusEffect();
        status.effect_id = "minecraft:weakness";
        status.duration = 8.0F;     // 8 seconds
        status.amplifier = 1;
        status.show_particles = true;

        impact.action.status_effect = status;
        spell.impacts = List.of(impact);

        return new Entry(id, spell);
    }
    public static final Entry EYE_BEAM = add(createEyebeam());

    private static  Entry createEyebeam() {
        Identifier id = Identifier.of(Demonhunter.MOD_ID, "eyebeam");
        Spell spell = new Spell();
        spell.type = Spell.Type.ACTIVE;
        spell.school = SpellSchools.ARCANE;
        spell.group = "primary";
        spell.range = 30.0F;

        // Casting config
        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 2.0F;
        spell.active.cast.animation = "spell_engine:channel_beam";

        // Beam targeting
        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.BEAM;
        spell.target.beam = new Spell.Target.Beam();
        spell.target.beam.width = 1.5F;
        spell.target.beam.flow = 2.0F;

        // Release
        spell.release = new Spell.Release();
        spell.release.animation = "spell_engine:release_beam";

        // Cooldown and cost
        spell.cost = new Spell.Cost();
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = 12.0F;

        // Impact: Damage + Heal
        Spell.Impact damageImpact = new Spell.Impact();
        damageImpact.action = new Spell.Impact.Action();
        damageImpact.action.type = Spell.Impact.Action.Type.DAMAGE;
        damageImpact.action.damage = new Spell.Impact.Action.Damage();
        damageImpact.action.damage.spell_power_coefficient = 1.5F;
        damageImpact.action.damage.knockback = 0.2F;

        Spell.Impact healImpact = new Spell.Impact();
        healImpact.action = new Spell.Impact.Action();
        healImpact.action.type = Spell.Impact.Action.Type.HEAL;
        healImpact.action.heal = new Spell.Impact.Action.Heal();
        healImpact.action.heal.spell_power_coefficient = 1.0F;
        healImpact.action.apply_to_caster = true;

        // Apply Metamorphosis to the caster
        Spell.Impact metaImpact = new Spell.Impact();
        metaImpact.action = new Spell.Impact.Action();
        metaImpact.action.type = Spell.Impact.Action.Type.STATUS_EFFECT;
        metaImpact.action.apply_to_caster = true;

        Spell.Impact.Action.StatusEffect metaEffect = new Spell.Impact.Action.StatusEffect();
        metaEffect.effect_id = "demonhunter:metamorphosis";
        metaEffect.duration = 5.0F; // 5 seconds
        metaEffect.amplifier = 0;
        metaEffect.show_particles = true;
        metaEffect.refresh_duration = true;

        metaImpact.action.status_effect = metaEffect;

        // Combine all impacts
        spell.impacts = List.of(damageImpact, healImpact, metaImpact);
        return new Entry(id, spell);
    }






}

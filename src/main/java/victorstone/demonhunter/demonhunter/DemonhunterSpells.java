package victorstone.demonhunter.demonhunter;
/**
 * import net.paladins.entity.BarrierEntity;
 * import net.paladins.effect.PaladinEffects;
 * import net.paladins.PaladinsMod;
 * Check this out and double check what these files use.
 * import org.jetbrains.annotations.Nullable;
 * */

import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.spell_engine.api.render.LightEmission;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.util.TriState;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
import org.jetbrains.annotations.Nullable;
import victorstone.demonhunter.Demonhunter;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class DemonhunterSpells {

    public record SpellTooltipData(Identifier id, Spell spell, String name, String description,
                                   @Nullable SpellTooltip.DescriptionMutator mutator) {
    }

    public static List<SpellTooltipData> spellTooltips = new ArrayList<>();

    private static SpellTooltipData add(SpellTooltipData spellTooltipData) {
        spellTooltips.add(spellTooltipData);
        return spellTooltipData;
    }

    private static final String GROUP_PRIMARY = "primary";


    private static Spell active() {
        var spell = new Spell();
        // What type of spell
        spell.type = Spell.Type.ACTIVE;
        spell.active.cast = new Spell.Active.Cast();
        spell.learn = new Spell.Learn();
        spell.active.scroll = new Spell.Active.Scroll();

        return spell;
    }

    private static Spell passive(){
        var spell = new Spell();

        spell.range = 0;
        spell.tier = 7; // Not sure what this means gotta look into it, was recommended by docs of other resources I looked at

        spell.type = Spell.Type.PASSIVE;
        spell.passive = new Spell.Passive();

        spell.tooltip.show_header = false;
        spell.tooltip.name = new Spell.Tooltip.LineOptions(false, false);
        spell.tooltip.description.color = Formatting.BLACK.asString();
        spell.tooltip.description.show_in_compact = true;

        return spell;

}
    private static void Cooldown(Spell spell, float duration) {
        if(spell.cost == null){
            spell.cost = new Spell.Cost();
        }
        if(spell.cost.cooldown == null){
            spell.cost.cooldown = new Spell.Cost.Cooldown();
        }
        spell.cost.cooldown.duration = duration;
    }

    private static Spell.Impact spellImpact(String effectID, float duration){
        var buff = new Spell.Impact();
        buff.action = new Spell.Impact.Action();
        buff.action.type = Spell.Impact.Action.Type.STATUS_EFFECT;
        buff.action.status_effect = new Spell.Impact.Action.StatusEffect();
        buff.action.status_effect.effect_id = effectID;
        buff.action.status_effect.duration = duration;
        return buff;
    }

    private static Spell.Impact Damage(float coefficent, float knockback){
        var buff = new Spell.Impact();
        buff.action = new Spell.Impact.Action();
        buff.action.damage = new Spell.Impact.Action.Damage();
        buff.action.type = Spell.Impact.Action.Type.DAMAGE;
        buff.action.damage.spell_power_coefficient = coefficent;
        buff.action.damage.knockback = knockback;
        return buff;
    }

    private static Spell.Impact Heal(float coefficent){
        var buff = new Spell.Impact();
        buff.action = new Spell.Impact.Action();
        buff.action.heal = new Spell.Impact.Action.Heal();
        buff.action.type = Spell.Impact.Action.Type.HEAL;
        buff.action.heal.spell_power_coefficient = coefficent;
        return buff;
    }

    private static Spell.Impact.TargetModifier impactModifier(String type){
        var condition = new Spell.TargetCondition();
        condition.entity_type = type;
        var modifier = new Spell.Impact.TargetModifier();
        modifier.conditions = List.of(condition);
        return modifier;
    }

    private static ParticleBatch castedParticles(String particleId){
        return new ParticleBatch(
                particleId,
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.053f, 0.1f);

    }

    private static Spell.Impact EffectImpact(Identifier effectId, float duration) {
        var buff = new Spell.Impact();
        buff.action = new Spell.Impact.Action();
        buff.action.type = Spell.Impact.Action.Type.STATUS_EFFECT;
        buff.action.status_effect = new Spell.Impact.Action.StatusEffect();
        buff.action.status_effect.effect_id = effectId.toString();
        buff.action.status_effect.duration = duration;
        return buff;
    }

    private static void ItemCost(Spell spell, String itemId) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.item = new Spell.Cost.Item();
        spell.cost.item.id = itemId;
    }

    private static final Identifier CHAOS_SPARKS = SpellEngineParticles.getMagicParticleVariant(
            SpellEngineParticles.WHITE,
            SpellEngineParticles.MagicParticleFamily.Shape.IMPACT,
            SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id();
    private static final Identifier CHAOS_SPARKS_DECELERATE = SpellEngineParticles.getMagicParticleVariant(
            SpellEngineParticles.WHITE,
            SpellEngineParticles.MagicParticleFamily.Shape.IMPACT,
            SpellEngineParticles.MagicParticleFamily.Motion.DECELERATE).id();
    private static final Identifier CHAOS_IMPACT = SpellEngineParticles.getMagicParticleVariant(
            SpellEngineParticles.WHITE,
            SpellEngineParticles.MagicParticleFamily.Shape.IMPACT,
            SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id();
    private static final Identifier CHAOS_IMPACT_DECELERATE = SpellEngineParticles.getMagicParticleVariant(
            SpellEngineParticles.WHITE,
            SpellEngineParticles.MagicParticleFamily.Shape.IMPACT,
            SpellEngineParticles.MagicParticleFamily.Motion.DECELERATE).id();




    public static final SpellTooltipData METAMORPHOSIS = add(metamorphosis());
    private static SpellTooltipData metamorphosis() {
            var id = Identifier.of(Demonhunter.MOD_ID, "metamorphosis");
            var title = "Metamorphosis";
            var description = "";

            var spell = active();
            spell.school = SpellSchools.FIRE;
            spell.range = 0;
            spell.tier = 2;

//            spell.release.animation = "spell_engine:one_handed_area_release";

            var buf = EffectImpact(DemonHunterEffects.METAMORPHOSIS.id, 8);
            buff.action.status_effect.amplifier = 0;
            buff.action.status_effect.amplifier_cap = 2;
            buff.action.status_effect.amplifier_power_multiplier = 0.5F;

            buff.sound = new Sound(DemonHunterSounds.metamorphosis_release.id());
            buff.particles = new ParticleBatch[] {
                    new ParticleBatch(
                            CHAOS_IMPACT_DECELERATE.toString(),
                            ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                            40, 0.2F, 0.2F)
            };
            spell.impacts = List.of(buff);

            Cooldown(spell, 30);
            ItemCost(spell, "runes:chaos_stone");
            spell.cost.exhaust = 0.3F;

            return new SpellTooltipData(id, spell, title, description, null);
        }
    }





}


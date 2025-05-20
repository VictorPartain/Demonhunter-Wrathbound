package victorstone.demonhunter.registry;
import net.minecraft.util.Formatting;
import victorstone.demonhunter.spell.FelRushSpell;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.api.render.LightEmission;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.util.TriState;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_power.api.SpellSchools;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.ArrayList;

public class DemonhunterSpells {
    public record SpellTooltipData(Identifier id, Spell spell, String name, String description) {
    }

    public static List<SpellTooltipData> spellTooltips = new ArrayList<>();

    protected static SpellTooltipData add(SpellTooltipData spellTooltipData) {
        spellTooltips.add(spellTooltipData);
        return spellTooltipData;
    }

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



}

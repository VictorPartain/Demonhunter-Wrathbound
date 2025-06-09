package victorstone.demonhunter.demonhunter;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.spell_engine.api.config.AttributeModifier;
import net.spell_engine.api.config.WeaponConfig;
import net.spell_engine.api.item.Equipment;
import net.spell_engine.api.item.weapon.SpellSwordItem;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellSchools;
import victorstone.demonhunter.Demonhunter;
import victorstone.demonhunter.demonhunter.DemonhunterItemGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemonhunterWeapons {
    public static final List<Weapon.Entry> entries = new ArrayList<>();

    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material, Weapon.Factory factory, WeaponConfig config, Equipment.WeaponType type) {
        var entry = new Weapon.Entry(Demonhunter.MOD_ID, name, material, factory, config, type);
        entries.add(entry);
        return entry;
    }

    private static final float warglaiveDamage = 5.5F;
    private static final float warglaiveSpeed = -2.0F;

    private static Weapon.Entry createWarglaive(String name) {
        return entry(
                name,
                Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),
                SpellSwordItem::new,
                new WeaponConfig(warglaiveDamage, warglaiveSpeed),
                Equipment.WeaponType.SWORD
        );
    }

    public static final Weapon.Entry WARGLAIVE = createWarglaive("warglaive")
            .attribute(AttributeModifier.bonus(SpellSchools.ARCANE.id, 2.5F))
            .loot(Equipment.LootProperties.of(3));

    public static void register(Map<String, WeaponConfig> configs) {
        Weapon.register(configs, entries, victorstone.demonhunter.DemonhunterItemGroup.KEY);
    }
}

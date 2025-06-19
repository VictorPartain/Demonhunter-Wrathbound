package victorstone.demonhunter.item.armor;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import net.spell_engine.api.config.ArmorSetConfig;
import net.spell_engine.api.config.AttributeModifier;
import net.spell_engine.api.item.Equipment;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.SpellSchools;

import victorstone.demonhunter.Demonhunter;
import victorstone.demonhunter.item.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class armor {
    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();

    // ========== Utility ==========
    private static RegistryEntry<ArmorMaterial> material(
            String name, int protHead, int protChest, int protLegs, int protFeet,
            int enchantability, Supplier<Ingredient> repairIngredient) {

        var material = new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.HELMET, protHead,
                        ArmorItem.Type.CHESTPLATE, protChest,
                        ArmorItem.Type.LEGGINGS, protLegs,
                        ArmorItem.Type.BOOTS, protFeet
                ),
                enchantability,
                repairIngredient,
                new ArmorMaterial.Layer(Demonhunter.MOD_ID + ":" + name),
                0, 0
        );

        return Registry.registerReference(Registries.ARMOR_MATERIAL,Identifier.of(Demonhunter.MOD_ID, name), material);
    }

    private static final Identifier ATTACK_DAMAGE_ID = Identifier.ofVanilla("generic.attack_damage");
    private static final Identifier ARMOR_TOUGHNESS_ID = Identifier.ofVanilla("generic.armor_toughness");

    private static AttributeModifier toughnessBonus(float value) {
        return new AttributeModifier(
                ARMOR_TOUGHNESS_ID.toString(),
                value,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
    }

    // ========== Armor Materials ==========
    public static final RegistryEntry<ArmorMaterial> fel_armor_material = material(
            "fel_armor",
            2, 6, 5, 2,
            9,
            () -> Ingredient.ofItems(Items.NETHERITE_INGOT)
    );

    // ========== Armor Sets ==========
    private static final float fel_spell_power = 1.0F;
    private static final float fel_toughness = 1.0F;

    public static final Armor.Set felArmorSet = create(
            fel_armor_material,
            Identifier.of(Demonhunter.MOD_ID, "fel_armor"),
            30,
            DemonhunterGenericArmor::new,
            ArmorSetConfig.with(
                    new ArmorSetConfig.Piece(2)
                            .add(toughnessBonus(fel_toughness))
                            .add(AttributeModifier.multiply(SpellSchools.ARCANE.id, fel_spell_power)),
                    new ArmorSetConfig.Piece(6)
                            .add(toughnessBonus(fel_toughness))
                            .add(AttributeModifier.multiply(SpellSchools.ARCANE.id, fel_spell_power)),
                    new ArmorSetConfig.Piece(5)
                            .add(toughnessBonus(fel_toughness))
                            .add(AttributeModifier.multiply(SpellSchools.ARCANE.id, fel_spell_power)),
                    new ArmorSetConfig.Piece(2)
                            .add(toughnessBonus(fel_toughness))
                            .add(AttributeModifier.multiply(SpellSchools.ARCANE.id, fel_spell_power))
            ), 3
    ).armorSet();

    // ========== Entry Creation ==========
    private static Armor.Entry create(
            RegistryEntry<ArmorMaterial> material, Identifier id, int durability,
            Armor.Set.ItemFactory factory, ArmorSetConfig defaults, int tier) {
        var entry = Armor.Entry.create(material, id, durability, factory, defaults, Equipment.LootProperties.of(tier));
        entries.add(entry);
        return entry;
    }

    // ========== Register ==========
    public static void register(Map<String, ArmorSetConfig> configs) {
        Armor.register(configs, entries, Group.KEY);
    }
}

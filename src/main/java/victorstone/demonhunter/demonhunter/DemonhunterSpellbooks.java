package victorstone.demonhunter.demonhunter;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.compat.trinkets.SpellBookTrinketItem;

public class DemonhunterSpellbooks {
    public static final Item HAVOC_SPELLBOOK = register(
            "metamorphosis_spellbook",
            new SpellBookTrinketItem(
                    new Item.Settings().maxCount(1),
                    Identifier.of("demonhunter", "havoc"),
                    null
            )
    );

    public static final Item VENGEANCE_SPELLBOOK = register(
            "vengeance_spellbook",
            new SpellBookTrinketItem(
                    new Item.Settings().maxCount(1),
                    Identifier.of("demonhunter", "vengeance"),
                    null
            )
    );

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of("demonhunter", name), item);
    }

    public static void registerItems() {
        // Optional
    }
}

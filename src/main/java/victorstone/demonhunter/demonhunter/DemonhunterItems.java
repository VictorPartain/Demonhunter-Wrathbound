package victorstone.demonhunter.demonhunter;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.spell_engine.compat.trinkets.SpellBookTrinketItem;


public class DemonhunterItems {

    // Register your custom spell book
    public static final Item Havoc = register(
            "metamorphosis_spellbook",
            new SpellBookTrinketItem(
                    new Item.Settings().maxCount(1),
                    Identifier.of("demonhunter", "spell_pools/havoc"),
                    DemonhunterSounds.metamorphosis.soundEvent()
            )
    );

    // Static method to register an item
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM,  Identifier.of("demonhunter", name), item);
    }

    // Call this in your mod initializer to register everything
    public static void registerItems() {
        // Forces class load to trigger static initializers (optional in some setups)
    }
}

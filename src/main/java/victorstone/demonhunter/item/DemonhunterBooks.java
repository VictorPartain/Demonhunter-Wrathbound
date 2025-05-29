package victorstone.demonhunter.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.SpellBooks;
import net.spell_engine.compat.trinkets.SpellBookTrinketItem;
import victorstone.demonhunter.Demonhunter;
import victorstone.demonhunter.demonhunter.DemonhunterSounds;

import java.util.List;



public class DemonhunterBooks {
    public static void register() {
        var books = List.of("havoc","vengeance");
        for (var name: books) {
            SpellBooks.createAndRegister(Identifier.of(Demonhunter.MOD_ID, name), Group.KEY);
        }
    }
    public static final Item HAVOC_SPELLBOOK = Registry.register(
            Registries.ITEM,
            Identifier.of("demonhunter", "havoc_spellbook"),
            new SpellBookTrinketItem(
                    new Item.Settings().maxCount(1),
                    Identifier.of("demonhunter", "havoc"),
                    null
            )
    );


    public static final Item VENGEANCE_SPELLBOOK = Registry.register(
            Registries.ITEM,
            Identifier.of("demonhunter", "vengeance_spellbook"),
            new SpellBookTrinketItem(
                    new Item.Settings().maxCount(1),
                    null,
                    null// no spell pool
            )
    );

}
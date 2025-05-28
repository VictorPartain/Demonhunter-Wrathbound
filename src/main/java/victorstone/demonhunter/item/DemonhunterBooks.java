package victorstone.demonhunter.item;

import net.minecraft.util.Identifier;
import victorstone.demonhunter.*;
import net.spell_engine.api.item.SpellBooks;
import victorstone.demonhunter.Demonhunter;

import java.util.List;

public class DemonhunterBooks {
    public static void register() {
        var books = List.of("havoc");
        for (var name: books) {
            SpellBooks.createAndRegister(Identifier.of(Demonhunter.MOD_ID, name), Group.KEY);
        }
    }
}
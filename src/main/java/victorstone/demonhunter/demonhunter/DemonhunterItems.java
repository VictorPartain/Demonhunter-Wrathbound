package victorstone.demonhunter.demonhunter;

import net.minecraft.item.Item;
import java.util.HashMap;


public class DemonhunterItems {
    public static final HashMap<String, Item> entries;
        static {
            entries = new HashMap<>();
            for (var weaponEntry : DemonhunterWeapons.entries) {
                entries.put(weaponEntry.id().toString(), weaponEntry.item());
            }

        }}

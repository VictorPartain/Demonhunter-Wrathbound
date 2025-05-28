package victorstone.demonhunter.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import victorstone.demonhunter.Demonhunter;

public class Group {
    public static Identifier ID = Identifier.of(Demonhunter.MOD_ID, "generic");
    public static RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ID);
    public static ItemGroup DEMONHUNTER;
}

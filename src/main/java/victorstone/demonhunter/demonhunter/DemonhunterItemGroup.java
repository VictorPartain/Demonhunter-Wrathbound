package victorstone.demonhunter.demonhunter;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import victorstone.demonhunter.Demonhunter;

public class DemonhunterItemGroup {
    public static final Identifier ID = Identifier.of(Demonhunter.MOD_ID, "general");
    public static final RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ID);
    public static ItemGroup DEMONHUNTER;
}

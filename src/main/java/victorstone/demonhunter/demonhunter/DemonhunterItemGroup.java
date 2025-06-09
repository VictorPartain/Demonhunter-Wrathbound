package victorstone.demonhunter.demonhunter;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import victorstone.demonhunter.demonhunter.DemonhunterWeapons;

public class DemonhunterItemGroup {
    public static final RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("demonhunter", "general"));

    public static final ItemGroup GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DemonhunterWeapons.WARGLAIVE.item()))
            .displayName(Text.literal("Demonhunter Items"))
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, KEY, GROUP);
    }
}

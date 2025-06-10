package victorstone.demonhunter;

import net.fabric_extras.structure_pool.api.StructurePoolConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.spell_engine.api.config.ConfigFile;
import net.tinyconfig.ConfigManager;
import net.wizards.config.Default;
//import victorstone.demonhunter.demonhunter.DemonhunterItemGroup;
import victorstone.demonhunter.demonhunter.*;
//import victorstone.demonhunter.demonhunter.DemonhunterWeapons;
import victorstone.demonhunter.effect.DemonhunterEffects;
import victorstone.demonhunter.item.DemonhunterBooks;

public class Demonhunter implements ModInitializer {
	public static final String MOD_ID = "demonhunter";
	public static ConfigManager<ConfigFile.Equipment> itemConfig = new ConfigManager<>
			("equipment", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	public static ConfigManager<ConfigFile.Shields> shieldConfig = new ConfigManager<>
			("shields", new ConfigFile.Shields())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();
	public static ConfigManager<ConfigFile.Effects> effectsConfig = new ConfigManager<>
			("effects", new ConfigFile.Effects())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	public static ConfigManager<StructurePoolConfig> villageConfig = new ConfigManager<>
			("villages", Default.villageConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

//	public static ConfigManager<TweaksConfig> tweaksConfig = new ConfigManager<>
//			("tweaks", new TweaksConfig())
//			.builder()
//			.setDirectory(ID)
//			.sanitize(true)
//			.build();


	public void onInitialize() {
		itemConfig.refresh();
		shieldConfig.refresh();
		effectsConfig.refresh();
		villageConfig.refresh();
//		DemonhunterItemGroup.register();
//		DemonhunterWeapons.register(WeaponConfigs.WEAPONS); // adjust reference to your actual config
		DemonhunterSounds.register(); // Make sure this is called
		DemonhunterSpellbooks.registerItems(); // ← This ensures the spell book gets registered

//		tweaksConfig.refresh();
//		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
//			tweaksConfig.value.ignore_items_required_mods = true;
//		}


		DemonhunterItemGroup.DEMONHUNTER = FabricItemGroup.builder()
				.icon(() -> new ItemStack(DemonhunterWeapons.fel_warglaive.item()))
				.displayName(Text.translatable("itemGroup.demonhunter.spellbooks"))
				.entries((context, entries) -> {
					DemonhunterWeapons.entries.forEach(entry -> entries.add(entry.item()));
					entries.add(DemonhunterSpellbooks.HAVOC_SPELLBOOK);
					entries.add(DemonhunterSpellbooks.VENGEANCE_SPELLBOOK);
				})
				.build();

		Registry.register(Registries.ITEM_GROUP, DemonhunterItemGroup.KEY, DemonhunterItemGroup.DEMONHUNTER);

//		DemonhunterBlocks.register();
		DemonhunterBooks.register();

//		Weapons.register(itemConfig.value.weapons);
//		Shields.register(shieldConfig.value.shields);
//		Armors.register(itemConfig.value.armor_sets);
		shieldConfig.save();
		itemConfig.save();

		DemonhunterEffects.register(effectsConfig.value);
		effectsConfig.save();

	}

}
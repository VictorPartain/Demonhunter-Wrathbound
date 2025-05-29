package victorstone.demonhunter.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.spell_engine.api.datagen.SimpleSoundGeneratorV2;
import net.spell_engine.api.datagen.SpellGenerator;
import victorstone.demonhunter.demonhunter.DemonhunterSounds;
import victorstone.demonhunter.spells.DemonhunterSpells;

import java.util.concurrent.CompletableFuture;

public class DemonhunterDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(SoundGen::new);
        pack.addProvider(SpellGen::new);
    }

    public static class SoundGen extends SimpleSoundGeneratorV2 {
        public SoundGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(output, registryLookup);
        }

        @Override
        public void generateSounds(Builder builder) {
            builder.entries.add(new Entry("demonhunter",
                    DemonhunterSounds.entries.stream()
                            .map(entry -> SoundEntry.withVariants(entry.id().getPath(), entry.variants()))
                            .toList()
            ));
        }
    }

    public static class SpellGen extends SpellGenerator {
        public SpellGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(output, registryLookup);
        }

        @Override
        public void generateSpells(Builder builder) {
            for (var entry : DemonhunterSpells.entries) {
                builder.add(entry.id(), entry.spell());
            }
        }
    }
}

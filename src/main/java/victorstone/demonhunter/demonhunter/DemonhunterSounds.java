package victorstone.demonhunter.demonhunter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DemonhunterSounds {
    // Step 1: Define identifiers FIRST
    public static final Identifier METAMORPHOSIS_ID =  Identifier.of("demonhunter", "metamorphosis");
    public static final SoundEvent METAMORPHOSIS = SoundEvent.of(METAMORPHOSIS_ID);
    public static Entry metamorphosis;

    // Step 2: Entry wrapper
    public static final class Entry {
        private final Identifier id;
        private final SoundEvent soundEvent;
        private RegistryEntry<SoundEvent> entry;
        private int variants = 1;

        public Entry(Identifier id, SoundEvent soundEvent) {
            this.id = id;
            this.soundEvent = soundEvent;
        }

        public Entry(String name) {
            this( Identifier.of("demonhunter", name), SoundEvent.of(Identifier.of("demonhunter", name)));
        }

        public Entry travelDistance(float distance) {
            return new Entry(id, SoundEvent.of(id, distance));
        }

        public Entry variants(int variants) {
            this.variants = variants;
            return this;
        }

        public Identifier id() {
            return id;
        }

        public SoundEvent soundEvent() {
            return soundEvent;
        }

        public RegistryEntry<SoundEvent> entry() {
            return entry;
        }

        public int variants() {
            return variants;
        }
    }

    // Step 3: Register entries
    public static final List<Entry> entries = new ArrayList<>();
    public static Entry add(Entry entry) {
        entries.add(entry);
        return entry;
    }

    // Step 4: Add metamorphosis to entries list
    public static final Entry METAMORPHOSIS_ENTRY = add(new Entry(METAMORPHOSIS_ID, METAMORPHOSIS));

    // Step 5: Registration
    public static void register() {
        for (var entry : entries) {
            entry.entry = Registry.registerReference(Registries.SOUND_EVENT, entry.id(), entry.soundEvent());
        }
    }

    public static void playSoundEvent(World world, Entity entity, SoundEvent soundEvent) {
        playSoundEvent(world, entity, soundEvent, 1, 1);
    }

    public static void playSoundEvent(World world, Entity entity, SoundEvent soundEvent, float volume, float pitch) {
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }
}

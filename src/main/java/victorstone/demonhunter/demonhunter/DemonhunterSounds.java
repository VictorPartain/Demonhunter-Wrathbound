package victorstone.demonhunter.demonhunter;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DemonhunterSounds {
    public static final class Entry {
        private final Identifier id;
        private final SoundEvent soundEvent;
        private RegistryEntry<SoundEvent> entry;
        private int variants = 1;

        public Entry(Identifier id) {
            this.id = id;
            this.soundEvent = SoundEvent.of(id);
        }

        public Entry(String name) {
            this(Identifier.of("demonhunter", name));
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

        private void setEntry(RegistryEntry<SoundEvent> entry) {
            this.entry = entry;
        }
    }

    public static final List<Entry> entries = new ArrayList<>();

    private static Entry add(Entry entry) {
        entries.add(entry);
        return entry;
    }

    // EXAMPLE sound registration
    public static final Entry METAMORPHOSIS = add(new Entry("metamorphosis"));

    public static void register() {
        for (Entry entry : entries) {
            entry.setEntry(Registry.registerReference(Registries.SOUND_EVENT, entry.id(), entry.soundEvent()));
        }
    }
}

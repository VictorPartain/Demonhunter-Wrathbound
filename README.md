# 🧿 Demon Hunter: Wrathbound

**A Minecraft Fabric mod for 1.21+**  
**Powered by [Spell Engine](https://modrinth.com/mod/spell-engine) · Built with the [[TxniTemplate multiversion template]]([url](https://github.com/txnimc/blahaj))**

---

Unleash vengeance upon the abyss.  
_Demon Hunter: Wrathbound_ transforms Minecraft into a dark fantasy battlefield, giving players the power to become fel-infused warriors who blend melee mastery with deadly magic.

---

## 🔥 Features

### ⚔️ Two Unique Specializations
- **Havoc (DPS):** Agile, fast-paced, spell-enhanced melee combat with burst potential.
- **Vengeance (Tank):** Resilient frontliner with retaliatory spells and defensive tools.

### 🌀 Spell System Integration
- Full support for **Spell Engine**
- Spellcasting through weapon attacks (e.g., Warglaives, Runeblades)
- Attributes like **Fel Power**, **Shadow Power**, and **Flame Power**
- All spells defined in simple, modular JSON format

### 🦅 Movement & Combat Abilities
- Double jumps, air dashes, gliding, and fel leaps
- Spell-infused mobility skills for fast-paced battles
- Aerial finishers and teleportation

### ✨ Visual Effects & Immersion
- Custom particles, animations, and sound effects
- Dark, demonic aesthetics to enhance the fantasy vibe

### 📜 Lore & Progression
- Unlock powers through forbidden scrolls and nether relics
- Customize and enhance your build with unique upgrade paths

### 🛠️ Built for Modders
- Uses the **Blahj template** for multiversion Fabric development
- Easily extendable with new spells, weapons, and mechanics
- Designed to be JSON-driven and developer-friendly

---

## 📦 Dependencies

This mod requires:
- [Spell Engine](https://modrinth.com/mod/spell-engine)
- [Fabric API](https://modrinth.com/mod/fabric-api)
- [Animation API (KosmX)](https://modrinth.com/mod/player-animator)

Be sure to include the correct Maven repositories in your `build.gradle`:
```groovy
maven {
    name = "Modrinth"
    url = "https://api.modrinth.com/maven"
    content {
        includeGroup "maven.modrinth"
    }
}

maven {
    name = "KosmX's maven"
    url = "https://maven.kosmx.dev/"
}


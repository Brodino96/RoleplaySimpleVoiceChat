# Roleplay Simple Voice Chat

![mod_banner](https://imgur.com/eJa97jA.png)

An addon that expands the functionality of Simple Voice Chat to fit immersive roleplay servers and custom narrative universes.

When adding this mod to your project, you are encouraged to edit the locales and assets to perfectly blend with your server's lore!

---

### Features

#### Voice System
- **Voice Range Cycling**: Cycle between customizable voice ranges (whisper, normal, shout) via a keybind.
- **Extended Range**: Reach even further with the Extended range, accessible via the Resonant Shell item or the Extended Speech status effect.
- **Dead Players Use Normal Range**: Dead players always transmit at the normal voice distance, regardless of their selected state.
- **Built in Keybinds Suppressed**: The mod disables Simple Voice Chat's built-in whisper, group, and adjust-volume keybinds in favor of the unified cycling system.

#### Custom GUI
- **State Aware HUD**: Replaces the default Simple Voice Chat HUD icon with a custom 13-sprite atlas that visually reflects the current voice range and state.
- **Icon States**: Each range (whisper, normal, shout, extended) has three sprite variants: **on** (talking), **off** (idle), and **disabled** (muted/dead/under Negated Speech).

#### Settings Integration
- **Cycle Mode Button**: A "Cycle Voice Mode" keybind button is injected directly into the Simple Voice Chat settings screen for easy remapping.

#### Proximity Based Chat
- **Range Linked Text Chat**: The in game text chat follows the current voice range. Players can only read your messages if they are within your active voice distance.

#### Menu Restrictions
- **Group Menu Disabled**: The Simple Voice Chat group menu is greyed out to prevent out of character coordinate sharing and unimmersive communication.
- **Adjust Volume Menu Disabled**: The adjust volume menu is hidden to prevent players from abusing it to see player names.

#### Items
- **Resonant Shell**: A custom Epic rarity item (found in the Tools tab) that forces the Extended voice range while held, allowing your voice to carry over much larger distances. Fireproof, unbreakable, and stack size of 1. Includes customizable tooltip descriptions.

#### Custom Status Effects
| Effect              | Color     | Behavior                                                                                                                                     |
|---------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------------------|
| **Extended Speech** | `#2CE0B3` | Imbues your character with voice enhancement, extending speaking range to the Extended distance. Prevents manual state cycling while active. |
| **Negated Speech**  | `#000000` | Prevents your character from speaking entirely, mutes voice chat and blocks outgoing voice packets server side. Distance is forced to 0.     |

---

### Configuration

The configuration file is auto-created at: `config/roleplaysimplevoicechat.json`

Can be reloaded by using the command `/rpsvc reloadConfig`.  
It is also reloaded automatically whenever a data pack reload (`/reload`) is triggered.

#### Configurable Options:
- `whisperDistance` (Default: `6.0` blocks): The maximum hearing distance for whisper mode.
- `normalDistance` (Default: `12.0` blocks): The maximum hearing distance for normal speaking mode.
- `shoutDistance` (Default: `24.0` blocks): The maximum hearing distance for shout mode.
- `extendedDistance` (Default: `48.0` blocks): The maximum hearing distance when using the Resonant Shell or under the Extended Speech effect.

---

### Customization

Everything in the mod can be customized to fit your specific narrative universe:
- **Locales**: Edit the translation files under `assets/roleplaysimplevoicechat/lang/` to rename items (such as the Resonant Shell), keybinds, and effect names.
- **Assets**: Swap out textures, icons, and models under the `assets/` directory to visually match your server's theme.

---

### Contributors
- **Brodino**: Main developer
- **FapTobi_**: Textures
- **NormanNoone**: Textures

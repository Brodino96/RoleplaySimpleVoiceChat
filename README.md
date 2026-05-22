# Roleplay Simple Voice Chat

![mod_banner](https://imgur.com/eJa97jA.png)

An addon that expands the functionality of Simple Voice Chat to fit immersive roleplay servers and custom narrative universes

When adding this mod to your project, you are encouraged to edit the locales and assets to perfectly blend with your server's lore!

---

### Features

- **Voice Range Cycling**: Cycle between customizable voice ranges (whisper, normal, shout) via a keybind (default: [z]).
- **Proximity Based Chat**: In game text chat follows the current voice chat ranges, meaning players can only read your text messages if they are within your active voice range distance.
- **Group Menu Disabled**: The Simple Voice Chat group menu is disabled to prevent out of character coordinates sharing and unimmersive communication.
- **Adjust Volume Menu Disabled**: The adjust volume menu is disabled avoid players abusing of it to see player names.
- **Resonant Shell**: A custom item that forces a specific voice range (Extended) when held in the hand, allowing your voice to carry over much larger distances.
- **Custom Status Effects**:
  - **Extended Speech**: Imbues your character with voice enhancement, allowing your speaking voice to reach further.
  - **Negated Speech**: Prevents your character from speaking entirely (mutes voice chat while active).

---

### Configuration

You can configure the voice range distances by editing the configuration file located at:
`config/roleplaysimplevoicechat/roleplaysimplevoicechat.json`

#### Configurable Options:
- `whisperDistance` (Default: `6.0` blocks): The maximum hearing distance for whisper mode.
- `normalDistance` (Default: `12.0` blocks): The maximum hearing distance for normal speaking mode.
- `shoutDistance` (Default: `24.0` blocks): The maximum hearing distance for shout mode.
- `extendedDistance` (Default: `48.0` blocks): The maximum hearing distance when using the Resonant Shell or under the Extended Speech effect.

---

### Commands

- `/rpsvc reloadConfig`

---

### Customization

Everything in the mod can be customized to fit your specific narrative universe:
- **Locales**: Edit the translation files under `assets/roleplaysimplevoicechat/lang/` to rename items (such as the Resonant Shell), keybinds, and effect names.
- **Assets**: Swap out textures and models under the assets directory to visual match your server's theme.

---

### Contributors
- **Brodino**: Main developer
- **FapTobi_**: Graphics
- **NormanNoone**: Textures

For installation and advanced instructions, see the [Documentation](https://docs.brodino.dev/minecraft/roleplaysimplevoicechat).

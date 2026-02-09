# MobKiler

A Minecraft Paper plugin that launches monsters into the air and explodes them upon spawning.

## Features

- Automatically detects monster spawns (Zombies, Skeletons, Creepers, etc.)
- Launches monsters into the air after 4 seconds
- Creates a TNT-like explosion after the launch
- Lightweight and efficient using Bukkit's scheduler
- No configuration needed - works out of the box

## Requirements

- Minecraft Server: Paper 1.21+
- Java: 21 or higher

## Installation

1. Download the latest release from [Releases](../../releases)
2. Place `mobkiler-x.x.jar` in your server's `plugins` folder
3. Restart your server
4. Enjoy the chaos!

## How It Works

```
Monster Spawns -> Wait 4 seconds -> Launch to sky -> Wait 1 second -> BOOM!
```

1. Plugin listens for `CreatureSpawnEvent`
2. When a `Monster` entity spawns, it's stored in a HashMap
3. After 80 ticks (4 seconds), the mob's Y velocity is increased
4. After 20 more ticks (1 second), the mob is removed and an explosion is created

## Building from Source

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/mobkiler.git
cd mobkiler

# Build the plugin
./gradlew build

# The JAR file will be in build/libs/
```

## Configuration

Currently, the plugin has no configuration. Future versions may include:

- Explosion power customization
- Delay timing settings
- Mob type whitelist/blacklist
- Toggle fire/block damage from explosions

## Technical Details

| Parameter | Value | Description |
|-----------|-------|-------------|
| Launch Delay | 80 ticks (4s) | Time before launching mob |
| Explosion Delay | 20 ticks (1s) | Time after launch before explosion |
| Explosion Power | 4.0 | Same as TNT |
| Set Fire | false | Explosion doesn't create fire |
| Break Blocks | false | Explosion doesn't destroy blocks |

## API Version

This plugin is built for Paper API `1.21` and uses modern Java 21 features.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## Author

Made with fun for Minecraft servers!

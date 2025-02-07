# Space Update  
**This mod is not yet complete! Expected to release on the 1st of April 2025.**  


## Changes  
- Updated Menu  
- Added Bloom  
- Added Space Station Dimension  
- Added Moon Dimension  
- Added Breath of the Cosmos, and Heavy Footed enchantments  
- Added Moon Stone  
- Added Moon Brick, Moon Bricks, Cracked Moon Bricks, Moon Brick Stairs, Moon Brick Slab, Moon Brick Wall, and Chiseled Moon Bricks  
- Added Crystal Ore, Crystal, Crystal Block, Crystal Stairs, Crystal Slab, Crystal Wall, Crystal Glass, Crystal Glass Pane, and Crystal Wings  
- Added Pulser Block  
- Added Steve Co. Supply Crate  
- Added Moon Slime, Moon Slime Spawn Egg, and Moon Slime Ball  
- Added Bright Day Music Disc  
- Added Moon, and Sun Painting
- Added Space Air  
- Removed Herobrine  

### Menu  
- Updated button textures to be space themed  
- Updated the title screen panorama with several space themed panoramas  
- Updated the title screen music with the following:  
  - `dtaf2025:music/devoid`  
  - `dtaf2025:music/cosmic`  
  - `dtaf2025:music/light`  
  - `dtaf2025:music/aware`  
  - `dtaf2025:music/dark`  
- Replaced box blur shader with gaussian blur shader  

### Post Effects  
#### Gaussian Menu Blur  
- This post effect replaces the menu blur shader  
- The post effect is located at: `assets/dtaf2025/post_effect/gaussian`  
#### Bloom  
- This post effect will always render  
- The post effect is located at: `assets/dtaf2025/post_effect/bloom.json`  
#### Space  
- This post effect will render when you are in a `#dtaf2025:space` biome  
- The post effect is located at: `assets/dtaf2025/post_effect/space.json`  

### Dimensions  
#### Space Station  
- Entities that are 96 blocks above the overworld height limit will be teleported to the bottom of the space station dimension  
- Entities that are 16 blocks below the space station height limit will be teleported to 48 blocks above overworld height limit  
  - If the entity doesn't have slow falling, they will be set on fire for 32 seconds (or until they are in water).  
- Generates a space station at `0, 64, 0` on first world generation.  
- You can't breathe here without the `Breath of the Cosmos` enchantment  
- The only biome that generates here is `dtaf2025:space`  
- Gravity is 0.16x that of the overworld, unless wearing boots with the Heavy Footed enchantment  
#### The Moon  
- Entities that are 96 blocks above the space station height limit will be teleported to 48 blocks above the moon height limit  
- Entities that are 96 blocks above the moon height limit will be teleported to 48 blocks above the space station height limit  
- The only biome that generates here is `dtaf2025:the_moon`  
- Gravity is 0.16x that of the overworld, unless wearing boots with the Heavy Footed enchantment  
### Biomes  
#### Space  
- Nothing generates here, it's a void biome.   
- You can't breathe here without the `Breath of the Cosmos` enchantment  
- The following music is played in this biome:
  - `dtaf2025:music/devoid`  
  - `dtaf2025:music/cosmic`  
  - `dtaf2025:music/light`  
  - `dtaf2025:music/aware`  
  - `dtaf2025:music/dark`  
#### The Moon  
- This dimension is made of Moon Stone, with Crystal Ore, Craters, Moon Rovers, and Moon Slime.  
- You can't breathe here without the `Breath of the Cosmos` enchantment  
- The following music is played in this biome:  
  - `dtaf2025:music/devoid`  
  - `dtaf2025:music/cosmic`  
  - `dtaf2025:music/light`  
  - `dtaf2025:music/aware`  
  - `dtaf2025:music/dark`  

### Enchantments  
#### Breath of the Cosmos  
- When worn on head, the entity can breathe in `#dtaf2025:no_oxygen` biomes, without space air  
#### Heavy Footed  
- When worn on feet, gravity returns to normal in space dimensions  
- If worn in a dimension that has normal gravity, gravity becomes 1.94x that of normal  

### Moon Stone  
- Generates in `dtaf2025:the_moon` biome, as the moon equivalent of stone  
- It can be smelted into Moon Brick, which can be crafted into its block variants  

### Crystal  
- Can be found in ore form in `dtaf2025:the_moon` biome  
- Can be used to craft Crystal Blocks, Crystal Stairs, Crystal Slab, Crystal Wall, Crystal Glass, Crystal Glass Pane, Crystal Wings, Pulser, and Steve Co. Supply Crates  
#### Crystal Wings  
- This can be crafted using 1 crystal, and 4 phantom membranes  
#### Pulser
#### Steve Co. Supply Crate  
- This can be crafted using a crystal, and a chest  
- Opens a screen that tells the player to get a Supply Crate Key from the Minecraft Store  
  - When the player presses that button, the official [Minecraft Shop](https://shop.minecraft.net/) is opened in their browser, this feature is meant as a joke  
##### Developer's Note  
We took the "Steve Co. Supply Crates" (also known as "Locked Chests") from Minecraft Beta 1.4,  
and made them link to the actual minecraft shop. See [here](https://minecraft.wiki/w/Locked_chest) for more info on the original!  
This mod contains a reference to "Supply Crates" (a.k.a. "Loot Boxes", "Crates", "Gambling"), which we do not support, nor do we endorse the use of these for exchange of real-world currency. These are predatory practices designed to cause harm, and we encourage you to avoid them.  
This reference was intended to make fun of companies that use these predatory practices.  

### Moon Slime  
#### Mob  
- Moon Slimes look similar to their overworld counterparts, but are blue instead of green  
- Moon Slimes have twice the health, movement speed, and attack damage compared to their overworld counterparts  
- Moon Slimes will attack and convert overworld slimes if they are to ever be near each other  
#### Moon Slime Ball
- Unlike overworld and nether slimes, Moon Slimes drop Moon Slime Balls which can be consumed quickly  

### Bright Day Music Disc  
- Found in Space Station and Moon loot chests  
- Plays [dannytaylor - Bright Day](https://www.youtube.com/watch?v=lsb9qSkNLVE) when inserted into a jukebox  
- Has a comparator output of 10  

### Paintings  
#### The Moon  
- 1x1 painting of the moon  
#### The Sun  
- 2x2 painting of the sun from the moon  

### Space Air  
- Entities that are in `#dtaf2025:no_oxygen` biomes can breathe when in or next to space air  


## Technical Changes  
- Celestial objects can now be data-driven  
- The menu panorama can now be data-driven, and will be randomized on resource reload  
- Splash Texts are now translatable  
- Added `dtaf2025:request` C2S and S2C packets  
- Added `dtaf2025:is_sanic` S2C packet  
- Added `dtaf2025:open_screen` S2C packet  
- Added `dtaf2025:space_breathing` enchantment effect  
- Added `dtaf2025:heavy_footed` enchantment effect  
- Paintings and Enchantments with the `#dtaf2025:dtaf2025` tag will be added to the Space Update item group  

### Celestial Objects  
- Celestial objects can be data-driven by adding entries to `assets/<namespace>/sky/<id>.json`  
- Fields in file:  
  - `dimensions` - array of dimension objects  
    - dimension object:
      - `id` - namespaced id of the dimension  
      - `position` - array of position objects  
        - position object:  
          - `type` - string of `x`, `y`, or `z`  
          - `value` - float, how much to move in the type direction, `value * (skyAngle * skyAngleMultiplier)`  
          - `skyAngleMultiplier` - integer  
      - `scale` - float, scale of object  
      - `visible` - string of `day`, `night`, or `always`, when to render  
      - `phases` - positive integer, amount of phases  
      - `phaseOffset` - integer, offset of current phase  
- The texture asset resolves to `assets/<namespace>/textures/sky/<id>.png`  

### Panorama  
- Panoramas can be data-driven by adding entries to `assets/<namespace>/panorama/<id>.json`  
- Fields in file:  
  - `asset_id` - namespaced id for the panorama assets, resolves to `assets/<namespace>/textures/<path>/panorama_(0-5/overlay).png`  
- A random panorama is selected on resource reload  
  - If no panoramas are registered, the default panorama is used  

### Splash Texts  
- Splash texts can be data-driven by adding entries to `assets/<namespace>/splashes.json`  
- Fields in file:  
  - `replace` - boolean, should replace all currently registered splash texts  
  - `translatable` - list of translation keys strings  
  - `literal` - list of strings, these will not be translated  
- The splash text will be randomized on resource reload  

### Networking  
#### Client → Server (C2S)  
- Added `dtaf2025:request`  
  - Sends an identifier to the server  
  - The server can return the requested data  
    - Currently `dtaf2025:is_sanic` is the only valid request  
#### Server → Client (S2C)  
- Added `dtaf2025:request`  
  - Sends an identifier to the client  
  - The client can return the requested data  
    - This is currently not used by anything  
- Added `dtaf2025:is_sanic`  
  - Send a boolean to the client if the seed matches the SANIC easter egg  
- Added `dtaf2025:open_screen`  
  - Sends an identifier to the client requesting for that screen to open.  
    - Currently `dtaf2025:locked_chest` is the only valid screen  

### Commands  
- Added `dtaf2025:dimension`  
#### `dtaf2025:dimension` Command  
- This command makes it easier to teleport to a dimension.  
  - This doesn't add anything you couldn't already do, but just makes it easier.  
##### Syntax  
`dtaf2025:dimension <dimension> [<targets>]`
##### Parameters  
- `dimension`: The dimension that you want the entity to teleport to.  
- `targets`: (Optional) The entities you want to teleport, defaults to the entity that ran the command.    

### Tags  
#### Block Tags
- Added `#dtaf2025:moon_ore_replaceables` for blocks that moon ores can replace when generating  
- Added `#dtaf2025:space_air` for blocks that restore air when within the block  
- Added `#dtaf2025:crystal_blocks`  
- Added `#dtaf2025:moon_stone_blocks`  
#### Enchantment Tags  
- Added `#dtaf2025:dtaf2025` for enchantments added by Space Update  
#### Entity Type Tags  
- Added `#dtaf2025:can_breathe_in_space` for entities that can breathe when in a biome without oxygen  
#### Painting Variant Tags  
- Added `#dtaf2025:dtaf2025` for paintings added by Space Update  
#### Biome Tags  
- Added `#dtaf2025:no_oxygen` for biomes that don't have oxygen  
- Added `#dtaf2025:space` for biomes that are in space  
#### Item Tags  
- Added `#dtaf2025:crystal`  
- Added `#dtaf2025:moon_brick`  
- Added `#dtaf2025:crystal_blocks`  
- Added `#dtaf2025:moon_stone_blocks`  


## Easter Eggs  
### SANIC  
This easter egg will be enabled when your seed is set to `SANIC`  
#### Attributes  
- Doubles the player's gravity, jump velocity, and movement speed  
#### Post Effect  
- This post effect will render when you are sprinting  
- The post effect is located at: `assets/dtaf2025/post_effect/sanic.json`  


## Attributions  
- [Emafire003/StructurePlacerAPI](https://github.com/Emafire003/StructurePlacerAPI)  
  - We forked StructurePlacerAPI for use of structure generation (specifically for the space station).  
- [Fabric Loader](https://fabricmc.net)  
- [FabricAPI](https://modrinth.com/mod/fabric-api)  
- [Minecraft](https://minecraft.net/)  

##  
Licensed under LGPL-3.0-or-later.  

**This mod is not affiliated with/endorsed by Mojang Studios, Microsoft, Snow Commerce, or The Official Minecraft Shop.**  
The Minecraft Logo, name, and some game assets are property of Mojang Studios and fall under Minecraft EULA.  
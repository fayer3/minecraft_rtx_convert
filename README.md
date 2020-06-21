# minecraft_rtx_convert
Converts Minecraft java resourcepacks to Bedrock Minecraft

supports java resourcepacks of version 1.13 and above, 1.16 currently not included.
supports compressed resourcepack .zip files and extracted resourcepacks
also supports conversion of textures for java Minecraft shaders to the Bedrock RTX format.

## requirements to run
requires Java with javafx support to run

## usage
select the resourcepack to convert with the corresponding button **choose Folder** or **choose zip** or **drag and drop** the resourcepack into the window.

with the button **conversion settings** it is possible to change some general settings for the conversion.
especially you can disable leather horsearmor conversion, if it is buggy.
you can disable conversion of any texture by adding the full path to the **Textures to exclude** table. For example if you would like to exclude the dirt texture you would add 'assets/minecraft/textures/block/dirt.png'.

it is also possible to disable mipmaps by setting them to 0.

to enable RTX support for the final resourcepack check [x] the option **enable raytracing**

if your java resourcepack doues not use the lab pbr standard you can customize the conversion with the **customize** button

if you are don with the setup press **convert** to convert the the resourcepack.
when complete there should be a new file in the same directory as the source resourcepack with the extension **.mcpack**

## Dependecies for building: 
* [Google gson](https://github.com/google/gson) for json support
* [Harald Kuhrs TwelveMonkeys ImageIO](https://github.com/haraldk/TwelveMonkeys) for tga support
* Javafx

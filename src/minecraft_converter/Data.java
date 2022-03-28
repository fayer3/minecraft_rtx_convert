package minecraft_converter;

import java.util.*;

public class Data {
    public static final Map<String, String> textures;
    public static final Map<String, String> texturesMode;
    private static Map<String, String> texturesModeVersionspecific;
    private static final Map<String, String> optionsDefault;
    public static Map<String, Boolean> optionDone;
    public static Map<String, String> bannerOffset;
    public static Map<String, String> particlesOffset;
    public static Map<String, String> paintingOffset;
    //public static Map<String, String> options;

    // Mob effect colors from java
    // absorption = 2445989
    // bad_omen = 745784
    // blindness = 2039587
    // conduit_power = 1950417
    // dolphins_grace = 8954814
    // fire_resistance = 14981690
    // glowing = 9740385
    // haste = 14270531
    // health_boost = 16284963
    // hero_of_the_village = 4521796
    // hunger = 5797459
    // instant_damage = 4393481
    // instant_health = 16262179
    // invisibility = 8356754
    // jump_boost = 2293580
    // levitation = 13565951
    // luck = 3381504
    // nausea = 5578058
    // night_vision = 2039713
    // mining_fatigue = 4866583
    // poison = 5149489
    // regeneration = 13458603
    // resistance = 10044730
    // saturation = 16262179
    // slow_falling = 16773073
    // slowness = 5926017
    // speed = 8171462
    // strength = 9643043
    // turtle_master = 4*slowness+3*resistance / 7 = 7691106
    // unluck = 12624973
    // water_breathing = 3035801
    // weakness = 4738376
    // wither = 3484199
    //
    // water = 3694022

    static {
        Map<String, String> tempBlocks = new HashMap<>();
        Map<String, String> tempOptions = new HashMap<>();
        optionDone = new HashMap<>();
        //options = new HashMap<>();
        optionsDefault = new HashMap<>();
        texturesModeVersionspecific = new HashMap<>();

        bannerOffset = new HashMap<>();
        particlesOffset = new HashMap<>();
        paintingOffset = new HashMap<>();

        // banner
        // needs restucture, add all banner files to banner atlas
        // not needed any more since 1.16
        /*tempBlocks.put("assets/minecraft/textures/entity/banner_base.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner_base.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner_base.png", "0,0");
        tempBlocks.put("assets/minecraft/textures/entity/banner/base.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/base.png", "function[banner]");
        //bannerOffset.put("assets/minecraft/textures/entity/banner/base.png", ",");
        tempBlocks.put("assets/minecraft/textures/entity/banner/border.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/border.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/border.png", "0,1");
        tempBlocks.put("assets/minecraft/textures/entity/banner/bricks.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/bricks.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/bricks.png", "0,2");
        tempBlocks.put("assets/minecraft/textures/entity/banner/circle.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/circle.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/circle.png", "0,3");
        tempBlocks.put("assets/minecraft/textures/entity/banner/creeper.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/creeper.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/creeper.png", "0,4");
        tempBlocks.put("assets/minecraft/textures/entity/banner/cross.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/cross.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/cross.png", "0,5");
        tempBlocks.put("assets/minecraft/textures/entity/banner/curly_border.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/curly_border.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/curly_border.png", "0,6");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_left.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/diagonal_left.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/diagonal_left.png", "0,7");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/diagonal_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/diagonal_right.png", "1,0");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_up_left.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/diagonal_up_left.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/diagonal_up_left.png", "1,1");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_up_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/diagonal_up_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/diagonal_up_right.png", "1,2");
        tempBlocks.put("assets/minecraft/textures/entity/banner/flower.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/flower.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/flower.png", "1,3");
        tempBlocks.put("assets/minecraft/textures/entity/banner/globe.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/globe.png", "function[banner]");
        //bannerOffset.put("assets/minecraft/textures/entity/banner/globe.png", ",");
        tempBlocks.put("assets/minecraft/textures/entity/banner/gradient.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/gradient.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/gradient.png", "1,4");
        tempBlocks.put("assets/minecraft/textures/entity/banner/gradient_up.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/gradient_up.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/gradient_up.png", "1,5");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_horizontal.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/half_horizontal.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/half_horizontal.png", "1,6");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_horizontal_bottom.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/half_horizontal_bottom.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/half_horizontal_bottom.png", "1,7");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_vertical.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/half_vertical.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/half_vertical.png", "2,0");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_vertical_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/half_vertical_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/half_vertical_right.png", "2,1");
        tempBlocks.put("assets/minecraft/textures/entity/banner/mojang.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/mojang.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/mojang.png", "2,2");
        tempBlocks.put("assets/minecraft/textures/entity/banner/rhombus.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/rhombus.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/rhombus.png", "2,3");
        tempBlocks.put("assets/minecraft/textures/entity/banner/skull.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/skull.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/skull.png", "2,4");
        tempBlocks.put("assets/minecraft/textures/entity/banner/small_stripes.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/small_stripes.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/small_stripes.png", "2,5");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_bottom_left.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/square_bottom_left.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/square_bottom_left.png", "2,6");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_bottom_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/square_bottom_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/square_bottom_right.png", "2,7");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_top_left.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/square_top_left.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/square_top_left.png", "3,0");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_top_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/square_top_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/square_top_right.png", "3,1");
        tempBlocks.put("assets/minecraft/textures/entity/banner/straight_cross.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/straight_cross.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/straight_cross.png", "3,2");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_bottom.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_bottom.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_bottom.png", "3,3");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_center.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_center.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_center.png", "3,4");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_downleft.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_downleft.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_downleft.png", "3,5");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_downright.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_downright.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_downright.png", "3,6");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_left.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_left.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_left.png", "3,7");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_middle.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_middle.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_middle.png", "4,0");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_right.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_right.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_right.png", "4,1");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_top.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/stripe_top.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/stripe_top.png", "4,2");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangles_bottom.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/triangles_bottom.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/triangles_bottom.png", "4,5");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangles_top.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/triangles_top.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/triangles_top.png", "4,6");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangle_bottom.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/triangle_bottom.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/triangle_bottom.png", "4,3");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangle_top.png", "");
        tempOptions.put("assets/minecraft/textures/entity/banner/triangle_top.png", "function[banner]");
        bannerOffset.put("assets/minecraft/textures/entity/banner/triangle_top.png", "4,4");*/

        tempBlocks.put("assets/minecraft/textures/block/acacia_door_bottom.png", "textures/blocks/door_acacia_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_door_top.png", "textures/blocks/door_acacia_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_leaves.png", "textures/blocks/leaves_acacia.tga;textures/blocks/leaves_acacia_carried.tga;textures/blocks/leaves_acacia_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/acacia_leaves.png", "copy;color[181,181,181:51,133,15];fill[color=108,105,105,255]");
        tempBlocks.put("assets/minecraft/textures/block/acacia_log.png", "textures/blocks/log_acacia.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_log_top.png", "textures/blocks/log_acacia_top.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_planks.png", "textures/blocks/planks_acacia.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_sapling.png", "textures/blocks/sapling_acacia.png");
        tempBlocks.put("assets/minecraft/textures/block/acacia_trapdoor.png", "textures/blocks/acacia_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/activator_rail.png", "textures/blocks/rail_activator.png");
        tempBlocks.put("assets/minecraft/textures/block/activator_rail_on.png", "textures/blocks/rail_activator_powered.png");
        tempBlocks.put("assets/minecraft/textures/block/allium.png", "textures/blocks/flower_allium.png");
        tempBlocks.put("assets/minecraft/textures/block/andesite.png", "textures/blocks/stone_andesite.png");
        tempBlocks.put("assets/minecraft/textures/block/anvil.png", "textures/blocks/anvil_base.png");
        tempBlocks.put("assets/minecraft/textures/block/anvil_top.png", "textures/blocks/anvil_top_damaged_0.png");
        tempBlocks.put("assets/minecraft/textures/block/attached_melon_stem.png", "textures/blocks/melon_stem_connected.png");
        tempBlocks.put("assets/minecraft/textures/block/attached_pumpkin_stem.png", "textures/blocks/pumpkin_stem_connected.png");
        tempBlocks.put("assets/minecraft/textures/block/azure_bluet.png", "textures/blocks/flower_houstonia.png");
        tempBlocks.put("assets/minecraft/textures/block/bamboo_large_leaves.png", "textures/blocks/bamboo_leaf.png");
        tempBlocks.put("assets/minecraft/textures/block/bamboo_singleleaf.png", "textures/blocks/bamboo_singleleaf.png");
        tempBlocks.put("assets/minecraft/textures/block/bamboo_small_leaves.png", "textures/blocks/bamboo_small_leaf.png");
        tempBlocks.put("assets/minecraft/textures/block/bamboo_stage0.png", "textures/blocks/bamboo_sapling.png");
        tempBlocks.put("assets/minecraft/textures/block/bamboo_stalk.png", "textures/blocks/bamboo_stem.png");
        tempBlocks.put("assets/minecraft/textures/block/barrel_bottom.png", "textures/blocks/barrel_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/barrel_side.png", "textures/blocks/barrel_side.png");
        tempBlocks.put("assets/minecraft/textures/block/barrel_top.png", "textures/blocks/barrel_top.png");
        tempBlocks.put("assets/minecraft/textures/block/barrel_top_open.png", "textures/blocks/barrel_top_open.png");
        tempBlocks.put("assets/minecraft/textures/block/beacon.png", "textures/blocks/beacon.png");
        tempBlocks.put("assets/minecraft/textures/block/bedrock.png", "textures/blocks/bedrock.png");
        tempBlocks.put("assets/minecraft/textures/block/beehive_end.png", "textures/blocks/beehive_top.png");
        tempBlocks.put("assets/minecraft/textures/block/beehive_front.png", "textures/blocks/beehive_front.png");
        tempBlocks.put("assets/minecraft/textures/block/beehive_front_honey.png", "textures/blocks/beehive_front_honey.png");
        tempBlocks.put("assets/minecraft/textures/block/beehive_side.png", "textures/blocks/beehive_side.png");
        tempBlocks.put("assets/minecraft/textures/block/beetroots_stage0.png", "textures/blocks/beetroots_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/beetroots_stage1.png", "textures/blocks/beetroots_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/beetroots_stage2.png", "textures/blocks/beetroots_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/beetroots_stage3.png", "textures/blocks/beetroots_stage_3.png");
        tempBlocks.put("assets/minecraft/textures/block/bee_nest_bottom.png", "textures/blocks/bee_nest_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/bee_nest_front.png", "textures/blocks/bee_nest_front.png");
        tempBlocks.put("assets/minecraft/textures/block/bee_nest_front_honey.png", "textures/blocks/bee_nest_front_honey.png");
        tempBlocks.put("assets/minecraft/textures/block/bee_nest_side.png", "textures/blocks/bee_nest_side.png");
        tempBlocks.put("assets/minecraft/textures/block/bee_nest_top.png", "textures/blocks/bee_nest_top.png");
        tempBlocks.put("assets/minecraft/textures/block/bell_bottom.png", "textures/blocks/bell_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/bell_side.png", "textures/blocks/bell_side.png");
        tempBlocks.put("assets/minecraft/textures/block/bell_top.png", "textures/blocks/bell_top.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_door_bottom.png", "textures/blocks/door_birch_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_door_top.png", "textures/blocks/door_birch_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_leaves.png", "textures/blocks/leaves_birch.tga;textures/blocks/leaves_birch_carried.tga;textures/blocks/leaves_birch_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/birch_leaves.png", "copy;color[183,185,183:50,131,15];fill[color=111,108,111,255]");
        tempBlocks.put("assets/minecraft/textures/block/birch_log.png", "textures/blocks/log_birch.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_log_top.png", "textures/blocks/log_birch_top.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_planks.png", "textures/blocks/planks_birch.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_sapling.png", "textures/blocks/sapling_birch.png");
        tempBlocks.put("assets/minecraft/textures/block/birch_trapdoor.png", "textures/blocks/birch_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/black_concrete.png", "textures/blocks/concrete_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_concrete_powder.png", "textures/blocks/concrete_powder_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_glazed_terracotta.png", "textures/blocks/glazed_terracotta_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_shulker_box.png", "textures/blocks/shulker_top_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_stained_glass.png", "textures/blocks/glass_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_terracotta.png", "textures/blocks/hardened_clay_stained_black.png");
        tempBlocks.put("assets/minecraft/textures/block/black_wool.png", "textures/blocks/wool_colored_black.png");
        tempBlocks.put("assets/minecraft/textures/block/blast_furnace_front.png", "textures/blocks/blast_furnace_front_off.png");
        tempBlocks.put("assets/minecraft/textures/block/blast_furnace_front_on.png", "textures/blocks/blast_furnace_front_on.png");
        tempBlocks.put("assets/minecraft/textures/block/blast_furnace_side.png", "textures/blocks/blast_furnace_side.png");
        tempBlocks.put("assets/minecraft/textures/block/blast_furnace_top.png", "textures/blocks/blast_furnace_top.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_concrete.png", "textures/blocks/concrete_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_concrete_powder.png", "textures/blocks/concrete_powder_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_glazed_terracotta.png", "textures/blocks/glazed_terracotta_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_ice.png", "textures/blocks/blue_ice.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_orchid.png", "textures/blocks/flower_blue_orchid.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_shulker_box.png", "textures/blocks/shulker_top_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_stained_glass.png", "textures/blocks/glass_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_terracotta.png", "textures/blocks/hardened_clay_stained_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_wool.png", "textures/blocks/wool_colored_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/bone_block_side.png", "textures/blocks/bone_block_side.png");
        tempBlocks.put("assets/minecraft/textures/block/bone_block_top.png", "textures/blocks/bone_block_top.png");
        tempBlocks.put("assets/minecraft/textures/block/bookshelf.png", "textures/blocks/bookshelf.png");
        tempBlocks.put("assets/minecraft/textures/block/brain_coral.png", "textures/blocks/coral_plant_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/brain_coral_block.png", "textures/blocks/coral_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/brain_coral_fan.png", "textures/blocks/coral_fan_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/brewing_stand.png", "textures/blocks/brewing_stand.png");
        tempBlocks.put("assets/minecraft/textures/block/brewing_stand_base.png", "textures/blocks/brewing_stand_base.png");
        tempBlocks.put("assets/minecraft/textures/block/bricks.png", "textures/blocks/brick.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_concrete.png", "textures/blocks/concrete_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_concrete_powder.png", "textures/blocks/concrete_powder_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_glazed_terracotta.png", "textures/blocks/glazed_terracotta_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_mushroom.png", "textures/blocks/mushroom_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_mushroom_block.png", "textures/blocks/mushroom_block_skin_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_shulker_box.png", "textures/blocks/shulker_top_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_stained_glass.png", "textures/blocks/glass_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_terracotta.png", "textures/blocks/hardened_clay_stained_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_wool.png", "textures/blocks/wool_colored_brown.png");
        tempBlocks.put("assets/minecraft/textures/block/bubble_coral.png", "textures/blocks/coral_plant_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/bubble_coral_block.png", "textures/blocks/coral_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/bubble_coral_fan.png", "textures/blocks/coral_fan_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/cactus_bottom.png", "textures/blocks/cactus_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/block/cactus_side.png", "textures/blocks/cactus_side.tga");
        tempBlocks.put("assets/minecraft/textures/block/cactus_top.png", "textures/blocks/cactus_top.tga");
        tempBlocks.put("assets/minecraft/textures/block/cake_bottom.png", "textures/blocks/cake_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/cake_inner.png", "textures/blocks/cake_inner.png");
        tempBlocks.put("assets/minecraft/textures/block/cake_side.png", "textures/blocks/cake_side.png");
        tempBlocks.put("assets/minecraft/textures/block/cake_top.png", "textures/blocks/cake_top.png");
        tempBlocks.put("assets/minecraft/textures/block/campfire_fire.png", "textures/blocks/campfire.png");
        tempBlocks.put("assets/minecraft/textures/block/campfire_log.png", "textures/blocks/campfire_log.png");
        tempBlocks.put("assets/minecraft/textures/block/campfire_log_lit.png", "textures/blocks/campfire_log_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/carrots_stage0.png", "textures/blocks/carrots_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/carrots_stage1.png", "textures/blocks/carrots_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/carrots_stage2.png", "textures/blocks/carrots_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/carrots_stage3.png", "textures/blocks/carrots_stage_3.png");
        tempBlocks.put("assets/minecraft/textures/block/cartography_table_side1.png", "textures/blocks/cartography_table_side1.png");
        tempBlocks.put("assets/minecraft/textures/block/cartography_table_side2.png", "textures/blocks/cartography_table_side2.png");
        tempBlocks.put("assets/minecraft/textures/block/cartography_table_side3.png", "textures/blocks/cartography_table_side3.png");
        tempBlocks.put("assets/minecraft/textures/block/cartography_table_top.png", "textures/blocks/cartography_table_top.png");
        tempBlocks.put("assets/minecraft/textures/block/carved_pumpkin.png", "textures/blocks/pumpkin_face_off.png");
        tempBlocks.put("assets/minecraft/textures/block/cauldron_bottom.png", "textures/blocks/cauldron_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/cauldron_inner.png", "textures/blocks/cauldron_inner.png");
        tempBlocks.put("assets/minecraft/textures/block/cauldron_side.png", "textures/blocks/cauldron_side.png");
        tempBlocks.put("assets/minecraft/textures/block/cauldron_top.png", "textures/blocks/cauldron_top.png");
        tempOptions.put("assets/minecraft/textures/block/cauldron_top.png", "copy[alphaColor=0,0,0]");
        tempBlocks.put("assets/minecraft/textures/block/chain_command_block_back.png", "textures/blocks/chain_command_block_back.png;textures/blocks/chain_command_block_back_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/chain_command_block_back.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/chain_command_block_conditional.png", "textures/blocks/chain_command_block_conditional.png;textures/blocks/chain_command_block_conditional_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/chain_command_block_conditional.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/chain_command_block_front.png", "textures/blocks/chain_command_block_front.png;textures/blocks/chain_command_block_front_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/chain_command_block_front.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/chain_command_block_side.png", "textures/blocks/chain_command_block_side.png;textures/blocks/chain_command_block_side_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/chain_command_block_side.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/chipped_anvil_top.png", "textures/blocks/anvil_top_damaged_1.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_quartz_block.png", "textures/blocks/quartz_block_chiseled.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_quartz_block_top.png", "textures/blocks/quartz_block_chiseled_top.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_red_sandstone.png", "textures/blocks/red_sandstone_carved.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_sandstone.png", "textures/blocks/sandstone_carved.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_stone_bricks.png", "textures/blocks/stonebrick_carved.png");
        tempBlocks.put("assets/minecraft/textures/block/chorus_flower.png", "textures/blocks/chorus_flower.png");
        tempBlocks.put("assets/minecraft/textures/block/chorus_flower_dead.png", "textures/blocks/chorus_flower_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/chorus_plant.png", "textures/blocks/chorus_plant.png");
        tempBlocks.put("assets/minecraft/textures/block/clay.png", "textures/blocks/clay.png");
        tempBlocks.put("assets/minecraft/textures/block/coal_block.png", "textures/blocks/coal_block.png");
        tempBlocks.put("assets/minecraft/textures/block/coal_ore.png", "textures/blocks/coal_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/coarse_dirt.png", "textures/blocks/coarse_dirt.png");
        tempBlocks.put("assets/minecraft/textures/block/cobblestone.png", "textures/blocks/cobblestone.png");
        tempBlocks.put("assets/minecraft/textures/block/cobweb.png", "textures/blocks/web.png");
        tempBlocks.put("assets/minecraft/textures/block/cocoa_stage0.png", "textures/blocks/cocoa_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/cocoa_stage1.png", "textures/blocks/cocoa_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/cocoa_stage2.png", "textures/blocks/cocoa_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/command_block_back.png", "textures/blocks/command_block_back.png;textures/blocks/command_block_back_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/command_block_back.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/command_block_conditional.png", "textures/blocks/command_block_conditional.png;textures/blocks/command_block_conditional_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/command_block_conditional.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/command_block_front.png", "textures/blocks/command_block_front.png;textures/blocks/command_block_front_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/command_block_front.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/command_block_side.png", "textures/blocks/command_block_side.png;textures/blocks/command_block_side_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/command_block_side.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/comparator.png", "textures/blocks/comparator_off.png");
        tempBlocks.put("assets/minecraft/textures/block/comparator_on.png", "textures/blocks/comparator_on.png");
        tempBlocks.put("assets/minecraft/textures/block/composter_bottom.png", "textures/blocks/composter_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/composter_compost.png", "textures/blocks/compost.png");
        tempBlocks.put("assets/minecraft/textures/block/composter_ready.png", "textures/blocks/compost_ready.png");
        tempBlocks.put("assets/minecraft/textures/block/composter_side.png", "textures/blocks/composter_side.png");
        tempBlocks.put("assets/minecraft/textures/block/composter_top.png", "textures/blocks/composter_top.png");
        tempBlocks.put("assets/minecraft/textures/block/conduit.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/block/cornflower.png", "textures/blocks/flower_cornflower.png");
        tempBlocks.put("assets/minecraft/textures/block/cracked_stone_bricks.png", "textures/blocks/stonebrick_cracked.png");
        tempBlocks.put("assets/minecraft/textures/block/crafting_table_front.png", "textures/blocks/crafting_table_front.png");
        tempBlocks.put("assets/minecraft/textures/block/crafting_table_side.png", "textures/blocks/crafting_table_side.png");
        tempBlocks.put("assets/minecraft/textures/block/crafting_table_top.png", "textures/blocks/crafting_table_top.png");
        tempBlocks.put("assets/minecraft/textures/block/cut_red_sandstone.png", "textures/blocks/red_sandstone_smooth.png");
        tempBlocks.put("assets/minecraft/textures/block/cut_sandstone.png", "textures/blocks/sandstone_smooth.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_concrete.png", "textures/blocks/concrete_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_concrete_powder.png", "textures/blocks/concrete_powder_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_glazed_terracotta.png", "textures/blocks/glazed_terracotta_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_shulker_box.png", "textures/blocks/shulker_top_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_stained_glass.png", "textures/blocks/glass_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_terracotta.png", "textures/blocks/hardened_clay_stained_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_wool.png", "textures/blocks/wool_colored_cyan.png");
        tempBlocks.put("assets/minecraft/textures/block/damaged_anvil_top.png", "textures/blocks/anvil_top_damaged_2.png");
        tempBlocks.put("assets/minecraft/textures/block/dandelion.png", "textures/blocks/flower_dandelion.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_door_bottom.png", "textures/blocks/door_dark_oak_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_door_top.png", "textures/blocks/door_dark_oak_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_leaves.png", "textures/blocks/leaves_big_oak.tga;textures/blocks/leaves_big_oak_carried.tga;textures/blocks/leaves_big_oak_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/dark_oak_leaves.png", "copy;color[183,185,183:94,122,61];fill[color=111,108,111,255]");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_log.png", "textures/blocks/log_big_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_log_top.png", "textures/blocks/log_big_oak_top.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_planks.png", "textures/blocks/planks_big_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_sapling.png", "textures/blocks/sapling_roofed_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_oak_trapdoor.png", "textures/blocks/dark_oak_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/dark_prismarine.png", "textures/blocks/prismarine_dark.png");
        tempBlocks.put("assets/minecraft/textures/block/daylight_detector_inverted_top.png", "textures/blocks/daylight_detector_inverted_top.png");
        tempBlocks.put("assets/minecraft/textures/block/daylight_detector_side.png", "textures/blocks/daylight_detector_side.png");
        tempBlocks.put("assets/minecraft/textures/block/daylight_detector_top.png", "textures/blocks/daylight_detector_top.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_brain_coral.png", "textures/blocks/coral_plant_pink_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_brain_coral_block.png", "textures/blocks/coral_pink_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_brain_coral_fan.png", "textures/blocks/coral_fan_pink_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_bubble_coral.png", "textures/blocks/coral_plant_purple_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_bubble_coral_block.png", "textures/blocks/coral_purple_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_bubble_coral_fan.png", "textures/blocks/coral_fan_purple_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_bush.png", "textures/blocks/deadbush.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_fire_coral.png", "textures/blocks/coral_plant_red_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_fire_coral_block.png", "textures/blocks/coral_red_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_fire_coral_fan.png", "textures/blocks/coral_fan_red_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_horn_coral.png", "textures/blocks/coral_plant_yellow_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_horn_coral_block.png", "textures/blocks/coral_yellow_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_horn_coral_fan.png", "textures/blocks/coral_fan_yellow_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_tube_coral.png", "textures/blocks/coral_plant_blue_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_tube_coral_block.png", "textures/blocks/coral_blue_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/dead_tube_coral_fan.png", "textures/blocks/coral_fan_blue_dead.png");
        tempBlocks.put("assets/minecraft/textures/block/debug.png", "");
        tempBlocks.put("assets/minecraft/textures/block/debug2.png", "");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_0.png", "textures/environment/destroy_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_1.png", "textures/environment/destroy_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_2.png", "textures/environment/destroy_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_3.png", "textures/environment/destroy_stage_3.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_4.png", "textures/environment/destroy_stage_4.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_5.png", "textures/environment/destroy_stage_5.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_6.png", "textures/environment/destroy_stage_6.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_7.png", "textures/environment/destroy_stage_7.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_8.png", "textures/environment/destroy_stage_8.png");
        tempBlocks.put("assets/minecraft/textures/block/destroy_stage_9.png", "textures/environment/destroy_stage_9.png");
        tempBlocks.put("assets/minecraft/textures/block/detector_rail.png", "textures/blocks/rail_detector.png");
        tempBlocks.put("assets/minecraft/textures/block/detector_rail_on.png", "textures/blocks/rail_detector_powered.png");
        tempBlocks.put("assets/minecraft/textures/block/diamond_block.png", "textures/blocks/diamond_block.png");
        tempBlocks.put("assets/minecraft/textures/block/diamond_ore.png", "textures/blocks/diamond_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/diorite.png", "textures/blocks/stone_diorite.png");
        tempBlocks.put("assets/minecraft/textures/block/dirt.png", "textures/blocks/dirt.png");
        tempBlocks.put("assets/minecraft/textures/block/dispenser_front.png", "textures/blocks/dispenser_front_horizontal.png");
        tempBlocks.put("assets/minecraft/textures/block/dispenser_front_vertical.png", "textures/blocks/dispenser_front_vertical.png");
        tempBlocks.put("assets/minecraft/textures/block/dragon_egg.png", "textures/blocks/dragon_egg.png");
        tempBlocks.put("assets/minecraft/textures/block/dried_kelp_bottom.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/block/dried_kelp_side.png", "textures/blocks/dried_kelp_side_a.png;textures/blocks/dried_kelp_side_b.png"); // mirror
        tempOptions.put("assets/minecraft/textures/block/dried_kelp_side.png", "copy;copy[mirror_x=true]");
        tempBlocks.put("assets/minecraft/textures/block/dried_kelp_top.png", "textures/blocks/dried_kelp_top.png");
        tempBlocks.put("assets/minecraft/textures/block/dropper_front.png", "textures/blocks/dropper_front_horizontal.png");
        tempBlocks.put("assets/minecraft/textures/block/dropper_front_vertical.png", "textures/blocks/dropper_front_vertical.png");
        tempBlocks.put("assets/minecraft/textures/block/emerald_block.png", "textures/blocks/emerald_block.png");
        tempBlocks.put("assets/minecraft/textures/block/emerald_ore.png", "textures/blocks/emerald_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/enchanting_table_bottom.png", "textures/blocks/enchanting_table_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/enchanting_table_side.png", "textures/blocks/enchanting_table_side.png");
        tempBlocks.put("assets/minecraft/textures/block/enchanting_table_top.png", "textures/blocks/enchanting_table_top.png");
        tempBlocks.put("assets/minecraft/textures/block/end_portal_frame_eye.png", "textures/blocks/endframe_eye.png");
        tempBlocks.put("assets/minecraft/textures/block/end_portal_frame_side.png", "textures/blocks/endframe_side.png");
        tempBlocks.put("assets/minecraft/textures/block/end_portal_frame_top.png", "textures/blocks/endframe_top.png");
        tempBlocks.put("assets/minecraft/textures/block/end_rod.png", "textures/blocks/end_rod.png");
        tempBlocks.put("assets/minecraft/textures/block/end_stone.png", "textures/blocks/end_stone.png");
        tempBlocks.put("assets/minecraft/textures/block/end_stone_bricks.png", "textures/blocks/end_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/farmland.png", "textures/blocks/farmland_dry.png");
        tempBlocks.put("assets/minecraft/textures/block/farmland_moist.png", "textures/blocks/farmland_wet.png");
        tempBlocks.put("assets/minecraft/textures/block/fern.png", "textures/blocks/fern.tga;textures/blocks/fern_carried.tga");
        tempOptions.put("assets/minecraft/textures/block/fern.png", "copy;color[165,162,165:80,120,69]");
        tempBlocks.put("assets/minecraft/textures/block/fire_0.png", "textures/blocks/fire_0.png;textures/blocks/fire_0_placeholder.png");
        tempOptions.put("assets/minecraft/textures/block/fire_0.png", "copy;single[last=true]");
        tempBlocks.put("assets/minecraft/textures/block/fire_1.png", "textures/blocks/fire_1.png;textures/blocks/fire_1_placeholder.png");
        tempOptions.put("assets/minecraft/textures/block/fire_1.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/fire_coral.png", "textures/blocks/coral_plant_red.png");
        tempBlocks.put("assets/minecraft/textures/block/fire_coral_block.png", "textures/blocks/coral_red.png");
        tempBlocks.put("assets/minecraft/textures/block/fire_coral_fan.png", "textures/blocks/coral_fan_red.png");
        tempBlocks.put("assets/minecraft/textures/block/fletching_table_front.png", "textures/blocks/fletcher_table_side2.png");
        tempBlocks.put("assets/minecraft/textures/block/fletching_table_side.png", "textures/blocks/fletcher_table_side1.png");
        tempBlocks.put("assets/minecraft/textures/block/fletching_table_top.png", "textures/blocks/fletcher_table_top.png");
        tempBlocks.put("assets/minecraft/textures/block/flower_pot.png", "textures/blocks/flower_pot.png");
        tempBlocks.put("assets/minecraft/textures/block/frosted_ice_0.png", "textures/blocks/frosted_ice_0.png");
        tempBlocks.put("assets/minecraft/textures/block/frosted_ice_1.png", "textures/blocks/frosted_ice_1.png");
        tempBlocks.put("assets/minecraft/textures/block/frosted_ice_2.png", "textures/blocks/frosted_ice_2.png");
        tempBlocks.put("assets/minecraft/textures/block/frosted_ice_3.png", "textures/blocks/frosted_ice_3.png");
        tempBlocks.put("assets/minecraft/textures/block/furnace_front.png", "textures/blocks/furnace_front_off.png");
        tempBlocks.put("assets/minecraft/textures/block/furnace_front_on.png", "textures/blocks/furnace_front_on.png");
        tempBlocks.put("assets/minecraft/textures/block/furnace_side.png", "textures/blocks/furnace_side.png");
        tempBlocks.put("assets/minecraft/textures/block/furnace_top.png", "textures/blocks/furnace_top.png");
        tempBlocks.put("assets/minecraft/textures/block/glass.png", "textures/blocks/glass.png");
        tempBlocks.put("assets/minecraft/textures/block/glass_pane_top.png", "textures/blocks/glass_pane_top.png");
        tempBlocks.put("assets/minecraft/textures/block/glowstone.png", "textures/blocks/glowstone.png");
        tempBlocks.put("assets/minecraft/textures/block/gold_block.png", "textures/blocks/gold_block.png");
        tempBlocks.put("assets/minecraft/textures/block/gold_ore.png", "textures/blocks/gold_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/granite.png", "textures/blocks/stone_granite.png");
        tempBlocks.put("assets/minecraft/textures/block/grass.png", "textures/blocks/tallgrass.tga;textures/blocks/tallgrass_carried.tga");
        tempOptions.put("assets/minecraft/textures/block/grass.png", "copy;color[184,183,184:96,146,50]");
        tempBlocks.put("assets/minecraft/textures/block/grass_block_side.png", ""); // used by grass_side_carried.png
        tempBlocks.put("assets/minecraft/textures/block/grass_block_side_overlay.png", "textures/blocks/grass_side.tga;textures/blocks/grass_side_carried.png");
        tempOptions.put("assets/minecraft/textures/block/grass_block_side_overlay.png", "combine[assets/minecraft/textures/block/grass_block_side.png:transparent=true:this:copy,minAlphaToCopy=30];" +
                "combine[assets/minecraft/textures/block/grass_block_side.png:copy:this:copy,minAlphaToCopy=30,color=178-178-178_156-203-108,blend=true]"
                /*"color[178,178,178:156,203,108]"*/);
        tempBlocks.put("assets/minecraft/textures/block/grass_block_snow.png", "textures/blocks/grass_side_snowed.png"/*"textures/blocks/grass_side_snowed.png;textures/blocks/grass_side_snowed.tga"*/);
        //tempOptions.put("assets/minecraft/textures/block/grass_block_snow.png", "copy;copy[alpha=0]");
        tempBlocks.put("assets/minecraft/textures/block/grass_block_top.png", "textures/blocks/grass_top.png;textures/blocks/grass_carried.png");
        tempOptions.put("assets/minecraft/textures/block/grass_block_top.png", "copy;color[195,195,195:103,157,56]");
        tempBlocks.put("assets/minecraft/textures/block/grass_path_side.png", "textures/blocks/grass_path_side.png");
        tempBlocks.put("assets/minecraft/textures/block/grass_path_top.png", "textures/blocks/grass_path_top.png");
        tempBlocks.put("assets/minecraft/textures/block/gravel.png", "textures/blocks/gravel.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_concrete.png", "textures/blocks/concrete_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_concrete_powder.png", "textures/blocks/concrete_powder_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_glazed_terracotta.png", "textures/blocks/glazed_terracotta_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_shulker_box.png", "textures/blocks/shulker_top_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_stained_glass.png", "textures/blocks/glass_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_terracotta.png", "textures/blocks/hardened_clay_stained_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_wool.png", "textures/blocks/wool_colored_gray.png");
        tempBlocks.put("assets/minecraft/textures/block/green_concrete.png", "textures/blocks/concrete_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_concrete_powder.png", "textures/blocks/concrete_powder_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_glazed_terracotta.png", "textures/blocks/glazed_terracotta_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_shulker_box.png", "textures/blocks/shulker_top_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_stained_glass.png", "textures/blocks/glass_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_terracotta.png", "textures/blocks/hardened_clay_stained_green.png");
        tempBlocks.put("assets/minecraft/textures/block/green_wool.png", "textures/blocks/wool_colored_green.png");
        tempBlocks.put("assets/minecraft/textures/block/grindstone_pivot.png", "textures/blocks/grindstone_pivot.tga");
        tempBlocks.put("assets/minecraft/textures/block/grindstone_round.png", "textures/blocks/grindstone_round.tga");
        tempBlocks.put("assets/minecraft/textures/block/grindstone_side.png", "textures/blocks/grindstone_side.tga");
        tempBlocks.put("assets/minecraft/textures/block/hay_block_side.png", "textures/blocks/hay_block_side.png");
        tempBlocks.put("assets/minecraft/textures/block/hay_block_top.png", "textures/blocks/hay_block_top.png");
        tempBlocks.put("assets/minecraft/textures/block/honeycomb_block.png", "textures/blocks/honeycomb.png");
        tempBlocks.put("assets/minecraft/textures/block/honey_block_bottom.png", "textures/blocks/honey_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/honey_block_side.png", "textures/blocks/honey_side.png");
        tempBlocks.put("assets/minecraft/textures/block/honey_block_top.png", "textures/blocks/honey_top.png");
        tempBlocks.put("assets/minecraft/textures/block/hopper_inside.png", "textures/blocks/hopper_inside.png");
        tempBlocks.put("assets/minecraft/textures/block/hopper_outside.png", "textures/blocks/hopper_outside.png");
        tempBlocks.put("assets/minecraft/textures/block/hopper_top.png", "textures/blocks/hopper_top.png");
        tempBlocks.put("assets/minecraft/textures/block/horn_coral.png", "textures/blocks/coral_plant_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/horn_coral_block.png", "textures/blocks/coral_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/horn_coral_fan.png", "textures/blocks/coral_fan_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/ice.png", "textures/blocks/ice.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_bars.png", "textures/blocks/iron_bars.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_block.png", "textures/blocks/iron_block.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_door_bottom.png", "textures/blocks/door_iron_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_door_top.png", "textures/blocks/door_iron_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_ore.png", "textures/blocks/iron_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/iron_trapdoor.png", "textures/blocks/iron_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/item_frame.png", "textures/blocks/itemframe_background.png");
        tempBlocks.put("assets/minecraft/textures/block/jack_o_lantern.png", "textures/blocks/pumpkin_face_on.png");
        tempBlocks.put("assets/minecraft/textures/block/jigsaw_bottom.png", "textures/blocks/jigsaw_back.png");
        tempBlocks.put("assets/minecraft/textures/block/jigsaw_side.png", "textures/blocks/jigsaw_side.png");
        tempBlocks.put("assets/minecraft/textures/block/jigsaw_top.png", "textures/blocks/jigsaw_front.png");
        tempBlocks.put("assets/minecraft/textures/block/jukebox_side.png", "textures/blocks/jukebox_side.png");
        tempBlocks.put("assets/minecraft/textures/block/jukebox_top.png", "textures/blocks/jukebox_top.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_door_bottom.png", "textures/blocks/door_jungle_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_door_top.png", "textures/blocks/door_jungle_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_leaves.png", "textures/blocks/leaves_jungle.tga;textures/blocks/leaves_jungle_carried.tga;textures/blocks/leaves_jungle_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/jungle_leaves.png", "copy;color[183,185,183:63,138,6];fill[color=111,108,111,255]");
        tempBlocks.put("assets/minecraft/textures/block/jungle_log.png", "textures/blocks/log_jungle.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_log_top.png", "textures/blocks/log_jungle_top.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_planks.png", "textures/blocks/planks_jungle.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_sapling.png", "textures/blocks/sapling_jungle.png");
        tempBlocks.put("assets/minecraft/textures/block/jungle_trapdoor.png", "textures/blocks/jungle_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/kelp.png", "textures/blocks/kelp_top.tga;textures/blocks/kelp_top_bulb.tga");
        tempOptions.put("assets/minecraft/textures/block/kelp.png", "copy;copy");
        tempBlocks.put("assets/minecraft/textures/block/kelp_plant.png", "textures/blocks/kelp_a.tga;textures/blocks/kelp_b.tga;textures/blocks/kelp_c.tga;textures/blocks/kelp_d.tga");
        tempOptions.put("assets/minecraft/textures/block/kelp_plant.png", "copy;copy;copy;copy");
        tempBlocks.put("assets/minecraft/textures/block/ladder.png", "textures/blocks/ladder.png");
        tempBlocks.put("assets/minecraft/textures/block/lantern.png", "textures/blocks/lantern.png");
        tempBlocks.put("assets/minecraft/textures/block/lapis_block.png", "textures/blocks/lapis_block.png");
        tempBlocks.put("assets/minecraft/textures/block/lapis_ore.png", "textures/blocks/lapis_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/large_fern_bottom.png", "textures/blocks/double_plant_fern_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/block/large_fern_top.png", "textures/blocks/double_plant_fern_top.tga;textures/blocks/double_plant_fern_carried.png");
        tempOptions.put("assets/minecraft/textures/block/large_fern_top.png", "copy;color[172,170,172:95,144,51]");
        tempBlocks.put("assets/minecraft/textures/block/lava_flow.png", "textures/blocks/lava_flow.png");
        tempBlocks.put("assets/minecraft/textures/block/lava_still.png", "textures/blocks/lava_still.png;textures/blocks/lava_placeholder.png");
        tempOptions.put("assets/minecraft/textures/block/lava_still.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/lectern_base.png", "textures/blocks/lectern_base.png");
        tempBlocks.put("assets/minecraft/textures/block/lectern_front.png", "textures/blocks/lectern_front.png");
        tempBlocks.put("assets/minecraft/textures/block/lectern_sides.png", "textures/blocks/lectern_sides.png");
        tempBlocks.put("assets/minecraft/textures/block/lectern_top.png", "textures/blocks/lectern_top.png");
        tempBlocks.put("assets/minecraft/textures/block/lever.png", "textures/blocks/lever.png;textures/items/lever.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_concrete.png", "textures/blocks/concrete_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_concrete_powder.png", "textures/blocks/concrete_powder_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_glazed_terracotta.png", "textures/blocks/glazed_terracotta_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_shulker_box.png", "textures/blocks/shulker_top_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_stained_glass.png", "textures/blocks/glass_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_terracotta.png", "textures/blocks/hardened_clay_stained_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_wool.png", "textures/blocks/wool_colored_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_concrete.png", "textures/blocks/concrete_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_concrete_powder.png", "textures/blocks/concrete_powder_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_glazed_terracotta.png", "textures/blocks/glazed_terracotta_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_shulker_box.png", "textures/blocks/shulker_top_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_stained_glass.png", "textures/blocks/glass_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_terracotta.png", "textures/blocks/hardened_clay_stained_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_wool.png", "textures/blocks/wool_colored_silver.png");
        tempBlocks.put("assets/minecraft/textures/block/lilac_bottom.png", "textures/blocks/double_plant_syringa_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/block/lilac_top.png", "textures/blocks/double_plant_syringa_top.tga");
        tempBlocks.put("assets/minecraft/textures/block/lily_of_the_valley.png", "textures/blocks/flower_lily_of_the_valley.png");
        tempBlocks.put("assets/minecraft/textures/block/lily_pad.png", "textures/blocks/waterlily.png;textures/blocks/carried_waterlily.png");
        tempOptions.put("assets/minecraft/textures/block/lily_pad.png", "copy;color[163,163,163:80,121,43]");
        tempBlocks.put("assets/minecraft/textures/block/lime_concrete.png", "textures/blocks/concrete_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_concrete_powder.png", "textures/blocks/concrete_powder_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_glazed_terracotta.png", "textures/blocks/glazed_terracotta_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_shulker_box.png", "textures/blocks/shulker_top_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_stained_glass.png", "textures/blocks/glass_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_terracotta.png", "textures/blocks/hardened_clay_stained_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_wool.png", "textures/blocks/wool_colored_lime.png");
        tempBlocks.put("assets/minecraft/textures/block/loom_bottom.png", "textures/blocks/loom_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/loom_front.png", "textures/blocks/loom_front.png");
        tempBlocks.put("assets/minecraft/textures/block/loom_side.png", "textures/blocks/loom_side.png");
        tempBlocks.put("assets/minecraft/textures/block/loom_top.png", "textures/blocks/loom_top.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_concrete.png", "textures/blocks/concrete_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_concrete_powder.png", "textures/blocks/concrete_powder_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_glazed_terracotta.png", "textures/blocks/glazed_terracotta_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_shulker_box.png", "textures/blocks/shulker_top_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_stained_glass.png", "textures/blocks/glass_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_terracotta.png", "textures/blocks/hardened_clay_stained_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_wool.png", "textures/blocks/wool_colored_magenta.png");
        tempBlocks.put("assets/minecraft/textures/block/magma.png", "textures/blocks/magma.png");
        tempBlocks.put("assets/minecraft/textures/block/melon_side.png", "textures/blocks/melon_side.png");
        tempBlocks.put("assets/minecraft/textures/block/melon_stem.png", "textures/blocks/melon_stem_disconnected.png");
        tempBlocks.put("assets/minecraft/textures/block/melon_top.png", "textures/blocks/melon_top.png");
        tempBlocks.put("assets/minecraft/textures/block/mossy_cobblestone.png", "textures/blocks/cobblestone_mossy.png");
        tempBlocks.put("assets/minecraft/textures/block/mossy_stone_bricks.png", "textures/blocks/stonebrick_mossy.png");
        tempBlocks.put("assets/minecraft/textures/block/mushroom_block_inside.png", "textures/blocks/mushroom_block_inside.png");
        tempBlocks.put("assets/minecraft/textures/block/mushroom_stem.png", "textures/blocks/mushroom_block_skin_stem.png");
        tempBlocks.put("assets/minecraft/textures/block/mycelium_side.png", "textures/blocks/mycelium_side.png");
        tempBlocks.put("assets/minecraft/textures/block/mycelium_top.png", "textures/blocks/mycelium_top.png");
        tempBlocks.put("assets/minecraft/textures/block/netherrack.png", "textures/blocks/netherrack.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_bricks.png", "textures/blocks/nether_brick.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_portal.png", "textures/blocks/portal.png;textures/blocks/portal_placeholder.png");
        tempOptions.put("assets/minecraft/textures/block/nether_portal.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/nether_quartz_ore.png", "textures/blocks/quartz_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_wart_block.png", "textures/blocks/nether_wart_block.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_wart_stage0.png", "textures/blocks/nether_wart_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_wart_stage1.png", "textures/blocks/nether_wart_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_wart_stage2.png", "textures/blocks/nether_wart_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/note_block.png", "textures/blocks/noteblock.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_door_bottom.png", "textures/blocks/door_wood_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_door_top.png", "textures/blocks/door_wood_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_leaves.png", "textures/blocks/leaves_oak.tga;textures/blocks/leaves_oak_carried.tga;textures/blocks/leaves_oak_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/oak_leaves.png", "copy;color[185,188,185:49,128,15];fill[color=104,99,104,255]");
        tempBlocks.put("assets/minecraft/textures/block/oak_log.png", "textures/blocks/log_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_log_top.png", "textures/blocks/log_oak_top.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_planks.png", "textures/blocks/planks_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_sapling.png", "textures/blocks/sapling_oak.png");
        tempBlocks.put("assets/minecraft/textures/block/oak_trapdoor.png", "textures/blocks/trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/observer_back.png", "textures/blocks/observer_back.png");
        tempBlocks.put("assets/minecraft/textures/block/observer_back_on.png", "textures/blocks/observer_back_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/observer_front.png", "textures/blocks/observer_front.png");
        tempBlocks.put("assets/minecraft/textures/block/observer_side.png", "textures/blocks/observer_side.png");
        tempBlocks.put("assets/minecraft/textures/block/observer_top.png", "textures/blocks/observer_top.png");
        tempBlocks.put("assets/minecraft/textures/block/obsidian.png", "textures/blocks/obsidian.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_concrete.png", "textures/blocks/concrete_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_concrete_powder.png", "textures/blocks/concrete_powder_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_glazed_terracotta.png", "textures/blocks/glazed_terracotta_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_shulker_box.png", "textures/blocks/shulker_top_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_stained_glass.png", "textures/blocks/glass_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_terracotta.png", "textures/blocks/hardened_clay_stained_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_tulip.png", "textures/blocks/flower_tulip_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_wool.png", "textures/blocks/wool_colored_orange.png");
        tempBlocks.put("assets/minecraft/textures/block/oxeye_daisy.png", "textures/blocks/flower_oxeye_daisy.png");
        tempBlocks.put("assets/minecraft/textures/block/packed_ice.png", "textures/blocks/ice_packed.png");
        tempBlocks.put("assets/minecraft/textures/block/peony_bottom.png", "textures/blocks/double_plant_paeonia_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/peony_top.png", "textures/blocks/double_plant_paeonia_top.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_concrete.png", "textures/blocks/concrete_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_concrete_powder.png", "textures/blocks/concrete_powder_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_glazed_terracotta.png", "textures/blocks/glazed_terracotta_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_shulker_box.png", "textures/blocks/shulker_top_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_stained_glass.png", "textures/blocks/glass_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_terracotta.png", "textures/blocks/hardened_clay_stained_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_tulip.png", "textures/blocks/flower_tulip_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_wool.png", "textures/blocks/wool_colored_pink.png");
        tempBlocks.put("assets/minecraft/textures/block/piston_bottom.png", "textures/blocks/piston_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/piston_inner.png", "textures/blocks/piston_inner.png");
        tempBlocks.put("assets/minecraft/textures/block/piston_side.png", "textures/blocks/piston_side.png");
        tempBlocks.put("assets/minecraft/textures/block/piston_top.png", "textures/blocks/piston_top_normal.png;textures/entity/pistonarm/pistonArm.png"); // pistonArm also needs piston_side and piston_inner
        tempOptions.put("assets/minecraft/textures/block/piston_top.png", "copy;restructure[piston:this:null:assets/minecraft/textures/block/piston_side.png:assets/minecraft/textures/block/piston_inner.png]");
        tempBlocks.put("assets/minecraft/textures/block/piston_top_sticky.png", "textures/blocks/piston_top_sticky.png;textures/entity/pistonarm/pistonArmSticky.png"); // pistonArm also needs piston_side and piston_inner
        tempOptions.put("assets/minecraft/textures/block/piston_top_sticky.png", "copy;restructure[piston:assets/minecraft/textures/block/piston_top.png:this:assets/minecraft/textures/block/piston_side.png:assets/minecraft/textures/block/piston_inner.png]");
        tempBlocks.put("assets/minecraft/textures/block/podzol_side.png", "textures/blocks/dirt_podzol_side.png");
        tempBlocks.put("assets/minecraft/textures/block/podzol_top.png", "textures/blocks/dirt_podzol_top.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_andesite.png", "textures/blocks/stone_andesite_smooth.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_diorite.png", "textures/blocks/stone_diorite_smooth.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_granite.png", "textures/blocks/stone_granite_smooth.png");
        tempBlocks.put("assets/minecraft/textures/block/poppy.png", "textures/blocks/flower_rose.png");
        tempBlocks.put("assets/minecraft/textures/block/potatoes_stage0.png", "textures/blocks/potatoes_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/potatoes_stage1.png", "textures/blocks/potatoes_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/potatoes_stage2.png", "textures/blocks/potatoes_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/potatoes_stage3.png", "textures/blocks/potatoes_stage_3.png");
        tempBlocks.put("assets/minecraft/textures/block/powered_rail.png", "textures/blocks/rail_golden.png");
        tempBlocks.put("assets/minecraft/textures/block/powered_rail_on.png", "textures/blocks/rail_golden_powered.png");
        tempBlocks.put("assets/minecraft/textures/block/prismarine.png", "textures/blocks/prismarine_rough.png");
        tempBlocks.put("assets/minecraft/textures/block/prismarine_bricks.png", "textures/blocks/prismarine_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/pumpkin_side.png", "textures/blocks/pumpkin_side.png");
        tempBlocks.put("assets/minecraft/textures/block/pumpkin_stem.png", "textures/blocks/pumpkin_stem_disconnected.png");
        tempBlocks.put("assets/minecraft/textures/block/pumpkin_top.png", "textures/blocks/pumpkin_top.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_concrete.png", "textures/blocks/concrete_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_concrete_powder.png", "textures/blocks/concrete_powder_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_glazed_terracotta.png", "textures/blocks/glazed_terracotta_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_shulker_box.png", "textures/blocks/shulker_top_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_stained_glass.png", "textures/blocks/glass_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_terracotta.png", "textures/blocks/hardened_clay_stained_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_wool.png", "textures/blocks/wool_colored_purple.png");
        tempBlocks.put("assets/minecraft/textures/block/purpur_block.png", "textures/blocks/purpur_block.png");
        tempBlocks.put("assets/minecraft/textures/block/purpur_pillar.png", "textures/blocks/purpur_pillar.png");
        tempBlocks.put("assets/minecraft/textures/block/purpur_pillar_top.png", "textures/blocks/purpur_pillar_top.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_block_bottom.png", "textures/blocks/quartz_block_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_block_side.png", "textures/blocks/quartz_block_side.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_block_top.png", "textures/blocks/quartz_block_top.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_pillar.png", "textures/blocks/quartz_block_lines.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_pillar_top.png", "textures/blocks/quartz_block_lines_top.png");
        tempBlocks.put("assets/minecraft/textures/block/rail.png", "textures/blocks/rail_normal.png");
        tempBlocks.put("assets/minecraft/textures/block/rail_corner.png", "textures/blocks/rail_normal_turned.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_block.png", "textures/blocks/redstone_block.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_dust_dot.png", ""); // used by redstone_dust_cross
        tempBlocks.put("assets/minecraft/textures/block/redstone_dust_line0.png", "textures/blocks/redstone_dust_line.png"); // rotate left
        tempOptions.put("assets/minecraft/textures/block/redstone_dust_line0.png", "copy[rotate=-90]");
        tempBlocks.put("assets/minecraft/textures/block/redstone_dust_line1.png", "textures/blocks/redstone_dust_cross.png"); // + redstone_dust_dot.png + rotate left
        tempOptions.put("assets/minecraft/textures/block/redstone_dust_line1.png", "combine[assets/minecraft/textures/block/redstone_dust_dot.png:copy:this:copy,minAlphaToCopy=1:this:copy,minAlphaToCopy=1,rotate=-90]");
        tempBlocks.put("assets/minecraft/textures/block/redstone_dust_overlay.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/block/redstone_lamp.png", "textures/blocks/redstone_lamp_off.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_lamp_on.png", "textures/blocks/redstone_lamp_on.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_ore.png", "textures/blocks/redstone_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_torch.png", "textures/blocks/redstone_torch_on.png");
        tempBlocks.put("assets/minecraft/textures/block/redstone_torch_off.png", "textures/blocks/redstone_torch_off.png");
        tempBlocks.put("assets/minecraft/textures/block/red_concrete.png", "textures/blocks/concrete_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_concrete_powder.png", "textures/blocks/concrete_powder_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_glazed_terracotta.png", "textures/blocks/glazed_terracotta_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_mushroom.png", "textures/blocks/mushroom_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_mushroom_block.png", "textures/blocks/mushroom_block_skin_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_nether_bricks.png", "textures/blocks/red_nether_brick.png");
        tempBlocks.put("assets/minecraft/textures/block/red_sand.png", "textures/blocks/red_sand.png");
        tempBlocks.put("assets/minecraft/textures/block/red_sandstone.png", "textures/blocks/red_sandstone_normal.png");
        tempBlocks.put("assets/minecraft/textures/block/red_sandstone_bottom.png", "textures/blocks/red_sandstone_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/red_sandstone_top.png", "textures/blocks/red_sandstone_top.png");
        tempBlocks.put("assets/minecraft/textures/block/red_shulker_box.png", "textures/blocks/shulker_top_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_stained_glass.png", "textures/blocks/glass_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_terracotta.png", "textures/blocks/hardened_clay_stained_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_tulip.png", "textures/blocks/flower_tulip_red.png");
        tempBlocks.put("assets/minecraft/textures/block/red_wool.png", "textures/blocks/wool_colored_red.png");
        tempBlocks.put("assets/minecraft/textures/block/repeater.png", "textures/blocks/repeater_off.png");
        tempBlocks.put("assets/minecraft/textures/block/repeater_on.png", "textures/blocks/repeater_on.png");
        tempBlocks.put("assets/minecraft/textures/block/repeating_command_block_back.png", "textures/blocks/repeating_command_block_back.png;textures/blocks/repeating_command_block_back_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/repeating_command_block_back.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/repeating_command_block_conditional.png", "textures/blocks/repeating_command_block_conditional.png;textures/blocks/repeating_command_block_conditional_mipmap.png");
        tempOptions.put("textures/block/repeating_command_block_conditional.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/repeating_command_block_front.png", "textures/blocks/repeating_command_block_front.png;textures/blocks/repeating_command_block_front_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/repeating_command_block_front.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/repeating_command_block_side.png", "textures/blocks/repeating_command_block_side.png;textures/blocks/repeating_command_block_side_mipmap.png");
        tempOptions.put("assets/minecraft/textures/block/repeating_command_block_side.png", "copy;single");
        tempBlocks.put("assets/minecraft/textures/block/rose_bush_bottom.png", "textures/blocks/double_plant_rose_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/rose_bush_top.png", "textures/blocks/double_plant_rose_top.png");
        tempBlocks.put("assets/minecraft/textures/block/sand.png", "textures/blocks/sand.png");
        tempBlocks.put("assets/minecraft/textures/block/sandstone.png", "textures/blocks/sandstone_normal.png");
        tempBlocks.put("assets/minecraft/textures/block/sandstone_bottom.png", "textures/blocks/sandstone_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/sandstone_top.png", "textures/blocks/sandstone_top.png");
        tempBlocks.put("assets/minecraft/textures/block/scaffolding_bottom.png", "textures/blocks/scaffolding_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/block/scaffolding_side.png", "textures/blocks/scaffolding_side.tga");
        tempBlocks.put("assets/minecraft/textures/block/scaffolding_top.png", "textures/blocks/scaffolding_top.tga");
        tempBlocks.put("assets/minecraft/textures/block/seagrass.png", "textures/blocks/seagrass.png");
        tempBlocks.put("assets/minecraft/textures/block/sea_lantern.png", "textures/blocks/sea_lantern.png");
        tempBlocks.put("assets/minecraft/textures/block/sea_pickle.png", "textures/blocks/sea_pickle.png");
        tempBlocks.put("assets/minecraft/textures/block/shulker_box.png", "textures/blocks/shulker_top_undyed.png");
        tempBlocks.put("assets/minecraft/textures/block/slime_block.png", "textures/blocks/slime.png");
        tempBlocks.put("assets/minecraft/textures/block/smithing_table_bottom.png", "textures/blocks/smithing_table_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/smithing_table_front.png", "textures/blocks/smithing_table_front.png");
        tempBlocks.put("assets/minecraft/textures/block/smithing_table_side.png", "textures/blocks/smithing_table_side.png");
        tempBlocks.put("assets/minecraft/textures/block/smithing_table_top.png", "textures/blocks/smithing_table_top.png");
        tempBlocks.put("assets/minecraft/textures/block/smoker_bottom.png", "textures/blocks/smoker_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/smoker_front.png", "textures/blocks/smoker_front_off.png");
        tempBlocks.put("assets/minecraft/textures/block/smoker_front_on.png", "textures/blocks/smoker_front_on.png");
        tempBlocks.put("assets/minecraft/textures/block/smoker_side.png", "textures/blocks/smoker_side.png");
        tempBlocks.put("assets/minecraft/textures/block/smoker_top.png", "textures/blocks/smoker_top.png");
        tempBlocks.put("assets/minecraft/textures/block/smooth_stone.png", "textures/blocks/stone_slab_top.png");
        tempBlocks.put("assets/minecraft/textures/block/smooth_stone_slab_side.png", "textures/blocks/stone_slab_side.png");
        tempBlocks.put("assets/minecraft/textures/block/snow.png", "textures/blocks/snow.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_sand.png", "textures/blocks/soul_sand.png");
        tempBlocks.put("assets/minecraft/textures/block/spawner.png", "textures/blocks/mob_spawner.png");
        tempBlocks.put("assets/minecraft/textures/block/sponge.png", "textures/blocks/sponge.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_door_bottom.png", "textures/blocks/door_spruce_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_door_top.png", "textures/blocks/door_spruce_upper.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_leaves.png", "textures/blocks/leaves_spruce.tga;textures/blocks/leaves_spruce_carried.tga;textures/blocks/leaves_spruce_opaque.png");
        tempOptions.put("assets/minecraft/textures/block/spruce_leaves.png", "copy;color[154,154,154:57,91,57];fill[color=66,66,66,255]");
        tempBlocks.put("assets/minecraft/textures/block/spruce_log.png", "textures/blocks/log_spruce.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_log_top.png", "textures/blocks/log_spruce_top.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_planks.png", "textures/blocks/planks_spruce.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_sapling.png", "textures/blocks/sapling_spruce.png");
        tempBlocks.put("assets/minecraft/textures/block/spruce_trapdoor.png", "textures/blocks/spruce_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/stone.png", "textures/blocks/stone.png");
        tempBlocks.put("assets/minecraft/textures/block/stonecutter_bottom.png", "textures/blocks/stonecutter2_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/stonecutter_saw.png", "textures/blocks/stonecutter2_saw.tga");
        tempBlocks.put("assets/minecraft/textures/block/stonecutter_side.png", "textures/blocks/stonecutter2_side.png");
        tempBlocks.put("assets/minecraft/textures/block/stonecutter_top.png", "textures/blocks/stonecutter2_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stone_bricks.png", "textures/blocks/stonebrick.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_acacia_log.png", "textures/blocks/stripped_acacia_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_acacia_log_top.png", "textures/blocks/stripped_acacia_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_birch_log.png", "textures/blocks/stripped_birch_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_birch_log_top.png", "textures/blocks/stripped_birch_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_dark_oak_log.png", "textures/blocks/stripped_dark_oak_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_dark_oak_log_top.png", "textures/blocks/stripped_dark_oak_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_jungle_log.png", "textures/blocks/stripped_jungle_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_jungle_log_top.png", "textures/blocks/stripped_jungle_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_oak_log.png", "textures/blocks/stripped_oak_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_oak_log_top.png", "textures/blocks/stripped_oak_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_spruce_log.png", "textures/blocks/stripped_spruce_log.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_spruce_log_top.png", "textures/blocks/stripped_spruce_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/structure_block.png", "textures/blocks/structure_block.png");
        tempBlocks.put("assets/minecraft/textures/block/structure_block_corner.png", "textures/blocks/structure_block_corner.png");
        tempBlocks.put("assets/minecraft/textures/block/structure_block_data.png", "textures/blocks/structure_block_data.png");
        tempBlocks.put("assets/minecraft/textures/block/structure_block_load.png", "textures/blocks/structure_block_load.png");
        tempBlocks.put("assets/minecraft/textures/block/structure_block_save.png", "textures/blocks/structure_block_save.png");
        tempBlocks.put("assets/minecraft/textures/block/sugar_cane.png", "textures/blocks/reeds.tga");
        tempBlocks.put("assets/minecraft/textures/block/sunflower_back.png", "textures/blocks/double_plant_sunflower_back.png");
        tempBlocks.put("assets/minecraft/textures/block/sunflower_bottom.png", "textures/blocks/double_plant_sunflower_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/sunflower_front.png", "textures/blocks/double_plant_sunflower_front.png");
        tempBlocks.put("assets/minecraft/textures/block/sunflower_top.png", "textures/blocks/double_plant_sunflower_top.png");
        tempBlocks.put("assets/minecraft/textures/block/sweet_berry_bush_stage0.png", "textures/blocks/sweet_berry_bush_stage0.png");
        tempBlocks.put("assets/minecraft/textures/block/sweet_berry_bush_stage1.png", "textures/blocks/sweet_berry_bush_stage1.png");
        tempBlocks.put("assets/minecraft/textures/block/sweet_berry_bush_stage2.png", "textures/blocks/sweet_berry_bush_stage2.png");
        tempBlocks.put("assets/minecraft/textures/block/sweet_berry_bush_stage3.png", "textures/blocks/sweet_berry_bush_stage3.png");
        tempBlocks.put("assets/minecraft/textures/block/tall_grass_bottom.png", "textures/blocks/double_plant_grass_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/block/tall_grass_top.png", "textures/blocks/double_plant_grass_top.tga;textures/blocks/double_plant_grass_carried.png");
        tempOptions.put("assets/minecraft/textures/block/tall_grass_top.png", "copy;color[172,170,172:95,144,51]");
        tempBlocks.put("assets/minecraft/textures/block/tall_seagrass_bottom.png", "textures/blocks/seagrass_doubletall_bottom_a.tga;textures/blocks/seagrass_doubletall_bottom_b.tga"); // mirror
        tempOptions.put("assets/minecraft/textures/block/tall_seagrass_bottom.png", "copy;copy[mirror_x=true]");
        tempBlocks.put("assets/minecraft/textures/block/tall_seagrass_top.png", "textures/blocks/seagrass_doubletall_top_a.tga;textures/blocks/seagrass_doubletall_top_b.tga"); // mirror
        tempOptions.put("assets/minecraft/textures/block/tall_seagrass_top.png", "copy;copy[mirror_x=true]");
        tempBlocks.put("assets/minecraft/textures/block/terracotta.png", "textures/blocks/hardened_clay.png");
        tempBlocks.put("assets/minecraft/textures/block/tnt_bottom.png", "textures/blocks/tnt_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/tnt_side.png", "textures/blocks/tnt_side.png");
        tempBlocks.put("assets/minecraft/textures/block/tnt_top.png", "textures/blocks/tnt_top.png");
        tempBlocks.put("assets/minecraft/textures/block/torch.png", "textures/blocks/torch_on.png");
        tempBlocks.put("assets/minecraft/textures/block/tripwire.png", "textures/blocks/trip_wire.png");
        tempBlocks.put("assets/minecraft/textures/block/tripwire_hook.png", "textures/blocks/trip_wire_source.png");
        tempBlocks.put("assets/minecraft/textures/block/tube_coral.png", "textures/blocks/coral_plant_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/tube_coral_block.png", "textures/blocks/coral_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/tube_coral_fan.png", "textures/blocks/coral_fan_blue.png");
        tempBlocks.put("assets/minecraft/textures/block/turtle_egg.png", "textures/blocks/turtle_egg_not_cracked.png");
        tempBlocks.put("assets/minecraft/textures/block/turtle_egg_slightly_cracked.png", "textures/blocks/turtle_egg_slightly_cracked.png");
        tempBlocks.put("assets/minecraft/textures/block/turtle_egg_very_cracked.png", "textures/blocks/turtle_egg_very_cracked.png");
        tempBlocks.put("assets/minecraft/textures/block/vine.png", "textures/blocks/vine.png;textures/blocks/vine_carried.png");
        tempOptions.put("assets/minecraft/textures/block/vine.png", "copy;color[170,170,170:95,144,51]");
        tempBlocks.put("assets/minecraft/textures/block/water_flow.png", "textures/blocks/water_flow_grey.png;textures/blocks/water_flow.png");
        tempOptions.put("assets/minecraft/textures/block/water_flow.png", "copy[(!rtx)alpha=255];copy[color=255-255-255_75-155-254:(!rtx)alpha=255]");
        tempBlocks.put("assets/minecraft/textures/block/water_overlay.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/block/water_still.png", "textures/blocks/water_still_grey.png;textures/blocks/water_placeholder.png;textures/blocks/water_still.png;textures/blocks/cauldron_water.png;textures/blocks/cauldron_water_placeholder.png");
        tempOptions.put("assets/minecraft/textures/block/water_still.png", "copy[(!rtx)alpha=240];single[color=255,255,255-75,155,254:(!rtx)alpha=255];copy[color=255-255-255_75-155-254:(!rtx)alpha=255];copy[adjustColor=0,230,160,230];copy[adjustColor=0,230,160,230:single=first]");
        tempBlocks.put("assets/minecraft/textures/block/wet_sponge.png", "textures/blocks/sponge_wet.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage0.png", "textures/blocks/wheat_stage_0.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage1.png", "textures/blocks/wheat_stage_1.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage2.png", "textures/blocks/wheat_stage_2.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage3.png", "textures/blocks/wheat_stage_3.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage4.png", "textures/blocks/wheat_stage_4.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage5.png", "textures/blocks/wheat_stage_5.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage6.png", "textures/blocks/wheat_stage_6.png");
        tempBlocks.put("assets/minecraft/textures/block/wheat_stage7.png", "textures/blocks/wheat_stage_7.png");
        tempBlocks.put("assets/minecraft/textures/block/white_concrete.png", "textures/blocks/concrete_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_concrete_powder.png", "textures/blocks/concrete_powder_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_glazed_terracotta.png", "textures/blocks/glazed_terracotta_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_shulker_box.png", "textures/blocks/shulker_top_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_stained_glass.png", "textures/blocks/glass_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_terracotta.png", "textures/blocks/hardened_clay_stained_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_tulip.png", "textures/blocks/flower_tulip_white.png");
        tempBlocks.put("assets/minecraft/textures/block/white_wool.png", "textures/blocks/wool_colored_white.png");
        tempBlocks.put("assets/minecraft/textures/block/wither_rose.png", "textures/blocks/flower_wither_rose.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_concrete.png", "textures/blocks/concrete_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_concrete_powder.png", "textures/blocks/concrete_powder_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_glazed_terracotta.png", "textures/blocks/glazed_terracotta_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_shulker_box.png", "textures/blocks/shulker_top_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_stained_glass.png", "textures/blocks/glass_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_stained_glass_pane_top.png", "textures/blocks/glass_pane_top_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_terracotta.png", "textures/blocks/hardened_clay_stained_yellow.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_wool.png", "textures/blocks/wool_colored_yellow.png");


        //armor
        tempBlocks.put("assets/minecraft/textures/models/armor/chainmail_layer_1.png", "textures/models/armor/chain_1.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/chainmail_layer_2.png", "textures/models/armor/chain_2.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/diamond_layer_1.png", "textures/models/armor/diamond_1.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/diamond_layer_2.png", "textures/models/armor/diamond_2.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/gold_layer_1.png", "textures/models/armor/gold_1.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/gold_layer_2.png", "textures/models/armor/gold_2.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/iron_layer_1.png", "textures/models/armor/iron_1.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/iron_layer_2.png", "textures/models/armor/iron_2.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/leather_layer_1.png", "textures/models/armor/cloth_1.png"); // color brown
        tempOptions.put("assets/minecraft/textures/models/armor/leather_layer_1.png", "color[214,212,212:167,105,67]");
        tempBlocks.put("assets/minecraft/textures/models/armor/leather_layer_1_overlay.png", "textures/models/armor/leather_1.tga"); // cloth_1.png color, overlay transparent color
        tempOptions.put("assets/minecraft/textures/models/armor/leather_layer_1_overlay.png", "combine[assets/minecraft/textures/models/armor/leather_layer_1.png:copy:this:maxAlpha=1,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/models/armor/leather_layer_2.png", "textures/models/armor/cloth_2.png"); // color brown
        tempOptions.put("assets/minecraft/textures/models/armor/leather_layer_2.png", "color[214,212,212:167,105,67]");
        tempBlocks.put("assets/minecraft/textures/models/armor/leather_layer_2_overlay.png", "textures/models/armor/leather_2.tga"); // cloth_2.png color, overlay transparent color
        tempOptions.put("assets/minecraft/textures/models/armor/leather_layer_2_overlay.png", "combine[assets/minecraft/textures/models/armor/leather_layer_2.png:copy:this:maxAlpha=1,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/models/armor/turtle_layer_1.png", "textures/models/armor/turtle_1.png");

        // entity
        tempBlocks.put("assets/minecraft/textures/entity/alex.png", "textures/entity/alex.png");
        tempBlocks.put("assets/minecraft/textures/entity/arrow.png", "textures/entity/arrows.png");


        tempBlocks.put("assets/minecraft/textures/entity/bat.png", "textures/entity/bat.png");
        tempBlocks.put("assets/minecraft/textures/entity/beacon_beam.png", "textures/entity/beacon_beam.png");
        tempBlocks.put("assets/minecraft/textures/entity/blaze.png", "textures/entity/blaze.tga");
        tempBlocks.put("assets/minecraft/textures/entity/chicken.png", "textures/entity/chicken.png");
        tempBlocks.put("assets/minecraft/textures/entity/dolphin.png", "textures/entity/dolphin.png"); // needs restructureing
        tempOptions.put("assets/minecraft/textures/entity/dolphin.png", "restructure[dolphin:this]");
        tempBlocks.put("assets/minecraft/textures/entity/elytra.png", "textures/models/armor/elytra.png");
        tempBlocks.put("assets/minecraft/textures/entity/enchanting_table_book.png", "textures/entity/enchanting_table_book.png");
        tempBlocks.put("assets/minecraft/textures/entity/endermite.png", "textures/entity/endermite.png");
        tempBlocks.put("assets/minecraft/textures/entity/end_gateway_beam.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/entity/end_portal.png", "textures/entity/end_portal.png");
        tempBlocks.put("assets/minecraft/textures/entity/experience_orb.png", "textures/entity/experience_orb.png");
        //tempBlocks.put("assets/minecraft/textures/entity/fishing_hook.png", ""); // not compatible
        tempBlocks.put("assets/minecraft/textures/entity/guardian.png", "textures/entity/guardian.png");
        tempBlocks.put("assets/minecraft/textures/entity/guardian_beam.png", "textures/entity/guardian_beam.png");
        tempBlocks.put("assets/minecraft/textures/entity/guardian_elder.png", "textures/entity/guardian_elder.png");
        tempBlocks.put("assets/minecraft/textures/entity/lead_knot.png", "textures/entity/lead_knot.png");
        tempBlocks.put("assets/minecraft/textures/entity/minecart.png", "textures/entity/minecart.png");
        tempBlocks.put("assets/minecraft/textures/entity/phantom.png", "textures/entity/phantom.tga"); // + phantom eyes transparent
        tempOptions.put("assets/minecraft/textures/entity/phantom.png", "combine[this:copy:assets/minecraft/textures/entity/phantom_eyes.png:transparent=true,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/entity/phantom_eyes.png", ""); // used by phantom
        tempBlocks.put("assets/minecraft/textures/entity/shield_base.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/entity/shield_base_nopattern.png", "textures/entity/shield.png");
        tempBlocks.put("assets/minecraft/textures/entity/silverfish.png", "textures/entity/silverfish.png");
        tempBlocks.put("assets/minecraft/textures/entity/snow_golem.png", "textures/entity/snow_golem.png");
        tempBlocks.put("assets/minecraft/textures/entity/spider_eyes.png", ""); // used by spider
        tempBlocks.put("assets/minecraft/textures/entity/squid.png", "textures/entity/squid.png");
        tempBlocks.put("assets/minecraft/textures/entity/steve.png", "textures/entity/steve.png");
        tempBlocks.put("assets/minecraft/textures/entity/trident.png", "textures/entity/trident.png");
        tempBlocks.put("assets/minecraft/textures/entity/trident_riptide.png", "textures/entity/trident_riptide.png");
        tempBlocks.put("assets/minecraft/textures/entity/wandering_trader.png", "textures/entity/wandering_trader.png");
        tempBlocks.put("assets/minecraft/textures/entity/witch.png", "textures/entity/witch.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_pigman.png", "textures/entity/pig/pigzombie.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_pigman.png", "copy[height=0.5]");

        tempBlocks.put("assets/minecraft/textures/entity/armorstand/wood.png", "textures/entity/armor_stand.png");

        // new banners
        tempBlocks.put("assets/minecraft/textures/entity/banner_base.png", "textures/entity/banner/banner_base.tga;textures/entity/banner/banner_illager.tga");
        tempOptions.put("assets/minecraft/textures/entity/banner_base.png", "restructure[banner_base:this];function[banner_illager]");
        tempBlocks.put("assets/minecraft/textures/entity/banner/base.png", ""); // ignore
        tempBlocks.put("assets/minecraft/textures/entity/banner/border.png", "textures/entity/banner/banner_border.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/bricks.png", "textures/entity/banner/banner_bricks.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/circle.png", "textures/entity/banner/banner_circle.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/creeper.png", "textures/entity/banner/banner_creeper.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/cross.png", "textures/entity/banner/banner_cross.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/curly_border.png", "textures/entity/banner/banner_curly_border.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_left.png", "textures/entity/banner/banner_diagonal_left.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_right.png", "textures/entity/banner/banner_diagonal_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_up_left.png", "textures/entity/banner/banner_diagonal_up_left.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/diagonal_up_right.png", "textures/entity/banner/banner_diagonal_up_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/flower.png", "textures/entity/banner/banner_flower.tga");
        //tempBlocks.put("assets/minecraft/textures/entity/banner/globe.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/banner/gradient.png", "textures/entity/banner/banner_gradient.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/gradient_up.png", "textures/entity/banner/banner_gradient_up.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_horizontal.png", "textures/entity/banner/banner_half_horizontal.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_horizontal_bottom.png", "textures/entity/banner/banner_half_horizontal_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_vertical.png", "textures/entity/banner/banner_half_vertical.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/half_vertical_right.png", "textures/entity/banner/banner_half_vertical_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/mojang.png", "textures/entity/banner/banner_mojang.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/rhombus.png", "textures/entity/banner/banner_rhombus.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/skull.png", "textures/entity/banner/banner_skull.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/small_stripes.png", "textures/entity/banner/banner_small_stripes.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_bottom_left.png", "textures/entity/banner/banner_square_bottom_left.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_bottom_right.png", "textures/entity/banner/banner_square_bottom_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_top_left.png", "textures/entity/banner/banner_square_top_left.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/square_top_right.png", "textures/entity/banner/banner_square_top_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/straight_cross.png", "textures/entity/banner/banner_straight_cross.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_bottom.png", "textures/entity/banner/banner_stripe_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_center.png", "textures/entity/banner/banner_stripe_center.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_downleft.png", "textures/entity/banner/banner_stripe_downleft.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_downright.png", "textures/entity/banner/banner_stripe_downright.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_left.png", "textures/entity/banner/banner_stripe_left.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_middle.png", "textures/entity/banner/banner_stripe_middle.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_right.png", "textures/entity/banner/banner_stripe_right.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/stripe_top.png", "textures/entity/banner/banner_stripe_top.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangles_bottom.png", "textures/entity/banner/banner_triangles_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangles_top.png", "textures/entity/banner/banner_triangles_top.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangle_bottom.png", "textures/entity/banner/banner_triangle_bottom.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/triangle_top.png", "textures/entity/banner/banner_triangle_top.tga");
        tempBlocks.put("assets/minecraft/textures/entity/banner/piglin.png", "textures/entity/banner/banner_piglin.tga");

        //    Cyan Lozenge
        //    Light Gray Base
        //    Gray Pale
        //    Light Gray Bordure
        //    Black Fess
        //    Light Gray Per Fess
        //    Light Gray Roundel
        //    Black Bordure

        // bear
        tempBlocks.put("assets/minecraft/textures/entity/bear/polarbear.png", "textures/entity/polarbear.png");
        // beds // all need restructure
        tempBlocks.put("assets/minecraft/textures/entity/bed/black.png", "textures/entity/bed/black.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/black.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/blue.png", "textures/entity/bed/blue.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/blue.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/brown.png", "textures/entity/bed/brown.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/brown.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/cyan.png", "textures/entity/bed/cyan.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/cyan.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/gray.png", "textures/entity/bed/gray.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/gray.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/green.png", "textures/entity/bed/green.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/green.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/light_blue.png", "textures/entity/bed/light_blue.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/light_blue.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/light_gray.png", "textures/entity/bed/silver.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/light_gray.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/lime.png", "textures/entity/bed/lime.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/lime.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/magenta.png", "textures/entity/bed/magenta.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/magenta.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/orange.png", "textures/entity/bed/orange.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/orange.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/pink.png", "textures/entity/bed/pink.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/pink.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/purple.png", "textures/entity/bed/purple.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/purple.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/red.png", "textures/entity/bed/red.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/red.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/white.png", "textures/entity/bed/white.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/white.png", "restructure[bed:this]");
        tempBlocks.put("assets/minecraft/textures/entity/bed/yellow.png", "textures/entity/bed/yellow.png");
        tempOptions.put("assets/minecraft/textures/entity/bed/yellow.png", "restructure[bed:this]");
        // textures in blocks not needed?
        // bed_feet_end.png
        // bed_feet_side.png
        // bed_feet_top.png
        // bed_head_end.png
        // bed_head_side.png
        // bed_head_top.png

        //bee
        tempBlocks.put("assets/minecraft/textures/entity/bee/bee.png", "textures/entity/bee/bee.png");
        tempBlocks.put("assets/minecraft/textures/entity/bee/bee_angry.png", "textures/entity/bee/bee_angry.png");
        tempBlocks.put("assets/minecraft/textures/entity/bee/bee_angry_nectar.png", "textures/entity/bee/bee_angry_nectar.png");
        tempBlocks.put("assets/minecraft/textures/entity/bee/bee_nectar.png", "textures/entity/bee/bee_nectar.png");
        //tempBlocks.put("assets/minecraft/textures/entity/bee/bee_stinger.png", "textures/entity/bee/bee_stinger.png"); not available

        //bell
        tempBlocks.put("assets/minecraft/textures/entity/bell/bell_body.png", "textures/entity/bell/bell.png");

        //boat
        tempBlocks.put("assets/minecraft/textures/entity/boat/acacia.png", "textures/entity/boat/boat_acacia.png");
        tempBlocks.put("assets/minecraft/textures/entity/boat/birch.png", "textures/entity/boat/boat_birch.png");
        tempBlocks.put("assets/minecraft/textures/entity/boat/dark_oak.png", "textures/entity/boat/boat_darkoak.png");
        tempBlocks.put("assets/minecraft/textures/entity/boat/jungle.png", "textures/entity/boat/boat_jungle.png");
        tempBlocks.put("assets/minecraft/textures/entity/boat/oak.png", "textures/entity/boat/boat_oak.png");
        tempBlocks.put("assets/minecraft/textures/entity/boat/spruce.png", "textures/entity/boat/boat_spruce.png");

        // cat
        tempBlocks.put("assets/minecraft/textures/entity/cat/all_black.png", "textures/entity/cat/allblackcat.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/black.png", "textures/entity/cat/blackcat.png;textures/entity/cat/tuxedo.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/british_shorthair.png", "textures/entity/cat/britishshorthair.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/calico.png", "textures/entity/cat/calico.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/cat_collar.png", "textures/entity/cat/allblackcat_tame.tga;" +
                "textures/entity/cat/britishshorthair_tame.tga;" +
                "textures/entity/cat/calico_tame.tga;" +
                "textures/entity/cat/jellie_tame.tga;" +
                "textures/entity/cat/ocelot_tame.tga;" +
                "textures/entity/cat/persian_tame.tga;" +
                "textures/entity/cat/ragdoll_tame.tga;" +
                "textures/entity/cat/redtabby_tame.tga;" +
                "textures/entity/cat/tabby_tame.tga;" +
                "textures/entity/cat/tuxedo_tame.tga;" +
                "textures/entity/cat/white_tame.tga"); // combine with cats transparent
        tempOptions.put("assets/minecraft/textures/entity/cat/cat_collar.png", "combine[assets/minecraft/textures/entity/cat/all_black.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/british_shorthair.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/calico.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/jellie.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/ocelot.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/persian.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/ragdoll.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/red.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/tabby.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/black.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1];" +
                "combine[assets/minecraft/textures/entity/cat/white.png:copy,maxAlpha=4:this:copy,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/entity/cat/jellie.png", "textures/entity/cat/jellie.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/ocelot.png", "textures/entity/cat/ocelot.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/persian.png", "textures/entity/cat/persian.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/ragdoll.png", "textures/entity/cat/ragdoll.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/red.png", "textures/entity/cat/red.png;textures/entity/cat/redtabby.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/siamese.png", "textures/entity/cat/siamese.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/tabby.png", "textures/entity/cat/tabby.png");
        tempBlocks.put("assets/minecraft/textures/entity/cat/white.png", "textures/entity/cat/white.png");
        // not available
        // siamesecat.png
        // siamesecat_tame.tga
        // graytabby_tame.tga

        // chest
        //tempBlocks.put("assets/minecraft/textures/entity/chest/christmas.png", "");
        //tempBlocks.put("assets/minecraft/textures/entity/chest/christmas_left.png", "");
        //tempBlocks.put("assets/minecraft/textures/entity/chest/christmas_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/chest/ender.png", "textures/entity/chest/ender.png"); // needs restructure
        tempOptions.put("assets/minecraft/textures/entity/chest/ender.png", "restructure[chest_single:this]");
        tempBlocks.put("assets/minecraft/textures/entity/chest/normal.png", "textures/entity/chest/normal.png"); // needs restructure
        tempOptions.put("assets/minecraft/textures/entity/chest/normal.png", "restructure[chest_single:this]");
        tempBlocks.put("assets/minecraft/textures/entity/chest/normal_left.png", "textures/entity/chest/double_normal.png"); // needs restructure with normal_right
        tempOptions.put("assets/minecraft/textures/entity/chest/normal_left.png", "restructure[chest_double:this:assets/minecraft/textures/entity/chest/normal_right.png]");
        tempBlocks.put("assets/minecraft/textures/entity/chest/normal_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/chest/normal_double.png", "textures/entity/chest/double_normal.png");
        tempBlocks.put("assets/minecraft/textures/entity/chest/trapped.png", "textures/entity/chest/trapped.png"); // needs restructure
        tempOptions.put("assets/minecraft/textures/entity/chest/trapped.png", "restructure[chest_single:this]");
        tempBlocks.put("assets/minecraft/textures/entity/chest/trapped_left.png", "textures/entity/chest/trapped_double.png"); // needs restructure with trapped_right
        tempOptions.put("assets/minecraft/textures/entity/chest/trapped_left.png", "restructure[chest_double:this:assets/minecraft/textures/entity/chest/trapped_right.png]");
        tempBlocks.put("assets/minecraft/textures/entity/chest/trapped_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/chest/trapped_double.png", "textures/entity/chest/trapped_double.png");
        // textures in blocks not needed ?
        // chest_front.png
        // chest_side.png
        // chest_top.png
        // trapped_chest_front.png
        // ender_chest_front.png
        // ender_chest_side.png
        // ender_chest_top.png

        // conduit
        tempBlocks.put("assets/minecraft/textures/entity/conduit/base.png", "textures/blocks/conduit_base.png"); // needs resturcture
        tempOptions.put("assets/minecraft/textures/entity/conduit/base.png", "copy[width=0.75:height=0.75]");
        tempBlocks.put("assets/minecraft/textures/entity/conduit/break_particle.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/entity/conduit/cage.png", "textures/blocks/conduit_cage.png");
        tempBlocks.put("assets/minecraft/textures/entity/conduit/closed_eye.png", "textures/blocks/conduit_closed.png");
        tempOptions.put("assets/minecraft/textures/entity/conduit/closed_eye.png", "versionSpecific");
        texturesModeVersionspecific.put("assets/minecraft/textures/entity/conduit/closed_eye.png", "3|3|3|copy|copy[width=0.5:height=0.5]|4");
        tempBlocks.put("assets/minecraft/textures/entity/conduit/open_eye.png", "textures/blocks/conduit_open.png");
        tempOptions.put("assets/minecraft/textures/entity/conduit/open_eye.png", "versionSpecific");
        texturesModeVersionspecific.put("assets/minecraft/textures/entity/conduit/open_eye.png", "3|3|3|copy|copy[width=0.5:height=0.5]|4");
        tempBlocks.put("assets/minecraft/textures/entity/conduit/wind.png", "textures/blocks/conduit_wind_horizontal.png");
        tempOptions.put("assets/minecraft/textures/entity/conduit/wind.png", "copy[heightFrames=11]");
        tempBlocks.put("assets/minecraft/textures/entity/conduit/wind_vertical.png", "textures/blocks/conduit_wind_vertical.png");
        tempOptions.put("assets/minecraft/textures/entity/conduit/wind_vertical.png", "copy[heightFrames=11]");

        // cow
        tempBlocks.put("assets/minecraft/textures/entity/cow/brown_mooshroom.png", "textures/entity/cow/brown_mooshroom.png");
        tempBlocks.put("assets/minecraft/textures/entity/cow/cow.png", "textures/entity/cow/cow.png");
        tempBlocks.put("assets/minecraft/textures/entity/cow/red_mooshroom.png", "textures/entity/cow/mooshroom.png");

        // creeper
        tempBlocks.put("assets/minecraft/textures/entity/creeper/creeper.png", "textures/entity/creeper/creeper.png;textures/entity/skulls/creeper.png");
        tempBlocks.put("assets/minecraft/textures/entity/creeper/creeper_armor.png", "textures/entity/creeper/creeper_armor.png;textures/entity/wither_boss/wither_armor_blue.png");

        // end_crystal
        tempBlocks.put("assets/minecraft/textures/entity/end_crystal/end_crystal.png", "textures/entity/endercrystal/endercrystal.png");
        tempBlocks.put("assets/minecraft/textures/entity/end_crystal/end_crystal_beam.png", "textures/entity/endercrystal/endercrystal_beam.png");

        // enderdragon
        tempBlocks.put("assets/minecraft/textures/entity/enderdragon/dragon.png", "textures/entity/dragon/dragon.tga"); // needs restructure
        tempOptions.put("assets/minecraft/textures/entity/enderdragon/dragon.png", "restructure[dragon:this]");
        tempBlocks.put("assets/minecraft/textures/entity/enderdragon/dragon_exploding.png", "textures/entity/dragon/dragon_exploding.png");// needs restructure
        tempOptions.put("assets/minecraft/textures/entity/enderdragon/dragon_exploding.png", "restructure[dragon:this]");
        tempBlocks.put("assets/minecraft/textures/entity/enderdragon/dragon_eyes.png", "textures/entity/dragon/dragon_eyes.png");
        tempBlocks.put("assets/minecraft/textures/entity/enderdragon/dragon_fireball.png", "textures/items/dragon_fireball.png");

        // enderman
        tempBlocks.put("assets/minecraft/textures/entity/enderman/enderman.png", "textures/entity/enderman/enderman.tga"); // + transparent enderman_eyes
        tempOptions.put("assets/minecraft/textures/entity/enderman/enderman.png", "combine[this:copy,alphaColor=0-0-0:assets/minecraft/textures/entity/enderman/enderman_eyes.png:transparent=true,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/entity/enderman/enderman_eyes.png", ""); // used by enderman

        // fish // cod and tropical fish need rework
        tempBlocks.put("assets/minecraft/textures/entity/fish/cod.png", "textures/entity/fish/cod.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/cod.png", "restructure[cod:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/pufferfish.png", "textures/entity/fish/pufferfish.png");
        tempBlocks.put("assets/minecraft/textures/entity/fish/salmon.png", "textures/entity/fish/salmon.png");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a.png", "textures/entity/fish/tropical_a.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_1.png", "textures/entity/fish/tropical_a_pattern_1.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_1.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_2.png", "textures/entity/fish/tropical_a_pattern_2.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_2.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_3.png", "textures/entity/fish/tropical_a_pattern_3.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_3.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_4.png", "textures/entity/fish/tropical_a_pattern_4.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_4.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_5.png", "textures/entity/fish/tropical_a_pattern_5.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_5.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_6.png", "textures/entity/fish/tropical_a_pattern_6.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_a_pattern_6.png", "restructure[tropical_a:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b.png", "textures/entity/fish/tropical_b.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_1.png", "textures/entity/fish/tropical_b_pattern_1.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_1.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_2.png", "textures/entity/fish/tropical_b_pattern_2.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_2.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_3.png", "textures/entity/fish/tropical_b_pattern_3.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_3.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_4.png", "textures/entity/fish/tropical_b_pattern_4.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_4.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_5.png", "textures/entity/fish/tropical_b_pattern_5.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_5.png", "restructure[tropical_b:this]");
        tempBlocks.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_6.png", "textures/entity/fish/tropical_b_pattern_6.png");
        tempOptions.put("assets/minecraft/textures/entity/fish/tropical_b_pattern_6.png", "restructure[tropical_b:this]");

        // fox
        tempBlocks.put("assets/minecraft/textures/entity/fox/fox.png", "textures/entity/fox/fox.png"); // rework with sleep
        tempOptions.put("assets/minecraft/textures/entity/fox/fox.png", "restructure[fox:this:assets/minecraft/textures/entity/fox/fox_sleep.png]");
        tempBlocks.put("assets/minecraft/textures/entity/fox/fox_sleep.png", ""); // used by fox
        tempBlocks.put("assets/minecraft/textures/entity/fox/snow_fox.png", "textures/entity/fox/arctic_fox.png"); // rework with sleep
        tempOptions.put("assets/minecraft/textures/entity/fox/snow_fox.png", "restructure[fox:this:assets/minecraft/textures/entity/fox/snow_fox_sleep.png]");
        tempBlocks.put("assets/minecraft/textures/entity/fox/snow_fox_sleep.png", ""); // used by snow fox

        // ghast
        tempBlocks.put("assets/minecraft/textures/entity/ghast/ghast.png", "textures/entity/ghast/ghast.png");
        tempBlocks.put("assets/minecraft/textures/entity/ghast/ghast_shooting.png", "textures/entity/ghast/ghast_shooting.tga"); // difference to ghast is transparent, should only be eyes and mout
        tempOptions.put("assets/minecraft/textures/entity/ghast/ghast_shooting.png", "combine[assets/minecraft/textures/entity/ghast/ghast.png:copy:this:transparent=true,only_difference=true]");

        // horse all need restructure, horse2 not use when possible
        tempBlocks.put("assets/minecraft/textures/entity/horse/donkey.png", "textures/entity/horse2/donkey.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_black.png", "textures/entity/horse2/horse_black.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_brown.png", "textures/entity/horse2/horse_brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_chestnut.png", "textures/entity/horse2/horse_chestnut.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_creamy.png", "textures/entity/horse2/horse_creamy.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_darkbrown.png", "textures/entity/horse2/horse_darkbrown.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_gray.png", "textures/entity/horse2/horse_gray.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_blackdots.png", "textures/entity/horse2/horse_markings_blackdots.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_white.png", "textures/entity/horse2/horse_markings_white.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse2/horse_markings_whitedots.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse2/horse_markings_whitefield.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_skeleton.png", "textures/entity/horse2/horse_skeleton.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_white.png", "textures/entity/horse2/horse_white.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_zombie.png", "textures/entity/horse2/horse_zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/mule.png", "textures/entity/horse2/mule.png");

        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_diamond.png", "textures/entity/horse2/armor/horse_armor_diamond.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse2/armor/horse_armor_gold.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse2/armor/horse_armor_iron.png");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png", "textures/entity/horse2/armor/horse_armor_leather.tga"); // everything non colorchanging should be transparent
        tempOptions.put("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png", "copy[alpha=4:setAlphaColorOnly=true:alphaGrayThreshold=option.horseLeatherArmorThreshold]");
        // with old horse
        /*tempBlocks.put("assets/minecraft/textures/entity/horse/donkey.png", "textures/entity/horse/donkey.png;textures/entity/horse2/donkey.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/donkey.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_black.png", "textures/entity/horse/horse_black.png;textures/entity/horse2/horse_black.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_black.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_brown.png;textures/entity/horse2/horse_brown.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_brown.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_chestnut.png;textures/entity/horse2/horse_chestnut.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_chestnut.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_creamy.png;textures/entity/horse2/horse_creamy.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_creamy.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_darkbrown.png", "textures/entity/horse/horse_darkbrown.png;textures/entity/horse2/horse_darkbrown.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_darkbrown.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_gray.png;textures/entity/horse2/horse_gray.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_gray.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_blackdots.png", "textures/entity/horse/horse_markings_blackdots.png;textures/entity/horse2/horse_markings_blackdots.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_markings_blackdots.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_white.png;textures/entity/horse2/horse_markings_white.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_markings_white.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_whitedots.png;textures/entity/horse2/horse_markings_whitedots.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_markings_whitedots.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitefield.png;textures/entity/horse2/horse_markings_whitefield.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_markings_whitefield.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_skeleton.png", "textures/entity/horse/horse_skeleton.png;textures/entity/horse2/horse_skeleton.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_skeleton.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_white.png", "textures/entity/horse/horse_white.png;textures/entity/horse2/horse_white.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_white.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/horse_zombie.png", "textures/entity/horse/horse_zombie.png;textures/entity/horse2/horse_zombie.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/horse_zombie.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/mule.png", "textures/entity/horse/mule.png;textures/entity/horse2/mule.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/mule.png", "restructure[horse:this];copy");

        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_diamond.png", "textures/entity/horse/armor/horse_armor_diamond.png;textures/entity/horse2/armor/horse_armor_diamond.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/armor/horse_armor_diamond.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_gold.png;textures/entity/horse2/armor/horse_armor_gold.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/armor/horse_armor_gold.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_iron.png;textures/entity/horse2/armor/horse_armor_iron.png");
        tempOptions.put("assets/minecraft/textures/entity/horse/armor/horse_armor_iron.png", "restructure[horse:this];copy");
        tempBlocks.put("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png", "textures/entity/horse/armor/horse_armor_leather.tga;textures/entity/horse2/armor/horse_armor_leather.tga"); // everything non colorchanging should be transparent
        tempOptions.put("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png", "restructure[horse_lether:this];copy[alpha=0:setAlphaGreyOnly]");*/

        // illager
        tempBlocks.put("assets/minecraft/textures/entity/illager/evoker.png", "textures/entity/illager/evoker.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/evoker_fangs.png", "textures/entity/illager/fangs.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/illusioner.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/illager/pillager.png", "textures/entity/pillager.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/ravager.png", "textures/entity/illager/ravager.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/vex.png", "textures/entity/vex/vex.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/vex_charging.png", "textures/entity/vex/vex_charging.png");
        tempBlocks.put("assets/minecraft/textures/entity/illager/vindicator.png", "textures/entity/vindicator.png");

        // iron_golem.png
        tempBlocks.put("assets/minecraft/textures/entity/iron_golem/iron_golem.png", "textures/entity/iron_golem.png");
        tempBlocks.put("assets/minecraft/textures/entity/iron_golem/iron_golem_crackiness_high.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/iron_golem/iron_golem_crackiness_low.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/iron_golem/iron_golem_crackiness_medium.png", ""); // not available

        // llama
        tempBlocks.put("assets/minecraft/textures/entity/llama/brown.png", "textures/entity/llama/llama_brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/creamy.png", "textures/entity/llama/llama.png;textures/entity/llama/llama_creamy.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/gray.png", "textures/entity/llama/llama_gray.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/spit.png", "textures/entity/llama/spit.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/white.png", "textures/entity/llama/llama_white.png");

        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/black.png", "textures/entity/llama/decor/decor_black.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/blue.png", "textures/entity/llama/decor/decor_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/brown.png", "textures/entity/llama/decor/decor_brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/cyan.png", "textures/entity/llama/decor/decor_cyan.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/gray.png", "textures/entity/llama/decor/decor_gray.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/green.png", "textures/entity/llama/decor/decor_green.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/light_blue.png", "textures/entity/llama/decor/decor_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/light_gray.png", "textures/entity/llama/decor/decor_silver.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/lime.png", "textures/entity/llama/decor/decor_lime.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/magenta.png", "textures/entity/llama/decor/decor_magenta.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/orange.png", "textures/entity/llama/decor/decor_orange.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/pink.png", "textures/entity/llama/decor/decor_pink.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/purple.png", "textures/entity/llama/decor/decor_purple.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/red.png", "textures/entity/llama/decor/decor_red.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/trader_llama.png", "textures/entity/llama/decor/trader_llama_decor.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/white.png", "textures/entity/llama/decor/decor_white.png");
        tempBlocks.put("assets/minecraft/textures/entity/llama/decor/yellow.png", "textures/entity/llama/decor/decor_yellow.png");

        //panda
        tempBlocks.put("assets/minecraft/textures/entity/panda/aggressive_panda.png", "textures/entity/panda/panda_aggressive.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/brown_panda.png", "textures/entity/panda/panda_brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/lazy_panda.png", "textures/entity/panda/panda_lazy.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/panda.png", "textures/entity/panda/panda.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/playful_panda.png", "textures/entity/panda/panda_playful.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/weak_panda.png", "textures/entity/panda/panda_sneezy.png");
        tempBlocks.put("assets/minecraft/textures/entity/panda/worried_panda.png", "textures/entity/panda/panda_worried.png");

        // parrot
        tempBlocks.put("assets/minecraft/textures/entity/parrot/parrot_blue.png", "textures/entity/parrot/parrot_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/parrot/parrot_green.png", "textures/entity/parrot/parrot_green.png");
        tempBlocks.put("assets/minecraft/textures/entity/parrot/parrot_grey.png", "textures/entity/parrot/parrot_grey.png");
        tempBlocks.put("assets/minecraft/textures/entity/parrot/parrot_red_blue.png", "textures/entity/parrot/parrot_red_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/parrot/parrot_yellow_blue.png", "textures/entity/parrot/parrot_yellow_blue.png");

        // pig
        tempBlocks.put("assets/minecraft/textures/entity/pig/pig.png", "textures/entity/pig/pig.png");
        tempBlocks.put("assets/minecraft/textures/entity/pig/pig_saddle.png", "textures/entity/pig/pig_saddle.png;textures/entity/saddle.png"); // pig + pig_saddle
        tempOptions.put("assets/minecraft/textures/entity/pig/pig_saddle.png", "combine[assets/minecraft/textures/entity/pig/pig.png:copy:this:copy,minAlphaToCopy=1];copy");

        // projectiles
        tempBlocks.put("assets/minecraft/textures/entity/projectiles/arrow.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/projectiles/spectral_arrow.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/projectiles/tipped_arrow.png", ""); // not available

        // rabbit
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/black.png", "textures/entity/rabbit/blackrabbit.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/brown.png", "textures/entity/rabbit/brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/caerbannog.png", ""); // not available
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/gold.png", "textures/entity/rabbit/gold.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/salt.png", "textures/entity/rabbit/salt.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/toast.png", "textures/entity/rabbit/toast.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/white.png", "textures/entity/rabbit/white.png");
        tempBlocks.put("assets/minecraft/textures/entity/rabbit/white_splotched.png", "textures/entity/rabbit/white_splotched.png");

        //sheep
        tempBlocks.put("assets/minecraft/textures/entity/sheep/sheep.png", "textures/entity/sheep/sheep.tga"); // restructure with fur, nonfur transparent
        tempOptions.put("assets/minecraft/textures/entity/sheep/sheep.png", "restructure[sheep:this:assets/minecraft/textures/entity/sheep/sheep_fur.png]");
        tempBlocks.put("assets/minecraft/textures/entity/sheep/sheep_fur.png", ""); // used by sheep

        // shield not available

        tempBlocks.put("assets/minecraft/textures/entity/shield/base.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/border.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/bricks.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/circle.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/creeper.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/cross.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/curly_border.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/diagonal_left.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/diagonal_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/diagonal_up_left.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/diagonal_up_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/flower.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/globe.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/gradient.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/gradient_up.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/half_horizontal.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/half_horizontal_bottom.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/half_vertical.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/half_vertical_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/mojang.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/rhombus.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/skull.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/small_stripes.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/square_bottom_left.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/square_bottom_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/square_top_left.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/square_top_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/straight_cross.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_bottom.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_center.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_downleft.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_downright.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_left.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_middle.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_right.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/stripe_top.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/triangles_bottom.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/triangles_top.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/triangle_bottom.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/shield/triangle_top.png", "");

        // shulker
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker.png", "textures/entity/shulker/shulker_undyed.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_black.png", "textures/entity/shulker/shulker_black.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_blue.png", "textures/entity/shulker/shulker_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_brown.png", "textures/entity/shulker/shulker_brown.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_cyan.png", "textures/entity/shulker/shulker_cyan.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_gray.png", "textures/entity/shulker/shulker_gray.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_green.png", "textures/entity/shulker/shulker_green.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_light_blue.png", "textures/entity/shulker/shulker_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_light_gray.png", "textures/entity/shulker/shulker_silver.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_lime.png", "textures/entity/shulker/shulker_lime.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_magenta.png", "textures/entity/shulker/shulker_magenta.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_orange.png", "textures/entity/shulker/shulker_orange.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_pink.png", "textures/entity/shulker/shulker_pink.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_purple.png", "textures/entity/shulker/shulker_purple.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_red.png", "textures/entity/shulker/shulker_red.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_white.png", "textures/entity/shulker/shulker_white.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/shulker_yellow.png", "textures/entity/shulker/shulker_yellow.png");
        tempBlocks.put("assets/minecraft/textures/entity/shulker/spark.png", "textures/entity/shulker/spark.png");

        // signs
        tempBlocks.put("assets/minecraft/textures/entity/signs/acacia.png", "textures/entity/sign_acacia.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/birch.png", "textures/entity/sign_birch.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/dark_oak.png", "textures/entity/sign_darkoak.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/jungle.png", "textures/entity/sign_jungle.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/oak.png", "textures/entity/sign.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/spruce.png", "textures/entity/sign_spruce.png");

        // skeleton
        tempBlocks.put("assets/minecraft/textures/entity/skeleton/skeleton.png", "textures/entity/skeleton/skeleton.png;textures/entity/skulls/skeleton.png");
        tempBlocks.put("assets/minecraft/textures/entity/skeleton/stray.png", "textures/entity/skeleton/stray.png");
        tempBlocks.put("assets/minecraft/textures/entity/skeleton/stray_overlay.png", "textures/entity/skeleton/stray_overlay.png");
        tempBlocks.put("assets/minecraft/textures/entity/skeleton/wither_skeleton.png", "textures/entity/skeleton/wither_skeleton.png;textures/entity/skulls/wither_skeleton.png");

        // slime
        tempBlocks.put("assets/minecraft/textures/entity/slime/slime.png", "textures/entity/slime/slime.png");
        tempBlocks.put("assets/minecraft/textures/entity/slime/magmacube.png", "textures/entity/slime/magmacube.tga"); // body alpha 126, lava alpha 29
        tempOptions.put("assets/minecraft/textures/entity/slime/magmacube.png", "restructure[magmacube:this]");

        // spider
        tempBlocks.put("assets/minecraft/textures/entity/spider/spider.png", "textures/entity/spider/spider.png"); // + spider_eyes transparent
        tempOptions.put("assets/minecraft/textures/entity/spider/spider.png", "combine[this:copy:assets/minecraft/textures/entity/spider_eyes.png:transparent=true,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/entity/spider/cave_spider.png", "textures/entity/spider/cave_spider.png"); // spider_eyes transparent
        tempOptions.put("assets/minecraft/textures/entity/spider/cave_spider.png", "combine[this:copy:assets/minecraft/textures/entity/spider_eyes.png:transparent=true,minAlphaToCopy=1]");

        // turtle
        tempBlocks.put("assets/minecraft/textures/entity/turtle/big_sea_turtle.png", "textures/entity/sea_turtle.png");

        // villager
        tempBlocks.put("assets/minecraft/textures/entity/villager/villager.png", "textures/entity/villager2/villager.png");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/desert.png", "textures/entity/villager2/biomes/biome_desert.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/desert.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/jungle.png", "textures/entity/villager2/biomes/biome_jungle.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/jungle.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/plains.png", "textures/entity/villager2/biomes/biome_plains.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/plains.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/savanna.png", "textures/entity/villager2/biomes/biome_savanna.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/savanna.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/snow.png", "textures/entity/villager2/biomes/biome_snow.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/snow.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/swamp.png", "textures/entity/villager2/biomes/biome_swamp.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/swamp.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/type/taiga.png", "textures/entity/villager2/biomes/biome_taiga.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/type/taiga.png", "copy[alphaColor=255,255,255]");

        tempBlocks.put("assets/minecraft/textures/entity/villager/profession_level/diamond.png", "textures/entity/villager2/levels/level_diamond.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession_level/diamond.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession_level/emerald.png", "textures/entity/villager2/levels/level_emerald.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession_level/emerald.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession_level/gold.png", "textures/entity/villager2/levels/level_gold.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession_level/gold.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession_level/iron.png", "textures/entity/villager2/levels/level_iron.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession_level/iron.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession_level/stone.png", "textures/entity/villager2/levels/level_stone.png");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession_level/stone.png", "copy[alphaColor=255,255,255]");

        // transparent pixels neet to be white
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/armorer.png", "textures/entity/villager2/professions/armorer.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/armorer.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/butcher.png", "textures/entity/villager2/professions/butcher.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/butcher.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/cartographer.png", "textures/entity/villager2/professions/cartographer.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/cartographer.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/cleric.png", "textures/entity/villager2/professions/cleric.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/cleric.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/farmer.png", "textures/entity/villager2/professions/farmer.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/farmer.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/fisherman.png", "textures/entity/villager2/professions/fisherman.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/fisherman.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/fletcher.png", "textures/entity/villager2/professions/fletcher.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/fletcher.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/leatherworker.png", "textures/entity/villager2/professions/leatherworker.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/leatherworker.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/librarian.png", "textures/entity/villager2/professions/librarian.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/librarian.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/mason.png", "textures/entity/villager2/professions/mason.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/mason.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/nitwit.png", "textures/entity/villager2/professions/nitwit.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/nitwit.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/shepherd.png", "textures/entity/villager2/professions/shepherd.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/shepherd.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/toolsmith.png", "textures/entity/villager2/professions/toolsmith.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/toolsmith.png", "copy[alphaColor=255,255,255]");
        tempBlocks.put("assets/minecraft/textures/entity/villager/profession/weaponsmith.png", "textures/entity/villager2/professions/weaponsmith.tga");
        tempOptions.put("assets/minecraft/textures/entity/villager/profession/weaponsmith.png", "copy[alphaColor=255,255,255]");

        // wither
        tempBlocks.put("assets/minecraft/textures/entity/wither/wither.png", "textures/entity/wither_boss/wither.png");
        tempBlocks.put("assets/minecraft/textures/entity/wither/wither_armor.png", "textures/entity/wither_boss/wither_armor_white.png");
        tempBlocks.put("assets/minecraft/textures/entity/wither/wither_invulnerable.png", "textures/entity/wither_boss/wither_invulnerable.png");

        // wolf
        tempBlocks.put("assets/minecraft/textures/entity/wolf/wolf.png", "textures/entity/wolf/wolf.png");
        tempBlocks.put("assets/minecraft/textures/entity/wolf/wolf_angry.png", "textures/entity/wolf/wolf_angry.png");
        tempBlocks.put("assets/minecraft/textures/entity/wolf/wolf_collar.png", "textures/entity/wolf/wolf_tame.tga"); // wolf_tame as transparent + wolf collar
        tempOptions.put("assets/minecraft/textures/entity/wolf/wolf_collar.png", "combine[assets/minecraft/textures/entity/wolf/wolf_tame.png:transparent=true:this:copy,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/entity/wolf/wolf_tame.png", ""); // used by wolf_collar

        // zombie
        tempBlocks.put("assets/minecraft/textures/entity/zombie/drowned.png", "textures/entity/zombie/drowned.tga"); // restructure with outer layer, transparent needs black
        tempOptions.put("assets/minecraft/textures/entity/zombie/drowned.png", "restructure[drowned:this:assets/minecraft/textures/entity/zombie/drowned_outer_layer.png]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie/drowned_outer_layer.png", ""); // used by drowned
        tempBlocks.put("assets/minecraft/textures/entity/zombie/husk.png", "textures/entity/zombie/husk.png"); // rescale
        tempOptions.put("assets/minecraft/textures/entity/zombie/husk.png", "copy[height=0.5]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie/zombie.png", "textures/entity/zombie/zombie.png;textures/entity/skulls/zombie.png"); // rescale
        tempOptions.put("assets/minecraft/textures/entity/zombie/zombie.png", "copy[height=0.5];copy[height=0.5]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie/zombie_villager.png", ""); // not available

        // zombie villager
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/zombie_villager.png", "textures/entity/zombie_villager2/zombie-villager.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/desert.png", "textures/entity/zombie_villager2/biomes/biome-desert-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/jungle.png", "textures/entity/zombie_villager2/biomes/biome-jungle-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/plains.png", "textures/entity/zombie_villager2/biomes/biome-plains-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/savanna.png", "textures/entity/zombie_villager2/biomes/biome-savanna-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/snow.png", "textures/entity/zombie_villager2/biomes/biome-snow-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/swamp.png", "textures/entity/zombie_villager2/biomes/biome-swamp-zombie.png");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/type/taiga.png", "textures/entity/zombie_villager2/biomes/biome-taiga-zombie.png");

        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/armorer.png", "textures/entity/zombie_villager2/professions/armorer.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/armorer.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/butcher.png", "textures/entity/zombie_villager2/professions/butcher.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/butcher.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/cartographer.png", "textures/entity/zombie_villager2/professions/cartographer.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/cartographer.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/cleric.png", "textures/entity/zombie_villager2/professions/cleric.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/cleric.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/farmer.png", "textures/entity/zombie_villager2/professions/farmer.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/farmer.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/fisherman.png", "textures/entity/zombie_villager2/professions/fisherman.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/fisherman.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/fletcher.png", "textures/entity/zombie_villager2/professions/fletcher.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/fletcher.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/leatherworker.png", "textures/entity/zombie_villager2/professions/leatherworker.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/leatherworker.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/librarian.png", "textures/entity/zombie_villager2/professions/librarian.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/librarian.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/mason.png", "textures/entity/zombie_villager2/professions/mason.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/mason.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/nitwit.png", "textures/entity/zombie_villager2/professions/nitwit.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/nitwit.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/shepherd.png", "textures/entity/zombie_villager2/professions/shepherd.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/shepherd.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/toolsmith.png", "textures/entity/zombie_villager2/professions/toolsmith.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/toolsmith.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession/weaponsmith.png", "textures/entity/zombie_villager2/professions/weaponsmith.png");
        tempOptions.put("assets/minecraft/textures/entity/zombie_villager/profession/weaponsmith.png", "fill[color=255,255,255,0:onlyTransparent=false]");
        // not used
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession_level/diamond.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession_level/emerald.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession_level/gold.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession_level/iron.png", "");
        tempBlocks.put("assets/minecraft/textures/entity/zombie_villager/profession_level/stone.png", "");
        // additional bedrock textures
        // textures/entity/agent.png
        // textures/entity/camera_tripod.png
        // textures/entity/cape_invisible.png
        // textures/entity/char.png
        // textures/entity/dummy.png
        // textures/entity/enchanting_table_book_shadow.png
        // textures/entity/fireworks.png
        // textures/entity/lead_rope.png
        // textures/entity/loyalty_rope.png

        // environment
        tempBlocks.put("assets/minecraft/textures/environment/clouds.png", "textures/environment/clouds.png");
        tempBlocks.put("assets/minecraft/textures/environment/end_sky.png", "textures/environment/end_sky.png.png"); // remove color
        tempOptions.put("assets/minecraft/textures/environment/end_sky.png", "copy[grayscale=true]");
        tempBlocks.put("assets/minecraft/textures/environment/moon_phases.png", "textures/environment/moon_phases.png");
        //tempBlocks.put("assets/minecraft/textures/environment/rain.png", "textures/environment/weather.png"); not compatible
        //tempBlocks.put("assets/minecraft/textures/environment/snow.png", "textures/environment/weather.png"); not compatible

        // map
        tempBlocks.put("assets/minecraft/textures/map/map_background.png", "textures/map/map_background.png");
        tempBlocks.put("assets/minecraft/textures/map/map_background_checkerboard.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/map/map_icons.png", "textures/map/map_icons.png"); // needs restructure
        tempOptions.put("assets/minecraft/textures/map/map_icons.png", "restructure[map_icons:this]");

        // misc
        tempBlocks.put("assets/minecraft/textures/misc/enchanted_item_glint.png", "textures/misc/enchanted_item_glint.png"); // remove color
        tempOptions.put("assets/minecraft/textures/misc/enchanted_item_glint.png", "copy[grayscale=true]");
        tempBlocks.put("assets/minecraft/textures/misc/forcefield.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/misc/pumpkinblur.png", "textures/misc/pumpkinblur.png");
        tempBlocks.put("assets/minecraft/textures/misc/shadow.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/misc/underwater.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/misc/unknown_pack.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/misc/unknown_server.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/misc/vignette.png", ""); // not used

        // painting // combine to one
        tempBlocks.put("assets/minecraft/textures/painting/alban.png", "");
        tempOptions.put("assets/minecraft/textures/painting/alban.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/alban.png", "2,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/aztec.png", "");
        tempOptions.put("assets/minecraft/textures/painting/aztec.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/aztec.png", "1,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/aztec2.png", "");
        tempOptions.put("assets/minecraft/textures/painting/aztec2.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/aztec2.png", "3,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/back.png", "");
        tempOptions.put("assets/minecraft/textures/painting/back.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/back.png", "12,0;1,1;4,4");
        tempBlocks.put("assets/minecraft/textures/painting/bomb.png", "");
        tempOptions.put("assets/minecraft/textures/painting/bomb.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/bomb.png", "4,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/burning_skull.png", "");
        tempOptions.put("assets/minecraft/textures/painting/burning_skull.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/burning_skull.png", "8,12;4,4");
        tempBlocks.put("assets/minecraft/textures/painting/bust.png", "");
        tempOptions.put("assets/minecraft/textures/painting/bust.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/bust.png", "2,8;2,2");
        tempBlocks.put("assets/minecraft/textures/painting/courbet.png", "");
        tempOptions.put("assets/minecraft/textures/painting/courbet.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/courbet.png", "2,2;1,2");
        tempBlocks.put("assets/minecraft/textures/painting/creebet.png", "");
        tempOptions.put("assets/minecraft/textures/painting/creebet.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/creebet.png", "8,2;1,2");
        tempBlocks.put("assets/minecraft/textures/painting/donkey_kong.png", "");
        tempOptions.put("assets/minecraft/textures/painting/donkey_kong.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/donkey_kong.png", "12,7;4,3");
        tempBlocks.put("assets/minecraft/textures/painting/fighters.png", "");
        tempOptions.put("assets/minecraft/textures/painting/fighters.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/fighters.png", "0,6;4,2");
        tempBlocks.put("assets/minecraft/textures/painting/graham.png", "");
        tempOptions.put("assets/minecraft/textures/painting/graham.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/graham.png", "1,4;1,2");
        tempBlocks.put("assets/minecraft/textures/painting/kebab.png", "");
        tempOptions.put("assets/minecraft/textures/painting/kebab.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/kebab.png", "0,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/match.png", "");
        tempOptions.put("assets/minecraft/textures/painting/match.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/match.png", "0,8;2,2");
        tempBlocks.put("assets/minecraft/textures/painting/pigscene.png", "");
        tempOptions.put("assets/minecraft/textures/painting/pigscene.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/pigscene.png", "4,12;4,4");
        tempBlocks.put("assets/minecraft/textures/painting/plant.png", "");
        tempOptions.put("assets/minecraft/textures/painting/plant.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/plant.png", "5,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/pointer.png", "");
        tempOptions.put("assets/minecraft/textures/painting/pointer.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/pointer.png", "0,12;4,4");
        tempBlocks.put("assets/minecraft/textures/painting/pool.png", "");
        tempOptions.put("assets/minecraft/textures/painting/pool.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/pool.png", "0,2;2,1");
        tempBlocks.put("assets/minecraft/textures/painting/sea.png", "");
        tempOptions.put("assets/minecraft/textures/painting/sea.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/sea.png", "4,2;2,1");
        tempBlocks.put("assets/minecraft/textures/painting/skeleton.png", "");
        tempOptions.put("assets/minecraft/textures/painting/skeleton.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/skeleton.png", "12,4;4,3");
        tempBlocks.put("assets/minecraft/textures/painting/skull_and_roses.png", "");
        tempOptions.put("assets/minecraft/textures/painting/skull_and_roses.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/skull_and_roses.png", "8,8;2,2");
        tempBlocks.put("assets/minecraft/textures/painting/stage.png", "");
        tempOptions.put("assets/minecraft/textures/painting/stage.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/stage.png", "4,8;2,2");
        tempBlocks.put("assets/minecraft/textures/painting/sunset.png", "");
        tempOptions.put("assets/minecraft/textures/painting/sunset.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/sunset.png", "6,2;2,1");
        tempBlocks.put("assets/minecraft/textures/painting/void.png", "");
        tempOptions.put("assets/minecraft/textures/painting/void.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/void.png", "6,8;2,2");
        tempBlocks.put("assets/minecraft/textures/painting/wanderer.png", "");
        tempOptions.put("assets/minecraft/textures/painting/wanderer.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/wanderer.png", "0,4;1,2");
        tempBlocks.put("assets/minecraft/textures/painting/wasteland.png", "");
        tempOptions.put("assets/minecraft/textures/painting/wasteland.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/wasteland.png", "6,0;1,1");
        tempBlocks.put("assets/minecraft/textures/painting/wither.png", "");
        tempOptions.put("assets/minecraft/textures/painting/wither.png", "function[painting]");
        paintingOffset.put("assets/minecraft/textures/painting/wither.png", "10,8;2,2");

        // particle combine to campfire_smoke.png and particles.png
        // campfire smoke
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_0.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_1.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_10.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_10.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_11.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_11.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_2.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_3.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_4.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_5.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_6.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_7.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_8.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_8.png", "function[campfire_smoke]");
        tempBlocks.put("assets/minecraft/textures/particle/big_smoke_9.png", "");
        tempOptions.put("assets/minecraft/textures/particle/big_smoke_9.png", "function[campfire_smoke]");

        // other particles
        tempBlocks.put("assets/minecraft/textures/particle/angry.png", "");
        tempOptions.put("assets/minecraft/textures/particle/angry.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/angry.png", "1,5");
        tempBlocks.put("assets/minecraft/textures/particle/bubble.png", "");
        tempOptions.put("assets/minecraft/textures/particle/bubble.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/bubble.png", "0,2");
        tempBlocks.put("assets/minecraft/textures/particle/critical_hit.png", "");
        tempOptions.put("assets/minecraft/textures/particle/critical_hit.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/critical_hit.png", "1,4");
        tempBlocks.put("assets/minecraft/textures/particle/drip_fall.png", "");
        tempOptions.put("assets/minecraft/textures/particle/drip_fall.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/drip_fall.png", "1,7");
        tempBlocks.put("assets/minecraft/textures/particle/drip_hang.png", "");
        tempOptions.put("assets/minecraft/textures/particle/drip_hang.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/drip_hang.png", "0,7");
        tempBlocks.put("assets/minecraft/textures/particle/drip_land.png", "");
        tempOptions.put("assets/minecraft/textures/particle/drip_land.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/drip_land.png", "2,7.25");
        tempBlocks.put("assets/minecraft/textures/particle/effect_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_0.png", "0,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_1.png", "1,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_2.png", "2,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_3.png", "3,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_4.png", "4,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_5.png", "5,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_6.png", "6,8");
        tempBlocks.put("assets/minecraft/textures/particle/effect_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/effect_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/effect_7.png", "7,8");
        tempBlocks.put("assets/minecraft/textures/particle/enchanted_hit.png", "");
        tempOptions.put("assets/minecraft/textures/particle/enchanted_hit.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/enchanted_hit.png", "2,4");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_0.png", "0,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_1.png", "1,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_10.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_10.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_10.png", "10,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_11.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_11.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_11.png", "11,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_12.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_12.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_12.png", "12,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_13.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_13.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_13.png", "13,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_14.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_14.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_14.png", "14,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_15.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_15.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_15.png", "15,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_2.png", "2,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_3.png", "3,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_4.png", "4,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_5.png", "5,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_6.png", "6,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_7.png", "7,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_8.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_8.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_8.png", "8,10");
        tempBlocks.put("assets/minecraft/textures/particle/explosion_9.png", "");
        tempOptions.put("assets/minecraft/textures/particle/explosion_9.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/explosion_9.png", "9,10");
        tempBlocks.put("assets/minecraft/textures/particle/flame.png", "");
        tempOptions.put("assets/minecraft/textures/particle/flame.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/flame.png", "0,3");
        tempBlocks.put("assets/minecraft/textures/particle/flash.png", "");
        tempOptions.put("assets/minecraft/textures/particle/flash.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/flash.png", "4,2");
        tempBlocks.put("assets/minecraft/textures/particle/generic_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_0.png", "0,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_1.png", "1,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_2.png", "2,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_3.png", "3,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_4.png", "4,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_5.png", "5,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_6.png", "6,0");
        tempBlocks.put("assets/minecraft/textures/particle/generic_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/generic_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/generic_7.png", "7,0");
        tempBlocks.put("assets/minecraft/textures/particle/glint.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glint.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glint.png", "2,5");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_0.png", "0,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_1.png", "1,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_2.png", "2,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_3.png", "3,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_4.png", "4,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_5.png", "5,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_6.png", "6,11");
        tempBlocks.put("assets/minecraft/textures/particle/glitter_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/glitter_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/glitter_7.png", "7,11");
        tempBlocks.put("assets/minecraft/textures/particle/heart.png", "");
        tempOptions.put("assets/minecraft/textures/particle/heart.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/heart.png", "0,5");
        tempBlocks.put("assets/minecraft/textures/particle/lava.png", "");
        tempOptions.put("assets/minecraft/textures/particle/lava.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/lava.png", "1,3");
        tempBlocks.put("assets/minecraft/textures/particle/nautilus.png", "");
        tempOptions.put("assets/minecraft/textures/particle/nautilus.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/nautilus.png", "0,13;1,13;2,13;3,13;4,13;5,13;6,13;7,13;8,13;9,13;10,13;11,13");
        tempBlocks.put("assets/minecraft/textures/particle/note.png", "");
        tempOptions.put("assets/minecraft/textures/particle/note.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/note.png", "0,4");
        tempBlocks.put("assets/minecraft/textures/particle/sga_a.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_a.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_a.png", "1,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_b.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_b.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_b.png", "2,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_c.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_c.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_c.png", "3,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_d.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_d.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_d.png", "4,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_e.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_e.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_e.png", "5,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_f.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_f.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_f.png", "6,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_g.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_g.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_g.png", "7,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_h.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_h.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_h.png", "8,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_i.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_i.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_i.png", "9,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_j.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_j.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_j.png", "10,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_k.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_k.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_k.png", "11,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_l.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_l.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_l.png", "12,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_m.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_m.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_m.png", "13,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_n.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_n.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_n.png", "14,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_o.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_o.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_o.png", "15,14");
        tempBlocks.put("assets/minecraft/textures/particle/sga_p.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_p.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_p.png", "0,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_q.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_q.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_q.png", "1,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_r.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_r.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_r.png", "2,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_s.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_s.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_s.png", "3,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_t.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_t.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_t.png", "4,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_u.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_u.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_u.png", "5,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_v.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_v.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_v.png", "6,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_w.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_w.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_w.png", "7,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_x.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_x.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_x.png", "8,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_y.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_y.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_y.png", "9,15");
        tempBlocks.put("assets/minecraft/textures/particle/sga_z.png", "");
        tempOptions.put("assets/minecraft/textures/particle/sga_z.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/sga_z.png", "10,15");
        tempBlocks.put("assets/minecraft/textures/particle/spark_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_0.png", "0,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_1.png", "1,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_2.png", "2,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_3.png", "3,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_4.png", "4,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_5.png", "5,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_6.png", "6,12");
        tempBlocks.put("assets/minecraft/textures/particle/spark_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spark_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spark_7.png", "7,12");
        tempBlocks.put("assets/minecraft/textures/particle/spell_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_0.png", "0,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_1.png", "1,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_2.png", "2,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_3.png", "3,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_4.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_4.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_4.png", "4,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_5.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_5.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_5.png", "5,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_6.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_6.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_6.png", "6,9");
        tempBlocks.put("assets/minecraft/textures/particle/spell_7.png", "");
        tempOptions.put("assets/minecraft/textures/particle/spell_7.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/spell_7.png", "7,9");
        tempBlocks.put("assets/minecraft/textures/particle/splash_0.png", "");
        tempOptions.put("assets/minecraft/textures/particle/splash_0.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/splash_0.png", "0,1;3,1");
        tempBlocks.put("assets/minecraft/textures/particle/splash_1.png", "");
        tempOptions.put("assets/minecraft/textures/particle/splash_1.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/splash_1.png", "4,1");
        tempBlocks.put("assets/minecraft/textures/particle/splash_2.png", "");
        tempOptions.put("assets/minecraft/textures/particle/splash_2.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/splash_2.png", "5,1");
        tempBlocks.put("assets/minecraft/textures/particle/splash_3.png", "");
        tempOptions.put("assets/minecraft/textures/particle/splash_3.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/splash_3.png", "6,1");
        tempBlocks.put("assets/minecraft/textures/particle/footprint.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/damage.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/bubble_pop_0.png", "");// not available
        tempBlocks.put("assets/minecraft/textures/particle/bubble_pop_1.png", "");// not available
        tempBlocks.put("assets/minecraft/textures/particle/bubble_pop_2.png", "");// not available
        tempBlocks.put("assets/minecraft/textures/particle/bubble_pop_3.png", "");// not available
        tempBlocks.put("assets/minecraft/textures/particle/bubble_pop_4.png", "");// not available
        tempBlocks.put("assets/minecraft/textures/particle/sweep_0.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_1.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_2.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_3.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_4.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_5.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_6.png", "");
        tempBlocks.put("assets/minecraft/textures/particle/sweep_7.png", "");

        /*
        barrier.png
border.png
bubble_column_down_top_a.png
bubble_column_down_top_b.png
bubble_column_down_top_c.png
bubble_column_down_top_d.png
bubble_column_inner_a.png
bubble_column_inner_b.png
bubble_column_outer_a.png
bubble_column_outer_b.png
bubble_column_outer_c.png
bubble_column_outer_d.png
bubble_column_outer_e.png
bubble_column_outer_f.png
bubble_column_outer_g.png
bubble_column_outer_h.png
bubble_column_up_top_a.png
bubble_column_up_top_b.png
bubble_column_up_top_c.png
bubble_column_up_top_d.png
build_allow.png
build_deny.png
camera_back.png
camera_front.png
camera_side.png
camera_top.png
command_block.png
end_gateway.png
end_portal.png
flower_paeonia.png
flower_rose_blue.png
glowing_obsidian.png
missing_tile.png
reactor_core_stage_0.png
reactor_core_stage_1.png
reactor_core_stage_2.png
stonecutter_bottom.png
stonecutter_other_side.png
stonecutter_side.png
stonecutter_top.png
structure_air.png
structure_block_export.png
structure_void.png
tallgrass.png
         */

        // items
        tempBlocks.put("assets/minecraft/textures/item/barrier.png", "textures/blocks/barrier.png");
        tempBlocks.put("assets/minecraft/textures/item/apple.png", "textures/items/apple.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_apple.png", "textures/items/apple_golden.png");
        tempBlocks.put("assets/minecraft/textures/item/armor_stand.png", "textures/items/armor_stand.png");
        tempBlocks.put("assets/minecraft/textures/item/arrow.png", "textures/items/arrow.png");
        tempBlocks.put("assets/minecraft/textures/item/bamboo.png", "textures/items/bamboo.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_beef.png", "textures/items/beef_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/beef.png", "textures/items/beef_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/beetroot.png", "textures/items/beetroot.png");
        tempBlocks.put("assets/minecraft/textures/item/beetroot_soup.png", "textures/items/beetroot_soup.png");
        tempBlocks.put("assets/minecraft/textures/item/blaze_powder.png", "textures/items/blaze_powder.png");
        tempBlocks.put("assets/minecraft/textures/item/blaze_rod.png", "textures/items/blaze_rod.png");
        tempBlocks.put("assets/minecraft/textures/item/acacia_boat.png", "textures/items/boat_acacia.png");
        tempBlocks.put("assets/minecraft/textures/item/birch_boat.png", "textures/items/boat_birch.png");
        tempBlocks.put("assets/minecraft/textures/item/dark_oak_boat.png", "textures/items/boat_darkoak.png");
        tempBlocks.put("assets/minecraft/textures/item/jungle_boat.png", "textures/items/boat_jungle.png");
        tempBlocks.put("assets/minecraft/textures/item/oak_boat.png", "textures/items/boat_oak.png");
        tempBlocks.put("assets/minecraft/textures/item/spruce_boat.png", "textures/items/boat_spruce.png");
        tempBlocks.put("assets/minecraft/textures/item/bone.png", "textures/items/bone.png");
        tempBlocks.put("assets/minecraft/textures/item/enchanted_book.png", "textures/items/book_enchanted.png");
        tempBlocks.put("assets/minecraft/textures/item/book.png", "textures/items/book_normal.png");
        tempBlocks.put("assets/minecraft/textures/item/knowledge_book.png", "textures/items/book_portfolio.png");
        tempBlocks.put("assets/minecraft/textures/item/writable_book.png", "textures/items/book_writable.png");
        tempBlocks.put("assets/minecraft/textures/item/written_book.png", "textures/items/book_written.png");
        tempBlocks.put("assets/minecraft/textures/item/bowl.png", "textures/items/bowl.png");
        tempBlocks.put("assets/minecraft/textures/item/bow_pulling_0.png", "textures/items/bow_pulling_0.png");
        tempBlocks.put("assets/minecraft/textures/item/bow_pulling_1.png", "textures/items/bow_pulling_1.png");
        tempBlocks.put("assets/minecraft/textures/item/bow_pulling_2.png", "textures/items/bow_pulling_2.png");
        tempBlocks.put("assets/minecraft/textures/item/bow.png", "textures/items/bow_standby.png");
        tempBlocks.put("assets/minecraft/textures/item/bread.png", "textures/items/bread.png");
        tempBlocks.put("assets/minecraft/textures/item/brewing_stand.png", "textures/items/brewing_stand.png");
        tempBlocks.put("assets/minecraft/textures/item/brick.png", "textures/items/brick.png");
        tempBlocks.put("assets/minecraft/textures/item/broken_elytra.png", "textures/items/broken_elytra.png");
        tempBlocks.put("assets/minecraft/textures/item/cod_bucket.png", "textures/items/bucket_cod.png");
        tempBlocks.put("assets/minecraft/textures/item/bucket.png", "textures/items/bucket_empty.png");
        tempBlocks.put("assets/minecraft/textures/item/lava_bucket.png", "textures/items/bucket_lava.png");
        tempBlocks.put("assets/minecraft/textures/item/milk_bucket.png", "textures/items/bucket_milk.png");
        tempBlocks.put("assets/minecraft/textures/item/pufferfish_bucket.png", "textures/items/bucket_pufferfish.png");
        tempBlocks.put("assets/minecraft/textures/item/salmon_bucket.png", "textures/items/bucket_salmon.png");
        tempBlocks.put("assets/minecraft/textures/item/tropical_fish_bucket.png", "textures/items/bucket_tropical.png");
        tempBlocks.put("assets/minecraft/textures/item/water_bucket.png", "textures/items/bucket_water.png");
        tempBlocks.put("assets/minecraft/textures/item/cake.png", "textures/items/cake.png");
        tempBlocks.put("assets/minecraft/textures/item/campfire.png", "textures/items/campfire.png");
        tempBlocks.put("assets/minecraft/textures/item/carrot.png", "textures/items/carrot.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_carrot.png", "textures/items/carrot_golden.png");
        tempBlocks.put("assets/minecraft/textures/item/carrot_on_a_stick.png", "textures/items/carrot_on_a_stick.png");
        tempBlocks.put("assets/minecraft/textures/item/cauldron.png", "textures/items/cauldron.png");
        tempBlocks.put("assets/minecraft/textures/item/chainmail_boots.png", "textures/items/chainmail_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/chainmail_chestplate.png", "textures/items/chainmail_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/chainmail_helmet.png", "textures/items/chainmail_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/chainmail_leggings.png", "textures/items/chainmail_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/charcoal.png", "textures/items/charcoal.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_chicken.png", "textures/items/chicken_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/chicken.png", "textures/items/chicken_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/chorus_fruit.png", "textures/items/chorus_fruit.png");
        tempBlocks.put("assets/minecraft/textures/item/popped_chorus_fruit.png", "textures/items/chorus_fruit_popped.png");
        tempBlocks.put("assets/minecraft/textures/item/clay_ball.png", "textures/items/clay_ball.png");
        tempBlocks.put("assets/minecraft/textures/item/coal.png", "textures/items/coal.png");
        tempBlocks.put("assets/minecraft/textures/item/comparator.png", "textures/items/comparator.png");
        tempBlocks.put("assets/minecraft/textures/item/cookie.png", "textures/items/cookie.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_arrow.png", "textures/items/crossbow_arrow.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_firework.png", "textures/items/crossbow_firework.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_pulling_0.png", "textures/items/crossbow_pulling_0.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_pulling_1.png", "textures/items/crossbow_pulling_1.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_pulling_2.png", "textures/items/crossbow_pulling_2.png");
        tempBlocks.put("assets/minecraft/textures/item/crossbow_standby.png", "textures/items/crossbow_standby.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond.png", "textures/items/diamond.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_axe.png", "textures/items/diamond_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_boots.png", "textures/items/diamond_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_chestplate.png", "textures/items/diamond_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_helmet.png", "textures/items/diamond_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_hoe.png", "textures/items/diamond_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_horse_armor.png", "textures/items/diamond_horse_armor.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_leggings.png", "textures/items/diamond_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_pickaxe.png", "textures/items/diamond_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_shovel.png", "textures/items/diamond_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/diamond_sword.png", "textures/items/diamond_sword.png");
        tempBlocks.put("assets/minecraft/textures/item/acacia_door.png", "textures/items/door_acacia.png");
        tempBlocks.put("assets/minecraft/textures/item/birch_door.png", "textures/items/door_birch.png");
        tempBlocks.put("assets/minecraft/textures/item/dark_oak_door.png", "textures/items/door_dark_oak.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_door.png", "textures/items/door_iron.png");
        tempBlocks.put("assets/minecraft/textures/item/jungle_door.png", "textures/items/door_jungle.png");
        tempBlocks.put("assets/minecraft/textures/item/spruce_door.png", "textures/items/door_spruce.png");
        tempBlocks.put("assets/minecraft/textures/item/oak_door.png", "textures/items/door_wood.png");
        tempBlocks.put("assets/minecraft/textures/item/dragon_breath.png", "textures/items/dragons_breath.png");
        tempBlocks.put("assets/minecraft/textures/item/dried_kelp.png", "textures/items/dried_kelp.png");
        tempBlocks.put("assets/minecraft/textures/item/ink_sac.png", "textures/items/dye_powder_black.png");
        tempBlocks.put("assets/minecraft/textures/item/black_dye.png", "textures/items/dye_powder_black_new.png");
        tempBlocks.put("assets/minecraft/textures/item/lapis_lazuli.png", "textures/items/dye_powder_blue.png");
        tempBlocks.put("assets/minecraft/textures/item/blue_dye.png", "textures/items/dye_powder_blue_new.png");
        tempBlocks.put("assets/minecraft/textures/item/brown_dye.png", "textures/items/dye_powder_brown.png");
        tempBlocks.put("assets/minecraft/textures/item/cocoa_beans.png", "textures/items/dye_powder_brown_new.png");
        tempBlocks.put("assets/minecraft/textures/item/cyan_dye.png", "textures/items/dye_powder_cyan.png");
        tempBlocks.put("assets/minecraft/textures/item/gray_dye.png", "textures/items/dye_powder_gray.png");
        tempBlocks.put("assets/minecraft/textures/item/green_dye.png", "textures/items/dye_powder_green.png");
        tempBlocks.put("assets/minecraft/textures/item/light_blue_dye.png", "textures/items/dye_powder_light_blue.png");
        tempBlocks.put("assets/minecraft/textures/item/light_gray_dye.png", "textures/items/dye_powder_silver.png");
        tempBlocks.put("assets/minecraft/textures/item/lime_dye.png", "textures/items/dye_powder_lime.png");
        tempBlocks.put("assets/minecraft/textures/item/magenta_dye.png", "textures/items/dye_powder_magenta.png");
        tempBlocks.put("assets/minecraft/textures/item/orange_dye.png", "textures/items/dye_powder_orange.png");
        tempBlocks.put("assets/minecraft/textures/item/pink_dye.png", "textures/items/dye_powder_pink.png");
        tempBlocks.put("assets/minecraft/textures/item/purple_dye.png", "textures/items/dye_powder_purple.png");
        tempBlocks.put("assets/minecraft/textures/item/red_dye.png", "textures/items/dye_powder_red.png");
        tempBlocks.put("assets/minecraft/textures/item/bone_meal.png", "textures/items/dye_powder_white.png");
        tempBlocks.put("assets/minecraft/textures/item/white_dye.png", "textures/items/dye_powder_white_new.png");
        tempBlocks.put("assets/minecraft/textures/item/yellow_dye.png", "textures/items/dye_powder_yellow.png");
        tempBlocks.put("assets/minecraft/textures/item/egg.png", "textures/items/egg.png");
        tempBlocks.put("assets/minecraft/textures/item/elytra.png", "textures/items/elytra.png");
        tempBlocks.put("assets/minecraft/textures/item/emerald.png", "textures/items/emerald.png");
        tempBlocks.put("assets/minecraft/textures/item/empty_armor_slot_boots.png", "textures/items/empty_armor_slot_boots.png;textures/ui/empty_armor_slot_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/empty_armor_slot_chestplate.png", "textures/items/empty_armor_slot_chestplate.png;textures/ui/empty_armor_slot_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/empty_armor_slot_helmet.png", "textures/items/empty_armor_slot_helmet.png;textures/ui/empty_armor_slot_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/empty_armor_slot_leggings.png", "textures/items/empty_armor_slot_leggings.png;textures/ui/empty_armor_slot_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/empty_armor_slot_shield.png", "textures/items/empty_armor_slot_shield.png;textures/ui/empty_armor_slot_shield.png");
        tempBlocks.put("assets/minecraft/textures/item/ender_eye.png", "textures/items/ender_eye.png");
        tempBlocks.put("assets/minecraft/textures/item/ender_pearl.png", "textures/items/ender_pearl.png");
        tempBlocks.put("assets/minecraft/textures/item/end_crystal.png", "textures/items/end_crystal.png");
        tempBlocks.put("assets/minecraft/textures/item/experience_bottle.png", "textures/items/experience_bottle.png");
        tempBlocks.put("assets/minecraft/textures/item/feather.png", "textures/items/feather.png");
        tempBlocks.put("assets/minecraft/textures/item/fire_charge.png", "textures/items/fireball.png");
        tempBlocks.put("assets/minecraft/textures/item/firework_rocket.png", "textures/items/fireworks.png");
        tempBlocks.put("assets/minecraft/textures/item/firework_star.png", "textures/items/fireworks_charge.tga");
        tempOptions.put("assets/minecraft/textures/item/firework_star.png", "combine[this:copy,maxAlpha=4:assets/minecraft/textures/item/firework_star_overlay.png:copy,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/firework_star_overlay.png", ""); // used by firework_star
        tempBlocks.put("assets/minecraft/textures/item/fishing_rod_cast.png", "textures/items/fishing_rod_cast.png");
        tempBlocks.put("assets/minecraft/textures/item/fishing_rod.png", "textures/items/fishing_rod_uncast.png");
        tempBlocks.put("assets/minecraft/textures/item/tropical_fish.png", "textures/items/fish_clownfish_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_cod.png", "textures/items/fish_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/pufferfish.png", "textures/items/fish_pufferfish_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/cod.png", "textures/items/fish_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_salmon.png", "textures/items/fish_salmon_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/salmon.png", "textures/items/fish_salmon_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/flint.png", "textures/items/flint.png");
        tempBlocks.put("assets/minecraft/textures/item/flint_and_steel.png", "textures/items/flint_and_steel.png");
        tempBlocks.put("assets/minecraft/textures/item/flower_pot.png", "textures/items/flower_pot.png");
        tempBlocks.put("assets/minecraft/textures/item/ghast_tear.png", "textures/items/ghast_tear.png");
        tempBlocks.put("assets/minecraft/textures/item/glass_bottle.png", ""); // not used
        tempBlocks.put("assets/minecraft/textures/item/glowstone_dust.png", "textures/items/glowstone_dust.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_axe.png", "textures/items/gold_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_boots.png", "textures/items/gold_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_chestplate.png", "textures/items/gold_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_helmet.png", "textures/items/gold_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_hoe.png", "textures/items/gold_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_horse_armor.png", "textures/items/gold_horse_armor.png");
        tempBlocks.put("assets/minecraft/textures/item/gold_ingot.png", "textures/items/gold_ingot.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_leggings.png", "textures/items/gold_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/gold_nugget.png", "textures/items/gold_nugget.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_pickaxe.png", "textures/items/gold_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_shovel.png", "textures/items/gold_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/golden_sword.png", "textures/items/gold_sword.png");
        tempBlocks.put("assets/minecraft/textures/item/gunpowder.png", "textures/items/gunpowder.png");
        tempBlocks.put("assets/minecraft/textures/item/heart_of_the_sea.png", "textures/items/heartofthesea_closed.png");
        tempBlocks.put("assets/minecraft/textures/item/honeycomb.png", "textures/items/honeycomb.png");
        tempBlocks.put("assets/minecraft/textures/item/honey_bottle.png", "textures/items/honey_bottle.png");
        tempBlocks.put("assets/minecraft/textures/item/hopper.png", "textures/items/hopper.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_axe.png", "textures/items/iron_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_boots.png", "textures/items/iron_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_chestplate.png", "textures/items/iron_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_helmet.png", "textures/items/iron_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_hoe.png", "textures/items/iron_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_horse_armor.png", "textures/items/iron_horse_armor.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_ingot.png", "textures/items/iron_ingot.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_leggings.png", "textures/items/iron_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_nugget.png", "textures/items/iron_nugget.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_pickaxe.png", "textures/items/iron_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_shovel.png", "textures/items/iron_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/iron_sword.png", "textures/items/iron_sword.png");
        tempBlocks.put("assets/minecraft/textures/item/item_frame.png", "textures/items/item_frame.png");
        tempBlocks.put("assets/minecraft/textures/item/kelp.png", "textures/items/kelp.png");
        tempBlocks.put("assets/minecraft/textures/item/lantern.png", "textures/items/lantern.png");
        tempBlocks.put("assets/minecraft/textures/item/lead.png", "textures/items/lead.png");
        tempBlocks.put("assets/minecraft/textures/item/leather.png", "textures/items/leather.png");
        tempBlocks.put("assets/minecraft/textures/item/leather_boots.png", "textures/items/leather_boots.tga");
        tempOptions.put("assets/minecraft/textures/item/leather_boots.png", "combine[this:copy:assets/minecraft/textures/item/leather_boots_overlay.png:copy,maxAlpha=3,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/leather_boots_overlay.png", ""); //used by leather_boots
        tempBlocks.put("assets/minecraft/textures/item/leather_chestplate.png", "textures/items/leather_chestplate.png");
        tempOptions.put("assets/minecraft/textures/item/leather_chestplate.png", "combine[this:copy:assets/minecraft/textures/item/leather_chestplate_overlay.png:copy,maxAlpha=3,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/leather_chestplate_overlay.png", ""); //used by leather_chestplate
        tempBlocks.put("assets/minecraft/textures/item/leather_helmet.png", "textures/items/leather_helmet.tga");
        tempOptions.put("assets/minecraft/textures/item/leather_helmet.png", "combine[this:copy:assets/minecraft/textures/item/leather_helmet_overlay.png:copy,maxAlpha=3,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/leather_helmet_overlay.png", ""); //used by leather_helmet
        tempBlocks.put("assets/minecraft/textures/item/leather_horse_armor.png", "textures/items/leather_horse_armor.tga");
        tempOptions.put("assets/minecraft/textures/item/leather_horse_armor.png", "copy[alpha=4:setAlphaColorOnly=true:alphaGrayThreshold=option.horseLeatherItemThreshold]");
        tempBlocks.put("assets/minecraft/textures/item/leather_leggings.png", "textures/items/leather_leggings.tga");
        tempOptions.put("assets/minecraft/textures/item/leather_leggings.png", "combine[this:copy:assets/minecraft/textures/item/leather_leggings_overlay.png:copy,maxAlpha=3,minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/leather_leggings_overlay.png", ""); //used by leather_leggings
        tempBlocks.put("assets/minecraft/textures/item/magma_cream.png", "textures/items/magma_cream.png");
        tempBlocks.put("assets/minecraft/textures/item/map.png", "textures/items/map_empty.png");
        tempBlocks.put("assets/minecraft/textures/item/filled_map.png", "" +
                "textures/items/map_filled.png;" +
                "textures/items/map_mansion.png;" +
                "textures/items/map_monument.png;" +
                "textures/items/map_nautilus.png");
        tempOptions.put("assets/minecraft/textures/item/filled_map.png", "" +
                "combine[this:copy:assets/minecraft/textures/item/filled_map_markings.png:blend=true,minAlphaToCopy=1,colorMultInt=4603950];" + // -12173266 in source
                "combine[this:copy:assets/minecraft/textures/item/filled_map_markings.png:blend=true,minAlphaToCopy=1,colorMultInt=5393476];" +
                "combine[this:copy:assets/minecraft/textures/item/filled_map_markings.png:blend=true,minAlphaToCopy=1,colorMultInt=3830373];" +
                "combine[this:copy:assets/minecraft/textures/item/filled_map_markings.png:blend=true,minAlphaToCopy=1,colorMultInt=4603950]");// bedrock uses 6773421
        tempBlocks.put("assets/minecraft/textures/item/filled_map_markings.png", ""); // used by filled_map
        //tempBlocks.put("", "textures/items/map_locked.png"); not available
        tempBlocks.put("assets/minecraft/textures/item/melon_slice.png", "textures/items/melon.png");
        tempBlocks.put("assets/minecraft/textures/item/glistering_melon_slice.png", "textures/items/melon_speckled.png");
        tempBlocks.put("assets/minecraft/textures/item/chest_minecart.png", "textures/items/minecart_chest.png");
        tempBlocks.put("assets/minecraft/textures/item/command_block_minecart.png", "textures/items/minecart_command_block.png");
        tempBlocks.put("assets/minecraft/textures/item/furnace_minecart.png", "textures/items/minecart_furnace.png");
        tempBlocks.put("assets/minecraft/textures/item/hopper_minecart.png", "textures/items/minecart_hopper.png");
        tempBlocks.put("assets/minecraft/textures/item/minecart.png", "textures/items/minecart_normal.png");
        tempBlocks.put("assets/minecraft/textures/item/tnt_minecart.png", "textures/items/minecart_tnt.png");
        tempBlocks.put("assets/minecraft/textures/item/mushroom_stew.png", "textures/items/mushroom_stew.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_mutton.png", "textures/items/mutton_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/mutton.png", "textures/items/mutton_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/name_tag.png", "textures/items/name_tag.png");
        tempBlocks.put("assets/minecraft/textures/item/nautilus_shell.png", "textures/items/nautilus.png");
        tempBlocks.put("assets/minecraft/textures/item/nether_brick.png", "textures/items/netherbrick.png");
        tempBlocks.put("assets/minecraft/textures/item/nether_star.png", "textures/items/nether_star.png");
        tempBlocks.put("assets/minecraft/textures/item/nether_wart.png", "textures/items/nether_wart.png");
        tempBlocks.put("assets/minecraft/textures/item/painting.png", "textures/items/painting.png");
        tempBlocks.put("assets/minecraft/textures/item/paper.png", "textures/items/paper.png");
        tempBlocks.put("assets/minecraft/textures/item/phantom_membrane.png", "textures/items/phantom_membrane.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_porkchop.png", "textures/items/porkchop_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/porkchop.png", "textures/items/porkchop_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/potato.png", "textures/items/potato.png");
        tempBlocks.put("assets/minecraft/textures/item/baked_potato.png", "textures/items/potato_baked.png");
        tempBlocks.put("assets/minecraft/textures/item/poisonous_potato.png", "textures/items/potato_poisonous.png");
        tempBlocks.put("assets/minecraft/textures/item/potion.png", "textures/items/potion_bottle_empty.png");
        tempBlocks.put("assets/minecraft/textures/item/lingering_potion.png", "textures/items/potion_bottle_lingering_empty.png");
        tempBlocks.put("assets/minecraft/textures/item/potion_overlay.png", "textures/items/potion_overlay.png;" +
                // default potions
                "textures/items/potion_bottle_absorption.png;" +
                "textures/items/potion_bottle_blindness.png;" +
                "textures/items/potion_bottle_confusion.png;" + // nausea effect
                "textures/items/potion_bottle_damageBoost.png;" + // strength effect
                "textures/items/potion_bottle_digSlowdown.png;" + // minig fatigue
                "textures/items/potion_bottle_digSpeed.png;" + // haste
                "textures/items/potion_bottle_drinkable.png;" + // water
                "textures/items/potion_bottle_fireResistance.png;" +
                "textures/items/potion_bottle_harm.png;" + // instant_damage
                "textures/items/potion_bottle_heal.png;" + // instant_health
                "textures/items/potion_bottle_healthBoost.png;" +
                "textures/items/potion_bottle_hunger.png;" +
                "textures/items/potion_bottle_invisibility.png;" +
                "textures/items/potion_bottle_jump.png;" +
                "textures/items/potion_bottle_levitation.png;" +
                "textures/items/potion_bottle_moveSlowdown.png;" +
                "textures/items/potion_bottle_moveSpeed.png;" +
                "textures/items/potion_bottle_nightVision.png;" +
                "textures/items/potion_bottle_poison.png;" +
                "textures/items/potion_bottle_regeneration.png;" +
                "textures/items/potion_bottle_resistance.png;" +
                "textures/items/potion_bottle_saturation.png;" +
                "textures/items/potion_bottle_slowFall.png;" +
                "textures/items/potion_bottle_turtleMaster.png;" +
                "textures/items/potion_bottle_waterBreathing.png;" +
                "textures/items/potion_bottle_weakness.png;" +
                "textures/items/potion_bottle_wither.png;" +
                // lingering potions
                "textures/items/potion_bottle_lingering.png;" +
                "textures/items/potion_bottle_lingering_damageBoost.png;" +
                "textures/items/potion_bottle_lingering_fireResistance.png;" +
                "textures/items/potion_bottle_lingering_harm.png;" +
                "textures/items/potion_bottle_lingering_heal.png;" +
                "textures/items/potion_bottle_lingering_invisibility.png;" +
                "textures/items/potion_bottle_lingering_jump.png;" +
                "textures/items/potion_bottle_lingering_luck.png;" +
                "textures/items/potion_bottle_lingering_moveSlowdown.png;" +
                "textures/items/potion_bottle_lingering_moveSpeed.png;" +
                "textures/items/potion_bottle_lingering_nightVision.png;" +
                "textures/items/potion_bottle_lingering_poison.png;" +
                "textures/items/potion_bottle_lingering_regeneration.png;" +
                "textures/items/potion_bottle_lingering_slowFall.png;" +
                "textures/items/potion_bottle_lingering_turtleMaster.png;" +
                "textures/items/potion_bottle_lingering_waterBreathing.png;" +
                "textures/items/potion_bottle_lingering_weakness.png;" +
                "textures/items/potion_bottle_lingering_wither.png;" +
                // splash potions
                "textures/items/potion_bottle_splash.png;" +
                "textures/items/potion_bottle_splash_absorption.png;" +
                "textures/items/potion_bottle_splash_blindness.png;" +
                "textures/items/potion_bottle_splash_confusion.png;" +
                "textures/items/potion_bottle_splash_damageBoost.png;" +
                "textures/items/potion_bottle_splash_digSlowdown.png;" +
                "textures/items/potion_bottle_splash_digSpeed.png;" +
                "textures/items/potion_bottle_splash_fireResistance.png;" +
                "textures/items/potion_bottle_splash_harm.png;" +
                "textures/items/potion_bottle_splash_heal.png;" +
                "textures/items/potion_bottle_splash_healthBoost.png;" +
                "textures/items/potion_bottle_splash_hunger.png;" +
                "textures/items/potion_bottle_splash_invisibility.png;" +
                "textures/items/potion_bottle_splash_jump.png;" +
                "textures/items/potion_bottle_splash_levitation.png;" +
                "textures/items/potion_bottle_splash_moveSlowdown.png;" +
                "textures/items/potion_bottle_splash_moveSpeed.png;" +
                "textures/items/potion_bottle_splash_nightVision.png;" +
                "textures/items/potion_bottle_splash_poison.png;" +
                "textures/items/potion_bottle_splash_regeneration.png;" +
                "textures/items/potion_bottle_splash_resistance.png;" +
                "textures/items/potion_bottle_splash_saturation.png;" +
                "textures/items/potion_bottle_splash_slowFall.png;" +
                "textures/items/potion_bottle_splash_turtleMaster.png;" +
                "textures/items/potion_bottle_splash_waterBreathing.png;" +
                "textures/items/potion_bottle_splash_weakness.png;" +
                "textures/items/potion_bottle_splash_wither.png;");
        tempOptions.put("assets/minecraft/textures/item/potion_overlay.png", "copy;" +
                // default potions
                "combine[this:copy,colorMultInt=2445989:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039587:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5578058:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=9643043:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4866583:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=14270531:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3694022:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=14981690:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4393481:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16284963:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5797459:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8356754:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2293580:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13565951:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5926017:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8171462:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039713:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5149489:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13458603:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=10044730:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16773073:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=7691106:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3035801:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4738376:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3484199:assets/minecraft/textures/item/potion.png:minAlphaToCopy=1];" +
                // lingering
                "combine[this:copy,colorMultInt=3694022:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=9643043:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=14981690:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4393481:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8356754:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2293580:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3381504:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5926017:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8171462:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039713:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5149489:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13458603:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16773073:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=7691106:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3035801:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4738376:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3484199:assets/minecraft/textures/item/lingering_potion.png:minAlphaToCopy=1];" +
                // splash
                "combine[this:copy,colorMultInt=3694022:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2445989:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039587:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5578058:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=9643043:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4866583:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=14270531:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=14981690:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4393481:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16284963:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5797459:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8356754:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2293580:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13565951:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5926017:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8171462:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039713:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5149489:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13458603:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=10044730:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16773073:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=7691106:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3035801:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4738376:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3484199:assets/minecraft/textures/item/splash_potion.png:minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/prismarine_crystals.png", "textures/items/prismarine_crystals.png");
        tempBlocks.put("assets/minecraft/textures/item/prismarine_shard.png", "textures/items/prismarine_shard.png");
        tempBlocks.put("assets/minecraft/textures/item/pumpkin_pie.png", "textures/items/pumpkin_pie.png");
        tempBlocks.put("assets/minecraft/textures/item/quartz.png", "textures/items/quartz.png");
        tempBlocks.put("assets/minecraft/textures/item/cooked_rabbit.png", "textures/items/rabbit_cooked.png");
        tempBlocks.put("assets/minecraft/textures/item/rabbit_foot.png", "textures/items/rabbit_foot.png");
        tempBlocks.put("assets/minecraft/textures/item/rabbit_hide.png", "textures/items/rabbit_hide.png");
        tempBlocks.put("assets/minecraft/textures/item/rabbit.png", "textures/items/rabbit_raw.png");
        tempBlocks.put("assets/minecraft/textures/item/rabbit_stew.png", "textures/items/rabbit_stew.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_11.png", "textures/items/record_11.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_13.png", "textures/items/record_13.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_blocks.png", "textures/items/record_blocks.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_cat.png", "textures/items/record_cat.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_chirp.png", "textures/items/record_chirp.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_far.png", "textures/items/record_far.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_mall.png", "textures/items/record_mall.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_mellohi.png", "textures/items/record_mellohi.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_stal.png", "textures/items/record_stal.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_strad.png", "textures/items/record_strad.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_wait.png", "textures/items/record_wait.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_ward.png", "textures/items/record_ward.png");
        tempBlocks.put("assets/minecraft/textures/item/redstone.png", "textures/items/redstone_dust.png");
        tempBlocks.put("assets/minecraft/textures/item/sugar_cane.png", "textures/items/reeds.png");
        tempBlocks.put("assets/minecraft/textures/item/repeater.png", "textures/items/repeater.png");
        tempBlocks.put("assets/minecraft/textures/item/rotten_flesh.png", "textures/items/rotten_flesh.png");
        tempBlocks.put("assets/minecraft/textures/item/ruby.png", "textures/items/ruby.png");
        tempBlocks.put("assets/minecraft/textures/item/saddle.png", "textures/items/saddle.png");
        tempBlocks.put("assets/minecraft/textures/item/seagrass.png", "textures/blocks/seagrass_carried.png");
        tempBlocks.put("assets/minecraft/textures/item/sea_pickle.png", "textures/items/sea_pickle.png");
        tempBlocks.put("assets/minecraft/textures/item/beetroot_seeds.png", "textures/items/seeds_beetroot.png");
        tempBlocks.put("assets/minecraft/textures/item/melon_seeds.png", "textures/items/seeds_melon.png");
        tempBlocks.put("assets/minecraft/textures/item/pumpkin_seeds.png", "textures/items/seeds_pumpkin.png");
        tempBlocks.put("assets/minecraft/textures/item/wheat_seeds.png", "textures/items/seeds_wheat.png");
        tempBlocks.put("assets/minecraft/textures/item/shears.png", "textures/items/shears.png");
        tempBlocks.put("assets/minecraft/textures/item/shulker_shell.png", "textures/items/shulker_shell.png");
        tempBlocks.put("assets/minecraft/textures/item/oak_sign.png", "textures/items/sign.png");
        tempBlocks.put("assets/minecraft/textures/item/acacia_sign.png", "textures/items/sign_acacia.png");
        tempBlocks.put("assets/minecraft/textures/item/birch_sign.png", "textures/items/sign_birch.png");
        tempBlocks.put("assets/minecraft/textures/item/dark_oak_sign.png", "textures/items/sign_darkoak.png");
        tempBlocks.put("assets/minecraft/textures/item/jungle_sign.png", "textures/items/sign_jungle.png");
        tempBlocks.put("assets/minecraft/textures/item/spruce_sign.png", "textures/items/sign_spruce.png");
        tempBlocks.put("assets/minecraft/textures/item/slime_ball.png", "textures/items/slimeball.png");
        tempBlocks.put("assets/minecraft/textures/item/snowball.png", "textures/items/snowball.png");
        tempBlocks.put("assets/minecraft/textures/item/_egg.png", "textures/items/_egg.png");
        tempBlocks.put("assets/minecraft/textures/item/_egg_overlay.png", "textures/items/_egg_overlay.png");
        tempBlocks.put("assets/minecraft/textures/item/spider_eye.png", "textures/items/spider_eye.png");
        tempBlocks.put("assets/minecraft/textures/item/fermented_spider_eye.png", "textures/items/spider_eye_fermented.png");
        tempBlocks.put("assets/minecraft/textures/item/stick.png", "textures/items/stick.png");
        tempBlocks.put("assets/minecraft/textures/item/stone_axe.png", "textures/items/stone_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/stone_hoe.png", "textures/items/stone_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/stone_pickaxe.png", "textures/items/stone_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/stone_shovel.png", "textures/items/stone_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/stone_sword.png", "textures/items/stone_sword.png");
        tempBlocks.put("assets/minecraft/textures/item/string.png", "textures/items/string.png");
        tempBlocks.put("assets/minecraft/textures/item/structure_void.png", "textures/blocks/structure_void.png");
        tempBlocks.put("assets/minecraft/textures/item/sugar.png", "textures/items/sugar.png");
        tempBlocks.put("assets/minecraft/textures/item/suspicious_stew.png", "textures/items/suspicious_stew.png");
        tempBlocks.put("assets/minecraft/textures/item/sweet_berries.png", "textures/items/sweet_berries.png");
        tempBlocks.put("assets/minecraft/textures/item/spectral_arrow.png", ""); // not available
        tempBlocks.put("", "textures/items/tipped_arrow.png");
        tempBlocks.put("assets/minecraft/textures/item/tipped_arrow_base.png", "textures/items/tipped_arrow_base.png");
        tempBlocks.put("assets/minecraft/textures/item/tipped_arrow_head.png", "textures/items/tipped_arrow_head.png;" +
                "textures/items/tipped_arrow.png;" +
                "textures/items/tipped_arrow_fireres.png;" +
                "textures/items/tipped_arrow_harm.png;" + // instant damage
                "textures/items/tipped_arrow_healing.png;" + // instant health
                "textures/items/tipped_arrow_invisibility.png;" +
                "textures/items/tipped_arrow_leaping.png;" + // jump_boost
                "textures/items/tipped_arrow_luck.png;" +
                "textures/items/tipped_arrow_nightvision.png;" +
                "textures/items/tipped_arrow_poison.png;" +
                "textures/items/tipped_arrow_regen.png;" +
                "textures/items/tipped_arrow_slow.png;" + // slowness
                "textures/items/tipped_arrow_slowfalling.png;" +
                "textures/items/tipped_arrow_strength.png;" +
                "textures/items/tipped_arrow_swift.png;" + // speed
                "textures/items/tipped_arrow_turtlemaster.png;" +
                "textures/items/tipped_arrow_waterbreathing.png;" +
                "textures/items/tipped_arrow_weakness.png;" +
                "textures/items/tipped_arrow_wither.png");
        tempOptions.put("assets/minecraft/textures/item/tipped_arrow_head.png", "copy;" +
                "combine[this:copy:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                // tipped arrows with potion color
                "combine[this:copy,colorMultInt=14981690:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4393481:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16262179:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8356754:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2293580:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3381504:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=2039713:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5149489:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=13458603:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=5926017:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=16773073:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=9643043:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=8171462:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=7691106:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3035801:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=4738376:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1];" +
                "combine[this:copy,colorMultInt=3484199:assets/minecraft/textures/item/tipped_arrow_base.png:minAlphaToCopy=1]");
        tempBlocks.put("assets/minecraft/textures/item/totem_of_undying.png", "textures/items/totem.png");
        tempBlocks.put("assets/minecraft/textures/item/trident.png", "textures/items/trident.png");
        tempBlocks.put("assets/minecraft/textures/item/turtle_egg.png", "textures/items/turtle_egg.png");
        tempBlocks.put("assets/minecraft/textures/item/turtle_helmet.png", "textures/items/turtle_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/scute.png", "textures/items/turtle_shell_piece.png");
        tempBlocks.put("assets/minecraft/textures/item/bell.png", "textures/items/villagebell.png");
        tempBlocks.put("assets/minecraft/textures/item/wheat.png", "textures/items/wheat.png");
        tempBlocks.put("assets/minecraft/textures/item/wooden_axe.png", "textures/items/wood_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/wooden_hoe.png", "textures/items/wood_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/wooden_pickaxe.png", "textures/items/wood_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/wooden_shovel.png", "textures/items/wood_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/wooden_sword.png", "textures/items/wood_sword.png");

        tempBlocks.put("assets/minecraft/textures/item/compass_00.png", "textures/items/compass_atlas.png;textures/items/lodestonecompass_atlas.png");
        tempOptions.put("assets/minecraft/textures/item/compass_00.png", "restructure[animation:this:assets/minecraft/textures/item/compass_01.png:assets/minecraft/textures/item/compass_02.png:assets/minecraft/textures/item/compass_03.png:assets/minecraft/textures/item/compass_04.png:assets/minecraft/textures/item/compass_05.png:assets/minecraft/textures/item/compass_06.png:assets/minecraft/textures/item/compass_07.png:assets/minecraft/textures/item/compass_08.png:assets/minecraft/textures/item/compass_09.png:assets/minecraft/textures/item/compass_10.png:assets/minecraft/textures/item/compass_11.png:assets/minecraft/textures/item/compass_12.png:assets/minecraft/textures/item/compass_13.png:assets/minecraft/textures/item/compass_14.png:assets/minecraft/textures/item/compass_15.png:assets/minecraft/textures/item/compass_16.png:assets/minecraft/textures/item/compass_17.png:assets/minecraft/textures/item/compass_18.png:assets/minecraft/textures/item/compass_19.png:assets/minecraft/textures/item/compass_20.png:assets/minecraft/textures/item/compass_21.png:assets/minecraft/textures/item/compass_22.png:assets/minecraft/textures/item/compass_23.png:assets/minecraft/textures/item/compass_24.png:assets/minecraft/textures/item/compass_25.png:assets/minecraft/textures/item/compass_26.png:assets/minecraft/textures/item/compass_27.png:assets/minecraft/textures/item/compass_28.png:assets/minecraft/textures/item/compass_29.png:assets/minecraft/textures/item/compass_30.png:assets/minecraft/textures/item/compass_31.png];restructure[animation:this:assets/minecraft/textures/item/compass_01.png:assets/minecraft/textures/item/compass_02.png:assets/minecraft/textures/item/compass_03.png:assets/minecraft/textures/item/compass_04.png:assets/minecraft/textures/item/compass_05.png:assets/minecraft/textures/item/compass_06.png:assets/minecraft/textures/item/compass_07.png:assets/minecraft/textures/item/compass_08.png:assets/minecraft/textures/item/compass_09.png:assets/minecraft/textures/item/compass_10.png:assets/minecraft/textures/item/compass_11.png:assets/minecraft/textures/item/compass_12.png:assets/minecraft/textures/item/compass_13.png:assets/minecraft/textures/item/compass_14.png:assets/minecraft/textures/item/compass_15.png:assets/minecraft/textures/item/compass_16.png:assets/minecraft/textures/item/compass_17.png:assets/minecraft/textures/item/compass_18.png:assets/minecraft/textures/item/compass_19.png:assets/minecraft/textures/item/compass_20.png:assets/minecraft/textures/item/compass_21.png:assets/minecraft/textures/item/compass_22.png:assets/minecraft/textures/item/compass_23.png:assets/minecraft/textures/item/compass_24.png:assets/minecraft/textures/item/compass_25.png:assets/minecraft/textures/item/compass_26.png:assets/minecraft/textures/item/compass_27.png:assets/minecraft/textures/item/compass_28.png:assets/minecraft/textures/item/compass_29.png:assets/minecraft/textures/item/compass_30.png:assets/minecraft/textures/item/compass_31.png]");
        tempBlocks.put("assets/minecraft/textures/item/compass_01.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_02.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_03.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_04.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_05.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_06.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_07.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_08.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_09.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_10.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_11.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_12.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_13.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_14.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_15.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_16.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_17.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_18.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_19.png", "textures/items/compass_item.png;textures/items/lodestonecompass_item.png");
        tempBlocks.put("assets/minecraft/textures/item/compass_20.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_21.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_22.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_23.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_24.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_25.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_26.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_27.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_28.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_29.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_30.png", "");
        tempBlocks.put("assets/minecraft/textures/item/compass_31.png", "");

        tempBlocks.put("assets/minecraft/textures/item/clock_00.png", "textures/items/clock_item.png;textures/items/watch_atlas.png"); // combine to watch atlas
        tempOptions.put("assets/minecraft/textures/item/clock_00.png", "copy;restructure[animation:this:assets/minecraft/textures/item/clock_01.png:assets/minecraft/textures/item/clock_02.png:assets/minecraft/textures/item/clock_03.png:assets/minecraft/textures/item/clock_04.png:assets/minecraft/textures/item/clock_05.png:assets/minecraft/textures/item/clock_06.png:assets/minecraft/textures/item/clock_07.png:assets/minecraft/textures/item/clock_08.png:assets/minecraft/textures/item/clock_09.png:assets/minecraft/textures/item/clock_10.png:assets/minecraft/textures/item/clock_11.png:assets/minecraft/textures/item/clock_12.png:assets/minecraft/textures/item/clock_13.png:assets/minecraft/textures/item/clock_14.png:assets/minecraft/textures/item/clock_15.png:assets/minecraft/textures/item/clock_16.png:assets/minecraft/textures/item/clock_17.png:assets/minecraft/textures/item/clock_18.png:assets/minecraft/textures/item/clock_19.png:assets/minecraft/textures/item/clock_20.png:assets/minecraft/textures/item/clock_21.png:assets/minecraft/textures/item/clock_22.png:assets/minecraft/textures/item/clock_23.png:assets/minecraft/textures/item/clock_24.png:assets/minecraft/textures/item/clock_25.png:assets/minecraft/textures/item/clock_26.png:assets/minecraft/textures/item/clock_27.png:assets/minecraft/textures/item/clock_28.png:assets/minecraft/textures/item/clock_29.png:assets/minecraft/textures/item/clock_30.png:assets/minecraft/textures/item/clock_31.png:assets/minecraft/textures/item/clock_32.png:assets/minecraft/textures/item/clock_33.png:assets/minecraft/textures/item/clock_34.png:assets/minecraft/textures/item/clock_35.png:assets/minecraft/textures/item/clock_36.png:assets/minecraft/textures/item/clock_37.png:assets/minecraft/textures/item/clock_38.png:assets/minecraft/textures/item/clock_39.png:assets/minecraft/textures/item/clock_40.png:assets/minecraft/textures/item/clock_41.png:assets/minecraft/textures/item/clock_42.png:assets/minecraft/textures/item/clock_43.png:assets/minecraft/textures/item/clock_44.png:assets/minecraft/textures/item/clock_45.png:assets/minecraft/textures/item/clock_46.png:assets/minecraft/textures/item/clock_47.png:assets/minecraft/textures/item/clock_48.png:assets/minecraft/textures/item/clock_49.png:assets/minecraft/textures/item/clock_50.png:assets/minecraft/textures/item/clock_51.png:assets/minecraft/textures/item/clock_52.png:assets/minecraft/textures/item/clock_53.png:assets/minecraft/textures/item/clock_54.png:assets/minecraft/textures/item/clock_55.png:assets/minecraft/textures/item/clock_56.png:assets/minecraft/textures/item/clock_57.png:assets/minecraft/textures/item/clock_58.png:assets/minecraft/textures/item/clock_59.png:assets/minecraft/textures/item/clock_60.png:assets/minecraft/textures/item/clock_61.png:assets/minecraft/textures/item/clock_62.png:assets/minecraft/textures/item/clock_63.png]");
        tempBlocks.put("assets/minecraft/textures/item/clock_01.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_02.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_03.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_04.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_05.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_06.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_07.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_08.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_09.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_10.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_11.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_12.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_13.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_14.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_15.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_16.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_17.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_18.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_19.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_20.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_21.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_22.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_23.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_24.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_25.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_26.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_27.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_28.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_29.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_30.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_31.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_32.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_33.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_34.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_35.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_36.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_37.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_38.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_39.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_40.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_41.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_42.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_43.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_44.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_45.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_46.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_47.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_48.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_49.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_50.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_51.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_52.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_53.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_54.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_55.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_56.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_57.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_58.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_59.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_60.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_61.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_62.png", "");
        tempBlocks.put("assets/minecraft/textures/item/clock_63.png", "");

        // colormaps
        tempBlocks.put("assets/minecraft/textures/colormap/grass.png", "textures/colormap/grass.png");
        tempBlocks.put("assets/minecraft/textures/colormap/foliage.png", "textures/colormap/foliage.png");
        //banner pattern
        tempBlocks.put("assets/minecraft/textures/item/creeper_banner_pattern.png", "option.this");
        tempBlocks.put("assets/minecraft/textures/item/globe_banner_pattern.png", "option.this");
        tempBlocks.put("assets/minecraft/textures/item/flower_banner_pattern.png", "option.this");
        tempBlocks.put("assets/minecraft/textures/item/mojang_banner_pattern.png", "option.this");
        tempBlocks.put("assets/minecraft/textures/item/skull_banner_pattern.png", "option.this");

        // spawn eggs
        tempBlocks.put("assets/minecraft/textures/item/spawn_egg.png", "textures/items/spawn_egg.png;" +
                "textures/items/egg_bat.png;" +
                "textures/items/egg_bee.png;" +
                "textures/items/egg_blaze.png;" +
                "textures/items/egg_cat.png;" +
                "textures/items/egg_cave_spider.png;" +
                "textures/items/egg_chicken.png;" +
                "textures/items/egg_clownfish.png;" +
                "textures/items/egg_cod.png;" +
                "textures/items/egg_cow.png;" +
                "textures/items/egg_creeper.png;" +
                "textures/items/egg_dolphin.png;" +
                "textures/items/egg_donkey.png;" +
                "textures/items/egg_drowned.png;" +
                "textures/items/egg_elderguardian.png;" +
                "textures/items/egg_enderman.png;" +
                "textures/items/egg_endermite.png;" +
                "textures/items/egg_evoker.png;" +
                "textures/items/egg_fish.png;" +
                "textures/items/egg_fox.png;" +
                "textures/items/egg_ghast.png;" +
                "textures/items/egg_guardian.png;" +
                "textures/items/egg_horse.png;" +
                "textures/items/egg_husk.png;" +
                "textures/items/egg_lava_slime.png;" +
                "textures/items/egg_llama.png;" +
                "textures/items/egg_mule.png;" +
                "textures/items/egg_mushroomcow.png;" +
                //"textures/items/egg_npc.png;" +
                "textures/items/egg_ocelot.png;" +
                "textures/items/egg_panda.png;" +
                "textures/items/egg_parrot.png;" +
                "textures/items/egg_phantom.png;" +
                "textures/items/egg_pig.png;" +
                "textures/items/egg_pigzombie.png;" +
                "textures/items/egg_pillager.png;" +
                "textures/items/egg_polarbear.png;" +
                "textures/items/egg_pufferfish.png;" +
                "textures/items/egg_rabbit.png;" +
                "textures/items/egg_ravager.png;" +
                "textures/items/egg_salmon.png;" +
                "textures/items/egg_sheep.png;" +
                "textures/items/egg_shulker.png;" +
                "textures/items/egg_silverfish.png;" +
                "textures/items/egg_skeleton.png;" +
                "textures/items/egg_skeletonhorse.png;" +
                "textures/items/egg_slime.png;" +
                "textures/items/egg_spider.png;" +
                "textures/items/egg_squid.png;" +
                "textures/items/egg_stray.png;" +
                "textures/items/egg_turtle.png;" +
                "textures/items/egg_vex.png;" +
                "textures/items/egg_villager.png;" +
                "textures/items/egg_vindicator.png;" +
                "textures/items/egg_wanderingtrader.png;" +
                "textures/items/egg_witch.png;" +
                "textures/items/egg_wither.png;" +
                "textures/items/egg_wolf.png;" +
                "textures/items/egg_zombie.png;" +
                "textures/items/egg_zombiehorse.png;" +
                "textures/items/egg_zombievillager.png;" +
                "textures/items/egg_mask.png;" +
                "textures/items/egg_null.png");
        // colors from net/minecraft/world/item/items.java
        // mobs since 1.16 use egg_null and egg_mask
        tempOptions.put("assets/minecraft/textures/item/spawn_egg.png", "copy;" +
                "combine[this:copy,colorMultInt=4996656:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=986895];" + // BAT
                "combine[this:copy,colorMultInt=15582019:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=4400155];" + // BEE
                "combine[this:copy,colorMultInt=16167425:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16775294];" + // BLAZE
                "combine[this:copy,colorMultInt=15714446:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=9794134];" + // CAT
                "combine[this:copy,colorMultInt=803406:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=11013646];" + // CAVE_SPIDER
                "combine[this:copy,colorMultInt=10592673:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16711680];" + // CHICKEN
                "combine[this:copy,colorMultInt=15690005:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16775663];" + // TROPICAL_FISH / clownfish
                "combine[this:copy,colorMultInt=12691306:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15058059];" + // COD
                "combine[this:copy,colorMultInt=4470310:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=10592673];" + // COW
                "combine[this:copy,colorMultInt=894731:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=0];" + // CREEPER
                "combine[this:copy,colorMultInt=2243405:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16382457];" + // DOLPHIN
                "combine[this:copy,colorMultInt=5457209:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=8811878];" + // DONKEY
                "combine[this:copy,colorMultInt=9433559:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7969893];" + // DROWNED
                "combine[this:copy,colorMultInt=13552826:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7632531];" + // ELDER_GUARDIAN
                "combine[this:copy,colorMultInt=1447446:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=0];" + // ENDERMAN
                "combine[this:copy,colorMultInt=1447446:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7237230];" + // ENDERMITE
                "combine[this:copy,colorMultInt=9804699:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=1973274];" + // EVOKER
                "combine[this:copy,colorMultInt=12691306:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15058059];" + // COD / Fish
                "combine[this:copy,colorMultInt=14005919:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=13396256];" + // FOX
                "combine[this:copy,colorMultInt=16382457:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=12369084];" + // GHAST
                "combine[this:copy,colorMultInt=5931634:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15826224];" + // GUARDIAN
                "combine[this:copy,colorMultInt=12623485:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15656192];" + // HORSE
                "combine[this:copy,colorMultInt=7958625:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15125652];" + // HUSK
                "combine[this:copy,colorMultInt=3407872:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16579584];" + // MAGMA_CUBE / lava slime
                "combine[this:copy,colorMultInt=12623485:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=10051392];" + // LLAMA
                "combine[this:copy,colorMultInt=1769984:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5321501];" + // MULE
                "combine[this:copy,colorMultInt=10489616:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=12040119];" + // MOOSHROOM
                "combine[this:copy,colorMultInt=15720061:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5653556];" + // OCELOT
                "combine[this:copy,colorMultInt=15198183:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=1776418];" + // PANDA
                "combine[this:copy,colorMultInt=894731:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16711680];" + // PARROT
                "combine[this:copy,colorMultInt=4411786:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=8978176];" + // PHANTOM
                "combine[this:copy,colorMultInt=15771042:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=14377823];" + // PIG
                "combine[this:copy,colorMultInt=15373203:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5009705];" + // ZOMBIE_PIGMAN / pigzombie
                "combine[this:copy,colorMultInt=5451574:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=9804699];" + // PILLAGER
                "combine[this:copy,colorMultInt=15921906:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=9803152];" + // POLAR_BEAR
                "combine[this:copy,colorMultInt=16167425:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=3654642];" + // PUFFERFISH
                "combine[this:copy,colorMultInt=10051392:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7555121];" + // RABBIT
                "combine[this:copy,colorMultInt=7697520:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5984329];" + // RAVAGER
                "combine[this:copy,colorMultInt=10489616:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=951412];" + // SALMON
                "combine[this:copy,colorMultInt=15198183:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16758197];" + // SHEEP
                "combine[this:copy,colorMultInt=9725844:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5060690];" + // SHULKER
                "combine[this:copy,colorMultInt=7237230:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=3158064];" + // SILVERFISH
                "combine[this:copy,colorMultInt=12698049:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=4802889];" + // SKELETON
                "combine[this:copy,colorMultInt=6842447:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15066584];" + // SKELETON_HORSE
                "combine[this:copy,colorMultInt=5349438:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=8306542];" + // SLIME
                "combine[this:copy,colorMultInt=3419431:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=11013646];" + // SPIDER
                "combine[this:copy,colorMultInt=2243405:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7375001];" + // SQUID
                "combine[this:copy,colorMultInt=6387319:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=14543594];" + // STRAY
                //"combine[this:copy,colorMultInt=15377456:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=4547222];" + // TRADER_LLAMA
                "combine[this:copy,colorMultInt=15198183:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=44975];" + // TURTLE
                "combine[this:copy,colorMultInt=8032420:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15265265];" + // VEX
                "combine[this:copy,colorMultInt=5651507:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=12422002];" + // VILLAGER
                "combine[this:copy,colorMultInt=9804699:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=2580065];" + // VINDICATOR
                "combine[this:copy,colorMultInt=4547222:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=15377456];" + // WANDERING_TRADER
                "combine[this:copy,colorMultInt=3407872:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=5349438];" + // WITCH
                "combine[this:copy,colorMultInt=1315860:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=4672845];" + // WITHER_SKELETON
                "combine[this:copy,colorMultInt=14144467:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=13545366];" + // WOLF
                "combine[this:copy,colorMultInt=44975:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7969893];" + // ZOMBIE
                "combine[this:copy,colorMultInt=3232308:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=9945732];" + // ZOMBIE_HORSE
                "combine[this:copy,colorMultInt=5651507:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=7969893];" + // ZOMBIE_VILLAGER
                "combine[this:copy,colorMultInt=14155776:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1,colorMultInt=16776960];" + // mask, red for egg yellow for pattern
                "combine[this:copy:assets/minecraft/textures/item/spawn_egg_overlay.png:minAlphaToCopy=1]" // null
        );
        tempBlocks.put("assets/minecraft/textures/item/spawn_egg_overlay.png", "textures/items/spawn_egg_overlay.png");
        tempOptions.put("assets/minecraft/textures/item/spawn_egg_overlay.png", "copy");

        // gui stuff
        // need to extract all small icons from the single gui sheets
        tempBlocks.put("assets/minecraft/textures/gui/icons.png", "");
        tempOptions.put("assets/minecraft/textures/gui/icons.png", "function[icons]");
        tempBlocks.put("assets/minecraft/textures/gui/widgets.png", "");
        tempOptions.put("assets/minecraft/textures/gui/widgets.png", "function[widgets]");
        tempBlocks.put("assets/minecraft/textures/gui/container/inventory.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/inventory.png", "function[inventory]");
        tempBlocks.put("assets/minecraft/textures/gui/container/horse.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/horse.png", "function[horse]");
        tempBlocks.put("assets/minecraft/textures/gui/container/furnace.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/furnace.png", "function[furnace]");
        tempBlocks.put("assets/minecraft/textures/gui/container/blast_furnace.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/blast_furnace.png", "function[furnace_blast]");
        tempBlocks.put("assets/minecraft/textures/gui/container/smoker.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/smoker.png", "function[furnace_smoker]");
        tempBlocks.put("assets/minecraft/textures/gui/container/enchanting_table.png", "");
        tempOptions.put("assets/minecraft/textures/gui/container/enchanting_table.png", "function[enchanting]");
        //tempBlocks.put("assets/minecraft/textures/gui/", "");
        //tempOptions.put("assets/minecraft/textures/gui/", "function[]");


        // 1.16 stuff
        // blocks
        tempBlocks.put("assets/minecraft/textures/block/ancient_debris_side.png", "textures/blocks/ancient_debris_side.png");
        tempBlocks.put("assets/minecraft/textures/block/ancient_debris_top.png", "textures/blocks/ancient_debris_top.png");
        tempBlocks.put("assets/minecraft/textures/block/basalt_side.png", "textures/blocks/basalt_side.png");
        tempBlocks.put("assets/minecraft/textures/block/basalt_top.png", "textures/blocks/basalt_top.png");
        tempBlocks.put("assets/minecraft/textures/block/blackstone.png", "textures/blocks/blackstone.png");
        tempBlocks.put("assets/minecraft/textures/block/blackstone_top.png", "textures/blocks/blackstone_top.png");
        tempBlocks.put("assets/minecraft/textures/block/chain.png", "");
        tempOptions.put("assets/minecraft/textures/block/chain.png", "split[textures/blocks/chain1.png;textures/blocks/chain2.png]");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_nether_bricks.png", "textures/blocks/chiseled_nether_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/chiseled_polished_blackstone.png", "textures/blocks/chiseled_polished_blackstone.png");
        tempBlocks.put("assets/minecraft/textures/block/cracked_nether_bricks.png", "textures/blocks/cracked_nether_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/cracked_polished_blackstone_bricks.png", "textures/blocks/cracked_polished_blackstone_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_fungus.png", "textures/blocks/crimson_fungus.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_nylium_side.png", "textures/blocks/crimson_nylium_side.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_nylium.png", "textures/blocks/crimson_nylium_top.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_roots.png", "textures/blocks/crimson_roots.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_roots_pot.png", "textures/blocks/crimson_roots_pot.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_door_bottom.png", "textures/blocks/huge_fungus/crimson_door_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_door_top.png", "textures/blocks/huge_fungus/crimson_door_top.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_stem.png", "textures/blocks/huge_fungus/crimson_log_side.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_stem_top.png", "textures/blocks/huge_fungus/crimson_log_top.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_planks.png", "textures/blocks/huge_fungus/crimson_planks.png");
        tempBlocks.put("assets/minecraft/textures/block/crimson_trapdoor.png", "textures/blocks/huge_fungus/crimson_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_crimson_stem.png", "textures/blocks/huge_fungus/stripped_crimson_stem_side.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_crimson_stem_top.png", "textures/blocks/huge_fungus/stripped_crimson_stem_top.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_door_bottom.png", "textures/blocks/huge_fungus/warped_door_lower.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_door_top.png", "textures/blocks/huge_fungus/warped_door_top.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_planks.png", "textures/blocks/huge_fungus/warped_planks.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_stem.png", "textures/blocks/huge_fungus/warped_stem_side.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_stem_top.png", "textures/blocks/huge_fungus/warped_stem_top.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_trapdoor.png", "textures/blocks/huge_fungus/warped_trapdoor.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_warped_stem.png", "textures/blocks/huge_fungus/stripped_warped_stem_side.png");
        tempBlocks.put("assets/minecraft/textures/block/stripped_warped_stem_top.png", "textures/blocks/huge_fungus/stripped_warped_stem_top.png");
        tempBlocks.put("assets/minecraft/textures/block/crying_obsidian.png", "textures/blocks/crying_obsidian.png");
        tempBlocks.put("assets/minecraft/textures/block/gilded_blackstone.png", "textures/blocks/gilded_blackstone.png");
        tempBlocks.put("assets/minecraft/textures/block/lodestone_side.png", "textures/blocks/lodestone_side.png");
        tempBlocks.put("assets/minecraft/textures/block/lodestone_top.png", "textures/blocks/lodestone_top.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_gold_ore.png", "textures/blocks/nether_gold_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/nether_sprouts.png", "textures/blocks/nether_sprouts.png");
        tempBlocks.put("assets/minecraft/textures/block/netherite_block.png", "textures/blocks/netherite_block.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_basalt_side.png", "textures/blocks/polished_basalt_side.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_basalt_top.png", "textures/blocks/polished_basalt_top.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_blackstone.png", "textures/blocks/polished_blackstone.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_blackstone_bricks.png", "textures/blocks/polished_blackstone_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/quartz_bricks.png", "textures/blocks/quartz_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_bottom.png", "textures/blocks/respawn_anchor_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_side0.png", "textures/blocks/respawn_anchor_side0.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_side1.png", "textures/blocks/respawn_anchor_side1.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_side2.png", "textures/blocks/respawn_anchor_side2.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_side3.png", "textures/blocks/respawn_anchor_side3.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_side4.png", "textures/blocks/respawn_anchor_side4.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_top.png", "textures/blocks/respawn_anchor_top.png");
        tempBlocks.put("assets/minecraft/textures/block/respawn_anchor_top_off.png", "textures/blocks/respawn_anchor_top_off.png");
        tempBlocks.put("assets/minecraft/textures/block/shroomlight.png", "textures/blocks/shroomlight.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_campfire_fire.png", "textures/blocks/soul_campfire.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_campfire_log_lit.png", "textures/blocks/soul_campfire_log_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_fire_0.png", "textures/blocks/soul_fire_0.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_fire_1.png", "textures/blocks/soul_fire_1.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_lantern.png", "textures/blocks/soul_lantern.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_soil.png", "textures/blocks/soul_soil.png");
        tempBlocks.put("assets/minecraft/textures/block/soul_torch.png", "textures/blocks/soul_torch.png");
        tempBlocks.put("assets/minecraft/textures/block/target_side.png", "textures/blocks/target_side.png");
        tempBlocks.put("assets/minecraft/textures/block/target_top.png", "textures/blocks/target_top.png");
        tempBlocks.put("assets/minecraft/textures/block/twisting_vines_plant.png", "textures/blocks/twisting_vines_base.png");
        tempBlocks.put("assets/minecraft/textures/block/twisting_vines.png", "textures/blocks/twisting_vines_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_fungus.png", "textures/blocks/warped_fungus.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_nylium_side.png", "textures/blocks/warped_nylium_side.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_nylium.png", "textures/blocks/warped_nylium_top.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_roots.png", "textures/blocks/warped_roots.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_roots_pot.png", "textures/blocks/warped_roots_pot.png");
        tempBlocks.put("assets/minecraft/textures/block/warped_wart_block.png", "textures/blocks/warped_wart_block.png");
        tempBlocks.put("assets/minecraft/textures/block/weeping_vines_plant.png", "textures/blocks/weeping_vines_base.png");
        tempBlocks.put("assets/minecraft/textures/block/weeping_vines.png", "textures/blocks/weeping_vines_bottom.png");

        // items
        tempBlocks.put("assets/minecraft/textures/item/chain.png", "textures/items/chain.png");
        tempBlocks.put("assets/minecraft/textures/item/crimson_door.png", "textures/items/crimson_door.png");
        tempBlocks.put("assets/minecraft/textures/item/nether_sprouts.png", "textures/items/nether_sprouts.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_axe.png", "textures/items/netherite_axe.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_boots.png", "textures/items/netherite_boots.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_chestplate.png", "textures/items/netherite_chestplate.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_helmet.png", "textures/items/netherite_helmet.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_hoe.png", "textures/items/netherite_hoe.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_ingot.png", "textures/items/netherite_ingot.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_leggings.png", "textures/items/netherite_leggings.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_pickaxe.png", "textures/items/netherite_pickaxe.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_scrap.png", "textures/items/netherite_scrap.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_shovel.png", "textures/items/netherite_shovel.png");
        tempBlocks.put("assets/minecraft/textures/item/netherite_sword.png", "textures/items/netherite_sword.png");
        tempBlocks.put("assets/minecraft/textures/item/crimson_sign.png", "textures/items/sign_crimson.png");
        tempBlocks.put("assets/minecraft/textures/item/warped_sign.png", "textures/items/sign_warped.png");
        tempBlocks.put("assets/minecraft/textures/item/music_disc_pigstep.png", "textures/items/record_pigstep.png");
        tempBlocks.put("assets/minecraft/textures/item/soul_campfire.png", "textures/items/soul_campfire.png");
        tempBlocks.put("assets/minecraft/textures/item/soul_lantern.png", "textures/items/soul_lantern.png");
        tempBlocks.put("assets/minecraft/textures/item/warped_door.png", "textures/items/warped_door.png");
        tempBlocks.put("assets/minecraft/textures/item/warped_fungus_on_a_stick.png", "textures/items/warped_fungus_on_a_stick.png");

        // armor
        tempBlocks.put("assets/minecraft/textures/models/armor/netherite_layer_1.png", "textures/models/armor/netherite_1.png");
        tempBlocks.put("assets/minecraft/textures/models/armor/netherite_layer_2.png", "textures/models/armor/netherite_2.png");

        // entities
        tempBlocks.put("assets/minecraft/textures/entity/hoglin/hoglin.png", "textures/models/entity/hoglin/hoglin.png");
        tempBlocks.put("assets/minecraft/textures/entity/hoglin/zoglin.png", "textures/models/entity/zoglin/zoglin.png");
        tempBlocks.put("assets/minecraft/textures/entity/piglin/piglin.png", "textures/models/entity/piglin/piglin.png");
        tempBlocks.put("assets/minecraft/textures/entity/piglin/piglin_brute.png", "textures/models/entity/piglin/piglin_brute.png");
        tempBlocks.put("assets/minecraft/textures/entity/piglin/zombified_piglin.png", "textures/models/entity/piglin/zombie_piglin.png");
        tempBlocks.put("assets/minecraft/textures/entity/strider/strider.png", "textures/models/entity/strider/strider.png");
        tempBlocks.put("assets/minecraft/textures/entity/strider/strider_cold.png", "textures/models/entity/strider/strider_suffocated.png");
        tempBlocks.put("assets/minecraft/textures/entity/strider/strider_saddle.png", "textures/models/entity/strider/strider_saddled.png;textures/models/entity/strider/strider_suffocated_saddled.png");
        tempOptions.put("assets/minecraft/textures/entity/strider/strider_saddle.png", "combine[assets/minecraft/textures/entity/strider/strider.png:copy:this:minAlphaToCopy=1];combine[assets/minecraft/textures/entity/strider/strider_cold.png:copy:this:minAlphaToCopy=1]");

        tempBlocks.put("assets/minecraft/textures/entity/signs/crimson.png", "textures/models/entity/sign_crimson.png");
        tempBlocks.put("assets/minecraft/textures/entity/signs/warped.png", "textures/models/entity/sign_warped.png");

        // soul particle
        tempBlocks.put("assets/minecraft/textures/particle/soul_0.png", "textures/particle/soul.png");
        tempOptions.put("assets/minecraft/textures/particle/soul_0.png", "restructure[animation:this:assets/minecraft/textures/particle/soul_1.png:assets/minecraft/textures/particle/soul_2.png:assets/minecraft/textures/particle/soul_3.png:assets/minecraft/textures/particle/soul_4.png:assets/minecraft/textures/particle/soul_5.png:assets/minecraft/textures/particle/soul_6.png:assets/minecraft/textures/particle/soul_7.png:assets/minecraft/textures/particle/soul_8.png:assets/minecraft/textures/particle/soul_9.png:assets/minecraft/textures/particle/soul_10.png]");

        // soulfire flame

        tempBlocks.put("assets/minecraft/textures/particle/soul_fire_flame.png", "");
        tempOptions.put("assets/minecraft/textures/particle/soul_fire_flame.png", "function[particles]");
        particlesOffset.put("assets/minecraft/textures/particle/soul_fire_flame.png", "2,3");


        //1.17 stuff
        tempBlocks.put("assets/minecraft/textures/block/amethyst_block.png", "textures/blocks/amethyst_block.png");
        tempBlocks.put("assets/minecraft/textures/block/amethyst_cluster.png", "textures/blocks/amethyst_cluster.png");
        tempBlocks.put("assets/minecraft/textures/block/azalea_leaves.png", "textures/blocks/azalea_leaves.png");
        tempBlocks.put("assets/minecraft/textures/block/azalea_plant.png", "textures/blocks/azalea_plant.png");
        tempBlocks.put("assets/minecraft/textures/block/azalea_side.png", "textures/blocks/azalea_side.png");
        tempBlocks.put("assets/minecraft/textures/block/azalea_top.png", "textures/blocks/azalea_top.png");
        tempBlocks.put("assets/minecraft/textures/block/big_dripleaf_side.png", "textures/blocks/big_dripleaf_side1.png");
        tempBlocks.put("assets/minecraft/textures/block/big_dripleaf_tip.png", "textures/blocks/big_dripleaf_side2.png");
        tempBlocks.put("assets/minecraft/textures/block/big_dripleaf_stem.png", "textures/blocks/big_dripleaf_stem.png");
        tempBlocks.put("assets/minecraft/textures/block/big_dripleaf_top.png", "textures/blocks/big_dripleaf_top.png");
        tempBlocks.put("assets/minecraft/textures/block/budding_amethyst.png", "textures/blocks/budding_amethyst.png");
        tempBlocks.put("assets/minecraft/textures/block/calcite.png", "textures/blocks/calcite.png");
        tempBlocks.put("assets/minecraft/textures/block/cave_vines.png", "textures/blocks/cave_vines_head.png");
        tempBlocks.put("assets/minecraft/textures/block/cave_vines_lit.png", "textures/blocks/cave_vines_head_berries.png");
        tempBlocks.put("assets/minecraft/textures/block/cave_vines_plant.png", "textures/blocks/cave_vines_body.png");
        tempBlocks.put("assets/minecraft/textures/block/cave_vines_plant_lit.png", "textures/blocks/cave_vines_body_berries.png");
        tempBlocks.put("assets/minecraft/textures/block/copper_block.png", "textures/blocks/copper_block.png");
        tempBlocks.put("assets/minecraft/textures/block/copper_ore.png", "textures/blocks/copper_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/cut_copper.png", "textures/blocks/cut_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/dirt_path_side.png", "textures/blocks/grass_path_side.png");
        tempBlocks.put("assets/minecraft/textures/block/dirt_path_top.png", "textures/blocks/grass_path_top.png");
        tempBlocks.put("assets/minecraft/textures/block/dripstone_block.png", "textures/blocks/dripstone_block.png");
        tempBlocks.put("assets/minecraft/textures/block/exposed_copper.png", "textures/blocks/exposed_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/exposed_cut_copper.png", "textures/blocks/exposed_cut_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/flowering_azalea_leaves.png", "textures/blocks/azalea_leaves_flowers.png");
        tempBlocks.put("assets/minecraft/textures/block/flowering_azalea_side.png", "textures/blocks/flowering_azalea_side.png");
        tempBlocks.put("assets/minecraft/textures/block/flowering_azalea_top.png", "textures/blocks/flowering_azalea_top.png");
        tempBlocks.put("assets/minecraft/textures/block/glow_item_frame.png", "textures/blocks/glow_item_frame.png");
        tempBlocks.put("assets/minecraft/textures/block/glow_lichen.png", "textures/blocks/glow_lichen.png");
        tempBlocks.put("assets/minecraft/textures/block/hanging_roots.png", "textures/blocks/hanging_roots.png");
        tempBlocks.put("assets/minecraft/textures/block/large_amethyst_bud.png", "textures/blocks/large_amethyst_bud.png");
        tempBlocks.put("assets/minecraft/textures/block/lightning_rod.png", "textures/blocks/lightning_rod.png");
        tempBlocks.put("assets/minecraft/textures/block/lightning_rod_on.png", ""); // does not exist
        tempBlocks.put("assets/minecraft/textures/block/medium_amethyst_bud.png", "textures/blocks/medium_amethyst_bud.png");
        tempBlocks.put("assets/minecraft/textures/block/moss_block.png", "textures/blocks/moss_block.png");
        tempBlocks.put("assets/minecraft/textures/block/oxidized_copper.png", "textures/blocks/oxidized_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/oxidized_cut_copper.png", "textures/blocks/oxidized_cut_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_down_base.png", "textures/blocks/pointed_dripstone_down_base.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_down_frustum.png", "textures/blocks/pointed_dripstone_down_frustum.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_down_middle.png", "textures/blocks/pointed_dripstone_down_middle.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_down_tip.png", "textures/blocks/pointed_dripstone_down_tip.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_down_tip_merge.png", "textures/blocks/pointed_dripstone_down_merge.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_up_base.png", "textures/blocks/pointed_dripstone_up_base.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_up_frustum.png", "textures/blocks/pointed_dripstone_up_frustum.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_up_middle.png", "textures/blocks/pointed_dripstone_up_middle.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_up_tip.png", "textures/blocks/pointed_dripstone_up_tip.png");
        tempBlocks.put("assets/minecraft/textures/block/pointed_dripstone_up_tip_merge.png", "textures/blocks/pointed_dripstone_up_merge.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_azalea_bush_plant.png", "textures/blocks/potted_azalea_bush_plant.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_azalea_bush_side.png", "textures/blocks/potted_azalea_bush_side.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_azalea_bush_top.png", "textures/blocks/potted_azalea_bush_top.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_flowering_azalea_bush_plant.png", "textures/blocks/potted_flowering_azalea_bush_plant.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_flowering_azalea_bush_side.png", "textures/blocks/potted_flowering_azalea_bush_side.png");
        tempBlocks.put("assets/minecraft/textures/block/potted_flowering_azalea_bush_top.png", "textures/blocks/potted_flowering_azalea_bush_top.png");
        tempBlocks.put("assets/minecraft/textures/block/powder_snow.png", "textures/blocks/powder_snow.png");
        tempBlocks.put("assets/minecraft/textures/block/raw_copper_block.png", "textures/blocks/raw_copper_block.png");
        tempBlocks.put("assets/minecraft/textures/block/raw_gold_block.png", "textures/blocks/raw_gold_block.png");
        tempBlocks.put("assets/minecraft/textures/block/raw_iron_block.png", "textures/blocks/raw_iron_block.png");
        tempBlocks.put("assets/minecraft/textures/block/rooted_dirt.png", "textures/blocks/dirt_with_roots.png");
        tempBlocks.put("assets/minecraft/textures/block/sculk_sensor_bottom.png", "textures/blocks/sculk_sensor_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/sculk_sensor_side.png", "textures/blocks/sculk_sensor_side.png");
        tempBlocks.put("assets/minecraft/textures/block/sculk_sensor_tendril_active.png", "textures/blocks/sculk_sensor_tendril_active.png");
        tempBlocks.put("assets/minecraft/textures/block/sculk_sensor_tendril_inactive.png", "textures/blocks/sculk_sensor_tendril_inactive.png");
        tempBlocks.put("assets/minecraft/textures/block/sculk_sensor_top.png", "textures/blocks/sculk_sensor_top.png");
        tempBlocks.put("assets/minecraft/textures/block/small_amethyst_bud.png", "textures/blocks/small_amethyst_bud.png");
        tempBlocks.put("assets/minecraft/textures/block/small_dripleaf_side.png", "textures/blocks/small_dripleaf_side.png");
        tempBlocks.put("assets/minecraft/textures/block/small_dripleaf_stem_bottom.png", "textures/blocks/small_dripleaf_stem_bottom.png");
        tempBlocks.put("assets/minecraft/textures/block/small_dripleaf_stem_top.png", "textures/blocks/small_dripleaf_stem_top.png");
        tempBlocks.put("assets/minecraft/textures/block/small_dripleaf_top.png", "textures/blocks/small_dripleaf_top.png");
        tempBlocks.put("assets/minecraft/textures/block/smooth_basalt.png", "textures/blocks/smooth_basalt.png");
        tempBlocks.put("assets/minecraft/textures/block/spore_blossom.png", "textures/blocks/spore_blossom.png");
        tempBlocks.put("assets/minecraft/textures/block/spore_blossom_base.png", "textures/blocks/spore_blossom_base.png");
        tempBlocks.put("assets/minecraft/textures/block/tinted_glass.png", "textures/blocks/tinted_glass.png");
        tempBlocks.put("assets/minecraft/textures/block/tuff.png", "textures/blocks/tuff.png");
        tempBlocks.put("assets/minecraft/textures/block/weathered_copper.png", "textures/blocks/weathered_copper.png");
        tempBlocks.put("assets/minecraft/textures/block/weathered_cut_copper.png", "textures/blocks/weathered_cut_copper.png");

        tempBlocks.put("assets/minecraft/textures/block/chiseled_deepslate.png", "textures/blocks/deepslate/chiseled_deepslate.png");
        tempBlocks.put("assets/minecraft/textures/block/cobbled_deepslate.png", "textures/blocks/deepslate/cobbled_deepslate.png");
        tempBlocks.put("assets/minecraft/textures/block/cracked_deepslate_bricks.png", "textures/blocks/deepslate/cracked_deepslate_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/cracked_deepslate_tiles.png", "textures/blocks/deepslate/cracked_deepslate_tiles.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate.png", "textures/blocks/deepslate/deepslate.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_bricks.png", "textures/blocks/deepslate/deepslate_bricks.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_coal_ore.png", "textures/blocks/deepslate/deepslate_coal_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_copper_ore.png", "textures/blocks/deepslate/deepslate_copper_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_diamond_ore.png", "textures/blocks/deepslate/deepslate_diamond_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_emerald_ore.png", "textures/blocks/deepslate/deepslate_emerald_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_gold_ore.png", "textures/blocks/deepslate/deepslate_gold_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_iron_ore.png", "textures/blocks/deepslate/deepslate_iron_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_lapis_ore.png", "textures/blocks/deepslate/deepslate_lapis_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_redstone_ore.png", "textures/blocks/deepslate/deepslate_redstone_ore.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_tiles.png", "textures/blocks/deepslate/deepslate_tiles.png");
        tempBlocks.put("assets/minecraft/textures/block/deepslate_top.png", "textures/blocks/deepslate/deepslate_top.png");
        tempBlocks.put("assets/minecraft/textures/block/polished_deepslate.png", "textures/blocks/deepslate/polished_deepslate.png");

        tempBlocks.put("assets/minecraft/textures/block/black_candle.png", "textures/blocks/candles/black_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/black_candle_lit.png", "textures/blocks/candles/black_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_candle.png", "textures/blocks/candles/blue_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/blue_candle_lit.png", "textures/blocks/candles/blue_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_candle.png", "textures/blocks/candles/brown_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/brown_candle_lit.png", "textures/blocks/candles/brown_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/candle.png", "textures/blocks/candles/candle.png");
        tempBlocks.put("assets/minecraft/textures/block/candle_lit.png", "textures/blocks/candles/candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_candle.png", "textures/blocks/candles/cyan_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/cyan_candle_lit.png", "textures/blocks/candles/cyan_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_candle.png", "textures/blocks/candles/gray_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/gray_candle_lit.png", "textures/blocks/candles/gray_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/green_candle.png", "textures/blocks/candles/green_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/green_candle_lit.png", "textures/blocks/candles/green_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_candle.png", "textures/blocks/candles/light_blue_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/light_blue_candle_lit.png", "textures/blocks/candles/light_blue_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/light_gray_candle.png", "textures/blocks/candles/light_gray_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_candle.png", "textures/blocks/candles/lime_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/lime_candle_lit.png", "textures/blocks/candles/lime_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_candle.png", "textures/blocks/candles/magenta_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/magenta_candle_lit.png", "textures/blocks/candles/magenta_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_candle.png", "textures/blocks/candles/orange_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/orange_candle_lit.png", "textures/blocks/candles/orange_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_candle.png", "textures/blocks/candles/pink_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/pink_candle_lit.png", "textures/blocks/candles/pink_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_candle.png", "textures/blocks/candles/purple_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/purple_candle_lit.png", "textures/blocks/candles/purple_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/red_candle.png", "textures/blocks/candles/red_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/red_candle_lit.png", "textures/blocks/candles/red_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/white_candle.png", "textures/blocks/candles/white_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/white_candle_lit.png", "textures/blocks/candles/white_candle_lit.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_candle.png", "textures/blocks/candles/yellow_candle.png");
        tempBlocks.put("assets/minecraft/textures/block/yellow_candle_lit.png", "textures/blocks/candles/yellow_candle_lit.png");

        // entities
        tempBlocks.put("assets/minecraft/textures/entity/axolotl/axolotl_blue.png", "textures/entity/axolotl/axolotl_blue.png");
        tempBlocks.put("assets/minecraft/textures/entity/axolotl/axolotl_cyan.png", "textures/entity/axolotl/axolotl_cyan.png");
        tempBlocks.put("assets/minecraft/textures/entity/axolotl/axolotl_gold.png", "textures/entity/axolotl/axolotl_gold.png");
        tempBlocks.put("assets/minecraft/textures/entity/axolotl/axolotl_lucy.png", "textures/entity/axolotl/axolotl_lucy.png");
        tempBlocks.put("assets/minecraft/textures/entity/axolotl/axolotl_wild.png", "textures/entity/axolotl/axolotl_wild.png");

        tempBlocks.put("assets/minecraft/textures/entity/goat/goat.png", "textures/entity/goat/goat.png");
        tempBlocks.put("assets/minecraft/textures/entity/squid/glow_squid.png", "textures/entity/glow_squid/glow_squid.png");
        tempBlocks.put("assets/minecraft/textures/entity/squid/squid.png", "textures/entity/squid.png");
        tempBlocks.put("assets/minecraft/textures/entity/.png", "textures/entity/.png");
        tempBlocks.put("assets/minecraft/textures/entity/.png", "textures/entity/.png");
        tempBlocks.put("assets/minecraft/textures/entity/.png", "textures/entity/.png");
        tempBlocks.put("assets/minecraft/textures/entity/.png", "textures/entity/.png");

        optionsDefault.put("horseLeatherItemThreshold", "99");
        optionsDefault.put("horseLeatherArmorThreshold", "150");
        //optionsDefault.put("bannerpatern", "textures/items/banner_pattern.png");

        optionsDefault.put("assets/minecraft/textures/item/creeper_banner_pattern.png", "textures/items/banner_pattern.png");
        optionsDefault.put("assets/minecraft/textures/item/globe_banner_pattern.png", "textures/items/banner_pattern.png");
        optionsDefault.put("assets/minecraft/textures/item/flower_banner_pattern.png", "textures/items/banner_pattern.png");
        optionsDefault.put("assets/minecraft/textures/item/mojang_banner_pattern.png", "textures/items/banner_pattern.png");
        optionsDefault.put("assets/minecraft/textures/item/skull_banner_pattern.png", "textures/items/banner_pattern.png");

        textures = Collections.unmodifiableMap(tempBlocks);
        texturesMode = Collections.unmodifiableMap(tempOptions);

        resetOptionDone();
        //resetOptions();
    }

    public static Map<String, String> getOptions() {
        return new HashMap<>(optionsDefault);
    }

    public static String getDefaultOption(String key) {
        return optionsDefault.get(key);
    }

    /*public static void resetOptions() {
        options.putAll(optionsDefault);
    }
    public static void resetOption(String option) {
        if (optionsDefault.containsKey(option))
            options.put(option, optionsDefault.get(option));
    }*/

    public static void resetOptionDone() {
        optionDone.put("painting", false);
        optionDone.put("campfire_smoke", false);
        optionDone.put("particles", false);
        optionDone.put("banner", false);
        optionDone.put("furnace", false);
        optionDone.put("textures/items/banner_pattern.png", false);
    }

    public static String getModeForVersion(String key, int packVersion) {
        String data = texturesModeVersionspecific.get(key);
        String[] split = data.split("\\|");
        if (split.length >= packVersion) {
            if (split[packVersion-1].length() == 1) {
                return split[Integer.parseInt(split[packVersion-1])];
            }
            return split[packVersion-1];
        }
        return "";
    }
}

package net.withrage.simpleexcavators.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModToolMaterial {

    public static final Tier WOOD = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.woodenDurability; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 1.0F; }
        @Override public int getEnchantmentValue() { return 8; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(ItemTags.PLANKS); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_WOODEN_TOOL; }
    };

    public static final Tier STONE = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.stoneDurability; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 1.0F; }
        @Override public int getEnchantmentValue() { return 9; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_STONE_TOOL; }
    };

    public static final Tier COPPER = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.copperDurability; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 1.0F; }
        @Override public int getEnchantmentValue() { return 10; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.COPPER_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_IRON_TOOL; }
    };

    public static final Tier GOLD = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.goldenDurability; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 1.0F; }
        @Override public int getEnchantmentValue() { return 11; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.GOLD_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_GOLD_TOOL; }
    };

    public static final Tier IRON = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.ironDurability; }
        @Override public float getSpeed() { return 6.0F; }
        @Override public float getAttackDamageBonus() { return 2.0F; }
        @Override public int getEnchantmentValue() { return 12; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.IRON_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_IRON_TOOL; }
    };

    public static final Tier EMERALD = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.emeraldDurability; }
        @Override public float getSpeed() { return 9.0F; }
        @Override public float getAttackDamageBonus() { return 3.0F; }
        @Override public int getEnchantmentValue() { return 18; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.EMERALD); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_DIAMOND_TOOL; }
    };

    public static final Tier DIAMOND = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.diamondDurability; }
        @Override public float getSpeed() { return 7.0F; }
        @Override public float getAttackDamageBonus() { return 3.0F; }
        @Override public int getEnchantmentValue() { return 16; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.DIAMOND); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_DIAMOND_TOOL; }
    };

    public static final Tier NETHERITE = new Tier() {
        @Override public int getUses() { return SimpleExcavatorsConfig.netheriteDurability; }
        @Override public float getSpeed() { return 8.0F; }
        @Override public float getAttackDamageBonus() { return 4.0F; }
        @Override public int getEnchantmentValue() { return 16; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.NETHERITE_INGOT); }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_NETHERITE_TOOL; }
    };
}

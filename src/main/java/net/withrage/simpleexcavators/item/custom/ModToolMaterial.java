package net.withrage.simpleexcavators.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class ModToolMaterial {

    public static final ToolMaterial WOOD      = ToolMaterials.WOOD;
    public static final ToolMaterial STONE     = ToolMaterials.STONE;
    public static final ToolMaterial IRON      = ToolMaterials.IRON;
    public static final ToolMaterial GOLD      = ToolMaterials.GOLD;
    public static final ToolMaterial DIAMOND   = ToolMaterials.DIAMOND;
    public static final ToolMaterial NETHERITE = ToolMaterials.NETHERITE;

    public static final ToolMaterial COPPER = new ToolMaterial() {
        @Override public int getDurability() { return 262; }
        @Override public float getMiningSpeedMultiplier() { return 6.0F; }
        @Override public float getAttackDamage() { return 2.0F; }
        @Override public int getEnchantability() { return 12; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.COPPER_INGOT); }
        @Override public TagKey<Block> getInverseTag() { return BlockTags.INCORRECT_FOR_IRON_TOOL; }
    };

    public static final ToolMaterial EMERALD = new ToolMaterial() {
        @Override public int getDurability() { return 750; }
        @Override public float getMiningSpeedMultiplier() { return 8.0F; }
        @Override public float getAttackDamage() { return 3.0F; }
        @Override public int getEnchantability() { return 18; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.EMERALD); }
        @Override public TagKey<Block> getInverseTag() { return BlockTags.INCORRECT_FOR_DIAMOND_TOOL; }
    };
}
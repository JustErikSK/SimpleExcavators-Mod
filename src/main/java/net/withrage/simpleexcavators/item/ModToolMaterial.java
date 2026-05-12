package net.withrage.simpleexcavators.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;

public class ModToolMaterial {
    public static final Tier WOOD = Tiers.WOOD;
    public static final Tier STONE = Tiers.STONE;
    public static final Tier GOLD = Tiers.GOLD;
    public static final Tier IRON = Tiers.IRON;
    public static final Tier DIAMOND = Tiers.DIAMOND;
    public static final Tier NETHERITE = Tiers.NETHERITE;

    public static final Tier COPPER = new Tier() {
        @Override
        public int getUses() {
            return 262;
        }

        @Override
        public float getSpeed() {
            return 5.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 2.0F;
        }

        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getEnchantmentValue() {
            return 12;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.COPPER_INGOT);
        }
    };

    public static final Tier EMERALD = new Tier() {
        @Override
        public int getUses() {
            return 2084;
        }

        @Override
        public float getSpeed() {
            return 8.5F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 3.0F;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.EMERALD);
        }
    };
}

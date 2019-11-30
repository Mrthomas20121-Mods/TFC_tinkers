package com.mrthomas20121.tinkerfirmacraft.Traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBrittle extends AbstractTrait {

    public TraitBrittle() {
        super("brittle", 0xffffff);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        /* Damage the tool on it */
        tool.damageItem(100, player);
    }
    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        tool.damageItem(50, player);
    }
}

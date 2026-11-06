package dev.ricstar.rickclient.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.ricstar.rickclient.RickClient;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Inject(method = "applyItemArmAttackTransform", at = @At("HEAD"), cancellable = true, require = 0)
    private void rickclient$sakuraSlash(PoseStack poseStack, HumanoidArm arm, float swingProgress, CallbackInfo ci) {
        if (!RickClient.sakuraSwing) {
            return;
        }
        int side = arm == HumanoidArm.RIGHT ? 1 : -1;
        float p = Mth.clamp(swingProgress, 0.0F, 1.0F);
        // Schneller Schwung nach vorn, weich zurück
        float slash = Mth.sin(p * (float) Math.PI);
        float sweep = Mth.sin((float) Math.sqrt(p) * (float) Math.PI);

        poseStack.mulPose(Axis.YP.rotationDegrees(side * (45.0F - slash * 38.0F)));
        poseStack.mulPose(Axis.ZP.rotationDegrees(side * (sweep * -55.0F + slash * 22.0F)));
        poseStack.mulPose(Axis.XP.rotationDegrees(sweep * -95.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(side * -45.0F));
        poseStack.translate(0.0F, -0.05F * slash, -0.12F * slash);
        ci.cancel();
    }
}

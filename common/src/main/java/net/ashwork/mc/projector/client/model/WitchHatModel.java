package net.ashwork.mc.projector.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class WitchHatModel<T extends EntityRenderState> extends EntityModel<T> {

	public WitchHatModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		var head = root.addOrReplaceChild(
				"head",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		var layer0 = head.addOrReplaceChild(
				"layer0",
				CubeListBuilder.create()
						.texOffs(0, 11).addBox(-1.0F, -2.0F, -4.5F, 2.0F, 1.0F, 1.0F)
						.texOffs(0, 0).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		var layer1 = layer0.addOrReplaceChild(
				"layer1",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -2.75F, -4.0F, 8.0F, 2.0F, 8.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		var layer2 = layer1.addOrReplaceChild(
				"layer2",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-3.5F, -4.6F, -3.85F, 7.0F, 2.0F, 7.0F),
				PartPose.offset(0.0F, 0.25F, 0.25F)
		);

		var layer3 = layer2.addOrReplaceChild(
				"layer3",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, -4.9551F, -0.3147F)
		);

		var layer4 = layer3.addOrReplaceChild(
				"layer4",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-2.5F, -1.0F, -2.8F, 5.0F, 2.0F, 5.0F),
				PartPose.offset(0.0F, -1.5102F, 0.332F)
		);

		var layer5 = layer4.addOrReplaceChild(
				"layer5",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-2.0F, -1.0F, -2.9F, 4.0F, 2.0F, 4.0F),
				PartPose.offset(0.0F, -1.4859F, 0.5315F)
		);

		var layer6 = layer5.addOrReplaceChild(
				"layer6",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-1.5F, -1.0F, -2.9F, 3.0F, 2.0F, 3.0F),
				PartPose.offset(0.0F, -1.5112F, 0.5146F)
		);

		var layer7 = layer6.addOrReplaceChild(
				"layer7",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-1.0F, -1.0F, -2.6F, 2.0F, 2.0F, 2.0F),
				PartPose.offset(0.0F, -1.2976F, 0.22F)
		);

		var layer8 = layer7.addOrReplaceChild(
				"layer8",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-0.5F, -1.0F, -0.55F, 1.0F, 2.0F, 1.0F),
				PartPose.offset(0.0F, -1.44F, -1.5334F)
		);

		return LayerDefinition.create(mesh, 64, 32);
	}
}
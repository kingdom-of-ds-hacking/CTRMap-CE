package ctrmap.renderer.scene.animation.skeletal;

import xstandard.math.vec.Vec3f;
import xstandard.math.InterpolationTimer;
import xstandard.math.vec.Matrix4;
import xstandard.math.vec.Quaternion;
import org.joml.Quaternionf;

public class InterpolatedBoneTransform extends SkeletalBoneTransform {

	private SkeletalBoneTransform leftBT;
	private SkeletalBoneTransform rightBT;
	private InterpolationTimer timer;
	private SkeletalController ctrlL;
	private SkeletalController ctrlR;

	public InterpolatedBoneTransform(SkeletalBoneTransform leftBT, SkeletalBoneTransform rightBT, SkeletalController ctrlL, SkeletalController ctrlR, InterpolationTimer timer) {
		name = leftBT.name;
		this.leftBT = leftBT;
		this.rightBT = rightBT;
		this.timer = timer;
		this.ctrlL = ctrlL;
		this.ctrlR = ctrlR;
	}

	@Override
	public Matrix4 getTransformMatrix(SkeletalAnimationTransformRequest req, Matrix4 dest) {
		float weight = timer.getInterpolationWeight();
		req.frame = ctrlL.frame;
		SkeletalAnimationFrame left = leftBT.getFrame(req);
		req.frame = ctrlR.frame;
		SkeletalAnimationFrame right = rightBT.getFrame(req);

		if (weight == 1f) {
			right.getTransformMatrix(dest);
		} else if (weight == 0f) {
			left.getTransformMatrix(dest);
		} else {
			Vec3f translation = new Vec3f(left.getTranslation(), right.getTranslation(), weight);
			Vec3f scale = new Vec3f(left.getScale(), right.getScale(), weight);
			Quaternionf quat = left.getRotation();
			quat.slerp(right.getRotation(), weight);
			quat.normalize();

			dest.translation(translation);
			dest.rotate(quat);
			dest.scale(scale);
		}
		
		left.free();
		right.free();

		return dest;
	}

	@Override
	public SkeletalAnimationFrame getFrame(SkeletalAnimationTransformRequest req) {
		float weight = timer.getInterpolationWeight();
		req.frame = ctrlL.frame;
		SkeletalAnimationFrame left = leftBT.getFrame(req);
		req.frame = ctrlR.frame;
		SkeletalAnimationFrame right = rightBT.getFrame(req);

		if (weight == 1f) {
			left.free();
			return right;
		}
		if (weight == 0f) {
			right.free();
			return left;
		}

		SkeletalAnimationFrame frm = new SkeletalAnimationFrame(req.useManualAllocation);
		if (req.translation) {
			Vec3f translation = new Vec3f(left.getTranslation(), right.getTranslation(), weight);
			frm.tx.setIfNotExists(translation.x);
			frm.ty.setIfNotExists(translation.y);
			frm.tz.setIfNotExists(translation.z);
		}
		if (req.rotation) {
			Quaternion quat = left.getRotation();
			quat.slerp(right.getRotation(), weight);
			quat.normalize();
			Vec3f rot = quat.getEulerRotation();
			frm.rx.setIfNotExists(rot.x);
			frm.ry.setIfNotExists(rot.y);
			frm.rz.setIfNotExists(rot.z);
		}
		if (req.scale) {
			Vec3f scale = new Vec3f(left.getScale(), right.getScale(), weight);
			frm.sx.setIfNotExists(scale.x);
			frm.sy.setIfNotExists(scale.y);
			frm.sz.setIfNotExists(scale.z);
		}

		left.free();
		right.free();

		return frm;
	}
}

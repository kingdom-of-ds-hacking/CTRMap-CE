package ctrmap.renderer.scene.model;

import xstandard.math.vec.Vec3f;
import ctrmap.renderer.scene.Scene;
import ctrmap.renderer.scenegraph.NamedResource;
import ctrmap.renderer.scene.texturing.Material;
import ctrmap.renderer.scene.metadata.MetaData;
import xstandard.math.vec.Matrix4;
import xstandard.util.ArraysEx;
import xstandard.util.ListenableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import xstandard.math.AABB6f;

public class Model implements NamedResource {

	public String name;

	public boolean isVisible = true;

	public final ListenableList<Material> materials = new ListenableList<>();

	public final ListenableList<Mesh> meshes = new ListenableList<>();

	public final ListenableList<MeshVisibilityGroup> visGroups = new ListenableList<>();

	public Skeleton skeleton = new Skeleton();

	public final AABB6f boundingBox = new AABB6f();

	public final MetaData metaData = new MetaData();

	public Model() {
		materials.addListener(new ListenableList.ElementChangeListener() {
			@Override
			public void onEntityChange(ListenableList.ElementChangeEvent evt) {
				if (evt.type == ListenableList.ElementChangeType.ADD) {
					Material mat = (Material) evt.element;
					if (mat != null) {
						mat.parentModel = Model.this;
					}
				}
			}
		});
		meshes.addListener(new ListenableList.ElementChangeListener() {
			@Override
			public void onEntityChange(ListenableList.ElementChangeEvent evt) {
				if (evt.type == ListenableList.ElementChangeType.ADD) {
					Mesh mesh = (Mesh) evt.element;
					if (mesh != null) {
						mesh.parentModel = Model.this;
						mesh.createAndInvalidateBuffers();
						updateBbox(mesh);
					}
				}
			}
		});
	}

	@Override
	public String getName() {
		return name;
	}

	public void createVisGroups() {
		HashSet<String> visGroupNames = new HashSet<>();
		for (MeshVisibilityGroup g : visGroups) {
			visGroupNames.add(g.name);
		}
		for (Mesh m : meshes) {
			if (m.visGroupName != null) {
				if (!visGroupNames.contains(m.visGroupName)) {
					visGroupNames.add(m.visGroupName);
				}
			}
		}
		List<String> namesList = new ArrayList<>(visGroupNames);
		Collections.sort(namesList);

		for (String visGroupName : namesList) {
			visGroups.add(new MeshVisibilityGroup(visGroupName));
		}
	}

	public void takeOwnMeshesAndMats() {
		for (Mesh mesh : meshes) {
			mesh.parentModel = this;
		}
		for (Material mat : materials) {
			mat.parentModel = this;
		}
		if (skeleton != null) {
			for (Joint j : skeleton.getJoints()) {
				j.parentSkeleton = skeleton;
			}
		}
	}

	public List<Vertex> getCollectiveVertices() {
		List<Vertex> l = new ArrayList<>();
		for (Mesh m : meshes) {
			l.addAll(m.vertices);
		}
		return l;
	}

	public int getTotalVertexCount() {
		int vc = 0;
		for (Mesh m : meshes) {
			vc += m.getVertexCount();
		}
		return vc;
	}

	public int getTotalVertexCountVBO() {
		int vc = 0;
		for (Mesh m : meshes) {
			vc += m.vertices.size();
		}
		return vc;
	}

	public void addMaterial(Material m) {
		ArraysEx.addIfNotNullOrContains(materials, m);
	}

	public void addMesh(Mesh m) {
		ArraysEx.addIfNotNullOrContains(meshes, m);
	}

	public void addVisGroup(MeshVisibilityGroup visGroup) {
		ArraysEx.addIfNotNullOrContains(visGroups, visGroup);
	}

	public Mesh getMeshByName(String name) {
		return Scene.getNamedObject(name, meshes);
	}

	public Material getMaterialByName(String name) {
		return Scene.getNamedObject(name, materials);
	}

	public MeshVisibilityGroup getVisGroupByName(String name) {
		return Scene.getNamedObject(name, visGroups);
	}

	private void resetBBox() {
		boundingBox.reset();
	}

	public void genBbox() {
		resetBBox();
		for (Mesh m : meshes) {
			updateBbox(m);
		}
	}

	private void updateBbox(Mesh m) {
		if (meshes.size() == 1) {
			resetBBox();
		} else if (meshes.isEmpty()) {
			boundingBox.zero();
		}
		Vec3f min = m.calcMinVector();
		Vec3f max = m.calcMaxVector();
		if (m.skinningType == Mesh.SkinningType.RIGID) {
			if (!m.vertices.isEmpty()) {
				Vertex vtx = m.vertices.get(0);
				if (!vtx.boneIndices.isEmpty()) {
					int bidx = vtx.boneIndices.get(0);

					if (bidx < skeleton.getJointCount()) {
						Matrix4 rigidJointMtx = skeleton.getAbsoluteJointBindPoseMatrix(skeleton.getJoint(bidx));
						min.mulPosition(rigidJointMtx);
						max.mulPosition(rigidJointMtx);
					}
				}
			}
		}
		boundingBox.min.min(min);
		boundingBox.max.max(max);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}

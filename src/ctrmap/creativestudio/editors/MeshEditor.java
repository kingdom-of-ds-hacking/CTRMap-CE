package ctrmap.creativestudio.editors;

import ctrmap.creativestudio.ngcs.tree.CSNode;
import ctrmap.renderer.scene.model.Mesh;
import ctrmap.renderer.scene.model.MeshVisibilityGroup;
import ctrmap.renderer.scene.model.PrimitiveType;
import ctrmap.renderer.scene.texturing.Material;
import ctrmap.renderer.util.MeshProcessor;
import ctrmap.renderer.util.ModelProcessor;
import ctrmap.renderer.util.PrimitiveConverter;
import xstandard.text.FormattingUtils;
import xstandard.gui.components.ComponentUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import xstandard.gui.DialogUtils;

public class MeshEditor extends javax.swing.JPanel implements IEditor {

	/**
	 * Creates new form MeshEditor
	 */
	public MeshEditor() {
		initComponents();

		ActionListener rlListener = (ActionEvent e) -> {
			setRl();
		};
		rl1Btn.addActionListener(rlListener);
		rl2Btn.addActionListener(rlListener);
		rl3Btn.addActionListener(rlListener);
		rl4Btn.addActionListener(rlListener);
	}

	private Mesh mesh = null;
	private CSNode node;
	private boolean loaded = false;

	@Override
	public void handleObject(Object o) {
		loaded = false;
		if (IEditor.checkIsCompatibleNG(o, Mesh.class)) {
			node = (CSNode) o;
			mesh = (Mesh) node.getContent();
			materialDropdown.removeAllItems();
			visGroupDropdown.removeAllItems();
			int idx = -1;
			if (mesh.parentModel != null) {
				int i = 0;
				for (Material mat : mesh.parentModel.materials) {
					if (Objects.equals(mat.name, mesh.materialName)) {
						idx = i;
					}
					i++;
					materialDropdown.addItem(mat.name);
				}
				materialDropdown.setSelectedIndex(idx);
				i = 0;
				idx = -1;
				for (MeshVisibilityGroup visGroup : mesh.parentModel.visGroups) {
					if (Objects.equals(visGroup.name, mesh.visGroupName)) {
						idx = i;
					}
					i++;
					visGroupDropdown.addItem(visGroup.name);
				}
				visGroupDropdown.setSelectedIndex(idx);
			}
			switch (mesh.renderLayer) {
				case 0:
					rl1Btn.setSelected(true);
					break;
				case 1:
					rl2Btn.setSelected(true);
					break;
				case 2:
					rl3Btn.setSelected(true);
					break;
				case 3:
					rl4Btn.setSelected(true);
					break;
			}
			primitiveType.setText(FormattingUtils.getFriendlyEnum(mesh.primitiveType));
			skinning.setText(FormattingUtils.getFriendlyEnum(mesh.skinningType));
		} else {
			mesh = null;
			node = null;
			materialDropdown.removeAllItems();
			visGroupDropdown.removeAllItems();
			ComponentUtils.clearComponents(materialDropdown, visGroupDropdown, rl1Btn, rl2Btn, rl3Btn, rl4Btn);
			primitiveType.setText("--");
		}
		nameField.loadNode(node);
		loaded = true;
	}

	@Override
	public void save() {
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this
	 * code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rlBtnGroup = new javax.swing.ButtonGroup();
        generalPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        materialLabel = new javax.swing.JLabel();
        materialDropdown = new javax.swing.JComboBox<>();
        ptLabel = new javax.swing.JLabel();
        primitiveType = new javax.swing.JLabel();
        skinningLabel = new javax.swing.JLabel();
        skinning = new javax.swing.JLabel();
        nameField = new ctrmap.creativestudio.editors.NameTextField();
        materialLabel1 = new javax.swing.JLabel();
        visGroupDropdown = new javax.swing.JComboBox<>();
        renderingPanel = new javax.swing.JPanel();
        layerLabel = new javax.swing.JLabel();
        rl1Btn = new javax.swing.JRadioButton();
        rl2Btn = new javax.swing.JRadioButton();
        rl3Btn = new javax.swing.JRadioButton();
        rl4Btn = new javax.swing.JRadioButton();
        miscPanel = new javax.swing.JPanel();
        btnVColToAlpha = new javax.swing.JButton();
        btnVColCreate = new javax.swing.JButton();
        btnSet010Normal = new javax.swing.JButton();
        btnTriangulate = new javax.swing.JButton();
        btnCalcTangents = new javax.swing.JButton();
        tgtCalcUVSetLabel = new javax.swing.JLabel();
        tgtCalcUVSet = new javax.swing.JSpinner();

        generalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));

        nameLabel.setText("Name");

        materialLabel.setText("Material");

        materialDropdown.setMaximumRowCount(25);
        materialDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialDropdownActionPerformed(evt);
            }
        });

        ptLabel.setText("Primitive type:");

        primitiveType.setText("--");

        skinningLabel.setText("Skinning mode:");

        skinning.setText("--");

        materialLabel1.setText("Visibility group");

        visGroupDropdown.setMaximumRowCount(25);
        visGroupDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visGroupDropdownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(visGroupDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(materialLabel)
                            .addGroup(generalPanelLayout.createSequentialGroup()
                                .addComponent(ptLabel)
                                .addGap(18, 18, 18)
                                .addComponent(primitiveType, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(generalPanelLayout.createSequentialGroup()
                                .addComponent(skinningLabel)
                                .addGap(15, 15, 15)
                                .addComponent(skinning, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(materialLabel1))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visGroupDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptLabel)
                    .addComponent(primitiveType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skinningLabel)
                    .addComponent(skinning))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        renderingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Rendering"));

        layerLabel.setText("Layer");

        rlBtnGroup.add(rl1Btn);
        rl1Btn.setText("1");

        rlBtnGroup.add(rl2Btn);
        rl2Btn.setText("2");

        rlBtnGroup.add(rl3Btn);
        rl3Btn.setText("3");

        rlBtnGroup.add(rl4Btn);
        rl4Btn.setText("4");

        javax.swing.GroupLayout renderingPanelLayout = new javax.swing.GroupLayout(renderingPanel);
        renderingPanel.setLayout(renderingPanelLayout);
        renderingPanelLayout.setHorizontalGroup(
            renderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(renderingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(renderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(renderingPanelLayout.createSequentialGroup()
                        .addComponent(rl1Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rl2Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rl3Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rl4Btn))
                    .addComponent(layerLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        renderingPanelLayout.setVerticalGroup(
            renderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(renderingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(renderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rl1Btn)
                    .addComponent(rl2Btn)
                    .addComponent(rl3Btn)
                    .addComponent(rl4Btn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        miscPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Processing"));

        btnVColToAlpha.setText("Vertex color -> alpha");
        btnVColToAlpha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVColToAlphaActionPerformed(evt);
            }
        });

        btnVColCreate.setText("Create/clear color layer");
        btnVColCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVColCreateActionPerformed(evt);
            }
        });

        btnSet010Normal.setText("Make all normals point up");
        btnSet010Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSet010NormalActionPerformed(evt);
            }
        });

        btnTriangulate.setText("Triangulate");
        btnTriangulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriangulateActionPerformed(evt);
            }
        });

        btnCalcTangents.setText("Recalculate tangents");
        btnCalcTangents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcTangentsActionPerformed(evt);
            }
        });

        tgtCalcUVSetLabel.setText("UV set:");

        tgtCalcUVSet.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));

        javax.swing.GroupLayout miscPanelLayout = new javax.swing.GroupLayout(miscPanel);
        miscPanel.setLayout(miscPanelLayout);
        miscPanelLayout.setHorizontalGroup(
            miscPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miscPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(miscPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVColCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVColToAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSet010Normal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTriangulate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCalcTangents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgtCalcUVSetLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgtCalcUVSet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        miscPanelLayout.setVerticalGroup(
            miscPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miscPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVColCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVColToAlpha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSet010Normal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTriangulate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(miscPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcTangents)
                    .addComponent(tgtCalcUVSetLabel)
                    .addComponent(tgtCalcUVSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(miscPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(renderingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(renderingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(miscPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void materialDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialDropdownActionPerformed
		if (loaded && materialDropdown.getSelectedIndex() != -1 && mesh != null && mesh.parentModel != null) {
			mesh.materialName = String.valueOf(materialDropdown.getSelectedItem());
		}
    }//GEN-LAST:event_materialDropdownActionPerformed

    private void btnVColToAlphaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVColToAlphaActionPerformed
		if (mesh != null) {
			ModelProcessor.colorToAlpha(mesh);
		}
    }//GEN-LAST:event_btnVColToAlphaActionPerformed

    private void btnVColCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVColCreateActionPerformed
		if (mesh != null) {
			ModelProcessor.clearVCol(mesh);
		}
    }//GEN-LAST:event_btnVColCreateActionPerformed

    private void btnSet010NormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSet010NormalActionPerformed
		if (mesh != null) {
			MeshProcessor.set010Normal(mesh);
		}
    }//GEN-LAST:event_btnSet010NormalActionPerformed

    private void btnTriangulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriangulateActionPerformed
		if (mesh != null) {
			PrimitiveConverter.triangulate(mesh);
		}
    }//GEN-LAST:event_btnTriangulateActionPerformed

    private void visGroupDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visGroupDropdownActionPerformed
		if (loaded && visGroupDropdown.getSelectedIndex() != -1 && mesh != null && mesh.parentModel != null) {
			mesh.visGroupName = String.valueOf(visGroupDropdown.getSelectedItem());
		}
    }//GEN-LAST:event_visGroupDropdownActionPerformed

    private void btnCalcTangentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcTangentsActionPerformed
		if (mesh != null) {
			int uvSet = ((Number) tgtCalcUVSet.getValue()).intValue();
			if (!mesh.hasUV(uvSet)) {
				DialogUtils.showErrorMessage(node.getCS(), "Not applicable", "This mesh does not contain UV layer " + uvSet + ".");
			}
			else {
				if (mesh.primitiveType != PrimitiveType.TRIS) {
					if (DialogUtils.showYesNoDialog(node.getCS(), "Triangulate", "Mesh has to be triangulated. Triangulate?")) {
						PrimitiveConverter.triangulate(mesh);
					}
					else {
						return;
					}
				}
				MeshProcessor.calculateTangents(mesh, uvSet);
			}
		}
    }//GEN-LAST:event_btnCalcTangentsActionPerformed

	private void setRl() {
		if (mesh != null) {
			if (rl1Btn.isSelected()) {
				mesh.renderLayer = 0;
			} else if (rl2Btn.isSelected()) {
				mesh.renderLayer = 1;
			} else if (rl3Btn.isSelected()) {
				mesh.renderLayer = 2;
			} else if (rl4Btn.isSelected()) {
				mesh.renderLayer = 3;
			}
		}
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcTangents;
    private javax.swing.JButton btnSet010Normal;
    private javax.swing.JButton btnTriangulate;
    private javax.swing.JButton btnVColCreate;
    private javax.swing.JButton btnVColToAlpha;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JLabel layerLabel;
    private javax.swing.JComboBox<String> materialDropdown;
    private javax.swing.JLabel materialLabel;
    private javax.swing.JLabel materialLabel1;
    private javax.swing.JPanel miscPanel;
    private ctrmap.creativestudio.editors.NameTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel primitiveType;
    private javax.swing.JLabel ptLabel;
    private javax.swing.JPanel renderingPanel;
    private javax.swing.JRadioButton rl1Btn;
    private javax.swing.JRadioButton rl2Btn;
    private javax.swing.JRadioButton rl3Btn;
    private javax.swing.JRadioButton rl4Btn;
    private javax.swing.ButtonGroup rlBtnGroup;
    private javax.swing.JLabel skinning;
    private javax.swing.JLabel skinningLabel;
    private javax.swing.JSpinner tgtCalcUVSet;
    private javax.swing.JLabel tgtCalcUVSetLabel;
    private javax.swing.JComboBox<String> visGroupDropdown;
    // End of variables declaration//GEN-END:variables
}

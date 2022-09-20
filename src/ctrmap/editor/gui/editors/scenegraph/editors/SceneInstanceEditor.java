package ctrmap.editor.gui.editors.scenegraph.editors;

import ctrmap.renderer.scenegraph.G3DResourceInstance;

public class SceneInstanceEditor extends javax.swing.JPanel implements IScenegraphEditor {

	private G3DResourceInstance instance;

	public SceneInstanceEditor() {
		initComponents();	
	}

	@Override
	public void load(Object o) {
		instance = null;
		if (o != null && o instanceof G3DResourceInstance) {
			G3DResourceInstance i = (G3DResourceInstance)o;
			parentMode.setSelectedIndex(i.parentMode.ordinal());
			pos.loadVec(i.getPosition());
			rot.loadVec(i.getRotation());
			sca.loadVec(i.getScale());
			if (i.resource != null && i.resource.models.size() == 1){
				name.setText(i.resource.models.get(0).name);
			}
			else {
				name.setText("N/A");
			}
			vtxCount.setText(i.getTotalVertexCountVBO() + " vertices, " + i.getTotalVertexCount() + " facepoints.");
			instance = i;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        edtPanel = new javax.swing.JPanel();
        parentModeLabel = new javax.swing.JLabel();
        parentMode = new javax.swing.JComboBox<>();
        posLabel = new javax.swing.JLabel();
        pos = new xstandard.gui.components.Vec3fEditor();
        rotLabel = new javax.swing.JLabel();
        rot = new xstandard.gui.components.Vec3fEditor();
        scaLabel = new javax.swing.JLabel();
        sca = new xstandard.gui.components.Vec3fEditor();
        vtxCountLabel = new javax.swing.JLabel();
        vtxCount = new javax.swing.JLabel();

        nameLabel.setText("Name:");

        name.setText("-");

        edtPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Instance"));

        parentModeLabel.setText("Inheritance mode:");

        parentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inherit all", "Inherit position", "Inherit position/rotation", "Skip parent" }));
        parentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentModeActionPerformed(evt);
            }
        });

        posLabel.setText("Position:");

        rotLabel.setText("Rotation:");

        scaLabel.setText("Scale:");

        javax.swing.GroupLayout edtPanelLayout = new javax.swing.GroupLayout(edtPanel);
        edtPanel.setLayout(edtPanelLayout);
        edtPanelLayout.setHorizontalGroup(
            edtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edtPanelLayout.createSequentialGroup()
                        .addComponent(parentModeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parentMode, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(posLabel)
                    .addComponent(pos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotLabel)
                    .addComponent(rot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scaLabel)
                    .addComponent(sca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        edtPanelLayout.setVerticalGroup(
            edtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentModeLabel)
                    .addComponent(parentMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(posLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vtxCountLabel.setForeground(new java.awt.Color(102, 102, 102));
        vtxCountLabel.setText("Total vertex count:");

        vtxCount.setForeground(new java.awt.Color(102, 102, 102));
        vtxCount.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vtxCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vtxCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vtxCountLabel)
                    .addComponent(vtxCount))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void parentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentModeActionPerformed
		if (instance != null){
			instance.parentMode = G3DResourceInstance.ParentMode.values()[parentMode.getSelectedIndex()];
		}
    }//GEN-LAST:event_parentModeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel edtPanel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> parentMode;
    private javax.swing.JLabel parentModeLabel;
    private xstandard.gui.components.Vec3fEditor pos;
    private javax.swing.JLabel posLabel;
    private xstandard.gui.components.Vec3fEditor rot;
    private javax.swing.JLabel rotLabel;
    private xstandard.gui.components.Vec3fEditor sca;
    private javax.swing.JLabel scaLabel;
    private javax.swing.JLabel vtxCount;
    private javax.swing.JLabel vtxCountLabel;
    // End of variables declaration//GEN-END:variables
}
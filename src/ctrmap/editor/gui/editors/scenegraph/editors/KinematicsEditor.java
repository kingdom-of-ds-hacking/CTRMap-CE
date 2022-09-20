package ctrmap.editor.gui.editors.scenegraph.editors;

import ctrmap.renderer.scene.animation.skeletal.KinematicsController;
import xstandard.gui.components.ComponentUtils;
import xstandard.gui.components.listeners.DocumentAdapterEx;
import javax.swing.event.DocumentEvent;

public class KinematicsEditor extends javax.swing.JPanel implements IScenegraphEditor {

	private KinematicsController ctrl;

	public KinematicsEditor() {
		initComponents();	
		
		kcJointName.getDocument().addDocumentListener(new DocumentAdapterEx() {
			@Override
			public void textChangedUpdate(DocumentEvent e) {
				if (ctrl != null){
					ctrl.targetJointName = ComponentUtils.getDocTextFromField(kcJointName);
				}
			}
		});
	}

	@Override
	public void load(Object o) {
		ctrl = null;
		if (o != null && o instanceof KinematicsController) {
			KinematicsController kc = (KinematicsController)o;
			kcJointName.setText(kc.targetJointName);
			tra.loadVec(kc.value);
			kcType.setSelectedIndex(kc.type.ordinal());
			callbackClass.setText(kc.callback == null ? "-" : kc.callback.getClass().getSimpleName());
			btnEnabled.setSelected(kc.enabled);
			
			ctrl = kc;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jntNameLabel = new javax.swing.JLabel();
        transformPanel = new javax.swing.JPanel();
        tra = new xstandard.gui.components.Vec3fEditor();
        traLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        kcType = new javax.swing.JComboBox<>();
        callbackLabel = new javax.swing.JLabel();
        callbackClass = new javax.swing.JLabel();
        btnEnabled = new javax.swing.JCheckBox();
        kcJointName = new javax.swing.JTextField();

        jntNameLabel.setText("Joint name:");

        transformPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Inverse Kinematics"));

        traLabel.setText("Translation:");

        typeLabel.setText("Type");

        kcType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Relative translation", "Absolute translation", "User callback" }));
        kcType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kcTypeActionPerformed(evt);
            }
        });

        callbackLabel.setText("Callback");

        callbackClass.setText("-");

        btnEnabled.setText("Enabled");
        btnEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnabledActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transformPanelLayout = new javax.swing.GroupLayout(transformPanel);
        transformPanel.setLayout(transformPanelLayout);
        transformPanelLayout.setHorizontalGroup(
            transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transformPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEnabled)
                    .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(traLabel)
                        .addComponent(tra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(transformPanelLayout.createSequentialGroup()
                            .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(callbackLabel)
                                .addComponent(typeLabel))
                            .addGap(18, 18, 18)
                            .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(kcType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(callbackClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        transformPanelLayout.setVerticalGroup(
            transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transformPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(kcType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(callbackLabel)
                    .addComponent(callbackClass))
                .addGap(18, 18, 18)
                .addComponent(traLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnEnabled)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transformPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jntNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kcJointName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jntNameLabel)
                    .addComponent(kcJointName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transformPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnabledActionPerformed
		if (ctrl != null){
			ctrl.enabled = btnEnabled.isSelected();
		}
    }//GEN-LAST:event_btnEnabledActionPerformed

    private void kcTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kcTypeActionPerformed
		if (ctrl != null){
			ctrl.type = KinematicsController.TransformType.values()[kcType.getSelectedIndex()];
		}
    }//GEN-LAST:event_kcTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox btnEnabled;
    private javax.swing.JLabel callbackClass;
    private javax.swing.JLabel callbackLabel;
    private javax.swing.JLabel jntNameLabel;
    private javax.swing.JTextField kcJointName;
    private javax.swing.JComboBox<String> kcType;
    private xstandard.gui.components.Vec3fEditor tra;
    private javax.swing.JLabel traLabel;
    private javax.swing.JPanel transformPanel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
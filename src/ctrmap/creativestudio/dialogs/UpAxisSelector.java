package ctrmap.creativestudio.dialogs;

import javax.swing.JFrame;

public class UpAxisSelector extends javax.swing.JDialog {

	public static final int Y_UP = 0;
	public static final int Z_UP = 1;
	
	/**
	 * Creates new form UpAxisSelector
	 */
	public UpAxisSelector(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setDescTest();
	}

	public void setDescTest(){
		String text = null;
		switch (axisDropdown.getSelectedIndex()){
			case Y_UP:
				text = "Used by Maya, SoftImage, Unity and most console games";
				break;
			case Z_UP:
				text = "Used by Blender, 3DS Max or Unreal Engine";
				break;
		}
		descText.setText(text);
	}
	
	public static int getUpAxisSelection(JFrame parent){
		UpAxisSelector sel = new UpAxisSelector(parent, true);
		sel.setLocationRelativeTo(parent);
		sel.setVisible(true);
		int selection = sel.axisDropdown.getSelectedIndex();
		sel.dispose();
		return selection;
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        instructionLabel = new javax.swing.JLabel();
        axisDropdown = new javax.swing.JComboBox<>();
        btnReturn = new javax.swing.JButton();
        descText = new javax.swing.JLabel();

        setTitle("Specify an up axis");
        setResizable(false);

        instructionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        instructionLabel.setText("<html><center>The up axis for this resource could not be detected automatically.<br/>Please specify the up axis before importing:<center></html>");

        axisDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Y up", "Z up" }));
        axisDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                axisDropdownActionPerformed(evt);
            }
        });

        btnReturn.setText("Confirm");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        descText.setForeground(new java.awt.Color(50, 50, 50));
        descText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(axisDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReturn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descText, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(instructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(axisDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descText, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReturn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
		setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void axisDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_axisDropdownActionPerformed
		setDescTest();
    }//GEN-LAST:event_axisDropdownActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> axisDropdown;
    private javax.swing.JButton btnReturn;
    private javax.swing.JLabel descText;
    private javax.swing.JLabel instructionLabel;
    // End of variables declaration//GEN-END:variables
}

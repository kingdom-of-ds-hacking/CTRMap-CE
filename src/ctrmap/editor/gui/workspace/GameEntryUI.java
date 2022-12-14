package ctrmap.editor.gui.workspace;

import ctrmap.editor.system.workspace.GameRegistryData;

public class GameEntryUI extends javax.swing.JPanel {

	public final String gamePath;
	private final ProjectManager man;
	
	public GameEntryUI(String gamePath, ProjectManager man) {
		initComponents();
		gamePath = GameRegistryData.normalizePath(gamePath);
		this.gamePath = gamePath;
		this.man = man;
		nameLabel.setText(gamePath);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        btnRemoveGame = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 5, 2, 5));
        setAlignmentY(0.0F);
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        nameLabel.setText("Game name");
        add(nameLabel, java.awt.BorderLayout.CENTER);

        btnRemoveGame.setText("Remove");
        btnRemoveGame.setMinimumSize(new java.awt.Dimension(71, 15));
        btnRemoveGame.setPreferredSize(new java.awt.Dimension(71, 15));
        btnRemoveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveGameActionPerformed(evt);
            }
        });
        add(btnRemoveGame, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveGameActionPerformed
		man.removeGameEntryAction(this);
    }//GEN-LAST:event_btnRemoveGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveGame;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}

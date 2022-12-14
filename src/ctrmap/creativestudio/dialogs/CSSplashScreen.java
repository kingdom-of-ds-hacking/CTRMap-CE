package ctrmap.creativestudio.dialogs;

import xstandard.gui.SwingWorkerUtils;
import java.awt.Color;

public class CSSplashScreen extends javax.swing.JWindow {

	/**
	 * Creates new form CSSplashScreen
	 */
	public CSSplashScreen() {
		initComponents();

		setLocationRelativeTo(null);
		setBackground(new Color(0, 0, 0, 0));
		setAlwaysOnTop(true);
	}

	@Override
	public void setVisible(boolean v) {
		if (getGraphicsConfiguration().isTranslucencyCapable()) {
			setOpacity(v ? 0f : 1f);
			if (v) {
				super.setVisible(v);
			}
			SwingWorkerUtils.executeJob((() -> {
				try {
					for (int i = v ? 1 : 99; v ? i <= 100 : i >= 0; i += v ? 1 : -1) {
						Thread.sleep(1);
						setOpacity(i / 100f);
					}
					if (!v) {
						super.setVisible(v);
					}
				} catch (InterruptedException ex) {

				}
			}));
		} else {
			super.setVisible(v);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ctrmap/resources/cs/cs_logo_ng.png"))); // NOI18N
        getContentPane().add(label, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables

}

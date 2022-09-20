package ctrmap.creativestudio.dialogs;

import ctrmap.renderer.scene.Camera;
import java.util.ArrayList;
import java.util.List;

public class CameraSelectionPanel extends javax.swing.JPanel {

	private List<Camera> cameras = new ArrayList<>();

	public CameraSelectionPanel() {
		initComponents();
	}

	public void loadCameras(List<Camera> cameras) {
		cameraBox.removeAllItems();
		if (cameras != null) {
			this.cameras = cameras;
			for (Camera m : cameras) {
				cameraBox.addItem(m.name);
			}
		}
	}

	public Camera getSelectedCamera() {
		int index = cameraBox.getSelectedIndex();
		if (index == -1 || index >= cameras.size()) {
			return null;
		}
		return cameras.get(index);
	}
	
	public boolean hasAnyCamera() {
		return cameraBox.getItemCount() > 0;
	}

	public void setCameraBoxEnabled(boolean value) {
		cameraBox.setEnabled(value);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cameraLabel = new javax.swing.JLabel();
        cameraBox = new javax.swing.JComboBox<>();

        cameraLabel.setText("Camera:");

        cameraBox.setMaximumRowCount(25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cameraLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cameraBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cameraBox)
            .addComponent(cameraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cameraBox;
    private javax.swing.JLabel cameraLabel;
    // End of variables declaration//GEN-END:variables
}
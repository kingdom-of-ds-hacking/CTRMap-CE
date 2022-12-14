package ctrmap.creativestudio.editors;

import ctrmap.creativestudio.ngcs.tree.CSNode;
import ctrmap.formats.generic.interchange.CMIFTextureFormat;
import ctrmap.renderer.scene.metadata.ReservedMetaData;
import ctrmap.renderer.scene.model.Model;
import ctrmap.renderer.scene.texturing.Material;
import ctrmap.renderer.scene.texturing.Texture;
import ctrmap.renderer.scene.texturing.TextureMapper;
import ctrmap.renderer.scene.texturing.formats.TextureFormat;
import xstandard.gui.DialogUtils;
import xstandard.gui.components.ComponentUtils;
import java.util.Objects;

public class TextureEditor extends javax.swing.JPanel implements IEditor {

	private static final int[] CMIF_TEXFMT_LOOKUP = new int[] {
		CMIFTextureFormat.AUTO.ordinal(),
		CMIFTextureFormat.FULL_COLOR.ordinal(),
		CMIFTextureFormat.REDUCED_COLOR.ordinal(),
		CMIFTextureFormat.COMPRESSED.ordinal()
	};
	
	/**
	 * Creates new form TextureForm
	 */
	public TextureEditor() {
		initComponents();

		saveCtrl.setCallback(this::save);
	}

	private CSNode node;
	private Texture texture;

	@Override
	public void handleObject(Object o) {
		if (IEditor.checkIsCompatibleNG(o, Texture.class)) {
			node = (CSNode) o;
			Texture tex = (Texture) node.getContent();
			texture = null;
			textureName.setText(tex.name);
			setTexFmtIdx(tex);
			btnTexAsLookup.setSelected(ReservedMetaData.isLUT(tex));
			texDim.setText(tex.width + "x" + tex.height);
			texture = tex;
		} else {
			node = null;
			texture = null;
			texDim.setText("--");
			ComponentUtils.clearComponents(textureName, textureFormat);
		}
		texturePreview.showTexture(texture);
	}

	private void setTexFmtIdx(Texture tex) {
		if (tex.metaData.hasValue(ReservedMetaData.DESIRED_TEX_FORMAT)) {
			int want = tex.metaData.getValue(ReservedMetaData.DESIRED_TEX_FORMAT).intValue();
			for (int i = 0; i < CMIF_TEXFMT_LOOKUP.length; i++) {
				if (CMIF_TEXFMT_LOOKUP[i] == want) {
					textureFormat.setSelectedIndex(i);
					break;
				}
			}
		} else {
			textureFormat.setSelectedIndex(0);
		}
	}

	@Override
	public void save() {
		if (texture != null) {
			String oldTextureName = texture.name;
			String n = textureName.getText();
			if (n.length() > 0) {
				texture.name = textureName.getText();
			}
			texture.metaData.putValue(ReservedMetaData.TEX_AS_LUT, btnTexAsLookup.isSelected() ? 1 : 0);
			node.updateThis();
			if (!texture.name.equals(oldTextureName)) {
				for (Model mdl : node.getCS().getModels()) {
					for (Material mat : mdl.materials) {
						for (TextureMapper tm : mat.textures) {
							if (Objects.equals(tm.textureName, oldTextureName)) {
								tm.textureName = texture.name;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        texturePreview = new ctrmap.util.gui.TexturePreview();
        texNameLabel = new javax.swing.JLabel();
        textureName = new javax.swing.JTextField();
        texFormatLabel = new javax.swing.JLabel();
        textureFormat = new javax.swing.JComboBox<>();
        saveCtrl = new ctrmap.creativestudio.editors.SaveControlPanel();
        btnTexAsLookup = new javax.swing.JCheckBox();
        texDimLabel = new javax.swing.JLabel();
        texDim = new javax.swing.JLabel();

        texturePreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        texNameLabel.setText("Texture name");

        texFormatLabel.setText("Desired format");

        textureFormat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automatic", "True Color", "High Color", "Compressed" }));
        textureFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textureFormatActionPerformed(evt);
            }
        });

        btnTexAsLookup.setText("Serialize as Lookup Table");

        texDimLabel.setText("Dimensions: ");

        texDim.setText("--");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texturePreview, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textureFormat, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(texNameLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(textureName, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texFormatLabel)
                                    .addComponent(btnTexAsLookup)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(texDimLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(texDim, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(saveCtrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(texturePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textureName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texFormatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textureFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTexAsLookup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texDimLabel)
                    .addComponent(texDim))
                .addGap(10, 10, 10)
                .addComponent(saveCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textureFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textureFormatActionPerformed
		if (texture != null) {
			if (texture.format.originFormat == TextureFormat.ETC1 || texture.format.originFormat == TextureFormat.ETC1A4) {
				if (!DialogUtils.showYesNoDialog(node.getCS(), "Warning", "This texture has cached data for a compressed format.\nChanging the texture format will delete this data. Is that OK?")) {
					setTexFmtIdx(texture);
					return;
				}
			}

			texture.metaData.putValue(ReservedMetaData.DESIRED_TEX_FORMAT, CMIF_TEXFMT_LOOKUP[textureFormat.getSelectedIndex()]);
		}
    }//GEN-LAST:event_textureFormatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox btnTexAsLookup;
    private ctrmap.creativestudio.editors.SaveControlPanel saveCtrl;
    private javax.swing.JLabel texDim;
    private javax.swing.JLabel texDimLabel;
    private javax.swing.JLabel texFormatLabel;
    private javax.swing.JLabel texNameLabel;
    private javax.swing.JComboBox<String> textureFormat;
    private javax.swing.JTextField textureName;
    private ctrmap.util.gui.TexturePreview texturePreview;
    // End of variables declaration//GEN-END:variables
}

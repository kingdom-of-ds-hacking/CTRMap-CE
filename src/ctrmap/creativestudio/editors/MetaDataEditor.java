package ctrmap.creativestudio.editors;

import ctrmap.renderer.scene.metadata.MetaData;
import ctrmap.renderer.scene.metadata.MetaDataValue;
import xstandard.gui.components.ComponentUtils;
import xstandard.gui.components.listeners.DocumentAdapterEx;
import xstandard.math.vec.Vec3f;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class MetaDataEditor extends javax.swing.JPanel {

	private DefaultListModel<String> elementListModel = new DefaultListModel<>();

	private boolean loaded = false;
	private MetaData metaData = new MetaData();

	/**
	 * Creates new form MetaDataEditor
	 */
	public MetaDataEditor() {
		initComponents();

		elementList.addListSelectionListener((ListSelectionEvent e) -> {
			loadValueElement();
		});

		valueField.getDocument().addDocumentListener(new DocumentAdapterEx() {
			@Override
			public void textChangedUpdate(DocumentEvent e) {
				if (loaded) {
					MetaDataValue v = getSelectedValue();
					if (v != null) {
						switch (v.getType()) {
							case FLOAT: {
								try {
									float f = Float.parseFloat(valueField.getText());
									v.setValue(elementList.getSelectedIndex(), f);
								} catch (NumberFormatException ex) {

								}
								break;
							}
							case INT: {
								try {
									int i = Integer.parseInt(valueField.getText());
									v.setValue(elementList.getSelectedIndex(), i);
								} catch (NumberFormatException ex) {

								}
								break;
							}
							case STRING:
								v.setValue(elementList.getSelectedIndex(), valueField.getText());
								break;
							case VEC3: {
								try {
									v.setValue(elementList.getSelectedIndex(), Vec3f.parseVec3f(valueField.getText()));
								} catch (NumberFormatException ex) {

								}
								break;
							}
						}
						int eidx = elementList.getSelectedIndex();

						if (eidx != -1) {
							elementListModel.setElementAt(v.stringValue(eidx), eidx);
						}
					}
				}
			}
		});
	}

	public void loadMetaData(MetaData md) {
		metaData = md;
		lastValueIdx = -1;
		if (metaData == null) {
			metaData = new MetaData();
		}
		loadValues();
		int idx = valueNames.getItemCount() - 1;
		valueNames.setSelectedIndex(idx);
	}

	public void loadValues() {
		loaded = false;
		valueNames.removeAllItems();
		for (MetaDataValue v : metaData.getValues()) {
			if (!v.getIsTransient()) {
				valueNames.addItem(v.getName());
			}
		}
		valueNames.setSelectedIndex(Math.min(valueNames.getItemCount() - 1, lastValueIdx));
		loadValue();
		loaded = true;
	}

	public void loadValue() {
		loaded = false;
		elementListModel.removeAllElements();

		MetaDataValue val = getSelectedValue();
		if (val != null && val.getType() != null) {
			List<Object> elems = val.getValues();

			for (Object e : elems) {
				elementListModel.addElement(String.valueOf(e));
			}
			valueType.setSelectedIndex(val.getType().ordinal());
			valueField.setText(null);
		}
		loaded = true;
	}

	private DefaultFormatterFactory floatFF = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.000")));
	private DefaultFormatterFactory intFF = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0")));
	private DefaultFormatterFactory stringFF = new DefaultFormatterFactory();

	public void loadValueElement() {
		loaded = false;
		int idx = elementList.getSelectedIndex();
		valueField.setText(null);
		if (idx >= 0) {
			MetaDataValue v = getSelectedValue();

			if (v != null) {
				switch (v.getType()) {
					case FLOAT:
						valueField.setFormatterFactory(floatFF);
						ComponentUtils.setNFValueClass(Float.class, valueField);
						valueField.setValue(v.floatValue(idx));
						break;
					case INT:
						valueField.setFormatterFactory(intFF);
						ComponentUtils.setNFValueClass(Integer.class, valueField);
						valueField.setValue(v.intValue(idx));
						break;
					case STRING:
					case VEC3:
						valueField.setFormatterFactory(stringFF);
						valueField.setText(v.stringValue(idx));
						break;
				}
			} else {
				valueField.setValue(null);
			}
		}
		loaded = true;
	}

	private MetaDataValue getSelectedValue() {
		int idx = valueNames.getSelectedIndex();
		if (idx >= 0) {
			return metaData.getValues().get(idx);
		}
		return null;
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this
	 * code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valueNames = new javax.swing.JComboBox<>();
        valueListSP = new javax.swing.JScrollPane();
        elementList = new javax.swing.JList<>();
        valueType = new javax.swing.JComboBox<>();
        valueTypeLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        valueField = new javax.swing.JFormattedTextField();
        btnAddMetaValue = new javax.swing.JButton();
        btnRemoveMetaValue = new javax.swing.JButton();
        btnAddValueElement = new javax.swing.JButton();
        btnRemoveValueElement = new javax.swing.JButton();

        valueNames.setEditable(true);
        valueNames.setMaximumRowCount(30);
        valueNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueNamesActionPerformed(evt);
            }
        });

        elementList.setModel(elementListModel);
        valueListSP.setViewportView(elementList);

        valueType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Float", "Integer", "Vector3", "String", "Raw" }));
        valueType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueTypeActionPerformed(evt);
            }
        });

        valueTypeLabel.setText("Value type");

        valueLabel.setText("Value");

        btnAddMetaValue.setText("+");
        btnAddMetaValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMetaValueActionPerformed(evt);
            }
        });

        btnRemoveMetaValue.setText("-");
        btnRemoveMetaValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveMetaValueActionPerformed(evt);
            }
        });

        btnAddValueElement.setText("+");
        btnAddValueElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddValueElementActionPerformed(evt);
            }
        });

        btnRemoveValueElement.setText("-");
        btnRemoveValueElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveValueElementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valueNames, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valueListSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valueLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(valueField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnAddMetaValue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnRemoveMetaValue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(valueTypeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valueType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddValueElement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoveValueElement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddMetaValue)
                    .addComponent(btnRemoveMetaValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(valueListSP, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddValueElement)
                    .addComponent(btnRemoveValueElement))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private int lastValueIdx = -1;

    private void valueNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueNamesActionPerformed
		if (loaded) {
			int idx = valueNames.getSelectedIndex();
			if (idx != lastValueIdx && idx != -1) {
				loadValue();
			}

			if (idx != -1) {
				lastValueIdx = idx;
			}

			if (lastValueIdx != -1) {
				String oldName = metaData.getValues().get(lastValueIdx).getName();
				String newName = ((String) valueNames.getEditor().getItem()).replace("\n", "");
				if (!newName.equals(oldName)) {
					metaData.getValues().get(lastValueIdx).setName(newName);
					loadValues();
				}
			}
		}
    }//GEN-LAST:event_valueNamesActionPerformed

    private void btnAddMetaValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMetaValueActionPerformed
		if (loaded) {
			Object val = (int) 0;
			switch (MetaDataValue.Type.values()[valueType.getSelectedIndex()]) {
				case FLOAT:
					val = 0f;
					break;
				case INT:
					val = (int) 0;
					break;
				case STRING:
					val = "Blank";
					break;
			}
			metaData.putValue("NewMetaDataValue", val);
			lastValueIdx = valueNames.getItemCount();
			loadValues();
		}
    }//GEN-LAST:event_btnAddMetaValueActionPerformed

    private void btnRemoveMetaValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveMetaValueActionPerformed
		if (loaded) {
			int idx = lastValueIdx;
			metaData.getValues().remove(idx);
			loadValues();
		}
    }//GEN-LAST:event_btnRemoveMetaValueActionPerformed

    private void valueTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueTypeActionPerformed
		MetaDataValue val = getSelectedValue();
		if (val != null) {
			MetaDataValue.Type type = MetaDataValue.Type.values()[valueType.getSelectedIndex()];
			if (type != val.getType()) {
				val.cast(type);
			}
		}
    }//GEN-LAST:event_valueTypeActionPerformed

    private void btnAddValueElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddValueElementActionPerformed
		MetaDataValue val = getSelectedValue();
		if (val != null) {
			val.appendDefaultValue();
			loadValue();
			elementList.setSelectedIndex(elementListModel.getSize() - 1);
			loadValueElement();
		}
    }//GEN-LAST:event_btnAddValueElementActionPerformed

    private void btnRemoveValueElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveValueElementActionPerformed
		MetaDataValue val = getSelectedValue();
		if (val != null) {
			val.getValues().remove(elementList.getSelectedIndex());
			int selIndex = elementList.getSelectedIndex();
			loadValue();
			elementList.setSelectedIndex(Math.min(elementListModel.getSize() - 1, selIndex));
			loadValueElement();
		}
    }//GEN-LAST:event_btnRemoveValueElementActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMetaValue;
    private javax.swing.JButton btnAddValueElement;
    private javax.swing.JButton btnRemoveMetaValue;
    private javax.swing.JButton btnRemoveValueElement;
    private javax.swing.JList<String> elementList;
    private javax.swing.JFormattedTextField valueField;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JScrollPane valueListSP;
    private javax.swing.JComboBox<String> valueNames;
    private javax.swing.JComboBox<String> valueType;
    private javax.swing.JLabel valueTypeLabel;
    // End of variables declaration//GEN-END:variables
}

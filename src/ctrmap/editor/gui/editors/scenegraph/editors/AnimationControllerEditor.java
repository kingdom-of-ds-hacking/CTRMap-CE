package ctrmap.editor.gui.editors.scenegraph.editors;

import ctrmap.renderer.scene.animation.AbstractAnimationController;
import xstandard.gui.components.ComponentUtils;
import xstandard.gui.components.listeners.DocumentAdapterEx;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;

public class AnimationControllerEditor extends javax.swing.JPanel implements IScenegraphEditor {

	private AbstractAnimationController ctrl;

	public AnimationControllerEditor() {
		initComponents();

		ComponentUtils.setNFValueClass(Float.class, animeSpeedMul);

		animeSpeedMul.getDocument().addDocumentListener(new DocumentAdapterEx() {
			@Override
			public void textChangedUpdate(DocumentEvent e) {
				if (ctrl != null) {
					ctrl.speedMultiplier = ComponentUtils.getFloatFromDocument(animeSpeedMul);
				}
			}
		});

		Thread frameUpdateThread = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(10);
						if (ctrl != null) {
							SwingUtilities.invokeLater((() -> {
								if (ctrl != null) {
									currentFrame.setText(String.valueOf((int)ctrl.frame));
									if (ctrl.anim != null) {
										frameCount.setText(String.valueOf((int)ctrl.anim.frameCount));
									}
								}
							}));
						}
					}
				} catch (InterruptedException ex) {
					Logger.getLogger(AnimationControllerEditor.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		};
		frameUpdateThread.setDaemon(true);
		frameUpdateThread.start();
	}

	@Override
	public void load(Object o) {
		ctrl = null;
		if (o != null && o instanceof AbstractAnimationController) {
			AbstractAnimationController ac = (AbstractAnimationController) o;
			animeSpeedMul.setValue(ac.speedMultiplier);
			animeCtrlType.setText(ac.getClass().getSimpleName());
			name.setText(ac.getName());
			ctrl = ac;
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
        animeControls = new javax.swing.JPanel();
        btnToggleAnime = new javax.swing.JButton();
        btnAdvanceAnimeFrame = new javax.swing.JButton();
        btnRestartAnime = new javax.swing.JButton();
        animeSpeedMulLabel = new javax.swing.JLabel();
        animeSpeedMul = new javax.swing.JFormattedTextField();
        animeInfo = new javax.swing.JPanel();
        currentFrameLabel = new javax.swing.JLabel();
        currentFrame = new javax.swing.JLabel();
        frameSeparator = new javax.swing.JLabel();
        frameCount = new javax.swing.JLabel();
        animeCtrlTypeLabel = new javax.swing.JLabel();
        animeCtrlType = new javax.swing.JLabel();

        nameLabel.setText("Name:");

        name.setText("-");

        animeControls.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));

        btnToggleAnime.setText("Play/pause");
        btnToggleAnime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleAnimeActionPerformed(evt);
            }
        });

        btnAdvanceAnimeFrame.setText("Next frame");
        btnAdvanceAnimeFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvanceAnimeFrameActionPerformed(evt);
            }
        });

        btnRestartAnime.setText("Restart");
        btnRestartAnime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartAnimeActionPerformed(evt);
            }
        });

        animeSpeedMulLabel.setText("Speed multiplier:");

        animeSpeedMul.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout animeControlsLayout = new javax.swing.GroupLayout(animeControls);
        animeControls.setLayout(animeControlsLayout);
        animeControlsLayout.setHorizontalGroup(
            animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animeControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(animeSpeedMulLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdvanceAnimeFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnToggleAnime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(animeControlsLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnRestartAnime))
                    .addGroup(animeControlsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(animeSpeedMul)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        animeControlsLayout.setVerticalGroup(
            animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animeControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnToggleAnime)
                    .addComponent(btnRestartAnime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdvanceAnimeFrame)
                .addGap(18, 18, 18)
                .addGroup(animeControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(animeSpeedMulLabel)
                    .addComponent(animeSpeedMul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        animeInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        currentFrameLabel.setText("Current frame:");

        currentFrame.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        currentFrame.setText("-");

        frameSeparator.setText("/");

        frameCount.setText("-");

        animeCtrlTypeLabel.setText("Type:");

        animeCtrlType.setText("-");

        javax.swing.GroupLayout animeInfoLayout = new javax.swing.GroupLayout(animeInfo);
        animeInfo.setLayout(animeInfoLayout);
        animeInfoLayout.setHorizontalGroup(
            animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animeInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentFrameLabel)
                    .addComponent(animeCtrlTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(animeInfoLayout.createSequentialGroup()
                        .addComponent(currentFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frameSeparator)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frameCount, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addComponent(animeCtrlType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        animeInfoLayout.setVerticalGroup(
            animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animeInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentFrameLabel)
                    .addComponent(currentFrame)
                    .addComponent(frameSeparator)
                    .addComponent(frameCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(animeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(animeCtrlTypeLabel)
                    .addComponent(animeCtrlType))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(animeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(animeControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(animeInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animeControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnToggleAnimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleAnimeActionPerformed
		if (ctrl != null) {
			ctrl.pauseOrUnpauseAnimation();
		}
    }//GEN-LAST:event_btnToggleAnimeActionPerformed

    private void btnRestartAnimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartAnimeActionPerformed
		if (ctrl != null){
			ctrl.restartAnimation();
		}
    }//GEN-LAST:event_btnRestartAnimeActionPerformed

    private void btnAdvanceAnimeFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvanceAnimeFrameActionPerformed
		if (ctrl != null){
			ctrl.frame++;
		}
    }//GEN-LAST:event_btnAdvanceAnimeFrameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animeControls;
    private javax.swing.JLabel animeCtrlType;
    private javax.swing.JLabel animeCtrlTypeLabel;
    private javax.swing.JPanel animeInfo;
    private javax.swing.JFormattedTextField animeSpeedMul;
    private javax.swing.JLabel animeSpeedMulLabel;
    private javax.swing.JButton btnAdvanceAnimeFrame;
    private javax.swing.JButton btnRestartAnime;
    private javax.swing.JButton btnToggleAnime;
    private javax.swing.JLabel currentFrame;
    private javax.swing.JLabel currentFrameLabel;
    private javax.swing.JLabel frameCount;
    private javax.swing.JLabel frameSeparator;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}

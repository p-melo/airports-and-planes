/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import classes.Aeroporto;
import edu.princeton.cs.algs4.RedBlackBST;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Modusaleatorios
 */
public class MapPanel extends javax.swing.JPanel
{

    /**
     * Creates new form MapPanel
     */
    public MapPanel()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(244, 249, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void placeAirportOnMap(RedBlackBST<String, Aeroporto> airportRBST)
    {
        for (String codeIter : airportRBST.keys())
        {
            bagAirportLabels.add(airportRBST.get(codeIter));
        }
        this.repaint();
    }
    
    public void placeEdgeOnMap(int[] line)
    {
        bagAirportLabels.addLines(line);
        this.repaint();
    }
    
    public void removeEdgesOnMap()
    {
        bagAirportLabels.emptyEdges();
        this.repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        
        Image offscreenImg = this.createImage(width, height);
      //  System.out.println("img = " + offscreenImg.getWidth(null) + " " + offscreenImg.getHeight(null));
        
        Graphics offscreenGraphics = offscreenImg.getGraphics();
        offscreenGraphics.setColor(this.getBackground());
        offscreenGraphics.fillRect(0, 0, width, height);
        bagAirportLabels.draw(offscreenGraphics);
        
        g.drawImage(offscreenImg, 0, 0, null);
        
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private BagMapAirportLabels bagAirportLabels = new BagMapAirportLabels();
}
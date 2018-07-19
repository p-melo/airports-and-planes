/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import classes.Aeroporto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Modusaleatorios
 */
public class BagMapAirportLabels
{
    private ArrayList<Aeroporto> alLabels = new ArrayList<>();
    private ArrayList<int[]> alEdges = new ArrayList<>();
    
    public BagMapAirportLabels() {}
    
    public void add(Aeroporto airport)
    {
        this.alLabels.add(airport);
    }
    
    public void addLines(int[] lineCoordinates)
    {
        this.alEdges.add(lineCoordinates);
    }
    
    public void draw(Graphics g)
    {
        for (Aeroporto airportIter : alLabels)
        {
            if (airportIter.getActiveStatus())
            {
                int x = (int)(180 + (airportIter.getMyCidade().getLongitude() * 1.2));
                int y = (int)(180 - (airportIter.getMyCidade().getLatitude() * 3.5));

                g.setColor(Color.BLACK);
                g.drawString(airportIter.getIdAeroporto(), (x * 2) - 40, y + 40);
                //g.drawString("0.0", (180 + (-8)) * 2, 180 - 41);
            }
        }
        
        for (int[] line : alEdges)
        {
            int y1 = (int)(180 - (line[0] * 3.5));
            int x1 = (int)(180 + (line[1] * 1.2));
            int y2 = (int)(180 - (line[2] * 3.5));
            int x2 = (int)(180 + (line[3] * 1.2));
            
            g.setColor(Color.RED);
            g.drawLine((x1 * 2) - 40, y1 + 40, (x2 * 2) - 40, y2 + 40);
        }
    }
    
    public void emptyEdges()
    {
        if (!alEdges.isEmpty())
        {
            this.alEdges.clear();
        }
    }
}

// Environment code for project SmortLamp.mas2j

import jason.asSyntax.*;

import jason.environment.*;

import jason.asSyntax.parser.*;



import java.util.logging.*;

import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;


public class SmortLamp extends Environment {
    public class SmortLampModel extends GridWorldModel{
        public SmortLampModel() {
            // Size of the map, num of agents to display
            super(12, 12, 1+3+4+1);

	    try{
		// junction controller
            	setAgPos(0, 4, 8);
		
		// car1
                setAgPos(1, 0, 6);
		//car2
		setAgPos(2, 5, 0);
		// car3
		setAgPos(3, 6,11);
				
		//lamp1
		setAgPos(4, 4,7);
		//lamp2
		setAgPos(5, 4,4);
		//lamp3
		setAgPos(6, 7,4);
		//lamp4
		setAgPos(7, 7,7);
		
		//police
		setAgPos(8,11,5);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
        }

	
    }

    public class CustomGridView extends GridWorldView
    {
        public CustomGridView(CustomGridModel model) {
            super(model, "SmortLamp", 600);
            setResizable(false);
            setVisible(true);
            repaint();
        }
        @Override
        public void draw(Graphics g, int x, int y, int object) {}

        @Override
        public void drawAgent(Graphics g, int x, int y, Color c, int id) {
            String label="A";
			/*switch(id){
				case 0: label="P";c = Color.black; break;
				case 1:label="C1"; c = Color.orange;
				break;
				case 2: label="C2"; c = Color.orange;
				break;
				case 3: label="C3"; c = Color.orange;
				break;
				case 4: label=" L";
					if( loff1){
						c= Color.yellow;
					}else if (l1){
						c = Color.green;
					}else{
						c = Color.red;
					}
					break;
				case 5: label=" L";
				if( loff2){
						c= Color.yellow;
					}else if (l2){
						c = Color.green;
					}else{
						c = Color.red;
					}
					break;
				case 6: label=" L";
				if( loff3){
						c= Color.yellow;
					}else if (l3){
						c = Color.green;
					}else{
						c = Color.red;
					}
					break;
				case 7: label=" L";
				if( loff4){
						c= Color.yellow;
					}else if (l4){
						c = Color.green;
					}else{
						c = Color.red;
					}
					break;
				case 8: label="*";c = Color.blue;break;

				default: label="X"; break;
			}*/

            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.white);
            super.drawString(g, x, y, defaultFont, label);
            repaint();
        }
    }


    public CustomGridModel model;
	public CustomGridView view;
	
    private Logger logger = Logger.getLogger("SmortLamp.mas2j."+SmortLamp.class.getName());



    /** Called before the MAS execution with the args informed in .mas2j */

    @Override
    public void init(String[] args) {

		logger.info("start");
        super.init(args);

		model = new CustomGridModel();
		view = new CustomGridView(model);
		model.setView(view);
        try {

			addPercept(ASSyntax.parseLiteral("percept(demo)"));

		} catch (ParseException e) {

			e.printStackTrace();

		}

    }



    @Override

    public boolean executeAction(String agName, Structure action) {

        logger.info("executing: "+action+", but not implemented!");

        if (true) { // you may improve this condition

             informAgsEnvironmentChanged();

        }

        return true; // the action was executed with success

    }



    /** Called before the end of MAS execution */

    @Override

    public void stop() {

        super.stop();

    }

}



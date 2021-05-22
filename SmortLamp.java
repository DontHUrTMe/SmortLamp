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
import java.util.Collection.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;

public class SmortLamp extends Environment {

    class Car{
        int[] xarr;
        int[] yarr;
        int i=0;
        int id;
        int x;
        int y;
        public Car(int _id,int _x,int _y)
        {
            i=0;
            id = _id;
            x = _x;
            y = _y;
            switch (id){
                case 1:
                    xarr = new int[]{0,1,2,3,4,5,6,6,6,6,6,6,0,1,2,3,4,5,5,5,5,5,5};
                    yarr = new int[]{6,6,6,6,6,6,5,4,3,2,1,0,6,6,6,6,6,5,4,3,2,1,0};
                    break;
                case 2:
                    xarr = new int[]{5,5,5,5,5,5,6,7,8,9,10,11,0,1,2,3,4,5,6,6,6,6,6};
                    yarr = new int[]{0,1,2,3,4,5,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9,10,11};
                    break;
                case 3:
                    xarr = new int[]{6,6,6,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,4,3,2,1,0};
                    yarr = new int[]{11,10,9,8,7,6,5,4,3,2,1,0,0,1,2,3,4,5,5,5,5,5,5};
                    break;
                case 0:
                    xarr = new int[]{11,10,9,8,7,6,6,6,6,6,6,6,6,6,6,6,6,5,4,3,2,1,0};
                    yarr = new int[]{5,5,5,5,5,5,4,3,2,1,0,11,10,9,8,7,6,5,5,5,5,5,5};
                    break;
            }
        }

        public void Draw()
        {
            try{
                model.setAgPos(id,x,y);
                view.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void Update()
        {
            i++;
            if(i==xarr.length)
            {
                i=0;
            }
            x = xarr[i];
            y = yarr[i];
        }

        public void CheckAtLamp()
        {
            if(x==3&&y==6)
            {
                addPercept("lamp0",Literal.parseLiteral("car"));
                addPercept("car"+id,Literal.parseLiteral("lamp0"));
            }else if(x==5&&y==3)
            {
                addPercept("lamp1",Literal.parseLiteral("car"));
                addPercept("car"+id,Literal.parseLiteral("lamp1"));
            }else if(x==8&&y==5)
            {
                addPercept("lamp2",Literal.parseLiteral("car"));
                addPercept("car"+id,Literal.parseLiteral("lamp2"));
            }else if(x==6&&y==8)
            {
                addPercept("lamp3",Literal.parseLiteral("car"));
                addPercept("car"+id,Literal.parseLiteral("lamp3"));
            }else if(x==4&&y==6)
            {
                //removePercept("lamp0",Literal.parseLiteral("car"));
                removePercept("car"+id,Literal.parseLiteral("lamp0"));
            }else if(x==5&&y==4)
            {
                //removePercept("lamp1",Literal.parseLiteral("car"));
                removePercept("car"+id,Literal.parseLiteral("lamp1"));
            }else if(x==7&&y==5)
            {
                //removePercept("lamp2",Literal.parseLiteral("car"));
                removePercept("car"+id,Literal.parseLiteral("lamp2"));
            }else if(x==6&&y==7)
            {
                //removePercept("lamp3",Literal.parseLiteral("car"));
                removePercept("car"+id,Literal.parseLiteral("lamp3"));
            }
        }

        public void UpdateLamp()
        {
            if (y==6&&x>=2&&4>=x)
            {
                waitl0++;
            } else if (x==5&&y>=2&&4>=y)
            {
                waitl1++;
            } else if(y==5&&x>=7&&9>=x)
            {
                waitl2++;
            }else if(x==6&&y>=7&&9>=y)
            {
                waitl3++;
            }
        }

        public void Move()
        {
            addPercept("lamp0",Literal.parseLiteral("carNum(0)"));
            Draw();
            CheckAtLamp();
            Update();
        }
    }

    int waitl0=0;
    int waitl1=0;
    int waitl2=0;
    int waitl3=0;
    Car car1,car2,car3,police;

    boolean l1 = false;
    boolean l2 = false;
    boolean l3 = false;
    boolean l4 = false;
    public class CustomGridModel extends GridWorldModel {
        public CustomGridModel() {
            // Size of the map, num of agents to display
            super(12, 12, 1 + 3 + 4 + 1);
            car1 = new Car(1,0,6);
            car2 = new Car(2,5,0);
            car3 = new Car(3,6,11);
            police = new Car(0,11,5);
            try {
                // junction controller
                //setAgPos(0, 4, 8);

                // car1
                setAgPos(1, 0, 6);
                //car2
                setAgPos(2, 5, 0);
                // car3
                setAgPos(3, 6, 11);

                //lamp1
                setAgPos(4, 4, 7);
                //lamp2
                setAgPos(5, 4, 4);
                //lamp3
                setAgPos(6, 7, 4);
                //lamp4
                setAgPos(7, 7, 7);

                //police
                setAgPos(0, 11, 5);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public class CustomGridView extends GridWorldView {
        public CustomGridView(CustomGridModel model) {
            super(model, "SmortLamp", 600);
            setResizable(false);
            setVisible(true);
            repaint();
        }

        @Override
        public void draw(Graphics g, int x, int y, int object) {
        }

        @Override
        public void drawAgent(Graphics g, int x, int y, Color c, int id) {
            String label = "A";
            switch (id) {
                case 0:
                    label = "Police";
                    c = Color.blue;
                    break;
               case 1:
                    label = "C1";
                    c = Color.orange;
                    break;
                case 2:
                    label = "C2";
                    c = Color.orange;
                    break;
                case 3:
                    label = "C3";
                    c = Color.orange;
                    break;
                case 4:
                    label = " L";
                    if (l1) {
                        c = Color.green;
                    } else {
                        c = Color.red;
                    }
                    break;
                case 5:
                    label = " L";
                    if (l2) {
                        c = Color.green;
                    } else {
                        c = Color.red;
                    }
                    break;
                case 6:
                    label = " L";
                    if (l3) {
                        c = Color.green;
                    } else {
                        c = Color.red;
                    }
                    break;
                case 7:
                    label = " L";
                    if (l4) {
                        c = Color.green;
                    } else {
                        c = Color.red;
                    }
                    break;

                default:
                    label = "X";
                    break;
            }

            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.white);
            super.drawString(g, x, y, defaultFont, label);
            repaint();
        }
    }


    public CustomGridModel model;
    public CustomGridView view;

    private Logger logger = Logger.getLogger("SmortLamp.mas2j." + SmortLamp.class.getName());


    /**
     * Called before the MAS execution with the args informed in .mas2j
     */

    @Override
    public void init(String[] args) {

        logger.info("start");
        super.init(args);

        model = new CustomGridModel();
        view = new CustomGridView(model);
        model.setView(view);
        //addPercept("junction",Literal.parseLiteral("asd"));


    }


    @Override

    public boolean executeAction(String agName, Structure action) {

        logger.info("action: " + action + ", ag: " + agName);
        if (action.equals(Literal.parseLiteral("move")))
        {
            switch (agName){
                case "car1":
                    car1.Move();
                    break;
                case "car2":
                    car2.Move();
                    break;
                case "car3":
                    car3.Move();
                    break;
                case "police":
                    police.Move();
                    break;
            }
        }else if (action.equals(Literal.parseLiteral("red"))){
            switch (agName){
                case "lamp0":
                    l1 = false;
                    break;
                case "lamp1":
                    l2 = false;
                    break;
                case "lamp2":
                    l3 = false;
                    break;
                case "lamp3":
                    l4 = false;
                    break;
            }
        }else if (action.equals(Literal.parseLiteral("green"))){
            switch (agName){
                case "lamp0":
                    l1 = true;
                    break;
                case "lamp1":
                    l2 = true;
                    break;
                case "lamp2":
                    l3 = true;
                    break;
                case "lamp3":
                    l4 = true;
                    break;
            }
        }else if(action.equals(Literal.parseLiteral("new")))
        {
            waitl0=0;
            waitl1=0;
            waitl2=0;
            waitl3=0;
            car1.UpdateLamp();
            car2.UpdateLamp();
            car3.UpdateLamp();
            clearPercepts("lamp0");
            clearPercepts("lamp1");
            clearPercepts("lamp2");
            clearPercepts("lamp3");
            addPercept("lamp0",Literal.parseLiteral("carNum("+waitl0+")"));
            addPercept("lamp1",Literal.parseLiteral("carNum("+waitl1+")"));
            addPercept("lamp2",Literal.parseLiteral("carNum("+waitl2+")"));
            addPercept("lamp3",Literal.parseLiteral("carNum("+waitl3+")"));
            logger.info("lamp0: "+waitl0);
            logger.info("lamp1: "+waitl1);
            logger.info("lamp2: "+waitl2);
            logger.info("lamp3: "+waitl3);
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        informAgsEnvironmentChanged();

        return true; // the action was executed with success

    }


    /**
     * Called before the end of MAS execution
     */

    @Override

    public void stop() {

        super.stop();

    }

}



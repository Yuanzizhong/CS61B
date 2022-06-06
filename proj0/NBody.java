public class NBody {
    /** Given a file name, returns a radius of the planet.  */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int number = in.readInt();
        double planetRadius = in.readDouble();
        return planetRadius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int planetNum = in.readInt();
        double Radius = in.readDouble();
        Planet[] planetInfo = new Planet[planetNum];
        for(int i = 0; i < planetNum; i++)
        {
            double xP = in.readDouble(), yP = in.readDouble();
            double xV = in.readDouble(), yV = in.readDouble();
            double m = in.readDouble();
            String fileName = in.readString();
            planetInfo[i] = new Planet(xP, yP, xV, yV, m, fileName);
        }
        return planetInfo;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        In in = new In(filename);
        int numberPlanet = in.readInt();
        double R = readRadius(filename);

        Planet[] planetInfo = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        StdDraw.show();

        for(int i = 0; i < numberPlanet; i++) {
            planetInfo[i].draw();
        }
        StdDraw.show();

        double[] xForces = new double[numberPlanet];
        double[] yForces = new double[numberPlanet];
        double nowT = 0.0;

        while(nowT < T) {
            for(int i = 0; i < numberPlanet; i++) {
                xForces[i] = planetInfo[i].calcNetForceExertedByX(planetInfo);
                yForces[i] = planetInfo[i].calcNetForceExertedByY(planetInfo);
                planetInfo[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-R, R);
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for(int j = 0; j < numberPlanet; j++) {
                planetInfo[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            nowT += dt;
        }
//        System.out.println(R);
    }
}

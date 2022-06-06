public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos)
                +  (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanet) {
        double sumPlanetX = 0;
        if (allPlanet == null) {
            return 0;
        }
        int planetLen = allPlanet.length, i = 0;
        while (i < planetLen) {
            if (this.equals(allPlanet[i])) {
                i++;
                continue;
            }
            sumPlanetX += this.calcForceExertedByX(allPlanet[i]);
            i++;
        }
        return sumPlanetX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet) {
        double sumPlanetY = 0.0;
        if (allPlanet == null) {
            return 0.0;
        }
        int planetLen = allPlanet.length, i = 0;
        while (i < planetLen) {
            if (this.equals(allPlanet[i])) {
                i++;
                continue;
            }
            sumPlanetY += this.calcForceExertedByY(allPlanet[i]);
            i++;
        }
        return sumPlanetY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = 0, aY = 0;
        aX = fX / this.mass;
        aY = fY / this.mass;

        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;

        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}

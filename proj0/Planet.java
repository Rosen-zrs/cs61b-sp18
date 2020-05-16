public class Planet {

    private static double G = 6.67e-11;
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet p1){
        double dx2 = (this.xxPos - p1.xxPos) * (this.xxPos - p1.xxPos);
        double dy2 = (this.yyPos - p1.yyPos) * (this.yyPos - p1.yyPos);
        return Math.sqrt(dx2 + dy2);
    }

    public double calcForceExertedBy(Planet p1){
        double r = calcDistance(p1);
        return G* this.mass * p1.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p1){
        double r = calcDistance(p1);
        if(r == 0){
            return 0;
        }
        else{
            return calcForceExertedBy(p1) * (p1.xxPos - this.xxPos) / r;
        }
    }

    public double calcForceExertedByY(Planet p1){
        double r = calcDistance(p1);
        if(r == 0){
            return 0;
        }
        else{
            return calcForceExertedBy(p1) * (p1.yyPos - this.yyPos) / r;
        }
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double Fxn = 0;
        for(Planet p: planets){
            if(this.equals(p))
            {
                continue;
            }
            Fxn += calcForceExertedByX(p);
        }
        return Fxn;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double Fyn = 0;
        for(Planet p: planets){
            if(this.equals(p))
            {
                continue;
            }
            Fyn += calcForceExertedByY(p);
        }
        return Fyn;
    }

    public void update(double dt, double fx, double fy){
        double ax, ay;
        ax = fx / this.mass;
        ay = fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
    }
}
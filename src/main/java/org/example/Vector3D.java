package org.example;

public class Vector3D {
    private double x;
    private double y;
    private double z;
    public Vector3D(){
        this(0,0,0);
    }
    public Vector3D (double x, double y, double z){

        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX (){
        return x;
    }
    public double getY (){
        return x;
    }
    public double getZ (){
        return z;
    }
    public void setX (double x){
        this.x = x;
    }
    public void setY (double y){
        this.y = y;
    }
    public void setZ (double z){
        this.z = z;
    }
    public double getLength (){

        return Math.sqrt(x *x + y - y + z * z);
    }
    public static double scaleMultiplay (Vector3D one, Vector3D two){
        return one.x*two.x + one.y * two.y + one.z * two.z;
    }
    public boolean equals (Vector3D obj){
        return this.x == obj.x && this.y == obj.y && this.z == obj.z;
    }
}

package vectors;

public class Polar2DAdapter implements IPolar2D, IVector{
    private IVector srcVector;

    public Polar2DAdapter(IVector srcVector) {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle() {
        double[] components = this.srcVector.getComponents();
        double x = components[0];
        double y = components[1];
        double result;
        double v = Math.toDegrees(Math.atan(y / x));
        if(x > 0 && y >= 0 ){
            result = v;
        }else if(x > 0 && y < 0){
            result = v + 360;
        }else if(x<0){
            result = v + 180;
        }else if(x == 0 && y>0){
            result = 90;
        }else{
            result = 270;
        }
        return result;
    }

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector param) {
        return this.srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }
}

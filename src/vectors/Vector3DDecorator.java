package vectors;

public class Vector3DDecorator implements IVector  {
    private IVector srcVector;
    private double z;

    public Vector3DDecorator(IVector srcVector, double z) {
        this.srcVector = srcVector;
        this.z = z;
    }

    @Override
    public double abs() {
        double[] comp = this.getComponents();
        return Math.sqrt(comp[0]*comp[0] + comp[1]*comp[1] + comp[2]*comp[2]);
    }

    @Override
    public double cdot(IVector param) {
        return this.srcVector.cdot(param) + this.getComponents()[2]*this.z;
    }

    @Override
    public double[] getComponents() {
        double[] comp = this.srcVector.getComponents();
        return new double[]{comp[0], comp[1], this.z};
    }

    public Vector3DDecorator cross(IVector param){
        double[] a = this.getComponents();
        double[] b = param.getComponents();
        double x_res = a[1]*b[2] - a[2]*b[1];
        double y_res = a[0]*b[2] - a[2]*b[0];
        double z_res = a[0]*b[1] - a[1]*b[0];
        IVector vector2D = new Vector2D(x_res, y_res);
        return new Vector3DDecorator(vector2D,z_res);
    }

    public IVector getSrcV(){
        return this.srcVector;
    }
}

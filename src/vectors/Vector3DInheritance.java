package vectors;

public class Vector3DInheritance extends Vector2D{
    private double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public double abs() {
        double[] comp = this.getComponents();
        return Math.sqrt(comp[0]*comp[0] + comp[1]*comp[1] + comp[2]*comp[2]);
    }

    @Override
    public double cdot(IVector param) {
        return super.cdot(param) + this.getComponents()[2]*this.z;
    }

    @Override
    public double[] getComponents() {
        double[] comp = super.getComponents();
        return new double[]{comp[0], comp[1], this.z};
    }

    public Vector3DInheritance cross(IVector param){
        double[] a = this.getComponents();
        double[] b = param.getComponents();
        double x_res = a[1]*b[2] - a[2]*b[1];
        double y_res = a[0]*b[2] - a[2]*b[0];
        double z_res = a[0]*b[1] - a[1]*b[0];
        return new Vector3DInheritance(x_res, y_res,z_res);
    }

    public IVector getSrcV(){
        double[] comp = this.getComponents();
        return new Vector2D(comp[0], comp[1]);
    }
}

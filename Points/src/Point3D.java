
public class Point3D extends Point implements Comparable<Point3D> {

	private int z;
	
	public Point3D() {
		super();
		this.z = 0;
	}
	
	public Point3D(int x, int y, int z){
		super(x, y);
		this.z = z;
	}
	
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		this.z = 0;
	}
	
	public void setLocation(int x, int y, int z){
		super.setLocation(x, y);
		this.z = z;
	}
	
	public int getZ(){
		return z;
	}
	
	public String toString(){
		return "(" + super.getX() + ", " + super.getY() + ", " + getZ() + ")";
	}
	
	public double distanceFromOrigin(){
		return Math.sqrt(Math.pow(super.getX(), 2) + Math.pow(super.getY(), 2) + Math.pow(getZ(), 2));
	}
}

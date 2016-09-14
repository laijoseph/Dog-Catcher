package a3;

import java.util.Vector;

public interface ICollider {

	public boolean collidesWith(ICollider otherObject);

	public void handleCollision(ICollider otherObject);

	Vector<ICollider> getCrashVector();

	public void crashVectorDel(ICollider collide);
}

package is.ru.tgra.objects;

/**
 * <h1>CollidableObject</h1>
 * <h2>is.ru.tgra.objects</h2>
 * <p>All object which want to be able to be collided with need to implement
 * this interface and calculate themselves if a collision has happened.</p>
 * Created on 5.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface CollidableObject extends ObjectReference {
    /**
     * This function receives an object which wants to check if a collision
     * has happened and this function returns true if the collision occurred.
     * The object needs to implement its own collision response.
     * @param or The object reference to check.
     * @param deltaTime
     * @return Returns true if a collision happens, false otherwise.
     */
    public void collision(ObjectReference or, float deltaTime);
}

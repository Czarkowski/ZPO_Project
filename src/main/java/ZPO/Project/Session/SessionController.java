package ZPO.Project.Session;

import jakarta.servlet.http.HttpSession;

public class SessionController {
    public static final String cartName = "cartName";
    public static <T> T SafeGetObject(HttpSession session, String name){
        Object myObject = session.getAttribute(name);

        if (myObject == null) {
            myObject = objFactory(name);
            session.setAttribute(name, myObject);
            System.out.println("add attribute: " + name);
        }
        return (T)myObject;
    }

    public static <T> void SafeSetObject(HttpSession session, String name, T object){
        session.setAttribute(name, object);
    }

    private static Object objFactory(String name){
        Object obj = null;
        switch (name){
            case cartName -> obj = new ShoppingCart();
        }
        return obj;
    }
}

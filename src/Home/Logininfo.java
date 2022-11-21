
package Home;

import java.util.HashMap;

public class Logininfo {
    HashMap<String, String> info = new HashMap<String, String>();
    
    Logininfo(){
        info.put("admin","admin");
        info.put("Alex","contra");
        
    }
    public HashMap getLoginInfo(){
        return info;
    }
}

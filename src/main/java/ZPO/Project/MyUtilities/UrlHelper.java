package ZPO.Project.MyUtilities;

import ZPO.Project.Routing.StaticRoutesName;

public class UrlHelper {

    public static String GetUrlOrderFinalize(Long zamId, boolean b) {
        String url = StaticRoutesName.GetFinalizeURL(zamId);
        return b ? url + StaticRoutesName.GOOD : url + StaticRoutesName.BAD;
    }
}
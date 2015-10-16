// -*- Java -*-
/*!
 * @file RangeDataViewer.java
 * @date $Date$
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;

/*!
 * @class RangeDataViewer
 * @brief Range Data Viewer Component
 */
public class RangeDataViewer implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "RangeDataViewer",
    	    "type_name",         "RangeDataViewer",
    	    "description",       "Range Data Viewer Component",
    	    "version",           "1.0.0",
    	    "vendor",            "Sugar Sweet Robotics",
    	    "category",          "Sensor",
    	    "activity_type",     "STATIC",
    	    "max_instance",      "1",
    	    "language",          "Java",
    	    "lang_type",         "compile",
            // Configuration variables
            "conf.default.debug", "1",
            // Widget
            "conf.__widget__.debug", "text",
            // Constraints
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new RangeDataViewerImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new RangeDataViewer(), new RangeDataViewer());
    }
}

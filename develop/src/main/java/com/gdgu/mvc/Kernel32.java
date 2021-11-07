package com.gdgu.mvc;

import com.sun.jna.*;
import com.sun.jna.win32.*;

public interface Kernel32 extends StdCallLibrary {
   public static class SYSTEM_POWER_STATUS extends Structure {
        public byte ACLineStatus;
        public byte BatteryFlag;
        public byte BatteryLifePercent;
        public byte Reserved1;
        public int BatteryLifeTime;
        public int BatteryFullLifeTime;
   }

   public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
}
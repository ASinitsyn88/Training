package head.first;

import head.first.equipment.CeilingFan;
import head.first.equipment.Light;
import head.first.equipment.Stereo;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo stereo = new Stereo("Living Room");
        CeilingFan ceilingFan = new CeilingFan("Living Room");

        remoteControl.setCommand(0, livingRoomLight::on, livingRoomLight::off);
        remoteControl.setCommand(1, kitchenLight::on, kitchenLight::off);
        remoteControl.setCommand(2, stereo::on, stereo::off);
        remoteControl.setCommand(3, ceilingFan::high, ceilingFan::off);

        System.out.println(remoteControl);

        // Макрокоманды - содержат список команд
//        Command[] party1 = {livingRoomLightOn, livingRoomLightOff};
//        Command[] party2 = {kitchenLightOn, kitchenLightOff};
//        MacroCommand macro1 = new MacroCommand(party1);
//        MacroCommand macro2 = new MacroCommand(party2);
//        remoteControl.setCommand(0, macro1, macro2);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.undoButtonWasPushed();
    }
}
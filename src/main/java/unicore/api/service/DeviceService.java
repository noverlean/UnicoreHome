package unicore.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.dto.devices.lightning.SyncLightning;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.entities.Device;
import unicore.api.entities.Environment;
import unicore.api.entities.User;

import java.text.ParseException;
import java.util.List;

public interface DeviceService {
    List<Environment> findByName(String name);

    @Transactional
    ResponseEntity<User> addDevice(String email, String deviceName, String deviceType, String deviceColor, String deviceIP);

    @Transactional
    ResponseEntity<User> removeDevice(String email, String deviceName);

    ResponseEntity<User> setLightning(String email, LightningDto lightningDto) throws ParseException;

    ResponseEntity<SyncLightning> synchronizeLightning_LOOP(SyncLightning syncLightning) throws ParseException;

    ResponseEntity<User> setSwitch(String email, SwitchDto switchDto) throws ParseException;

    ResponseEntity<SyncSwitch> synchronizeSwitch_LOOP(SyncSwitch syncSwitch) throws ParseException;

    ResponseEntity<Device> getDevice(String email, String deviceName, String accessCode);
}

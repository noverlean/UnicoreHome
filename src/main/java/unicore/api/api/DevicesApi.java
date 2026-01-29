package unicore.api.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unicore.api.dto.EmailDto;
import unicore.api.dto.devices.AddDeviceDto;
import unicore.api.dto.devices.GetDeviceDto;
import unicore.api.dto.devices.RemoveDeviceDto;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.dto.devices.lightning.SyncLightning;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.dto.environments.AddExitEnvironmentRequest;
import unicore.api.dto.environments.ConfirmAccessDto;
import unicore.api.dto.environments.CreateEnvironment;
import unicore.api.entities.Device;
import unicore.api.entities.User;

import java.security.Principal;
import java.text.ParseException;

@CrossOrigin(origins = "*")
public interface DevicesApi {
    @PostMapping("/environment/create")
    ResponseEntity<User> createEnvironment(@RequestBody CreateEnvironment createRequest, Principal principal);

    @PostMapping("/environment/add")
    ResponseEntity<EmailDto> addToEnvironmentRequest(@RequestBody AddExitEnvironmentRequest addRequest, Principal principal);

    @PostMapping("/environment/exit")
    ResponseEntity<User> exitFromEnvironment(@RequestBody AddExitEnvironmentRequest exitRequest, Principal principal);

    @PostMapping("/environment/add/confirm")
    ResponseEntity<User> addToEnvironmentConfirmation(@RequestBody ConfirmAccessDto confirmAccessDto, Principal principal);

    @PostMapping("/device/add")
    ResponseEntity<User> addDevice(@RequestBody AddDeviceDto addDeviceDto, Principal principal);

    @PostMapping("/device/remove")
    ResponseEntity<User> removeDevice(@RequestBody RemoveDeviceDto removeDeviceDto, Principal principal);

    @PostMapping("/device")
    ResponseEntity<Device> getDevice(@RequestBody GetDeviceDto getDeviceDto);

    @PostMapping("/device/lightning/set")
    ResponseEntity<User> setLightning(@RequestBody LightningDto lightningDto, Principal principal) throws ParseException;

    @PostMapping("/device/lightning/sync")
    ResponseEntity<SyncLightning> synchronizeLightning_LOOP(@RequestBody SyncLightning syncLightning) throws ParseException;

    @PostMapping("/device/switch/set")
    ResponseEntity<User> setSwitch(@RequestBody SwitchDto switchDto, Principal principal) throws ParseException;

    @PostMapping("/device/switch/sync")
    ResponseEntity<SyncSwitch> synchronizeSwitch_LOOP(@RequestBody SyncSwitch syncSwitch) throws ParseException;
}

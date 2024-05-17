package unicore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.dto.EmailDto;
import unicore.api.dto.devices.AddDeviceDto;
import unicore.api.dto.devices.GetDeviceDto;
import unicore.api.dto.devices.RemoveDeviceDto;
import unicore.api.dto.devices.SetIPDto;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.dto.devices.lightning.SyncLightning;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.dto.environments.AddExitEnvironmentRequest;
import unicore.api.dto.environments.ConfirmAccessDto;
import unicore.api.dto.environments.CreateEnvironment;
import unicore.api.entities.Device;
import unicore.api.entities.User;
import unicore.api.service.DeviceService;
import unicore.api.service.EnvironmentService;

import java.security.Principal;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DevicesController {
    private final EnvironmentService environmentService;
    private final DeviceService deviceService;

    @PostMapping("/environment/create")
    public ResponseEntity<User> createEnvironment(@RequestBody CreateEnvironment createRequest, Principal principal) {
        System.out.println(createRequest);
        return environmentService.createNewEnvironment(principal.getName(), createRequest.getEnvironment_name());
    }

    @PostMapping("/environment/add")
    public ResponseEntity<EmailDto> addToEnvironmentRequest(@RequestBody AddExitEnvironmentRequest addRequest, Principal principal) {
        System.out.println(addRequest);
        return environmentService.addToEnvironmentRequest(principal.getName(), addRequest.getEnvironment_name(), addRequest.getEnvironment_email());
    }

    @PostMapping("/environment/exit")
    public ResponseEntity<User> exitFromEnvironment(@RequestBody AddExitEnvironmentRequest exitRequest, Principal principal) {
        System.out.println(exitRequest);
        return environmentService.exitFromEnvironmentRequest(principal.getName(), exitRequest.getEnvironment_name(), exitRequest.getEnvironment_email());
    }

    @PostMapping("/environment/add/confirm")
    public ResponseEntity<User> addToEnvironmentConfirmation(@RequestBody ConfirmAccessDto confirmAccessDto, Principal principal) {
        System.out.println(confirmAccessDto);
        return environmentService.addToEnvironmentConfirm(principal.getName(), confirmAccessDto.getEmail(), confirmAccessDto.getName(), confirmAccessDto.getCode());
    }

    @PostMapping("/device/add")
    public ResponseEntity<User> addDevice(@RequestBody AddDeviceDto addDeviceDto, Principal principal) {
        System.out.println(addDeviceDto);
        return deviceService.addDevice(principal.getName(), addDeviceDto.getDevice_name(), addDeviceDto.getDevice_type(), addDeviceDto.getDevice_color(), addDeviceDto.getIp());
    }

    @PostMapping("/device/remove")
    public ResponseEntity<User> removeDevice(@RequestBody RemoveDeviceDto removeDeviceDto, Principal principal) {
        System.out.println(removeDeviceDto);
        return deviceService.removeDevice(principal.getName(), removeDeviceDto.getDevice_name());
    }

//    @PostMapping("/device/ip")
//    public ResponseEntity<User> setIP(@RequestBody SetIPDto setIPDto, Principal principal) {
//        System.out.println(setIPDto);
//        return deviceService.setIP(principal.getName(), setIPDto.getDevice_name(), setIPDto.getIp());
//    }

    @PostMapping("/device")
    public ResponseEntity<Device> getDevice(@RequestBody GetDeviceDto getDeviceDto) {
        System.out.println(getDeviceDto);
        return deviceService.getDevice(getDeviceDto.getEmail(), getDeviceDto.getDevice_name(), getDeviceDto.getAccess_code());
    }

    @PostMapping("/device/lightning/set")
    public ResponseEntity<User> setLightning(@RequestBody LightningDto lightningDto, Principal principal) throws ParseException {
        System.out.println(lightningDto);
        return deviceService.setLightning(principal.getName(), lightningDto);
    }

    @PostMapping("/device/lightning/sync")
    public ResponseEntity<SyncLightning> synchronizeLightning_LOOP(@RequestBody SyncLightning syncLightning) throws ParseException {
        System.out.println(syncLightning);
        return deviceService.synchronizeLightning_LOOP(syncLightning);
    }

    @PostMapping("/device/switch/set")
    public ResponseEntity<User> setSwitch(@RequestBody SwitchDto switchDto, Principal principal) throws ParseException {
        System.out.println(switchDto);
        return deviceService.setSwitch(principal.getName(), switchDto);
    }

    @PostMapping("/device/switch/sync")
    public ResponseEntity<SyncSwitch> synchronizeSwitch_LOOP(@RequestBody SyncSwitch syncSwitch) throws ParseException {
        System.out.println(syncSwitch);
        return deviceService.synchronizeSwitch_LOOP(syncSwitch);
    }
}